import java.lang.*;                                                             // used for max()

public class Rating
{
    public static final int CHECKMATE_RATING                 = 10000;
    public static final int DRAW_RATING                      = 0;

    // Pawn Position type
    public static final int ISOLATED                        = 0;      
    public static final int BACK                            = 1;   
    public static final int FRONT                           = 2;        
    public static final int PASSED                          = 3;    
    public static final int CONNECTED_ONE                   = 4;      
    public static final int CONNECTED_TWO                   = 5;         
    public static final int PASSED_AND_CONNECTED_ONE        = 6;
    public static final int PASSED_AND_CONNECTED_TWO        = 7;   
     
    public static final int FELLOW                          = 0;
    public static final int OPPONENT                        = 1;    
    
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
        
    public static void Rating()
    {
    }
    
    public static int GetRating1(int[][] Pos, int PositionStatus)               // Rating 1
    {
        int row;
        int col;
        int FinePawnRating;        
        int Rating                                  = 0;
        
        final int KING_RATING                       = 400;
        final int QUEEN_RATING                      = 900;
        final int ROOK_RATING                       = 500;
        final int KNIGHT_RATING                     = 300;
        final int BISHOP_RATING                     = 300;
        final int PAWN_RATING                       = 100;
        
        switch (PositionStatus)
        {
            case Position.CHECKMATE:
                switch(Position.GetMoveColor(Pos))
                {
                    case Position.WHITE_MOVE:
                        return CHECKMATE_RATING;
                        
                    case Position.BLACK_MOVE:
                        return -CHECKMATE_RATING;
                }
                break;
                
            case Position.STALEMATE:
            case Position.INSUFFICIENT_MATERIAL: 
            case Position.THREE_POSITION_REPETITION:
            case Position.FIFTY_MOVE:
                return DRAW_RATING;
        }

        for (row = 1; row <= Position.ROWS; row++)
        {
            for (col = 1; col <= Position.COLS; col++)              
            {                                                                   // For now rating scheme just counts value of figures ...
                switch(Pos[row][col])                                           // ...later rating scheme can be more sophisticated
                {   
                    case Position.WHITE_KING:                                   
                        Rating += KING_RATING;
                        break;
                            
                    case Position.WHITE_QUEEN:
                        Rating += QUEEN_RATING;
                        break;
                            
                    case Position.WHITE_ROOK:
                        Rating += ROOK_RATING;
                        break;
                            
                    case Position.WHITE_KNIGHT:
                        Rating += KNIGHT_RATING;
                        break;
                            
                    case Position.WHITE_BISHOP:
                        Rating += BISHOP_RATING;
                        break;
                            
                    case Position.WHITE_PAWN:
                        Rating += PAWN_RATING;
                        break;
                            
                    case Position.BLACK_KING:
                        Rating += -KING_RATING;
                        break;
                            
                    case Position.BLACK_QUEEN:
                        Rating += -QUEEN_RATING;
                        break;
                            
                    case Position.BLACK_ROOK:
                        Rating += -ROOK_RATING;
                        break;
                            
                    case Position.BLACK_KNIGHT:
                        Rating += -KNIGHT_RATING;
                        break;
                            
                    case Position.BLACK_BISHOP:
                        Rating += -BISHOP_RATING;
                        break;
                            
                    case Position.BLACK_PAWN:
                        Rating += -PAWN_RATING;
                        break;
                }
            }
        }
        return(Rating);
    }   

    public static int GetRating(int[][] Pos, int PositionStatus)                // Rating 2
    {
        int row;
        int col;
        int Rating = 0;
        int FinePawnRating;

        final int KING_RATING                     = 400;                        // Use centi pawn rating system
        final int QUEEN_RATING                    = 880;
        final int ROOK_RATING                     = 510;
        final int KNIGHT_RATING                   = 320;
        final int BISHOP_RATING                   = 333;
        final int PAWN_RATING                     = 100;
        
        switch (PositionStatus)
        {
            case Position.CHECKMATE:
                switch(Position.GetMoveColor(Pos))
                {
                    case Position.WHITE_MOVE:
                        return CHECKMATE_RATING;
                        
                    case Position.BLACK_MOVE:
                        return -CHECKMATE_RATING;
                }
                break;
                
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
                        case Position.WHITE_KING:                    
                            Rating += KING_RATING;
                            break;
                            
                        case Position.WHITE_QUEEN:
                            Rating += QUEEN_RATING;
                            break;
                            
                        case Position.WHITE_ROOK:
                            Rating += ROOK_RATING;
                            break;
                            
                        case Position.WHITE_KNIGHT:
                            Rating += KNIGHT_RATING;
                            break;
                            
                        case Position.WHITE_BISHOP:
                            Rating += BISHOP_RATING;
                            break;
                            
                        case Position.WHITE_PAWN:
                            Rating += PawnRating(Pos, row, col, OPENING_GAME);
                            break;
                            
                        case Position.BLACK_KING:
                            Rating -= KING_RATING;
                            break;
                            
                        case Position.BLACK_QUEEN:
                            Rating -= QUEEN_RATING;
                            break;
                            
                        case Position.BLACK_ROOK:
                            Rating -= ROOK_RATING;
                            break;
                            
                        case Position.BLACK_KNIGHT:
                            Rating -= KNIGHT_RATING;
                            break;
                            
                        case Position.BLACK_BISHOP:
                            Rating -= BISHOP_RATING;
                            break;
                            
                        case Position.BLACK_PAWN:
                            Rating -= PawnRating(Pos, row, col, OPENING_GAME);    
                            break;
                    }
            }
        }
        return(Rating);
    }       
      
    public static int GamePhase(int[][] Pos)
    {
        int row;
        int col;
        int Phase                                           = 0;
        int WhiteMaterial                                   = 0;
        int BlackMaterial                                   = 0;
        int Material;
       
        final int QUEEN_MATERIAL_RATING                    = 9;
        final int ROOK_MATERIAL_RATING                     = 5;
        final int KNIGHT_MATERIAL_RATING                   = 3;
        final int BISHOP_MATERIAL_RATING                   = 3;
        final int PAWN_MATERIAL_RATING                     = 1;
        
        final int OPENING_GAME_MIN_MATERIAL                = 31;
        final int END_GAME_MAX_MATERIAL                    = 14;        

        for (row = 1; row <= Position.ROWS; row++)
        {
            for (col = 1; col <= Position.COLS; col++)              
            {
                switch(Pos[row][col])
                {        
                    case Position.WHITE_QUEEN:
                        WhiteMaterial += QUEEN_MATERIAL_RATING;
                        break;
                            
                    case Position.WHITE_ROOK:
                        WhiteMaterial += ROOK_MATERIAL_RATING;
                        break;
                            
                    case Position.WHITE_KNIGHT:
                        WhiteMaterial += KNIGHT_MATERIAL_RATING;
                        break;
                            
                    case Position.WHITE_BISHOP:
                        WhiteMaterial += BISHOP_MATERIAL_RATING;
                        break;
                            
                    case Position.WHITE_PAWN:
                        WhiteMaterial += PAWN_MATERIAL_RATING;
                        break;
                            
                    case Position.BLACK_QUEEN:
                        BlackMaterial += QUEEN_MATERIAL_RATING;
                        break;
                            
                    case Position.BLACK_ROOK:
                        BlackMaterial += ROOK_MATERIAL_RATING;
                        break;
                            
                    case Position.BLACK_KNIGHT:
                        BlackMaterial += KNIGHT_MATERIAL_RATING;
                        break;
                            
                    case Position.BLACK_BISHOP:
                        BlackMaterial -= BISHOP_MATERIAL_RATING;
                        break;
                            
                    case Position.BLACK_PAWN:
                        BlackMaterial += PAWN_MATERIAL_RATING;
                        break;
                }
            }
        }
        
        Material = Math.max(WhiteMaterial, BlackMaterial);
                
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
    
    public static int PawnRating(int[][] Pos, int row, int col, int GamePhase)
    {
        final int UPPER_FILE_DELTA          = 1;
        final int LOWER_FILE_DELTA          = -1;        
        
        // Hans Berliner (1999), The System: A World Champions Approach to Chess

        int PawnValueOpeningAndMiddleGame[][]   =
        {  //   A    B    C,   D,   E,   F,  G,   H      Rank  
            {   0,   0,   0,   0,   0,   0,  0,    0 }, // 1                    Extended Berliner table              
            {  90,  95, 105, 110, 110, 105,  95,  90 }, // 2     
            {  90,  95, 105, 115, 115, 105,  95,  90 }, // 3     
            {  90,  95, 110, 120, 120, 110,  95,  90 }, // 4      
            {  97, 103, 117, 127, 127, 117, 103,  97 }, // 5      
            { 106, 112, 125, 140, 140, 125, 112, 106 }, // 6     
            { 116, 123, 135, 150, 150, 135, 123, 116 }, // 7                    Extended Berliner table                
            {   0,   0,   0,   0,   0,   0,   0,   0 }, // 8                    Extended Berliner table                    
        };    
        
        int PawnValueEndgame[][]   =
        {  //   A    B    C,   D,   E,   F,  G,   H      Rank              
            {   0,   0,   0,   0,   0,   0,  0,    0 }, // 1                    Extended Berliner table                   
            { 120, 105,  95,  90,  90,  95, 105, 120 }, // 2     
            { 120, 105,  95,  90,  90,  95, 105, 120 }, // 3     
            { 125, 110, 100,  95,  95, 100, 110, 125 }, // 4     
            { 133, 117, 107, 100, 100, 107, 117, 133 }, // 5    
            { 145, 129, 116, 105, 105, 116, 129, 145 }, // 6    
            { 164, 144, 126, 115, 115, 126, 144, 164 }, // 7                    Extended Berliner table        
            {   0,   0,   0,   0,   0,   0,  0,    0 }, // 8                    Extended Berliner table                        
        };         
        
        int MultiplierPawnAdvances[][]       =
        // C_T =       C_O + (C_O - ISO) * 0.3                                  // Source: Leo         
        // BAC = FRO = ISO + (C_O - ISO) * 0.5                                  // Source: Leo
        // PCT =       PCO + (PCO - PAS) * 0.5                                  // Source: Leo
                                                                                // ISO: Isolated
                                                                                // BAC: Back 
                                                                                // C_O: Connected to one pawn
                                                                                // C_T: connected to two pawns
                                                                                // PAS: Passed 
                                                                                // PCO: Passed and ConnectedOne 
                                                                                // PCT: Passed and ConnectedTwo 
         {  // ISO, BAC, FRO, C_O, C_T, PAS, PCO, PCT    Rank                     
            { 100, 100, 100, 100, 100, 100, 100, 100 }, // 1                    //Extended Berliner table  
            { 100, 102, 102, 103, 104, 108, 114, 117 }, // 2                    // Extended Berliner table
            { 103, 105, 105, 107, 108, 115, 128, 135 }, // 3                    // Extended Berliner table
            { 105, 110, 110, 115, 118, 130, 155, 168 }, // 4                
            { 130, 133, 133, 135, 137, 155, 230, 268 }, // 5         
            { 210, 223, 223, 235, 243, 255, 350, 398 }, // 6       
            { 220, 233, 233, 245, 253, 265, 360, 408 }, // 7                    // Extended Berliner table  
            { 100, 100, 100, 100, 100, 100, 100, 100 }, // 8                    // Extended Berliner table            
        };
        
        int MultiplierMultiplePawn[][]      =                                   // Extended Beeliner table
        { // Double     TripleOrMore      
          // Pawn       Pawn 
            { 33,       20 },                                                   // NoFellowAndNoOppoentPawnOnAdjacentFiles
            { 50,       30 },                                                   // NoFellowAndOpponentPawnOnAdjacentFile
            { 75,       45 },                                                   // FellowAndOpponentPawnOnOppositeAdjacentFile
            { 50,       30 },                                                   // FellowAndOpponentPawnOnSameAdjacentFile 
        };
        
        boolean FellowPawnOnAdjacentUpperFile;
        boolean FellowPawnOnAdjacentLowerFile;
        boolean OpponentPawnOnAdjacentUpperFile;
        boolean OpponentPawnOnAdjacentLowerFile;       
        int row_n;
        int PositionType;                    
        int FellowPawnsInFile;
        int Multiplier;
        
        int PawnValue                       = 0;
        int Rank                            = 0;
        int File;
        int PawnType                        = 0;
        int AdjacentIndex                   = 0;
        
        File = col - 1;
        
        switch(Pos[row][col])
        {
            case Position.WHITE_PAWN:
                Rank = row - 1;
                break;
                
            case Position.BLACK_PAWN:
                Rank = Position.ROWS - row;
                break;
        }
        
        switch(GamePhase)
        {
            case OPENING_GAME:
            case MIDDLE_GAME:
                PawnValue = PawnValueOpeningAndMiddleGame[Rank][File];
                break;
                
            case END_GAME:
                PawnValue = PawnValueEndgame[Rank][File];
                break;
        }
        
        //System.out.print("Pawn[" + row + "][" + col + "] ");
        PositionType = PawnPositionType(Pos, row, col);
        //ShowPawnType(Pos, row, col,  PositionType);
        switch(PositionType)
        {  
            case ISOLATED:
                PawnType = 0;
                //System.out.print("ISO ");
                break;
                    
            case BACK:
                PawnType = 1;
                //System.out.print("BAC ");
                break;  
                
            case FRONT:
                PawnType = 2;
                //System.out.print("FRO ");
                break;                      
                
            case CONNECTED_ONE:
                PawnType = 3;
                //System.out.print("C_O ");
                break;
            
            case CONNECTED_TWO:
                PawnType = 4;
                //System.out.print("C_T ");
                break;
                
            case PASSED:
                PawnType = 5;
                //System.out.print("PAS " );
                break;
                    
            case PASSED_AND_CONNECTED_ONE:
                PawnType = 6;
                //System.out.print("PCO ");
                break;
            
            case PASSED_AND_CONNECTED_TWO:
                PawnType = 7;
                //System.out.print("PCT ");
                break;                
                
        }
                   
        //System.out.print("Multiplier: Rank = " + Rank + "\t File = " + File + "\t");
        Multiplier = MultiplierPawnAdvances[Rank][PawnType];            
        PawnValue = (PawnValue * Multiplier)/100;
        
        if((PositionType == PASSED) || (PositionType == PASSED_AND_CONNECTED_ONE) || (PositionType == PASSED_AND_CONNECTED_TWO))
        {          
            return PawnValue;                                                   // Finished, no need to count for multiple pawns
        }
   
        FellowPawnsInFile = NumberOfFellowPawnsInFront(Pos, row, col);          // Identify if 0: Single, 1: Double or 2: Triple or more Pawn
        if(FellowPawnsInFile == 0)
        {
            return PawnValue;                                                   // Finished, Single Pawn
        }
        
        FellowPawnOnAdjacentUpperFile       = PawnOnFile(Pos, row, col, UPPER_FILE_DELTA , FELLOW);
        FellowPawnOnAdjacentLowerFile       = PawnOnFile(Pos, row, col, LOWER_FILE_DELTA , FELLOW);
        OpponentPawnOnAdjacentUpperFile     = PawnOnFile(Pos, row, col, UPPER_FILE_DELTA , OPPONENT);
        OpponentPawnOnAdjacentLowerFile     = PawnOnFile(Pos, row, col, LOWER_FILE_DELTA , OPPONENT);
        
        if(!FellowPawnOnAdjacentUpperFile && !FellowPawnOnAdjacentLowerFile && !OpponentPawnOnAdjacentUpperFile && !OpponentPawnOnAdjacentLowerFile)
        {
            AdjacentIndex = 0;                                                  // NoFellowAndNoOppoentPawnOnAdjacentFiles
        }
        
        if((!FellowPawnOnAdjacentUpperFile && !FellowPawnOnAdjacentLowerFile) && (OpponentPawnOnAdjacentUpperFile || OpponentPawnOnAdjacentLowerFile))
        {
            AdjacentIndex = 1;                                                  // NoFellowButOpponentPawnOnAdjacentFile
        }        
        
        if((FellowPawnOnAdjacentUpperFile && OpponentPawnOnAdjacentLowerFile) || (FellowPawnOnAdjacentLowerFile && OpponentPawnOnAdjacentUpperFile))
        {
            AdjacentIndex = 2;                                                  // FellowAndOpponentPawnOnOppositeAdjacentFile
        }        
        
        if((FellowPawnOnAdjacentUpperFile && OpponentPawnOnAdjacentUpperFile && !OpponentPawnOnAdjacentLowerFile) || 
           (FellowPawnOnAdjacentLowerFile && OpponentPawnOnAdjacentLowerFile && !OpponentPawnOnAdjacentUpperFile))
        {
            AdjacentIndex = 3;                                                  // FellowAndOpponentPawnOnSameAdjacentFile 
        }         

        if((AdjacentIndex < 0) || (AdjacentIndex > 3))
        {
           System.out.print("AdjacentIndex = " + AdjacentIndex + "is out or range");
        }
        
        if((FellowPawnsInFile < 1) || (FellowPawnsInFile > 2))
        {
            System.out.print("FellowPawnsInFile = " + FellowPawnsInFile + "is out or range");
        }
        
        Multiplier = MultiplierMultiplePawn[AdjacentIndex][FellowPawnsInFile - 1];    // FellowPawnsIn File == 1: Double pawn, use index 0, FellowPawnsIn File == 2: Triple of more pawn, use index 1     
        PawnValue = (PawnValue * Multiplier)/100; 
        
        
        if(Chess.ShowStatus > Settings.LOW)
        {
            Position.DisplayPosition(Pos);
            System.out.print("Pawn[" + row + "][" + col + "] = ");
            switch(FellowPawnsInFile)
            {
                case 1:
                    System.out.print("Double Pawn");
                    break;
                
                case 2:
                    System.out.print("Multiple Pawn");
                    break;        
            }
            switch(AdjacentIndex)
            {
                case 0:
                    System.out.println(" - No Fellow And No Opponent Pawn On Adjacent Files");
                    break;
                
                case 1:
                    System.out.println(" - No Fellow But Opponent Pawn On Adjacent File");
                    break;
                
                case 2:
                    System.out.println(" - Fellow And Opponent Pawn On Opposite Adjacent File");      
                    break;
                
                case 3:
                    System.out.println(" - Fellow And Opponent Pawn On Same Adjacent File");
                    break;
            }
            System.out.println("FellowPawnOnAdjacentUpperFile = "   + FellowPawnOnAdjacentUpperFile);
            System.out.println("FellowPawnOnAdjacentLowerFile = "   + FellowPawnOnAdjacentLowerFile); 
            System.out.println("OpponentPawnOnAdjacentUpperFile = " + OpponentPawnOnAdjacentUpperFile);   
            System.out.println("OpponentPawnOnAdjacentLowerFile = " + OpponentPawnOnAdjacentLowerFile);
            System.out.println("Multiplier = " + Multiplier);
        }
        return PawnValue; 
    }
        
    public static int PawnPositionType(int[][]Pos, int row,  int col)
    {
        int col_n;
        int col_step;
        
        int row_n;
        int row_start       = 0;
        int row_step        = 0;
        
        int PositionType;
        boolean PawnPassed;
            
        PositionType = ISOLATED;                                                // Default        
                                                                                // Identify if pawn is ISOLATED, FRONT, BACK, CONNECTED_ONE or CONNECTED_TWO
        loop_column: 
        for(col_step = -1; col_step < 2; col_step += 2)                         // Look at column down then column up
        {
            col_n = col + col_step;
            //System.out.println("col_n = " + col_n);
            
            if((col_n < 1) || (col_n > Position.COLS))
            {
                continue;
            }
            
            switch(Pos[row][col])
            {
                case Position.WHITE_PAWN:
                    row_start   = 2;
                    row_step    = 1;
                    break;
                
                case Position.BLACK_PAWN:
                    row_start   = 7;
                    row_step    = -1;
                    break;
            }            
            
            loop_row:
            for(row_n = row_start; ((row_n > 1) && (row_n < Position.ROWS)); row_n += row_step)          
            {                                                                   // Look at all rows
                if(((Pos[row][col] == Position.WHITE_PAWN) && (Pos[row_n][col_n] == Position.WHITE_PAWN)) ||
                   ((Pos[row][col] == Position.BLACK_PAWN) && (Pos[row_n][col_n] == Position.BLACK_PAWN)))
                {
                    if ((((Pos[row][col] == Position.WHITE_PAWN) && ((row_n - row) < -1)) || ((Pos[row][col] == Position.BLACK_PAWN) && ((row_n - row) > -1))) && (PositionType == ISOLATED))
                    {
                        PositionType = FRONT;                                   // FRONT overrules ISOLATED
                    }
                    
                    if ((((Pos[row][col] == Position.WHITE_PAWN) && ((row_n - row) > -1)) || ((Pos[row][col] == Position.BLACK_PAWN) && ((row_n - row) < -1))) && ((PositionType == ISOLATED) || (PositionType == FRONT)))                    {
                        PositionType = BACK;                                    // BACK overrules ISOLATED or FRONT
                    }
                                      
                    if (((row_n - row) >=  -1) && ((row_n - row) <=  1))
                    {
                        //System.out.println("row_n = " + row_n + " row = " + row);
                        if(PositionType != CONNECTED_ONE)
                        {
                            PositionType = CONNECTED_ONE;                       // Set PositionType to CONNECTED_ONE unless it is already set to CONNECTED_ONE
                            //System.out.println("Connected one");
                            break loop_row;                                     // Can't get a different type for this column, go to next column
                        }
                        else
                        {
                            PositionType = CONNECTED_TWO;                       // Set PositionType to CONNECTED_TWO since it was already set to CONNECTED_ONE
                            break loop_column;                                  // Can't get a different type from here on => exit loop
                        }
                    }                   
                }
            }        
        }
            
        // Identify if pawn passed
        PawnPassed = true; 
        loop_passed:
        for(col_step = -1; col_step < 2; col_step++)                            // Look at column down, own column and column up
        {       
            col_n = col + col_step;            
            if((col_n < 1) || (col_n > Position.COLS))    
            {
                continue;
            }            
            
            for(row_n = row_start + row_step; ((row_n > 1) && (row_n < Position.ROWS)); row_n += row_step)           
            {                                                                   // Look for opponent pawn at all rows in front of own pawn
                if(((Pos[row][col] == Position.WHITE_PAWN) && (Pos[row_n][col_n] == Position.BLACK_PAWN)) ||
                   ((Pos[row][col] == Position.BLACK_PAWN) && (Pos[row_n][col_n] == Position.WHITE_PAWN)))
                {
                    PawnPassed = false;
                    break loop_passed;
                }
            }  
        }
            
        if(PawnPassed)
        {
            switch(PositionType)
            {
                case ISOLATED:
                case FRONT:
                case BACK:
                    PositionType = PASSED;
                    break;
                
                case CONNECTED_ONE:
                    PositionType = PASSED_AND_CONNECTED_ONE;
                    break;
                    
                case CONNECTED_TWO:
                    PositionType = PASSED_AND_CONNECTED_TWO;
                    break;
            }
        }
        return PositionType;
    }
    
    public static int NumberOfFellowPawnsInFront(int[][]Pos, int row,  int col)
    {
        int PawnsInFront    = 0;
        int PawnStep        = 0;
        int row_n;
        
        switch(Pos[row][col])
        {
            case Position.WHITE_PAWN:  
                PawnStep = 1;
                break;
                    
            case Position.BLACK_PAWN:
                PawnStep = -1;
                break;
        }
        
        for(row_n = row + PawnStep; ((row_n > 1) && (row_n < Position.ROWS)); row_n += PawnStep)
        {
            if(((Pos[row][col] == Position.WHITE_PAWN) && (Pos[row_n][col] == Position.WHITE_PAWN)) ||
               ((Pos[row][col] == Position.BLACK_PAWN) && (Pos[row_n][col] == Position.BLACK_PAWN)))
            {
                PawnsInFront++;
                if(PawnsInFront == 2)                
                {
                    break;                                                      // Two or more pawns in front makes it a triple pawn
                }
            }
        }
                
        return PawnsInFront;
    }
    
    public static boolean PawnOnFile(int[][] Pos, int row, int col, int File, int Type)
    {
        int row_n;
        int row_start;
        int row_step;
        int col_n;
        int PawnCompare;
        int OppositePawnCompare;
        int PawnStep;
        
        col_n = col + File;
        
        if((col_n < 1) || (col_n > Position.COLS))
        {
            return false;
        }
         
        if(((Type == FELLOW) && (Pos[row][col] == Position.WHITE_PAWN)) || ((Type == OPPONENT) && (Pos[row][col] == Position.BLACK_PAWN)))
        {
            row_start               = 2;
            row_step                = 1;
            PawnCompare             = Position.WHITE_PAWN;
            OppositePawnCompare     = Position.BLACK_PAWN;          
        }
        else
        {
            row_start               = 7;
            row_step                = -1;
            PawnCompare             = Position.BLACK_PAWN;
            OppositePawnCompare     = Position.WHITE_PAWN; 
        }

        for(row_n = row_start; ((row_n > 1) && (row_n < Position.ROWS)); row_n += row_step)
        {

            if(((Pos[row][col] == Position.WHITE_PAWN) && (Pos[row_n][col_n] == PawnCompare)) ||
               ((Pos[row][col] == Position.BLACK_PAWN) && (Pos[row_n][col_n] == PawnCompare)))
            {
                return true;  
            }
            
            if(((Pos[row][col] == Position.WHITE_PAWN) && (Pos[row_n][col] == OppositePawnCompare)) ||
               ((Pos[row][col] == Position.BLACK_PAWN) && (Pos[row_n][col] == OppositePawnCompare)))           
            {
                break;
            }             
        }
        return false;
    }
    
    public static int GetPositionType(int[][] Pos)
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
    
    public static void ShowPositionType(int Type)
    {
        System.out.print("Position type:\t ");
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
    
    public static void ShowPawnType(int[][]Pos, int row, int col, int Type)
    {
        System.out.print("Pawn[" + row + "][" + col + "] is of type: ");
        switch(Type)
        {
            case ISOLATED:
                System.out.println("Isolated");
                break;
            
            case BACK:
                System.out.println("Back");
                break;
                
           case FRONT:
                System.out.println("Front");
                break;
                
           case PASSED:
                System.out.println("Passed");
                break;            
                
           case CONNECTED_ONE:
                System.out.println("Connected to one pawn");
                break; 
                
            case CONNECTED_TWO:
                System.out.println("Connetced to two pawns");
                break;
                
            case PASSED_AND_CONNECTED_ONE:
                System.out.println("Passed and connected to one pawn");
                break;
                
           case PASSED_AND_CONNECTED_TWO:
                System.out.println("Passed and connected to two pawns");
                break;               
        }
    }
    
    public static void ShowGamePhase(int Phase)
    {
        System.out.print("Game phase:\t ");
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
        
        }
    }
    

}
