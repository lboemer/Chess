import java.util.*; // For scanner

public class Settings
{
    public static final int ZERO                        = 0;
    public static final int LOW                         = 1;
    public static final int MEDIUM                      = 2;
    public static final int HIGH                        = 3;
    public static final int VERYHIGH                    = 4;
    
    // Java int:int is 32 bit signed type ranges from –2,147,483,648 to 2,147,483,647.
    public static final int ABSOLUTE_MAX_MOVES          = 2147483000;           
    public static final int DEFAULT_MAX_MOVES           = 10000000; 
        
    public static final int ABSOLUTE_MAX_MOVE_DEPTH     = 50; 
    public static final int DEFAULT_MAX_MOVE_DEPTH      = 50;   
    
    public static final long ABSOLUTE_MAX_SECONDS       = 60 * 60 * 24; // 24 h 
    public static final long DEFAULT_MAX_SECONDS        = 5;  // per computer move 
    
    // Decision rule
    public static final int MINMAX                      = 0;
    public static final int ALPHA_BETA                  = 1;  
    public static final int DEFAULT_DECISION_RULE       = ALPHA_BETA;
      
    // Play mode
    public static final int PLAYER_PLAYER               = 0;
    public static final int PLAYER_COMPUTER             = 1;
    public static final int COMPUTER_COMPUTER           = 2;
    public static final int DEFAULT_PLAY_MODE           = PLAYER_COMPUTER;
    
    // First move
    public static final int PLAYER                      = 0;
    public static final int COMPUTER                    = 1;
    public static final int DEFAULT_FIRST_MOVE          = PLAYER;
     
    public static final int NO_HEADER                   = 0;
    public static final int HEADER                      = 1;
    
    // Empty "" so indices start from 1
    public static final String[] positionMap = 
    {
        "New", 
        "Pawn", 
        "En passant", 
        "Promotion",
        "Castling", 
        "Insufficient material", 
        "One Move Mate",
        "Two Move Mate", 
        "Three Move Mate"
    };
    
    public static void Initiate(int[][] Pos)
    {
        Chess.ShowStatus        = LOW;  // Show criteria, implemented ZERO, LOW, MEDIUM, HIGH
        Chess.DebugLevel        = LOW;  // Debug level, implemented ZERO, LOW, MEDIUM, HIGH  
        // Initiate starting postion        
        Position.SetFromFile(Pos, stringToFilename(positionMap[Position.NEW_POSITION]));         
        Chess.MaxMoves          = DEFAULT_MAX_MOVES;        // Initiate MaxMoves
        Chess.MaxMoveDepth      = DEFAULT_MAX_MOVE_DEPTH;   // Initiate MaxMoveDepth
        Chess.MaxSeconds        = DEFAULT_MAX_SECONDS;      // Initiate Maxseconds
        Chess.DecisionRule      = DEFAULT_DECISION_RULE;    // Initiate DecisionRule                          
        Chess.PlayMode          = DEFAULT_PLAY_MODE;        // Initiate PlayMode
        Chess.FirstMove         = DEFAULT_FIRST_MOVE;       // Initiate FirstMove 
        Chess.WhiteBoard        = Chess.SetBoard(Pos);      // Set WhiteBoard
        Chess.ui.repaintWindow(Pos);                        // Draw beginning position
    } 

    public static boolean GetUserInput(int[][] Pos)    
    {
        int i;
        char[] ch                   = new char[100];  
        String inputString;
        Scanner scanner             = new Scanner(System.in);
        
        do{
            ClearScreen(Pos);
            System.out.println("Enter \t Next Step");
            System.out.println("1 \t Get user input");
            System.out.println("2 \t Make move");
            System.out.println("x \t Exit Program");
            
            inputString = scanner.nextLine(); 
     
            for(i = 0; i < inputString.length(); i++)  
            {  
                ch[i] = inputString.charAt(i);             
            }  
            
            switch(ch[0])
            {
                 case '1':
                    Set(Pos);
                    continue;
                   
                 case 'x':
                    return false;
            }
        } 
        while(ch[0] != '2');
        
        return true;
    }
    
    public static boolean NewGame(int[][] Pos, int[][] MoveHistory)    
    {
        int i;
        char UserInput;
        String inputString;
        Scanner scanner             = new Scanner(System.in);
        
        do{
            ClearScreen(Pos);
                                
            //Move.DisplayMoveList(MoveHistory, Move.ALL, 0, Move.TABLE, Move.SHOW_NO_RATING);
            
            System.out.println();
            System.out.println("Enter");
            System.out.println("1  \t\t\t New game");
            System.out.println("x  \t\t\t Exit program");

            inputString = scanner.nextLine(); 
            UserInput  = inputString.charAt(0);         
             
            switch(UserInput)
            {
                 case '1':
                    return true;
                    
                 case 'x':
                    return false;
            }
        } 
        while(true);
    }
    
    public static void Set(int[][] Pos)
    {  
        Scanner scanner = new Scanner(System.in);
        String inputString;
        char UserInput;
                
        do 
        {            
            ClearScreen(Pos);
            
            System.out.println("Enter\t Settings \t\t\t Current setting");
            System.out.print("1 \t Set Position\t\t\t ");               ShowPosition(NO_HEADER);
            System.out.print("2 \t Set Max Computer Moves\t\t ");       ShowMaxMoves(NO_HEADER);
            System.out.print("3 \t Set Max Computer Move Depth\t ");    ShowMaxMoveDepth(NO_HEADER);
            System.out.print("4 \t Set Max Computer Seconds\t ");       ShowMaxSeconds(NO_HEADER);
            System.out.print("5 \t Set Decision Rule\t\t ");            ShowDecisionRule(NO_HEADER);
            System.out.print("6 \t Set Move Color\t\t\t ");             ShowMoveColor(Pos, NO_HEADER);
            System.out.print("7 \t Set Play Mode\t\t\t ");              ShowPlayMode(NO_HEADER); 
            System.out.print("8 \t Set First Move\t\t\t ");             ShowFirstMove (NO_HEADER);
            System.out.println("x \t Exit");
                    
            inputString = scanner.nextLine(); 
            UserInput  = inputString.charAt(0);            

            switch(UserInput)
            {
                    case '1':
                        SetPosition(Pos);
                        break;
                                        
                    case '2':
                        SetMaxMoves(Pos);
                        break;                           
                        
                    case '3':
                        SetMaxMoveDepth(Pos);
                        break;
                        
                    case '4':
                        SetMaxSeconds(Pos);
                        break;
                    
                    case '5':
                        SetDecisionRule(Pos);
                        break;                        
                        
                    case '6':
                        SetMoveColor(Pos);
                        break;                                         
                    
                    case '7':
                        SetPlayMode(Pos);
                        break; 
                            
                    case '8':
                        SetFirstMove(Pos);
                        break;
            }
        }
        while (UserInput != 'x');
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
            for (i = 0; i < positionMap.length; i++)
            {
		System.out.format("%1d  \t\t %s\n", i+1, positionMap[i]);
            }
            
            inputString = scanner.nextLine();     
            UserInput = Character.getNumericValue(inputString.charAt(0)) - 1;

            if((UserInput < 0) || (UserInput >= positionMap.length))
            {
                InputValid = false;
                continue;
            }
            {
                InputValid = true;
            }
    
            Position.SetFromFile(Pos, stringToFilename(positionMap[UserInput]));
            
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
    
    public static void SetMaxMoves(int[][] Pos)
    {            
        boolean InputValid = false;
        String inputString;
        Scanner scanner             = new Scanner(System.in);
        do
        {       
            ClearScreen(Pos);
            
            System.out.println("Enter \t Max Computer Moves");
            System.out.print("Integer\t ");
            
            inputString = scanner.nextLine();     

            try 
            {
                Chess.MaxMoves = Integer.parseInt(inputString);
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Can't convert to integer. Please try again.");
                continue;
            }
            if (Chess.MaxMoves > ABSOLUTE_MAX_MOVES){
                System.out.println("MaxMoves is set to " + Chess.MaxMoves);
                System.out.println("ABSOLUTE_MOVES is" + ABSOLUTE_MAX_MOVES);
                System.out.println("Please lower MaxMoves");
            }
            else
            {
                InputValid = true;
            }
        }
        while(!InputValid);
    }
    
    public static void SetMaxMoveDepth(int[][] Pos)
    {
        int i;
        boolean InputValid = false;
        int number;
        String inputString;
        Scanner scanner             = new Scanner(System.in);
        do
        {  
            ClearScreen(Pos);

            System.out.println("Enter \t Max Move Depth");
            System.out.print("Integer\t ");

            inputString = scanner.nextLine();     
            
            try 
            {
                Chess.MaxMoveDepth = Integer.parseInt(inputString);
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Can't convert to integer. Please try again.");
                continue;
            }
            
            if (Chess.MaxMoveDepth > ABSOLUTE_MAX_MOVE_DEPTH -1)
            {
                ShowMaxMoveDepth(HEADER);
                System.out.println("ABSOLUTE_MAX_MOVES_DEPTH is = " + ABSOLUTE_MAX_MOVE_DEPTH);
                System.out.println("Please lower MaxMoveDepth");
            }
            else
            {
                InputValid = true;
            }
        }
        while(!InputValid);    
    }
            
    public static void SetMaxSeconds(int[][] Pos)
    {
        int i;
        boolean InputValid = false;
        int number;
        String inputString;
        Scanner scanner             = new Scanner(System.in);
        do
        {   
            ClearScreen(Pos);
            
            System.out.println("Enter \t Max Compputer Seconds");
            System.out.print("Integer\t ");
            
            inputString = scanner.nextLine();     
        
            try 
            {
                Chess.MaxSeconds = Integer.parseInt(inputString);
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Can't convert to integer. Please try again.");
                continue;
            }
            if (Chess.MaxSeconds > ABSOLUTE_MAX_SECONDS || (Chess.MaxSeconds <= 0))            
            {
                System.out.println("ABSOLUTE_MAX_SECONDS is = " + ABSOLUTE_MAX_SECONDS);
                System.out.println("0 < Max Computer Seconds < " + ABSOLUTE_MAX_SECONDS);
            }
            else
            {
                InputValid = true;
            }            
        }
        while(!InputValid);   
    }
          
    public static void SetDecisionRule(int[][] Pos)
    {      
        boolean InputValid = false;
        char ch;
        String inputString;
        Scanner scanner             = new Scanner(System.in);
        
        do
        {                
            ClearScreen(Pos);

            System.out.println("Enter \t Decision Rule");
            System.out.println("1 \t Minmax");
            System.out.println("2 \t Alpha Beta Pruning");
            
            inputString = scanner.nextLine();      
            switch(inputString.charAt(0))   
            {
                case '1':
                    Chess.DecisionRule = MINMAX;
                    InputValid = true;
                    break;
                    
                case '2':
                    Chess.DecisionRule = ALPHA_BETA;
                    InputValid = true;
                    break;
            }
        }   
        while(!InputValid);   
    }
    
    public static void SetMoveColor(int[][] Pos)
    {
        char ch;
        boolean InputValid = false;
        String inputString;
        Scanner scanner             = new Scanner(System.in);
        
        do
        {           
            ClearScreen(Pos);
    
            System.out.println("Enter \t Move color");
            System.out.println("1 \t White move");
            System.out.println("2 \t Black move");
            
            inputString = scanner.nextLine();     
            switch(inputString.charAt(0))   
            {
                case '1':
                    Position.SetMoveColor(Pos, Position.WHITE_MOVE); 
                    InputValid = true;
                    break;
                    
                case '2':
                    Position.SetMoveColor(Pos, Position.BLACK_MOVE);
                    InputValid = true;;
                    break;
            }
        }
        while (!InputValid);  
        
        Chess.WhiteBoard = Chess.SetBoard(Pos); // Sets WhiteBoard to true or false;
        Chess.ui.repaintWindow(Pos);            // Draws beginning position
    }
    
    public static void SetPlayMode(int[][] Pos)
    {
        char ch;
        boolean InputValid = false;
        String inputString;
        Scanner scanner             = new Scanner(System.in);
        
        do
        {       
            ClearScreen(Pos);
            
            System.out.println("Enter \t Play Mode");
            System.out.println("1 \t Player against player");
            System.out.println("2 \t Player against computer");
            System.out.println("3 \t Computer against computer" );
            
            inputString = scanner.nextLine();     
            switch(inputString.charAt(0)) 
            {
                case '1':
                    Chess.PlayMode = PLAYER_PLAYER;
                    InputValid = true;
                    break;
                    
                case '2':
                    Chess.PlayMode = PLAYER_COMPUTER;
                    InputValid = true;                    
                    break;
                    
                case '3':
                    Chess.PlayMode = COMPUTER_COMPUTER;
                    InputValid = true;                    
                    break;
            }
        }       
        while (!InputValid);  
    }
    
    public static void SetFirstMove(int[][] Pos)
    {
        char ch;
        boolean InputValid = false;
        String inputString;
        Scanner scanner             = new Scanner(System.in);
        
        do
        {  
            ClearScreen(Pos);
            System.out.println("Enter \t First Move");
            System.out.println("1 \t Player to make first move");
            System.out.println("2 \t Computer to make first move");
            
            inputString = scanner.nextLine();     
            switch(inputString.charAt(0)) 
            {
                case '1':
                    Chess.FirstMove = PLAYER;
                    InputValid = true;
                    break;
                    
                case '2':
                    Chess.FirstMove = COMPUTER;
                    InputValid = true;                    
                    break;
            }
        }
        while (!InputValid);  
        
        Chess.WhiteBoard = Chess.SetBoard(Pos); // Sets WhiteBoard to true or false;
        Chess.ui.repaintWindow(Pos);                                            // Draws beginning position     
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
           
            switch (Position.GetMoveColor(Pos))
            {
                case Position.WHITE_MOVE:
                    System.out.print("White");
                    break;
                    
                case Position.BLACK_MOVE:
                    System.out.print("Black");
                    break;
            }
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
    
    public static void ShowPosition(int type)
    {       
        if (type == HEADER)
        {
            System.out.print("Position:\t\t\t ");
        }
        System.out.println(positionMap[Position.BeginPosition]);
    }
    
    public static void ShowMaxMoves(int type)
    {
        if(type == HEADER)
        {        
            System.out.print("Max Computer Moves: \t\t ");
        }
        System.out.println(Chess.MaxMoves);        
    }
    
    public static void ShowMaxMoveDepth(int type)
    {
        if(type == HEADER)
        {
            System.out.print("Max Computer Move Depth:\t ");
        }
        System.out.println(Chess.MaxMoveDepth);
    }
    
    public static void ShowMaxSeconds(int type)
    {
        if(type == HEADER)
        {          
            System.out.print("Max Computer Seconds\t\t ");
        }
        System.out.println(Chess.MaxSeconds);
    }
    
    public static void ShowDecisionRule(int type)
    {
        if(type == HEADER)
        {          
            System.out.print("Decision Rule:\t\t\t ");        
        }        
        switch(Chess.DecisionRule)
        {
            case MINMAX:
                System.out.println("Minmax");  
                break;
                    
            case ALPHA_BETA:
                System.out.println("Alpha Beta Pruning");  
                break;
                    
            default:
                System.out.println("Error in ShowDecisionRule() DecisionRule =" +Chess.DecisionRule);
                break; 
        }
    }
    
    public static void ShowMoveColor(int[][] Pos, int type)
    {
        if(type == HEADER)
        {          
            System.out.print("Move Color:\t\t\t ");   
        }     
        switch (Position.GetMoveColor(Pos))
        {
            case Position.WHITE_MOVE:
                System.out.println("White");
                break;
                
            case Position.BLACK_MOVE:
                System.out.println("Black");
                break;
                                     
            default:
                System.out.println("Error in ShowMoveColor() MoveColor =" + Position.GetMoveColor(Pos));
                break;     
        }
    }
    
    public static void ShowPlayMode(int type)
    {
        if(type == HEADER)
        {          
            System.out.print("Play Mode     \t\t ");
        }    
        switch (Chess.PlayMode)
        {
            case PLAYER_PLAYER:
                System.out.println("Player against player");
                break;
                    
            case PLAYER_COMPUTER:
                System.out.println("Player against computer");
                break;
                
            case COMPUTER_COMPUTER:
                System.out.println("Computer against computer");
                break; 
                
            default:
                System.out.println("Error in ShowPlayMode(): PlayMode =" + Chess.PlayMode);
                break; 
        } 
    }
    
    public static void ShowFirstMove(int type)
    {
        if(type == HEADER)
        {          
            System.out.print("First Move: \t\t\t ");
        }         
        switch (Chess.FirstMove)
        {
            case PLAYER:
                System.out.println("Player");
                break;
                    
            case COMPUTER:
                System.out.println("Computer");
                break;
                
            default:
                System.out.println("Error in ShowFirstMove() FirstMove =" + Chess.FirstMove);
                break; 
        }    
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
