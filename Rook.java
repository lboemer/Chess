
// YouTube Video = Programmng a Chess Engine in C 
public class Rook
{
    public static int FieldValue[][]   =
    {  //   A    B    C,   D,   E,   F,  G,   H      Rank  
        {   0,   0,   5,  10, 10,    5,  0,    0 }, // 1                            
        {   0,   0,   5,  10, 10,    5,  0,    0 }, // 2                      
        {   0,   0,   5,  10, 10,    5,  0,    0 }, // 3                      
        {   0,   0,   5,  10, 10,    5,  0,    0 }, // 4                      
        {   0,   0,   5,  10, 10,    5,  0,    0 }, // 5                      
        {   0,   0,   5,  10, 10,    5,  0,    0 }, // 6                      
        {  25,  25,  25,  25, 25,   25, 25,   25 }, // 7    
        {   0,   0,   5,  10, 10,    5,  0,    0 }, // 8                              
    }; 
    
    public static int FileTypeValue[] = 
    { 
        0,      // CLOSED_FILE
        5,      // SEMI_OPEN+FILE
        10,     // OPEN_FILE
    };
    
    public static int PositionTypeValue[] = 
    {    
        0,      // UNDEFINED                      
        10,     // WIDE_OPEN  
        8,      // OPEN                              
        5,      // SEMI_OPEN                             
        0,      // AVERAGE                         
       -5,      // CLOSED    
      -10,      // BLOCKED                         
    };
    
    public static int GamePhaseValue[] = 
    {
        0,      // OPENING_GAME
        5,      // MIDDLE_GAME                    
       10,      // END_GAME
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
                return(Piece.Pieces[Pos[row][col]].getRating()         
                    + FieldValue[row-1][col-1]
                    + FileTypeValue[Rating.FileType(Pos, col)]
                    + PositionTypeValue[PositionType] 
                    + GamePhaseValue[GamePhase]);

            case Position.BLACK:
                return(Piece.Pieces[Pos[row][col]].getRating()         
                    - FieldValue[Position.ROWS - row][col-1]
                    - FileTypeValue[Rating.FileType(Pos, col)]
                    - PositionTypeValue[PositionType] 
                    - GamePhaseValue[GamePhase]);            
        }                 
        return 0;       // Statement is not reached, a return value is still required 
    }
}