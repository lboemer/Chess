import java.util.*;                                                             // For Date
import java.lang.*;                                                             // For abs

public class Position
{
    public static final int WHITE_KING                          = 6;
    public static final int WHITE_QUEEN                         = 5;
    public static final int WHITE_ROOK                          = 4;
    public static final int WHITE_KNIGHT                        = 3;
    public static final int WHITE_BISHOP                        = 2;
    public static final int WHITE_PAWN                          = 1;
    
    public static final int BLACK_KING                          = -6;
    public static final int BLACK_QUEEN                         = -5;
    public static final int BLACK_ROOK                          = -4;
    public static final int BLACK_KNIGHT                        = -3;
    public static final int BLACK_BISHOP                        = -2;
    public static final int BLACK_PAWN                          = -1;
    
    public static final int EMPTY                               = 0;

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
    
    public static final int COLUMN_STORE_MOVE_COLOR             = 0;
    public static final int ROW_STORE_MOVE_COLOR                = 0;

    public static final int COLUMN_STORE_WHITE_LONG_CASTLING    = 0;
    public static final int ROW_STORE_WHITE_LONG_CASTLING       = 1;
    
    public static final int COLUMN_STORE_WHITE_SHORT_CASTLING   = 0;
    public static final int ROW_STORE_WHITE_SHORT_CASTLING      = 2;
       
    public static final int COLUMN_STORE_BLACK_LONG_CASTLING    = 0;
    public static final int ROW_STORE_BLACK_LONG_CASTLING       = 3;
    
    public static final int COLUMN_STORE_BLACK_SHORT_CASTLING   = 0;
    public static final int ROW_STORE_BLACK_SHORT_CASTLING      = 4;
    
    public static final int COLUMN_STORE_EN_PASSANT             = 0;
    public static final int ROW_STORE_EN_PASSANT                = 5;
    
    public static final int COLUMN_STORE_FIFTY_MOVE             = 0;
    public static final int ROW_STORE_FIFTY_MOVE                = 6;    
      
    public static final int COLUMN_STORE_NUMBER_OF_REPETITIVE_POSITIONS = 0;
    public static final int ROW_STORE_NUMBER_OF_REPETITIVE_POSITIONS  = 7;

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
    
    public static final int NUMBER_OF_MOVES_WITH_NO_PAWN_MOVE_OR_CAPTURE_REQUIRED_TO_DECLARE_DRAW = 50;    
    
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

    public static final int FALSE                               = 0;
    public static final int TRUE                                = 1;
    
    // Parameter for Check()
    public static final int GIVING_CHECK                        = 0;
    public static final int RECEIVING_CHECK                     = 1;    

    public static int       BeginPosition;   
    
    public Position()
    {

    }
    
    public static void PositionNew(int[][] Pos)
    {
        ClearPosition(Pos);  
        
        Pos[1][A] = WHITE_ROOK;
        Pos[1][B] = WHITE_KNIGHT;
        Pos[1][C] = WHITE_BISHOP;
        Pos[1][D] = WHITE_QUEEN;
        Pos[1][E] = WHITE_KING;
        Pos[1][F] = WHITE_BISHOP;
        Pos[1][G] = WHITE_KNIGHT;
        Pos[1][H] = WHITE_ROOK;
        Pos[2][A] = WHITE_PAWN;
        Pos[2][B] = WHITE_PAWN;
        Pos[2][C] = WHITE_PAWN;
        Pos[2][D] = WHITE_PAWN;
        Pos[2][E] = WHITE_PAWN;
        Pos[2][F] = WHITE_PAWN;
        Pos[2][G] = WHITE_PAWN;
        Pos[2][H] = WHITE_PAWN;

        Pos[8][A] = BLACK_ROOK;
        Pos[8][B] = BLACK_KNIGHT;
        Pos[8][C] = BLACK_BISHOP;
        Pos[8][D] = BLACK_QUEEN;
        Pos[8][E] = BLACK_KING;
        Pos[8][F] = BLACK_BISHOP;
        Pos[8][G] = BLACK_KNIGHT;
        Pos[8][H] = BLACK_ROOK;
        Pos[7][A] = BLACK_PAWN;
        Pos[7][B] = BLACK_PAWN;
        Pos[7][C] = BLACK_PAWN;
        Pos[7][D] = BLACK_PAWN;
        Pos[7][E] = BLACK_PAWN;
        Pos[7][F] = BLACK_PAWN;
        Pos[7][G] = BLACK_PAWN;
        Pos[7][H] = BLACK_PAWN;
        
        SetMoveColor(Pos, WHITE_MOVE);
         
        SetColumnPawnMovedTwoRows(Pos,0);                                       // Shows column of pawn that moved two steps to reach this position, 0 means that no pawn moved two steps in last move 
        
        SetWhiteLongCastling(Pos, WRA1_AND_WKE1_DID_NOT_MOVE);                  // White Long Castling possible, WRa1 and WKe1 did not move before
        SetWhiteShortCastling(Pos, WRH1_AND_WKE1_DID_NOT_MOVE);                 // White Short Castling possible, WRh8 and WKe1 did not move before
        SetBlackLongCastling(Pos, BRA8_AND_BKE8_DID_NOT_MOVE);                  // Black Long Casling possible, WRh8 and BKe8 did not move before
        SetBlackShortCastling(Pos, BRH8_AND_BKE8_DID_NOT_MOVE);                 // Black Short Castling possible, BRa8 and BKe8 did not move before
    }
    
    public static void PositionPawn(int[][] Pos)
    {
        ClearPosition(Pos);  
        
        Pos[1][E] = WHITE_KING;
        Pos[2][B] = WHITE_PAWN;
        Pos[3][B] = WHITE_PAWN;
        //Pos[3][C] = WHITE_PAWN;
        //Pos[3][F] = WHITE_PAWN;
        Pos[5][D] = WHITE_PAWN;
        Pos[6][D] = WHITE_PAWN;
        Pos[2][F] = WHITE_PAWN;
        //Pos[4][F] = WHITE_PAWN;
        Pos[2][H] = WHITE_PAWN;
        Pos[3][H] = WHITE_PAWN;

        Pos[8][E] = BLACK_KING;
        Pos[7][A] = BLACK_PAWN;
        Pos[7][B] = BLACK_PAWN;
        Pos[6][E] = BLACK_PAWN;
        Pos[6][F] = BLACK_PAWN;
        Pos[5][H] = BLACK_PAWN;
        Pos[6][H] = BLACK_PAWN;        
        Pos[2][H] = BLACK_PAWN;
  
        SetMoveColor(Pos, WHITE_MOVE);
         
        SetColumnPawnMovedTwoRows(Pos,0);                                       // Shows column of pawn that moved two steps to reach this position, 0 means that no pawn moved two steps in last move 
        
        SetWhiteLongCastling(Pos, WRA1_AND_WKE1_DID_NOT_MOVE);                  // White Long Castling possible, WRa1 and WKe1 did not move before
        SetWhiteShortCastling(Pos, WRH1_AND_WKE1_DID_NOT_MOVE);                 // White Short Castling possible, WRh8 and WKe1 did not move before
        SetBlackLongCastling(Pos, BRA8_AND_BKE8_DID_NOT_MOVE);                  // Black Long Casling possible, WRh8 and BKe8 did not move before
        SetBlackShortCastling(Pos, BRH8_AND_BKE8_DID_NOT_MOVE);                 // Black Short Castling possible, BRa8 and BKe8 did not move before
    }   
    
    public static void PositionEnPassant(int[][] Pos)
    {
        ClearPosition(Pos);        
        
        Pos[8][A] = WHITE_ROOK;
        Pos[1][E] = WHITE_KING;
        Pos[1][H] = WHITE_ROOK;
        Pos[7][B] = WHITE_PAWN;
        Pos[4][B] = WHITE_PAWN;
        Pos[2][C] = WHITE_PAWN;
        Pos[2][D] = WHITE_PAWN;
        Pos[2][E] = WHITE_PAWN;
        Pos[2][F] = WHITE_PAWN;
        Pos[2][G] = WHITE_PAWN;
        Pos[5][H] = WHITE_PAWN;

        Pos[8][A] = BLACK_ROOK;
        Pos[8][E] = BLACK_KING;
        Pos[8][H] = BLACK_ROOK;
        Pos[4][A] = BLACK_PAWN;
        Pos[7][C] = BLACK_PAWN;
        Pos[6][D] = BLACK_PAWN;
        Pos[7][E] = BLACK_PAWN;
        Pos[7][F] = BLACK_PAWN;
        Pos[5][G] = BLACK_PAWN;
        
        SetMoveColor(Pos, BLACK_MOVE); 
         
        SetColumnPawnMovedTwoRows(Pos, B);                                      // Shows column of pawn that moved two steps to reach this position, 0 means that no pawn moved two steps in last move  
        
        SetWhiteLongCastling(Pos, WRA1_OR_WKE1_DID_MOVE);                       // White Long Castling not possible, WRa1 and WKe1 did not move before
        SetWhiteShortCastling(Pos, WRH1_AND_WKE1_DID_NOT_MOVE);                 // White Short Castling not possible, WRh8 and WKe1 did not move before
        SetBlackLongCastling(Pos, BRA8_AND_BKE8_DID_NOT_MOVE);                  // Black Long Casling not possible, WRh8 and BKe8 did not move before
        SetBlackShortCastling(Pos, BRH8_AND_BKE8_DID_NOT_MOVE);                 // Black Short Castling not possible, BRa8 and BKe8 did not move before
        
        SetNumberOfMovesWithNoPawnMoveOrCapture(Pos, 0);   
    } 
    
    public static void PositionPromotion(int[][] Pos)
    {
        ClearPosition(Pos);    
        
        Pos[1][A] = WHITE_ROOK;
        Pos[1][E] = WHITE_KING;
        Pos[1][H] = WHITE_ROOK;
        Pos[7][B] = WHITE_PAWN;
        Pos[2][C] = WHITE_PAWN;
        Pos[2][D] = WHITE_PAWN;
        Pos[2][E] = WHITE_PAWN;
        Pos[2][F] = WHITE_PAWN;
        Pos[2][G] = WHITE_PAWN;
        Pos[2][H] = WHITE_PAWN;

        Pos[8][A] = BLACK_ROOK;
        Pos[8][E] = BLACK_KING;
        Pos[7][C] = BLACK_PAWN;
        Pos[7][D] = BLACK_PAWN;
        Pos[7][E] = BLACK_PAWN;
        Pos[7][F] = BLACK_PAWN;
        Pos[7][G] = BLACK_PAWN;
        Pos[6][G] = BLACK_PAWN;
        Pos[4][G] = BLACK_PAWN;         
        
        SetMoveColor(Pos, WHITE_MOVE);
        SetColumnPawnMovedTwoRows(Pos,B);                                       // Shows column of pawn that moved two steps to reach this position, 0 means that no pawn moved two steps in last move 
        
        SetWhiteLongCastling(Pos, WRA1_AND_WKE1_DID_NOT_MOVE);                  // White Long Castling possible, WRa1 and WKe1 did not move before
        SetWhiteShortCastling(Pos, WRH1_AND_WKE1_DID_NOT_MOVE);                 // White Short Castling possible, WRh8 and WKe1 did not move before
        SetBlackLongCastling(Pos, BRA8_AND_BKE8_DID_NOT_MOVE);                  // Black Long Casling possible, WRh8 and BKe8 did not move before
        SetBlackShortCastling(Pos, BRH8_AND_BKE8_DID_NOT_MOVE);                 // Black Short Castling possible, BRa8 and BKe8 did not move before
    }
     
    public static void PositionCastling(int[][] Pos)
    {
        ClearPosition(Pos);        
        
        Pos[1][A] = WHITE_ROOK;
        Pos[1][E] = WHITE_KING;
        Pos[1][H] = WHITE_ROOK;
        Pos[2][A] = WHITE_PAWN;
        Pos[2][B] = WHITE_PAWN;
        Pos[2][C] = WHITE_PAWN;
        Pos[2][D] = WHITE_PAWN;
        Pos[2][E] = WHITE_PAWN;
        Pos[2][F] = WHITE_PAWN;
        Pos[2][G] = WHITE_PAWN;

        Pos[8][A] = BLACK_ROOK;
        Pos[8][E] = BLACK_KING;
        Pos[8][H] = BLACK_ROOK;
        Pos[7][B] = BLACK_PAWN;
        Pos[7][C] = BLACK_PAWN;
        Pos[7][D] = BLACK_PAWN;
        Pos[7][E] = BLACK_PAWN;
        Pos[7][F] = BLACK_PAWN;
        Pos[7][G] = BLACK_PAWN;
         
        SetMoveColor(Pos, WHITE_MOVE);
        SetColumnPawnMovedTwoRows(Pos,0);                                       // Shows column of pawn that moved two steps to reach this position, 0 means that no pawn moved two steps in last move 
        
        SetWhiteLongCastling(Pos, WRA1_AND_WKE1_DID_NOT_MOVE);                  // White Long Castling possible, WRa1 and WKe1 did not move before
        SetWhiteShortCastling(Pos, WRH1_AND_WKE1_DID_NOT_MOVE);                 // White Short Castling possible, WRh8 and WKe1 did not move before
        SetBlackLongCastling(Pos, BRA8_AND_BKE8_DID_NOT_MOVE);                  // Black Long Casling possible, WRh8 and BKe8 did not move before
        SetBlackShortCastling(Pos, BRH8_AND_BKE8_DID_NOT_MOVE);                 // Black Short Castling possible, BRa8 and BKe8 did not move before
    }
    
    public static void PositionInsufficientMaterial(int[][] Pos)
    {
        ClearPosition(Pos);

        Pos[1][E] = WHITE_KNIGHT;
        Pos[8][E] = WHITE_BISHOP;
        //Pos[8][E] = WHITE_QUEEN;
        Pos[2][G] = WHITE_KING;

        //Pos[5][B] = BLACK_BISHOP;
        Pos[7][G] = BLACK_KING;

        SetMoveColor(Pos, BLACK_MOVE);
        SetColumnPawnMovedTwoRows(Pos,0);                                       // Shows column of pawn that moved two steps to reach this position, 0 means that no pawn moved two steps in last move 
         
        SetWhiteLongCastling(Pos, WRA1_OR_WKE1_DID_MOVE);                       // White Long Castling not possible, WRa1 and WKe1 did not move before
        SetWhiteShortCastling(Pos, WRH1_OR_WKE1_DID_MOVE);                      // White Short Castling not possible, WRh8 and WKe1 did not move before
        SetBlackLongCastling(Pos, BRA8_OR_BKE8_DID_MOVE);                       // Black Long Casling not possible, WRh8 and BKe8 did not move before
        SetBlackShortCastling(Pos, BRH8_OR_BKE8_DID_MOVE);                      // Black Short Castling not possible, BRa8 and BKe8 did not move before
    }
     
    public static void PositionOneMoveMate(int[][] Pos)
    {
        ClearPosition(Pos);   
        
        Pos[8][A] = WHITE_ROOK;
        Pos[1][E] = WHITE_KING;
        Pos[1][H] = WHITE_ROOK;
        Pos[2][B] = WHITE_PAWN;
        Pos[2][C] = WHITE_PAWN;
        Pos[2][D] = WHITE_PAWN;
        Pos[2][E] = WHITE_PAWN;
        Pos[2][F] = WHITE_PAWN;
        Pos[2][G] = WHITE_PAWN;

        Pos[8][A] = BLACK_ROOK;
        Pos[8][E] = BLACK_KING;
        Pos[7][B] = BLACK_PAWN;
        Pos[7][C] = BLACK_PAWN;
        Pos[7][D] = BLACK_PAWN;
        Pos[7][E] = BLACK_PAWN;
        Pos[7][F] = BLACK_PAWN;
        Pos[7][G] = BLACK_PAWN;
         
        SetMoveColor(Pos, BLACK_MOVE);
        SetColumnPawnMovedTwoRows(Pos,0);                                       // Shows column of pawn that moved two steps to reach this position, 0 means that no pawn moved two steps in last move 
        
        SetWhiteLongCastling(Pos, WRA1_AND_WKE1_DID_NOT_MOVE);                  // White Long Castling possible, WRa1 and WKe1 did not move before
        SetWhiteShortCastling(Pos, WRH1_AND_WKE1_DID_NOT_MOVE);                 // White Short Castling possible, WRh8 and WKe1 did not move before
        SetBlackLongCastling(Pos, BRA8_AND_BKE8_DID_NOT_MOVE);                  // Black Long Casling possible, WRh8 and BKe8 did not move before
        SetBlackShortCastling(Pos, BRH8_AND_BKE8_DID_NOT_MOVE);                 // Black Short Castling possible, BRa8 and BKe8 did not move before
    }
     
    public static void PositionTwoMoveMate(int[][] Pos)
    {
        ClearPosition(Pos);
        
        Pos[4][B] = WHITE_ROOK;
        Pos[2][D] = WHITE_BISHOP;
        Pos[1][F] = WHITE_KING;
        Pos[8][B] = WHITE_ROOK;
        Pos[4][A] = WHITE_PAWN;
        Pos[2][F] = WHITE_PAWN;
        Pos[2][G] = WHITE_PAWN;
        Pos[3][H] = WHITE_PAWN;

        Pos[8][A] = BLACK_ROOK;
        Pos[7][C] = BLACK_KNIGHT;
        Pos[6][A] = BLACK_KING;
        Pos[2][A] = BLACK_PAWN;
        Pos[6][C] = BLACK_PAWN;
        Pos[5][F] = BLACK_PAWN;
        Pos[5][G] = BLACK_PAWN;
        Pos[4][H] = BLACK_PAWN;
 
        SetMoveColor(Pos,WHITE_MOVE);
        SetColumnPawnMovedTwoRows(Pos,0);                                       // Shows column of pawn that moved two steps to reach this position, 0 means that no pawn moved two steps in last move 

        SetWhiteLongCastling(Pos, WRA1_OR_WKE1_DID_MOVE);                       // White Long Castling not possible, WRa1 and WKe1 did not move before
        SetWhiteShortCastling(Pos, WRH1_OR_WKE1_DID_MOVE);                      // White Short Castling not possible, WRh8 and WKe1 did not move before
        SetBlackLongCastling(Pos, BRA8_OR_BKE8_DID_MOVE);                       // Black Long Casling not possible, WRh8 and BKe8 did not move before
        SetBlackShortCastling(Pos, BRH8_OR_BKE8_DID_MOVE);                      // Black Short Castling not possible, BRa8 and BKe8 did not move before
    }
    
    public static void PositionThreeMoveMate(int[][] Pos)
    {
        ClearPosition(Pos); 
        
        Pos[3][A] = WHITE_ROOK;
        Pos[1][E] = WHITE_KNIGHT;
        Pos[2][C] = WHITE_BISHOP;
        Pos[1][C] = WHITE_QUEEN;
        Pos[2][G] = WHITE_KING;
        Pos[1][G] = WHITE_BISHOP;
        Pos[2][A] = WHITE_PAWN;
        Pos[4][B] = WHITE_PAWN;
        Pos[5][D] = WHITE_PAWN;
        Pos[4][E] = WHITE_PAWN;
        Pos[3][F] = WHITE_PAWN;

        Pos[8][A] = BLACK_ROOK;
        Pos[6][F] = BLACK_KNIGHT;
        Pos[7][D] = BLACK_BISHOP;
        Pos[7][H] = BLACK_QUEEN;
        Pos[6][G] = BLACK_KING;
        Pos[4][G] = BLACK_KNIGHT;
        Pos[7][B] = BLACK_PAWN;
        Pos[6][D] = BLACK_PAWN;
        Pos[5][G] = BLACK_PAWN;
        Pos[5][E] = BLACK_PAWN;
        Pos[4][F] = BLACK_PAWN;

        SetMoveColor(Pos, BLACK_MOVE);
        SetColumnPawnMovedTwoRows(Pos,0);                                       // Shows column of pawn that moved two steps to reach this position, 0 means that no pawn moved two steps in last move 
 
        SetWhiteLongCastling(Pos, WRA1_OR_WKE1_DID_MOVE);                       // White Long Castling not possible, WRa1 and WKe1 did not move before
        SetWhiteShortCastling(Pos, WRH1_OR_WKE1_DID_MOVE);                      // White Short Castling not possible, WRh8 and WKe1 did not move before
        SetBlackLongCastling(Pos, BRA8_OR_BKE8_DID_MOVE);                       // Black Long Casling not possible, WRh8 and BKe8 did not move before
        SetBlackShortCastling(Pos, BRH8_OR_BKE8_DID_MOVE);                      // Black Short Castling not possible, BRa8 and BKe8 did not move before 
    }
    
    public static void ClearPosition(int[][] Pos)
    {
        int row;
        int col;
      
        for (row = 0; row <= ROWS; row++)                                       // Loop over total ROWS + 1
        {   
            for (col = 0; col <= COLS; col++)                                   // Loop over total COLS + 1 
            {
                Pos[row][col] = EMPTY;
            }
        }    
    }
    
    public static void SetPosition(int[][] Pos, int Position)
    {
        BeginPosition = Position;
        
        switch(Position)   
        {
            case NEW_POSITION:
                PositionNew(Pos);
                break;
                 
            case PAWN_POSITION:
                PositionPawn(Pos);
                break;
                    
            case EN_PASSANT_POSITION:
                PositionEnPassant(Pos);    
                break;
                
            case PROMOTION_POSITION:
                PositionPromotion(Pos);                  
                break;
                    
            case CASTLING_POSITION:
                PositionCastling(Pos);                   
                break;
                    
            case INSUFFICIENT_MATERIAL_POSITION:
                PositionInsufficientMaterial(Pos);                
                break;
                
            case ONE_MOVE_MATE_POSITION:
                PositionOneMoveMate(Pos);                     
                break;     
                
            case TWO_MOVE_MATE_POSITION:
                PositionTwoMoveMate(Pos);                     
                break;
                
            case THREE_MOVE_MATE_POSITION:
                PositionThreeMoveMate(Pos);                     
                break;  
        }        
    }
    
    public static boolean ValidatePosition(int[][] Pos)
    {
        int row;
        int col;
        boolean valid = true;

        int max_WQ  =    9;
        int max_BQ  =    9;
        int max_WR  =    9;
        int max_BR  =    9;
        int max_WN =     9;
        int max_BN =     9;
        int max_WB  =    9;
        int max_BB  =    9;
        int max_WP  =    8;
        int max_BP  =    8;
        
        int WK      =    0;
        int BK      =    0;
        int WQ      =    0;
        int BQ      =    0;
        int WR      =    0;
        int BR      =    0;
        int WN      =    0;
        int BN      =    0;
        int WB      =    0;
        int BB      =    0;
        int WP      =    0;
        int BP      =    0;
    
        for (row = 1; row <= ROWS; row++)
        {
            for (col = 1; col <= COLS; col++)                                   // Get rating of position            
            {
                switch(Pos[row][col])
                {   
                    case WHITE_KING:                                            // For now rating scheme just counts value of figures, later rating scheme can be more sophisticated
                        WK += 1;
                        break;
                        
                    case WHITE_QUEEN:
                        WQ += 1;
                        break;
                        
                    case WHITE_ROOK:
                        WR += 1;
                        break;
                        
                    case WHITE_KNIGHT:
                        WN += 1;
                        break;
                        
                    case WHITE_BISHOP:
                        WB += 1;
                        break;
                        
                    case WHITE_PAWN:
                        WP += 1;
                        break;
                    
                    case BLACK_KING:
                        BK += 1;
                        break;
                        
                    case BLACK_QUEEN:
                        BQ += 1;
                        break;
                        
                    case BLACK_ROOK:
                        BR += 1;
                        break;
                        
                    case BLACK_KNIGHT:
                        BN += 1;
                        break;
                        
                    case BLACK_BISHOP:
                        BB += 1;
                        break;
                        
                    case BLACK_PAWN:
                        BP += 1;
                        break;
                }
            }
        }
        
        if (WK != 1)
        {   
            System.out.println("Nonvalid position: Number of White Kings is " + WK + ". Need one White King.");
            valid = false;
        }
        if (WQ > max_WQ)
        {   
            System.out.println("Nonvalid position: Number of White Queens is " + WQ + " and exceeds allowed Number of White Queens of " + max_WQ);
            valid = false;
        }
        if (WR > max_WR)
        {   
            System.out.println("Nonvalid position: Number of White Rooks is " + WR + " and exceeds allowed Number of White Rookss of " + max_WR);
            valid = false;
        }
        if (WN > max_WN)
        {   
            System.out.println("Nonvalid position: Number of White Kights is " + WN + " and exceeds allowed Number of White Knigths of " + max_WN);
            valid = false;
        }
        if (WK > max_WB)
        {   
            System.out.println("Nonvalid position: Number of White Bishops is " + WB + " and exceeds allowed Number of White Bishops of " + max_WB);
            valid = false;
        }
        if (WP > max_WP)
        {   
            System.out.println("Nonvalid position: Number of White Pawns is " + WP + " and exceeds allowed Number of White Pawns of " + max_WP);
            valid = false;
        }
              
        if (BK != 1)
        {   
            System.out.println("Nonvalid position: Number of Black Kings is " + BK + ". Need one Black King. ");
            valid = false;
        } 
        if (BQ > max_BQ)
        {   
            System.out.println("Nonvalid position: Number of Black Queens is " + BQ + " and exceeds allowed Number of Black Queens of " + max_BQ);
            valid = false;
        }
        if (BR > max_BR)
        {   
            System.out.println("Nonvalid position: Number of Black Rooks is " + BR + "and exceeds allowed Number of Black Rookss of " + max_BR);
            valid = false;
        }
        if (BN > max_BN)
        {   
            System.out.println("Nonvalid position: Number of Black Kights is " + BN + " and exceeds allowed Number of Black Knigths of " + max_BN);
            valid = false;
        }
        if (BB > max_BB)
        {   
            System.out.println("Nonvalid position: Number of Black Bishops is " + BB + " and exceeds allowed Number of Black Bishops of " + max_BB);
            valid = false;
        }
        if (BP > max_BP)
        {   
            System.out.println("Nonvalid position: Number of Black Pawns is " + BP + " and exceeds allowed Number of White Pawns of " + max_BP);
            valid = false;
        }
        
        return(valid);
    }   
    
    public static void DisplayPosition(int[][] Pos)
    {
        int row;
        int col;
        int Type;

        System.out.print ("\n");
        for (row = ROWS; row > 0; row--)
        {
            System.out.print(row + " ");
            for (col = 1; col <= COLS; col++)
            {
                switch(Pos[row][col])
                {   
                    case WHITE_KING:
                        System.out.print(" WK ");
                        break;
                    case WHITE_QUEEN:
                        System.out.print(" WQ ");
                        break;
                    case WHITE_ROOK:
                        System.out.print(" WR ");
                        break;
                    case WHITE_KNIGHT:
                        System.out.print(" WN ");
                        break;
                    case WHITE_BISHOP:
                        System.out.print(" WB ");
                        break;
                    case WHITE_PAWN:
                        System.out.print(" WP ");
                        break;
                    case BLACK_KING:
                        System.out.print(" BK ");
                        break;
                    case BLACK_QUEEN:
                        System.out.print(" BQ ");
                        break;
                    case BLACK_ROOK:
                        System.out.print(" BR ");
                        break;
                    case BLACK_KNIGHT:
                        System.out.print(" BN ");
                        break;
                    case BLACK_BISHOP:
                        System.out.print(" BB ");
                        break;
                    case BLACK_PAWN:
                        System.out.print(" BP ");
                        break;
                    case EMPTY:
                        System.out.print(" -- ");
                        break;
                }
            }
            System.out.print ("\n");
        }

        System.out.print("   ");
        for (col = 1; col <= COLS; col++)
        {
            switch (col)
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
        
        if (Chess.ShowStatus > Settings.HIGH)
        { 
            if (GetColumnPawnMovedTwoRows(Pos) > 0)
            {
                System.out.println ("Pawn moved two steps in column " + GetColumnPawnMovedTwoRows(Pos));
            }
            if (GetWhiteLongCastling(Pos)  == 0)
            {
                System.out.println ("White Long Castling possible since WRa1 and WKe1 have not moved yet");
            }
            if (GetWhiteShortCastling(Pos) == 0)
            {
                System.out.println ("White Short Castling possible since WRa8 and WKe1 have not moved yet");
            }
            if (GetBlackLongCastling(Pos)  == 0)
            {
                System.out.println ("Black Long Castling possible since BRa8 and BKe8 have not moved yet");
            }
            if (GetBlackShortCastling(Pos)  == 0)
            {
                System.out.println ("Black Short Castling possible since BRa8 and BKe8 have not moved yet");
            }
        }
        System.out.println();
        ShowNumberOfRepetitivePositions(Pos);          
        ShowFiftyMoveCounter(Pos);
        Rating.ShowPositionType(Rating.GetPositionType(Pos));
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
        Pos[ROW_STORE_MOVE_COLOR][COLUMN_STORE_MOVE_COLOR] = color;       
    }
    
    public static int GetMoveColor(int[][]Pos)
    {
        return Pos[Position.ROW_STORE_MOVE_COLOR][Position.COLUMN_STORE_MOVE_COLOR];        
    } 
        
    public static void SwitchMoveColor(int Pos[][])
    {
        switch (GetMoveColor(Pos))
        {
              case WHITE_MOVE:
                SetMoveColor(Pos, BLACK_MOVE);
                break;
                
              case BLACK_MOVE:
                SetMoveColor(Pos, WHITE_MOVE);
                break;
        }
    }
    
    public static void DisplayMoveColor(int Pos[][])
    {
        switch (GetMoveColor(Pos))
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
        Pos[ROW_STORE_WHITE_LONG_CASTLING][COLUMN_STORE_WHITE_LONG_CASTLING] = set;
    }
        
    public static int GetWhiteLongCastling(int[][]Pos)
    {
        return(Pos[ROW_STORE_WHITE_LONG_CASTLING][COLUMN_STORE_WHITE_LONG_CASTLING]);
    }
    
    public static void SetWhiteShortCastling(int[][]Pos, int set)
    {
        Pos[ROW_STORE_WHITE_SHORT_CASTLING][COLUMN_STORE_WHITE_SHORT_CASTLING] = set;
    }   
        
    public static int GetWhiteShortCastling(int[][]Pos)
    {
        return(Pos[ROW_STORE_WHITE_SHORT_CASTLING][COLUMN_STORE_WHITE_SHORT_CASTLING]);
    }  
    
    public static void SetBlackLongCastling(int[][]Pos, int set)
    {
        Pos[ROW_STORE_BLACK_LONG_CASTLING][COLUMN_STORE_BLACK_LONG_CASTLING] = set;
    }
            
    public static int GetBlackLongCastling(int[][]Pos)
    {
        return(Pos[ROW_STORE_BLACK_LONG_CASTLING][COLUMN_STORE_BLACK_LONG_CASTLING]);
    }
    
    public static void SetBlackShortCastling(int[][]Pos, int set)
    {
        Pos[ROW_STORE_BLACK_SHORT_CASTLING][COLUMN_STORE_BLACK_SHORT_CASTLING] = set;
    }
    
    public static int GetBlackShortCastling(int[][]Pos)
    {
        return(Pos[ROW_STORE_BLACK_SHORT_CASTLING][COLUMN_STORE_BLACK_SHORT_CASTLING]);
    }
    
    public static void SetColumnPawnMovedTwoRows(int[][] Pos, int column)
    {
        Pos[ROW_STORE_EN_PASSANT][COLUMN_STORE_EN_PASSANT] = column;           
    } 
    
    public static int GetColumnPawnMovedTwoRows(int[][] Pos)
    {
        return Pos[ROW_STORE_EN_PASSANT][COLUMN_STORE_EN_PASSANT];        
    } 
    
    public static void SetNumberOfMovesWithNoPawnMoveOrCapture(int[][] Pos, int set)
    {
        Pos[ROW_STORE_FIFTY_MOVE][COLUMN_STORE_FIFTY_MOVE] = set;    
    } 
    
    public static void SetNumberOfRepetitivePositions(int[][] Pos, int set)
    {
        Pos[ROW_STORE_NUMBER_OF_REPETITIVE_POSITIONS][COLUMN_STORE_NUMBER_OF_REPETITIVE_POSITIONS] = set;    
    }       
    
    public static int GetNumberOfRepetitivePositions(int[][] Pos)
    {
        return Pos[ROW_STORE_NUMBER_OF_REPETITIVE_POSITIONS][COLUMN_STORE_NUMBER_OF_REPETITIVE_POSITIONS];  
    }     
    
    public static void IncrementNumberOfMovesWithNoPawnMoveOrCapture(int[][] Pos)
    {
        int set;
        Pos[ROW_STORE_FIFTY_MOVE][COLUMN_STORE_FIFTY_MOVE]++;      
    }        
    
    public static int GetNumberOfMovesWithNoPawnMoveOrCapture(int[][] Pos)
    {
        return Pos[ROW_STORE_FIFTY_MOVE][COLUMN_STORE_FIFTY_MOVE];        
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
                if(OpponentKing(Pos, col_K, row_K))
                {
                    break loop;                                                 // Found row and column of opponent king
                }
            }
        }
        //System.out.println("row_K = " + row_K + " col_K = " + col_K);

        loop_over_own_figures:
        for(row = 1; row <= ROWS; row++)                                        // Go through all fields and find own figure
        {
            for(col = 1; col <= COLS; col++)
            {
                if(OwnFigure(Pos, col, row))                                    // Found own figure
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
                                CanTakeKing = true;                                    // Rook can capture king
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_KNIGHT:
                        case BLACK_KNIGHT:
                            if(((Math.abs(col - col_K) == 2) && (Math.abs(row - row_K) == 1)) ||
                               ((Math.abs(col - col_K) == 1) && (Math.abs(row - row_K) == 2)))
                            {
                                CanTakeKing = true;                                    // Knight can capture king
                                break loop_over_own_figures;
                            }
                            break;
              
                        case WHITE_BISHOP:
                        case BLACK_BISHOP:
                            if(BishopCanCaptureKing(Pos, row, col, row_K, col_K))
                            {
                                CanTakeKing = true;                                    // Bishop can capture king  
                                break loop_over_own_figures;
                            }
                            break;
                            
                        case WHITE_QUEEN:
                        case BLACK_QUEEN:
                            if((BishopCanCaptureKing(Pos, row, col, row_K, col_K)) ||                           
                               (RookCanCaptureKing  (Pos, row, col, row_K, col_K)))
                            {                                                   // Queen moves are the sum of rook or bishop moves
                                CanTakeKing = true;                                    // Queen can capture king 
                                break loop_over_own_figures;
                            }
                            break;
                        
                        case WHITE_KING:
                        case BLACK_KING:
                            if(((Math.abs(col - col_K) == 1) || (Math.abs(col - col_K) == 0)) &&
                               ((Math.abs(row - row_K) == 1) || (Math.abs(row - row_K) == 0)))
                            {
                                CanTakeKing = true;                                    // King can capture king 
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
        int row_s = 0;                                                          // row_s is row step
        int col_s = 0;                                                          // col_s is col step
        
        if((row == row_K) || (col == col_K))                                    // King is on rook's row or column
        {                                                                       // Identify direction of rook's move towards king
            if(col < col_K)                                                     // King is on rook's row
            {
                col_s = 1;                                                      // Step column up to reach opponent king
            }
            if(col > col_K)                                                     // Do not use else here since if would change col_s for col == col_K
            {
                col_s = -1;                                                     // Step column down to reach opponent king
            }
            
            if(row < row_K)                                                     // King is on rook's column
            {
                row_s = 1;                                                      // Step row up to reach opponent king
            }
            if(row > row_K)                                                     // Do not use else here since if would change row_s for rol == row_K
            {
                row_s = -1;                                                     // Step row down to reach opponent king
            }            
            return(CanCaptureKingMovingInThisDirection(Pos, row, col, row_s, col_s, row_K, col_K));
        }
        return false;                                                           // King is not on one rook's ow or column
    }
        
    public static boolean BishopCanCaptureKing(int[][] Pos, int row, int col, int row_K, int col_K)
    {
        int row_s = 1;                                                          // row_s is row step
        int col_s = 1;                                                          // col_s is col step
        
        if(Math.abs(row - row_K) == Math.abs(col - col_K))                      // King is on one Bishop diagonal
        {                                                                       // Identify direction of bishop move towards king
            //System.out.println("Bishop is on diagonale");
            if(col > col_K)
            {
                col_s = -1;
            }
            if(row > row_K)
            {
                row_s = -1;
            }
            return(CanCaptureKingMovingInThisDirection(Pos, row, col, row_s, col_s, row_K, col_K));
        }
        return false;                                                           // King is not on one Bishop diagonal
    }
    
    public static boolean CanCaptureKingMovingInThisDirection(int[][] Pos, int row, int col, int row_s, int col_s, int row_K, int col_K)
    {
        int i;

        for(i = 1; Pos[row + i * row_s][col + i * col_s] == EMPTY; i++)         // Move in this direction until reaching an occupied field
        {     
        } 
        return(((row + i * row_s) == row_K) && ((col + i * col_s) == col_K));  
    }
    
    public static boolean AnyMovePossible(int[][] Pos)  
    {
        int temp_figure;
        int temp_figure_1;
        int temp_ep;
        int[] Castling                  = new int[4];
        int[] CastlingList              = new int[2];
        int list;
        boolean CastlingPossible;
        int col;
        int col_n;
        int row;
        int row_n;
        int i;
        int j;
        int step;
        int p;
        int factor;
        int dir;         
        int RookColumn                  = EMPTY;
        int NewRookColumn               = EMPTY;            
        
        col_n = 0;                                                              // To make complier happy
        row_n = 0;                                                              // To make complier happy
        step = GetMoveColor(Pos);
            
        //System.out.println("Entering noLegalMove() Move Color = " + GetMoveColor(Pos));
        
        for (row = 1; row <= Position.ROWS; row++)
        {
            for (col = 1; col <= COLS; col++)
            {
                if (Chess.DebugLevel > Settings.MEDIUM)
                {
                    System.out.println("Col = " + col + " Row = " + row + " Move no: " + Chess.Total_Move_Counter + " Pos[row][col] = " + Pos[row][col]);
                    System.out.println("GetMoveColor(Pos)  = " + GetMoveColor(Pos));
                }
                if (Pos[row][col]*GetMoveColor(Pos) > 0)                        // Move WHITE figure if GetMoveColor(Pos) == WHITE, Move BLACK figure if GetMoveColor(Pos) == BLACK
                {
                    switch (Pos[row][col])
                    {
                        case WHITE_PAWN:
                        case BLACK_PAWN:
                        
                            if (Pos[row + step][col] == Position.EMPTY)         // Move Pawn one field forward
                            {
                                col_n = col;
                                row_n = row + step;
                                if (Chess.DebugLevel > Settings.MEDIUM)
                                {
                                    System.out.println("row+step=  = " + row_n);
                                }
                                if (row_n == Position.ROWS || row_n == 1)       // Convert Pawn
                                {
                                    for (p = 5; p > 1; p--)                     // Loop for Queen, Rook, Knight, Bishop
                                    {
                                        temp_figure = Pos[row][col];
                                        Pos[row][col] = p*GetMoveColor(Pos);            
                                        if (Chess.DebugLevel > Settings.MEDIUM)
                                        {
                                            System.out.println("Checking Pawn move one step to col = " + col_n + "\t row = " + row_n);
                                        }

                                        if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                        {
                                            Pos[row][col]= temp_figure;
                                            return true;
                                        } 

                                        Pos[row][col]= temp_figure;
                                    }
                                }
                                else
                                {                                   
                                    if (Chess.DebugLevel > Settings.MEDIUM)
                                    {
                                        System.out.println("Checking Pawn move one step to col = " + col + "\t row = " + row_n);
                                    }
                                    if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                    {
                                        return true;
                                    } 
                                }
                            }
               
                            col_n = col;
                            row_n = row + 2*step;                               // Move two steps forward
                            if ((GetMoveColor(Pos) == WHITE_MOVE&& row == 2) || (GetMoveColor(Pos) == BLACK_MOVE&& row == 7))
                            {
                                if ((Pos[row+step][col] == Position.EMPTY) && (Pos[row_n][col] == Position.EMPTY))         
                                {                                               // Move Pawn two fields forward

                                    if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                    {
                                        return true;
                                    }  

                                }
                            }
                            
                            for (i = -1; i < 2; i += 2)                         // Pawn takes opponent figure away
                            {
                                col_n = col + i;
                                row_n = row + step;
                                if ((row_n == Position.ROWS || row_n == 1) && col_n > 0 && col_n < COLS+1 && Pos[row_n][col_n]*GetMoveColor(Pos) < 0)   
                                {                                               // Take away opponent figure
                         
                                    for (p = 5; p > 1; p--)                     // .... and convert to new officer, Loop for Queen, Rook, Knight, Bishop
                                    {
                                        temp_figure = Pos[row][col];
                                        temp_figure_1 = Pos[row_n][col_n];
                                        Pos[row][col] = p*GetMoveColor(Pos);
                                        
                                        if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                        {
                                            Pos[row][col]= temp_figure;
                                            Pos[row_n][col_n] = temp_figure_1;
                                            return true;
                                        }  
                                        
                                        Pos[row][col]= temp_figure;
                                        Pos[row_n][col_n] = temp_figure_1;
                                    }
                                }
                                
                                if (row_n < Position.ROWS && row_n > 1 && col_n > 0 && col_n < COLS+1 && Pos[row_n][col_n]*GetMoveColor(Pos) < 0) 
                                {                                               // Take away opponent figure
                                    
                                    if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                    {
                                        return true;
                                    }
                                }

                                if ((GetMoveColor(Pos) == WHITE_MOVE&& row == 5) || (GetMoveColor(Pos) == BLACK_MOVE&& row == 4) && col_n > 0 && col_n <= COLS)   // En Passant
                                {                                                                                                 
                                   if (col_n == GetColumnPawnMovedTwoRows(Pos)) //Workaround: Compiler ssems to miss this if this is added to line before
                                    {
                                        if(Chess.DebugLevel == Settings.HIGH)
                                        {
                                            System.out.println("En Passant");
                                        }
                                        temp_figure = Pos[row][col_n];
                                        Pos[row][col_n] = Position.EMPTY;
                                        Pos[row_n][col_n] = temp_figure;        // Only to have EvaluateMove detect that a figure was taken away and 
                                                                                // have noation show it 
                                        if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                        {
                                            Pos[row][col_n] = temp_figure;  
                                            Pos[row_n][col_n] = Position.EMPTY;
                                            return true;
                                        } 
                                                                               
                                        Pos[row][col_n] = temp_figure;  
                                        Pos[row_n][col_n] = Position.EMPTY;
                                    }
                                }
                            }                    
                            break;
               
                        case WHITE_ROOK:
                        case BLACK_ROOK:
               
                            for (dir = 0; dir < 4; dir ++)                      // Loop over all four directions
                            {
                                for (i = 1; i < COLS; i++)                      // Loop over all steps in one direction         
                                {
                                    switch (dir)
                                    {
                                        case 0:                                 // Move N
                                            col_n = col;           
                                            row_n = row + i;
                                            break;
                                            
                                        case 1:                                 // Move E
                                            col_n = col + i;          
                                            row_n = row;
                                            break;
                                            
                                        case 2:                                 // Move S
                                            col_n = col;
                                            row_n = row - i;
                                            break;
                                            
                                        case 3:                                 // Move W
                                            col_n = col - i;
                                            row_n = row;
                                            break;
                                    }
                                        
                                    if (col_n < 1 || col_n > COLS || row_n < 1 || row_n > Position.ROWS || Pos[row_n][col_n]*GetMoveColor(Pos) > 0) 
                                        break;                                  // Reached boundry or own figure
                                                       
                                    if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                    {
                                        return true;
                                    } 
                                    
                                    if (Pos[row_n][col_n]* GetMoveColor(Pos) < 0)                               
                                        break;                                  // Took opponent figure away, stop to moving in this direction
                                }
                            }
                            break;
               
                        case WHITE_KNIGHT:
                        case BLACK_KNIGHT:

                            for(i = -2; i < 3; i++)                             // Loop over 5 column steps, column step
                            {
                                j = -1;                                         // initialize j to makle compiler happy
                                
                                if (i == 0)                                     // skip column step 0
                                {
                                    continue;
                                }
                                if (i == -2 || i == 2)                          // set row step to 1
                                    j = 1;
                                if (i == -1 || i == 1)                          // set row step to 2
                                    j = 2;

                                for (factor = -1; factor < 2; factor += 2)      // Loop over two rows
                                {
                                    col_n = col + i;
                                    row_n = row + j * factor;
                                    if (col_n > 0 && col_n <= COLS && row_n <= Position.ROWS  && row_n > 0 && Pos[row_n][col_n]*GetMoveColor(Pos) < 1)   // exclude column step 0
                                    {
                                        if (Chess.DebugLevel > Settings.MEDIUM)
                                        {
                                            System.out.println("Checking Knight move to col = " + col_n + "\t row = " + row_n);
                                        }
                                        if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                        {
                                            return true;
                                        }
                                    }
                                }
                            }                          
                            break;
               
                        case WHITE_BISHOP:
                        case BLACK_BISHOP:
          
                            for (dir = 0; dir < 4; dir ++)                      // Loop over all four directions
                            {
                                for (i = 1; i< COLS; i++)                       // Loop over all steps in one direction         
                                {
                                    switch (dir)
                                    {
                                        case 0:                                 // Move NE
                                            col_n = col + i;           
                                            row_n = row + i;
                                            break;
                                            
                                        case 1:                                 // Move SE
                                            col_n = col + i;          
                                            row_n = row - i;
                                            break;
                                            
                                        case 2:                                 // Move SW
                                            col_n = col - i;
                                            row_n = row - i;
                                            break;
                                            
                                        case 3:                                 // Move NW
                                            col_n = col - i;
                                            row_n = row + i;
                                            break;
                                    }
                                        
                                    if (col_n < 1 || col_n > COLS || row_n < 1 || row_n > Position.ROWS || Pos[row_n][col_n]*GetMoveColor(Pos) > 0) // Reached boundry or own figure
                                        break;
                                    if (Chess.DebugLevel > Settings.MEDIUM)
                                    {
                                        System.out.println("Checking Bishop move to col = " + col_n + "\t row = " + row_n);                                    
                                    }
                                                                          
                                    if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                    {
                                        return true;
                                    }    

                                    if (Pos[row_n][col_n]* GetMoveColor(Pos) < 0)                           // Took oponent figure away, stop to moving in this direction
                                        break;
                                }
                            }  
                            break;
               
                        case WHITE_QUEEN:
                        case BLACK_QUEEN:
                                
                            for (dir = 0; dir < 8; dir ++)                      // Loop over all eight directions
                            {
                                for (i = 1; i < COLS; i++)                      // Loop over all steps in one direction         
                                {
                                    switch (dir)
                                    {
                                        case 0:                                 // Move N
                                            col_n = col;           
                                            row_n = row + i;
                                            break;
                                            
                                        case 1:                                 // Move NE
                                            col_n = col + i;          
                                            row_n = row + i;
                                            break;
                                            
                                        case 2:                                 // Move E
                                            col_n = col + i;
                                            row_n = row;
                                            break;
                                            
                                        case 3:                                 // Move SE
                                            col_n = col + i;
                                            row_n = row - i;
                                            break;
                                            
                                        case 4:                                 // Move S
                                            col_n = col;           
                                            row_n = row - i;
                                            break;
                                            
                                        case 5:                                 // Move SW
                                            col_n = col - i;          
                                            row_n = row - i;
                                            break;
                                            
                                        case 6:                                 // Move W
                                            col_n = col - i;
                                            row_n = row;
                                            break;
                                            
                                        case 7:                                 // Move NW
                                            col_n = col - i;
                                            row_n = row + i;
                                            break;                                           
                                    }
                                        
                                    if (col_n < 1 || col_n > COLS || row_n < 1 || row_n > Position.ROWS || Pos[row_n][col_n]*GetMoveColor(Pos) > 0) // Reached boundry or own figure
                                        break;
                                    if (Chess.DebugLevel > Settings.MEDIUM)
                                    {
                                        System.out.println("Checking Queen move to col = " + col_n + "\t row = " + row_n);
                                    }                                   

                                    if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                    {
                                        return true;
                                    }    

                                    if (Pos[row_n][col_n]* GetMoveColor(Pos) < 0)         // Took oponent figure away, stop to moving in this direction
                                        break;
                                }
                            }  
                            break;
               
                        case WHITE_KING:
                        case BLACK_KING:
                                       
                            for (i=-1; i < 2 ; i++)                             // loop over three columns
                            {     
                                for (j=-1; j < 2 ; j++)                         // loop over three rows
                                {
                                    col_n = col+i;
                                    row_n = row+j;

                                    if ((i != 0 || j != 0) && col_n > 0 && col_n < COLS+1 && row_n > 0 && row_n < Position.ROWS+1 && Pos[row_n][col_n]*GetMoveColor(Pos) < 1)
                                    {
                                       if (Chess.DebugLevel > Settings.MEDIUM)
                                       {
                                           System.out.println("Message from King move to col = " + col_n + "\t row = " + row_n);
                                       }               
                                       if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                       {
                                           return true;
                                       }    
                                   }
                                }
                            }
                            // Add 0-0 and 0-0-0
                            
                            list = (Move.Castling(Pos, CastlingList));
                            //System.out.println("Performing Castling."); 
                            for (i = 0; i < list; i++)
                            {
                                //System.out.println("Performing Castling."); 
                                //Position.DisplayPosition(Pos);
                                switch(CastlingList[i])
                                {
                                    case Position.LONG_CASTLING:
                                        RookColumn      = Position.A;
                                        NewRookColumn   = Position.D;
                                        col_n           = Position.C;           //For King
                                        break;
                                        
                                    case Position.SHORT_CASTLING:
                                        RookColumn      = Position.H;
                                        NewRookColumn   = Position.F;
                                        col_n           = Position.G;           //For King
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

                                Pos[row_n][RookColumn]   = Pos[row][RookColumn];
                                Pos[row][RookColumn]        = EMPTY;                                
                                
                                if (MovePossible(Pos, col, row, col_n, row_n) == true)
                                {
                                    return true;
                                }

                                Pos[row_n][RookColumn]      = Pos[row][NewRookColumn];
                                Pos[row][NewRookColumn]     = EMPTY;                                 
                            }
                            break;        
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean MovePossible(int[][] Pos, int col, int row, int col_n, int row_n)
    {
        int temp_figure;
        boolean check;

        //System.out.println("Entering MovePossible(): Checking if opponent could take King in the next move...");
        
        temp_figure = Pos[row_n][col_n];                                        // Save opponent figure on move to field, to be able to restore the orginal position before returning
        Pos[row_n][col_n] = Pos[row][col];                                      // Make move
        Pos[row][col] = Position.EMPTY;                                         // Set leaving field to empty  

        if (Chess.DebugLevel > Settings.MEDIUM)
        {
            System.out.println("MovePossible: Checking if opponent could take King in the next move...");
        } 

        check = Check(Pos, RECEIVING_CHECK);                                    // Check if King could be captured in the next move

        Pos[row][col] = Pos[row_n][col_n];
        Pos[row_n][col_n] = temp_figure;
        
        return(!check);
    }    
    
    public static boolean InsufficientMaterial(int[][] Pos)                     // Detect Draw for not enough material on field    
    {
        int col;
        int row;
        int NoWhiteKnights = 0;
        int NoBlackKnights = 0;
        int NoWhiteBishopsBlackField = 0;
        int NoWhiteBishopsWhiteField = 0;
        int NoBlackBishopsBlackField = 0;
        int NoBlackBishopsWhiteField = 0;
            
        for (row = 1; row <= Position.ROWS; row++)      
        {
            for (col = 1; col <= COLS; col++)                          
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
                        NoWhiteKnights +=1;
                        if (NoWhiteKnights > 1)
                        {
                            return false;
                        }
                        break;
                            
                    case BLACK_KNIGHT:
                        NoBlackKnights +=1;
                        if (NoBlackKnights > 1)
                        {
                            return false;
                        }     
                        break;
                        
                    case WHITE_BISHOP:
                        if ((((col % 2 ) == 1) && ((row % 2 ) == 1)) || (((col % 2) ==0) && ((row %2 ) == 0)))
                        {
                            NoWhiteBishopsBlackField +=1;
                        }
                        else
                        {
                            NoWhiteBishopsWhiteField +=1;
                        }
                        if ((NoWhiteBishopsBlackField > 0) && (NoWhiteBishopsWhiteField > 0))
                        {
                            return false;
                        }
                        break;
                            
                    case BLACK_BISHOP:
                        if ((((col % 2 ) == 1) && ((row % 2 ) == 1)) || (((col % 2) ==0) && ((row %2 ) == 0)))
                        {
                            NoBlackBishopsBlackField +=1;
                        }
                        else
                        {
                            NoBlackBishopsWhiteField +=1;
                        }
                        if ((NoBlackBishopsBlackField > 0) && (NoBlackBishopsWhiteField > 0))
                        {
                            return false;
                        }
                        break;
                }
            }
        }
        if ((NoWhiteKnights == 1) && ((NoWhiteBishopsWhiteField > 0) || (NoWhiteBishopsBlackField > 0)))
        {
            return false;       
        }
        if ((NoBlackKnights == 1) && ((NoBlackBishopsWhiteField > 0) || (NoBlackBishopsBlackField > 0)))
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
    
    public static int GetPositionStatus(int[][] Pos, int[][] MoveList)
    {
        int Status = NO_CONDITION;     
        
        SwitchMoveColor(Pos);   
        if (!AnyMovePossible(Pos))
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
        
        if (InsufficientMaterial(Pos))
        {
            Status = INSUFFICIENT_MATERIAL;
        }    
        
        if (ThreePositionRepetition(Pos, MoveList))
        {
            Status = THREE_POSITION_REPETITION;
        }        
        
        if (FiftyMove(Pos))
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
    
    public static boolean EndPosition(int[][] Pos, int[][] MoveHistory, boolean show)
    {
        //System.out.println("Entering EndPosition()... Move Color = " + GetMoveColor(Pos));

        if(Checkmate(Pos, show) || Draw(Pos, MoveHistory, show))
        {
           if(show)
           {
               System.out.println("End position");
           }
           return true;
        }
        return false;
    }
    
    public static boolean Checkmate(int[][] Pos, boolean ShowPositionStatus)
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
            DisplayPosition(Pos);
        
            if(AnyMovePossible(Pos) == true)
                System.out.println("In Checkmate(): AnyMovePossible = true");
        
            if(AnyMovePossible(Pos) == false)
                System.out.println("In Checkmate(): AnyMovePossible = false");
        
            if(Check(Pos, RECEIVING_CHECK) == true)
                System.out.println("In Checkmate(): ReceivingCheck(Pos) = true");
            
            if(Check(Pos, RECEIVING_CHECK) == false)
                System.out.println("In Checkmate(): ReceivingCheck(Pos) = false");
        }
            
        if(!AnyMovePossible(Pos) && Check(Pos, RECEIVING_CHECK))
        {
            if (ShowPositionStatus)
            {
                System.out.println("Checkmate!");
            }
            return true;
        }       
        return false;
    }
    
    public static boolean Draw(int[][] Pos, int[][] MovePath, boolean show)
    {   
        if (Stalemate(Pos, show) || InsufficientMaterial(Pos) || ThreePositionRepetition(Pos, MovePath) || FiftyMove(Pos))
        {
            if (show)
            {
                System.out.println("Draw!");
            }
            return true;
        }        
        return false;
    }
    
    public static boolean Stalemate(int[][] Pos, boolean show)
    {
        //if((NoLegalMove(Pos) == true) && (ReceivingCheck(Pos) == false))
        if((!AnyMovePossible(Pos) == true) && (Check(Pos, RECEIVING_CHECK) == false))        
        {
            if (show)
            {
                System.out.println("Stalemate!");
            }
            return true;
        }
        return false;
    }

    public static boolean ThreePositionRepetition(int[][] Pos, int[][] MovePath)
    {   
        return(Move.RepetitivePositions(MovePath) >= Move.NUMBER_REPETIVE_POSITIONS_TO_CAUSE_DRAW);
    }         

    public static boolean FiftyMove(int[][] Pos)
    {
        return(GetNumberOfMovesWithNoPawnMoveOrCapture(Pos) >= NUMBER_OF_MOVES_WITH_NO_PAWN_MOVE_OR_CAPTURE_REQUIRED_TO_DECLARE_DRAW);
    }
    
    public static boolean OwnFigure(int[][] Pos, int col, int row)
    {                               
        int i;
        
        for (i = 0; i < Move.WhiteFigure.length; i++)
        {    
            switch (GetMoveColor(Pos))
            {
                case WHITE_MOVE:
                    if (Pos[row][col] == Move.WhiteFigure[i])
                    {
                        return true;
                    }
                    break;
            
                case BLACK_MOVE:
                    if (Pos[row][col] == Move.BlackFigure[i])
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
        
        for (i = 0; i < Move.WhiteFigure.length; i++)
        {    
            if (Figure == Move.WhiteFigure[i])
            {
                return WHITE_FIGURE;
            }
        }
        return BLACK_FIGURE;
    }  
    
    public static boolean OpponentFigure(int[][] Pos, int col_n, int row_n)
    {                               
        int i;

        for (i = 0; i < Move.WhiteFigure.length; i++)
        {    
            switch (GetMoveColor(Pos))
            {
                case WHITE_MOVE:
                    if (Pos[row_n][col_n] == Move.BlackFigure[i])
                    {
                        return true;
                    }
                    break;
            
                case BLACK_MOVE:
                    if (Pos[row_n][col_n] == Move.WhiteFigure[i])
                    {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }
    
    public static boolean OpponentKing(int[][] Pos, int col_n, int row_n)
    {                               
        switch (GetMoveColor(Pos))
        {
            case WHITE_MOVE:
                if (Pos[row_n][col_n] == BLACK_KING)
                {
                    return true;
                }
                break;
            
            case BLACK_MOVE:
                if (Pos[row_n][col_n] == WHITE_KING)
                {
                    return true;
                }
                break;
        }
        return false;
    }
      
    public static boolean OffBoardOrOwnFigure(int[][] Pos, int col_n, int row_n)
    {        
        int i;
        
        if (OnBoard(col_n, row_n))
        {
            if (Pos[row_n][col_n] == EMPTY)
            {
                return false;
            }
            for (i = 0; i < Move.WhiteFigure.length; i++)
            {    
                switch (GetMoveColor(Pos))
                {
                    case WHITE_MOVE:
                        if (Pos[row_n][col_n] == Move.WhiteFigure[i])
                        {
                            return true;
                        }
                        break;
            
                    case BLACK_MOVE:
                        if (Pos[row_n][col_n] == Move.BlackFigure[i])
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
    
    public static boolean OnBoard(int col_n, int row_n)
    {                               
        return((col_n >= 1) && (col_n <= COLS) && (row_n >= 1) && (row_n <= ROWS));    
    }
    
    public static void StoreCastling(int[] CastlingLocal, int[][] Pos)
    {
        CastlingLocal[0] = GetWhiteLongCastling(Pos);
        CastlingLocal[1] = GetWhiteShortCastling(Pos);
        CastlingLocal[2] = GetBlackLongCastling(Pos);
        CastlingLocal[3] = GetBlackShortCastling(Pos);
    }
    
    public static void RestoreCastling(int[] CastlingLocal, int[][] Pos)
    {
        SetWhiteLongCastling(Pos, CastlingLocal[0]);
        SetWhiteShortCastling(Pos, CastlingLocal[1]);
        SetBlackLongCastling(Pos, CastlingLocal[2]);
        SetBlackShortCastling(Pos, CastlingLocal[3]);
    }

    public static void GenerateCandidateMoveList(int[][] Pos, int[][] MoveList)
    {
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

        for (FieldNo = 0; FieldNo < (ROWS * COLS); FieldNo++)
        {
            col         = (FieldNo % COLS) + 1;
            row         = (FieldNo / ROWS) + 1 ;
            Figure      = Pos[row][col];
            
            if(OwnFigure(Pos, col, row) == false)
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
                    if (Pos[row_n][col_n] == Position.EMPTY)                                                    
                    {
                        if ((row_n == WHITE_PAWN_PROMOTION_ROW) || (row_n == BLACK_PAWN_PROMOTION_ROW))                        
                        {                                                   // Convert Pawn
                            for (i = 0; i < WhitePromotionFigure.length; i++)    
                            {                                               // Loop for Queen, Rook, Knight, Bishop
                                switch (GetMoveColor(Pos))
                                {
                                    case WHITE_MOVE:
                                        Figure_n = WhitePromotionFigure[i];
                                        break;

                                    case BLACK_MOVE:
                                        Figure_n = BlackPromotionFigure[i];
                                        break;
                                }
                                Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure_n, col_n, row_n);                                                               
                            }
                        }
                        else
                        {
                            Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);                            
                        }
                    }
                    
                    col_n = col;        
                    row_n = row + 2 * PawnStep;                             // Move two PawnSteps forward
                    if (((GetMoveColor(Pos) == WHITE_MOVE) && (row == WHITE_PAWN_INITIAL_ROW)) || 
                        ((GetMoveColor(Pos) == BLACK_MOVE) && (row == BLACK_PAWN_INITIAL_ROW)))
                    {
                        if ((Pos[row+PawnStep][col] == Position.EMPTY) && (Pos[row_n][col] == Position.EMPTY))         
                        { 
                            Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);                            
                        }
                    }
                    
                    for (i = -1; i < 2; i += 2)                                 // Pawn takes opponent figure away
                    {
                        col_n = col + i;
                        row_n = row + PawnStep;
                        if((col_n >= 1) && (col_n <= COLS) && (OpponentFigure(Pos,col_n, row_n)))
                        {
                            if ((row_n == WHITE_PAWN_PROMOTION_ROW) || (row_n == BLACK_PAWN_PROMOTION_ROW))                     // Convert Pawn                           
                            {                                                   // Take away opponent figure
                                for (i = 0; i < WhitePromotionFigure.length; i++)    // Loop for Queen, Rook, Knight, Bishop
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
                                    Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure_n, col_n, row_n);
                                }
                            }
                            else
                            {

                                Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);
                                              
                            }
                        }   

                        // En passant
                        if ((((GetMoveColor(Pos) == WHITE_MOVE) && (row == 5)) || ((GetMoveColor(Pos) == BLACK_MOVE) && (row == 4))) && (col_n >= 1) && (col_n <= COLS))
                        {                                                                                                 
                            if (col_n == GetColumnPawnMovedTwoRows(Pos))        //Workaround: Compiler ssems to miss this if this is added to line beforE
                            {
                                    Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);                          
                            }               
                        }
                        
                    }                   
                    break;
               
                case WHITE_ROOK:
                case BLACK_ROOK:
                    for (dir = 1; dir <= 4; dir++)                              // Loop over all four directions
                    {
                        for (i = 1; i < COLS; i++)                              // Loop over all PawnSteps in one direction         
                        {
                            switch(dir)
                            {
                                case 1:                                         // Move N
                                    col_n = col;           
                                    row_n = row + i;
                                    break;
                                        
                                case 2:                                         // Move E
                                    col_n = col + i;          
                                    row_n = row;
                                    break;
                                        
                                case 3:                                         // Move S
                                    col_n = col;
                                    row_n = row - i;
                                    break;
                                        
                                case 4:                                         // Move W
                                    col_n = col - i;
                                    row_n = row;
                                    break;
                            } 
                         
                            if(OffBoardOrOwnFigure(Pos, col_n, row_n))
                            {
                                break;                                          // Stop moving in this direction, continue with next direction
                            }

                            Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);
                            
                            if(OpponentFigure(Pos, col_n, row_n))               // Took oponent figure away, stop to moving in this direction
                            {                               
                                break;
                            }
                        }
                    }               
                    break;
               
                case WHITE_KNIGHT:
                case BLACK_KNIGHT:
                    for (dir = 1; dir <= 8; dir++)                              // Loop over all four directions
                    {
                        switch(dir)
                        {
                            case 1:
                                col_n = col - 2;
                                row_n = row - 1;
                                break;
                                
                            case 2:
                                col_n = col - 2;
                                row_n = row + 1;
                                break;
                                
                            case 3:
                                col_n = col - 1;
                                row_n = row - 2;
                                break;
                                
                            case 4:
                                col_n = col - 1;
                                row_n = row + 2 ;
                                break;
                                
                            case 5:
                                col_n = col + 1;
                                row_n = row - 2;
                                break;
                                
                            case 6:
                                col_n = col + 1;
                                row_n = row + 2;
                                break;
                                
                            case 7:
                                col_n = col + 2;
                                row_n = row - 1;
                                break;
                                
                            case 8:
                                col_n = col + 2;
                                row_n = row + 1 ;
                                break;                                     
                        }
                                
                        if(OffBoardOrOwnFigure(Pos, col_n, row_n))
                        {
                            continue;                                           // Continue with next direction
                        }
                        Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);
                    }
                    break;
               
                case WHITE_BISHOP:
                case BLACK_BISHOP:
                    for (dir = 1; dir <= 4; dir++)                              // Loop over all four directions
                    {
                        for (i = 1; i < COLS; i++)                              // Loop over all steps in one direction         
                        {
                            switch(dir)
                            {
                                case 1:                                         // Move NE
                                    col_n = col + i;           
                                    row_n = row + i;
                                    break;
                                        
                                case 2:                                         // Move SE
                                    col_n = col + i;          
                                    row_n = row - i;;
                                    break;
                                        
                                case 3:                                         // Move SW
                                    col_n = col - i;
                                    row_n = row - i;
                                    break;
                                        
                                case 4:                                         // Move NW
                                    col_n = col - i;
                                    row_n = row + i;
                                    break;
                            } 
                                    
                            if(OffBoardOrOwnFigure(Pos, col_n, row_n))
                            {
                                break;                                          // Stop moving in this direction, continue with next direction
                            }
                            Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);                            
                            if(OpponentFigure(Pos, col_n, row_n))               // Took oponent figure away, stop to moving in this direction
                            {
                                break;
                            }
                        }
                    }               
                    break;                

                case WHITE_QUEEN:
                case BLACK_QUEEN:        
                    for (dir = 1; dir <= 8; dir++)                              // Loop over all eight directions
                    {
                        for (i = 1; i < COLS; i++)                              // Loop over all PawnSteps in one direction         
                        {
                            switch(dir)
                            {
                                case 1:                                         // Move N
                                    col_n = col;           
                                    row_n = row + i;
                                    break;
                                    
                                case 2:                                         // Move NE
                                    col_n = col + i;          
                                    row_n = row + i;
                                    break;
                                    
                                case 3:                                         // Move E
                                    col_n = col + i;
                                    row_n = row;
                                    break;
                                    
                                case 4:                                         // Move SE
                                    col_n = col + i;
                                    row_n = row - i;
                                    break;
                                        
                                case 5:                                         // Move S
                                    col_n = col;           
                                    row_n = row - i;
                                    break;
                                    
                                case 6:                                         // Move SW
                                    col_n = col - i;          
                                    row_n = row - i;
                                    break;
                                        
                                case 7:                                         // Move W
                                    col_n = col - i;
                                    row_n = row;
                                    break;
                                   
                                case 8:                                         // Move NW
                                    col_n = col - i;
                                    row_n = row + i;
                                    break;                                           
                            }
                                        
                            if(OffBoardOrOwnFigure(Pos, col_n, row_n))
                            {
                                break;
                            }

                            Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);
                            
                            if(OpponentFigure(Pos, col_n, row_n))               // Took oponent figure away, stop to moving in this direction
                            {
                                break;
                            }   
                        }
                    }  
                    break;
               
                case WHITE_KING:
                case BLACK_KING:
                    for (dir = 1; dir <= 8; dir++)                              // Loop over all eight directions
                    {
                        switch(dir)
                        {
                            case 1:
                                col_n = col - 1;
                                row_n = row - 1;                                // N
                                break;
                                
                            case 2:
                                col_n = col - 1;                                // NE
                                row_n = row;
                                break;
                                
                            case 3:
                                col_n = col - 1;                                // E
                                row_n = row + 1;
                                break;
                                
                            case 4:
                                col_n = col;                                    // SE
                                row_n = row - 1 ;
                                break;
                                
                            case 5:
                                col_n = col;                                    // S
                                row_n = row + 1;
                                break;
                                
                            case 6:
                                col_n = col + 1;                                // SW
                                row_n = row - 1;
                                break;
                                
                            case 7:
                                col_n = col + 1;                                // W
                                row_n = row;
                                break;
                                
                            case 8:
                                col_n = col + 1;                                // NW
                                row_n = row + 1;
                                break;                                     
                        }       
                        if(OffBoardOrOwnFigure(Pos, col_n, row_n))
                        {
                            continue;                                           // Stop moving in this direction, continue with next direction
                        } 
                        Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);                     
                    }
                    list = Move.Castling(Pos, CastlingList);
                    for (i = 0; i < list; i++)
                    {
                        switch(CastlingList[i])
                        {
                            case Position.LONG_CASTLING:
                                col_n           = Position.C;               //For King
                                break;
                                        
                            case Position.SHORT_CASTLING:
                                col_n           = Position.G;               //For King
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
                        Move.AddMoveToMoveList(MoveList, Figure, col, row, Pos[row_n][col_n], Figure, col_n, row_n);    
                    }
                    break;                            
            }
        }
    }              
}
