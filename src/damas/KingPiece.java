package damas;

import java.util.LinkedList;
import java.util.List;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

/* Rules:
    King Piece  : The last row is called the king row. If you get a piece across the board to the 
                opponent's king row, that piece becomes a king. Another piece is placed onto that 
                piece so it is now two pieces high. King pieces can move in both directions,
                forward and backward.
*/
public class KingPiece extends Piece {

    public KingPiece(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    // Pieces can move vertical and horizontal 
    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] dys = {1,2,3,4,5,6,7};
        int dyy = 2*player-1;

        // UP-RIGHT
        for(int dy : dys){
            int yy = y - dy;
            int xx = x + dy;
            // MOVEMENT
            if( xx<=7 && yy>=0 && grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }
            //CAPTURING
            //Capture condition
            if( xx<7 && yy>0 && grid.pieceAt(xx,yy) != null && grid.pieceAt(xx-dyy,yy+dyy) == null && grid.pieceAt(xx+dyy,yy-dyy) == null && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                
                // Capture
                cpy.pieceAt(xx,yy).player = cpy.current_player + 2;      
                cpy.movePiece(xx,yy,10,10);                              
                
                //Move Piece
                cpy.movePiece(x,y,xx-dyy,yy+dyy);          
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx-dyy,yy+dyy),cpy));
                break;
            }  
        }

        // UP-LEFT

        for(int dy : dys){
            int yy = y - dy;
            int xx = x - dy;

            // Movement for empty square
            if(xx>=0 && yy>=0 && grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
            Game cpy = grid.cloneGame();
            cpy.movePiece(x,y,xx,yy);           
            cpy.current_player = 1 - cpy.current_player;                 
            moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
            // Capture Option
            if( xx>0 && yy>0 && grid.pieceAt(xx,yy) != null && grid.pieceAt(xx+dyy,yy+dyy) == null && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                
                // Capture
                cpy.pieceAt(xx,yy).player = cpy.current_player + 2;     
                cpy.movePiece(xx,yy,10,10);                               
                
                //Move Piece
                cpy.movePiece(x,y,xx+dyy,yy+dyy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx+dyy,yy+dyy),cpy));
                break;
            }

         
        }

        // DOWN-LEFT
        for(int dy : dys){
            int yy = y + dy;
            int xx = x - dy;
            

            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }
            //MOVEMENT
            if(xx>=0 && yy<=7 && grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }  
            
            //CAPTURING
            if(xx>0 && yy<7 && grid.pieceAt(xx,yy) != null && grid.pieceAt(xx+dyy,yy-dyy) ==null && grid.isInside(xx,yy) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                //Capture
                cpy.pieceAt(xx,yy).player = cpy.current_player + 2;     
                cpy.movePiece(xx,yy,10,10);                               
                //Move
                cpy.movePiece(x,y,xx+dyy,yy-dyy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx+dyy,yy-dyy),cpy));
                break;
            }
             
        }

        // DOWN RIGHT
        for(int dy : dys){
            int yy = y + dy;
            int xx = x + dy;
            
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }
            //MOVEMENT
            if(xx<=7 && yy<=7 && grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            } 
            //CAPTURING
            if(xx<=7 && yy<=7 && grid.pieceAt(xx,yy) != null && grid.pieceAt(xx-dyy,yy-dyy) ==null && grid.isInside(xx,yy) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                //Capture
                cpy.pieceAt(xx,yy).player = cpy.current_player + 2;      // new state change piece player, no one has control of it
                cpy.movePiece(xx,yy,10,10);                                // new state moves the piece to captured zone 
                //Move
                cpy.movePiece(x,y,xx-dyy,yy-dyy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx-dyy,yy-dyy),cpy));
                break;
            }
        }

        return moves;
    }

  
    @Override
    public String asciiRepresentation(){
        if(player==0 || player==3){
            return "⛁ ";
        }
        if(player==1 || player ==2){
            return "⛃ ";
        }
        return "☓";
    }

    // Clone the KingPiece so that the clone is a KingPiece instance
    @Override
    public Piece clonePiece(){
        KingPiece other = new KingPiece(player,x,y);
        return other;
    }
}
