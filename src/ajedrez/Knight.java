

package ajedrez;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Knight  extends Piece {

    public Knight (int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    
    /* Knight Rules:
    A knight moves to the nearest square not on the same rank, file, or diagonal. 
    (This can be thought of as moving two squares horizontally then one square vertically, 
    or moving one square horizontally then two squares vertically—i.e. in an "L" pattern.) 
    The knight is not blocked by other pieces: it jumps to the new location.*/

    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] dys = {-1,1};     // 2 alternatives for delta y
        for(int dy : dys){
            int[] dxs = {-2,2}; // 2 alternatives for delta x
            for(int dx : dxs){
                int xx = x + dx;
                int yy = y + dy;

                // Capture Option
                if(grid.pieceAt(xx,yy) != null && grid.isInside(xx,yy) && grid.pieceAt(xx,yy).player != grid.current_player){
                    Game cpy = grid.cloneGame();
                
                    // Capture
                    cpy.pieceAt(xx,yy).player=cpy.current_player + 2;           // new state change piece player, no one has control of it
                    cpy.movePiece(xx,yy,8,7);                                   // new state moves to captured zone  
                
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

        int[] dys2 = {-2,2};     // 2 alternatives for delta y
        for(int dy : dys2){
            int[] dxs = {-1,1}; // 2 alternatives for delta x
            for(int dx : dxs){
                int xx = x + dx;
                int yy = y + dy;
            
                // Capture Option
                if(grid.pieceAt(xx,yy) != null && grid.isInside(xx,yy) && grid.pieceAt(xx,yy).player != grid.current_player){
                    Game cpy = grid.cloneGame();

                    // Capture
                    cpy.pieceAt(xx,yy).player = cpy.current_player + 2;     // new state change piece player, no one has control of it 
                    cpy.movePiece(xx,yy,8,7);                               // new state moves to captured zone  
                
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
        if(player==0 || player==3){
            return "♘ ";
        }
        if(player==1 || player ==2){
            return "♞ ";
        }
        return "??";
    }

    @Override
    public Piece clonePiece(){
     Knight  other = new Knight (player,x,y);
        return other;
    }
}