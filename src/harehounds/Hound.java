package harehounds;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Hound extends Piece {

    public Hound(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    // A Hound can move diagonally but only forward
    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridChange grid = (GridChange) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        // Player 0 moves on -Y and player 1 on +Y
        int[] dys = {2*player-1,0};     // 2 alternatives for delta y
        for(int dy : dys){
            int[] dxs = {-1,0,1};  // 2 alternatives for delta x
            for(int dx : dxs){
                int revisar = dx + dy;
                if(dy==0 && dx ==0){
                  continue;
                }
                if(x==1 && (y==1 || y==3)){
                  if(revisar == 0 || revisar == 2 || revisar == -2){
                    continue;
                  }
                }
                if(y==2 && (x==0 || x==2)){
                  if(revisar == 0 || revisar == 2 || revisar == -2){
                    continue;
                  }
                }
                int xx = x + dx;
                int yy = y + dy;
                // Check inside bounds and that there is not piece
                if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                    // Clone the current state to use it in the movement
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);                     // new state moves this piece
                    cpy.current_player = 1 - cpy.current_player;  // new state changes player
                    // Append new movement to the movement list
                    moves.add( new Movement(Movement.moveCommand(x,y,xx,yy),cpy) );
              }
          }
        }

        return moves;
    }

    @Override
    public String asciiRepresentation(){
        return "☉ ";
    }

    // Clone the Hound so that the clone is a Hound instance
    @Override
    public Piece clonePiece(){
        Hound other = new Hound(player,x,y);
        return other;
    }
}
