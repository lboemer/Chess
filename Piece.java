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
	
	public Piece(int new_type, int new_color, String new_image, String new_console_notation, String new_notation, int new_rating, int new_material_rating, int new_min_num, int new_max_num)
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
	
	}
	
	public int getRating()
	{
		return rating;
	}
	
	public int getMaterialRating()
	{
		return material_rating;
	}
	
	public String getImageString()
	{
		return image;
	}
	
	public int getMaxNum()
	{
		return max_num;
	}
	
	public int getMinNum()
	{
		return min_num;
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getColor()
	{
		return color;
	}
	
	public String getConsoleNotation()
	{
		return console_notation;
	}
	
	public String getNotation()
	{
		return notation;
	}
	
	public static int ConsoleNotationToType(String console_notation)
	{
		for (Piece piece : Pieces)
		{
			if (piece.getConsoleNotation().equals(console_notation))
				return piece.getType();
		}
		return -1;
	}
	
	public static Piece empty        = new Piece(Position.EMPTY,      Position.EMPTY,   "", "--", " ", 0,   0,  0, Position.ROWS * Position.COLS);
	
    public static Piece white_king   = new Piece(Position.WHITE_KING, Position.WHITE,   "wk.png", "WK", "K", 400, 0, Position.MIN_WK, Position.MAX_WK);
    public static Piece white_queen  = new Piece(Position.WHITE_QUEEN, Position.WHITE,  "wq.png", "WQ", "Q", 880, 9, Position.MIN_WQ, Position.MAX_WQ);
    public static Piece white_rook   = new Piece(Position.WHITE_ROOK, Position.WHITE,   "wr.png", "WR", "R", 510, 5, Position.MIN_WR, Position.MAX_WR);
    public static Piece white_knight = new Piece(Position.WHITE_KNIGHT, Position.WHITE, "wn.png", "WN", "N", 320, 3, Position.MIN_WN, Position.MAX_WN);
    public static Piece white_bishop = new Piece(Position.WHITE_BISHOP, Position.WHITE, "wb.png", "WB", "B", 333, 3, Position.MIN_WB, Position.MAX_WB);
    public static Piece white_pawn   = new Piece(Position.WHITE_PAWN, Position.WHITE,   "wp.png", "WP", " ", 100, 1, Position.MIN_WP, Position.MAX_WP);
    
    public static Piece black_king   = new Piece(Position.BLACK_KING, Position.BLACK,   "bk.png", "BK", "K", -400, 0, Position.MIN_BK, Position.MAX_BK);
    public static Piece black_queen  = new Piece(Position.BLACK_QUEEN, Position.BLACK,  "bq.png", "BQ", "Q", -880, -9, Position.MIN_BQ, Position.MAX_BQ);
    public static Piece black_rook   = new Piece(Position.BLACK_ROOK, Position.BLACK,   "br.png", "BR", "R", -510, -5, Position.MIN_BR, Position.MAX_BR);
    public static Piece black_knight = new Piece(Position.BLACK_KNIGHT, Position.BLACK, "bn.png", "BN", "N", -320, -3, Position.MIN_BN, Position.MAX_BN);
    public static Piece black_bishop = new Piece(Position.BLACK_BISHOP, Position.BLACK, "bb.png", "BB", "B", -333, -3, Position.MIN_BB, Position.MAX_BB);
    public static Piece black_pawn   = new Piece(Position.BLACK_PAWN, Position.BLACK,   "bp.png", "BP", " ", -100, -1, Position.MIN_BP, Position.MAX_BP);
    
    
    public static Piece[] Pieces = { empty,
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
									 black_pawn };
									 
	public static Piece[] WhitePromotionFigures = {
									 white_queen,
									 white_rook,
									 white_knight,
									 white_bishop  };
									 

	public static Piece[] BlackPromotionFigures = {
								 black_queen,
								 black_rook,
								 black_knight,
								 black_bishop  };


}
