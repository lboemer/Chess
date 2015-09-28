// YouTube Video = Programmng a Chess Engine in C 
public class Knight
{
    public static int FieldValue[][] =
    {  //   A    B    C,   D,   E,   F,  G,   H      Rank  
        {   0,  -15,  0,   0,   0,    0, -15,    0 }, // 1                            
        {   0,   0,   0,   8,   8,    0,   0,    0 }, // 2                      
        {   0,   0,  15,  15,  15,   15,   0,    0 }, // 3                      
        {   0,   8,  15,  30,  30,   15,   5,    0 }, // 4                      
        {   8,  15,  22,  30,  30,   33,  15,    8 }, // 5                      
        {   8,  15,  15,  30,  30,   10,  15,    8 }, // 6                      
        {   0,   0,   8,  15,  15,    8,   0,    0 }, // 7    
        {   0,   0,   0,   0,   0,    0,   0,    0 }, // 8                             
    }; 
    
    public static int PositionTypeValue[] = 
    {    
        0,      // UNDEFINED                       
       -3,      // WIDE_OPEN  
       -2,       // OPEN                              
       -1,      // SEMI_OPEN                             
        0,      // AVERAGE                         
        5,      // CLOSED    
       10,      // BLOCKED                         
    };
    
    public static int GamePhaseValue[] = 
    {
        3,      // OPENING_GAME
        6,      // MIDDLE_GAME                    
        0,      // END_GAME
    };
    
    
    public static int Rating(int[][] Pos, int row, int col, int PositionType, int GamePhase)
    {
        /* Debug messages begin  
        int Rank = 0;
        
        switch(Piece.Pieces[Pos[row][col]].getColor())
        {
            case Position.WHITE:
                Rank = row - 1;
                break;
                
            case Position.BLACK:
                Rank = Position.ROWS - row;
                break;
        }     
        
   
        int Rat                 = Piece.Pieces[Pos[row][col]].getRating();
        int FieldAdder          = FieldValue[Rank][col-1];
        int PositionTypeAdder   = PositionTypeValue[PositionType]; 
        int GamePhaseAdder      = GamePhaseValue[GamePhase];
        int Total               = Rat + FieldAdder + PositionTypeAdder + GamePhaseAdder;        
        
        System.out.print(Piece.Pieces[Pos[row][col]].getConsoleNotation() + "[" + row + "][" + col + "]");
        System.out.print("Rat = " + Rat + " FieldAdder = " + FieldAdder + " PositionTypeAdder = " + PositionTypeAdder + " GamePhaseAdder = " + GamePhaseAdder); 
        System.out.println(" Total  = " + Total);
        */ // Debug messages end
             
        switch(Piece.Pieces[Pos[row][col]].getColor())
        {
            case Position.WHITE:
                return(Piece.Pieces[Pos[row][col]].getRating() 
                    + FieldValue[row-1][col-1]
                    + PositionTypeValue[PositionType] 
                    + GamePhaseValue[GamePhase]);
                
            case Position.BLACK:
                return(Piece.Pieces[Pos[row][col]].getRating() 
                    - FieldValue[Position.ROWS - row][col - 1]
                    - PositionTypeValue[PositionType] 
                    - GamePhaseValue[GamePhase]);
        }             
        return 0;     // Statement is not reached, a return value is still required 
    }
}