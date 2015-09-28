// YouTube Video = Programmng a Chess Engine in C 
public class King
{
    public static int FieldValueOpeningGame[][] =               // Incentive to stay on row 1/8 and get out of center
    {  //   A    B    C,   D,   E,   F,  G,    H    Rank  
        {   0,   5,   5, -10, -10,   0,  10,   0 }, // 1                            
        { -10, -10, -10, -10, -10, -10, -10, -10 }, // 2 
        { -30, -30, -30, -30, -30, -30, -30, -30 }, // 3   
        { -70, -70, -70, -70, -70, -70, -70, -70 }, // 4   
        { -75, -75, -75, -75, -75, -75, -75, -75 }, // 5    
        { -80, -80, -80, -80, -80, -80, -80, -80 }, // 6
        { -85, -85, -85, -85, -85, -85, -85, -85 }, // 7 
        { -90, -90, -90, -90, -90, -90, -90, -90 }, // 8                                      
    }; 
    
    public static int FieldValueEndGame[][] =                   // Incentive to go to centre and stay away from corners
    {  //   A    B    C,   D,   E,   F,  G,    H    Rank  
        { -50, -20,   0,   0,   0,   0, -20, -50 }, // 1                            
        { -20,   0,  20,  20,  20,  20,   0, -20 }, // 2                      
        {   0,  20,  40,  40,  40,  40,  20,   0 }, // 3                      
        {   0,  20,  40,  50,  50,  40,  20,   0 }, // 4                      
        {   0,  20,  40,  50,  50,  40,  20,   0 }, // 5                      
        {   0,  20,  40,  40,  40,  40,  20,   0 }, // 6                      
        { -20,   0,  20,  20,  20,  20,   0, -20 }, // 7    
        { -50, -20,   0,   0,   0,   0, -20, -50 }, // 8                              
    };     
  
    
    public static int Rating(int[][] Pos, int row, int col, int GamePhase)
    {
        /* Debug messages begin
        int FieldAdder  = 0;
        int Rat         = Piece.Pieces[Pos[row][col]].getRating();      
        
        switch(Piece.Pieces[Pos[row][col]].getColor())
        {
            case Position.WHITE:
                switch(GamePhase)
                {
                    case Rating.OPENING_GAME:
                    case Rating.MIDDLE_GAME:   
                        FieldAdder = FieldValueOpeningGame[row - 1][col - 1];
                        break;
          
                    case Rating.END_GAME:
                        FieldAdder = FieldValueEndGame[row - 1][col - 1];    
                        break;
                }
                break;
            
            case Position.BLACK:
                switch(GamePhase)
                {
                    case Rating.OPENING_GAME:
                    case Rating.MIDDLE_GAME:   
                        FieldAdder = -FieldValueOpeningGame[Position.ROWS - row][col - 1];
                        break;
          
                    case Rating.END_GAME:
                        FieldAdder = -FieldValueEndGame[Position.ROWS - row][col - 1];       
                        break;
                }   
                break;
        }        
        int Total = Rat + FieldAdder;        
        
        System.out.print(Piece.Pieces[Pos[row][col]].getConsoleNotation() + "[" + row + "][" + col + "]");
        System.out.print(" Rat = " + Rat + " FieldAdder = " + FieldAdder); 
        System.out.println(" Total  = " + Total);        
        */ // Debug messages end
        
        switch(Piece.Pieces[Pos[row][col]].getColor())
        {
            case Position.WHITE:
                switch(GamePhase)
                {
                    case Rating.OPENING_GAME:
                    case Rating.MIDDLE_GAME:   
                        return(Piece.Pieces[Pos[row][col]].getRating() + FieldValueOpeningGame[row - 1][col - 1]);    
                        
                    case Rating.END_GAME:
                        return(Piece.Pieces[Pos[row][col]].getRating() + FieldValueEndGame[row - 1][col - 1]);                    
                }
                break;
            
            case Position.BLACK:
                switch(GamePhase)
                {
                    case Rating.OPENING_GAME:
                    case Rating.MIDDLE_GAME:   
                        return(Piece.Pieces[Pos[row][col]].getRating() - FieldValueOpeningGame[Position.ROWS - row][col - 1]);
          
                    case Rating.END_GAME:
                        return(Piece.Pieces[Pos[row][col]].getRating() - FieldValueEndGame[Position.ROWS - row][col - 1]);                    
                }   
                break;
        }
        return 0;       // Statement is not reached, a return value is still required   
    }
}