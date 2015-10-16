import java.lang.*;     // For abs, Signum
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Position
{
    public static final int EMPTY                       = 0;
    
    /* Use the following values to represent an int[][] Position, 
     *   and also as indices in Piece.Pieces */
    public static final int WHITE_KING                  = 1;
    public static final int WHITE_QUEEN                 = 2;
    public static final int WHITE_ROOK                  = 3;
    public static final int WHITE_KNIGHT                = 4;
    public static final int WHITE_BISHOP                = 5;
    public static final int WHITE_PAWN                  = 6;
    
    public static final int BLACK_KING                  = 7;
    public static final int BLACK_QUEEN                 = 8;
    public static final int BLACK_ROOK                  = 9;
    public static final int BLACK_KNIGHT                = 10;
    public static final int BLACK_BISHOP                = 11;
    public static final int BLACK_PAWN                  = 12;
    
    public static final int NUM_PIECES                  = 13;
    
    public static final int WHITE                       = 1;
    public static final int BLACK                       = -1;
    
    public static final int MIN_WQ = 0, MAX_WQ          = 9;
    public static final int MIN_BQ = 0, MAX_BQ          = 9;
    public static final int MIN_WR = 0, MAX_WR          = 9;
    public static final int MIN_BR = 0, MAX_BR          = 9;
    public static final int MIN_WN = 0, MAX_WN          = 9;
    public static final int MIN_BN = 0, MAX_BN          = 9;
    public static final int MIN_WB = 0, MAX_WB          = 9;
    public static final int MIN_BB = 0, MAX_BB          = 9;
    public static final int MIN_WP = 0, MAX_WP          = 8;
    public static final int MIN_BP = 0, MAX_BP          = 8;
    public static final int MIN_WK = 1, MAX_WK          = 1;
    public static final int MIN_BK = 1, MAX_BK          = 1;         

    public static final int A                           = 1;
    public static final int B                           = 2;
    public static final int C                           = 3;
    public static final int D                           = 4;
    public static final int E                           = 5;
    public static final int F                           = 6;
    public static final int G                           = 7;
    public static final int H                           = 8;
    
    public static final int ROWS                        = 8;
    public static final int COLS                        = 8;
    
    public static final int COLUMN_UP                   = 1;
    public static final int COLUMN_DOWN                 = -1;
    
    public static final int WHITE_CASTLING_ROW          = 1;
    public static final int BLACK_CASTLING_ROW          = 8;
    public static final int KING_CASTLING_STEPS         = 2;
    
    public static final int LONG_CASTLING               = 0;
    public static final int SHORT_CASTLING              = 1;
    public static int[] CASTLINGS = 
    {
        LONG_CASTLING, 
        SHORT_CASTLING
    };
    
    public static final int COLUMN_MOVE_COLOR           = 0;
    public static final int ROW_MOVE_COLOR              = 0;

    public static final int COLUMN_WHITE_LONG_CASTLING  = 0;
    public static final int ROW_WHITE_LONG_CASTLING     = 1;
    
    public static final int COLUMN_WHITE_SHORT_CASTLING = 0;
    public static final int ROW_WHITE_SHORT_CASTLING    = 2;
       
    public static final int COLUMN_BLACK_LONG_CASTLING  = 0;
    public static final int ROW_BLACK_LONG_CASTLING     = 3;
    
    public static final int COLUMN_BLACK_SHORT_CASTLING = 0;
    public static final int ROW_BLACK_SHORT_CASTLING    = 4;
    
    public static final int COLUMN_EN_PASSANT           = 0;
    public static final int ROW_EN_PASSANT              = 5;
    
    public static final int COLUMN_FIFTY_MOVE           = 0;
    public static final int ROW_FIFTY_MOVE              = 6;    
      
    public static final int COLUMN_REPETITIVE_POSITIONS = 0;
    public static final int ROW_REPETITIVE_POSITIONS    = 7;

    public static final int WHITE_MOVE                  = 0;
    public static final int BLACK_MOVE                  = 1;
          
    public static final String[] MoveColorMenu = 
    {
        "Move Color", 
        "White move", 
        "Black move" 
    };           
            
    public static final int WHITE_FIGURE                = 1;
    public static final int BLACK_FIGURE                = -1;    
    
    // Castling possible, rook and king did not move before
    public static final int WRA1_AND_WKE1_DID_NOT_MOVE  = 0;  
    public static final int WRA1_OR_WKE1_DID_MOVE       = 1;    
    public static final int WRH1_AND_WKE1_DID_NOT_MOVE  = 0;          
    public static final int WRH1_OR_WKE1_DID_MOVE       = 1;    
    public static final int BRA8_AND_BKE8_DID_NOT_MOVE  = 0;       
    public static final int BRA8_OR_BKE8_DID_MOVE       = 1;           
    public static final int BRH8_AND_BKE8_DID_NOT_MOVE  = 0;          
    public static final int BRH8_OR_BKE8_DID_MOVE       = 1;          

    public static final int WHITE_PAWN_PROMOTION_ROW    = 8;  
    public static final int BLACK_PAWN_PROMOTION_ROW    = 1;  
        
    public static final int WHITE_PAWN_INITIAL_ROW      = 2;  
    public static final int BLACK_PAWN_INITIAL_ROW      = 7;  
    
    // En passant status
    public static final int NO_EN_PASSANT               = 0;
    public static final int EN_PASSANT                  = 1;    
    
    // Check status
    public static final int NO_CHECK                    = 0;
    public static final int CHECK                       = 1;   
    
    // Position status
    public static final int NO_CONDITION                = 0;
    public static final int CHECKMATE                   = 1;
    public static final int STALEMATE                   = 2;
    public static final int INSUFFICIENT_MATERIAL       = 3;
    public static final int THREE_POSITION_REPETITION   = 4;    
    public static final int FIFTY_MOVE                  = 5;
    
    public static final String[] PositionStatusText = 
    {
        "No condition", 
        "Checkmate", 
        "Stalemate", 
        "Insufficient material",
        "Three position repetition", 
        "Fifty move rule" 
    };
    
    public static final int REVERSABLE_MOVES_LIMIT      = 50;    
    
    // Psition names
    public static final int NEW_POSITION                = 0;
    public static final int PAWN_POSITION               = 1;    
    public static final int EN_PASSANT_POSITION         = 2;
    public static final int PROMOTION_POSITION          = 3;
    public static final int CASTLING_POSITION           = 4;
    public static final int INSUFFICIENT_MATERIAL_POSITION  = 5;
    public static final int ONE_MOVE_MATE_POSITION      = 6;
    public static final int TWO_MOVE_MATE_POSITION      = 7;
    public static final int THREE_MOVE_MATE_POSITION    = 8;    
      
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
    
    
    
    // Check parameter
    public static final int GIVING_CHECK                = 0;
    public static final int RECEIVING_CHECK             = 1;    

    public static int       BeginPosition;   
    
    public static int[] WhiteFigure = 
    { 
        WHITE_KING, 
        WHITE_QUEEN, 
        WHITE_ROOK, 
        WHITE_KNIGHT, 
        WHITE_BISHOP, 
        WHITE_PAWN
    };
                                                            
    public static int[] BlackFigure = { 
        BLACK_KING, 
        BLACK_QUEEN, 
        BLACK_ROOK, 
        BLACK_KNIGHT, 
        BLACK_BISHOP, 
        BLACK_PAWN
    };
            
    public static int[] WhitePromotionFigure = 
    { 
        WHITE_QUEEN, 
        WHITE_ROOK, 
        WHITE_KNIGHT, 
        WHITE_BISHOP
    };
                                                            
    public static int[] BlackPromotionFigure = 
    { 
        BLACK_QUEEN, 
        BLACK_ROOK, 
        BLACK_KNIGHT, 
        BLACK_BISHOP
    }; 
    
    public static final String[] colStr = // used to display column notation
    {
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h"
    };
    
    public static String ToString(int[][] Pos)
    {
        String PosString = "";
        for (int row = 1; row <= ROWS; row++) {
            for (int col = 1; col <= COLS; col++) {
                PosString += " " + Integer.toString(Pos[row][col]);
            }
        }
        return PosString;
    }
    
    public static void SetFromFile(int[][] Pos, String filename)
    {
        File file = new File(filename);
        
        Clear(Pos);

        try
        {
            Scanner sc = new Scanner(file);
            for(int row = ROWS; row > 0; row--)
            {
                
                sc.next(); // skip row number
                for(int col = 1; col <= COLS; col++)
                {
                    Pos[row][col] = Piece.ConsoleNotationToType(sc.next());
                    if(ValidPiece(Pos[row][col])) 
                    {
                        continue;    // Continues with next column
                    }
                    System.out.println("Pos[" + row + "][" + col + "] = " + Pos[row][col] + " is invalid. Please check input file."); 
                    return;
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
    
    public static boolean ValidPiece(int Figure)
    {
        for(int pieceNumber = 0; pieceNumber < NUM_PIECES; pieceNumber++)
        {
            if(Figure == pieceNumber)
            {
                return true;     // Found valid piece
            }
        }
        return false;    // No valid piece found
    }
    
    public static void Clear(int[][] Pos)
    {
        for(int row = 0; row <= ROWS; row++)        // Loop over total ROWS + 1
        {   
            for(int col = 0; col <= COLS; col++)    // Loop over total COLS + 1 
            {
                Pos[row][col] = EMPTY;
            }
        }    
    }
    
    public static void Copy(int[][] FromPos, int[][] ToPos)  // Copies FromPos to ToPos
    {
        for(int row = 0; row <= ROWS; row++)                // Loop over total ROWS + 1
        {   
            for(int col = 0; col <= COLS; col++)            // Loop over total COLS + 1 
            {
                ToPos[row][col] = FromPos[row][col];
            }
        }
    }   
    
    public static int[] CountPieces(int[][] Pos) {
        int counts[] = new int[NUM_PIECES];
        
        for(int row = 1; row <= ROWS; row++)
        {
            for(int col = 1; col <= COLS; col++)           
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
                System.out.println
                (
                    "Nonvalid position: Number of " + 
                    Piece.Pieces[i].getConsoleNotation() + 
                    " is " + 
                    counts[i] + 
                    " and is not in the allowed range of " + 
                    min_allowed + 
                    " to " + max_allowed
                );
                valid = false;
            }
        }
        return(valid);
    }   
    
    public static void Display(int[][] Pos)
    {
        int row, col;
        
        for(row = ROWS; row > 0; row--)
        {
            System.out.print(row + " ");
            for(col = 1; col <= COLS; col++)
            {
                System.out.print(" " + Piece.Pieces[Pos[row][col]].getConsoleNotation() + " ");
            }
            System.out.print ("\n");
        }

        // Show column notation as a, b, c, ..., h
        System.out.print("   ");
        for(col = 0; col < COLS; col++) // colStr starts at 0
        {
            System.out.print(colStr[col] + "  "); 
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
        System.out.println();
    }
    
    public static void ShowNumberOfRepetitivePositions(int[][] Pos)
    {
        System.out.println("RepetitivePositionCounter: \t" + GetNumberOfRepetitivePositions(Pos));
    }        
    
    public static void ShowFiftyMoveCounter(int[][] Pos)
    {
        System.out.println("FiftyMoveCounter: \t\t" + GetNumberOfMovesWithNoPawnMoveOrCapture(Pos));
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
        System.out.println((GetMoveColor(Pos) == WHITE_MOVE) ? "White" : "Black");
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
        int row_s;          // Row step
        int col_s;          // Column step       
        int row_K = 0;      // Row of opponent king 
        int col_K = 0;      // Column of opponent king

        boolean CanTakeKing = false;    // False initialization is required
       
        if(Type == RECEIVING_CHECK)
        {
            SwitchMoveColor(Pos);       // Switch to opponent move   
        }        
        
        // Find row and column of opponent king
        loop:
        for(row_K = 1; row_K <= ROWS; row_K++)   
        {
            for(col_K = 1; col_K <= COLS; col_K++)
            {
                if(OpponentKing(Pos, row_K, col_K))
                {
                    break loop;     // Found row and column of opponent king
                }
            }
        }

        loop_over_own_figures:
        for(row = 1; row <= ROWS; row++)  // Go through all fields and find own figure
        {
            for(col = 1; col <= COLS; col++)
            {
                if(OwnFigure(Pos, row, col))    // Found own figure
                {
                    switch(Pos[row][col])
                    {
                        case WHITE_PAWN:
                        case BLACK_PAWN:
                            row_s = (Pos[row][col] == WHITE_PAWN) ? 1 : -1;
                            if(((row + row_s) == row_K) && (Math.abs(col - col_K) == 1))
                            {
                                CanTakeKing = true; // Pawn can capture king
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_ROOK:
                        case BLACK_ROOK:
                            if(RookCanCaptureKing(Pos, row, col, row_K, col_K))
                            {
                                CanTakeKing = true; // Rook can capture king
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_KNIGHT:
                        case BLACK_KNIGHT:
                            if(((Math.abs(col - col_K) == 2) && (Math.abs(row - row_K) == 1)) ||
                               ((Math.abs(col - col_K) == 1) && (Math.abs(row - row_K) == 2)))
                            {
                                CanTakeKing = true; // Knight can capture king
                                break loop_over_own_figures;
                            }
                            break;
              
                        case WHITE_BISHOP:
                        case BLACK_BISHOP:
                            if(BishopCanCaptureKing(Pos, row, col, row_K, col_K))
                            {
                                CanTakeKing = true; // Bishop can capture king  
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_QUEEN:
                        case BLACK_QUEEN:
                            if((BishopCanCaptureKing(Pos, row, col, row_K, col_K)) ||                           
                               (RookCanCaptureKing  (Pos, row, col, row_K, col_K)))
                            {   
                                // Queen moves are the sum of rook or bishop moves
                                CanTakeKing = true;     // Queen can capture king 
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_KING:
                        case BLACK_KING:            
                            // Avoids that own king can move next to opponent king
                            if((Math.abs(col - col_K) <= 1) && (Math.abs(row - row_K) <= 1))
                            {
                                CanTakeKing = true; // King can capture king 
                                break loop_over_own_figures;                                
                            }
                            break;   
                    }   // End of switch
                }   // Not own figure, continue loop
            }   // end of col loop
        }   // end of row loop
        if(Type == RECEIVING_CHECK)
        {
            SwitchMoveColor(Pos);   // Switch back to own move   
        }   
        return CanTakeKing;
    }
        
    public static boolean RookCanCaptureKing(int[][] Pos, int row, int col, int row_K, int col_K)
    {
        int row_s = Integer.signum(row_K - row);    // row_s is row step
        int col_s = Integer.signum(col_K - col);    // col_s is col step
        
        return ((row_s * col_s == 0)) && CanCaptureKingMovingInThisDirection(Pos, row, col, row_s, col_s, row_K, col_K);         
    }
        
    public static boolean BishopCanCaptureKing(int[][] Pos, int row, int col, int row_K, int col_K)
    {   
        if(Math.abs(row - row_K) == Math.abs(col - col_K))  // King is on one bishop diagonale
        {                             
            return(CanCaptureKingMovingInThisDirection(Pos, row, col, Integer.signum(row_K - row), Integer.signum(col_K - col), row_K, col_K));
        }
        return false;        
    }
        
    public static boolean CanCaptureKingMovingInThisDirection(int[][] Pos, int row, int col, int row_s, int col_s, int row_K, int col_K)
    {
        int i;
        
        for(i = 1; Pos[row + i * row_s][col + i * col_s] == EMPTY; i++)       
        {     
            // Moves in this direction until reaching an occupied field
            // No need to check for off board since moving towards king is always on board
        } 
        // Return true if occupied field has opponent King on it
        return(((row + i * row_s) == row_K) && ((col + i * col_s) == col_K));  
    }
    
    public static boolean AnyMovePossible(int[][] Pos, int[][] MovePath)  
    {
        // MovesPosition[][] holds all possible moves for one position
        int[][] MovesPosition               = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];          
        boolean ReturnOnFirstMovePossible   = true;
        boolean MovePossible;
        
        Move.EmptyList(MovesPosition); 
        
        return(GenerateMoveList(Pos, MovesPosition, MovePath, ReturnOnFirstMovePossible));       
    }
   
    public static boolean InsufficientMaterial(int[][] Pos)                      
    {
        // Detect Draw for not enough material on field   
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
            Status = (Check(Pos, RECEIVING_CHECK)) ? CHECKMATE : STALEMATE;
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
        return(Check(Pos, GIVING_CHECK) && (PositionStatus  != CHECKMATE)) ? CHECK : NO_CHECK;
    }
    
    public static boolean End(int[][] Pos, int[][] MovePath)
    {
        return(Checkmate(Pos, MovePath) || Draw(Pos, MovePath)) ? true : false;
    }
    
    public static boolean Checkmate(int[][] Pos, int[][] MovePath)
    {
        return(!AnyMovePossible(Pos, MovePath) && Check(Pos, RECEIVING_CHECK)) ? true : false;
    }
    
    public static boolean Draw(int[][] Pos, int[][] MovePath)
    {   
        return (Stalemate(Pos, MovePath) || InsufficientMaterial(Pos) || ThreePositionRepetition(Pos, MovePath) || FiftyMove(Pos)) ? true : false;
    }
    
    public static boolean Stalemate(int[][] Pos, int[][] MovePath)
    {
        return ((!AnyMovePossible(Pos, MovePath) == true) && (Check(Pos, RECEIVING_CHECK) == false)) ? true : false;  
      
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
    {                                                                           
        // Generates move list or returns on first move found    
        int col;
        int col_n = 0;
        int row;
        int row_n = 0;
        int i;
        int PawnStep = 0;
        int FieldNo;
        int dir;    // Counter for going through all possible directions
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
                    PawnStep = ((GetMoveColor(Pos)) == WHITE_MOVE) ? 1 : -1;
                    col_n = col;    // Move Pawn one field forward
                    row_n = row + PawnStep;      
                    if(Pos[row_n][col_n] == Position.EMPTY)                                                    
                    {
                        if((row_n == WHITE_PAWN_PROMOTION_ROW) || (row_n == BLACK_PAWN_PROMOTION_ROW))                        
                        {                                                       
                            // Convert Pawn
                            for(i = 0; i < WhitePromotionFigure.length; i++)    
                            {                                                   
                                // Loop for Queen, Rook, Knight, Bishop
                                Figure_n = ((GetMoveColor(Pos)) == WHITE_MOVE) ? WhitePromotionFigure[i] : BlackPromotionFigure[i];
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
                    row_n = row + 2 * PawnStep;     // Move two PawnSteps forward
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
                    
                    for(i = -1; i <= 1; i += 2) // Pawn takes opponent figure away
                    {
                        col_n = col + i;
                        row_n = row + PawnStep;
                        if((col_n >= 1) && (col_n <= COLS) && (OpponentFigure(Pos, row_n, col_n)))
                        {
                            if((row_n == WHITE_PAWN_PROMOTION_ROW) || (row_n == BLACK_PAWN_PROMOTION_ROW))                             
                            {   
                                // Take away opponent figure and promote pawn
                                for(i = 0; i < WhitePromotionFigure.length; i++)    
                                {        
                                    Figure_n = ((GetMoveColor(Pos)) == WHITE_MOVE) ? WhitePromotionFigure[i] : BlackPromotionFigure[i];
                                    MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure_n, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                                    if(ReturnOnFirstMovePossible && MoveAdded)
                                    {
                                        return true;
                                    }
                                }
                            }
                            else
                            {
                                // Take away opponent figure
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
                case WHITE_KNIGHT:
                case BLACK_KNIGHT:
                case WHITE_BISHOP:
                case BLACK_BISHOP:
                case WHITE_QUEEN:
                case BLACK_QUEEN:        
                    if(DirectionMoves(Piece.Pieces[Figure].getPieceMove(), Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible))
                    {
                        return true;
                    }
                    break;

                case WHITE_KING:
                case BLACK_KING:
                    if(DirectionMoves(Piece.Pieces[Figure].getPieceMove(), Pos, row, col, Figure, row_n, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible))
                    {
                        return true;
                    }   
                  
                    list = Move.Castling(Pos, CastlingList);
                    for(i = 0; i < list; i++)
                    {
                        col_n = (CastlingList[i] == Position.LONG_CASTLING) ? Position.C : Position.G;
                        row = ((Position.GetMoveColor(Pos)) == Position.WHITE_MOVE) ? Position.WHITE_CASTLING_ROW : Position.BLACK_CASTLING_ROW;
                        MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row, col_n, MovesPosition, MovePath, ReturnOnFirstMovePossible);
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
        return false;   // Only used by AnyMovePossible()
    }       
    
    public static boolean DirectionMoves(Piece.PieceMove piece_move, int[][]Pos, int row, int col, int Figure, int row_n, int col_n, int[][] MovesPosition, int[][]MovePath, boolean ReturnOnFirstMovePossible)
    {
        int [][] Direction = piece_move.direction;
        int dir;
        int i;
        boolean MoveAdded;
        
        for(dir = 0; dir < Direction.length; dir++)  // Loop over all directions
        {
            for(i = 1;!OffBoardOrOwnFigure(Pos, row + Direction[dir][0] * i, col + Direction[dir][1] * i); i++)      
            {
                if( i > piece_move.max_steps) 
                { 
                    break; 
                }
                MoveAdded = AddMoveToMoveListIfNoReceivingCheck(Pos, row, col, Figure, row + Direction[dir][0] * i, col + Direction[dir][1] * i, MovesPosition, MovePath, ReturnOnFirstMovePossible);
                if(ReturnOnFirstMovePossible && MoveAdded)
                {
                    return true;
                }   
                if(OpponentFigure(Pos, row + Direction[dir][0] * i, col + Direction[dir][1] * i))                          
                {
                    // Took oponent figure away, stop to moving in this direction, continue with next direction
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
        int MoveNumber;
        int[][] PosStore = new int[Position.ROWS + 1][Position.COLS + 1];
        
        Copy(Pos, PosStore);    // Store position
        Figure              = Pos[row][col]; 
        Figure_p            = Pos[row_n][col_n];
        EnPassantStatus = (((Figure == Position.WHITE_PAWN) || (Figure == Position.BLACK_PAWN)) && (col != col_n) && (Figure_p == Position.EMPTY)) ? Position.EN_PASSANT : Position.NO_EN_PASSANT;
        
        // Create new position which needs to be investigated if own king can be captured 
        Move.Make(Pos, row, col, Figure_n, row_n, col_n, MovePath, Move.DO_NOT_ADD_TO_MOVE_HISTORY);    
        AddMove = !Check(Pos, Position.RECEIVING_CHECK); // Check for receiving check
        if(!ReturnOnFirstMovePossible && AddMove)                                                  
        {   
            // No receiving check, add move to MovesPosition
            MoveNumber                                          = Move.LastIndex(MovesPosition) + 1;
            MovesPosition[MoveNumber][Move.FIGURE]              = Figure;
            MovesPosition[MoveNumber][Move.ROW]                 = row;
            MovesPosition[MoveNumber][Move.COL]                 = col;
            MovesPosition[MoveNumber][Move.FIGURE_P]            = Figure_p;       
            MovesPosition[MoveNumber][Move.FIGURE_N]            = Figure_n;
            MovesPosition[MoveNumber][Move.ROW_N]               = row_n;
            MovesPosition[MoveNumber][Move.COL_N]               = col_n;
            // Need EN_PASSANT_STATUS to display e.p. as part of move  
            MovesPosition[MoveNumber][Move.EN_PASSANT_STATUS]   = EnPassantStatus;  
            // Need POSITION_STATUS to display checkmate of draw as part of move 
            MovesPosition[MoveNumber][Move.POSITION_STATUS]     = GetStatus(Pos, MovePath);    
            // Need CHECK_STATUS to display check as part of move
            MovesPosition[MoveNumber][Move.CHECK_STATUS]        = Position.GetCheckStatus(Pos, MovesPosition[MoveNumber][Move.POSITION_STATUS]);   
            // Needed to display rating as part of move
            MovesPosition[MoveNumber][Move.RATING]              = Rating.Rating(Pos, MovesPosition[MoveNumber][Move.POSITION_STATUS]);           
        }
        Copy(PosStore, Pos);    // Restore position
        return AddMove;         // Returns true if move was added
    }
}
