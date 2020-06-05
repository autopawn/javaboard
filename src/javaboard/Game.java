package javaboard;

import java.util.LinkedList;
import java.util.List;

public abstract class Game {

    public List<Piece> pieces;
    public int current_player;

    public List<Movement> getMovements(){
        List<Movement> moves = new LinkedList<Movement>();

        for(Piece pc : pieces){
            if(pc.player != current_player) continue;
            List<Movement> piece_moves = pc.getMovements(this);
            for(Movement mov : piece_moves) {
                if(!mov.result.isValid()) continue;
                moves.add(mov);
            }
        }
        return moves;
    }

    public boolean isValid(){
        return true;
    }

    public Integer currentWinner(List<Movement> current_player_moves){
        if(current_player_moves.size()==0){
            assert(current_player==0 || current_player==1);
            return 1-current_player;
        }
        return null;
    }

    public Piece pieceAt(int x, int y){
        for(Piece pc : pieces){
            if(pc.x == x && pc.y == y) return pc;
        }
        return null;
    }

    public void movePiece(int x, int y, int xx, int yy){
        Piece pc = pieceAt(x,y);
        if(pc==null) return;
        pc.x = xx;
        pc.y = yy;
    }

    public abstract Game cloneGame();


}
