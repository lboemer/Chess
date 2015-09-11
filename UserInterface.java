import java.util.*;                                                             // For scanner
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class UserInterface extends JPanel implements MouseListener, MouseMotionListener
{
    public static final int SQUARE_SIZE                         = 64;  
    public static final int NUMBER_OF_PROMOTION_FIGURES         = 4;
    public static final int X_PROMOTION_FRAME_SQUARE_OFFSET     = 1;
    public static final int Y_PROMOTION_FRAME_SQUARE_OFFSET     = 3;    
    public static final int PROMOTION_FRAME_SQUARE_LENGHT       = NUMBER_OF_PROMOTION_FIGURES + 1;
    public static final int PROMOTION_FRAME_SQUARE_HEIGHT       = 2;  
    
    public static final int COL_FIRST_PROMOTION_FIGURE          = 3;      

    static boolean LeftMouseButtonPressed                       = false;
    static boolean WhitePromotionFigure                         = false;
    static boolean BlackPromotionFigure                         = false;     
    static boolean MouseInputActive                             = false;
        
    public static int[][] PosDrawBuffer                         = new int[Position.COLS+1][Position.ROWS+1];

    int[][] FillMoveList                                        = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];
    int[][] FillMoveHistory                                     = new int[Move.MAX_NUMBER_MOVE_LIST][Move.ENTRIES_MOVE_LIST];   
    
    int Figure_n;    
    
    static String srt;    
    static int col_f, row_f, col_n, row_n;    
    static int col_p;                                                           // Column promotion figure selected
        
    public static String[] MoveTable = new String[Move.MAX_NUMBER_MOVE_LIST];
    
    public void repaintWindow(int[][] Pos) {
        int row;
        int col;
        
        for(row = 0; row <= Position.ROWS; row++)                               // Go through entire array, ROWS + 1 rows
        {  
            for(col = 0; col <= Position.COLS; col++)                           // Go through entire array, COLS + 1 cols
            {
                PosDrawBuffer[row][col] = Pos[row][col];                        // Copy position into PosDrawBuffer to ensure that the postion at this step in the program is drawn
            }
        }

        repaint();                                                              // Draw position from PosDrawBuffer[][]
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
        boolean empty;
        
        super.paintComponent(g);                                                // Redraws everytime, otherwise it would add to drawing
        this.setBackground(Color.yellow);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        //System.out.println("In paint() beginning: WhitePromotionFigure = " + WhitePromotionFigure + "BlackPromotionFigure = " + BlackPromotionFigure);
        
        for (i = 0; i  < Position.ROWS * Position.COLS; i += 2)                 // Draws two background fields in alternate colors
        {
            g.setColor(new Color(255,200,100));
            g.fillRect((i % Position.ROWS + (i / Position.ROWS) % 2) * SQUARE_SIZE, (i / Position.ROWS) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            g.setColor(new Color(150,50,30));
            g.fillRect(((i+1) % Position.ROWS - ((i+1) / Position.ROWS) % 2) * SQUARE_SIZE, ((i+1) / Position.ROWS) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);                
        }
        
        if(LeftMouseButtonPressed && !WhitePromotionFigure && !BlackPromotionFigure)
        {
            g.setColor(new Color(255,100,100));                                 // Red
            g.fillRect(GetColDrawFromCol(col_f) * SQUARE_SIZE, GetRowDrawFromRow(row_f) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);                // Override background color of "from field"

            g.setColor(new Color(100,150,100));                                 // Green
            for(m = 0; FillMoveList[m][Move.FIGURE] != Position.EMPTY; m++)     // Override background color of possible "move to fields"
            {           
               if((FillMoveList[m][Move.COL] == col_f) && (FillMoveList[m][Move.ROW] == row_f))
                {
                    g.fillRect(GetColDrawFromCol(FillMoveList[m][Move.COL_N]) * SQUARE_SIZE, GetRowDrawFromRow(FillMoveList[m][Move.ROW_N]) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); 
               }
            }
        }            
        
        Image chessPieceImage;
        chessPieceImage = new ImageIcon("wk.png").getImage();                   // Default initialization
        
        for(row_d = 0; row_d  < Position.ROWS; row_d++)       
        {
            for(col_d = 0; col_d < Position.COLS; col_d++)
            {
                empty = false;    

                switch(PosDrawBuffer[GetRowFromRowDraw(row_d)][GetColFromColDraw(col_d)])                           
                {                                                               // Select image
                    case Position.WHITE_KING:
                        chessPieceImage = new ImageIcon("wk.png").getImage();
                        break;
                        
                    case Position.WHITE_QUEEN:
                        chessPieceImage = new ImageIcon("wq.png").getImage();                    
                        break;
                        
                    case Position.WHITE_ROOK:
                        chessPieceImage = new ImageIcon("wr.png").getImage();
                        break;
                        
                    case Position.WHITE_KNIGHT:
                        chessPieceImage = new ImageIcon("wn.png").getImage();
                        break;
                        
                    case Position.WHITE_BISHOP:
                        chessPieceImage = new ImageIcon("wb.png").getImage();
                        break;
                        
                    case Position.WHITE_PAWN:
                        chessPieceImage = new ImageIcon("wp.png").getImage();
                        break;
                        
                    case Position.BLACK_KING:
                        chessPieceImage = new ImageIcon("bk.png").getImage();
                        break;
                        
                    case Position.BLACK_QUEEN:
                        chessPieceImage = new ImageIcon("bq.png").getImage();
                        break;
                        
                    case Position.BLACK_ROOK:
                        chessPieceImage = new ImageIcon("br.png").getImage();
                        break;
                        
                    case Position.BLACK_KNIGHT:
                        chessPieceImage = new ImageIcon("bn.png").getImage();
                        break;
                        
                    case Position.BLACK_BISHOP:
                        chessPieceImage = new ImageIcon("bb.png").getImage();
                        break;
                        
                    case Position.BLACK_PAWN:
                        chessPieceImage = new ImageIcon("bp.png").getImage();
                        break;
                        
                    default:
                        empty = true;
                        break;
                } 
            
                if (!empty)                                                     // Draw image
                {
                    g.drawImage(chessPieceImage, col_d * SQUARE_SIZE, row_d * SQUARE_SIZE, 
                        (col_d +1) * SQUARE_SIZE, (row_d + 1) * SQUARE_SIZE,                   
                        0, 0,
                        SQUARE_SIZE, SQUARE_SIZE,
                        this);
                }
            }
        }  
                
        if(WhitePromotionFigure || BlackPromotionFigure)
        {
            //System.out.println("In paint() WhitePromotionFigure = " + WhitePromotionFigure + "BlackPromotionFigure = " + BlackPromotionFigure);
            g.setColor(new Color(100,150,100));                                 // Draw background frame for Promotion figures
            g.fillRect(
                X_PROMOTION_FRAME_SQUARE_OFFSET * SQUARE_SIZE + SQUARE_SIZE / 2, 
                Y_PROMOTION_FRAME_SQUARE_OFFSET * SQUARE_SIZE,                     
                PROMOTION_FRAME_SQUARE_LENGHT * SQUARE_SIZE, 
                PROMOTION_FRAME_SQUARE_HEIGHT * SQUARE_SIZE
            );        
                    
            if(LeftMouseButtonPressed)                                          // Draw background for selected promotion figure
            {
                g.setColor(new Color(255,100,100));                             // Red
                g.fillRect((col_p - 1) * SQUARE_SIZE, 3 * SQUARE_SIZE + SQUARE_SIZE /2, SQUARE_SIZE, SQUARE_SIZE);  
            }
            
            for(i = 0; i < NUMBER_OF_PROMOTION_FIGURES; i++)
            {
                if(WhitePromotionFigure)                                        // Select whIte promotion image
                {
                    switch(i)
                    {
                        case 0:                      
                            chessPieceImage = new ImageIcon("wq.png").getImage();  
                            break;
                            
                        case 1:
                            chessPieceImage = new ImageIcon("wr.png").getImage();  
                            break;  
                            
                        case 2:                      
                            chessPieceImage = new ImageIcon("wn.png").getImage();  
                            break;
                            
                        case 3:
                            chessPieceImage = new ImageIcon("wb.png").getImage();  
                            break;      
                    }
                }
            
                if(BlackPromotionFigure)                                        // Select black promotion image
                {
                    switch(i)
                    {
                        case 0:                      
                            chessPieceImage = new ImageIcon("bq.png").getImage();  
                            break;
                            
                        case 1:
                            chessPieceImage = new ImageIcon("br.png").getImage();  
                            break;  
                            
                        case 2:                      
                            chessPieceImage = new ImageIcon("bn.png").getImage();  
                            break;
                            
                        case 3:
                            chessPieceImage = new ImageIcon("bb.png").getImage();  
                            break;      
                    }                    
                }            
                g.drawImage(chessPieceImage,                                    // Draw promotion image
                    (X_PROMOTION_FRAME_SQUARE_OFFSET + 1 + i)      * SQUARE_SIZE, Y_PROMOTION_FRAME_SQUARE_OFFSET         * SQUARE_SIZE + SQUARE_SIZE / 2, 
                    (X_PROMOTION_FRAME_SQUARE_OFFSET + 2 + i)      * SQUARE_SIZE, (Y_PROMOTION_FRAME_SQUARE_OFFSET + 1)   * SQUARE_SIZE + SQUARE_SIZE / 2, 
                    0, 0, SQUARE_SIZE, SQUARE_SIZE, this);   
            }
        }        
        
        switch(Position.GetMoveColor(PosDrawBuffer) )
        {
            case Position.WHITE_MOVE:
                Chess.str ="White move";
                break;
                
            case Position.BLACK_MOVE:
                Chess.str ="Black move";
                break;
        }
        g.drawString(Chess.str, 20, 15 + 8 * SQUARE_SIZE);        
           
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

    public int GetUserMoveFromMouseInput(int[][] Pos, int[][] MovePath, int[][] MoveBest, boolean Back)
    {
        int row;
        int col;
        int i;
        boolean CheckForCanTakeKingOnly = false;
        //String[] MoveTable = new String[Move.MAX_NUMBER_MOVE_LIST];
        
        Scanner scanner             = new Scanner(System.in);
        
        
        Settings.ClearScreen(Pos);  
        if(!Back)
        {
            Move.DisplayMoveList(MoveBest, Move.ALL, 0, Move.LINE, Move.SHOW_RATING_LAST_MOVE);              // Displays MoveBest
        }
        
        /*
        System.out.println("Ply in GetUserMoveFromMouseInput = " + Chess.Ply);
        System.out.println("In GetUserMoveFromMouseInput going to careyt move table");    
        scanner.nextLine();  
                            
        Move.CreateMoveTable(MovePath, Move.STOP, Chess.Ply, MoveTable, Move.TABLE, Move.SHOW_NO_RATING);  
        System.out.println("In GetUserMoveFromMouseInput .. Created move table");          
        scanner.nextLine();        
        Move.DisplayMoveTableConsole(MoveTable);
        System.out.println("In GetUserMoveFromMouseInput .. Displayed move table");                          
        scanner.nextLine();          
        
        */
        

        
        System.out.println(); 
        Move.DisplayMoveList(MovePath, Move.STOP, Chess.Ply, Move.TABLE, Move.SHOW_NO_RATING);                     // Displays MoveHistory
        //System.out.println("in  GetUserMoveFromMouseInput()");
        
        for(row = 0; row <= Position.ROWS; row++)                               // Copies Pos[][] into PosDrawBuffer[][]
        {  
            for(col = 0; col <= Position.COLS; col++)        
            {
                PosDrawBuffer[row][col] = Pos[row][col];
                //System.out.println(Pos[row][col]);
            }
        }        
        
        //System.out.println("Generate Movelist in GetUserMoveFromMouseInput()");        
        Move.EmptyMoveList(FillMoveList);
        //Move.GenerateMoveList(Pos, FillMoveList, FillMoveHistory, CheckForCanTakeKingOnly);     // When called with PosDrawBuffer[][] it will draw changed initial position wrong like one move was made
        Move.GenerateMoveList(Pos, FillMoveList, FillMoveHistory);     // When called with PosDrawBuffer[][] it will draw changed initial position wrong like one move was made
        //Move.DisplayMoveList(FillMoveList, Move.ALL, 0, Move.LIST, Move.SHOW_NO_RATING); 

        do
        {
            MouseInputActive = true;                                            // Get mouse input
            do                                                                  // Wait until mouse input finished
            {
               System.out.print ("");                                           // Somehow needs to do something to get out of loop ..... accidentially figured this out         
            }while(MouseInputActive);
            //System.out.println("Mouse input finished");        
        }while(!Move.UserMoveSuccessful(Pos, col_f, row_f, Figure_n, col_n, row_n, MovePath));
        repaint();                                                              // Draws position from PosDrawBuffer[][]
        Settings.ClearScreen(Pos); 
        return '0';
    }
    
    @Override
    public void mouseMoved(MouseEvent e)                                        // Runs when mouse moves
    {
                          
    }
     
    @Override  
    public void mousePressed(MouseEvent e)                                      // Runs once at mouse button press event
    {
        int mouseX = e.getX();
        int mouseY = e.getY();
        //System.out.println("mouseX = " + mouseX + " mouseY = " + mouseY);
        
        if (MouseInputActive && !WhitePromotionFigure && !BlackPromotionFigure) 
        {
            if((mouseX >= 0) && (mouseX < (Position.COLS * SQUARE_SIZE)) && (mouseY  >= 0) && (mouseY < (Position.ROWS * SQUARE_SIZE)))      
            {                                                                   // Check valid input to 0 <= x < COLS, 0 <= y < ROWS
                row_f = GetRowFromMouse(mouseY);
                col_f = GetColFromMouse(mouseX);
                
                Figure_n = PosDrawBuffer[row_f][col_f];                         // Default unless pawn promotion    
                //System.out.println("col_f = " + col_f + " row_f = " + row_f);               
                LeftMouseButtonPressed = true;                                  // Enables repaint() to highlight from-field and possible to-fields
                repaint();
            }            
            else
            {
                col_f = 0;                                                      // Will result in an invalid move and trigger another mousePressed() event
                row_f = 0;
                //System.out.println("col_f = " + col_f + "row_f = " + row_f);
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
                //System.out.println("Promotion figure = " + Figure_n);
                
                LeftMouseButtonPressed = true;                                  // Enables repaint() to highlight from-field and possible to-fields                
                repaint();                                                      // Draw position from PosDrawBuffer[][]
            }
            else
            {
                Figure_n  = Position.EMPTY;                                     // Will result in an invalid move and trigger anothrt mousePressed() event
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e)                                     // Runs at mouse button relase event
    {     
        int mouseX = e.getX();
        int mouseY = e.getY();
        //System.out.println("In mouseReleased(): mouseX = " + mouseX + " mouseY = " + mouseY);

        if (MouseInputActive && !WhitePromotionFigure && !BlackPromotionFigure) 
        {
            if((mouseX >= 0) && (mouseX < (Position.COLS * SQUARE_SIZE)) && (mouseY  >= 0) && (mouseY < (Position.ROWS * SQUARE_SIZE)))        
            {                                                                   // Check valid input to 0 <= x < COLS, 0 <= y < ROWS
                row_n = GetRowFromMouse(mouseY);
                col_n = GetColFromMouse(mouseX);           
                                  
                //System.out.println("col_n = " + col_n + " row_n = " + row_n);        
                
                if(((Figure_n == Position.WHITE_PAWN) && (row_n == Position.WHITE_PAWN_PROMOTION_ROW)) || ((Figure_n == Position.BLACK_PAWN) && (row_n == Position.BLACK_PAWN_PROMOTION_ROW)))
                {                                                               // repaint() to show pawn moved to promotion row
                    PosDrawBuffer[row_f][col_f]= Position.EMPTY;                // Move pawn to promotion row
                    PosDrawBuffer[row_n][col_n]= Figure_n;                      
                    LeftMouseButtonPressed = false;                             // diables repaint() to highlight from-field and possible to-fields
                    
                    
                    if((Figure_n == Position.WHITE_PAWN) && (row_n == Position.WHITE_PAWN_PROMOTION_ROW))
                    {
                        WhitePromotionFigure = true;                            // Enables repaint() to draw white promotion figure selection
                        //System.out.println("In MouseReleased() WhitePromotionFigure = " + WhitePromotionFigure);
                    }
                    if((Figure_n == Position.BLACK_PAWN) && (row_n == Position.BLACK_PAWN_PROMOTION_ROW))
                    {
                        BlackPromotionFigure = true;                            // Enables repaint() to draw black promotion figure selection
                        //System.out.println("In MouseReleased() BlackPromotionFigure = " + BlackPromotionFigure);
                    }                
                    repaint();                                                  // Draws pawn on promotion row and promotion figure selection  
                    return;                                                     // Keeps MouseInputActive = true to allow user to select promtotion figure with mouse on mousePressed() event
                }
            
                MouseInputActive = false;                                       // Finishes mouse move input and lets GetUserMoveFromMouseInput() check if move was valid
                LeftMouseButtonPressed = false;                                 // Diables repaint() to highlight from-filed and possible to-fields
                repaint();
            }
            else
            {
                col_n = 0;                                                      // Will result in an invalid move and trigger anothrt mousePressed() event
                row_n = 0;                                                      // Will result in an invalid move and trigger anothrt mousePressed() event
                //System.out.println("col_n = " + col_n + " row_n = " + row_n);
            }
        }
        
        if(MouseInputActive && LeftMouseButtonPressed && (WhitePromotionFigure || BlackPromotionFigure))              
        {          
            PosDrawBuffer[row_n][col_n] = Figure_n;                             // Replaces pawn with the selected promotion figure
            WhitePromotionFigure        = false;                                // Promotion figure selection finished, instructs repaint() not to draw promotion selection images anymore          
            BlackPromotionFigure        = false;                                // Promotion figure selection finished, instructs repaint() not to draw promotion selection images anymore                    
            MouseInputActive            = false;                                // Finishes mouse move input and lets GetUserMoveFromMouseInput() check if move was valid
            LeftMouseButtonPressed      = false;                                // diables repaint() to highlight from-filed and possible to-fields
            repaint();
        }
    }
        
    @Override
    public void mouseClicked(MouseEvent e)                                      // Mouse button pressed and released at same location
    {
       // x = e.getX();
       // y = e.getY();
       //repaint();         

    }
             
    @Override                                                                   // Runs when mouse button clicked and mouse moves
    public void mouseDragged(MouseEvent e)
    {   
    }
                 
    @Override                                                                   // Runs only once when mouse cursor enters the window
    public void mouseEntered(MouseEvent e)
    {        
    }
                     
    @Override   
    public void mouseExited(MouseEvent e)                                       // Runs only once when mouse cursor exits the window
    {       
    }
   
    public static int GetRowFromMouse(int pixel_row)
    {
        if(Chess.WhiteBoard)
        {
           return(Position.ROWS - (pixel_row / SQUARE_SIZE));              
        }
        else
        {
            return((pixel_row / SQUARE_SIZE) + 1);
        }
    }    

    public static int GetColFromMouse(int pixel_col)
    {
        if(Chess.WhiteBoard)
        {
           return((pixel_col / SQUARE_SIZE) + 1);
        }
        else
        {
           return(Position.ROWS - (pixel_col / SQUARE_SIZE));     
        }
    }     

    public static int GetRowFromRowDraw(int row_d)
    {   
        if(Chess.WhiteBoard)
        {
            return(Position.ROWS - row_d);  
        }
        else
        {
            return(row_d + 1);
        }
    }    
    
    public static int GetColFromColDraw(int col_d)
    {   
        if(Chess.WhiteBoard)
        {
            return(col_d + 1);               
        }
        else
        {
            return(Position.COLS - col_d);
        }
    }    
    
    public static int GetRowDrawFromRow(int row)
    {
        if(Chess.WhiteBoard)
        {
            return(Position.ROWS - row);              
        }
        else
        {
            return(row - 1);
        }
    }    
    
    public static int GetColDrawFromCol(int col)
    {
        if(Chess.WhiteBoard)
        {
            return(col - 1);               
        }
        else
        {
            return(Position.COLS - col);
        }
    }    
}
