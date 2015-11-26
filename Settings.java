import java.util.*; // For scanner

public class Settings
{
    //Debug and Show settings
    public static final int ZERO                        = 0;
    public static final int LOW                         = 1;
    public static final int MEDIUM                      = 2;
    public static final int HIGH                        = 3;
    public static final int VERYHIGH                    = 4;
    
    // Java int:int is 32 bit signed type ranges from –2,147,483,648 to 2,147,483,647.
    public static final int ABSOLUTE_MAX_MOVES          = 2147483000;           
    public static final int DEFAULT_MAX_MOVES           = 10000000; 
    public static final String[] MaxComputerMoveMenu = 
    {
        "Max Computer Moves"
    };   
    
    public static final int ABSOLUTE_MAX_MOVE_DEPTH     = 50; 
    public static final int DEFAULT_MAX_MOVE_DEPTH      = 50;   
    public static final String[] MaxMoveDepthMenu = 
    {
        "Max Move Depth"
    };
    
    public static final int ABSOLUTE_MAX_SECONDS       = 60 * 60 * 24; // 24 h 
    public static final int DEFAULT_MAX_SECONDS        = 5;  // per computer move 
    public static final String[] MaxSecondsMenu = 
    {
        "Max seconds per computer move"
    };
    
    // Decision rule
    public static final int MINMAX                      = 0;
    public static final int ALPHA_BETA                  = 1;  
    public static final int DEFAULT_DECISION_RULE       = ALPHA_BETA;
    public static final String[] DecisionRuleMenu = 
    {
        "Decision Rule", 
        "Minmax", 
        "Alpha Beta Pruning"
    };
             
    // Play mode
    public static final int PLAYER_PLAYER               = 0;
    public static final int PLAYER_COMPUTER             = 1;
    public static final int COMPUTER_COMPUTER           = 2;
    public static final int DEFAULT_PLAY_MODE           = PLAYER_COMPUTER;
    public static final String[] PlayModeMenu = 
    {
        "Play Mode", 
        "Player against player", 
        "Player against computer", 
        "Computer against computer"
    };    
    
    // First move
    public static final int PLAYER                      = 0;
    public static final int COMPUTER                    = 1;
    public static final int DEFAULT_FIRST_MOVE          = PLAYER;
    public static final String[] FirstMoveMenu = 
    {
        "First Move", 
        "Player to make first move", 
        "Computer to make first move"
    };   
    
    // Hashmap use
    public static final int DO_NOT_USE_HASHMAP          = 0;
    public static final int USE_HASHMAP                 = 1;
    public static final int DEFAULT_HASHMAP_USE         = USE_HASHMAP;
    public static final String[] HashmapMenu = 
    {
        "Hashmap",
        "Do not use Hashmap", 
        "Use Hashmap"
    };  
    
    // Rating method
    public static final int MATERIAL_ONLY               = 0;
    public static final int MATERIAL_AND_POSITION       = 1;
    public static final int DEFAULT_RATING_METHOD       = MATERIAL_AND_POSITION;
    public static final String[] RatingMenu = 
    {
        "Rating method",
        "Use material only", 
        "Use material and position"
    };
    
    public static void Initiate(int[][] Pos)
    {
        Position.BeginPosition = Position.NEW_POSITION;
        Chess.ShowStatus        = LOW;  // Show criteria, implemented ZERO, LOW, MEDIUM, HIGH
        Chess.DebugLevel        = LOW;  // Debug level, implemented ZERO, LOW, MEDIUM, HIGH  
        // Initiate starting postion        
        Position.SetFromFile(Pos, stringToFilename(Position.positionMap[Position.BeginPosition]));         
        Chess.MaxMoves          = DEFAULT_MAX_MOVES;        // Initiate MaxMoves
        Chess.MaxMoveDepth      = DEFAULT_MAX_MOVE_DEPTH;   // Initiate MaxMoveDepth
        Chess.MaxSeconds        = DEFAULT_MAX_SECONDS;      // Initiate Maxseconds
        Chess.DecisionRule      = DEFAULT_DECISION_RULE;    // Initiate DecisionRule                          
        Chess.PlayMode          = DEFAULT_PLAY_MODE;        // Initiate PlayMode
        Chess.FirstMove         = DEFAULT_FIRST_MOVE;       // Initiate FirstMove 
        Chess.HashmapUse        = DEFAULT_HASHMAP_USE;      // Initiate FirstMove        
        Chess.RatingMethod      = DEFAULT_RATING_METHOD;    // Initiate RatingMethod      
        Chess.WhiteBoard        = Chess.SetBoard(Pos);      // Set WhiteBoard
        Chess.ui.repaintWindow(Pos);                        // Draw beginning position
    } 

    public static boolean Set(int[][] Pos)
    {  
        int UserInput = 0;
        Scanner scanner = new Scanner(System.in);
        
        do 
        {          
            ClearScreen(Pos);
            
            System.out.println("Enter\t Settings \t\t\t Current setting");
            System.out.println("1 \t Make Move"); 
            System.out.print("2 \t Set First Move\t\t\t ");             System.out.println(FirstMoveMenu[Chess.FirstMove + 1]);
            System.out.print("3 \t Set Move Color\t\t\t ");             Position.DisplayMoveColor(Pos);
            System.out.print("4 \t Set Play Mode\t\t\t ");              System.out.println(PlayModeMenu[Chess.PlayMode + 1]);
            System.out.print("5 \t Set Max Computer Seconds\t ");       System.out.println(Chess.MaxSeconds);
            System.out.print("6 \t Set Max Computer Move Depth\t ");    System.out.println(Chess.MaxMoveDepth);
            System.out.print("7 \t Set Max Computer Moves\t\t ");       System.out.println(Chess.MaxMoves);    
            System.out.print("8 \t Set Decision Rule\t\t ");            System.out.println(DecisionRuleMenu[Chess.DecisionRule + 1]);
            System.out.print("9 \t Set Hashmap Use\t\t ");              System.out.println(HashmapMenu[Chess.HashmapUse + 1]);        
            System.out.print("10 \t Set Rating Method\t\t ");           System.out.println(RatingMenu[Chess.RatingMethod + 1]);           
            System.out.print("11 \t Set Position\t\t\t ");              System.out.println(Position.positionMap[Position.BeginPosition]);
            System.out.println("12 \t Exit");
                    
            try 
            {
                UserInput = Integer.parseInt(scanner.nextLine());
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Can't convert to integer. Please try again.");
                continue;
            }
            switch(UserInput)
            {
                //case 1:
                    //System.out.println("Entered Make Move");  
                //    break;
                                    
                case 2:
                    Chess.FirstMove = UserSetParameter(Pos, FirstMoveMenu, FirstMoveMenu.length) - 1;
                    Chess.WhiteBoard = Chess.SetBoard(Pos); // Sets WhiteBoard to true or false;
                    Chess.ui.repaintWindow(Pos); // Draws beginning position                        
                    break;            
                                    
                case 3:
                    Position.SetMoveColor(Pos, UserSetParameter(Pos, Position.MoveColorMenu, Position.MoveColorMenu.length) - 1); 
                    Chess.WhiteBoard = Chess.SetBoard(Pos); // Sets WhiteBoard to true or false;
                    Chess.ui.repaintWindow(Pos); // Draws beginning position
                    break;                                      

                case 4:
                    Chess.PlayMode = UserSetParameter(Pos, PlayModeMenu, PlayModeMenu.length) - 1;
                    break; 

                case 5:
                    Chess.MaxSeconds = UserSetParameter(Pos, MaxSecondsMenu, ABSOLUTE_MAX_SECONDS + 1);
                    break;            
 
                case 6:
                    Chess.MaxMoveDepth = UserSetParameter(Pos, MaxMoveDepthMenu, ABSOLUTE_MAX_MOVE_DEPTH + 1);
                    break;
                    
                case 7:
                    Chess.MaxMoves = UserSetParameter(Pos, MaxComputerMoveMenu, ABSOLUTE_MAX_MOVES + 1);
                    break;                       

                case 8:
                    Chess.DecisionRule = UserSetParameter(Pos, DecisionRuleMenu, DecisionRuleMenu.length) - 1;
                    break;                        

                case 9:
                    Chess.HashmapUse = UserSetParameter(Pos, HashmapMenu, HashmapMenu.length) - 1;                    
                    break;                                     
                 
                case 10:
                    Chess.RatingMethod = UserSetParameter(Pos, RatingMenu, RatingMenu.length) - 1;                    
                    break;                      
                
                case 11:
                    SetPosition(Pos);
                    break;

                case 12:
                    System.out.println("Entered Exit");  
                    return false;
            }
        }
        while (UserInput != 1);
        return true;
    }
    
    public static String stringToFilename(String str)
    {
        return str.replace(" ", "_").concat(".txt");
    }
       
    public static void SetPosition(int[][] Pos)
    {  
        boolean InputValid;
        String inputString;
        Scanner scanner     = new Scanner(System.in);
        int UserInput;
        int[][] MoveHistory = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];
        int i;
        
        do
        {        
            Move.EmptyList(MoveHistory);

            ClearScreen(Pos);
          
            System.out.println("Enter \t\t Position");
            for (i = 0; i < Position.positionMap.length; i++)
            {
		System.out.format("%1d  \t\t %s\n", i+1, Position.positionMap[i]);
            }
            
            inputString = scanner.nextLine();     
            UserInput = Character.getNumericValue(inputString.charAt(0)) - 1;

            if((UserInput < 0) || (UserInput >= Position.positionMap.length))
            {
                InputValid = false;
                continue;
            }
            {
                InputValid = true;
            }
    
            Position.SetFromFile(Pos, stringToFilename(Position.positionMap[UserInput]));
            
            if (Position.Validate(Pos) == false)
            {
                System.out.println("Invalid position!");
                InputValid = false;
            }

            switch(Position.GetStatus(Pos, MoveHistory))
            {
                case Position.NO_CONDITION:
                    break;
                    
                case Position.CHECKMATE:
                    Position.DisplayMoveColor(Pos);               
                    System.out.println(" is checkmate!");
                    InputValid = false;
                    
                case Position.STALEMATE:
                    Position.DisplayMoveColor(Pos);                  
                    System.out.println(" is stalemate!");
                    InputValid = false;
            
                case Position.INSUFFICIENT_MATERIAL:      
                    Position.DisplayMoveColor(Pos);              
                    System.out.println(" has insufficient material to win. Draw!");
                    InputValid = false;
            }
            // Check if the opponent King could be taken in the next move        
            if (Position.Check(Pos, Position.GIVING_CHECK) == true)              
            {         
                System.out.println("Opponent can take");
                Position.DisplayMoveColor(Pos);   
                System.out.println(" king in the next move. Invalid position!");
                InputValid = false;;
            }
            
            if(InputValid == false)
            {
                System.out.println("Please enter a new position");
            }
        }
        while(!InputValid);
        
        Position.BeginPosition = UserInput; // Sets new begin position 
        if(Position.GetMoveColor(Pos) == Position.WHITE_MOVE)
        {
            Chess.WhiteBoard = true;
            System.out.println("Chess.WhiteBoard = " + Chess.WhiteBoard);
        }
        else
        {
            Chess.WhiteBoard = false;
            System.out.println("Chess.WhiteBoard = " + Chess.WhiteBoard);
        } 
        
        Chess.ui.repaintWindow(Pos); 
    }    
    
     public static int UserSetParameter(int[][] Pos, String[] str, int Max)
     {
        int inputValue = 0;
        Scanner scanner             = new Scanner(System.in);
                
        do
        {     
            ClearScreen(Pos);
            System.out.println("Enter \t" + str[0]);
            for (int i = 1; i < str.length; i++)
            {
                System.out.format("%d  \t%s\n", i, str[i]);
            }

            try 
            {
                inputValue = Integer.parseInt(scanner.nextLine());
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Can't convert to integer. Please try again.");
                continue;
            }
        }
        while ((inputValue <= 0) || (inputValue >=  Max));  
        
        return inputValue;
    }
    
    public static char GetUserMove(int[][] Pos, int[][] MovePath, int[][] MoveBest, boolean Back)
    {
        int col = 0;
        int col_p_n = 0;
        int row = 0;
        int row_p_n = 0;     
        int i;
        char PromotionFigure;
        int Figure_n = 0;
        int h;     
        String[] MoveTable = new String[Move.MAX_NUMBER_MOVE_LIST];

        do
        {                   
            Settings.ClearScreen(Pos);
            
            if(!Back)
            {
                //Move.DisplayMoveList(MoveBest, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE); 
                Move.Display(MoveBest, Move.ALL, 0, MoveTable, Move.LINE, Move.SHOW_RATING_LAST_MOVE); 
            }
            
            System.out.println();

            //Move.DisplayMoveList(MovePath, Move.ALL, 0, Move.TABLE, Move.SHOW_NO_RATING);
            Move.Display(MovePath, Move.ALL, 0, MoveTable, Move.TABLE, Move.SHOW_NO_RATING);
            
            System.out.print("\nEnter \t ");
            Position.DisplayMoveColor(Pos);
            System.out.println(" move");
            
            System.out.println("Move \t E.g. e2e4 or e7e8B for pawn promotion");
            System.out.println("1 \t Computer to make next move");
            System.out.println("2 \t Take one move backwards");           
            System.out.println("x \t Exit Move Entry"); 
           
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine(); 

            char[] ch = new char[inputString.length()];  
            for(i = 0; i < inputString.length(); i++)  
            {  
               ch[i] = inputString.charAt(i);      
            }  

            switch(ch[0])
            {
                case '1':
                case '2':
                case 'x':
                    return ch[0];
            }
                     
            if ((inputString.length() < 4) || (inputString.length() > 5))
            {
               System.out.println("Entered " + inputString.length() + " characters.");
               System.out.println("Please enter your move as 4 characters or as 5 characters for a pawn promotion.");
               continue;
            }
     
            col       = (int) ch[0]- 'a' + 1; 
            row       = (int) ch[1]- '0';                 
            col_p_n   = (int) ch[2]- 'a' + 1; 
            row_p_n   = (int) ch[3]- '0'; 

            //System.out.println("col = " + col + " row = " + row + " col_p_n = " + col_p_n + " row_p_n = " + row_p_n);
     
            if ((col < 1 ) || (col > Position.COLS))
            {
               System.out.println("Entered from-column = " + ch[0]);
               System.out.println("Please enter a from-column between a and h.");
               continue;
            }
            
            if ((row < 1) || (row > Position.ROWS))
            {
               System.out.println("Entered from-row = " + row);
               System.out.println("Please enter a from-row between 1 and 8.");
               continue;
            }
            if ((col_p_n < 1) || (col_p_n > Position.COLS))
            {
               System.out.println("Entered to-column = " + ch[2]);
               System.out.println("Please enter a to-column between a and h.");
               continue;
            }
            
            if ((row_p_n < 1) || (row_p_n > Position.ROWS))
            {
               System.out.println("Entered to-row = " + row_p_n);
               System.out.println("Please enter a to-row between 1 and 8.");
               continue;
            }    
             
            if (inputString.length() == 5)
            {
               if ((Pos[row][col] != Position.WHITE_PAWN) && (Pos[row][col] != Position.BLACK_PAWN))
               {
                   System.out.println("Pawn promotion not possible since field "+ col + row + "has not a pawn.");
                   continue;
               }                              
               
               if (Pos[row][col] == Position.WHITE_PAWN) 
               {    
                   if (row != 7)
                   { 
                       System.out.println("Pawn promotion not possible since white pawn is not on 7'th row.");
                       continue;
                   }
                   if (row_p_n != 8)
                   { 
                       System.out.println("Pawn promotion not possible since white pawn does not move on 8'th row.");
                       continue;
                   }                   
                   if ((Pos[row_p_n][col_p_n] != Position.EMPTY) && (col_p_n == col))
                   { 
                       System.out.println("Pawn promotion not possible since field on 8'th row has a figure.");
                       continue;
                   }
               }
               
               if (Pos[row][col] == Position.BLACK_PAWN) 
               {    
                   if (row != 2)
                   { 
                       System.out.println("Pawn promotion not possible since black pawn is not on 2'th row.");
                       continue;
                   }
                   if (row_p_n != 1)
                   { 
                       System.out.println("Pawn promotion not possible since black pawn does not move on 1'th row.");
                       continue;
                   }                   
                   if ((Pos[row_p_n][col_p_n] != Position.EMPTY) && (col_p_n == col))
                   { 
                       System.out.println("Pawn promotion not possible since field on 1'th row has a figure.");
                       continue;
                   }
               }
               System.out.println("Pawn promotion");           
               switch (ch[4])
               {
                   case 'R':    
                   case 'N':                            
                   case 'B':
                   case 'Q':
                        PromotionFigure = ch[4];
                        break; 
                   default:
                        System.out.println("Pawn promotion not possible. Please enter pawn promotion to be R, N, B or Q.");
                        continue;
               }               
            }
            else
            {
                PromotionFigure = '0';
            }

            if (((Pos[row][col] == Position.WHITE_PAWN) && (row_p_n == 8) && (inputString.length() != 5)) ||
               ((Pos[row][col] == Position.BLACK_PAWN) && (row_p_n == 1) && (inputString.length() != 5)))
            {
               System.out.println("Pawn promotion not possible. Please enter pawn promotion to be R, N, B or Q.");
               continue;
            }
            
            if (Pos[row][col] == Position.EMPTY)
            {
                System.out.println("Field empty. Please enter own figure to Move.");
                continue;
            }
            
            if (Position.OwnFigure(Pos, col, row) == false)
            {
                System.out.println("Not own figure. Please enter own figure to Move.");
                continue;
            }
            
            if(Position.GetMoveColor(Pos) == Position.WHITE_MOVE)
            {
                switch (PromotionFigure)
                {
                    case 'R':
                        Figure_n = Position.WHITE_ROOK;
                        break;
                        
                    case 'N': 
                        Figure_n = Position.WHITE_KNIGHT;
                        break;   
                        
                    case 'B':
                        Figure_n = Position.WHITE_BISHOP;
                        break;                    
                        
                    case 'Q':
                        Figure_n = Position.WHITE_QUEEN;
                        break; 
                        
                    case '0':
                        Figure_n = Pos[row][col];
                        break;  
                }
            }
            else
            {
                switch (PromotionFigure)
                {
                    case 'R':
                        Figure_n = Position.BLACK_ROOK;
                        break;
                        
                    case 'N': 
                        Figure_n = Position.BLACK_KNIGHT;
                        break;   
                        
                    case 'B':
                        Figure_n = Position.BLACK_BISHOP;
                        break;                    
                        
                    case 'Q':
                        Figure_n = Position.BLACK_QUEEN;
                        break;  
                        
                    case '0':
                        Figure_n = Pos[row][col];
                        break;                          
                }
            }
        }while(!Move.UserSuccessful(Pos, row, col, Figure_n, row_p_n, col_p_n, MovePath));            
        Settings.ClearScreen(Pos);  
        return '0';
    }
   
    public static void ClearScreen(int[][] Pos)
    {
        int i;
        float UserTime      = Chess.UserTotal_ms;
        float ComputerTime  = Chess.ComputerTotal_ms;
        
        UserTime /= 1000;
        ComputerTime /= 1000;
        
        //System.out.print('\u000C');  // Clears console in Java
        Chess.Info();
        System.out.format("Player time \t Computer time \n");
        System.out.format("%.3f sec \t %.3f sec \n", UserTime, ComputerTime);        
        Position.Display(Pos);  
    }
  
}
