/**
 
V1.0 2015-03-29 

V1.1 2015-03-29
- removed code that is replaced by move lists

V1.2 2015-04-21
- introduced ClearScreen
- updated menus

V1.3 2015-04-25
- moved GetUserMove() from Chess. to Settings.
- moved UserMoveSuccessful() from Chess. to Move.
- moved OwnFigure(), GetMoveColor(), OpponentFigure(), OpponentKing(), OffBoardOrOwnFigure(), OnBoard() from Chess. to Position.
- moved FigureLists{} from Chess to Position 

V1.4 2015-05-11
- Updated main such that, if UserMove() returns x, program goes to NewGame menue instead of exiting program
- added SortMoveList() 
- swapped col and row in Pos[][]

V1.5 2015-05-21
- introduced MoveBestFinishedIteration
- introduced Back variable to not not show last best move

V1.6 2015-06-01
- introduced RatingFloat and SecondsUsedFloat to simplify System.out
- show CHECKMATE_RATING as #

V1.7 2015-07-05
-  Pos[][] moved to in class Chess used by class UserInterface

V1.8 2015-07-25
-  added ui.repaintWindow() for move backwards
-  set UserInterface.WhiteBoard to draw white of Black board

V1.9 2015-08-30
- added fifty move rule

V2.0 2015-08-30
- meregd MoveIteration and MoveHistory to MovePath

**/

import java.util.*;
import java.text.*;
 
import java.util.Scanner;   // Import Scanner class (contains methods
import java.io.*;

/// From frames
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  //notice javax

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

//import javax.swing.*;
//import java.awt.event.*;
public class Chess

//public class Chess extends JFrame implements ActionListener
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
   
    // timing variables
    public static long      Start;
    public static long      End;

    public static long      SecondsUsed;
    public static long      MilliSecondsUsed;
    
    public static long      UserTotal_ms;
    public static long      ComputerTotal_ms;
    
    private static Position myPosition = new Position();
 
    //JLabel answer           = new JLabel("");             // create pane object
    //JPanel pane             = new JPanel();
    JButton pressme         = new JButton("Press Me");
    
    //public static int[][] Pos                 = new int[Position.COLS+1][Position.ROWS+1];
    //public static int[][] MoveHistory           = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];           // Holds performed moves        
    
    public static String str = "Leo test \n new line";
    
    public static        JFrame frame = new JFrame("Leo Java Chess");
    public static UserInterface ui = new UserInterface();
     
    public static void Info()
    { 
        System.out.println("Chess Program by Leopold Boemer");
        System.out.println("Version V6.6");
        System.out.println("Date 2015-04-21");        
        System.out.println();
    }
    
    Chess() // the frame constructor method
    {
        //super("Event Handler Demo"); 
        //setBounds(100,100,300,200);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Container con = this.getContentPane(); // inherit main frame
        //con.add(pane); // add the panel to frame
        // customize panel here
        // pane.add(someWidget);
        
        
        ///pressme.setMnemonic('P'); // associate hotkey to button
        //pane.add(pressme); 
        //pressme.addActionListener(this);   // register button listener
        ///pane.add(answer); 
        //pane.add(pressme); 
        //pressme.requestFocus();
        
        //pressme.requestFocus();
        
        
        //setVisible(true); // display this frame
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
        int LocalRating                  = 0;
        int Iteration_Finished      = 0;
        Start                       = System.currentTimeMillis( );
        char[] ch                   = new char[100];  
        int[][] Pos                 = new int[Position.COLS+1][Position.ROWS+1];
        boolean ShowPositionStatus;
        Scanner scanner             = new Scanner(System.in);
        
        char P;
        char Px;
        int MoveNumber = 0;
        
        long        UserBegin_ms;
        long        UserEnd_ms;
        long        ComputerBegin_ms;  
        long        ComputerEnd_ms;
        
        float       SecondsUsedFloat;
        float       RatingFloat;
        
        
        //JFrame frame = new JFrame("Leo Java Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //UserInterface ui = new UserInterface();
        frame.add(ui);
        
        
        //JTextField textField = new JTextField();
        //textField.addKeyListener(new MyKeyListener());
        //frame.add(textField);
        //frame.setSize(Position.COLS * UserInterface.SQUARE_SIZE + 10, Position.ROWS * UserInterface.SQUARE_SIZE + 100);
        
        
        frame.setSize(Position.COLS * UserInterface.SQUARE_SIZE + 100, Position.ROWS * UserInterface.SQUARE_SIZE + 200);
        frame.setVisible(true);
        
       
        System.out.println(new Date( ));
        System.out.println();
        Info();        
        ShowPositionStatus = true;
        
        //int[][] MoveIteration                 = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH][Move.ENTRIES_MOVE_LIST];    // Holds move that the computer is currently analyzing 
        int[][] MovePath                        = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH][Move.ENTRIES_MOVE_LIST];    // Holds move history and move that the computer is currently analyzing 
        int[][] MoveBest                        = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH][Move.ENTRIES_MOVE_LIST];    // Holds best move
        int[][] MoveBestFinishedIteration       = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH][Move.ENTRIES_MOVE_LIST];    // Holds best move        
        //int[][] MoveHistory                 = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];           // Holds performed moves    
        boolean Back = false;
        
        String[] MoveTable = new String[Move.MAX_NUMBER_MOVE_LIST];
        
        
        do{ // New Game
            //Move.EmptyMoveList(MoveIteration);
            Move.EmptyMoveList(MovePath);
            Move.EmptyMoveList(MoveBest);          
            Move.EmptyMoveList(MoveBestFinishedIteration);            
            //Move.EmptyMoveList(MoveHistory); 
            
            Ply = 0;
            Iteration_Move_Counter[0] = 1;
      
            Settings.InitiateSettings(Pos);
            if(Position.GetMoveColor(Pos) == Position.WHITE_MOVE)
            {
                UserInterface.WhiteBoard = true;
                System.out.println("UserInterface.WhiteBoard = " + UserInterface.WhiteBoard);
            }
            else
            {
                UserInterface.WhiteBoard = false;
                System.out.println("UserInterface.WhiteBoard = " + UserInterface.WhiteBoard);
            }
                          
            ui.repaintWindow(Pos);                  // draws beginning position
        
            if(Settings.GetUserInput(Pos) == false)
            {
                return;
            }
            
            if(Position.GetMoveColor(Pos) == Position.WHITE_MOVE)
            {
                UserInterface.WhiteBoard = true;
                System.out.println("UserInterface.WhiteBoard = " + UserInterface.WhiteBoard);
            }
            else
            {
                UserInterface.WhiteBoard = false;
                System.out.println("UserInterface.WhiteBoard = " + UserInterface.WhiteBoard);
            }            
            
            ui.repaintWindow(Pos);                  // draws new beginning position
            //Start = System.currentTimeMillis( );            
            UserBegin_ms        = 0;
            UserTotal_ms = 0;
            UserEnd_ms = 0;
            ComputerBegin_ms = 0;
            ComputerTotal_ms = 0;
            ComputerEnd_ms =0;           
                          
            do
            {
                //System.out.println("Entering user - computer move loop...");
                //Settings.ShowMoveColor(Pos);
                
                UserBegin_ms = System.currentTimeMillis( );
                
                MoveNumber++;
                if((MoveNumber == 1) && (FirstMove == Settings.PLAYER) && ((PlayMode == Settings.PLAYER_PLAYER) || (PlayMode == Settings.PLAYER_COMPUTER))
                 || (MoveNumber >  1) &&                                   ((PlayMode == Settings.PLAYER_PLAYER) || (PlayMode == Settings.PLAYER_COMPUTER)))
                {
                    
                    do
                    {
                        switch(ui.GetUserMoveFromMouseInput(Pos, MovePath, MoveBestFinishedIteration, Back))
                        {
                            case '0':                                       // User made a valid move
                                //System.out.println("In main... Ply in main()= " + Ply);
                                //scanner.nextLine();
                            
                            
                                Move.DisplayMoveList(MovePath, Move.STOP, Ply, Move.TABLE, Move.SHOW_NO_RATING);
                                //Move.CreateMoveTable(MoveHistory, Move.TABLE, Move.SHOW_NO_RATING);         
  
                                
                                //Move.CreateMoveTable(MovePath, Move.STOP, Ply, MoveTable, Move.TABLE, Move.SHOW_NO_RATING);  
                                //Move.DisplayMoveTableConsole(MoveTable);
                       
                                //scanner.nextLine();  
                                
                                ui.repaintWindow(Pos);                               
                                
                                Position.SwitchMoveColor(Pos);              // Switch move color
                                NewGame = false;
                                GetNewUserMove = false;
                                Back = false;
                                break;
                    
                            case '1':                                       // Computer to make next move instead of player
                                NewGame = false;
                                GetNewUserMove = false;
                                Back = false;
                                break;  // Leaves switch()
                         
                            case '2':            
                                //Move.RevertMove(Pos, MoveHistory);
                                Move.RevertMove(Pos, MovePath);                                
                                ui.repaintWindow(Pos);
                                
                                GetNewUserMove = true;
                                Back = true;
                                break;  // Leaves switch()       
                            
                            case 'x':
                                System.out.println("Entered x: Exit program!");
                                NewGame = true;
                                GetNewUserMove = false;
                                Back = false;
                                //return;
                                break;
                        }        
                        
                        //UserMoveInput = true;  // Get inpuit from Mouse
                    }while(GetNewUserMove);
                    
                    if(NewGame)
                    {
                        break; // Leaves do loop to start new game
                    }
                    
                    //if(Position.EndPosition(Pos, MoveHistory, ShowPositionStatus))
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
                {
                    //System.out.println("Entering computer Move...");
                    //Settings.ShowMoveColor(Pos);

                    // Computer to make move
                    Start = System.currentTimeMillis( );
                    Total_Move_Counter = 0;
        
                    Iteration = 1;
            
                    for(i = 0; i < MaxMoveDepth; i++)
                    {
                        AlphaBetaCounter[i] = 0;
                    }
            
                    System.out.println("\nDepth  Moves   Rating   MoveBest") ;
                    // Generate Computer move
                    for(MoveDepth = 1; MoveDepth <= MaxMoveDepth; MoveDepth++)
                    {
                        alpha = -(Rating.CHECKMATE_RATING + 1);         // alpha = min, min is smaller than checkmate rating such that a checkmate rating triggers an update of alpha
                        beta  =   Rating.CHECKMATE_RATING + 1;          // beta = max,  axn is larger  than checkmate rating such that a checkmate rating triggers an update of beta
            
                        Iteration_Move_Counter[Iteration] = 0;
                        if(DebugLevel > Settings.LOW)
                        {
                            System.out.println("Message from Main Program: MoveDepth = " + MoveDepth + " MaxMoveDepth = " + MaxMoveDepth);
                            System.out.print("\n");
                        }

                        //LocalRating = IterateMove(Pos, alpha, beta, MoveIteration, MoveBest, MoveHistory);
                        LocalRating = IterateMove(Pos, alpha, beta, MovePath, MoveBest);
                        //LocalRating = IterateMove(alpha, beta, MoveIteration, MoveBest, MoveHistory);
                        //System.out.print(MoveDepth);        
                        //System.out.format("  %9d", Total_Move_Counter);  
                        //System.out.format("   %6d   ", rating);                        
                        //Move.DisplayMoveList(MoveBest, Move.LINE, Move.SHOW_RATING_LAST_MOVE);                        
                        
                        switch(DecisionRule)
                        {
                            case Settings.ALPHA_BETA:
                                if(ShowStatus > Settings.LOW)
                                {
                                    for(i=1; i <= MoveDepth; i++)
                                    {
                                        System.out.println("AlphaBetaCounter[" + i + "] = " + AlphaBetaCounter[i]);
                                    }
                                }
                                break;
                        }
            
                        if((Total_Move_Counter >= MaxMoves) || (SecondsUsed >= MaxSeconds))  // 
                        {
                            break;
                        }

                        //System.out.println("Before copy MoveBest");
                        //System.out.println("MoveBest");
                        //Move.DisplayMoveList(MoveBest, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);
                        //System.out.println("MoveBestFinishedIteration");
                        //Move.DisplayMoveList(MoveBestFinishedIteration, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);                        
                        //scanner.nextLine();        
                        
                                    
                        Move.CopyMoveList(MoveBest, 0, MoveBestFinishedIteration);  
 

                        //System.out.println("aftre copy MoveBest");
                        //System.out.println("MoveBest");
                        //Move.DisplayMoveList(MoveBest, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);
                        //System.out.println("MoveBestFinishedIteration");
                        //Move.DisplayMoveList(MoveBestFinishedIteration, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);                        
                        //scanner.nextLine();                             
                        
                        // Only show finished MoveDepth evaluation
                        System.out.print(MoveDepth);        
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
                        Move.DisplayMoveList(MoveBest, Move.ALL, 0, Move.LINE, Move.SHOW_NO_RATING);        
                        
                        //Move.CreateMoveTable(MoveBest, Move.LINE, Move.SHOW_NO_RATING);                              
                        //Move.DisplayMoveTable();                                 

                        if((LocalRating == Rating.CHECKMATE_RATING) || (LocalRating == -Rating.CHECKMATE_RATING))
                        {
                            break;
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
                             MoveBestFinishedIteration[0][Move.COL],                                     
                             MoveBestFinishedIteration[0][Move.ROW],      
                             MoveBestFinishedIteration[0][Move.FIGURE_N],      
                             MoveBestFinishedIteration[0][Move.COL_N],                                     
                             MoveBestFinishedIteration[0][Move.ROW_N],                    
                             //MoveHistory, Move.ADD_TO_MOVE_HISTORY);   
                             MovePath, Move.ADD_TO_MOVE_HISTORY);                               
                             
                    //Settings.ClearScreen(Pos);          
                    
                    Position.SwitchMoveColor(Pos); 
                    ShowPositionStatus = false;

                    //if(Position.EndPosition(Pos, MoveHistory, ShowPositionStatus))
                    if(Position.EndPosition(Pos, MovePath, ShowPositionStatus))
                    {
                        //System.out.println("After computer move: Game ended!");
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
        //while(Settings.NewGame(Pos, MoveHistory));
        while(Settings.NewGame(Pos, MovePath));
        
        System.out.println("Ended Program");   
    } 
    
    //public static int IterateMove(int[][] Pos, int alpha, int beta, int[][] MoveIteration, int[][] MoveBestUpper, int[][] MoveHistory)
    public static int IterateMove(int[][] Pos, int alpha, int beta, int[][] MovePath, int[][] MoveBestUpper)
    //public static int IterateMove(int alpha, int beta, int[][] MoveIteration, int[][] MoveBestUpper, int[][] MoveHistory)
    {    
        int[][] MoveRating          = new int[Settings.ABSOLUTE_MAX_MOVE_DEPTH][Move.ENTRIES_MOVE_LIST];    // Holds move that the computer is currently analyzing 

        //int[][] MoveList       = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];
        int[][] MovesPosition       = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];
        int[] CastlingLocal         = new int[4];        
        int minmax                  = 0;
        int RatingScore             = 0;
        int Figure                  = 0;
        int TempPawn                = 0;
        int temp_ep                 = 0;
        int ReturnValue             = 0;      
        int EnPassantStatus         = 0;
        boolean CheckForCanTakeKingOnly = false;
        int RepetitivePositionsCounterLocal;
        int FiftyMoveCounterLocal;       
        int i;
        int p;
        int col;
        int row;                
        int Figure_p; 
        int Figure_n;                 
        int col_n;
        int row_n;   
                
        Scanner scanner             = new Scanner(System.in);
        //int NumberOfMoves;
        
        Move.EmptyMoveList(MoveRating); 
     
        //Move.EmptyMoveList(MoveList); 
        Move.EmptyMoveList(MovesPosition);  
        
        //NumberOfMoves = Move.GenerateMoveList(Pos, MoveList, MoveHistory);
        Move.MoveListIteration  = 1;
        //Move.GenerateMoveList(Pos, MoveList, MoveHistory, CheckForCanTakeKingOnly);    
        //Move.GenerateMoveList(Pos, MoveList, MoveIteration, MoveHistory, CheckForCanTakeKingOnly);    
        Move.GenerateMoveList(Pos, MovesPosition, MovePath, CheckForCanTakeKingOnly);    
        //Position.GenerateCandidateMoveList(Pos, CandidateMoveList);
        
       
        //System.out.println("NumberOfMoves = " + NumberOfMoves);
        
        
        //System.out.println("MoveList befor sorting");
        //Move.DisplayMoveList(MovesPosition, Move.ALL, 0, Move.LIST, Move.SHOW_RATING_EVERY_MOVE);        
        
        //Move.SortMoveList(MoveList);
        Move.SortMoveList(MovesPosition);
        
        //System.out.println("\nMoveList after sorting");
        //Move.DisplayMoveList(MovesPosition, Move.ALL, 0, Move.LIST, Move.SHOW_RATING_EVERY_MOVE);

        switch(DecisionRule)
        {  
            case Settings.MINMAX:
                if(Position.GetMoveColor(Pos) == Position.WHITE_MOVE)
                {   // Max node
                    minmax = - (Rating.CHECKMATE_RATING + 1);
                    if(ShowStatus > Settings.LOW)
                    {                    
                        System.out.println("Iteration " + Iteration + " Set minmax to " + minmax);  
                    }
                }
                else
                {   //Min node
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
        
        loop_over_all_moves_in_one_position:                                // set break point 
        for(p = 0; MovesPosition[p][Move.FIGURE] != Position.EMPTY; p++)
        {
            
            if(Total_Move_Counter > MaxMoves - 1)
            {
                if(DebugLevel > Settings.MEDIUM)
                {
                    System.out.println("Reached max Moves = " + Total_Move_Counter);
                }       
                return MOVE_NOT_POSSIBLE;                  
            }

            MilliSecondsUsed = (System.currentTimeMillis()- Start);
            SecondsUsed = MilliSecondsUsed / 1000; 
            //System.out.println("Seconds allowed = " + MaxSeconds + "Seconds used = " + SecondsUsed);
            if(SecondsUsed >= MaxSeconds)
            {
                if(DebugLevel > Settings.MEDIUM)
                {
                    System.out.println("Reached max seconds allowed = " + MaxSeconds);
                }  
                return MOVE_NOT_POSSIBLE ;    
            }
            
            Total_Move_Counter++;                                   // Valid move, increase total move counter
            Iteration_Move_Counter[Iteration] ++;                   // Increase count of moves in current iteration level
            Display_Move_Counter[Iteration] ++;                     // Increase count of moves in current iteration level
        
            //Move.SetMove(MoveList, l, MoveIteration, Iteration -1);
            
            
            //System.out.println("MovePath 1");
            //Move.DisplayMoveList(MovePath, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);
            
            //System.out.println("Ply = " + Ply + " Iteration = " + Iteration);
            
            //scanner.nextLine();         
          
            Move.SetMove(MovesPosition, p, MovePath, Ply + Iteration - 1); 
            
            // System.out.println("MovePath 2");            
            //Move.DisplayMoveList(MovePath, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);     
            // scanner.nextLine();             
            
            if(ShowStatus > Settings.LOW)
            {
                System.out.print("Move " + Total_Move_Counter + "\t Iteration[" + Iteration + "] \t");
                //Move.DisplayMoveList(MoveIteration, Move.LINE, Move.SHOW_RATING_LAST_MOVE);
               //Move.DisplayMoveList(MovePath, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);
            }     
            //RatingScore = MoveList[l][Move.RATING];
            
            if((MovesPosition[p][Move.POSITION_STATUS] == Position.NO_CONDITION) && (Iteration < MoveDepth))  // Parentnode, create children   

            {                                                                       
                Position.SwitchMoveColor(Pos);                                      // Position.Switch move color
                Iteration ++;                                                     // Increase counter for move iteration 
                Iteration_Move_Counter[Iteration] = 0;                              // Reset counter for move iteration
                Display_Move_Counter[Iteration] = 0;  
                
                Figure              = MovesPosition[p][Move.FIGURE];                 
                col                 = MovesPosition[p][Move.COL];
                row                 = MovesPosition[p][Move.ROW];
                Figure_p            = MovesPosition[p][Move.FIGURE_P]; 
                Figure_n            = MovesPosition[p][Move.FIGURE_N];                 
                col_n               = MovesPosition[p][Move.COL_N];
                row_n               = MovesPosition[p][Move.ROW_N];         
                
                // Store position settings
                
                if(((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN)) && (col != col_n) && (Figure_p == Position.EMPTY))
                {
                    TempPawn = Pos[row][col_n];
                }
               
                if((Figure == Position.WHITE_ROOK) || (Figure == Position.BLACK_ROOK) || (Figure == Position.WHITE_KING) || (Figure == Position.BLACK_KING))                               
                {
                    Position.StoreCastling(CastlingLocal, Pos);
                }                 
                temp_ep                     = Position.GetColumnPawnMovedTwoRows(Pos);                          // save previous column where pawn moved two steps in temp_ep
                RepetitivePositionsCounterLocal = Position.GetNumberOfRepetitivePositions(Pos);                
                FiftyMoveCounterLocal       = Position.GetNumberOfMovesWithNoPawnMoveOrCapture(Pos);                
                
                // Make move  
                //Move.MakeMove(Pos, col, row, Figure_n, col_n, row_n, MoveHistory, Move.DO_NOT_ADD_TO_MOVE_HISTORY);         
                Move.MakeMove(Pos, col, row, Figure_n, col_n, row_n, MovePath, Move.DO_NOT_ADD_TO_MOVE_HISTORY);  
                //RatingScore = IterateMove(Pos, alpha, beta, MoveIteration, MoveRating, MoveHistory);   
                RatingScore = IterateMove(Pos, alpha, beta, MovePath, MoveRating);   
                //RatingScore = IterateMove(alpha, beta, MoveIteration, MoveRating, MoveHistory);                   
                //System.out.print("MoveBestLocal ");
                //Move.DisplayMoveList(MoveBestLocal, Move.LINE, Move.SHOW_RATING_LAST_MOVE);                
                
                //Move.CopyMove(MoveBestLocal, Iteration, MoveRating);
                
                //System.out.print("MoveRating[" + Iteration + "] \t");
                //Move.DisplayMoveList(MoveRating, Move.LINE, Move.SHOW_RATING_LAST_MOVE);                
                
                Position.SwitchMoveColor(Pos);                                              // Position.Switch move color back 
                Iteration--;                                                             // Decrease counter for move iteration
                Iteration_Move_Counter[Iteration + 1] = 0;                                  // Not sure if required
                Display_Move_Counter[Iteration + 1] = 0;                                    // Not sure if required

                // Revert Move
                Pos[row][col]       = Figure;
                Pos[row_n][col_n]   = Figure_p;
                
                if(EnPassantStatus == Position.EN_PASSANT)
                {
                    Pos[row][col_n] = TempPawn;
                }
   
                if((Figure == Position.WHITE_KING) && (col == Position.E))
                {
                    switch(col_n)
                    {
                        case Position.C:
                            //System.out.println("0-0");
                            Pos[Position.WHITE_CASTLING_ROW][Position.A] = Position.WHITE_ROOK;
                            Pos[Position.WHITE_CASTLING_ROW][Position.D] = Position.EMPTY;
                            break;
                        
                        case Position.G:
                            //System.out.println("0-0-O");
                            Pos[Position.WHITE_CASTLING_ROW][Position.H] = Position.WHITE_ROOK;
                            Pos[Position.WHITE_CASTLING_ROW][Position.F] = Position.EMPTY;
                            break;
                    }
                }
                
                if((Figure == Position.BLACK_KING) && (col == Position.E))
                {
                    switch(col_n)
                    {
                        case Position.C:
                            //System.out.println("0-0");
                            Pos[Position.BLACK_CASTLING_ROW][Position.A] = Position.BLACK_ROOK;
                            Pos[Position.BLACK_CASTLING_ROW][Position.D] = Position.EMPTY;
                            break;
                        
                        case Position.G:
                            //System.out.println("0-0-O");
                            Pos[Position.BLACK_CASTLING_ROW][Position.H] = Position.BLACK_ROOK;
                            Pos[Position.BLACK_CASTLING_ROW][Position.F] = Position.EMPTY;
                            break;
                    }
                }  
                        
                // Restore position settings
                Position.SetColumnPawnMovedTwoRows(Pos, temp_ep);                           // Restore column where pawn moved two steps in temp_ep
                
                if((Figure == Position.WHITE_ROOK) || (Figure == Position.BLACK_ROOK) || (Figure == Position.WHITE_KING) || (Figure == Position.BLACK_KING))                               
                {
                    Position.RestoreCastling(CastlingLocal, Pos);
                }            
                Position.SetNumberOfRepetitivePositions(Pos, RepetitivePositionsCounterLocal);                        
                Position.SetNumberOfMovesWithNoPawnMoveOrCapture(Pos, FiftyMoveCounterLocal);
                
            }
            else                                                        // Child node
            {
                RatingScore = MovesPosition[p][Move.RATING];
                // Before 
                //Move.CopyMoveList(MoveIteration, MoveRating);
                
                //After...
               
                //System.out.println("Before Move.CopyMoveList... ");
                //System.out.println("MovePath = ... ");       
               // Move.DisplayMoveList(MovePath, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);
                
               // System.out.println("MoveRating = ... ");       
               // Move.DisplayMoveList(MoveRating, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);                
                
                //scanner.nextLine();  
                            
                //System.out.println("Ply = " + Ply + " Iteration = " + Iteration);
                //scanner.nextLine(); 
                
                Move.CopyMoveList(MovePath, Ply, MoveRating);   // Copies move under investigation to MoveRating, MovePath contains the move history up to Ply            
                
                //System.out.println("After Move.CopyMoveList... ");
                //System.out.println("MovePath = ... ");       
               // Move.DisplayMoveList(MovePath, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);
                
               // System.out.println("MoveRating = ... ");       
               // Move.DisplayMoveList(MoveRating, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);                
                
                //scanner.nextLine();                  
            }
            
            if(RatingScore == MOVE_NOT_POSSIBLE)
            {
               // System.out.println("Move not possible = ... ");  
                return MOVE_NOT_POSSIBLE;
            }
                
            //System.out.println("Before DecisionRule = ... ");     
            //scanner.nextLine();   
            
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
                            Move.DisplayMoveList(MoveBestUpper, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);     
                        }
                    }                                           
                    break;
                                                
                case Settings.ALPHA_BETA:
                    //System.out.println("In ALPHA_BETA ... ");     
                    //scanner.nextLine();                  
                    if((Position.GetMoveColor(Pos) == Position.WHITE_MOVE) && (RatingScore > alpha))
                    {                                       // Max node
                        alpha = RatingScore;                         
                        Move.CopyMoveList(MoveRating, 0, MoveBestUpper);
                        
                        //if(ShowStatus > Settings.LOW)
                        //{
                         //   System.out.print("Iteration[" + Iteration + "] \t New alpha = " + alpha + "\t");                        
                        //   System.out.print("MoveBestUpper[" + Iteration +"] ");
                        //    Move.DisplayMoveList(MoveBestUpper, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);   
                            //                   scanner.nextLine();  
                        //}
                        
                    }
                    if((Position.GetMoveColor(Pos) == Position.BLACK_MOVE) && (RatingScore < beta))                                                    
                    {                                      // Min node
                        beta = RatingScore;
                        Move.CopyMoveList(MoveRating, 0, MoveBestUpper);
                        
                        //if(ShowStatus > Settings.LOW)
                        //{
                          //  System.out.print("Iteration[" + Iteration + "]\t New beta = " + beta + "\t");                            
                         //   System.out.print("MoveBestUpper[" + Iteration +"] ");
                         //   Move.DisplayMoveList(MoveBestUpper, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);
                             //                   scanner.nextLine();  
                        //}
                    }
                    if((alpha >= beta) || (alpha == Rating.CHECKMATE_RATING) || (beta == -Rating.CHECKMATE_RATING))             // No more siblings also called alpha-beta pruned
                    {
                        AlphaBetaCounter[Iteration] ++;
                        //if(ShowStatus > Settings.LOW)
                        //{
                           // System.out.println("AlphaBetaCounter[" + Iteration + "] = " + AlphaBetaCounter[Iteration]);
                             //               scanner.nextLine();  //}
                        break loop_over_all_moves_in_one_position;
                    }  
                    break; 
            }
            
            //System.out.println("Before ClearMove... ");
             //   System.out.println("MovePath = ... ");       
            //    Move.DisplayMoveList(MovePath, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);   
                
             //               System.out.println("Ply = " + Ply + " Iteration = " + Iteration);
            // scanner.nextLine();
            
            //Move.ClearMove(MovePath, Ply + Iteration - 1 );
            
            //System.out.println("After ClearMove... ");
               // System.out.println("MovePath = ... ");       
              //  Move.DisplayMoveList(MovePath, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);                 
            //scanner.nextLine();
        }

        //System.out.println("After for loop in IterateMove... ");
                    //scanner.nextLine();
        switch(DecisionRule)
        {
            case Settings.MINMAX:
                return minmax;
                          
            case Settings.ALPHA_BETA:
                if(Position.GetMoveColor(Pos) == Position.WHITE_MOVE)
                {                                       // Max node
                    return alpha;              
                }
                if(Position.GetMoveColor(Pos) == Position.BLACK_MOVE)                                                    
                {                                      // Min node
                    return beta;
                } 
                break;  
        }
        
        return 0;  // This statement is not reached and is added to make compiler happy  
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