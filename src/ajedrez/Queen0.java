

package ajedrez;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Queen0  extends Piece {

    public Queen0
 (int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    // A Fox can move diagonally forward and backwards
    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        /*int[] dys = {-7,-6,-5,-4,-3,-2,-1,1,2,3,4,5,6,7};     // 2 alternatives for delta y
        for(int dy : dys){
            int[] dxs = {-7,-6,-5,-4,-3,-2,-1,1,2,3,4,5,6,7}; // 2 alternatives for delta x
            for(int dx : dxs){
                int xx = x + dx;
                int yy = y + dy;
                // Check inside bounds and that there is not piece
                if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                    // Clone the current state to use it in the movement
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);                    // new state moves this piece
                    cpy.current_player = 1 - cpy.current_player; // new state changes player
                    // Append new movement to the movement list
                    moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                    moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                }
            }
        }*/
        int[] dys = {1,2,3,4,5,6,7};     // 2 alternatives for delta y
        for(int dy : dys){
            int yy = y - dy;
                // Check inside bounds and that there is not piece
            if(grid.pieceAt(x,yy)!=null){
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
            if(grid.pieceAt(x,yy)!=null){
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
            if(grid.pieceAt(xx,y)!=null){
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
            if(grid.pieceAt(xx,y)!=null){
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
            int yy = y + dy;
                // Check inside bounds and that there is not piece
            if(grid.pieceAt(xx,yy)!=null){
                break;
            }
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }
        for(int dy : dys){
            int xx = x + dy;
            int yy = y - dy;
                // Check inside bounds and that there is not piece
            if(grid.pieceAt(xx,yy)!=null){
                break;
            }
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }
        for(int dy : dys){
            int xx = x - dy;
            int yy = y + dy;
                // Check inside bounds and that there is not piece
            if(grid.pieceAt(xx,yy)!=null){
                break;
            }
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }
        for(int dy : dys){
            int xx = x - dy;
            int yy = y - dy;
                // Check inside bounds and that there is not piece
            if(grid.pieceAt(xx,yy)!=null){
                break;
            }
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }


        return moves;
    }

    @Override
    public String asciiRepresentation(){
        return "♕ ";
    }

    // Clone the Fox so that the clone is a Fox instance
    @Override
    public Piece clonePiece(){
     Queen0
      other = new Queen0
     (player,x,y);
        return other;
    }
}