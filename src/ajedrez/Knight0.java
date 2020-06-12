

package ajedrez;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Knight0  extends Piece {

    public Knight0 (int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    // A Fox can move diagonally forward and backwards
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


            if(grid.pieceAt(xx,yy)!=null && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.pieceAt(xx,yy).player=cpy.current_player+2; // Change piece player, no one has control of it 
                cpy.movePiece(xx,yy,8,7); // moves to captured zone  
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));

            } 
                // Check inside bounds and that there is not piece
                if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                    // Clone the current state to use it in the movement
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);                    // new state moves this piece
                    cpy.current_player = 1 - cpy.current_player; // new state changes player
                    // Append new movement to the movement list
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

            if(grid.pieceAt(xx,yy)!=null && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.pieceAt(xx,yy).player=cpy.current_player+2; // Change piece player, no one has control of it 
                cpy.movePiece(xx,yy,8,7); // moves to captured zone  
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
            } 
                // Check inside bounds and that there is not piece
                if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                    // Clone the current state to use it in the movement
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);                    // new state moves this piece
                    cpy.current_player = 1 - cpy.current_player; // new state changes player
                    // Append new movement to the movement list
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                }
            }
        }

    
        return moves;
    }

    @Override
    public String asciiRepresentation(){
        return "♘ ";
    }

    // Clone the Fox so that the clone is a Fox instance
    @Override
    public Piece clonePiece(){
     Knight0  other = new Knight0 (player,x,y);
        return other;
    }
}