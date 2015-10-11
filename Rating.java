import java.lang.*;                                                             // used for max()

public class Rating
{
    public static final int CHECKMATE_RATING                 = 10000;
    public static final int DRAW_RATING                      = 0;    
    
    // Position Type
    public static final int UNDEFINED                       = 0;
    public static final int WIDE_OPEN                       = 1;   
    public static final int OPEN                            = 2;  
    public static final int SEMI_OPEN                       = 3;      
    public static final int AVERAGE                         = 4;
    public static final int CLOSED                          = 5;    
    public static final int BLOCKED                         = 6;
    
    // Game phase
    public static final int OPENING_GAME                    = 0;
    public static final int MIDDLE_GAME                     = 1;
    public static final int END_GAME                        = 2;
    
    // Material rating for Game phase
    public static final int OPENING_GAME_MIN_MATERIAL       = 31;
    public static final int END_GAME_MAX_MATERIAL           = 15;
    
    /*
    // Reduced  by equivalent of 1 bishop, 1 knight, 2 pawns
    public static final int OPENING_GAME_MIN_MATERIAL       =       piece.white_king.getRating() +     
                                                                    white_queen.getRating + 
                                                                2 * white_rook.getRating() + 
                                                                    white_bishop.getRating + 
                                                                    white_knight.getRating() + 
                                                                6 * white_pawn.gteRating;
    
    // Reduced  by equivalent of 2 rooks, 1 bishop, 1 knight, 4 pawns                                                            
    public static final int END_GAME_MIN_MATERIAL       =           white_king.getRating() + 
                                                                    white_queen.getRating + 
                                                                    white_bishop.getRating + 
                                                                    white_knight.getRating() + 
                                                                4 * white_pawn.gteRating;    
    */                                                            
                                    
    public static final int FELLOW                          = 0;
    public static final int OPPONENT                        = 1; 
    
    // File type used for rook and queen evaluation 
    public static int CLOSED_FILE                           = 0;
    public static int SEMI_OPEN_FILE                        = 1; 
    public static int OPEN_FILE                             = 2;
    
    public static int Rating(int[][] Pos, int PositionStatus)
    {
        int row;
        int col;
        int Rating = 0;
        int FinePawnRating;
        int Type = PositionType(Pos);
        int Phase = GamePhase(Pos);
        int rat;

        switch (PositionStatus)
        {
            case Position.CHECKMATE:
                return ((Position.GetMoveColor(Pos)) == Position.WHITE_MOVE) ? CHECKMATE_RATING : -CHECKMATE_RATING;
                
            case Position.STALEMATE:
            case Position.INSUFFICIENT_MATERIAL:
            case Position.THREE_POSITION_REPETITION:
            case Position.FIFTY_MOVE: 
                return DRAW_RATING;
        }

        for (row = 1; row <= Position.ROWS; row++)
        {
            for (col = 1; col <= Position.COLS; col++)              
            {
                switch(Pos[row][col])
                {
                    case Position.WHITE_PAWN:
                    case Position.BLACK_PAWN:     
                        //rat = Pawn.Rating(Pos, row, col, Phase);
                        //System.out.println(" In rating Pawn .. rat = " + rat);                    
                        Rating += Pawn.Rating(Pos, row, col, Phase);
                        break;
                            
                    case Position.WHITE_ROOK:
                    case Position.BLACK_ROOK:        
                        //rat = Rook.Rating(Pos, row, col, Type, Phase);
                        //System.out.println(" In rating Rook .. rat = " + rat);
                        Rating += Rook.Rating(Pos, row, col, Type, Phase);
                        break;

                    case Position.WHITE_BISHOP:
                    case Position.BLACK_BISHOP:
                        //rat = Bishop.Rating(Pos, row, col, Type, Phase);
                        //System.out.println(" In rating Rook .. rat = " + rat);                    
                        Rating += Bishop.Rating(Pos, row, col, Type, Phase);
                        break;
        
                    case Position.WHITE_QUEEN:
                    case Position.BLACK_QUEEN:                    
                        //rat = Queen.Rating(Pos, row, col, Type, Phase);
                        //System.out.println(" In rating Queen .. rat = " + rat);
                        Rating += Queen.Rating(Pos, row, col, Type, Phase);
                        break;

                    case Position.WHITE_KING:
                    case Position.BLACK_KING:                       
                        //rat = King.Rating(Pos, row, col, Phase);
                        //System.out.println(" In rating King .. rat = " + rat);                     
                        Rating += King.Rating(Pos, row, col, Phase);
                        break;              
                }
            }
        }
        // Chess.PositionCache.put(Position.PositionToString(Pos), Rating);
        return(Rating);
    }       
      
    public static int GamePhase(int[][] Pos)
    {
        int row;
        int col;
        int Phase                                           = 0;
        int WhiteMaterial                                   = 0;
        int BlackMaterial                                   = 0;
        Piece piece;
        int Material;

        for (row = 1; row <= Position.ROWS; row++)
        {
            for (col = 1; col <= Position.COLS; col++)              
            {
                piece = Piece.Pieces[Pos[row][col]];
                if (piece.getColor() == Position.WHITE )
                {
                    WhiteMaterial += piece.getMaterialRating();
                }
                else if(piece.getColor() == Position.BLACK )
                {
                    BlackMaterial += piece.getMaterialRating();
                }
            }
        } 
        
        Material = Math.max(WhiteMaterial, Math.abs(BlackMaterial));
                   
        if(Material > OPENING_GAME_MIN_MATERIAL)
        {
            Phase = OPENING_GAME;
        }
        
        if((Material <= OPENING_GAME_MIN_MATERIAL) && (Material >= END_GAME_MAX_MATERIAL))
        {
            Phase = MIDDLE_GAME;
        }
        
        if(Material < END_GAME_MAX_MATERIAL)
        {
            Phase = END_GAME;
        }      
        return Phase;
    }

    public static int PositionType(int[][] Pos)
    {      
        if((PawnExchanges(Pos) >= 2) && (CenterPawnsBlocked(Pos) == 0))
        {
            return WIDE_OPEN;
        }
        
        if((CenterPawnExchanges(Pos) >= 1) && (CenterPawnsBlocked(Pos) == 0))
        {
            return OPEN;
        }        
         
        if(((PawnExchanges(Pos) > 1)  && (CenterPawnsBlocked(Pos) == 1)) ||       
           ((PawnExchanges(Pos) == 1) && (CenterPawnsBlocked(Pos) == 0)))        
        {
            return SEMI_OPEN;
        }
        
        if(((PawnExchanges(Pos) == 0) && (CenterPawnsBlocked(Pos) == 0)) ||
           ((PawnExchanges(Pos) == 1) && (CenterPawnsBlocked(Pos) == 1)))
        {
            return AVERAGE;
        }        
        
        if((PawnExchanges(Pos) == 0) && (CenterPawnsBlocked(Pos) == 1)) 
        {
            return CLOSED;
        }       
        
        if((CenterPawnsBlocked(Pos) == 2) ||
           (BlockedPawn(Pos, Position.C) && BlockedPawn(Pos, Position.D)) ||
           (BlockedPawn(Pos, Position.E) && BlockedPawn(Pos, Position.F)))     
        {
            return BLOCKED;
        }         
        
        return UNDEFINED;
    }
    
    public static int CenterPawnsBlocked(int[][] Pos)
    {
        int number;
        int col;
        
        number = 0;
        for(col = Position.D; col <= Position.E; col++)
        {
            if(BlockedPawn(Pos,col))
            {
                number++;
            }
        }
        return number;
    }

    public static boolean BlockedPawn(int[][] Pos, int col)
    {
        int row;

        for(row = 2; row < Position.ROWS - 1; row++)
        {
            if((Pos[row][col] == Position.WHITE_PAWN)  && (Pos[row + 1][col] == Position.BLACK_PAWN))
            {
                return true;
            }
        }
        return false;
    }   
    
    public static int CenterPawnExchanges(int[][] Pos)
    {
        return Math.min(PawnExchanges(Pos), 4 - CenterPawns(Pos));
    }     
    
    public static int PawnExchanges(int[][] Pos)    
    {
        final int MAX_NUMBER_PAWNS  = 8;
        int row;
        int col;
        int WhitePawns              = 0;
        int BlackPawns              = 0;
        
        for(row = 2; row < Position.ROWS; row++)
        {
            for(col = 1; col <= Position.COLS; col++)
            {
                if(Pos[row][col] == Position.WHITE_PAWN)
                {
                    WhitePawns ++;
                }
                if(Pos[row][col] == Position.BLACK_PAWN)
                {
                    BlackPawns ++;
                }                
            }
        }
        return(MAX_NUMBER_PAWNS - Math.max(WhitePawns, BlackPawns)); 
    }

    public static int CenterPawns(int[][] Pos)    
    {
        int row;
        int col;
        int WhitePawns  = 0;
        int BlackPawns  = 0;
        
        for(row = 2; row < Position.ROWS; row++)
        {
            for(col = Position.D; col <= Position.E; col++)
            {
                if((Pos[row][col] == Position.WHITE_PAWN) && (WhitePawns < 2))
                {
                    WhitePawns ++;
                }
                
                if((Pos[row][col] == Position.BLACK_PAWN) && (BlackPawns < 2))
                {
                    BlackPawns ++;
                }                
            }
        }
        return(WhitePawns + BlackPawns); 
    }    
    
    public static int FileType(int[][] Pos, int col)
    {
        int NumWhitePawn = 0;
        int NumBlackPawn = 0;
        int row;
        
        for(row = 2; row < Position.ROWS; row++)
        {
            if(Pos[row][col] == Position.WHITE_PAWN)
            {
                NumWhitePawn++;
            }            
            if(Pos[row][col] == Position.BLACK_PAWN)
            {
                NumBlackPawn++;
            }           
        }
        if((NumWhitePawn == 0) && (NumBlackPawn == 0))
        {
            return OPEN_FILE;
        }
        if(((NumWhitePawn == 0) && (NumBlackPawn > 0)) || ((NumWhitePawn > 0) && (NumBlackPawn == 0)))
        {
            return SEMI_OPEN_FILE;
        }        
        return CLOSED_FILE;
    }
    
    public static void ShowPositionType(int Type)
    {
        System.out.print("Position Type:\t\t\t");
        switch(Type)
        {
            case UNDEFINED:
                System.out.print("Undefined");
                break;
            
            case WIDE_OPEN:
                System.out.print("Wide Open");
                break;
                
           case OPEN:
                System.out.print("Open");
                break;
            
           case SEMI_OPEN:
                System.out.print("Semi Open");
                break; 
                
            case AVERAGE:
                System.out.print("Average");
                break;
                
            case CLOSED:
                System.out.print("Closed");
                break;
                
           case BLOCKED:
                System.out.print("Blocked");
                break;
        }
        System.out.println();
    }
    
    public static void ShowGamePhase(int Phase)
    {
        System.out.print("Game Phase:\t\t\t");
        switch(Phase)
        {
            case OPENING_GAME:
                System.out.println("Opening game");
                break;
            
            case MIDDLE_GAME:
                System.out.println("Middle game");
                break;
                
           case END_GAME:
                System.out.println("End game");       
                break;
        
        }
    }  
}
