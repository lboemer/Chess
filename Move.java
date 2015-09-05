/**
  
V1.0 2015-03-29 

V1.1 2015-04-21
- updates on display moves formatting

V1.2 2015-04-23
- moved UserMoveSuccessful() from Chess. to Move.

V1.3 2015-04-26
- renamed GenerateMoveList() to GenerateMoveListOld()
- created a new GenerateMoveList() using GenerateCandiateMoveList()

V1.4 2015-05-11
- added SortMoveList()
- added SwapMove()
- swapped col and row in Pos[][]
- deleted GenerateMoveListOld()

v1.5 2015-05-16
- added status for castling and column of pawn that moved two steps to Move[]

V1.6 2015-08-30
- added figty move rule

**/
import java.util.*;

public class Move
{
    // Move list size 
    public static final int MAX_NUMBER_MOVE_LIST            = 250;        // Article say there are a maximum of 218 moves possible
    public static final int ENTRIES_MOVE_LIST               = 18;
    
    // Entries move list
    public static final int FIGURE                          = 0;
    public static final int COL                             = 1;
    public static final int ROW                             = 2;
    public static final int FIGURE_P                        = 3;
    public static final int FIGURE_N                        = 4;
    public static final int COL_N                           = 5;
    public static final int ROW_N                           = 6;
    public static final int EN_PASSANT_STATUS               = 7;
    public static final int WHITE_LONG_CASTLING             = 8;
    public static final int WHITE_SHORT_CASTLING            = 9;
    public static final int BLACK_LONG_CASTLING             = 10;
    public static final int BLACK_SHORT_CASTLING            = 11;
    public static final int COLUMN_PAWN_MOVED_TWO_STEPS     = 12;
    public static final int CHECK_STATUS                    = 13;    
    public static final int POSITION_STATUS                 = 14;    
    public static final int RATING                          = 15;
    public static final int REPETITIVE_POSITION_COUNTER         = 16; 
    public static final int FIFTY_MOVE_COUNTER              = 17;    
      
    
    public static int[] WhiteFigure                     = { Position.WHITE_KING, 
                                                            Position.WHITE_QUEEN, 
                                                            Position.WHITE_ROOK, 
                                                            Position.WHITE_KNIGHT, 
                                                            Position.WHITE_BISHOP, 
                                                            Position.WHITE_PAWN};
                                                            
    public static int[] BlackFigure                     = { Position.BLACK_KING, 
                                                            Position.BLACK_QUEEN, 
                                                            Position.BLACK_ROOK, 
                                                            Position.BLACK_KNIGHT, 
                                                            Position.BLACK_BISHOP, 
                                                            Position.BLACK_PAWN};
            
    public static int[] WhitePromotionFigure            = { Position.WHITE_QUEEN, 
                                                            Position.WHITE_ROOK, 
                                                            Position.WHITE_KNIGHT, 
                                                            Position.WHITE_BISHOP};
                                                            
    public static int[] BlackPromotionFigure            = { Position.BLACK_QUEEN, 
                                                            Position.BLACK_ROOK, 
                                                            Position.BLACK_KNIGHT, 
                                                            Position.BLACK_BISHOP};
                                                            
    // Move list display format 
    public static final int LINE                                = 0;
    public static final int LIST                                = 1;    
    public static final int TABLE                               = 2;
    
    // Move rating display format 
    public static final int SHOW_NO_RATING                      = 0;
    public static final int SHOW_RATING_LAST_MOVE               = 1;    
    public static final int SHOW_RATING_EVERY_MOVE              = 2;     
    
    // Parameter for adding move to move hostory
    public static final int DO_NOT_ADD_TO_MOVE_HISTORY          = 0;
    public static final int ADD_TO_MOVE_HISTORY                 = 1;   
    
    // Return value of GenerateMoveList()
    public static final int CAN_TAKE_KING                       = -2; 
    public static final int CAN_NOT_TAKE_OPPONENT_KING          = -1; 
    public static final int NO_MOVE_ADDED                       = 0;
    public static final int MOVE_ADDED                          = 1;    

    public static int MoveListIteration                         = 0;
    
    public static final int ALL                                 = 0;
    public static final int START                               = 1;
    public static final int STOP                                = 2;    
    
    public static final int NUMBER_REPETIVE_POSITIONS_TO_CAUSE_DRAW = 3; 
    public static final int PLYS_PER_REPETITIVE_MOVE            = 4;     
    
    public static final int PLY_PER_MOVE                        = 2;
    
    
    //public static String[] MoveTable = new String[MAX_NUMBER_MOVE_LIST];
    
    public static void Move()
    {
        // initialise instance variables
        //int x = 0;
    }
        
    public static void EmptyMoveList(int[][] MoveList)
    {
        int l;
        int j;                

        for(l = 0; l < MoveList.length; l++)
        {
            for(j = 0; j < ENTRIES_MOVE_LIST; j++)
            {
                MoveList[l][j] = 0;
            }
        }
    }

    public static void CreateMoveTable(int[][] MovePath, int BoundryType, int BoundryValue, String[] MoveTable, int ListFormat, int RatingFormat)    //Write move from MoveList[][] as string into MoveTable[] wich is a global list of strings
    {
        int l;
        int begin   = 0;
        int end     = 0;
        int t;
        String str;
        
        for(t = 0; t  < MAX_NUMBER_MOVE_LIST; t++)
        {
            MoveTable[t] = "0";
        }
        
        switch(BoundryType)
        {
            case START:
                begin = BoundryValue - 1;
                for(end = BoundryValue - 1; MovePath[end][FIGURE] != Position.EMPTY; end++)     
                {
                }
                break;
                
            case STOP:
                begin = 0;
                end = BoundryValue;
                break;       
        }        
        
        for(l = begin; l <= end; l++)     
        {
            
            if(Position.GetFigureColor(MovePath[0][FIGURE]) == Position.WHITE_FIGURE)
            {   // White move is first move
                switch(ListFormat)
                {
                    case LINE:
                        t = 0;
                        break;
                        
                    case TABLE:
                        t = 1 + (l / 2);
                        break;
                }
                
                if((l % 2) == 0)
                {
                    switch(ListFormat)
                    {
                        case LINE:
                            if (l  == 0)
                            {
                                MoveTable[t] = String.format("%2d. ", (l / 2) + 1);
                            }
                            else
                            {
                                str = String.format("%2d. ", (l / 2) + 1);
                                MoveTable[t] = MoveTable[t].concat(str);
                            }
                            break;
                            
                        case TABLE:
                            MoveTable[t] = String.format("%2d. ", (l / 2) + 1);
                            break;
                    }
                }    
                else
                {                                                                                       // Black is first move
                    switch(ListFormat)
                    {
                        case LINE:
                            t = 0;
                            break;
                        
                        case TABLE:
                            t = 1 + (l / 2);
                            break;
                    }                    
                    
                    if(l == 0)
                    {
                        MoveTable[t] = String.format("1. ...     ");
                    }
                    
                    if((l % 2) == 1)
                    {     
                        str = String.format("%2d. ", (l+1) / 2 + 1);
                        MoveTable[t] = MoveTable[t].concat(str);            
                    }     
                }

                if(((MovePath[l][FIGURE] == Position.WHITE_KING) || (MovePath[l][FIGURE] == Position.BLACK_KING)) && 
                    (MovePath[l][COL] == Position.E) && 
                    ((MovePath[l][COL_N] == Position.C) || (MovePath[l][COL_N] == Position.G)))
                {
                    switch(MovePath[l][COL_N])
                    {
                        case Position.C:
                            MoveTable[t] = MoveTable[t].concat(" 0-0-0");                    
                            break;
                        
                        case Position.G:
                            MoveTable[t] = MoveTable[t].concat("   0-0");                                       
                            break;
                    }
                }                
                else
                {            
                    DisplayFigureTable(             MoveTable, t, MovePath[l][FIGURE]);
                    DisplayColTable(                MoveTable, t, MovePath[l][COL]);
                    DisplayRowTable(                MoveTable, t, MovePath[l][ROW]);
                    DisplayMoveTypeTable(           MoveTable, t, MovePath[l][FIGURE], MovePath[l][FIGURE_P], MovePath[l][COL], MovePath[l][COL_N]);
                    DisplayColTable(                MoveTable, t, MovePath[l][COL_N]);
                    DisplayRowTable(                MoveTable, t, MovePath[l][ROW_N]);  
                    if((MovePath[l][FIGURE] == Position.WHITE_PAWN) && (MovePath[l][ROW_N] == Position.WHITE_PAWN_PROMOTION_ROW) ||
                        (MovePath[l][FIGURE] == Position.BLACK_PAWN) && (MovePath[l][ROW_N] == Position.BLACK_PAWN_PROMOTION_ROW))
                    {
                        DisplayFigureTable(         MoveTable, t, MovePath[l][FIGURE_N]);
                    }
                    DisplayEnPassantStatusTable(    MoveTable, t, MovePath[l][EN_PASSANT_STATUS]);                 // .... replace with pawn query....     
                    DisplayCheckStatusTable(        MoveTable, t, MovePath[l][CHECK_STATUS], MovePath[l][POSITION_STATUS]);              
                    DisplayPositionStatusTable(     MoveTable, t, MovePath[l][POSITION_STATUS]);  
                    DisplayRatingTable(             MoveTable, t, MovePath, l, MovePath[l][RATING], RatingFormat);
                }
            }
        } 
    }
    
    public static void DisplayMoveTableConsole(String[] MoveTable)                           //Display MoveTable[] wich is a global list of strings to console
    {
        int t;  
               Scanner scanner             = new Scanner(System.in);
        
        System.out.println("In DisplayMoveTabelConsole..befinning");
        
        for(t = 0; MoveTable[t] != "0"; t++)
        {
            System.out.println("t = " + t);
            System.out.println(MoveTable[t]);
        }
        
        System.out.println("In DisplayMoveTabelConsole...end"); 
        scanner.nextLine();  
        
    }
    
    public static void DisplayMoveList(int[][] MoveList, int BoundryType, int BoundryValue, int ListFormat, int RatingFormat)  //Display DisplayMoveLis[][] to console    ................... replace with MoveTable later
    {
        int l;
        int i = 0;
        int j;
        int begin = 0;
        int end;
        
        for(end = 0; MoveList[end][FIGURE] != Position.EMPTY; end++)     
        {
        }

        switch(BoundryType)
        {
            case ALL: 
                break;
    
            case START:
                begin = BoundryValue - 1;
                break;
                
            case STOP:
                end = BoundryValue;
                break;       
        }        
        
        for(l = begin; l < end; l++) 
        
        
        
        
       
        //for(l = 0; MoveList[l][FIGURE] != Position.EMPTY; l++)            
        {
            switch(ListFormat)
            {
                case LIST:  
                    System.out.format("%2d. ", l + 1);
                    DisplayMove(MoveList, l, RatingFormat); 
                    System.out.println();
                    break;
   
                case LINE:
                case TABLE:
                    i = l + 1;
                    if(Position.GetFigureColor(MoveList[0][FIGURE]) == Position.WHITE_FIGURE)
                    {   // White move is first move
                        if((i % 2) == 1)
                        {
                            System.out.format("%2d. ", i/2 +1);
                        }
                    }    
                    else
                    {   // Black is first move
                        if(i == 1)
                        {
                            System.out.print   (" 1. ...     "); 
                        }
                        if((i % 2) == 0)
                        {
                            System.out.format("%2d. ", i/2 +1);                    
                        }                          
                    }
                    
                    DisplayMove(MoveList, l, RatingFormat);               
                    System.out.print    (" ");

                    if(((Position.GetFigureColor(MoveList[0][FIGURE]) == Position.WHITE_FIGURE) && ((i % 2) == 0)) ||
                       ((Position.GetFigureColor(MoveList[0][FIGURE]) == Position.BLACK_FIGURE) && ((i % 2) == 1)))
                    {
                        switch (ListFormat)
                        {
                            case LINE:                           
                                System.out.print(" ");
                                
                                break;
                                
                            case TABLE:
                                System.out.println();
                                break;
                        }                        
                    }
                    break;
            }      
        }
         
        switch(ListFormat)
        {     
            case LINE:
                System.out.println();                                    
                break;            
            case TABLE: 
                if(((Position.GetFigureColor(MoveList[0][FIGURE]) == Position.WHITE_FIGURE) && ((i % 2) == 1)) ||
                   ((Position.GetFigureColor(MoveList[0][FIGURE]) == Position.BLACK_FIGURE) && ((i % 2) == 0)))        
                {
                    System.out.println();                                    
                    break;
                }
        }     
    }     
    
    public static void DisplayFigureTable(String[] MoveTable, int t, int figure)
    {
        switch(figure)
        {
            case Position.WHITE_PAWN:
            case Position.BLACK_PAWN:
                MoveTable[t] = MoveTable[t].concat(" ");                
                break;
                        
            case Position.WHITE_ROOK:
            case Position.BLACK_ROOK:   
                MoveTable[t] = MoveTable[t].concat("R");                                
                break;                        
                                              
            case Position.WHITE_KNIGHT:
            case Position.BLACK_KNIGHT:
                MoveTable[t] = MoveTable[t].concat("N");                                
                break;
                                              
            case Position.WHITE_BISHOP:
            case Position.BLACK_BISHOP:  
                MoveTable[t] = MoveTable[t].concat("B");                                
                break;
                                              
            case Position.WHITE_QUEEN:
            case Position.BLACK_QUEEN:  
                MoveTable[t] = MoveTable[t].concat("Q");                                
                break;
                     
            case Position.WHITE_KING:
            case Position.BLACK_KING:   
                System.out.print("K");
                MoveTable[t] = MoveTable[t].concat("K");                                
                break;          
        }
    }
        
    public static void DisplayColTable(String[] MoveTable, int t, int col)
    {
        switch (col)
        {
            case Position.A:
                MoveTable[t] = MoveTable[t].concat("a");                                
                break;
                
            case Position.B:
                MoveTable[t] = MoveTable[t].concat("b ");                
                break;        
                
            case Position.C:
                MoveTable[t] = MoveTable[t].concat("c");                
                break;
                
            case Position.D:
                MoveTable[t] = MoveTable[t].concat("d");                
                break;    
                
            case Position.E:
                MoveTable[t] = MoveTable[t].concat("e");                
                break;
                
            case Position. F:
                MoveTable[t] = MoveTable[t].concat("f ");                
                break;      
                
            case Position.G:
                MoveTable[t] = MoveTable[t].concat("g");                
                break;
                
            case Position.H:
                MoveTable[t] = MoveTable[t].concat("h");                
                break;     
        }
    }
    
    public static void DisplayRowTable(String[] MoveTable, int t, int row)
    {
        String str;
        str = String.format("%1d", row);
        MoveTable[t] = MoveTable[t].concat(str);                
    }
    
    public static void DisplayMoveTypeTable(String[] MoveTable, int t, int Figure, int Figure_p, int col, int col_n)
    {      
        if((Figure_p != Position.EMPTY) ||              // Took opponent figure away
            (((Figure_p == Position.EMPTY) && ((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN)) && (col != col_n))))
        {
            MoveTable[t] = MoveTable[t].concat("x");                
        }
        else
        {
            MoveTable[t] = MoveTable[t].concat("-");                
        }
    }
        
    public static void DisplayEnPassantStatusTable(String[] MoveTable, int t, int EnPassant)
    {      
        if(EnPassant == Position.EN_PASSANT)             
        {
            MoveTable[t] = MoveTable[t].concat("e.p.");                
        }
    }
    
    public static void DisplayCheckStatusTable(String[] MoveTable, int t, int Check, int Mate)
    {      
        if(Check == Position.CHECK)             
        {
            MoveTable[t] = MoveTable[t].concat("+");                
        }
        else
        {
            if(Mate != Position.CHECKMATE)
            {
                MoveTable[t] = MoveTable[t].concat(" ");                
            }
        }
    }
    
    public static void DisplayPositionStatusTable(String[] MoveTable, int t, int PositionStatus)
    {      
        switch(PositionStatus)
        {
            case Position.NO_CONDITION:
                break;
                
            case Position.CHECKMATE:
                MoveTable[t] = MoveTable[t].concat("#");                
                break;

           case Position.STALEMATE:   
                MoveTable[t] = MoveTable[t].concat(" 1/2-1/2 (Stalemate)");       
                break;
                
           case Position.INSUFFICIENT_MATERIAL:   
                MoveTable[t] = MoveTable[t].concat(" 1/2-1/2 (Insufficient material)");     
                break; 
                  
           case Position.THREE_POSITION_REPETITION:
                MoveTable[t] = MoveTable[t].concat(" 1/2-1/2 (Three position repeat)");
                break;
                
           case Position.FIFTY_MOVE:
                MoveTable[t] = MoveTable[t].concat(" 1/2-1/2 (Fifty move rule)");
                break;                   
        }
    }
        
    public static void DisplayRatingTable(String[] MoveTable, int t, int[][] MoveList, int l, int Rating, int RatingFormat)
    {      
        float RatingFloat;
        String str;

        RatingFloat = Rating;
        RatingFloat /= 100;
        str = String.format(" Rating = %.2f", RatingFloat);
        
        switch(RatingFormat)
        {
            case SHOW_NO_RATING:
                break;
                        
            case SHOW_RATING_LAST_MOVE:
                if(MoveList[l+1][FIGURE] == Position.EMPTY)
                {      
                    MoveTable[t] = MoveTable[t].concat(str);   
                }
                break;
                        
            case SHOW_RATING_EVERY_MOVE: 
                MoveTable[t] = MoveTable[t].concat(str);                     
                break;
        }                
    } 
     
    public static void DisplayMove(int[][] MoveList, int l, int RatingFormat)
    {
        if(((MoveList[l][FIGURE] == Position.WHITE_KING) || (MoveList[l][FIGURE] == Position.BLACK_KING)) && 
            (MoveList[l][COL] == Position.E) && 
            ((MoveList[l][COL_N] == Position.C) || (MoveList[l][COL_N] == Position.G)))
        {
            switch(MoveList[l][COL_N])
            {
                case Position.C:
                    System.out.print(" 0-0-0");
                    break;
                        
                case Position.G:
                    System.out.print("   0-0");
                    break;
            }
        }                
        else
        {
            DisplayFigure(          MoveList[l][FIGURE]);
            DisplayCol(             MoveList[l][COL]);
            DisplayRow(             MoveList[l][ROW]);
            DisplayMoveType(        MoveList[l][FIGURE], MoveList[l][FIGURE_P], MoveList[l][COL], MoveList[l][COL_N]);
            DisplayCol(             MoveList[l][COL_N]);
            DisplayRow(             MoveList[l][ROW_N]);  
            if((MoveList[l][FIGURE] == Position.WHITE_PAWN) && (MoveList[l][ROW_N] == Position.WHITE_PAWN_PROMOTION_ROW) ||
                (MoveList[l][FIGURE] == Position.BLACK_PAWN) && (MoveList[l][ROW_N] == Position.BLACK_PAWN_PROMOTION_ROW))
            {
                DisplayFigure(      MoveList[l][FIGURE_N]);
            }
            DisplayEnPassantStatus( MoveList[l][EN_PASSANT_STATUS]);
        }
        DisplayCheckStatus(     MoveList[l][CHECK_STATUS], MoveList[l][POSITION_STATUS]);              
        DisplayPositionStatus(  MoveList[l][POSITION_STATUS]);  
        DisplayRating(          MoveList, l, MoveList[l][RATING], RatingFormat);
    }
        
    public static void DisplayFigure(int figure)
    {
        switch(figure)
        {
            case Position.WHITE_PAWN:
            case Position.BLACK_PAWN:
                System.out.print(" ");
                break;
                        
            case Position.WHITE_ROOK:
            case Position.BLACK_ROOK:   
                System.out.print("R");
                break;                        
                                              
            case Position.WHITE_KNIGHT:
            case Position.BLACK_KNIGHT:
                System.out.print("N");
                break;
                                              
            case Position.WHITE_BISHOP:
            case Position.BLACK_BISHOP:  
                System.out.print("B");
                break;
                                              
            case Position.WHITE_QUEEN:
            case Position.BLACK_QUEEN:  
                System.out.print("Q");
                break;
                     
            case Position.WHITE_KING:
            case Position.BLACK_KING:   
                System.out.print("K");
                break;          
        }
    }
        
    public static void DisplayCol(int col)
    {
        switch (col)
        {
            case Position.A:
                System.out.print("a");
                break;
            case Position.B:
                System.out.print("b");
                break;                
            case Position.C:
                System.out.print("c");
                break;
            case Position.D:
                System.out.print("d");
                break;        
            case Position.E:
                System.out.print("e");
                break;
            case Position. F:
                System.out.print("f");
                break;                
            case Position.G:
                System.out.print("g");
                break;
            case Position.H:
                System.out.print("h");
                break;     
        }
    }
    
    public static void DisplayRow(int row)
    {
        System.out.print(row);
    }
    
    public static void DisplayMoveType(int Figure, int Figure_p, int col, int col_n)
    {      
        if((Figure_p != Position.EMPTY) ||              // Took opponent figure away
            (((Figure_p == Position.EMPTY) && ((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN)) && (col != col_n))))
        {
            System.out.print("x");    
        }
        else
        {
            System.out.print("-");
        }
    }
        
    public static void DisplayEnPassantStatus(int EnPassant)
    {      
        if(EnPassant == Position.EN_PASSANT)             
        {
            System.out.print("e.p.");    
        }
    }
    
    public static void DisplayCheckStatus(int Check, int Mate)
    {      
        //System.out.println("Check =" + Check + " Mate =" + Mate );    
        
        if(Check == Position.CHECK)             
        {
            System.out.print("+");    
        }
        else
        {
            if(Mate != Position.CHECKMATE)
            {
                System.out.print(" ");
            }
        }
    }
    
    public static void DisplayPositionStatus(int PositionStatus)
    {      
        switch(PositionStatus)
        {
            case Position.NO_CONDITION:
                break;
                
            case Position.CHECKMATE:
                System.out.print("#"); 
                break;

           case Position.STALEMATE:   
                System.out.print(" 1/2-1/2 (Stalemate)");
                break;
                
           case Position.INSUFFICIENT_MATERIAL:   
                System.out.print(" 1/2-1/2 (Insufficient material)");
                break; 
                
           case Position.THREE_POSITION_REPETITION:
                System.out.print(" 1/2-1/2 (Three position repeat)");
                break;
                
           case Position.FIFTY_MOVE:
                System.out.print(" 1/2-1/2 (Fifty move rule)");
                break;                
        }
    }
        
    public static void DisplayRating(int[][] MoveList, int l, int Rating, int RatingFormat)
    {      
        float RatingFloat;
        
        RatingFloat = Rating;
        RatingFloat /= 100;

        switch(RatingFormat)
        {
            case SHOW_NO_RATING:
                break;
                        
            case SHOW_RATING_LAST_MOVE:
                if(MoveList[l+1][FIGURE] == Position.EMPTY)
                {
                    System.out.format(" Rating = %.2f", RatingFloat);                       
                }
                break;
                        
            case SHOW_RATING_EVERY_MOVE: 
                System.out.format(" Rating = %.2f", RatingFloat);
                break;
        }                
    } 
    
    public static int GenerateMoveList(int[][] Pos, int[][] MovesPosition, int[][] MovePath, boolean CheckForCanTakeKingOnly)
    {
        int l;
        int[][] CandidateMoveList   = new int[MAX_NUMBER_MOVE_LIST][ENTRIES_MOVE_LIST];
        
        EmptyMoveList(CandidateMoveList);  
        Position.GenerateCandidateMoveList(Pos, CandidateMoveList, Position.MOVES);
        
        for(l = 0; CandidateMoveList[l][FIGURE] != Position.EMPTY; l++)
        {      
            IfNoReceivingCheckAddMoveToMoveList(
                Pos, 
                CandidateMoveList[l][COL],
                CandidateMoveList[l][ROW],
                CandidateMoveList[l][FIGURE_N],
                CandidateMoveList[l][COL_N],
                CandidateMoveList[l][ROW_N],
                //MoveList, 
                //MoveIteration,
                //MoveHistory); 
                MovesPosition,
                MovePath);
        }
        return 0;
    } 
    
    //public static void IfNoReceivingCheckAddMoveToMoveList(int[][] Pos, int col, int row, int Figure_n, int col_n, int row_n, int[][] MoveList, int[][] MoveHistory)
    public static void IfNoReceivingCheckAddMoveToMoveList(int[][] Pos, int col, int row, int Figure_n, int col_n, int row_n, int[][] MovesPosition, int[][] MovePath)
    {
        boolean Status;
        int EnPassantStatus;
        int Figure;
        int Figure_p;   
        int TempPawn = 0;
        int MoveNumber = 0;
        int i;
        int temp_ep;
        int[] CastlingLocal         = new int[4];    
        int RepetitivePositionCounterLocal;
        int FiftyMoveCounterLocal;
        
        Figure_p            = Pos[row_n][col_n];
        Figure              = Pos[row][col];  

        if(((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN)) && (col != col_n) && (Figure_p == Position.EMPTY))
        {
            TempPawn = Pos[row][col_n];
            EnPassantStatus = Position.EN_PASSANT;
        }
        else
        {
            EnPassantStatus = Position.NO_EN_PASSANT; 
        }        


        if((Figure == Position.WHITE_ROOK) || (Figure == Position.BLACK_ROOK) || (Figure == Position.WHITE_KING) || (Figure == Position.BLACK_KING))                               
        {
            Position.StoreCastling(CastlingLocal, Pos);
        }                        
        temp_ep = Position.GetColumnPawnMovedTwoRows(Pos);                          // save previous column where pawn moved two steps in temp_ep
        RepetitivePositionCounterLocal = Position.GetNumberOfRepetitivePositions(Pos);
        FiftyMoveCounterLocal = Position.GetNumberOfMovesWithNoPawnMoveOrCapture(Pos);
        
        //System.out.println("In IfNoReceivingCheckAddMoveToMoveList() calling MakeMove()");
        
        //MakeMove(Pos, col, row, Figure_n, col_n, row_n, MoveHistory, DO_NOT_ADD_TO_MOVE_HISTORY);
        MakeMove(Pos, col, row, Figure_n, col_n, row_n, MovePath, DO_NOT_ADD_TO_MOVE_HISTORY);     // Creates new position which needs to be investigated if own king can be captured 
        
        if(!Position.Check(Pos, Position.RECEIVING_CHECK))                                                  // Check for receiving check
        {                                                                                                   // No receiving check, add move to MovesPosition
            for(MoveNumber = 0; MovesPosition[MoveNumber][FIGURE] != Position.EMPTY; MoveNumber++)          // Go to next available Move
            {
                //  System.out.println("Checking MoveList entry " + MoveNumber);
            }
            MovesPosition[MoveNumber][FIGURE]                = Figure;
            MovesPosition[MoveNumber][COL]                   = col;
            MovesPosition[MoveNumber][ROW]                   = row;
            MovesPosition[MoveNumber][FIGURE_P]              = Figure_p;       
            MovesPosition[MoveNumber][FIGURE_N]              = Figure_n;
            MovesPosition[MoveNumber][COL_N]                 = col_n;
            MovesPosition[MoveNumber][ROW_N]                 = row_n;
            MovesPosition[MoveNumber][EN_PASSANT_STATUS]     = EnPassantStatus;                                                             // Needed to display e.p. as part of move 
            //MovesPosition[MoveNumber][POSITION_STATUS]       = Position.GetPositionStatus(Pos, MoveList);            
            MovesPosition[MoveNumber][POSITION_STATUS]       = Position.GetPositionStatus(Pos, MovePath);                                   // Needed to diplay checkmate of draw as part of move 
            MovesPosition[MoveNumber][CHECK_STATUS]          = Position.GetCheckStatus(Pos, MovesPosition[MoveNumber][POSITION_STATUS]);    // Needed to display check as part of move
            MovesPosition[MoveNumber][RATING]                = Rating.GetRating(Pos, MovesPosition[MoveNumber][POSITION_STATUS]);           // Needed to display rating as part of move
        }

        // revert move
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
  
        Position.SetColumnPawnMovedTwoRows(Pos, temp_ep);                           // Restore column where pawn moved two steps in temp_ep
                
        if((Figure == Position.WHITE_ROOK) || (Figure == Position.BLACK_ROOK) || (Figure == Position.WHITE_KING) || (Figure == Position.BLACK_KING))                               
        {
            Position.RestoreCastling(CastlingLocal, Pos);
        }
        Position.SetNumberOfRepetitivePositions(Pos, RepetitivePositionCounterLocal);        
        Position.SetNumberOfMovesWithNoPawnMoveOrCapture(Pos, FiftyMoveCounterLocal);
    }
    
    public static void AddMoveToMoveList(int[][] MoveList, int Figure, int col, int row, int Figure_p, int Figure_n, int col_n, int row_n)
    {
        int l;
        int i;
        
        //System.out.println("AddMoveToMoveList");
        for(l = 0; MoveList[l][FIGURE] != Position.EMPTY; l++)
        {
          //  System.out.println("Checking MoveList entry " + MoveNumber);
        }
        MoveList[l][FIGURE]     = Figure;
        MoveList[l][COL]        = col;
        MoveList[l][ROW]        = row;
        MoveList[l][FIGURE_P]   = Figure_p;       
        MoveList[l][FIGURE_N]   = Figure_n;
        MoveList[l][COL_N]      = col_n;
        MoveList[l][ROW_N]      = row_n;
        //System.out.print("Added Move " + MoveNumber);          
        //System.out.println("\t Rating = " + MoveList[l][RATING]);         
    }
    
    public static void AddMove(int[][] Move, int m, int[][] List)
    {
        int l;
        int i;
        
        for(l = 0; List[l][FIGURE] != Position.EMPTY; l++)
        {
          //  System.out.println("Move to end of list");
        }
        
        for(i = 0; i < ENTRIES_MOVE_LIST; i++)
        {
            Move[l][i] = List[m][i];
        }
    }
    
    public static void SetMove(int[][] Move, int m, int[][] MovePath, int l)
    {
        int i;
        
        for(i = 0; i < ENTRIES_MOVE_LIST; i++)
        {
            MovePath[l][i] = Move[m][i];
            //System.out.println("l =" + l + "Copy " + List[l][i]);
        }
       
        for(l++; l < MovePath.length; l++)          // Set remainder of List to 0
        {
            for(i = 0; i < ENTRIES_MOVE_LIST; i++)
            {
                MovePath[l][i] = 0;
            }
        }
    }   
    
    public static void ClearMove(int[][] MovePath, int p)
    {
        int i;
        
        for(i = 0; i < ENTRIES_MOVE_LIST; i++)
        {
            MovePath[p][i] = 0;
        }
    }
        
    public static boolean IdenticalMove(int[][] MoveList, int a, int b)
    {
        int e;
        
        for(e = 0; e < ENTRIES_MOVE_LIST; e++)      
        {
            if(MoveList[a][e] != MoveList[b][e])
            {
                return false;
            }
        }
        return true;
    }   
      
    public static int IndexOfLastMove(int[][] MovePath)
    {
        int i;
          
        for(i = 0; MovePath[i][FIGURE] != Position.EMPTY; i++)                                                  //  Move to end of list
        {
        }       
        i--; 
        return(i);                                                                                            // Total of i moves in move list  
    }

    public static int RepetitivePositions(int[][] MovePath)  
    {
        int RepetitivePositionCounter; 
        int p = IndexOfLastMove(MovePath);                                                                                               // There can be more repetitive positions that what is covered here                                                                                                        
    
              
        //System.out.println("In RepetitivePositions ... before DisplayMoveList()");
        //DisplayMoveList(MovePath,  ALL, 0, TABLE, SHOW_NO_RATING);
        //System.out.println("In RepetitivePositions ... after DisplayMoveList()");

        for(RepetitivePositionCounter = 1; ((RepetitivePositionCounter < NUMBER_REPETIVE_POSITIONS_TO_CAUSE_DRAW) && ((RepetitivePositionCounter * PLYS_PER_REPETITIVE_MOVE) + 1 <= p)); RepetitivePositionCounter++)
        {
            if(!MovedToSamePosition(MovePath, p - ((RepetitivePositionCounter - 1) * PLYS_PER_REPETITIVE_MOVE)))
            {
                break;
            }
        }
        return(RepetitivePositionCounter);
    }
    
    public static boolean MovedToSamePosition(int[][] MovePath, int p)     
    {
        return((MovePath[p    ][FIGURE] == MovePath[p -     PLYS_PER_REPETITIVE_MOVE][FIGURE])  &&                      // Same figure type moved to same field than two moves before
               (MovePath[p    ][COL_N]  == MovePath[p -     PLYS_PER_REPETITIVE_MOVE][COL_N])   &&                      // Moved to same column than two moves before 
               (MovePath[p    ][ROW_N]  == MovePath[p -     PLYS_PER_REPETITIVE_MOVE][ROW_N])   &&                         // Moved to same row than two moves before                  
               (MovePath[p - 1][FIGURE] == MovePath[p - 1 - PLYS_PER_REPETITIVE_MOVE][FIGURE])  &&                      // Same figure type moved to same field than two moves before
               (MovePath[p - 1][COL_N]  == MovePath[p - 1 - PLYS_PER_REPETITIVE_MOVE][COL_N])   &&                      // Moved to same column than two moves before 
               (MovePath[p - 1][ROW_N]  == MovePath[p - 1 - PLYS_PER_REPETITIVE_MOVE][ROW_N]));   
    }

    public static void CopyMoveList(int[][] FromMoveList, int CopyStart, int[][] ToMoveList)
    {
        int l;
        int e;
        int r;
        
        EmptyMoveList(ToMoveList);

        for(l = CopyStart; FromMoveList[l][FIGURE] != Position.EMPTY; l++)
        {
            for(e = 0; e < ENTRIES_MOVE_LIST; e++)      
            {
                ToMoveList[l - CopyStart][e] = FromMoveList[l][e];
            }
        }     
    }    
        
    public static int Castling(int[][] Pos, int[] CastlingList)
    {
        int list = 0;
        int row = 0;
        
        //System.out.println("Enter Castling ...");
        //Position.DisplayPosition(Pos);
       
        switch(Position.GetMoveColor(Pos))
        {
            case Position.WHITE_MOVE:
                row = Position.WHITE_CASTLING_ROW; 
                break;
                
            case Position.BLACK_MOVE:
                row = Position.BLACK_CASTLING_ROW; 
                break;
        }  
       
        if(((Position.GetMoveColor(Pos)                     == Position.WHITE_MOVE)                 && 
            (Position.GetWhiteLongCastling(Pos)             == Position.WRA1_AND_WKE1_DID_NOT_MOVE) && 
            (Pos[Position.WHITE_CASTLING_ROW][Position.A]   == Position.WHITE_ROOK)                 && 
            (Pos[Position.WHITE_CASTLING_ROW][Position.B]   == Position.EMPTY)                      && 
            (Pos[Position.WHITE_CASTLING_ROW][Position.C]   == Position.EMPTY)                      && 
            (Pos[Position.WHITE_CASTLING_ROW][Position.D]   == Position.EMPTY)                      && 
            (Pos[Position.WHITE_CASTLING_ROW][Position.E]   == Position.WHITE_KING))
         ||((Position.GetMoveColor(Pos)                     == Position.BLACK_MOVE)                 && 
            (Position.GetBlackLongCastling(Pos)             == Position.BRA8_AND_BKE8_DID_NOT_MOVE) && 
            (Pos[Position.BLACK_CASTLING_ROW][Position.A]   == Position.BLACK_ROOK)                 && 
            (Pos[Position.BLACK_CASTLING_ROW][Position.B]   == Position.EMPTY)                      && 
            (Pos[Position.BLACK_CASTLING_ROW][Position.C]   == Position.EMPTY)                      && 
            (Pos[Position.BLACK_CASTLING_ROW][Position.D]   == Position.EMPTY)                      && 
            (Pos[Position.BLACK_CASTLING_ROW][Position.E]   == Position.BLACK_KING)))                    
        {   //Long Castling

            //System.out.println("Message from King: Neither King nor Rook has moved before and fields between King and Rook are Position.EMPTY for Long Castling.");
            //System.out.println("Checking now if King is under check or if King would travel over a fields that is under check for Long Castling.");
            if(CastlingFieldsCheckFree(Pos, Position.LONG_CASTLING))
            {
                CastlingList[list++] = Position.LONG_CASTLING;
            }  
        }
        
        if(((Position.GetMoveColor(Pos)                     == Position.WHITE_MOVE)                 && 
            (Position.GetWhiteShortCastling(Pos)            == Position.WRH1_AND_WKE1_DID_NOT_MOVE) && 
            (Pos[Position.WHITE_CASTLING_ROW][Position.E]   == Position.WHITE_KING)                 && 
            (Pos[Position.WHITE_CASTLING_ROW][Position.F]   == Position.EMPTY)                      && 
            (Pos[Position.WHITE_CASTLING_ROW][Position.G]   == Position.EMPTY)                      && 
            (Pos[Position.WHITE_CASTLING_ROW][Position.H]   == Position.WHITE_ROOK))
         ||((Position.GetMoveColor(Pos)                     == Position.BLACK_MOVE)                 && 
            (Position.GetBlackShortCastling(Pos)            == Position.BRH8_AND_BKE8_DID_NOT_MOVE) && 
            (Pos[Position.BLACK_CASTLING_ROW][Position.E]   == Position.BLACK_KING)                 && 
            (Pos[Position.BLACK_CASTLING_ROW][Position.F]   == Position.EMPTY)                      && 
            (Pos[Position.BLACK_CASTLING_ROW][Position.G]   == Position.EMPTY)                      && 
            (Pos[Position.BLACK_CASTLING_ROW][Position.H]   == Position.BLACK_ROOK)))
        {   //Short Castling 
            //System.out.println("Message from King: Neither King nor Rook has moved before and fields between King and Rook are Position.EMPTY for Short Castling.");
            //System.out.println("Checking now if King is under check or if King would travel over a fields that is under check for Short Castling.");

            if(CastlingFieldsCheckFree(Pos, Position.SHORT_CASTLING))
            {
                CastlingList[list++] = Position.SHORT_CASTLING;
            }
        }
        //System.out.println("Castling List lenght =" + CastlingList.length);
        //System.out.println("list =" + list);
        //System.out.println("Leaving Castling ...");
        //Position.DisplayPosition(Pos);        
        return(list);
    }
        
    public static boolean CastlingFieldsCheckFree(int[][] Pos, int CastlingType)
    {
        int NewKingColumn;
        int temp_figure;
        int row                     = 0;
        int KingColumn              = Position.E;
        int KingColumnStep          = 0; 
        boolean CastlingPossible    = true;
        
        switch(CastlingType)
        {
            case Position.LONG_CASTLING:
                KingColumnStep = Position.COLUMN_DOWN; 
                break;
                
            case Position.SHORT_CASTLING:
                KingColumnStep = Position.COLUMN_UP; 
                break;
        } 

        switch(Position.GetMoveColor(Pos))
        {
            case Position.WHITE_MOVE:
                row = Position.WHITE_CASTLING_ROW; 
                break;
                
            case Position.BLACK_MOVE:
                row = Position.BLACK_CASTLING_ROW; 
                break;
        } 
        
        for(NewKingColumn = KingColumn; (Math.abs(NewKingColumn - KingColumn) <= Position.KING_CASTLING_STEPS) && CastlingPossible; NewKingColumn += KingColumnStep)
        {                                    
            if(Chess.DebugLevel > Settings.MEDIUM)
            {
                System.out.println("Examining if Field[" + NewKingColumn + "][" + row + " is under Check");
            }
                        
            temp_figure                 = Pos[row][KingColumn];     // !!!!! Save to temp_figure first to cover the case that NewKingColumn == King Column
            Pos[row][KingColumn]        = Position.EMPTY;            
            Pos[row][NewKingColumn]     = temp_figure;             // WHITE or BLACK KING;

            CastlingPossible            = !Position.Check(Pos, Position.RECEIVING_CHECK);
     
            Pos[row][NewKingColumn]     = Position.EMPTY;    // !!!!! Set Pos[NewKingColumn][row] to EMPTY first to cover the case that NewKingColumn == King Column
            Pos[row][KingColumn]        = temp_figure;             
        }
        return(CastlingPossible);
    }
    
    public static void MakeMove(int[][] Pos, int col, int row, int Figure_n, int col_n, int row_n, int[][] MovePath, int AddMoveToHistory)
    {                                                                               // Makes the move by updating the position in Pos[][] 
        int m;                                                                      // Is called by user makes move or by computer makes move and move is added to move path
        int Figure;
        int Figure_p;
        int EnPassantStatus;
        
        int WhiteLongCastlingPreviousPosition           = 0;
        int WhiteShortCastlingPreviousPosition          = 0;
        int BlackLongCastlingPreviousPosition           = 0;
        int BlackShortCastlingPreviousPosition          = 0;
        int ColumnPawnMovedTwoStepsPreviousPosition     = 0;
        int RepetitivePositionCounterPreviousPosition       = 0;
        int FifyMoveCounterPreviousPosition             = 0;
        
        //System.out.println("In MakeMove= ");
        
        if(AddMoveToHistory == ADD_TO_MOVE_HISTORY)                                // Save postion status information from the previous position before move is made to local variables
        {                                                                          // The position status information from the previous position is used to reconstruct the previoosu position if moves are going backwards             
            WhiteLongCastlingPreviousPosition                       = Position.GetWhiteLongCastling(Pos);
            WhiteShortCastlingPreviousPosition                      = Position.GetWhiteShortCastling(Pos);
            BlackLongCastlingPreviousPosition                       = Position.GetBlackLongCastling(Pos);
            BlackShortCastlingPreviousPosition                      = Position.GetBlackShortCastling(Pos);
            ColumnPawnMovedTwoStepsPreviousPosition                 = Position.GetColumnPawnMovedTwoRows(Pos);  
            RepetitivePositionCounterPreviousPosition               = Position.GetNumberOfRepetitivePositions(Pos);
            FifyMoveCounterPreviousPosition                         = Position.GetNumberOfMovesWithNoPawnMoveOrCapture(Pos);
        }
        
        // Make move       
        Figure              = Pos[row][col];
        Figure_p            = Pos[row_n][col_n];        
        Pos[row][col]       = Position.EMPTY;
        Pos[row_n][col_n]   = Figure_n; 
        
        if(((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN)) && (col != col_n) && (Figure_p == Position.EMPTY))
        {
            Pos[row][col_n] = Position.EMPTY;
            EnPassantStatus = Position.EN_PASSANT;
        }
        else
        {
            EnPassantStatus = Position.NO_EN_PASSANT; 
        }

        if((Figure == Position.WHITE_KING) && (col == Position.E))
        {
            switch(col_n)
            {
                case Position.C:                                                                // Long white castling, move rook
                    Pos[Position.WHITE_CASTLING_ROW][Position.A] = Position.EMPTY;
                    Pos[Position.WHITE_CASTLING_ROW][Position.D] = Position.WHITE_ROOK;
                    break;
                        
                case Position.G:
                    Pos[Position.WHITE_CASTLING_ROW][Position.H] = Position.EMPTY;              // Short white castling, move rook
                    Pos[Position.WHITE_CASTLING_ROW][Position.F] = Position.WHITE_ROOK;
                    break;
            }
        }

        if((Figure == Position.BLACK_KING) && (col == Position.E))
        {
            switch(col_n)
            {
                case Position.C:                                                                // Long black castling, move rook
                    Pos[Position.BLACK_CASTLING_ROW][Position.A] = Position.EMPTY;
                    Pos[Position.BLACK_CASTLING_ROW][Position.D] = Position.BLACK_ROOK;
                    break;
                        
                case Position.G:                                                                // Short white castling, move rook
                    Pos[Position.BLACK_CASTLING_ROW][Position.H] = Position.EMPTY;
                    Pos[Position.BLACK_CASTLING_ROW][Position.F] = Position.BLACK_ROOK;
                    break;
            }
        }               
                                                
        if((Figure == Position.WHITE_ROOK) && (col == Position.A) && (row == Position.WHITE_CASTLING_ROW))          // WRa1 moved
        {
            Position.SetWhiteLongCastling(Pos, Position.WRA1_OR_WKE1_DID_MOVE);
        }        
                    
        if((Figure == Position.WHITE_ROOK) && (col == Position.H) && (row == Position.WHITE_CASTLING_ROW))          // WRa8 moved
        {
            Position.SetWhiteShortCastling(Pos, Position.WRH1_OR_WKE1_DID_MOVE);
        }
                
        if((Figure == Position.BLACK_ROOK) && (col == Position.A) && (row == Position.BLACK_CASTLING_ROW))          // BRa8 moved
        {
            Position.SetBlackLongCastling(Pos, Position.BRA8_OR_BKE8_DID_MOVE);
        }        
                
        if((Figure == Position.BLACK_ROOK) && (col == Position.H) && (row == Position.BLACK_CASTLING_ROW))          // BRa8 moved
        {
            Position.SetBlackShortCastling(Pos, Position.BRH8_OR_BKE8_DID_MOVE);
        }  
                
        if((Figure == Position.WHITE_KING) && (col == Position.E) && (row == Position.WHITE_CASTLING_ROW))     // WKe1 moved, White short and long castling not possible anymore
        {
            Position.SetWhiteLongCastling(Pos, Position.WRA1_OR_WKE1_DID_MOVE);
            Position.SetWhiteShortCastling(Pos, Position.WRA1_OR_WKE1_DID_MOVE);
        }     
                                        
        if((Figure == Position.BLACK_KING) && (col == Position.E) && (row == Position.BLACK_CASTLING_ROW))        // BKe8 moved, Black short and long castling not possible anymore
        {                 
            Position.SetBlackLongCastling(Pos, Position.BRA8_OR_BKE8_DID_MOVE);
            Position.SetBlackShortCastling(Pos, Position.BRA8_OR_BKE8_DID_MOVE); 
        }    
        
        if(((Figure == Position.WHITE_PAWN) && (row == 2) &&  (row_n == 4)) ||
            ((Figure == Position.BLACK_PAWN) && (row == 7) &&  (row_n == 5)))
        {
            Position.SetColumnPawnMovedTwoRows(Pos, col);                         // Save new column where pawn moved two steps in ColumnPawnMovedTwoRows             
        }
        else
        {
            Position.SetColumnPawnMovedTwoRows(Pos, 0);   
        } 

        if((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN) || (Figure_p != Position.EMPTY))   // Pawn move or capture
        {
            Position.SetNumberOfMovesWithNoPawnMoveOrCapture(Pos,0);
        }
        else
        {
            //m = Position.GetNumberOfMovesWithNoPawnMoveOrCapture(Pos);
            //System.out.println("In MakeMove()... FiftyMove before = " + m);
            Position.IncrementNumberOfMovesWithNoPawnMoveOrCapture(Pos);
            
            //m = Position.GetNumberOfMovesWithNoPawnMoveOrCapture(Pos);            
            //System.out.println("In MakeMove()... FiftyMove after = " + m);            
        }
        
        if(AddMoveToHistory == ADD_TO_MOVE_HISTORY)
        {
            for(m = 0; MovePath[m][FIGURE] != Position.EMPTY; m++)          // Go to next available Move
            {
            }

            m = Chess.Ply;
            MovePath[m][Move.FIGURE]                    = Figure;                 
            MovePath[m][Move.COL]                       = col;
            MovePath[m][Move.ROW]                       = row;
            MovePath[m][Move.FIGURE_P]                  = Figure_p; 
            MovePath[m][Move.FIGURE_N]                  = Figure_n;                 
            MovePath[m][Move.COL_N]                     = col_n;
            MovePath[m][Move.ROW_N]                     = row_n;   
            MovePath[m][EN_PASSANT_STATUS]              = EnPassantStatus;                                  
            MovePath[m][WHITE_LONG_CASTLING]            = WhiteLongCastlingPreviousPosition;                                 
            MovePath[m][WHITE_SHORT_CASTLING]           = WhiteShortCastlingPreviousPosition;                                    
            MovePath[m][BLACK_LONG_CASTLING]            = BlackLongCastlingPreviousPosition;                                    
            MovePath[m][BLACK_SHORT_CASTLING]           = BlackShortCastlingPreviousPosition;                                      
            MovePath[m][COLUMN_PAWN_MOVED_TWO_STEPS]    = ColumnPawnMovedTwoStepsPreviousPosition;                                    
            MovePath[m][POSITION_STATUS]                = Position.GetPositionStatus(Pos, MovePath);       
            MovePath[m][CHECK_STATUS]                   = Position.GetCheckStatus(Pos, MovePath[m][POSITION_STATUS]);
            MovePath[m][RATING]                         = Rating.GetRating(Pos, MovePath[m][POSITION_STATUS]);  
            MovePath[m][REPETITIVE_POSITION_COUNTER]    = RepetitivePositionCounterPreviousPosition;
            MovePath[m][FIFTY_MOVE_COUNTER]             = FifyMoveCounterPreviousPosition;
                        
            Chess.Ply++;
        }
        
        Position.SetNumberOfRepetitivePositions(Pos,RepetitivePositions(MovePath)); //
        
        
        
    }
    
    //public static boolean UserMoveSuccessful(int[][] Pos, int col, int row, int Figure_n, int col_n, int row_n, int[][] MoveIteration, int[][] MoveHistory)       
    public static boolean UserMoveSuccessful(int[][] Pos, int col, int row, int Figure_n, int col_n, int row_n, int[][] MovePath)       
    {        
        int[][] UserMovesPosition            = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];
        int m;
        boolean CheckForCanTakeKingOnly = false;

        EmptyMoveList(UserMovesPosition);
        //GenerateMoveList(Pos, MoveIteration, UserMoveList, MoveHistory, CheckForCanTakeKingOnly);
        //GenerateMoveList(Pos, MoveIteration, UserMove, CheckForCanTakeKingOnly);        
        GenerateMoveList(Pos, UserMovesPosition, MovePath, CheckForCanTakeKingOnly);
        for(m = 0; UserMovesPosition[m][Move.FIGURE] != Position.EMPTY; m++)
        {
            if((UserMovesPosition[m][COL]       == col)         &&
               (UserMovesPosition[m][ROW]       == row)         &&  
               (UserMovesPosition[m][FIGURE_N]  == Figure_n)    &&
               (UserMovesPosition[m][COL_N]     == col_n)       &&          
               (UserMovesPosition[m][ROW_N]     == row_n))
            {
                //System.out.println("In UserMoveSuccessful calling MakeMove()");
                //Move.MakeMove(Pos, col, row, Figure_n, col_n, row_n, MoveHistory, Move.ADD_TO_MOVE_HISTORY);
                Move.MakeMove(Pos, col, row, Figure_n, col_n, row_n, MovePath, Move.ADD_TO_MOVE_HISTORY);
                return true;
            }   
        }
        System.out.println("Incorrect user Move.");
        return false;
    }  
    
    public static void SortMoveList(int[][] MoveList)
    {
        int m;
        int n;
        int MaxRating;
                
        if(Position.GetFigureColor(MoveList[0][FIGURE]) == Position.WHITE_FIGURE)
        {
            for(m = 0; MoveList[m][FIGURE] != Position.EMPTY; m++)          // Go to next available Move
            {
                MaxRating = MoveList[m][RATING];
                for(n = m + 1; MoveList[n][FIGURE] != Position.EMPTY; n++)
                {           
                    if(MoveList[n][RATING] > MaxRating)
                    {
                        MaxRating = MoveList[n][RATING];
                        SwapMove(MoveList, m, n);
                    }
                }
            }    
        }
        else
        {
            for(m = 0; MoveList[m][FIGURE] != Position.EMPTY; m++)          // Go to next available Move
            {
                MaxRating = MoveList[m][RATING];
                for(n = m + 1; MoveList[n][FIGURE] != Position.EMPTY; n++)
                {          
                    if(MoveList[n][RATING] < MaxRating)
                    {
                        MaxRating = MoveList[n][RATING];
                        SwapMove(MoveList, m, n);
                    }
                }
            }                
        }
    }        
    
    public static void SwapMove(int[][] MoveList, int m, int n)
    {
        int Temp;
        int i;

        for(i = 0; i < ENTRIES_MOVE_LIST; i++)
        {
            Temp = MoveList[m][i];            
            MoveList[m][i] = MoveList[n][i];
            MoveList[n][i] = Temp;            
        }
    }
    
    public static void RevertMove(int[][] Pos, int[][] MovePath)
    {
        int m;
        int e;
        int Figure;
        int col;
        int row;
        int Figure_p;
        int col_n;
        int row_n;
        
        for(m = 0; MovePath[m][FIGURE] != Position.EMPTY; m++)          // Go to last nove
        {
        }
        m--;
        if(m == -1)                                                         // No move 
        {
            return;
        }  
        
        Figure              = MovePath[m][Move.FIGURE];
        col                 = MovePath[m][Move.COL];
        row                 = MovePath[m][Move.ROW];
        Figure_p            = MovePath[m][Move.FIGURE_P];
        col_n               = MovePath[m][Move.COL_N];
        row_n               = MovePath[m][Move.ROW_N];
        
        Pos[row][col]       = Figure;
        Pos[row_n][col_n]   = Figure_p;      
        
        if((Figure == Position.WHITE_KING) && (col == Position.E))
        {
            switch(col_n)
            {
                case Position.C:
                    Pos[Position.WHITE_CASTLING_ROW][Position.A] = Position.WHITE_ROOK;
                    Pos[Position.WHITE_CASTLING_ROW][Position.D] = Position.EMPTY;
                    break;
                        
                case Position.G:
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
                    Pos[Position.BLACK_CASTLING_ROW][Position.A] = Position.BLACK_ROOK;
                    Pos[Position.BLACK_CASTLING_ROW][Position.D] = Position.EMPTY;
                    break;
                        
                case Position.G:
                    Pos[Position.BLACK_CASTLING_ROW][Position.H] = Position.BLACK_ROOK;
                    Pos[Position.BLACK_CASTLING_ROW][Position.F] = Position.EMPTY;
                    break;
            }
        }        
                      
        if((Figure == Position.WHITE_PAWN) && (col != col_n) && (Figure_p == Position.EMPTY))               // Reconstruct en passant
        {
            Pos[row][col_n] =  Position.BLACK_PAWN;
        }
        
        if((Figure == Position.BLACK_PAWN) && (col != col_n) && (Figure_p == Position.EMPTY))               // Reconstruct en passant
        {
            Pos[row][col_n] =  Position.WHITE_PAWN;
        }
        
        Position.SetWhiteLongCastling(                      Pos, MovePath[m][WHITE_LONG_CASTLING]);
        Position.SetWhiteShortCastling(                     Pos, MovePath[m][WHITE_SHORT_CASTLING]);
        Position.SetBlackLongCastling(                      Pos, MovePath[m][BLACK_LONG_CASTLING]);
        Position.SetBlackShortCastling(                     Pos, MovePath[m][BLACK_SHORT_CASTLING]);
        Position.SetColumnPawnMovedTwoRows(                 Pos, MovePath[m][COLUMN_PAWN_MOVED_TWO_STEPS]);
        Position.SetNumberOfRepetitivePositions(            Pos, MovePath[m][REPETITIVE_POSITION_COUNTER]);   
        Position.SetNumberOfMovesWithNoPawnMoveOrCapture(   Pos, MovePath[m][FIFTY_MOVE_COUNTER]);        
        
        for(e = 0; e < ENTRIES_MOVE_LIST; e++)              // Delete move
        {
            MovePath[m][e] = 0;
        }
        
        Position.SwitchMoveColor(Pos);
    }    
}