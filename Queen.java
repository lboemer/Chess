// YouTube Video = Programmng a Chess Engine in C 
public class Queen
{
    public static int FieldValueOpeningGame[][] =               // by Leo, need to think about it more, the idea is to not get the queen out too early
    {  //   A    B    C,   D,   E,   F,  G,   H    Rank         // The idea is to not get the queen out too early
        {   0,   0,   0,   0,   0,   0,   0,   0 }, // 1                            
        { -10, -10, -10, -10, -10, -10, -10, -10 }, // 2 
        { -30, -30, -30, -30, -30, -30, -30, -30 }, // 3   
        { -70, -70, -70, -70, -70, -70, -70, -70 }, // 4   
        { -75, -75, -75, -75, -75, -75, -75, -75 }, // 5    
        { -80, -80, -80, -80, -80, -80, -80, -80 }, // 6
        { -85, -85, -85, -85, -85, -85, -85, -85 }, // 7 
        { -90, -90, -90, -90, -90, -90, -90, -90 }, // 8            
    }; 
    
    public static int FieldValueEndGame[][] =                   //QueenFieldValue = RookFieldValue + BishopFieldValue  ....invented by Leo
    {  //   A    B    C,   D,   E,   F,  G,   H    Rank  
        {   0,   0,  -5,  10,  10,  -5,  0,   0 }, // 1                            
        {   0,   0,   5,  20,  20,   5,  0,   0 }, // 2                      
        {   0,   0,  15,  25,  25,  15,  0,   0 }, // 3                      
        {   0,  10,  20,  30,  30,  20, 10,   0 }, // 4                      
        {   0,  10,  20,  30,  30,  20, 10,   0 }, // 5                      
        {   0,   0,  15,  25,  25,  15,  0,   0 }, // 6                      
        {  25,  25,  25,  35,  35,  25, 25,  25 }, // 7    
        {   0,   0,   5,  10,  10,   5,  0,   0 }, // 8                              
    };     
    public static int FileTypeValue[] = 
    { 
        0,      // CLOSED_FILE
        7,      // SEMI_OPEN+FILE
        15,     // OPEN_FILE
    };    
    
    public static int PositionTypeValue[] = 
    {    
        0,      // UNDEFINED                       
       15,      // WIDE_OPEN  
       10,      // OPEN                              
        7,      // SEMI_OPEN                             
        0,      // AVERAGE                         
       -7,      // CLOSED    
       -15,      // BLOCKED                         
    };
    
    public static int GamePhaseValue[] = 
    {
        0,      // OPENING_GAME
       10,      // MIDDLE_GAME                    
       20,      // END_GAME
    };
    
    
    public static int Rating(int[][] Pos, int row, int col, int PositionType, int GamePhase)
    {   
        /* Begin debug messages
        int Rat                 = 0;           
        int FieldAdder          = 0;          
        int FileTypeAdder       = 0;       
        int PositionTypeAdder   = 0; 
        int GamePhaseAdder      = 0;    
        int Total               = 0;              
       
        switch(Piece.Pieces[Pos[row][col]].getColor())
        {
            case Position.WHITE:
                System.out.print("White"); 
                Rat                 = Piece.Pieces[Pos[row][col]].getRating();     
                FieldAdder          = FieldValue[row-1][col-1];
                FileTypeAdder       = FileTypeValue[Rating.FileType(Pos, col)];
                PositionTypeAdder   = PositionTypeValue[PositionType];
                GamePhaseAdder      = GamePhaseValue[GamePhase];
                Total               = Rat + FieldAdder  + FileTypeAdder + PositionTypeAdder + GamePhaseAdder;
                break;
 
            case Position.BLACK:
                System.out.print("Black");             
                Rat                 = Piece.Pieces[Pos[row][col]].getRating();       
                FieldAdder          = -FieldValue[Position.ROWS - row][col-1];
                FileTypeAdder       = -FileTypeValue[Rating.FileType(Pos, col)];
                PositionTypeAdder   = -PositionTypeValue[PositionType]; 
                GamePhaseAdder      = -GamePhaseValue[GamePhase];
                Total               = Rat + FieldAdder  + FileTypeAdder + PositionTypeAdder + GamePhaseAdder;          
                break;
        }
        System.out.print(" " + Piece.Pieces[Pos[row][col]].getConsoleNotation() + "[" + row + "][" + col + "]");
        System.out.print("Rat = " + Rat + " FieldAdder = " + FieldAdder + " FileTypeAdder = " + FileTypeAdder + " PositionTypeAdder = " + PositionTypeAdder + " GamePhaseAdder = " + GamePhaseAdder); 
        System.out.println(" Total = " + Total);        
        */ // End debug messages
        
        switch(Piece.Pieces[Pos[row][col]].getColor())
        {
            case Position.WHITE:
                switch(GamePhase)
                {
                    case Rating.OPENING_GAME:
                    case Rating.MIDDLE_GAME:   
                        return(Piece.Pieces[Pos[row][col]].getRating()         
                            + FieldValueOpeningGame[row-1][col-1]
                            + FileTypeValue[Rating.FileType(Pos, col)]
                            + PositionTypeValue[PositionType] 
                            + GamePhaseValue[GamePhase]);                    

                    case Rating.END_GAME:
                        return(Piece.Pieces[Pos[row][col]].getRating()         
                            + FieldValueEndGame[row-1][col-1]
                            + FileTypeValue[Rating.FileType(Pos, col)]
                            + PositionTypeValue[PositionType] 
                            + GamePhaseValue[GamePhase]);                    
                }
                break;

            case Position.BLACK:
                switch(GamePhase)
                {
                    case Rating.OPENING_GAME:
                    case Rating.MIDDLE_GAME:   
                        return(Piece.Pieces[Pos[row][col]].getRating()         
                            - FieldValueOpeningGame[Position.ROWS - row][col - 1]
                            - FileTypeValue[Rating.FileType(Pos, col)]
                            - PositionTypeValue[PositionType] 
                            - GamePhaseValue[GamePhase]);                    

                    case Rating.END_GAME:
                        return(Piece.Pieces[Pos[row][col]].getRating()         
                            - FieldValueEndGame[Position.ROWS - row][col - 1]
                            - FileTypeValue[Rating.FileType(Pos, col)]
                            - PositionTypeValue[PositionType] 
                            - GamePhaseValue[GamePhase]);                    
                }            
                break;
        }                 
        return 0;       // Statement is not reached, a return value is still required                   
    }
}