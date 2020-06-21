package connectfour;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Pieza extends Piece {

    public Pieza(int player, int x, int y) {
        super(player, x, y);
    }

    //cualquier parecido con la funcion de Movement.java es mera coincidencia
    public static String moveCommand(int x, int y, int xx, int yy){//modificado para que solo muestre el destino
        char xxn = (char)('a'+xx);
        return ""+xxn+yy;
    }

    @Override
    public String asciiRepresentation() {
        if(player == 0){
            return "🄌 ";
        }
        else if(player == 1){
            return "🄋 ";
        }
        return "? ";
    }

    @Override
    public List<Movement> getMovements(Game state) {
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();
        int[] dxs = {0, 1, 2, 3, 4, 5, 6}; // 2 alternatives for delta x
        for(int dx : dxs){
			int xx = dx;
            int yy = 5;
            // Check inside bounds and that there is not piece
            if(!grid.isInside(x, 1)){
                if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                    // Clone the current state to use it in the movement
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);                    // new state moves this piece
                    cpy.current_player = 1 - cpy.current_player; // new state changes player
                    // Append new movement to the movement list
                    moves.add(new Movement(moveCommand(x,y,xx,yy),cpy));
                }
                else{
                    for(int i=5; i>=0; i--){
                        if(grid.isInside(xx,i) && grid.pieceAt(xx,i)==null){
                            // Clone the current state to use it in the movement
                            Game cpy = grid.cloneGame();
                            cpy.movePiece(x,y,xx,i);                    // new state moves this piece
                            cpy.current_player = 1 - cpy.current_player; // new state changes player
                            // Append new movement to the movement list
                            moves.add(new Movement(moveCommand(x,y,xx,i),cpy));
                            break;
                        }
                        else{
                            continue;
                        }
                    }
                }
            }
        }
        return moves;
    }

    @Override
    public Piece clonePiece() {
        Pieza other = new Pieza(player,x,y);
        return other;
    }
    
}
