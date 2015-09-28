
// YouTube Video = Programmng a Chess Engine in C 
public class Bishop
{
    public static int FieldValue[][] =
    {  //   A    B    C,   D,   E,   F,  G,   H      Rank  
        {   0,   0, -10,   0,   0,  -10,  0,    0 }, // 1                            
        {   0,   0,   0,  10,  10,    0,  0,    0 }, // 2                      
        {   0,   0,  10,  15,  15,   10,  0,    0 }, // 3                      
        {   0,  10,  15,  20,  20,   15, 10,    0 }, // 4                      
        {   0,  10,  15,  20,  20,   15, 10,    0 }, // 5                      
        {   0,   0,  10,  15,  15,   10,  0,    0 }, // 6                      
        {   0,   0,   0,  10,  10,    0,  0,    0 }, // 7    
        {   0,   0,   0,   0,   0,    0,  0,    0 }, // 8                              
    }; 
    
    public static int PositionTypeValue[] = 
    {    
        0,      // UNDEFINED                       
        6,      // WIDE_OPEN  
        5,      // OPEN                              
        3,      // SEMI_OPEN                             
        0,      // AVERAGE                         
       -1,      // CLOSED    
       -6,      // BLOCKED                         
    };
    
    public static int GamePhaseValue[] = 
    {
        0,      // OPENING_GAME
        3,      // MIDDLE_GAME                    
        6,      // END_GAME
    };
    
    
    public static int Rating(int[][] Pos, int row, int col, int PositionType, int GamePhase)
    {                
        /* Begin debug messages
        int Rat                 = 0;           
        int FieldAdder          = 0;               
        int PositionTypeAdder   = 0; 
        int GamePhaseAdder      = 0;    
        int Total               = 0;              
       
        switch(Piece.Pieces[Pos[row][col]].getColor())
        {
            case Position.WHITE:
                System.out.print("White"); 
                Rat                 = Piece.Pieces[Pos[row][col]].getRating();     
                FieldAdder          = FieldValue[row-1][col-1];
                PositionTypeAdder   = PositionTypeValue[PositionType];
                GamePhaseAdder      = GamePhaseValue[GamePhase];
                break;
 
            case Position.BLACK:
                System.out.print("Black");             
                Rat                 = Piece.Pieces[Pos[row][col]].getRating();       
                FieldAdder          = -FieldValue[Position.ROWS - row][col-1];
                PositionTypeAdder   = -PositionTypeValue[PositionType]; 
                GamePhaseAdder      = -GamePhaseValue[GamePhase];  
                break;
        }
        Total               = Rat + FieldAdder + PositionTypeAdder + GamePhaseAdder;        
        System.out.print(" " + Piece.Pieces[Pos[row][col]].getConsoleNotation() + "[" + row + "][" + col + "]");
        System.out.print("Rat = " + Rat + " FieldAdder = " + FieldAdder + " PositionTypeAdder = " + PositionTypeAdder + " GamePhaseAdder = " + GamePhaseAdder); 
        System.out.println(" Total = " + Total);        
        */ // End debug messages
        
        switch(Piece.Pieces[Pos[row][col]].getColor())
        {
            case Position.WHITE:
                return(Piece.Pieces[Pos[row][col]].getRating()         
                    + FieldValue[row-1][col-1]
                    + PositionTypeValue[PositionType] 
                    + GamePhaseValue[GamePhase]);

            case Position.BLACK:
                return(Piece.Pieces[Pos[row][col]].getRating()         
                    - FieldValue[Position.ROWS - row][col-1]
                    - PositionTypeValue[PositionType] 
                    - GamePhaseValue[GamePhase]);            
        }                 
        return 0;       // Statement is not reached, a return value is still required                       
    }
}
