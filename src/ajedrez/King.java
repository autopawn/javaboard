

package ajedrez;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class King  extends Piece {

    public King(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    /* King Rules: 
    The king moves exactly one square horizontally, vertically, or diagonally. */

    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] dys = {-1,0,1};     
        for(int dy : dys){
            int[] dxs = {-1,0,1}; 
            for(int dx : dxs){
                int xx = x + dx;
                int yy = y + dy;
            
                // Capture Option    
                if(grid.pieceAt(xx,yy) != null && grid.isInside(xx,yy) && grid.pieceAt(xx,yy).player != grid.current_player){
                    Game cpy = grid.cloneGame();

                    // Capture
                    cpy.pieceAt(xx,yy).player = cpy.current_player + 2; 
                    cpy.movePiece(xx,yy,8,7);
                
                    // Move Piece
                    cpy.movePiece(x,y,xx,yy);           
                    cpy.current_player = 1 - cpy.current_player;                 
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                }
            
                // Movement for empty square
                if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                    
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);                    
                    cpy.current_player = 1 - cpy.current_player; 
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                }
            }
        }
        return moves;
    }   


    @Override
    public String asciiRepresentation(){
        if(player == 0 || player == 3){
            return "♔ ";
        }
        if(player == 1 || player == 2){
            return "♚ ";
        }
        return "??";
    }  
    
    @Override
    public Piece clonePiece(){
     King other = new King(player,x,y);
        return other;
    }
}