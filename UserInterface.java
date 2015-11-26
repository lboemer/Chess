import java.util.*;  // For scanner
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class UserInterface extends JPanel implements MouseListener, MouseMotionListener
{
    public static final int SQUARE_SIZE                         = 64;  
    public static final int NUMBER_OF_PROMOTION_FIGURES         = 4;
    public static final int X_PROMOTION_FRAME_SQUARE_OFFSET     = 1;
    public static final int Y_PROMOTION_FRAME_SQUARE_OFFSET     = 3;    
    public static final int PROMOTION_FRAME_SQUARE_LENGTH       = NUMBER_OF_PROMOTION_FIGURES + 1;
    public static final int PROMOTION_FRAME_SQUARE_HEIGHT       = 2;  
    
    public static final int COL_FIRST_PROMOTION_FIGURE          = 3;      

    static boolean LeftMouseButtonPressed                       = false;
    static boolean WhitePromotionFigure                         = false;
    static boolean BlackPromotionFigure                         = false;     
    static boolean MouseInputActive                             = false;
        
    public static int[][] PosDrawBuffer = new int[Position.COLS+1][Position.ROWS+1];

    int[][] FillMoveList = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];
    int[][] FillMoveHistory = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];   
    
    int Figure_n;    
    
    static String srt;    
    static int col_f, row_f, col_n, row_n;    
    static int col_p; // Column promotion figure selected
        
    public static String[] MoveTable = new String[Move.MAX_NUMBER_MOVE_LIST];
    
    public void repaintWindow(int[][] Pos) 
    {
        Position.Copy(Pos, PosDrawBuffer);
        repaint();  // Draw position from PosDrawBuffer[][]
    }

    @Override
    public void paintComponent(Graphics g)
    {
        int i;
        int row;
        int col;
        int row_d;
        int col_d;
        int m;
        
        super.paintComponent(g);    // Redraws everytime, otherwise it would add to drawing
        this.setBackground(Color.yellow);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        //System.out.println("In paint() beginning: WhitePromotionFigure = " + WhitePromotionFigure + "BlackPromotionFigure = " + BlackPromotionFigure);
        
        // Draws two background fields in alternate colors
        for (i = 0; i  < Position.ROWS * Position.COLS; i += 2)  
        {
            g.setColor(new Color(255,200,100));
            g.fillRect(
                (i % Position.ROWS + (i / Position.ROWS) % 2) * SQUARE_SIZE, 
                (i / Position.ROWS) * SQUARE_SIZE, 
                SQUARE_SIZE, SQUARE_SIZE
            );
            g.setColor(new Color(150,50,30));
            g.fillRect(
                ((i+1) % Position.ROWS - ((i+1) / Position.ROWS) % 2) * SQUARE_SIZE, 
                ((i+1) / Position.ROWS) * SQUARE_SIZE, 
                SQUARE_SIZE, SQUARE_SIZE
            );                
        }
        
        if(LeftMouseButtonPressed && !WhitePromotionFigure && !BlackPromotionFigure)
        {
            g.setColor(new Color(255,100,100)); // Red
            
            // Override background color of "from field"
            g.fillRect(
                GetColDrawFromCol(col_f) * SQUARE_SIZE, 
                GetRowDrawFromRow(row_f) * SQUARE_SIZE, 
                SQUARE_SIZE, SQUARE_SIZE
            );               

            g.setColor(new Color(100,150,100)); // Green
            
            // Override background color of possible "move to fields"
            for(m = 0; FillMoveList[m][Move.FIGURE] != Position.EMPTY; m++)    
            {           
               if((FillMoveList[m][Move.COL] == col_f) && (FillMoveList[m][Move.ROW] == row_f))
                {
                    g.fillRect(
                        GetColDrawFromCol(FillMoveList[m][Move.COL_N]) * SQUARE_SIZE, 
                        GetRowDrawFromRow(FillMoveList[m][Move.ROW_N]) * SQUARE_SIZE, 
                        SQUARE_SIZE, SQUARE_SIZE
                    ); 
               }
            }
        }            
        
        Image chessPieceImage  = new ImageIcon("wk.png").getImage(); // Default initialization;
        String imageString;
        Piece piece;
        
        for(row_d = 0; row_d  < Position.ROWS; row_d++)       
        {
            for(col_d = 0; col_d < Position.COLS; col_d++)
            {
                piece = Piece.Pieces[PosDrawBuffer[GetRowFromRowDraw(row_d)][GetColFromColDraw(col_d)]];
                
                if (piece.getType() != Position.EMPTY)
                {
                    imageString = piece.getImageString();
                    chessPieceImage = new ImageIcon(imageString).getImage();

                    g.drawImage
                    (
                        chessPieceImage, col_d * SQUARE_SIZE, row_d * SQUARE_SIZE, 
                        (col_d +1) * SQUARE_SIZE, (row_d + 1) * SQUARE_SIZE,                   
                        0, 0,
                        SQUARE_SIZE, SQUARE_SIZE,
                        this
                    );
                }
            }
        }  
                
        if(WhitePromotionFigure || BlackPromotionFigure)
        {
            //System.out.println("In paint() WhitePromotionFigure = " + WhitePromotionFigure + "BlackPromotionFigure = " + BlackPromotionFigure);
            g.setColor(new Color(100, 150, 100)); // Draw background frame for Promotion figures
            g.fillRect
            (
                X_PROMOTION_FRAME_SQUARE_OFFSET * SQUARE_SIZE + SQUARE_SIZE / 2, 
                Y_PROMOTION_FRAME_SQUARE_OFFSET * SQUARE_SIZE,                     
                PROMOTION_FRAME_SQUARE_LENGTH * SQUARE_SIZE, 
                PROMOTION_FRAME_SQUARE_HEIGHT * SQUARE_SIZE
            );        
                    
            if(LeftMouseButtonPressed) // Draw background for selected promotion figure
            {
                g.setColor(new Color(255, 100, 100));  // Red
                g.fillRect
                (
                    (col_p - 1) * SQUARE_SIZE, 
                    3 * SQUARE_SIZE + SQUARE_SIZE /2, 
                    SQUARE_SIZE, SQUARE_SIZE
                );  
            }
            
            for(i = 0; i < NUMBER_OF_PROMOTION_FIGURES; i++)
            {
                chessPieceImage = (WhitePromotionFigure) ? new ImageIcon( Piece.WhitePromotionFigures[i].getImageString() ).getImage() : new ImageIcon( Piece.BlackPromotionFigures[i].getImageString() ).getImage();
                g.drawImage
                (
                    chessPieceImage,     // Draw promotion image
                    (X_PROMOTION_FRAME_SQUARE_OFFSET + 1 + i)      * SQUARE_SIZE, 
                    Y_PROMOTION_FRAME_SQUARE_OFFSET         * SQUARE_SIZE + SQUARE_SIZE / 2, 
                    (X_PROMOTION_FRAME_SQUARE_OFFSET + 2 + i)      * SQUARE_SIZE, 
                    (Y_PROMOTION_FRAME_SQUARE_OFFSET + 1)   * SQUARE_SIZE + SQUARE_SIZE / 2, 
                    0, 0, SQUARE_SIZE, SQUARE_SIZE, this
                );
            }
        }        
        Chess.str = (Position.GetMoveColor(PosDrawBuffer) == Position.WHITE_MOVE) ? "White move" : "Black move";
        Chess.frame.setTitle(Chess.str);
        
        
        //g.drawString(Chess.str, 20, 15 + 8 * SQUARE_SIZE);        
           
        //srt ="cde";  
        //Chess.str = Chess.str.concat(srt);
        //Chess.str = " 123";
        //Chess.str += "456";
        
        //g.drawString(Chess.str, 300,300);
        
        //g.drawString("Showing MoveHistory using MoveHistoryTable", 300, 300);        
                    
        //g.drawString("Test: Showing MoveHistory using MoveHistoryTable", 20, 15 + 8*SQUARE_SIZE);
        
        //for(t = 0; Move.MoveHistoryTable[t] != null; t++)
        //for(t = 0; t <5 ; t++)
        //{
            //System.out.println(MoveHistoryTable[t]);
        //    srt = String.format("t = %d", t);
          //  g.drawString(Move.MoveHistoryTable[t], 20, 15*(t+2) + 8*SQUARE_SIZE);
            // g.drawString(srt, 20, 15*(t+2) + 8*SQUARE_SIZE);           
       
    }

    public int GetUserMoveFromMouseInput(int[][] Pos, int[][] MovePath, String[] MoveTable, int[][] MoveBest, boolean Back)
    {
        int row;
        int col;
        int i;
        boolean ReturnOnFirstMovePossible = false;
        
        /*
        Scanner scanner   = new Scanner(System.in);
        Scanner user_input = new Scanner(System.in);
        */
        
        Settings.ClearScreen(Pos);  
        if(!Back)
        {
            Move.Display(MoveBest, Move.ALL, Chess.Ply, MoveTable, Move.LINE, Move.SHOW_RATING_LAST_MOVE);          
        }
        System.out.println(); 
        // Display MoveHistory
        Move.Display(MovePath, Move.STOP, Chess.Ply, MoveTable, Move.TABLE, Move.SHOW_NO_RATING);                    
        
        // Display MoveHistory into a Window
        Move.DisplayIntoHistoryWindow(MoveTable);
        
        Position.Copy(Pos, PosDrawBuffer);  // Copies Pos[][] into PosDrawBuffer[][]
        
        Move.EmptyList(FillMoveList);   // Paint uses FillMoveList to draw possible move to fields green
        
        /*
        System.out.println("In GetUserMoveFromMouseInput() before GenerateMoveList... \n");
        System.out.println("Please press enter to continue\n");
        scanner.nextLine();
        */
        
        Position.GenerateMoveList(Pos, FillMoveList, MovePath, ReturnOnFirstMovePossible, false);              // When called with PosDrawBuffer[][] it will draw changed initial position wrong like one move was made
        
        /*
        System.out.println("In GetUserMoveFromMouseInput() after GenerateMoveList... \n");
        System.out.println("Please press enter to continue\n");
        scanner.nextLine();       
        */
        
        do
        {
            MouseInputActive = true;    // Get mouse input
            do                          // Wait until mouse input finished
            {
               System.out.print ("");   // Somehow needs to do something to get out of loop ..... accidentially figured this out         
            }while(MouseInputActive);
            //System.out.println("Mouse input finished");        
        }while(!Move.UserSuccessful(Pos, row_f, col_f, Figure_n, row_n, col_n, MovePath));
        repaint();                      // Draws position from PosDrawBuffer[][]
        Settings.ClearScreen(Pos); 
        // Display MoveHistory into a Window
        //Move.DisplayIntoWindow(MoveTable);        
        
        
        return '0';
    }
    
    @Override
    public void mouseMoved(MouseEvent e)   // Runs when mouse moves
    {
    }
     
    @Override  
    public void mousePressed(MouseEvent e) // Runs once at mouse button press event
    {
        int mouseX = e.getX();
        int mouseY = e.getY();
        //System.out.println("mouseX = " + mouseX + " mouseY = " + mouseY);
        
        if (MouseInputActive && !WhitePromotionFigure && !BlackPromotionFigure) 
        {
            if((mouseX >= 0) && (mouseX < (Position.COLS * SQUARE_SIZE)) && (mouseY  >= 0) && (mouseY < (Position.ROWS * SQUARE_SIZE)))      
            {                                   
                // Check valid input to 0 <= x < COLS, 0 <= y < ROWS
                row_f = GetRowFromMouse(mouseY);
                col_f = GetColFromMouse(mouseX);
                
                Figure_n = PosDrawBuffer[row_f][col_f];  // Default unless pawn promotion    
                LeftMouseButtonPressed = true;           // Enables repaint() to highlight from-field and possible to-fields
                repaint();
            }            
            else
            {
                row_f = 0;
                col_f = 0; // Will result in an invalid move and trigger another mousePressed() event
            }
        }
     
        if(MouseInputActive && (WhitePromotionFigure || BlackPromotionFigure))      
        {
            if(
                (mouseX >= ((COL_FIRST_PROMOTION_FIGURE - 1) * SQUARE_SIZE))                                    &&
                (mouseX <  ((COL_FIRST_PROMOTION_FIGURE - 1 + NUMBER_OF_PROMOTION_FIGURES)   * SQUARE_SIZE))    &&           
                (mouseY >= (Y_PROMOTION_FRAME_SQUARE_OFFSET * SQUARE_SIZE + SQUARE_SIZE / 2)                    &&
                (mouseY <  ((Y_PROMOTION_FRAME_SQUARE_OFFSET + 1) * SQUARE_SIZE + SQUARE_SIZE / 2))))
            {                   
                if(Chess.WhiteBoard)
                {
                    col_p = GetColFromMouse(mouseX);                    
                }
                else
                {
                    col_p = 9 - GetColFromMouse(mouseX);                
                }
                System.out.println("col_p = " + col_p);   
                
                if(WhitePromotionFigure)   
                {
                    switch(col_p)
                    {
                        case 3:
                            Figure_n = Position.WHITE_QUEEN;
                            break;
                        
                        case 4: 
                            Figure_n = Position.WHITE_ROOK;
                           break;    
                        
                        case 5:
                            Figure_n = Position.WHITE_KNIGHT;
                            break;
                        
                        case 6: 
                            Figure_n = Position.WHITE_BISHOP;
                            break;     
                    }
                }
            
                if(BlackPromotionFigure)  
                {
                    switch(col_p)
                    {
                        case 3:
                            Figure_n = Position.BLACK_QUEEN;
                            break;
                        
                        case 4: 
                            Figure_n = Position.BLACK_ROOK;
                            break;    
                        
                        case 5:
                            Figure_n = Position.BLACK_KNIGHT;
                            break;
                        
                        case 6: 
                            Figure_n = Position.BLACK_BISHOP;
                            break;     
                    }
                }        
                                
                LeftMouseButtonPressed = true; // Enables repaint() to highlight from-field and possible to-fields                
                repaint();// Draw position from PosDrawBuffer[][]
            }
            else
            {
                Figure_n  = Position.EMPTY; // Will result in an invalid move and trigger another mousePressed() event
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e)  // Runs at mouse button relase event
    {     
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (MouseInputActive && !WhitePromotionFigure && !BlackPromotionFigure) 
        {
            if((mouseX >= 0) && (mouseX < (Position.COLS * SQUARE_SIZE)) && (mouseY  >= 0) && (mouseY < (Position.ROWS * SQUARE_SIZE)))        
            {                                                                  
                // Check valid input to 0 <= x < COLS, 0 <= y < ROWS
                row_n = GetRowFromMouse(mouseY);
                col_n = GetColFromMouse(mouseX);           
                                                  
                if(((Figure_n == Position.WHITE_PAWN) && (row_n == Position.WHITE_PAWN_PROMOTION_ROW)) ||
					((Figure_n == Position.BLACK_PAWN) && (row_n == Position.BLACK_PAWN_PROMOTION_ROW)))
                {                                                               
                    // Repaint() to show pawn moved to promotion row
                    PosDrawBuffer[row_f][col_f]= Position.EMPTY;  // Move pawn to promotion row
                    PosDrawBuffer[row_n][col_n]= Figure_n;       
                    
                    // Disables repaint() to highlight from-field and possible to-fields
                    LeftMouseButtonPressed = false; 
                    
                    
                    if((Figure_n == Position.WHITE_PAWN) && (row_n == Position.WHITE_PAWN_PROMOTION_ROW))
                    {
                        // Enable repaint() to draw white promotion figure selection
                        WhitePromotionFigure = true;                            
                    }
                    if((Figure_n == Position.BLACK_PAWN) && (row_n == Position.BLACK_PAWN_PROMOTION_ROW))
                    {
                        // Enables repaint() to draw black promotion figure selection
                        BlackPromotionFigure = true;                            
                    }            
                      
                    // Draw pawn on promotion row and promotion figure selection  
                    repaint();                                               
                    
                    // Keeps MouseInputActive = true to allow user to select promtotion figure with mouse on mousePressed() event
                    return;                                                  
                }
                // Finish mouse move input and let GetUserMoveFromMouseInput() check if move was valid
                MouseInputActive = false;           
                
                // Diable repaint() to highlight from-filed and possible to-fields
                LeftMouseButtonPressed = false;                             
                repaint();
            }
            else
            {
                // Create an invalid move and trigger another mousePressed() event
                row_n = 0;                                                   
                col_n = 0;
            }
        }
        
        if(MouseInputActive && LeftMouseButtonPressed && (WhitePromotionFigure || BlackPromotionFigure))              
        {          
            // Replace pawn with the selected promotion figure
            PosDrawBuffer[row_n][col_n] = Figure_n;     
            
            // Instruct repaint() not to draw promotion selection images anymore   
            WhitePromotionFigure        = false;                                    
            BlackPromotionFigure        = false;  
                  
            // Finish mouse move input and let GetUserMoveFromMouseInput() check if move was valid
            MouseInputActive            = false;            
            
            // Diables repaint() to highlight from-filed and possible to-fields
            LeftMouseButtonPressed      = false;                           
            repaint();
        }
    }
        
    @Override       // Mouse button pressed and released at same location
    public void mouseClicked(MouseEvent e)  
    {
    }
             
    @Override       // Runs when mouse button clicked and mouse moves
    public void mouseDragged(MouseEvent e)
    {   
    }
                 
    @Override       // Runs only once when mouse cursor enters the window                                                      
    public void mouseEntered(MouseEvent e)
    {        
    }
                     
    @Override      // Runs only once when mouse cursor exits the window
    public void mouseExited(MouseEvent e)                                    
    {       
    }
   
    public static int GetRowFromMouse(int pixel_row)
    {
		return Chess.WhiteBoard ? Position.ROWS - (pixel_row / SQUARE_SIZE) : (pixel_row / SQUARE_SIZE) + 1;
    }    

    public static int GetColFromMouse(int pixel_col)
    {
		return Chess.WhiteBoard ? (pixel_col / SQUARE_SIZE) + 1 : Position.ROWS - (pixel_col / SQUARE_SIZE);
    }     

    public static int GetRowFromRowDraw(int row_d)
    {   
        return Chess.WhiteBoard ? Position.ROWS - row_d : row_d + 1;
    }    
    
    public static int GetColFromColDraw(int col_d)
    {   
		return Chess.WhiteBoard ? col_d + 1 : Position.COLS - col_d;
    }    
    
    public static int GetRowDrawFromRow(int row)
    {
        return Chess.WhiteBoard ? Position.ROWS - row : row - 1;
    }    
    
    public static int GetColDrawFromCol(int col)
    {
        return Chess.WhiteBoard ? col - 1 : Position.COLS - col;
    }    
}
