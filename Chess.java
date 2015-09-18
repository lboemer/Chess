import java.util.*;
import java.util.Scanner;                                                       // Import Scanner class
import javax.swing.*;                                                           //notice javax
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Chess
{
    public static final int MOVE_NOT_POSSIBLE           = 100000;

    public static int Total_Move_Counter                = 0;

    public static int[]     Iteration_Move_Counter      = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH];
    public static int[]     Display_Move_Counter        = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH];
    public static int[]     AlphaBetaCounter            = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH];

    // Display vriables
    public static int       ShowStatus;
    public static int       DebugLevel;   
    
    // Setting variables
    public static int       MaxMoves;                          
    public static int       MaxMoveDepth;
    public static long      MaxSeconds;    
    public static int       DecisionRule; 
    public static int       PlayMode;
    public static int       FirstMove; 
    
    public static int       Iteration;
    public static int       Ply;

    public static int       MoveDepth;
    public static boolean   WhiteBoard = false;
    
    // timing variables
    public static long      Start;
    public static long      End;

    public static long      SecondsUsed;
    public static long      MilliSecondsUsed;
    
    public static long      UserTotal_ms;
    public static long      ComputerTotal_ms;
    
    JButton pressme         = new JButton("Press Me");

    public static String str = "Leo test \n new line";
    
    public static JFrame frame = new JFrame("Leo Java Chess");
    public static UserInterface ui = new UserInterface();
     
    public static Scanner scanner             = new Scanner(System.in);
    
    public static void Info()
    { 
        System.out.println("Chess Program by Leopold Boemer");
        System.out.println("Version V6.6");
        System.out.println("Date 2015-04-21");        
        System.out.println();
    }
    
    Chess()
    {

    }
    
    public static void main(String[] args)
    {
        int col;
        int i;
        int j;
        int alpha;
        int beta;
        char UserEnter;
        boolean NewGame             = false;
        boolean GetNewUserMove      = false;    
        String inputString;
        int LocalRating             = 0;
        int Iteration_Finished      = 0;
        Start                       = System.currentTimeMillis( );
        char[] ch                   = new char[100];  
        int[][] Pos                 = new int[Position.ROWS + 1][Position.COLS + 1];
        boolean ShowPositionStatus;
        Scanner scanner             = new Scanner(System.in);
        
        char P;
        char Px;
        int MoveNumber;
        
        long UserBegin_ms;
        long UserEnd_ms;
        long ComputerBegin_ms;  
        long ComputerEnd_ms;
        
        float SecondsUsedFloat;
        float RatingFloat;
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(ui);
        
        frame.setSize(Position.COLS * UserInterface.SQUARE_SIZE + 100, Position.ROWS * UserInterface.SQUARE_SIZE + 200);
        frame.setVisible(true);
       
        System.out.println(new Date( ));
        System.out.println();
        Info();        
        ShowPositionStatus = true;
        
        int[][] MovePath                        = new int[Move.MAX_NUMBER_MOVE_LIST + Settings.ABSOLUTE_MAX_MOVE_DEPTH][Move.ENTRIES_MOVE_LIST];    // Holds move history and move that the computer is currently analyzing 
        int[][] MoveBest                        = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH][Move.ENTRIES_MOVE_LIST];    // Holds best move
        int[][] MoveBestFinishedIteration       = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH][Move.ENTRIES_MOVE_LIST];    // Holds best move        
        boolean Back = false;
        
        String[] MoveTable = new String[Move.MAX_NUMBER_MOVE_LIST];
        
        do{                                                                     // New Game
            Move.EmptyMoveList(MovePath);
            Move.EmptyMoveList(MoveBest);          
            Move.EmptyMoveList(MoveBestFinishedIteration);            
            
            Ply = 0;
            Iteration_Move_Counter[0] = 1;
            MoveNumber        = 0;
      
            Settings.InitiateSettings(Pos);
            
            Settings.GetUserInput(Pos);

      
            UserTotal_ms = 0;
            ComputerTotal_ms = 0;
                          
            do
            {
                UserBegin_ms = System.currentTimeMillis( );
                MoveNumber++;  

                if((MoveNumber == 1) && (FirstMove == Settings.PLAYER) && ((PlayMode == Settings.PLAYER_PLAYER) || (PlayMode == Settings.PLAYER_COMPUTER))
                 || (MoveNumber >  1) &&                                   ((PlayMode == Settings.PLAYER_PLAYER) || (PlayMode == Settings.PLAYER_COMPUTER)))
                {
                    do
                    {
                        switch(ui.GetUserMoveFromMouseInput(Pos, MovePath, MoveTable, MoveBestFinishedIteration, Back))
                        {
                            case '0':                                           // User made a valid move
                                Move.DisplayMoveTable(MovePath, Move.STOP, Ply, MoveTable, Move.TABLE, Move.SHOW_NO_RATING);  
                                Position.SwitchMoveColor(Pos);                  // Switch move color
                                ui.repaintWindow(Pos); 
                                NewGame = false;
                                GetNewUserMove = false;
                                Back = false;
                                break;
                    
                            case '1':                                           // Computer to make next move instead of player
                                NewGame = false;
                                GetNewUserMove = false;
                                Back = false;
                                break;                                          // Leaves switch()
                         
                            case '2':            
                                Move.RevertMove(Pos, MovePath);                                
                                ui.repaintWindow(Pos);
                                
                                GetNewUserMove = true;
                                Back = true;
                                break;                                          // Leaves switch()       
                            
                            case 'x':
                                System.out.println("Entered x: Exit program!");
                                NewGame = true;
                                GetNewUserMove = false;
                                Back = false;
                                break;
                        }        
                    }while(GetNewUserMove);
                    
                    if(NewGame)
                    {
                        break;                                                  // Leaves do loop to start new game
                    }

                    if(Position.EndPosition(Pos, MovePath, ShowPositionStatus))
                    {
                        System.out.println("After user move: Game ended!");
                        break;
                    }
                }
            
                UserEnd_ms = System.currentTimeMillis();                
                UserTotal_ms += (UserEnd_ms - UserBegin_ms); 

                ComputerBegin_ms = UserEnd_ms;
                
                if((PlayMode == Settings.PLAYER_COMPUTER) || (PlayMode == Settings.COMPUTER_COMPUTER))            
                {                                                               // Computer to make move
                    Start = System.currentTimeMillis( );
                    Total_Move_Counter = 0;
        
                    Iteration = 1;
            
                    for(i = 0; i < MaxMoveDepth; i++)
                    {
                        AlphaBetaCounter[i] = 0;
                    }
            
                    System.out.println("\nDepth  Moves   Rating   MoveBest") ;
                 
                    for(MoveDepth = 1; MoveDepth <= MaxMoveDepth; MoveDepth++)  // Generate Computer move
                    {
                        alpha = -(Rating.CHECKMATE_RATING + 1);                 // alpha = min, min is smaller than checkmate rating such that a checkmate rating triggers an update of alpha
                        beta  =   Rating.CHECKMATE_RATING + 1;                  // beta = max,  max is larger  than checkmate rating such that a checkmate rating triggers an update of beta
            
                        Iteration_Move_Counter[Iteration] = 0;
                        if(DebugLevel > Settings.LOW)
                        {
                            System.out.println("Message from Main Program: MoveDepth = " + MoveDepth + " MaxMoveDepth = " + MaxMoveDepth);
                            System.out.print("\n");
                        }

                        LocalRating = IterateMove(Pos, alpha, beta, MovePath, MoveBest);                       
                        
                        if(ShowStatus > Settings.LOW)
                        {
                            switch(DecisionRule)
                            {
                                case Settings.MINMAX:
                                    break;
                                
                                case Settings.ALPHA_BETA:
                                    {
                                        for(i=1; i <= MoveDepth; i++)
                                        {
                                            System.out.println("AlphaBetaCounter[" + i + "] = " + AlphaBetaCounter[i]);
                                        }
                                    }
                                     break;
                            }
                        }
            
                        if((Total_Move_Counter >= MaxMoves) || (SecondsUsed >= MaxSeconds))
                        {
                            break;
                        }
  
                        Move.CopyMoveList(MoveBest, 0, MoveBestFinishedIteration);                              
                        
                        
                        System.out.print(MoveDepth);                            // Only show finished MoveDepth evaluation
                        System.out.format("  %9d", Total_Move_Counter); 
                        
                        RatingFloat = LocalRating;
                        RatingFloat /= 100;   
                        
                        if((LocalRating == Rating.CHECKMATE_RATING) || (LocalRating == -Rating.CHECKMATE_RATING))
                        {
                            System.out.format("   #        ");
                        }                         
                        else
                        {
                            System.out.format("   %+.3f   ", RatingFloat);   
                        }
                        Move.DisplayMoveTable(MoveBest, Move.ALL, Ply, MoveTable, Move.LINE, Move.SHOW_NO_RATING);   

                        if((LocalRating == Rating.CHECKMATE_RATING) || (LocalRating == -Rating.CHECKMATE_RATING))
                        {
                            break;                                              // ?? May I need EndPosition()   ?
                        }                                              
                    }                      
                    
                    System.out.println();
                    if(ShowStatus > Settings.ZERO)
                    {
                        if(SecondsUsed >= MaxSeconds)
                        {
                            System.out.println("Finished by using allowed time.");
                        }
               
                        if(Total_Move_Counter >= MaxMoves)
                        {
                            System.out.println("Finished by using given moves.");
                        }
        
                        if((MoveDepth > MaxMoveDepth) && (SecondsUsed < MaxSeconds))
                        {
                            System.out.println("Finished by evaluating all possible moves of given half moves ahead.");
                        }
        
                        if((LocalRating == Rating.CHECKMATE_RATING) || (LocalRating == -Rating.CHECKMATE_RATING))
                        {
                            System.out.println("Finished by finding checkmate.");   
                            MoveDepth++;
                        }
                        
                        SecondsUsedFloat = MilliSecondsUsed;
                        SecondsUsedFloat /= 1000;
                        System.out.println("Used " + SecondsUsedFloat + " sec of allowed " + MaxSeconds + " sec.");
                        System.out.println("Used " + Total_Move_Counter + " moves out of " + MaxMoves + " allowed moves.");
                        System.out.println("Evaluated " + MoveDepth + " of allowed " + MaxMoveDepth + " half moves ahead.\n");
                    }
                    
                    Move.MakeMove(Pos,   
                             MoveBestFinishedIteration[0][Move.ROW],                                     
                             MoveBestFinishedIteration[0][Move.COL],      
                             MoveBestFinishedIteration[0][Move.FIGURE_N],      
                             MoveBestFinishedIteration[0][Move.ROW_N],                                     
                             MoveBestFinishedIteration[0][Move.COL_N],                     
                             MovePath, Move.ADD_TO_MOVE_HISTORY);                                       
                    
                    Position.SwitchMoveColor(Pos); 
                    ShowPositionStatus = false;

                    if(Position.EndPosition(Pos, MovePath, ShowPositionStatus))
                    {
                        ComputerEnd_ms = System.currentTimeMillis( );                  
                        ComputerTotal_ms += (ComputerEnd_ms - ComputerBegin_ms);                 
                
                        ui.repaintWindow(Pos);                           
                        
                        break;    
                    }
                }
                
                ComputerEnd_ms = System.currentTimeMillis( );                  
                ComputerTotal_ms += (ComputerEnd_ms - ComputerBegin_ms);                 
                
                ui.repaintWindow(Pos);                
            }
            while(true);
            
            ui.repaintWindow(Pos);                    
        }
        while(Settings.NewGame(Pos, MovePath));
        
        System.out.println("Ended Program");   
    } 

    public static int IterateMove(int[][] Pos, int alpha, int beta, int[][] MovePath, int[][] MoveBestUpper)
    {    
        int[][] MoveRating          = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH][Move.ENTRIES_MOVE_LIST];    // Holds move that the computer is currently analyzing 
        int[][] MovesPosition       = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];           // Holds all possible moves for one position
        int minmax                  = 0;
        int RatingScore;
        int ReturnValue             = 0;      
        int p;
        boolean ReturnOnFirstMovePossible = false;
        
        int[][] PosStore = new int[Position.ROWS + 1][Position.COLS + 1];
        String[] MoveTable = new String[Move.MAX_NUMBER_MOVE_LIST];
        
        
        Move.EmptyMoveList(MoveRating);    
        Move.EmptyMoveList(MovesPosition);  
        Move.MoveListIteration  = 1;   
        
        Position.GenerateMoveList(Pos, MovesPosition, MovePath, ReturnOnFirstMovePossible);                    // Generates all possible moves for one position into MovesPosition
             
        Move.SortMoveList(MovesPosition);                                       // Sorts the move list and places best move first

        switch(DecisionRule)
        {  
            case Settings.MINMAX:
                if(Position.GetMoveColor(Pos) == Position.WHITE_MOVE)           // Max node
                {                                                               
                    minmax = - (Rating.CHECKMATE_RATING + 1);
                    if(ShowStatus > Settings.LOW)
                    {                    
                        System.out.println("Iteration " + Iteration + " Set minmax to " + minmax);  
                    }
                }
                else                                                            // Min node
                {                                                                                           
                    minmax = Rating.CHECKMATE_RATING + 1;
                    if(ShowStatus > Settings.LOW)
                    {                     
                        System.out.println("Iteration " + Iteration + " Set minmax to " + minmax);
                    }
                }
                break;
            
            case Settings.ALPHA_BETA:
                break;
        }        
        
        loop_over_all_moves_in_one_position:                                    // Set break point 
        for(p = 0; MovesPosition[p][Move.FIGURE] != Position.EMPTY; p++)        // Loop over all possible moves in one position
        {    
            if(Total_Move_Counter > MaxMoves - 1)
            {
                return MOVE_NOT_POSSIBLE;                  
            }

            MilliSecondsUsed = (System.currentTimeMillis()- Start);
            SecondsUsed = MilliSecondsUsed / 1000; 
            if(SecondsUsed >= MaxSeconds)
            {
                return MOVE_NOT_POSSIBLE ;    
            }
            
            Total_Move_Counter++;                                               // Valid move, increase total move counter
            Iteration_Move_Counter[Iteration] ++;                               // Increase count of moves in current iteration level
            Display_Move_Counter[Iteration] ++;                                 // Increase count of moves in current iteration level      
          
            Move.SetMove(MovesPosition, p, MovePath, Ply + Iteration - 1);      // Adds move p from MovesPosition to MovePath and sets reminder of MovePath to 0  
            
            if((MovesPosition[p][Move.POSITION_STATUS] == Position.NO_CONDITION) && (Iteration < MoveDepth))   
            {                                                                   // Parentnode, create children  
                Position.CopyPosition(Pos, PosStore);                           // Store position
                
                Position.SwitchMoveColor(Pos);                                  // Switch move color
                Iteration ++;                                                   // Increase counter for move iteration 
                Iteration_Move_Counter[Iteration] = 0;                          // Reset counter for move iteration
                Display_Move_Counter[Iteration] = 0;  
                
                Move.MakeMove(Pos,  MovesPosition[p][Move.ROW],
                                    MovesPosition[p][Move.COL], 
                                    MovesPosition[p][Move.FIGURE_N], 
                                    MovesPosition[p][Move.ROW_N], 
                                    MovesPosition[p][Move.COL_N], 
                                    MovePath, 
                                    Move.DO_NOT_ADD_TO_MOVE_HISTORY);  
                RatingScore = IterateMove(Pos, alpha, beta, MovePath, MoveRating);   

                Iteration--;                                                    // Decrease counter for move iteration
                Iteration_Move_Counter[Iteration + 1] = 0;                      // Not sure if required
                Display_Move_Counter[Iteration + 1] = 0;                        // Not sure if required

                Position.CopyPosition(PosStore, Pos);                           // Restore position          
            
            }
            else                                                                // Child node
            {
                RatingScore = MovesPosition[p][Move.RATING];  
                Move.CopyMoveList(MovePath, Ply, MoveRating);                   // Copies move under investigation to MoveRating                       
            }
            
            if(RatingScore == MOVE_NOT_POSSIBLE)
            {
                return MOVE_NOT_POSSIBLE;
            } 
            
            switch(DecisionRule)
            {
                case Settings.MINMAX:
                    if(((Position.GetMoveColor(Pos) == Position.WHITE_MOVE) && (RatingScore > minmax)) || ((Position.GetMoveColor(Pos) == Position.BLACK_MOVE) && (RatingScore < minmax)))
                    {                     
                        minmax = RatingScore;    
                        Move.CopyMoveList(MoveRating, 0, MoveBestUpper);
                        
                        if(ShowStatus > Settings.LOW)
                        {                        
                            System.out.print("Iteration[" + Iteration + "] \t New Minmax = " + minmax + "\t");                        
                            System.out.print("MoveBestUpper[" + Iteration +"] ");
                            Move.DisplayMoveTable(MoveBestUpper, Move.ALL, Ply, MoveTable, Move.LINE, Move.SHOW_RATING_LAST_MOVE);   
                        }
                    }                                           
                    break;
                                                
                case Settings.ALPHA_BETA:                
                    if((Position.GetMoveColor(Pos) == Position.WHITE_MOVE) && (RatingScore > alpha))
                    {                                                           // Max node
                        alpha = RatingScore;                         
                        Move.CopyMoveList(MoveRating, 0, MoveBestUpper);
                    }
                    if((Position.GetMoveColor(Pos) == Position.BLACK_MOVE) && (RatingScore < beta))                                                    
                    {                                                           // Min node
                        beta = RatingScore;
                        Move.CopyMoveList(MoveRating, 0, MoveBestUpper);
                    }
                    if((alpha >= beta) || (alpha == Rating.CHECKMATE_RATING) || (beta == -Rating.CHECKMATE_RATING))             
                    {                                                           // No more siblings also called alpha-beta pruned
                        AlphaBetaCounter[Iteration] ++;
                        break loop_over_all_moves_in_one_position;
                    }  
                    break; 
            }
        }

        switch(DecisionRule)
        {
            case Settings.MINMAX:
                return minmax;
                          
            case Settings.ALPHA_BETA:
                if(Position.GetMoveColor(Pos) == Position.WHITE_MOVE)           // Max node
                {                                 
                    return alpha;              
                }
                if(Position.GetMoveColor(Pos) == Position.BLACK_MOVE)           // Min node                              
                {                                    
                    return beta;
                } 
                break;  
        }
        return 0;                                                               // This statement is not reached and is added to make compiler happy  
    }    

    public static boolean SetBoard(int[][] Pos)
    {
        return (((Chess.FirstMove == Settings.PLAYER)      && (Position.GetMoveColor(Pos) == Position.WHITE_MOVE)) ||
                ((Chess.FirstMove == Settings.COMPUTER)    && (Position.GetMoveColor(Pos) == Position.BLACK_MOVE)));  
    } 
}

class MyKeyListener implements KeyListener {
  public void keyTyped(KeyEvent e) {
    char c = e.getKeyChar();
    System.out.println("Key Typed: " + c);
  }
  public void keyPressed(KeyEvent e) {
    char c = e.getKeyChar();
    System.out.println("Key Pressed: " + c);
  }
  public void keyReleased(KeyEvent e) {
    char c = e.getKeyChar();
    System.out.println("Key Released: " + c);
  }
}