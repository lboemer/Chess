import java.util.*;

public class Move
{
    // Move list size 
    public static final int MAX_NUMBER_MOVE_LIST            = 250;              // Article say there are a maximum of 218 moves possible
    public static final int ENTRIES_MOVE_LIST               = 18;
    
    // Entries move list
    public static final int FIGURE                          = 0;
    public static final int ROW                             = 1;
    public static final int COL                             = 2;
    public static final int FIGURE_P                        = 3;
    public static final int FIGURE_N                        = 4;
    public static final int ROW_N                           = 5;
    public static final int COL_N                           = 6;
    public static final int EN_PASSANT_STATUS               = 7;
    public static final int WHITE_LONG_CASTLING             = 8;
    public static final int WHITE_SHORT_CASTLING            = 9;
    public static final int BLACK_LONG_CASTLING             = 10;
    public static final int BLACK_SHORT_CASTLING            = 11;
    public static final int COLUMN_PAWN_MOVED_TWO_STEPS     = 12;
    public static final int CHECK_STATUS                    = 13;    
    public static final int POSITION_STATUS                 = 14;    
    public static final int RATING                          = 15;
    public static final int REPETITIVE_POSITION_COUNTER     = 16; 
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

    public static int MoveListIteration                         = 0;
    
    public static final int ALL                                 = 0;
    public static final int START                               = 1;
    public static final int STOP                                = 2;    
    
    public static final int DRAW_REPETIVE_POSITIONS             = 3; 
    public static final int PLYS_PER_REPETITIVE_MOVE            = 4;     
    public static final int PLY_PER_MOVE                        = 2;

    public static void EmptyList(int[][] MoveList)
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
    
    // Write move from MovePath[][] as string into MoveTable[]
    public static void Display(int[][] MovePath, int BoundryType, int BoundryValue, String[] MoveTable, int ListFormat, int RatingFormat)    
    {                                                                           
        int l;
        int begin   = 0;
        int end     = 0;
        int t;
        String str;
        int MoveStart = 0;
 
        for(t = 0; t  < MoveTable.length; t++)
        {
            MoveTable[t] = "0";
        }
        
        switch(BoundryType)
        {
            case ALL: 
                begin       = 0;
                end         = LastIndex(MovePath) + 1;
                MoveStart   = BoundryValue;
                break;
            
            case START:
                begin       = BoundryValue - 1;
                end         = LastIndex(MovePath);
                MoveStart   = 0;
                break;
                
            case STOP:
                begin       = 0;
                end         = BoundryValue;
                MoveStart   = 0;
                break;       
        }        

        for(l = begin; l < end; l++)     
        {
            if(Position.GetFigureColor(MovePath[0][FIGURE]) == Position.WHITE_FIGURE)
            {                                                                   // White move is first move
                switch(ListFormat)
                {
                    case LIST:
                        t = l;
                        MoveTable[t] = String.format("%2d. ", l + 1);
                        break;
                        
                    case LINE:
                        t = 0;
                        if((l % 2) == 0)
                        {
                            if (l  == 0)
                            {
                                MoveTable[t] = String.format("%2d. ", (MoveStart + 1)/2 + 1);
                            }
                            else
                            {
                                str = String.format("%2d. ", (MoveStart + 1) / 2 + 1 + (l / 2));
                                MoveTable[t] = MoveTable[t].concat(str);
                            }
                        }
                        break;
                        
                    case TABLE:
                        t = l / 2;
                        if((l % 2) == 0)
                        {
                            MoveTable[t] = String.format("%2d. ", (l / 2) + 1);
                        }
                        break;
                }
            }
            else
            {                                                                   // Black is first move
                switch(ListFormat)
                {
                    case LIST:
                        t = l;
                        MoveTable[t] = String.format("%2d. ", l + 1);
                        break;
                        
                    case LINE:
                        t = 0;
                        if(l == 0)
                        {
                            MoveTable[t] = String.format("%2d.   ...   ", (MoveStart + 1) / 2);
                        }
                        if((l % 2) == 1)
                        {     
                            str = String.format("%2d. ", ((MoveStart + 1) / 2) + (l+1) / 2);
                            MoveTable[t] = MoveTable[t].concat(str);  
                        }
                        break;

                    case TABLE:
                        t = (l + 1) / 2;
                        
                        if(l == 0)
                        {
                            MoveTable[t] = String.format(" 1.   ...   ");
                        }

                        if((l % 2) == 1)
                        {     
                            MoveTable[t] = String.format("%2d. ", (l + 1) / 2 + 1); 
                        }
                        break;
                }                    
            }

            if(((MovePath[l][FIGURE] == Position.WHITE_KING) || (MovePath[l][FIGURE] == Position.BLACK_KING)) && 
                (MovePath[l][COL] == Position.E) && 
                ((MovePath[l][COL_N] == Position.C) || (MovePath[l][COL_N] == Position.G)))
            {
                MoveTable[t] = ((MovePath[l][COL_N]) == Position.C) ? MoveTable[t].concat(" 0-0-0  ") : MoveTable[t].concat("   0-0  ");
            }                
            else
            {            
                DisplayFigure(              MoveTable, t, MovePath[l][FIGURE]);
                DisplayCol(                 MoveTable, t, MovePath[l][COL]);
                DisplayRow(                 MoveTable, t, MovePath[l][ROW]);
                DisplayType(                MoveTable, t, MovePath[l][FIGURE], MovePath[l][FIGURE_P], MovePath[l][COL], MovePath[l][COL_N]);
                DisplayCol(                 MoveTable, t, MovePath[l][COL_N]);
                DisplayRow(                 MoveTable, t, MovePath[l][ROW_N]);  
                if((MovePath[l][FIGURE] == Position.WHITE_PAWN) && (MovePath[l][ROW_N] == Position.WHITE_PAWN_PROMOTION_ROW) ||
                    (MovePath[l][FIGURE] == Position.BLACK_PAWN) && (MovePath[l][ROW_N] == Position.BLACK_PAWN_PROMOTION_ROW))
                {
                    DisplayFigure(          MoveTable, t, MovePath[l][FIGURE_N]);
                }
                DisplayEnPassantStatus(     MoveTable, t, MovePath[l][EN_PASSANT_STATUS]);                 // .... replace with pawn query....     
                DisplayCheckStatus(         MoveTable, t, MovePath[l][CHECK_STATUS], MovePath[l][POSITION_STATUS]);              
                DisplayPositionStatus(      MoveTable, t, MovePath[l][POSITION_STATUS]);  
                DisplayRating(              MoveTable, t, MovePath, l, MovePath[l][RATING], RatingFormat);
            }
        } 
        DisplayConsole(MoveTable);
    }
    
    public static void DisplayConsole(String[] MoveTable)                       //Display MoveTable[] wich is a list of strings to console
    {
        int t;  
     
        for(t = 0; !"0".equals(MoveTable[t]); t++)
        {      
            System.out.println(MoveTable[t]);
        }
    }

    public static void DisplayIntoHistoryWindow(String[] MoveTable)                       //Display MoveTable[] wich is a list of strings to console
    {
        int t;  
        String testString = "hello\n";
        int len;
        
        Chess.moveHistoryTextArea.setText("");
        for(t = 0; !"0".equals(MoveTable[t]); t++)
        {      
            Chess.appendHistory(Chess.moveHistoryTextArea, MoveTable[t]);
        }
    }    
      
    public static void DisplayIntoAnalysisWindow(String[] MoveTable)
    {
        int t;  
      
        for(t = 0; !"0".equals(MoveTable[t]); t++)
        {      
            Chess.appendAnalysis(Chess.moveAnalysisTextArea, MoveTable[t]);
        }
    }   
      
    public static void DisplayFigure(String[] MoveTable, int t, int figure)
    {
        MoveTable[t] = MoveTable[t].concat(Piece.Pieces[figure].getNotation());
    }
        
    public static void DisplayCol(String[] MoveTable, int t, int col)
    {
        MoveTable[t] = MoveTable[t].concat(Position.colStr[col-1]); 
    }
    
    public static void DisplayRow(String[] MoveTable, int t, int row)
    {
        String str;
        str = String.format("%1d", row);
        MoveTable[t] = MoveTable[t].concat(str);                
    }
    
    public static void DisplayType(String[] MoveTable, int t, int Figure, int Figure_p, int col, int col_n)
    {      
        
        MoveTable[t] = ((Figure_p != Position.EMPTY) ||                                      // Took opponent figure away
            (((Figure_p == Position.EMPTY) && ((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN)) && (col != col_n)))) ?
                MoveTable[t].concat("x") : MoveTable[t].concat("-");
        
    }
        
    public static void DisplayEnPassantStatus(String[] MoveTable, int t, int EnPassant)
    {      
        if(EnPassant == Position.EN_PASSANT)             
        {
            MoveTable[t] = MoveTable[t].concat("e.p.");                
        }
    }
    
    public static void DisplayCheckStatus(String[] MoveTable, int t, int Check, int Mate)
    {      
        if(Check == Position.CHECK)             
        {
            MoveTable[t] = MoveTable[t].concat("+ ");                
        }
        else
        {
            if(Mate != Position.CHECKMATE)
            {
                MoveTable[t] = MoveTable[t].concat("  ");                
            }
        }
    }
    
    public static void DisplayPositionStatus(String[] MoveTable, int t, int PositionStatus)
    {      
        switch(PositionStatus)
        {
            case Position.NO_CONDITION:
                break;
                
            case Position.CHECKMATE:
                MoveTable[t] = MoveTable[t].concat("# ");                
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
        
    public static void DisplayRating(String[] MoveTable, int t, int[][] MoveList, int l, int Rating, int RatingFormat)
    {      
        float RatingFloat = (float) Rating / 100;
        
        //System.out.format(" Rating = %d RatingFloat = %.2f\n", Rating, RatingFloat);
        String str = String.format(" Rating = %.2f", RatingFloat);
        
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
 
    public static void Set(int[][] Move, int m, int[][] MovePath, int l) // Copies Move[m][] to MovePath[l][]
    {
        int i;
        
        for(i = 0; i < ENTRIES_MOVE_LIST; i++)
        {
            MovePath[l][i] = Move[m][i];
        }
       
        for(l++; l < MovePath.length; l++) // Sets remainder of MovePath to 0
        {
            for(i = 0; i < ENTRIES_MOVE_LIST; i++)
            {
                MovePath[l][i] = 0;
            }
        }
    }   
    
    public static void Clear(int[][] MovePath, int p)
    {
        int i;
        
        for(i = 0; i < ENTRIES_MOVE_LIST; i++)
        {
            MovePath[p][i] = 0;
        }
    }

    public static int LastIndex(int[][] MovePath)
    {
        int i;
          
        for(i = 0; MovePath[i][FIGURE] != Position.EMPTY; i++) // Move to end of list
        {
        }   // Total of i moves in move list  
        i--; 
        return(i);                                                              
    }

    public static int RepetitivePositions(int[][] MovePath)  
    {
        int RepetitivePositionCounter; 
        int p = LastIndex(MovePath);                                                                                                                                         

        for(RepetitivePositionCounter = 1; ((RepetitivePositionCounter < DRAW_REPETIVE_POSITIONS) &&
                                           ((RepetitivePositionCounter * PLYS_PER_REPETITIVE_MOVE) + 1 <= p)); RepetitivePositionCounter++)
        {
            if(!SamePosition(MovePath, p - ((RepetitivePositionCounter - 1) * PLYS_PER_REPETITIVE_MOVE)))
            {
                break;
            }
        }
        return(RepetitivePositionCounter);
    }
    
    public static boolean SamePosition(int[][] MovePath, int p)     
    {
        return((MovePath[p    ][FIGURE] == MovePath[p -     PLYS_PER_REPETITIVE_MOVE][FIGURE])  &&      // Same figure type moved to same field than two moves before
               (MovePath[p    ][COL_N]  == MovePath[p -     PLYS_PER_REPETITIVE_MOVE][COL_N])   &&      // Moved to same column than two moves before 
               (MovePath[p    ][ROW_N]  == MovePath[p -     PLYS_PER_REPETITIVE_MOVE][ROW_N])   &&      // Moved to same row than two moves before                  
               (MovePath[p - 1][FIGURE] == MovePath[p - 1 - PLYS_PER_REPETITIVE_MOVE][FIGURE])  &&      // Same figure type moved to same field than two moves before
               (MovePath[p - 1][COL_N]  == MovePath[p - 1 - PLYS_PER_REPETITIVE_MOVE][COL_N])   &&      // Moved to same column than two moves before 
               (MovePath[p - 1][ROW_N]  == MovePath[p - 1 - PLYS_PER_REPETITIVE_MOVE][ROW_N]));   
    }

    public static void CopyList(int[][] FromMoveList, int CopyStart, int[][] ToMoveList)
    {
        int l;
        int e;
        int r;
        
        EmptyList(ToMoveList);

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
    
        row = ((Position.GetMoveColor(Pos)) == Position.WHITE_MOVE) ? Position.WHITE_CASTLING_ROW : Position.BLACK_CASTLING_ROW;
        
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
        {                                                                       //Long Castling
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
        {                                                                       //Short Castling 
            if(CastlingFieldsCheckFree(Pos, Position.SHORT_CASTLING))
            {
                CastlingList[list++] = Position.SHORT_CASTLING;
            }
        }
        return(list);
    }
        
    public static boolean CastlingFieldsCheckFree(int[][] Pos, int CastlingType)
    {
        int NewKingColumn;
        int temp_figure;
        int row;
        int KingColumn              = Position.E;
        int KingColumnStep; 
        boolean CastlingPossible    = true;
        
        KingColumnStep = ((CastlingType) == Position.LONG_CASTLING) ? Position.COLUMN_DOWN : Position.COLUMN_UP; 
        row = ((Position.GetMoveColor(Pos)) == Position.WHITE_MOVE) ? Position.WHITE_CASTLING_ROW : Position.BLACK_CASTLING_ROW; 
        
        for(NewKingColumn = KingColumn; (Math.abs(NewKingColumn - KingColumn) <= Position.KING_CASTLING_STEPS) && CastlingPossible; NewKingColumn += KingColumnStep)
        {                                    
            if(Chess.DebugLevel > Settings.MEDIUM)
            {
                System.out.println("Examining if Field[" + NewKingColumn + "][" + row + " is under Check");
            }
                        
            temp_figure                 = Pos[row][KingColumn];                 // !!!!! Save to temp_figure first to cover the case that NewKingColumn == King Column
            Pos[row][KingColumn]        = Position.EMPTY;            
            Pos[row][NewKingColumn]     = temp_figure;                          // WHITE or BLACK KING;

            CastlingPossible            = !Position.Check(Pos, Position.RECEIVING_CHECK);
     
            Pos[row][NewKingColumn]     = Position.EMPTY;                       // !!!!! Set Pos[NewKingColumn][row] to EMPTY first to cover the case that NewKingColumn == King Column
            Pos[row][KingColumn]        = temp_figure;             
        }
        return(CastlingPossible);
    }
    
    public static void Make(int[][] Pos, int row, int col, int Figure_n, int row_n, int col_n, int[][] MovePath, int AddMoveToHistory)
    {                                                                           
        // Makes the move by updating the position in Pos[][] 
        // If called by user makes move or by computer makes move, move is added to move path
        int m;                                                              
        int Figure;
        int Figure_p;
        int EnPassantStatus;
        
        int WhiteLongCastlingPreviousPosition           = 0;
        int WhiteShortCastlingPreviousPosition          = 0;
        int BlackLongCastlingPreviousPosition           = 0;
        int BlackShortCastlingPreviousPosition          = 0;
        int ColumnPawnMovedTwoStepsPreviousPosition     = 0;
        int RepetitivePositionCounterPreviousPosition   = 0;
        int FifyMoveCounterPreviousPosition             = 0;
       
        if(AddMoveToHistory == ADD_TO_MOVE_HISTORY)                             
        {                                                                      
            // Save postion status information from the previous position before move is made to local variables ...
            // ... which is used to reconstruct the previous position for backward moves             
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
                case Position.C:    // Long white castling, move rook
                    Pos[Position.WHITE_CASTLING_ROW][Position.A] = Position.EMPTY;
                    Pos[Position.WHITE_CASTLING_ROW][Position.D] = Position.WHITE_ROOK;
                    break;
                        
                case Position.G:    // Short white castling, move rook
                    Pos[Position.WHITE_CASTLING_ROW][Position.H] = Position.EMPTY;              
                    Pos[Position.WHITE_CASTLING_ROW][Position.F] = Position.WHITE_ROOK;
                    break;
            }
        }

        if((Figure == Position.BLACK_KING) && (col == Position.E))
        {
            switch(col_n)
            {
                case Position.C:    // Long black castling, move rook
                    Pos[Position.BLACK_CASTLING_ROW][Position.A] = Position.EMPTY;
                    Pos[Position.BLACK_CASTLING_ROW][Position.D] = Position.BLACK_ROOK;
                    break;
                        
                case Position.G:    // Short white castling, move rook
                    Pos[Position.BLACK_CASTLING_ROW][Position.H] = Position.EMPTY;
                    Pos[Position.BLACK_CASTLING_ROW][Position.F] = Position.BLACK_ROOK;
                    break;
            }
        }               
                                                
        if((Figure == Position.WHITE_ROOK) && (col == Position.A) && (row == Position.WHITE_CASTLING_ROW))        
        {                                                                       
            // WRa1 moved
            Position.SetWhiteLongCastling(Pos, Position.WRA1_OR_WKE1_DID_MOVE);
        }        
                    
        if((Figure == Position.WHITE_ROOK) && (col == Position.H) && (row == Position.WHITE_CASTLING_ROW))          
        {                                                                       
            // WRa8 moved
            Position.SetWhiteShortCastling(Pos, Position.WRH1_OR_WKE1_DID_MOVE);
        }
                
        if((Figure == Position.BLACK_ROOK) && (col == Position.A) && (row == Position.BLACK_CASTLING_ROW))          
        {                                                                       
            // BRa8 moved
            Position.SetBlackLongCastling(Pos, Position.BRA8_OR_BKE8_DID_MOVE);
        }        
                
        if((Figure == Position.BLACK_ROOK) && (col == Position.H) && (row == Position.BLACK_CASTLING_ROW))         
        {                                                                       
            // BRa8 moved
            Position.SetBlackShortCastling(Pos, Position.BRH8_OR_BKE8_DID_MOVE);
        }  
                
        if((Figure == Position.WHITE_KING) && (col == Position.E) && (row == Position.WHITE_CASTLING_ROW))     
        {                                                                       
            // WKe1 moved, White short and long castling not possible anymore
            Position.SetWhiteLongCastling(Pos, Position.WRA1_OR_WKE1_DID_MOVE);
            Position.SetWhiteShortCastling(Pos, Position.WRA1_OR_WKE1_DID_MOVE);
        }
                                        
        if((Figure == Position.BLACK_KING) && (col == Position.E) && (row == Position.BLACK_CASTLING_ROW))        
        {                                                                       
            // BKe8 moved, Black short and long castling not possible anymore
            Position.SetBlackLongCastling(Pos, Position.BRA8_OR_BKE8_DID_MOVE);
            Position.SetBlackShortCastling(Pos, Position.BRA8_OR_BKE8_DID_MOVE); 
        }    
        
        if(((Figure == Position.WHITE_PAWN) && (row == 2) &&  (row_n == 4)) ||
            ((Figure == Position.BLACK_PAWN) && (row == 7) &&  (row_n == 5)))
        {
            // Save new column where pawn moved two steps in ColumnPawnMovedTwoRows  
            Position.SetColumnPawnMovedTwoRows(Pos, col);                                
        }
        else
        {
            Position.SetColumnPawnMovedTwoRows(Pos, 0);   
        } 

        if((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN) || (Figure_p != Position.EMPTY))   
        {                                                                       
            // Pawn move or capture
            Position.SetNumberOfMovesWithNoPawnMoveOrCapture(Pos,0);
        }
        else
        {
            Position.IncrementNumberOfMovesWithNoPawnMoveOrCapture(Pos);           
        }
        
        if(AddMoveToHistory == ADD_TO_MOVE_HISTORY)
        {
            m = Chess.Ply;
            MovePath[m][Move.FIGURE]                    = Figure;                 
            MovePath[m][Move.ROW]                       = row;
            MovePath[m][Move.COL]                       = col;
            MovePath[m][Move.FIGURE_P]                  = Figure_p; 
            MovePath[m][Move.FIGURE_N]                  = Figure_n;                 
            MovePath[m][Move.ROW_N]                     = row_n;
            MovePath[m][Move.COL_N]                     = col_n;   
            MovePath[m][EN_PASSANT_STATUS]              = EnPassantStatus;                                  
            MovePath[m][WHITE_LONG_CASTLING]            = WhiteLongCastlingPreviousPosition;                                 
            MovePath[m][WHITE_SHORT_CASTLING]           = WhiteShortCastlingPreviousPosition;                                    
            MovePath[m][BLACK_LONG_CASTLING]            = BlackLongCastlingPreviousPosition;                                    
            MovePath[m][BLACK_SHORT_CASTLING]           = BlackShortCastlingPreviousPosition;                                      
            MovePath[m][COLUMN_PAWN_MOVED_TWO_STEPS]    = ColumnPawnMovedTwoStepsPreviousPosition;                                    
            MovePath[m][POSITION_STATUS]                = Position.GetStatus(Pos, MovePath);       
            MovePath[m][CHECK_STATUS]                   = Position.GetCheckStatus(Pos, MovePath[m][POSITION_STATUS]);
            MovePath[m][RATING]                         = Rating.Rating(Pos, MovePath[m][POSITION_STATUS]);  
            MovePath[m][REPETITIVE_POSITION_COUNTER]    = RepetitivePositionCounterPreviousPosition;
            MovePath[m][FIFTY_MOVE_COUNTER]             = FifyMoveCounterPreviousPosition;
            Chess.Ply++;
        }
        Position.SetNumberOfRepetitivePositions(Pos, RepetitivePositions(MovePath));  
    }
        
    public static boolean UserSuccessful(int[][] Pos, int row, int col, int Figure_n, int row_n, int col_n, int[][] MovePath)       
    {        
        int[][] UserMovesPosition            = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];
        int m;
        boolean ReturnOnFirstMovePossible = false;

        EmptyList(UserMovesPosition);       
        Position.GenerateMoveList(Pos, UserMovesPosition, MovePath, ReturnOnFirstMovePossible);
        //Move.DisplayMoveList(UserMovesPosition, Move.ALL, 0, Move.LIST, Move.SHOW_NO_RATING); 
        for(m = 0; UserMovesPosition[m][Move.FIGURE] != Position.EMPTY; m++)
        {
            if((UserMovesPosition[m][ROW]       == row)         &&
               (UserMovesPosition[m][COL]       == col)         &&  
               (UserMovesPosition[m][FIGURE_N]  == Figure_n)    &&
               (UserMovesPosition[m][ROW_N]     == row_n)       &&          
               (UserMovesPosition[m][COL_N]     == col_n))
            {
                Move.Make(Pos, row, col, Figure_n, row_n, col_n, MovePath, Move.ADD_TO_MOVE_HISTORY);
                
                // Display MoveHistory into a Window
            //    Move.DisplayIntoWindow(MoveTable);
                
                return true;
            }   
        }
        System.out.println("Incorrect user Move.");
        return false;
    }  
    
    public static void SortList(int[][] MoveList)
    {
        int m;
        int n;
        int MaxRating;
                
        if(Position.GetFigureColor(MoveList[0][FIGURE]) == Position.WHITE_FIGURE)
        {
            for(m = 0; MoveList[m][FIGURE] != Position.EMPTY; m++)              
            {
                // Go to next available Move
                MaxRating = MoveList[m][RATING];
                for(n = m + 1; MoveList[n][FIGURE] != Position.EMPTY; n++)
                {           
                    if(MoveList[n][RATING] > MaxRating)
                    {
                        MaxRating = MoveList[n][RATING];
                        Swap(MoveList, m, n);
                    }
                }
            }    
        }
        else
        {
            for(m = 0; MoveList[m][FIGURE] != Position.EMPTY; m++)              
            {
                // Go to next available Move
                MaxRating = MoveList[m][RATING];
                for(n = m + 1; MoveList[n][FIGURE] != Position.EMPTY; n++)
                {          
                    if(MoveList[n][RATING] < MaxRating)
                    {
                        MaxRating = MoveList[n][RATING];
                        Swap(MoveList, m, n);
                    }
                }
            }                
        }
    }        
    
    public static void Swap(int[][] MoveList, int m, int n)
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
    
    public static void Revert(int[][] Pos, int[][] MovePath)
    {
        int m = LastIndex(MovePath);
        int e;
        int Figure;
        int row;
        int col;
        int Figure_p;
        int row_n;
        int col_n;

        if(m == -1)    // No move 
        {
            return;
        }  
        
        Figure              = MovePath[m][Move.FIGURE];
        row                 = MovePath[m][Move.ROW];
        col                 = MovePath[m][Move.COL];
        Figure_p            = MovePath[m][Move.FIGURE_P];
        row_n               = MovePath[m][Move.ROW_N];
        col_n               = MovePath[m][Move.COL_N];

        
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
                      
        if((Figure == Position.WHITE_PAWN) && (col != col_n) && (Figure_p == Position.EMPTY))              
        {                                                                       // Reconstruct en passant
            Pos[row][col_n] =  Position.BLACK_PAWN;
        }
        
        if((Figure == Position.BLACK_PAWN) && (col != col_n) && (Figure_p == Position.EMPTY))               
        {                                                                       // Reconstruct en passant
            Pos[row][col_n] =  Position.WHITE_PAWN;
        }
        
        Position.SetWhiteLongCastling(                      Pos, MovePath[m][WHITE_LONG_CASTLING]);
        Position.SetWhiteShortCastling(                     Pos, MovePath[m][WHITE_SHORT_CASTLING]);
        Position.SetBlackLongCastling(                      Pos, MovePath[m][BLACK_LONG_CASTLING]);
        Position.SetBlackShortCastling(                     Pos, MovePath[m][BLACK_SHORT_CASTLING]);
        Position.SetColumnPawnMovedTwoRows(                 Pos, MovePath[m][COLUMN_PAWN_MOVED_TWO_STEPS]);
        Position.SetNumberOfRepetitivePositions(            Pos, MovePath[m][REPETITIVE_POSITION_COUNTER]);   
        Position.SetNumberOfMovesWithNoPawnMoveOrCapture(   Pos, MovePath[m][FIFTY_MOVE_COUNTER]);        
        
        for(e = 0; e < ENTRIES_MOVE_LIST; e++)                                  // Delete move
        {
            MovePath[m][e] = 0;
        }       
        Position.SwitchMoveColor(Pos);
    }    
}
