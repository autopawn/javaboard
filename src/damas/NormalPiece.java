package damas;

import java.util.LinkedList;
import java.util.List;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

/* Rules:
    Movement    : Diagonally in the forward direction (towards the opponent) to the next dark square.
    Capturing   : If there is one of the opponent's pieces next to a piece and an empty space on the 
                other side, you jump your opponent and remove their piece. You can do multiple jumps 
                if they are lined up in the forward direction.
    King Piece  : The last row is called the king row. If you get a piece across the board to the 
                opponent's king row, that piece becomes a king. Another piece is placed onto that 
                piece so it is now two pieces high. King pieces can move in both directions,
                forward and backward.
*/
public class NormalPiece extends Piece {

    public NormalPiece(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    // Pieces can move diagonally
    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        // MOVEMENT
        int dy = 2*player-1; // Pieces moves only 1 step forward

        int[] dxs = {-1,1};  // 2 alternatives for delta x
        for(int dx : dxs){
            int xx = x + dx;
            int yy = y + dy;
            // Check inside bounds and that there is not piece
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                // Clone the current state to use it in the movement
                Game cpy = grid.cloneGame(); 
                //To become King
                if((player == 0 && y ==1) || (player == 1 && y ==6)){
                    //Game cpy = grid.cloneGame(); 
                    cpy.movePiece(x,y,10,10); 
                    cpy.pieces.add(new KingPiece(player, xx, yy));
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));     
                }
                //Normal Movement
                else{
                    cpy.movePiece(x,y,xx,yy);                     // new state moves this piece
                    cpy.current_player = 1 - cpy.current_player;  // new state changes player
                    // Append new movement to the movement list
                    moves.add( new Movement(Movement.moveCommand(x,y,xx,yy),cpy) );
                }
            }


            //CAPTURING       
            int dyy = 4*player-2; // The pieces captures other pieces jumping over them
            int[] dxxs = {2}; // Delta x
            
            //CAPTURE <---LEFT
            for (int dxx : dxxs){
                int xxx = x - dxx; //Jumping other player piece
                int yyy = y + dyy; //Jumping other player piece
                // Conditions to capturing piece
                if ( xxx>=0 && grid.pieceAt(x-1, yy) != null && grid.pieceAt(xxx, yyy) == null  && grid.isInside(xxx,yyy) && grid.pieceAt(x-1, yy).player != grid.current_player){
                    Game cpy = grid.cloneGame();

                    //Capturing a piece and become King
                   if((player == 0 && y ==2) || (player == 1 && y==5) && xxx>=0 && grid.pieceAt(x-1, yy) != null && grid.pieceAt(xxx, yyy) == null  && grid.isInside(xxx,yyy) && grid.pieceAt(x-1, yy).player != grid.current_player){
                        cpy.pieceAt(x-1,yy).player=cpy.current_player+2;    
                        cpy.movePiece(x-1,yy,10,10); 
                        cpy.movePiece(x, y, 10, 10);
                        cpy.pieces.add(new KingPiece(player, xxx, yyy));
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,xxx,yyy),cpy)); 
                    }

                    /*
                    //Multicapturing Left
                    if ( xxx>=0 && grid.pieceAt(xx-1, yyy) != null && grid.pieceAt(xxx, yyy) == null  && grid.isInside(xxx,yyy) && grid.pieceAt(xx-1, yyy).player != grid.current_player){
                        cpy.pieceAt(x-1,yy).player=cpy.current_player+2;       // Change player piece
                        cpy.movePiece(x-1,yy,10,10);                           // Captured piece is removed from the board
                        cpy.movePiece(x,y,xxx,yyy);                            // Moving the piece 
                        cpy.current_player = 1 - cpy.current_player;           // Piece jump capturing other piece
                        // Append new movement to the movement list
                        moves.add(new Movement(Movement.moveCommand(x,y,xxx,yyy),cpy));
                        break;     
                    }
                    //Multicapturing Right
                    if ( xxx<7 && grid.pieceAt(xx+1, yyy) != null && grid.pieceAt(xxx, yyy) == null && grid.isInside(xxx,yyy) && grid.pieceAt(xx+1, yyy).player != grid.current_player){
                        cpy.pieceAt(x-1,yy).player=cpy.current_player+2;    // Change player piece
                        cpy.movePiece(x-1,yy,10,10);                        // Captured piece is removed from the board
                        cpy.movePiece(x,y,xxx,yyy);                         // Moving the piece   
                        cpy.current_player = 1 - cpy.current_player;        // Piece jump capturing other piece
                        // Append new movement to the movement list
                        moves.add(new Movement(Movement.moveCommand(x,y,xxx,yyy),cpy)); 
                        break;      
                    }
                    */
                    
                    
                   // Just Capturing a piece
                    else{
                        cpy.pieceAt(x-1,yy).player=cpy.current_player+2;    // Change player piece
                        cpy.movePiece(x-1,yy,10,10);                        // Captured piece is removed from the board
                        cpy.movePiece(x,y,xxx,yyy);                         // Moving the piece  
                        cpy.current_player = 1 - cpy.current_player;        // Change player turn
                        // Append new movement to the movement list
                        moves.add(new Movement(Movement.moveCommand(x,y,xxx,yyy),cpy));  
                    }
                  
                
                }  
            }

            //CAPTURE RIGHT--->
            for (int dxx : dxxs){
                int xxx = x + dxx; //Jumping other player piece
                int yyy = y + dyy; //Jumping other player piece
                // If the move has a piece, and is not from the current player
                if ( xxx<=7 && grid.pieceAt(x+1, yy) != null && grid.pieceAt(xxx, yyy) == null && grid.isInside(xxx,yyy) && grid.pieceAt(x+1, yy).player != grid.current_player){
                    Game cpy = grid.cloneGame();
                 //Capturing a piece and become King
                    if((player == 0 && y ==2) || (player == 1 && y==5) && xxx<=7 && grid.pieceAt(x+1, yy) != null && grid.pieceAt(xxx, yyy) == null && grid.isInside(xxx,yyy) && grid.pieceAt(x+1, yy).player != grid.current_player){
                        //Game cpy = grid.cloneGame(); 
                        cpy.pieceAt(x+1,yy).player=cpy.current_player+2;    
                        cpy.movePiece(x+1,yy,11,11); 
                        cpy.movePiece(x, y, 11, 11);
                        cpy.pieces.add(new KingPiece(player, xxx, yyy));
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,xxx,yyy),cpy));
                    }
 
                    /*
                    //Multicapturing right
                    if ( xxx < 7 && (x+1)!= 7 && grid.pieceAt(xx+1, yyy) != null && grid.pieceAt(xxx, yyy) == null && grid.isInside(xxx,yyy) && grid.pieceAt(xx+1, yyy).player != grid.current_player){
                        cpy.pieceAt(x+1,yy).player=cpy.current_player+2;        // Change player piece
                        cpy.movePiece(x+1,yy,10,10);                            // Captured piece is removed from the board
                        cpy.movePiece(x,y,xxx,yyy);                             // Moving the piece  
                        cpy.current_player = 1 - cpy.current_player;            // Piece jump capturing other piece
                        // Append new movement to the movement list
                        moves.add(new Movement(Movement.moveCommand(x,y,xxx,yyy),cpy));
                        break;
                    }
                    
                    //Multicapturing Left
                    if( xxx>0 && grid.pieceAt(xx-1, yyy) != null && grid.pieceAt(xxx, yyy) == null  && grid.isInside(xxx,yyy) && grid.pieceAt(xx-1, yyy).player != grid.current_player){
                        cpy.pieceAt(x-1,yy).player=cpy.current_player+2;    // Change player piece
                        cpy.movePiece(x-1,yy,10,10);                        // Captured piece is removed from the board
                        cpy.movePiece(x,y,xxx,yyy);                         // Moving the piece        
                        cpy.current_player = 1 - cpy.current_player;        // Piece jump capturing other piece
                        // Append new movement to the movement list
                        moves.add(new Movement(Movement.moveCommand(x,y,xxx,yyy),cpy)); 
                        break; 
                    }
                    */

                    else{
                        //capturing Right
                        cpy.pieceAt(x+1,yy).player=cpy.current_player+2;    // Change player piece
                        cpy.movePiece(x+1,yy,11,11);                        // Captured piece is removed from the board
                        cpy.movePiece(x,y,xxx,yyy);                         // Moving the piece        
                        cpy.current_player = 1 - cpy.current_player;        // Change player turn
                        // Append new movement to the movement list
                        moves.add(new Movement(Movement.moveCommand(x,y,xxx,yyy),cpy));  
                    }
                }
            }
        }         
        return moves;
    }

  
    @Override
    public String asciiRepresentation(){
        if(player==0 || player==3){
            return "⛀ ";
        }
        if(player==1 || player ==2){
            return "⛂ ";
        }
        return "☓";
    }

    // Clone the NormalPiece so that the clone is a NormalPiece instance
    @Override
    public Piece clonePiece(){
        NormalPiece other = new NormalPiece(player,x,y);
        return other;
    }
}
