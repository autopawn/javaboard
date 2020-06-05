package foxhounds;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Hound extends Piece {

    public Hound(int player, int x, int y){
        super(player,x,y);
    }

    @Override
    public List<Movement> getMovements(Game state){
        GridGame grid = (GridGame) state;

        List<Movement> moves = new LinkedList<Movement>();

        int    dy = 2*player-1;
        int[] dxs = {-1,1};
        for(int dx : dxs){
            int xx = x + dx;
            int yy = y + dy;
            // Check inside bounds
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);
                cpy.current_player = 1 - cpy.current_player;
                moves.add( new Movement(Movement.moveCommand(x,y,xx,yy),cpy) );
            }
        }
        return moves;
    }

    @Override
    public String asciiRepresentation(){
        return "🄷 ";
    }

    @Override
    public Piece clonePiece(){
        Hound other = new Hound(player,x,y);
        return other;
    }
}
