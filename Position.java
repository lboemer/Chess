import java.lang.*;                                                             // For abs, Signum
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Position
{
    public static final int EMPTY                               = 0;
    
    /* Use the following values to represent an int[][] Position, and
     *  also as indices in Piece.Pieces */
    public static final int WHITE_KING                          = 1;
    public static final int WHITE_QUEEN                         = 2;
    public static final int WHITE_ROOK                          = 3;
    public static final int WHITE_KNIGHT                        = 4;
    public static final int WHITE_BISHOP                        = 5;
    public static final int WHITE_PAWN                          = 6;
    
    public static final int BLACK_KING                          = 7;
    public static final int BLACK_QUEEN                         = 8;
    public static final int BLACK_ROOK                          = 9;
    public static final int BLACK_KNIGHT                        = 10;
    public static final int BLACK_BISHOP                        = 11;
    public static final int BLACK_PAWN                          = 12;
    
    public static final int NUM_PIECES                          = 13;
    
    public static final int WHITE                               = 1;
    public static final int BLACK                               = -1;
    
    public static final int MIN_WQ = 0, MAX_WQ                  = 9;
    public static final int MIN_BQ = 0, MAX_BQ                  = 9;
    public static final int MIN_WR = 0, MAX_WR                  = 9;
    public static final int MIN_BR = 0, MAX_BR                  = 9;
    public static final int MIN_WN = 0, MAX_WN                  = 9;
    public static final int MIN_BN = 0, MAX_BN                  = 9;
    public static final int MIN_WB = 0, MAX_WB                  = 9;
    public static final int MIN_BB = 0, MAX_BB                  = 9;
    public static final int MIN_WP = 0, MAX_WP                  = 8;
    public static final int MIN_BP = 0, MAX_BP                  = 8;
    public static final int MIN_WK = 1, MAX_WK                  = 1;
    public static final int MIN_BK = 1, MAX_BK                  = 1;         

    public static int[] WhiteFigure                             = { WHITE_KING, 
                                                                    WHITE_QUEEN, 
                                                                    WHITE_ROOK, 
                                                                    WHITE_KNIGHT, 
                                                                    WHITE_BISHOP, 
                                                                    WHITE_PAWN};
                                                            
    public static int[] BlackFigure                             = { BLACK_KING, 
                                                                    BLACK_QUEEN, 
                                                                    BLACK_ROOK, 
                                                                    BLACK_KNIGHT, 
                                                                    BLACK_BISHOP, 
                                                                    BLACK_PAWN};
            
    public static int[] WhitePromotionFigure                    = { WHITE_QUEEN, 
                                                                    WHITE_ROOK, 
                                                                    WHITE_KNIGHT, 
                                                                    WHITE_BISHOP};
                                                            
    public static int[] BlackPromotionFigure                    = { BLACK_QUEEN, 
                                                                    BLACK_ROOK, 
                                                                    BLACK_KNIGHT, 
                                                                    BLACK_BISHOP}; 
                                                                    
    public static final int A                                   = 1;
    public static final int B                                   = 2;
    public static final int C                                   = 3;
    public static final int D                                   = 4;
    public static final int E                                   = 5;
    public static final int F                                   = 6;
    public static final int G                                   = 7;
    public static final int H                                   = 8;
    
    public static final int ROWS                                = 8;
    public static final int COLS                                = 8;
    
    public static final int COLUMN_UP                           = 1;
    public static final int COLUMN_DOWN                         = -1;
    
    public static final int WHITE_CASTLING_ROW                  = 1;
    public static final int BLACK_CASTLING_ROW                  = 8;
    public static final int KING_CASTLING_STEPS                 = 2;
    
    public static final int LONG_CASTLING                       = 0;
    public static final int SHORT_CASTLING                      = 1;
    public static int[] CASTLINGS                               = {LONG_CASTLING, 
                                                                   SHORT_CASTLING};
    
    public static final int COLUMN_MOVE_COLOR                   = 0;
    public static final int ROW_MOVE_COLOR                      = 0;

    public static final int COLUMN_WHITE_LONG_CASTLING          = 0;
    public static final int ROW_WHITE_LONG_CASTLING             = 1;
    
    public static final int COLUMN_WHITE_SHORT_CASTLING         = 0;
    public static final int ROW_WHITE_SHORT_CASTLING            = 2;
       
    public static final int COLUMN_BLACK_LONG_CASTLING          = 0;
    public static final int ROW_BLACK_LONG_CASTLING             = 3;
    
    public static final int COLUMN_BLACK_SHORT_CASTLING         = 0;
    public static final int ROW_BLACK_SHORT_CASTLING            = 4;
    
    public static final int COLUMN_EN_PASSANT                   = 0;
    public static final int ROW_EN_PASSANT                      = 5;
    
    public static final int COLUMN_FIFTY_MOVE                   = 0;
    public static final int ROW_FIFTY_MOVE                      = 6;    
      
    public static final int COLUMN_REPETITIVE_POSITIONS         = 0;
    public static final int ROW_REPETITIVE_POSITIONS            = 7;

    public static final int WHITE_MOVE                          = 1;
    public static final int BLACK_MOVE                          = -1;
    
    public static final int WHITE_FIGURE                        = 1;
    public static final int BLACK_FIGURE                        = -1;    
    
    public static final int WRA1_AND_WKE1_DID_NOT_MOVE          = 0;            // White Long   Castling        possible, WRa1 and WKe1 did not move before
    public static final int WRA1_OR_WKE1_DID_MOVE               = 1;            // White Long   Castling not    possible, WRa1 or WKe1  did     move before
    public static final int WRH1_AND_WKE1_DID_NOT_MOVE          = 0;            // White Short  Castling        possible, WRh1 and WKe1 did not move before
    public static final int WRH1_OR_WKE1_DID_MOVE               = 1;            // White Short  Castling not    possible, WRh8 or WKe1  did     move before
    public static final int BRA8_AND_BKE8_DID_NOT_MOVE          = 0;            // Black Long   Castling        possible, WRh8 and BKe8 did not move before
    public static final int BRA8_OR_BKE8_DID_MOVE               = 1;            // Black Long   Castling not    possible, WRh8 or BKe8  did     move before
    public static final int BRH8_AND_BKE8_DID_NOT_MOVE          = 0;            // Black Short  Castling        possible, WRh8 and BKe8 did not move before
    public static final int BRH8_OR_BKE8_DID_MOVE               = 1;            // Black Short  Casling  not    possible, WRh8 or BKe8  did     move before

    public static final int WHITE_PAWN_PROMOTION_ROW            = 8;  
    public static final int BLACK_PAWN_PROMOTION_ROW            = 1;  
        
    public static final int WHITE_PAWN_INITIAL_ROW              = 2;  
    public static final int BLACK_PAWN_INITIAL_ROW              = 7;  
    
    // Begin en passant status
    public static final int NO_EN_PASSANT                       = 0;
    public static final int EN_PASSANT                          = 1;    
    
    // Begin check status
    public static final int NO_CHECK                            = 0;
    public static final int CHECK                               = 1;   
    
    // Begin position status
    public static final int NO_CONDITION                        = 0;
    public static final int CHECKMATE                           = 1;
    public static final int STALEMATE                           = 2;
    public static final int INSUFFICIENT_MATERIAL               = 3;
    public static final int THREE_POSITION_REPETITION           = 4;    
    public static final int FIFTY_MOVE                          = 5;
    
    public static final int REVERSABLE_MOVES_LIMIT              = 50;    
    
    // Begin position names
    public static final int NEW_POSITION                        = 1;
    public static final int PAWN_POSITION                       = 2;    
    public static final int EN_PASSANT_POSITION                 = 3;
    public static final int PROMOTION_POSITION                  = 4;
    public static final int CASTLING_POSITION                   = 5;
    public static final int INSUFFICIENT_MATERIAL_POSITION      = 6;
    public static final int ONE_MOVE_MATE_POSITION              = 7;
    public static final int TWO_MOVE_MATE_POSITION              = 8;
    public static final int THREE_MOVE_MATE_POSITION            = 9;    
  
    // Parameter for Check()
    public static final int GIVING_CHECK                        = 0;
    public static final int RECEIVING_CHECK                     = 1;    

    public static int       BeginPosition;   
    
    public static String ToString(int[][] Pos)
    {
        String PosString = "";
        int row, col;
        for (row = 1; row <= ROWS; row++) {
            for (col = 1; col <= COLS; col++) {
                PosString += " " + Integer.toString(Pos[row][col]);
            }
        }
        return PosString;
    }
    
    public static void SetFromFile(int[][] Pos, String filename)
    {
        int row, col;
        File file = new File(filename);
        
        Clear(Pos);

        try {
            Scanner sc = new Scanner(file);
            for (row = ROWS; row > 0; row--)
            {
                sc.next(); // skip row number
                for (col = 1; col <= COLS; col++)
                {
                    Pos[row][col] = Piece.ConsoleNotationToType(sc.next());
                } 
            }
            sc.nextLine(); // skip to end of row
            sc.nextLine(); // skip column row
            
            SetMoveColor(Pos, sc.next().equals("WHITE_MOVE") ? WHITE_MOVE : BLACK_MOVE);
            SetColumnPawnMovedTwoRows(Pos, sc.nextInt());
            
            SetWhiteLongCastling(Pos, sc.next().equals("WRA1_AND_WKE1_DID_NOT_MOVE") ?
                WRA1_AND_WKE1_DID_NOT_MOVE : WRA1_OR_WKE1_DID_MOVE );
                
            SetWhiteShortCastling(Pos, sc.next().equals("WRH1_AND_WKE1_DID_NOT_MOVE") ? 
                WRH1_AND_WKE1_DID_NOT_MOVE : WRH1_OR_WKE1_DID_MOVE);
                
            SetBlackLongCastling(Pos, sc.next().equals("BRA8_AND_BKE8_DID_NOT_MOVE") ? 
                BRA8_AND_BKE8_DID_NOT_MOVE : BRA8_OR_BKE8_DID_MOVE);   
                
            SetBlackShortCastling(Pos, sc.next().equals("BRH8_AND_BKE8_DID_NOT_MOVE") ? 
                BRH8_AND_BKE8_DID_NOT_MOVE : BRH8_OR_BKE8_DID_MOVE);
            
        sc.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File " + filename + " not found");
        }
    }
    
    public static void Clear(int[][] Pos)
    {
        int row;
        int col;
      
        for(row = 0; row <= ROWS; row++)                                       // Loop over total ROWS + 1
        {   
            for(col = 0; col <= COLS; col++)                                   // Loop over total COLS + 1 
            {
                Pos[row][col] = EMPTY;
            }
        }    
    }
    
    public static void Copy(int[][] Pos, int[][] PosStore)             // Copies Pos to PosStore
    {
        int row;
        int col;
        
        for(row = 0; row <= ROWS; row++)                                    // Loop over total ROWS + 1
        {   
            for(col = 0; col <= COLS; col++)                                // Loop over total COLS + 1 
            {
                PosStore[row][col] = Pos[row][col];
            }
        }
    }   
    
    public static int[] CountPieces(int[][] Pos) {
        int counts[] = new int[NUM_PIECES];
        int row, col;
        
        for(row = 1; row <= ROWS; row++)
        {
            for(col = 1; col <= COLS; col++)           
            {
                counts[Piece.Pieces[Pos[row][col]].getType()]++;
            }
        }
        return counts;
    }
    
    public static boolean Validate(int[][] Pos)
    {
        int i;
        int max_allowed;
        int min_allowed;
        boolean valid = true;
        
        int[] counts = CountPieces(Pos);
        
        for(i = 0; i < counts.length; i++)
        {
            max_allowed = Piece.Pieces[i].getMaxNum();
            min_allowed = Piece.Pieces[i].getMinNum();
            if ( counts[i] < min_allowed || counts[i] > max_allowed )
            {
                System.out.println("Nonvalid position: Number of " + Piece.Pieces[i].getConsoleNotation() + " is " + counts[i] + " and is not in the allowed range of " + min_allowed + " to " + max_allowed);
                valid = false;
            }
            
        }
        
        return(valid);
    }   
    
    public static void Display(int[][] Pos)
    {
        int row;
        int col;
        int Type;

        System.out.print ("\n");
        for(row = ROWS; row > 0; row--)
        {
            System.out.print(row + " ");
            for(col = 1; col <= COLS; col++)
            {
                System.out.print(" " + Piece.Pieces[Pos[row][col]].getConsoleNotation() + " ");
            }
            System.out.print ("\n");
        }

        System.out.print("   ");
        for(col = 1; col <= COLS; col++)
        {
            switch(col)
            {
                case 1:
                    System.out.print("a   "); 
                    break;
                case 2:
                    System.out.print("b   "); 
                    break;                   
                case 3:
                    System.out.print("c   "); 
                    break;
                case 4:
                    System.out.print("d   "); 
                    break;                     
                case 5:
                    System.out.print("e   "); 
                    break;
                case 6:
                    System.out.print("f   "); 
                    break;                   
                case 7:
                    System.out.print("g   "); 
                    break;
                case 8:
                    System.out.println("h   "); 
                    break;             
            }
        }
        
        if(Chess.ShowStatus > Settings.HIGH)
        { 
            if(GetColumnPawnMovedTwoRows(Pos) > 0)
            {
                System.out.println ("Pawn moved two steps in column " + GetColumnPawnMovedTwoRows(Pos));
            }
            if(GetWhiteLongCastling(Pos)  == 0)
            {
                System.out.println ("White Long Castling possible since WRa1 and WKe1 have not moved yet");
            }
            if(GetWhiteShortCastling(Pos) == 0)
            {
                System.out.println ("White Short Castling possible since WRa8 and WKe1 have not moved yet");
            }
            if(GetBlackLongCastling(Pos)  == 0)
            {
                System.out.println ("Black Long Castling possible since BRa8 and BKe8 have not moved yet");
            }
            if(GetBlackShortCastling(Pos)  == 0)
            {
                System.out.println ("Black Short Castling possible since BRa8 and BKe8 have not moved yet");
            }
        }
        System.out.println();
        ShowNumberOfRepetitivePositions(Pos);          
        ShowFiftyMoveCounter(Pos);
        Rating.ShowPositionType(Rating.PositionType(Pos));
        Rating.ShowGamePhase(Rating.GamePhase(Pos));
    }
    
    public static void ShowNumberOfRepetitivePositions(int[][] Pos)
    {
        System.out.println("RepetitivePositionCounter: " + GetNumberOfRepetitivePositions(Pos));
    }        
    
    public static void ShowFiftyMoveCounter(int[][] Pos)
    {
        System.out.println("FiftyMoveCounter: " + GetNumberOfMovesWithNoPawnMoveOrCapture(Pos));
    }
    
    public static void SetMoveColor(int[][] Pos, int color)
    {
        Pos[ROW_MOVE_COLOR][COLUMN_MOVE_COLOR] = color;       
    }
    
    public static int GetMoveColor(int[][]Pos)
    {
        return Pos[Position.ROW_MOVE_COLOR][Position.COLUMN_MOVE_COLOR];        
    } 
        
    public static void SwitchMoveColor(int Pos[][])
    {
        SetMoveColor(Pos, GetMoveColor(Pos) == WHITE_MOVE ? BLACK_MOVE : WHITE_MOVE);
    }
    
    public static void DisplayMoveColor(int Pos[][])
    {
        switch(GetMoveColor(Pos))
        {
              case WHITE_MOVE:
                System.out.println("White");
                break;
                
              case BLACK_MOVE:
                System.out.println("Black");              
                break;
        }
    }
    
    public static void SetWhiteLongCastling(int[][]Pos, int set)
    {
        Pos[ROW_WHITE_LONG_CASTLING][COLUMN_WHITE_LONG_CASTLING] = set;
    }
        
    public static int GetWhiteLongCastling(int[][]Pos)
    {
        return(Pos[ROW_WHITE_LONG_CASTLING][COLUMN_WHITE_LONG_CASTLING]);
    }
    
    public static void SetWhiteShortCastling(int[][]Pos, int set)
    {
        Pos[ROW_WHITE_SHORT_CASTLING][COLUMN_WHITE_SHORT_CASTLING] = set;
    }   
        
    public static int GetWhiteShortCastling(int[][]Pos)
    {
        return(Pos[ROW_WHITE_SHORT_CASTLING][COLUMN_WHITE_SHORT_CASTLING]);
    }  
    
    public static void SetBlackLongCastling(int[][]Pos, int set)
    {
        Pos[ROW_BLACK_LONG_CASTLING][COLUMN_BLACK_LONG_CASTLING] = set;
    }
            
    public static int GetBlackLongCastling(int[][]Pos)
    {
        return(Pos[ROW_BLACK_LONG_CASTLING][COLUMN_BLACK_LONG_CASTLING]);
    }
    
    public static void SetBlackShortCastling(int[][]Pos, int set)
    {
        Pos[ROW_BLACK_SHORT_CASTLING][COLUMN_BLACK_SHORT_CASTLING] = set;
    }
    
    public static int GetBlackShortCastling(int[][]Pos)
    {
        return(Pos[ROW_BLACK_SHORT_CASTLING][COLUMN_BLACK_SHORT_CASTLING]);
    }
    
    public static void SetColumnPawnMovedTwoRows(int[][] Pos, int column)
    {
        Pos[ROW_EN_PASSANT][COLUMN_EN_PASSANT] = column;           
    } 
    
    public static int GetColumnPawnMovedTwoRows(int[][] Pos)
    {
        return Pos[ROW_EN_PASSANT][COLUMN_EN_PASSANT];        
    } 
    
    public static void SetNumberOfMovesWithNoPawnMoveOrCapture(int[][] Pos, int set)
    {
        Pos[ROW_FIFTY_MOVE][COLUMN_FIFTY_MOVE] = set;    
    } 
    
    public static void SetNumberOfRepetitivePositions(int[][] Pos, int set)
    {
        Pos[ROW_REPETITIVE_POSITIONS][COLUMN_REPETITIVE_POSITIONS] = set;    
    }       
    
    public static int GetNumberOfRepetitivePositions(int[][] Pos)
    {
        return Pos[ROW_REPETITIVE_POSITIONS][COLUMN_REPETITIVE_POSITIONS];  
    }     
    
    public static void IncrementNumberOfMovesWithNoPawnMoveOrCapture(int[][] Pos)
    {
        int set;
        Pos[ROW_FIFTY_MOVE][COLUMN_FIFTY_MOVE]++;      
    }        
    
    public static int GetNumberOfMovesWithNoPawnMoveOrCapture(int[][] Pos)
    {
        return Pos[ROW_FIFTY_MOVE][COLUMN_FIFTY_MOVE];        
    }    
    
    public static boolean Check(int[][] Pos, int Type)
    {
        int row;
        int col;
        int row_s;                                                              // Row step
        int col_s;                                                              // Column step       
        int row_K = 0;                                                          // Row of opponent king 
        int col_K = 0;                                                          // Column of opponent king

        boolean CanTakeKing = false;                                            // False initialization is required
       
        if(Type == RECEIVING_CHECK)
        {
            SwitchMoveColor(Pos);                                               // Switch to opponent move   
        }        
        
        loop:
        for(row_K = 1; row_K <= ROWS; row_K++)                                  // Find row and column of opponent king
        {
            for(col_K = 1; col_K <= COLS; col_K++)
            {
                if(OpponentKing(Pos, row_K, col_K))
                {
                    break loop;                                                 // Found row and column of opponent king
                }
            }
        }

        loop_over_own_figures:
        for(row = 1; row <= ROWS; row++)                                        // Go through all fields and find own figure
        {
            for(col = 1; col <= COLS; col++)
            {
                if(OwnFigure(Pos, row, col))                                    // Found own figure
                {
                    switch(Pos[row][col])
                    {
                        case WHITE_PAWN:
                        case BLACK_PAWN:
                            if(Pos[row][col] == WHITE_PAWN)
                            {
                                row_s = 1;
                            }
                            else
                            {
                                row_s = -1;
                            }
                            if(((row + row_s) == row_K) && (Math.abs(col - col_K) == 1))
                            {
                                CanTakeKing = true;                             // Pawn can capture king
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_ROOK:
                        case BLACK_ROOK:
                            if(RookCanCaptureKing(Pos, row, col, row_K, col_K))
                            {
                                CanTakeKing = true;                             // Rook can capture king
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_KNIGHT:
                        case BLACK_KNIGHT:
                            if(((Math.abs(col - col_K) == 2) && (Math.abs(row - row_K) == 1)) ||
                               ((Math.abs(col - col_K) == 1) && (Math.abs(row - row_K) == 2)))
                            {
                                CanTakeKing = true;                             // Knight can capture king
                                break loop_over_own_figures;
                            }
                            break;
              
                        case WHITE_BISHOP:
                        case BLACK_BISHOP:
                            if(BishopCanCaptureKing(Pos, row, col, row_K, col_K))
                            {
                                CanTakeKing = true;                             // Bishop can capture king  
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_QUEEN:
                        case BLACK_QUEEN:
                            if((BishopCanCaptureKing(Pos, row, col, row_K, col_K)) ||                           
                               (RookCanCaptureKing  (Pos, row, col, row_K, col_K)))
                            {                                                   // Queen moves are the sum of rook or bishop moves
                                CanTakeKing = true;                             // Queen can capture king 
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_KING:
                        case BLACK_KING:            // Avoids that King can move next to opponent king
                            if((Math.abs(col - col_K) <= 1) && (Math.abs(row - row_K) <= 1))
                            {
                                CanTakeKing = true;                             // King can capture king 
                                break loop_over_own_figures;                                
                            }
                            break;   
                    }
                }                                                               // Not own figure, continue loop
            }                                                                   // end of col loop
        }                                                                       // end of row loop
        if(Type == RECEIVING_CHECK)
        {
            SwitchMoveColor(Pos);                                               // Switch back to own move   
        }   
        return CanTakeKing;
    }
        
    public static boolean RookCanCaptureKing(int[][] Pos, int row, int col, int row_K, int col_K)
    {
        int row_s = Integer.signum(row_K - row);                                // row_s is row step
        int col_s = Integer.signum(col_K - col);                                // col_s is col step
        
        return ((row_s * col_s == 0)) && CanCaptureKingMovingInThisDirection(Pos, row, col, row_s, col_s, row_K, col_K);         
    }
        
    public static boolean BishopCanCaptureKing(int[][] Pos, int row, int col, int row_K, int col_K)
    {   
        if(Math.abs(row - row_K) == Math.abs(col - col_K))                      // King is on one bishop diagonale
        {                             
            return(CanCaptureKingMovingInThisDirection(Pos, row, col, Integer.signum(row_K - row), Integer.signum(col_K - col), row_K, col_K));
        }
        return false;        
    }
        
    public static boolean CanCaptureKingMovingInThisDirection(int[][] Pos, int row, int col, int row_s, int col_s, int row_K, int col_K)
    {
        int i;
        /* Debug begin
        Scanner scanner             = new Scanner(System.in);
        for(i = 1; ; i++)         // Move in this direction until reaching an occupied field
        {     
            if(((row + i * row_s) < 1) || ((row + i * row_s) > Position.ROWS) || ((col + i * col_s) < 1) || ((col + i * col_s) > Position.COLS))
            {
                System.out.println(" Error row = " + row + " i = " + i + " row_s = " + row_s + " col = " + col + " col_s = " + col_s);
                Position.Display(Pos);
                scanner.nextLine(); 
            }
            if (Pos[row + i * row_s][col + i * col_s] != EMPTY)
            {
                break; 
            }
        }   
        */ // Debug ends
        
        for(i = 1; Pos[row + i * row_s][col + i * col_s] == EMPTY; i++)         // Move in this direction until reaching an occupied field
        {
        } 
        return(((row + i * row_s) == row_K) && ((col + i * col_s) == col_K));  
    }
    
    public static boolean AnyMovePossible(int[][] Pos, int[][] MovePath)  
    {
        int[][] MovesPosition               = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];           // Holds all possible moves for one position
        boolean ReturnOnFirstMovePossible   = true;
        boolean MovePossible;
        
        Move.EmptyList(MovesPosition); 
        
        return(GenerateMoveList(Pos, MovesPosition, MovePath, ReturnOnFirstMovePossible));       
    }
   
    public static boolean InsufficientMaterial(int[][] Pos)                     // Detect Draw for not enough material on field    
    {
        int row;
        int col;
        int NumberWhiteKnights = 0;
        int NumberBlackKnights = 0;
        int NumberWhiteBishopsBlackField = 0;
        int NumberWhiteBishopsWhiteField = 0;
        int NumberBlackBishopsBlackField = 0;
        int NumberBlackBishopsWhiteField = 0;
        
        for(row = 1; row <= Position.ROWS; row++)      
        {
            for(col = 1; col <= COLS; col++)                          
            {
                switch(Pos[row][col])
                {   
                    case WHITE_PAWN:
                    case BLACK_PAWN:
                    case WHITE_QUEEN:
                    case BLACK_QUEEN:
                    case WHITE_ROOK:
                    case BLACK_ROOK:
                        return false;
                            
                    case WHITE_KNIGHT:
                        NumberWhiteKnights +=1;
                        if(NumberWhiteKnights > 1)
                        {
                            return false;
                        }
                        break;
                            
                    case BLACK_KNIGHT:
                        NumberBlackKnights +=1;
                        if(NumberBlackKnights > 1)
                        {
                            return false;
                        }     
                        break;
                        
                    case WHITE_BISHOP:
                        if(col % 2 == row % 2)
                        {
                            NumberWhiteBishopsBlackField +=1;
                        }
                        else
                        {
                            NumberWhiteBishopsWhiteField +=1;
                        }
                        if((NumberWhiteBishopsBlackField > 0) && (NumberWhiteBishopsWhiteField > 0))
                        {
                            return false;
                        }
                        break;
                            
                    case BLACK_BISHOP:
                        if(col % 2  == row % 2) 
                        {
                            NumberBlackBishopsBlackField +=1;
                        }
                        else
                        {
                            NumberBlackBishopsWhiteField +=1;
                        }
                        if((NumberBlackBishopsBlackField > 0) && (NumberBlackBishopsWhiteField > 0))
                        {
                            return false;
                        }
                        break;
                }
            }
        }
        if((NumberWhiteKnights == 1) && ((NumberWhiteBishopsWhiteField > 0) || (NumberWhiteBishopsBlackField > 0)))
        {
            return false;       
        }
        if((NumberBlackKnights == 1) && ((NumberBlackBishopsWhiteField > 0) || (NumberBlackBishopsBlackField > 0)))
        {
            return false;
        }
        // Zero or one Knight for White or Black left
        // Zero or more WhiteBishopsWhiteField for White left
        // Zero or more WhiteBoshopsBlackField for White left
        // Zero or more BlackBishopsWhiteField for Black left
        // Zero or more BlackBishopsBlackField for Black left
        return true;
    }  
    
    public static int GetStatus(int[][] Pos, int[][] MovePath)
    {
        int Status = NO_CONDITION;     
        
        SwitchMoveColor(Pos);   
        if(!AnyMovePossible(Pos, MovePath))
        {
            if(Check(Pos, RECEIVING_CHECK))
            {
                Status = CHECKMATE;
            }
            else
            {
                Status = STALEMATE;
            }
        }
        Position.SwitchMoveColor(Pos);  
        
        if(InsufficientMaterial(Pos))
        {
            Status = INSUFFICIENT_MATERIAL;
        }    
        
        if(ThreePositionRepetition(Pos, MovePath))
        {
            Status = THREE_POSITION_REPETITION;
        }        
        
        if(FiftyMove(Pos))
        {
            Status = FIFTY_MOVE;
        }
        
        return(Status);
    }        
    
    public static int GetCheckStatus(int[][] Pos, int PositionStatus)
    {   
        if(Check(Pos, GIVING_CHECK) && (PositionStatus  != CHECKMATE))        
        {
            return CHECK;
        }
        return NO_CHECK;
    }
    
    public static boolean End(int[][] Pos, int[][] MovePath)
    {
        //System.out.println("Entering EndPosition()... Move Color = " + GetMoveColor(Pos));

        if(Checkmate(Pos, MovePath) || Draw(Pos, MovePath))
        {
           return true;
        }
        return false;
    }
    
    public static boolean Checkmate(int[][] Pos, int[][] MovePath)
    {
        if(Chess.DebugLevel >= Settings.MEDIUM)
        {
            System.out.println();
            System.out.println("In Checkmate(): DisplayPosition()");
            
            if(GetMoveColor(Pos) == WHITE_MOVE)
            {
                System.out.println(" In Checkmate(): White");
            }
            else
            {
                System.out.println(" In Checkmate(): Black");
            }
            Display(Pos);
        
            if(AnyMovePossible(Pos, MovePath))
            {
                System.out.println("In Checkmate(): AnyMovePossible = true");
            }
            
            if(!AnyMovePossible(Pos, MovePath))
            {
                System.out.println("In Checkmate(): AnyMovePossible = false");
            }
            
            if(Check(Pos, RECEIVING_CHECK))
            {
                System.out.println("In Checkmate(): ReceivingCheck(Pos) = true");
            }
            
            if(!Check(Pos, RECEIVING_CHECK))
            {
                System.out.println("In Checkmate(): ReceivingCheck(Pos) = false");
            }
        }
            
        if(!AnyMovePossible(Pos, MovePath) && Check(Pos, RECEIVING_CHECK))
        {
            return true;
        }       
        return false;
    }
    
    public static boolean Draw(int[][] Pos, int[][] MovePath)
    {   
        if(Stalemate(Pos, MovePath) || InsufficientMaterial(Pos) || ThreePositionRepetition(Pos, MovePath) || FiftyMove(Pos))
        {
            return true;
        }        
        return false;
    }
    
    public static boolean Stalemate(int[][] Pos, int[][] MovePath)
    {
        if((!AnyMovePossible(Pos, MovePath) == true) && (Check(Pos, RECEIVING_CHECK) == false))        
        {
            return true;
        }
        return false;
    }

    public static boolean ThreePositionRepetition(int[][] Pos, int[][] MovePath)
    {   
        return(Move.RepetitivePositions(MovePath) >= Move.DRAW_REPETIVE_POSITIONS);
    }         

    public static boolean FiftyMove(int[][] Pos)
    {
        return(GetNumberOfMovesWithNoPawnMoveOrCapture(Pos) >= REVERSABLE_MOVES_LIMIT);
    }
    
    public static boolean OwnFigure(int[][] Pos, int row, int col)
    {                               
        int i;
        
        for(i = 0; i < Move.WhiteFigure.length; i++)
        {    
            switch(GetMoveColor(Pos))
            {
                case WHITE_MOVE:
                    if(Pos[row][col] == Move.WhiteFigure[i])
                    {
                        return true;
                    }
                    break;
            
                case BLACK_MOVE:
                    if(Pos[row][col] == Move.BlackFigure[i])
                    {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }
    
    public static int GetFigureColor(int Figure)
    {                               
        int i;
        
        for(i = 0; i < Move.WhiteFigure.length; i++)
        {    
            if(Figure == Move.WhiteFigure[i])
            {
                return WHITE_FIGURE;
            }
        }
        return BLACK_FIGURE;
    }  
    
    public static boolean OpponentFigure(int[][] Pos, int row_n, int col_n)
    {                               
        int i;

        for(i = 0; i < Move.WhiteFigure.length; i++)
        {    
            switch(GetMoveColor(Pos))
            {
                case WHITE_MOVE:
                    if(Pos[row_n][col_n] == Move.BlackFigure[i])
                    {
                        return true;
                    }
                    break;
            
                case BLACK_MOVE:
                    if(Pos[row_n][col_n] == Move.WhiteFigure[i])
                    {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }
    
    public static boolean OpponentKing(int[][] Pos, int row_n, int col_n)
    {                               
        switch(GetMoveColor(Pos))
        {
            case WHITE_MOVE:
                if(Pos[row_n][col_n] == BLACK_KING)
                {
                    return true;
                }
                break;
            
            case BLACK_MOVE:
                if(Pos[row_n][col_n] == WHITE_KING)
                {
                    return true;
                }
                break;
        }
        return false;
    }
      
    public static boolean OffBoardOrOwnFigure(int[][] Pos, int row_n, int col_n)
    {        
        int i;
        
        if(OnBoard(row_n, col_n))
        {
            if(Pos[row_n][col_n] == EMPTY)
            {
                return false;
            }
            for(i = 0; i < Move.WhiteFigure.length; i++)
            {    
                switch(GetMoveColor(Pos))
                {
                    case WHITE_MOVE:
                        if(Pos[row_n][col_n] == Move.WhiteFigure[i])
                        {
                            return true;
                        }
                        break;
            
                    case BLACK_MOVE:
                        if(Pos[row_n][col_n] == Move.BlackFigure[i])
                        {
                            return true;
                        }
                        break;
                }
            }
            return false;
        }
        return true;
    }
    
    public static boolean OnBoard(int row_n, int col_n)
    {                               
        return((col_n >= 1) && (col_n <= COLS) && (row_n >= 1) && (row_n <= ROWS));    
    }
    
    public static boolean GenerateMoveList(int[][] Pos, int[][] MovesPosition, int[][] MovePath, boolean ReturnOnFirstMovePossible)
    {                                                                           // Generates move list or returns on first move found    
        int col;
        int col_n = 0;
        int row;
        int row_n = 0;
        int i;
        int PawnStep = 0;
        int FieldNo;
        int dir;                                                                // Counter for going through all possible directions
        int Figure;
        int Figure_p = 0;
        int Figure_n = 0;
        int[] CastlingList = new int[2];
        int list;
        boolean MoveAdded = false;

        for(FieldNo = 0; FieldNo < (ROWS * COLS); FieldNo++)
        {
            col         = (FieldNo % COLS) + 1;
            row         = (FieldNo / ROWS) + 1 ;
            Figure      = Pos[row][col];
            
            if(Position.OwnFigure(Pos, row, col) == false)
            {
                continue;
            }
                    
            switch(Figure)
            {
                case WHITE_PAWN:
                case BLACK_PAWN:
                    switch(GetMoveColor(Pos))
                    {
                        case WHITE_MOVE:
                            PawnStep = 1;
                            break;
                
                        case BLACK_MOVE:
                            PawnStep = -1;
                            break;
                    }                
               
                    col_n = col;                                            // Move Pawn one field forward
                    row_n = row + PawnStep;      
                    if(Pos[row_n][col_n] == Position.EMPTY)                                                    
                    {
                        if((row_n == WHITE_PAWN_PROMOTION_ROW) || (row_n == BLACK_PAWN_PROMOTION_ROW))                        
                        {                                                   // Convert Pawn
                            for(i = 0; i < WhitePromotionFigure.length; i++)    
                            {                                               // Loop for Queen, Rook, Knight, Bishop
                                switch(GetMoveColor(Pos))
                                {
                                    case WHITE_MOVE:
                                        Figure_n = WhitePromotionFigure[i];
                                        break;

                                    case BLACK_MOVE:
                                        Figure_n = BlackPromotionFigure[i];
                                        break;
                                }
                                MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure_n, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                                if(ReturnOnFirstMovePossible && MoveAdded)
                                {
                                    return true;
                                }
    
                            }
                        }
                        else
                        {
                            MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                            if(ReturnOnFirstMovePossible && MoveAdded)
                            {
                                return true;
                            }
                        }
                    }
                    
                    col_n = col;        
                    row_n = row + 2 * PawnStep;                             // Move two PawnSteps forward
                    if(((GetMoveColor(Pos) == WHITE_MOVE) && (row == WHITE_PAWN_INITIAL_ROW)) || 
                        ((GetMoveColor(Pos) == BLACK_MOVE) && (row == BLACK_PAWN_INITIAL_ROW)))
                    {
                        if((Pos[row+PawnStep][col] == Position.EMPTY) && (Pos[row_n][col] == Position.EMPTY))         
                        { 
                            MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                            if(ReturnOnFirstMovePossible && MoveAdded)
                            {
                                return true;
                            }          
                        }
                    }
                    
                    for(i = -1; i < 2; i += 2)                                 // Pawn takes opponent figure away
                    {
                        col_n = col + i;
                        row_n = row + PawnStep;
                        if((col_n >= 1) && (col_n <= COLS) && (OpponentFigure(Pos, row_n, col_n)))
                        {
                            if((row_n == WHITE_PAWN_PROMOTION_ROW) || (row_n == BLACK_PAWN_PROMOTION_ROW))                     // Convert Pawn                           
                            {                                                   // Take away opponent figure
                                for(i = 0; i < WhitePromotionFigure.length; i++)    // Loop for Queen, Rook, Knight, Bishop
                                {                                               // .... and convert to new officer
                                    switch(GetMoveColor(Pos))
                                    {
                                        case WHITE_MOVE:
                                            Figure_n = WhitePromotionFigure[i];
                                            break;
                                        
                                            case BLACK_MOVE:
                                            Figure_n = BlackPromotionFigure[i];
                                            break;
                                    }     
                                    MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure_n, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                                    if(ReturnOnFirstMovePossible && MoveAdded)
                                    {
                                        return true;
                                    }
                                }
                            }
                            else
                            {
                                MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                                if(ReturnOnFirstMovePossible && MoveAdded)
                                {
                                    return true;
                                }
                            }
                        }   

                        // En passant
                        if((((GetMoveColor(Pos) == WHITE_MOVE) && (row == 5)) || ((GetMoveColor(Pos) == BLACK_MOVE) && (row == 4))) && (col_n >= 1) && (col_n <= COLS))
                        {                                                                                                 
                            if(col_n == GetColumnPawnMovedTwoRows(Pos))        //Workaround: Compiler ssems to miss this if this is added to line beforE
                            {
                                MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                                if(ReturnOnFirstMovePossible && MoveAdded)
                                {
                                    return true;
                                }                                     
                            }               
                        }                        
                    }                   
                    break;
               
                case WHITE_ROOK:
                case BLACK_ROOK:
                    if(DirectionMoves(Move.RookDirection, Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible))
                    {
                        return true;
                    }
                    break;                

                case WHITE_KNIGHT:
                case BLACK_KNIGHT:
                    for(dir = 0; dir < Move.KnightMove.length; dir++)                              // Loop over all four directions
                    {
                        row_n = row + Move.KnightMove[dir][0];
                        col_n = col + Move.KnightMove[dir][1];                             
                        if(OffBoardOrOwnFigure(Pos, row_n, col_n))
                        {
                            continue;                                           // Continue with next direction
                        }
                        MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                        if(ReturnOnFirstMovePossible && MoveAdded)
                        {
                            return true;
                        }                       
                    }
                    break;
               
                case WHITE_BISHOP:
                case BLACK_BISHOP:
                    if(DirectionMoves(Move.BishopDirection, Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible))
                    {
                        return true;
                    }
                    break;
                    
                case WHITE_QUEEN:
                case BLACK_QUEEN:        
                    if(DirectionMoves(Move.RookDirection, Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible) ||
                        DirectionMoves(Move.BishopDirection, Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible))
                    {
                        return true;
                    }
                    break;

                case WHITE_KING:
                case BLACK_KING:
                    for(dir = 0; dir < Move.KingMove.length; dir++)
                    {
                        row_n = row + Move.KingMove[dir][0];
                        col_n = col + Move.KingMove[dir][1];
                        if(OffBoardOrOwnFigure(Pos, row_n, col_n))
                        {
                            continue;                                           // Stop moving in this direction, continue with next direction
                        } 
                        MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                        if(ReturnOnFirstMovePossible && MoveAdded)
                        {
                            return true;
                        }
                    }
                    
                    list = Move.Castling(Pos, CastlingList);
                    for(i = 0; i < list; i++)
                    {
                        switch(CastlingList[i])
                        {
                            case Position.LONG_CASTLING:
                                col_n           = Position.C;                   //For King
                                break;
                                        
                            case Position.SHORT_CASTLING:
                                col_n           = Position.G;                   //For King
                                break;
                        }
                                
                        switch(Position.GetMoveColor(Pos))
                        {
                            case Position.WHITE_MOVE:
                                row     = Position.WHITE_CASTLING_ROW;
                                row_n   = Position.WHITE_CASTLING_ROW;
                                break;
                                        
                            case Position.BLACK_MOVE:
                                row     = Position.BLACK_CASTLING_ROW;
                                row_n   = Position.BLACK_CASTLING_ROW;
                                break;
                        }
                        MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                        if(Figure == Position.BLACK_KING && col_n == Position.F) {
                            System.out.println("now3;" + MoveAdded);
                        }
                        
                        if(ReturnOnFirstMovePossible && MoveAdded)
                        {
                            return true;
                        }                      
                    }
                    break;                            
            }
        }
        return false;                                                           // Only used by AnyMovePossible()
    }       
        
    public static boolean DirectionMoves(int[][] Direction, int[][]Pos, int row, int col, int Figure, int row_n, int col_n, int[][] MovesPosition, int[][]MovePath, boolean ReturnOnFirstMovePossible)
    {
        int dir;
        int i;
        boolean MoveAdded;
        
        for(dir = 0; dir < Direction.length; dir++)                             // Loop over all directions
        {
            for(i = 1;!OffBoardOrOwnFigure(Pos, row + Direction[dir][0] * i, col + Direction[dir][1] * i); i++)      
            {
                MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row + Direction[dir][0] * i, col + Direction[dir][1] * i, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                if(ReturnOnFirstMovePossible && MoveAdded)
                {
                    return true;
                }   
                if(OpponentFigure(Pos, row + Direction[dir][0] * i, col + Direction[dir][1] * i))                           // Took oponent figure away, stop to moving in this directio, continue with next direction
                {
                    break;
                }
            }
        }               
        return false;               
    }

    public static boolean AddMoveToMoveListIfNoReceivingCheck(int[][] Pos, int row, int col, int Figure_n, int row_n, int col_n, int[][] MovesPosition, int[][] MovePath, boolean ReturnOnFirstMovePossible)
    {
        boolean AddMove;
        int EnPassantStatus;
        int Figure;
        int Figure_p;   
        int TempPawn = 0;
        int MoveNumber;
        int i;
        int[][] PosStore = new int[Position.ROWS + 1][Position.COLS + 1];
        
        Copy(Pos, PosStore);                                            // Store position
        Figure              = Pos[row][col]; 
        Figure_p            = Pos[row_n][col_n];
        if(((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN)) && (col != col_n) && (Figure_p == Position.EMPTY))
        {
            EnPassantStatus = Position.EN_PASSANT;
        }
        else
        {
            EnPassantStatus = Position.NO_EN_PASSANT; 
        }        
        Move.Make(Pos, row, col, Figure_n, row_n, col_n, MovePath, Move.DO_NOT_ADD_TO_MOVE_HISTORY);     // Creates new position which needs to be investigated if own king can be captured 
        AddMove = !Check(Pos, Position.RECEIVING_CHECK);                        // Check for receiving check
        if(!ReturnOnFirstMovePossible && AddMove)                                                  
        {                                                                       // No receiving check, add move to MovesPosition
            MoveNumber                                          = Move.LastIndex(MovesPosition) + 1;
            MovesPosition[MoveNumber][Move.FIGURE]              = Figure;
            MovesPosition[MoveNumber][Move.ROW]                 = row;
            MovesPosition[MoveNumber][Move.COL]                 = col;
            MovesPosition[MoveNumber][Move.FIGURE_P]            = Figure_p;       
            MovesPosition[MoveNumber][Move.FIGURE_N]            = Figure_n;
            MovesPosition[MoveNumber][Move.ROW_N]               = row_n;
            MovesPosition[MoveNumber][Move.COL_N]               = col_n;
            MovesPosition[MoveNumber][Move.EN_PASSANT_STATUS]   = EnPassantStatus;                                                             // Needed to display e.p. as part of move          
            MovesPosition[MoveNumber][Move.POSITION_STATUS]     = GetStatus(Pos, MovePath);                                   // Needed to diplay checkmate of draw as part of move 
            MovesPosition[MoveNumber][Move.CHECK_STATUS]        = Position.GetCheckStatus(Pos, MovesPosition[MoveNumber][Move.POSITION_STATUS]);    // Needed to display check as part of move
            MovesPosition[MoveNumber][Move.RATING]              = Rating.Rating(Pos, MovesPosition[MoveNumber][Move.POSITION_STATUS]);           // Needed to display rating as part of move
        }
        Copy(PosStore, Pos);                                            // Restore position
        return AddMove;                                                         // Returns true if move was added
    }
}
