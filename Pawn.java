
// Hans Berliner (1999), The System: A World Champions Approach to Chess
import java.lang.*;                                                             // used for max()
import java.util.*;                                                             // For scanner

public class Pawn
{
    public static int PawnValueOpeningAndMiddleGame[][]   =
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
        
    public static int PawnValueEndgame[][]   =
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
        
    public static int MultiplierPawnAdvances[][]       =
    // C_T =       C_O + (C_O - ISO) * 0.3                                  // Source: Leo         
    // BAC = FRO = ISO + (C_O - ISO) * 0.5                                  // Source: Leo
    // PCT =       PCO + (PCO - PAS) * 0.5                                  // Source: Leo
                                                                            // ISO: Isolated
                                                                            // BAC: Back 
                                                                            // FRO: FRONT
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
      
    public static int MultiplierMultiplePawn[][]      =                     // Extended Beeliner table
    { // Double     TripleOrMore      
      // Pawn       Pawn 
        { 33,       20 },                                                   // 0: NoFellowAndNoOppoentPawnOnAdjacentFiles
        { 50,       30 },                                                   // 1: NoFellowAndOpponentPawnOnAdjacentFile
        { 75,       45 },                                                   // 2: FellowAndOpponentPawnOnOppositeAdjacentFile
        { 50,       30 },                                                   // 3: FellowAndOpponentPawnOnSameAdjacentFile 
    };

    // Pawn Position type
    public static final int ISOLATED                        = 0;      
    public static final int BACK                            = 1;   
    public static final int FRONT                           = 2;        
    public static final int CONNECTED_ONE                   = 3;      
    public static final int CONNECTED_TWO                   = 4; 
    public static final int PASSED                          = 5;     
    public static final int PASSED_AND_CONNECTED_ONE        = 6;
    public static final int PASSED_AND_CONNECTED_TWO        = 7;      
    
    // Number of fellow pawns in front
    public static final int SINGLE_PAWN                     = 0;
    public static final int DOUBLE_PAWN                     = 1;
    public static final int MULTIPLE_PAWN                   = 2;    
     
    public static int Rating(int[][] Pos, int row, int col, int GamePhase)
    {
        final int UPPER_FILE_DELTA          = 1;
        final int LOWER_FILE_DELTA          = -1;        

        boolean FellowPawnOnAdjacentUpperFile;
        boolean FellowPawnOnAdjacentLowerFile;
        boolean OpponentPawnOnAdjacentUpperFile;
        boolean OpponentPawnOnAdjacentLowerFile;       
        int row_n;
        int FellowPawnsInFile;
        int Multiplier;
        
        int PawnValue                       = 0;
        int Rank                            = 0;
        int File                            = col - 1;
        int PawnType                        = 0;
        int AdjacentIndex                   = 0;
        int ColorMultiplier                 = 0;
        
        Scanner scanner             = new Scanner(System.in);
        
        switch(Piece.Pieces[Pos[row][col]].getColor())
        {
            case Position.WHITE:
                Rank = row - 1;
                ColorMultiplier = 1;
                break;
                
            case Position.BLACK:
                Rank = Position.ROWS - row;
                ColorMultiplier = -1;
                break;
        } 
        
        switch(GamePhase)
        {
            case Rating.OPENING_GAME:
            case Rating.MIDDLE_GAME:
                PawnValue = PawnValueOpeningAndMiddleGame[Rank][File];
                break;
                
            case Rating.END_GAME:
                PawnValue = PawnValueEndgame[Rank][File];
                break;
        }
        
        PawnType            = PawnPositionType(Pos, row, col);
        Multiplier          = MultiplierPawnAdvances[Rank][PawnType];            
        PawnValue           = (PawnValue * Multiplier)/100;
        FellowPawnsInFile   = NumberOfFellowPawnsInFront(Pos, row, col);        // Identify if 0: SINGLE_PAWN, 1: DOUBLE_PAWN, 3: MULTIPLE_PAWN        
        
        if((PawnType == PASSED) || (PawnType == PASSED_AND_CONNECTED_ONE) || (PawnType == PASSED_AND_CONNECTED_TWO) || (FellowPawnsInFile == SINGLE_PAWN))
        {          
            /* Begin debug messages
            Position.DisplayPosition(Pos);
            System.out.print(Piece.Pieces[Pos[row][col]].getConsoleNotation() + "[" + row + "][" + col + "]");
            System.out.println("PawnType = " + PawnType + " Multiplier = " + Multiplier + " PawnValue = " + PawnValue + " FellowPawnsInFile = " + FellowPawnsInFile);
            scanner.nextLine(); 
            */ // End debug messages
            
            return(PawnValue * ColorMultiplier);                                // Finished, no need to count for multiple pawns
        }
        
        FellowPawnOnAdjacentUpperFile       = PawnOnFile(Pos, row, col, UPPER_FILE_DELTA , Rating.FELLOW);
        FellowPawnOnAdjacentLowerFile       = PawnOnFile(Pos, row, col, LOWER_FILE_DELTA , Rating.FELLOW);
        OpponentPawnOnAdjacentUpperFile     = PawnOnFile(Pos, row, col, UPPER_FILE_DELTA , Rating.OPPONENT);
        OpponentPawnOnAdjacentLowerFile     = PawnOnFile(Pos, row, col, LOWER_FILE_DELTA , Rating.OPPONENT);
        
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
        
        if((FellowPawnsInFile < 1) || (FellowPawnsInFile > 2))
        {
            System.out.print("FellowPawnsInFile = " + FellowPawnsInFile + "is out of range");
        }
        assert(FellowPawnsInFile == 1 || FellowPawnsInFile == 2);
        
        Multiplier = MultiplierMultiplePawn[AdjacentIndex][FellowPawnsInFile - 1];    // FellowPawnsIn File == 1: Double pawn, use index 0, FellowPawnsIn File == 2: Triple of more pawn, use index 1     
        PawnValue = (PawnValue * Multiplier)/100; 

        if(Chess.ShowStatus > Settings.LOW)
        {
            Position.Display(Pos);
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
        return(PawnValue * ColorMultiplier); 
    }
        
    public static int PawnPositionType(int[][]Pos, int row,  int col)
    {
        int col_n;
        int col_step;
        
        int row_n;
        int row_start       = 0;
        int row_step        = 0;
        
        int PositionType    = ISOLATED;          // Default       
        boolean PawnPassed;
            
        loop_column: 
        for(col_step = -1; col_step <= 1; col_step += 2)                         // Look at column down then column up
        {
            col_n = col + col_step;
            
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
                        PositionType = FRONT;                                   // ISOLATED condition avoids that Tyep is set to FRONT if type was set to different than ISOLATED from fisrt column 
                    }
                                 
                    if (((row_n - row) >=  -1) && ((row_n - row) <=  1))
                    {
                        if(PositionType != CONNECTED_ONE)
                        {
                            PositionType = CONNECTED_ONE;                       // Set PositionType to CONNECTED_ONE unless it is already set to CONNECTED_ONE
                            //System.out.println("Connected one");
                            break loop_row;                                     // Can't get a different type for this column, go to next column
                        }
                        else
                        {
                            PositionType = CONNECTED_TWO;                       // Set PositionType to CONNECTED_TWO since it was already set to CONNECTED_ONE from first column
                            break loop_column;                                  // Can't get a different type from here on => exit loop
                        }
                    }     
                    
                    if ((((Pos[row][col] == Position.WHITE_PAWN) && ((row_n - row) > 1)) || ((Pos[row][col] == Position.BLACK_PAWN) && ((row_n - row) < 1))) && ((PositionType == ISOLATED) || (PositionType == FRONT)))                    {
                        PositionType = BACK;                                    // BACK overrules ISOLATED or FRONT
                    }                    
                    
                }
            }        
        }
            
        // Identify if pawn passed
        PawnPassed = true; 
        loop_passed:
        for(col_step = -1; col_step <= 1; col_step++)                            // Look at column down, own column and column up
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
         
        if(((Type == Rating.FELLOW) && (Pos[row][col] == Position.WHITE_PAWN)) || ((Type == Rating.OPPONENT) && (Pos[row][col] == Position.BLACK_PAWN)))
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
}
