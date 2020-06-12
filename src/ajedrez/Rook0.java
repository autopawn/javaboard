

package ajedrez;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Rook0 extends Piece {

    public Rook0(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    // A Fox can move diagonally forward and backwards
    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] dys = {1,2,3,4,5,6,7};     // 2 alternatives for delta y
        for(int dy : dys){
            int yy = y - dy;
                // Check inside bounds and that there is not piece
            if(grid.pieceAt(x,yy)!=null && grid.pieceAt(x,yy).player == grid.current_player){
                break;
            }

            if(grid.pieceAt(x,yy)!=null && grid.pieceAt(x,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.pieceAt(x,yy).player=cpy.current_player+2; // Change piece player, no one has control of it 
                cpy.movePiece(x,yy,8,7); // moves to captured zone  
                cpy.movePiece(x,y,x,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                break;
            }

            if(grid.isInside(x,yy) && grid.pieceAt(x,yy)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,x,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));              
            }   
        }

        for(int dy : dys){
            int yy = y + dy;
                // Check inside bounds and that there is not piece
            if(grid.pieceAt(x,yy)!=null && grid.pieceAt(x,yy).player == grid.current_player){
                break;
            }
            if(grid.pieceAt(x,yy)!=null && grid.pieceAt(x,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.pieceAt(x,yy).player=cpy.current_player+2; // Change piece player, no one has control of it 
                cpy.movePiece(x,yy,8,7); // moves to captured zone  
                cpy.movePiece(x,y,x,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                break;
            }
            if(grid.isInside(x,yy) && grid.pieceAt(x,yy)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,x,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));              
            }   
        }
        for(int dy : dys){
            int xx = x - dy;
                // Check inside bounds and that there is not piece
            if(grid.pieceAt(xx,y)!=null && grid.pieceAt(xx,y).player == grid.current_player){
                break;
            }
            if(grid.pieceAt(xx,y)!=null && grid.pieceAt(xx,y).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.pieceAt(xx,y).player=cpy.current_player+2; // Change piece player, no one has control of it 
                cpy.movePiece(xx,y,8,7); // moves to captured zone  
                cpy.movePiece(x,y,xx,y);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));
                break;
            }
            if(grid.isInside(xx,y) && grid.pieceAt(xx,y)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,y);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));              
            }   
        }
        for(int dy : dys){
            int xx = x + dy;
                // Check inside bounds and that there is not piece
            if(grid.pieceAt(xx,y)!=null && grid.pieceAt(xx,y).player == grid.current_player){
                break;
            }
            if(grid.pieceAt(xx,y)!=null && grid.pieceAt(xx,y).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.pieceAt(xx,y).player=cpy.current_player+2; // Change piece player, no one has control of it 
                cpy.movePiece(xx,y,8,7); // moves to captured zone  
                cpy.movePiece(x,y,xx,y);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));
                break;
            }
            if(grid.isInside(xx,y) && grid.pieceAt(xx,y)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,y);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));              
            }   
        }

        return moves;
    }

    @Override
    public String asciiRepresentation(){
        return "♖ ";
    }

    // Clone the Fox so that the clone is a Fox instance
    @Override
    public Piece clonePiece(){
     Rook0 other = new Rook0(player,x,y);
        return other;
    }
}