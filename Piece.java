public class Piece
{
    int type;
    int color;
    String image;
    String console_notation;
    String notation;
    int rating;
    int material_rating;
    int min_num;
    int max_num;
    PieceMove piece_move;

    public static class PieceMove
    {
        public int[][] direction;
        public int max_steps;

        public PieceMove(int[][] new_direction, int new_max_steps) 
        {
                direction = new_direction;
                max_steps = new_max_steps;
        }
    }

    public Piece(int new_type, int new_color, String new_image, String new_console_notation, String new_notation, int new_rating, int new_material_rating, int new_min_num, int new_max_num, PieceMove new_piece_move)
    {
        type = new_type;
        color = new_color;
        image = new_image;
        console_notation = new_console_notation;
        notation = new_notation;
        rating = new_rating;
        material_rating = new_material_rating;
        max_num = new_max_num;
        min_num = new_min_num;
        piece_move = new_piece_move;
    }

    public int getType()
    {
            return type;
    }
        
    public int getColor()
    {
            return color;
    }
        
    public String getImageString()
    {
            return image;
    }
    
    public String getConsoleNotation()
    {
            return console_notation;
    }
    
    public String getNotation()
    {
            return notation;
    }
    
    public int getRating()
    {
            return rating;
    }
   
    public int getMaterialRating()
    {
            return material_rating;
    }

    public int getMaxNum()
    {
            return max_num;
    }

    public int getMinNum()
    {
            return min_num;
    }

    public PieceMove getPieceMove()
    {
            return piece_move;
    }

    public static int ConsoleNotationToType(String console_notation)
    {
        for(Piece piece : Pieces)
        {
                if(piece.getConsoleNotation().equals(console_notation))
                        return piece.getType();
        }
        return -1;
    }

    public static int[][] EightDirections = new int[][]
    {// row, col
        { 0,  1},       // Move east
        {-1,  1},       // Move southeast
        {-1,  0},       // Move south
        {-1, -1},       // Move southwest
        { 0, -1},       // Move west
        { 1, -1},       // Move northwest
        { 1,  0},       // Move north
        { 1,  1}        // Move northeast
    };

    public static int[][] KnightDirections = new int[][]
    {// row, col 
        { 2,  1},       // Move north northeast
        { 1,  2},       // Move east  northeast
        {-1,  2},       // Move east  southeast
        {-2,  1},       // Move south southeast
        {-2, -1},       // Move south southwest
        {-1, -2},       // Move west  southwest
        { 1, -2},       // Move west  northwest
        { 2, -1}        // Move north northwest
    };    

    public static int[][] BishopDirections = new int[][]
    {// row, col 
        { 1,  1},       // Move northeast
        {-1,  1},       // Move southheast
        {-1, -1},       // Move southwest
        { 1, -1},       // Move northeast
    };                                                    

    public static int[][] RookDirections = new int[][]
    {// row, col
        { 0,  1},       // Move east
        {-1,  0},       // Move south
        { 0, -1},       // Move west
        { 1,  0},       // Move north
    };
    
    public static int[][] Empty = new int[][]{};

    public static PieceMove EmptyMove = new PieceMove(Empty, 0);
    public static PieceMove KingMove = new PieceMove(EightDirections, 1);
    public static PieceMove QueenMove = new PieceMove(EightDirections, 7);
    public static PieceMove RookMove = new PieceMove(RookDirections, 7);
    public static PieceMove KnightMove = new PieceMove(KnightDirections, 1);
    public static PieceMove BishopMove = new PieceMove(BishopDirections, 7);
    public static PieceMove PawnMove = new PieceMove(new int[][]{}, 1);

    public static Piece empty        = new Piece(Position.EMPTY,      Position.EMPTY,   "", "--", " ", 0,   0,  0, Position.ROWS * Position.COLS, EmptyMove);
    public static Piece white_king   = new Piece(Position.WHITE_KING, Position.WHITE,   "wk.png", "WK", "K", 400, 0, Position.MIN_WK, Position.MAX_WK, KingMove);
    public static Piece white_queen  = new Piece(Position.WHITE_QUEEN, Position.WHITE,  "wq.png", "WQ", "Q", 880, 9, Position.MIN_WQ, Position.MAX_WQ, QueenMove);
    public static Piece white_rook   = new Piece(Position.WHITE_ROOK, Position.WHITE,   "wr.png", "WR", "R", 510, 5, Position.MIN_WR, Position.MAX_WR, RookMove);
    public static Piece white_knight = new Piece(Position.WHITE_KNIGHT, Position.WHITE, "wn.png", "WN", "N", 320, 3, Position.MIN_WN, Position.MAX_WN, KnightMove);
    public static Piece white_bishop = new Piece(Position.WHITE_BISHOP, Position.WHITE, "wb.png", "WB", "B", 333, 3, Position.MIN_WB, Position.MAX_WB, BishopMove);
    public static Piece white_pawn   = new Piece(Position.WHITE_PAWN, Position.WHITE,   "wp.png", "WP", " ", 100, 1, Position.MIN_WP, Position.MAX_WP, PawnMove);
    
    public static Piece black_king   = new Piece(Position.BLACK_KING, Position.BLACK,   "bk.png", "BK", "K", -400, 0, Position.MIN_BK, Position.MAX_BK, KingMove);
    public static Piece black_queen  = new Piece(Position.BLACK_QUEEN, Position.BLACK,  "bq.png", "BQ", "Q", -880, -9, Position.MIN_BQ, Position.MAX_BQ, QueenMove);
    public static Piece black_rook   = new Piece(Position.BLACK_ROOK, Position.BLACK,   "br.png", "BR", "R", -510, -5, Position.MIN_BR, Position.MAX_BR, RookMove);
    public static Piece black_knight = new Piece(Position.BLACK_KNIGHT, Position.BLACK, "bn.png", "BN", "N", -320, -3, Position.MIN_BN, Position.MAX_BN, KnightMove);
    public static Piece black_bishop = new Piece(Position.BLACK_BISHOP, Position.BLACK, "bb.png", "BB", "B", -333, -3, Position.MIN_BB, Position.MAX_BB, BishopMove);
    public static Piece black_pawn   = new Piece(Position.BLACK_PAWN, Position.BLACK,   "bp.png", "BP", " ", -100, -1, Position.MIN_BP, Position.MAX_BP, PawnMove);
    
    public static Piece[] Pieces = 
    { 
        empty,
        white_king,
        white_queen,
        white_rook,
        white_knight,
        white_bishop,
        white_pawn,
        black_king,
        black_queen,
        black_rook,
        black_knight,
        black_bishop,
        black_pawn 
    };
									 
    public static Piece[] WhitePromotionFigures = 
    {
        white_queen,
        white_rook,
        white_knight,
        white_bishop  
    };

    public static Piece[] BlackPromotionFigures = 
    {
        black_queen,
        black_rook,
        black_knight,
        black_bishop  
    };
}
