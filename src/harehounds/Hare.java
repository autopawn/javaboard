package harehounds;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Hare extends Piece {

    public Hare(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    // A hare can move in all 8 directions
    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridChange grid = (GridChange) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] dys = {-1,0,1};     // 2 alternatives for delta y
        for(int dy : dys){
            int[] dxs = {-1,0,1}; // 2 alternatives for delta x
            for(int dx : dxs){
                int revisar = dx + dy;
                if(dy==0 && dx ==0){
                  continue;
                }
                // Como el tablero tiene ciertas limitaciones de movimiento en algunas casillas, hay que considerarlas.
                if(x==1 && (y==1 || y==3)){
                  //Me fije que cuando no se mueve diagonalmente, la suma de las coordenadas o es -1 o 1, por lo cual en las casillas
                  //donde no puede haber movimientos diagonales, limito las sumas de 0, 2 y -2
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
        return "◉ ";
    }

    // Clone the hare so that the clone is a hare instance
    @Override
    public Piece clonePiece(){
        Hare other = new Hare(player,x,y);
        return other;
    }
}
