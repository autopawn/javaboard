package foxhounds;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class FoxAndHounds extends GridGame {

    public FoxAndHounds(){
        size_x = 8;
        size_y = 8;
        pieces = new LinkedList<Piece>();
        pieces.add(new Hound(1,1,0));
        pieces.add(new Hound(1,3,0));
        pieces.add(new Hound(1,5,0));
        pieces.add(new Hound(1,7,0));
        pieces.add(new Fox(0,0,7));
    }

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        for(Piece pc : pieces){
            if(pc.player==0 && pc.y==0) return 0;
        }
        Integer result = super.currentWinner(current_player_moves);
        return result;
    }

    @Override
    public Game cloneGame(){
        GridGame clone = new FoxAndHounds();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }
}
