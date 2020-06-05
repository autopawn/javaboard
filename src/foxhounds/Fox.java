package foxhounds;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Fox extends Piece {

    public Fox(int player, int x, int y){
        super(player,x,y);
    }

    @Override
    public List<Movement> getMovements(Game state){
        GridGame grid = (GridGame) state;
        // Movement list
        List<Movement> moves = new LinkedList<Movement>();
        // Player 0 moves on -Y and player 1 on +Y
        int[] dys = {-1,1};
        for(int dy : dys){
            int[] dxs = {-1,1};
            for(int dx : dxs){
                int xx = x + dx;
                int yy = y + dy;
                // Check inside bounds and that there is not piece
                if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
                    Game cpy = grid.cloneGame();
                    cpy.current_player = 1 - cpy.current_player;
                    cpy.movePiece(x,y,xx,yy);
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                }
            }
        }
        return moves;
    }

    @Override
    public String asciiRepresentation(){
        return "🄵 ";
    }

    @Override
    public Piece clonePiece(){
        Fox other = new Fox(player,x,y);
        return other;
    }
}
