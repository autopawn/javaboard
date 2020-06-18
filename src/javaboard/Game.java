package javaboard;

import java.util.LinkedList;
import java.util.List;

// Represents a current game state and its rules
public abstract class Game {
    // List of pieces

    public List<Piece> pieces;
    // Which player has the current turn
    public int current_player;

    public abstract String NameGame();

    // Retrieves the movements that the current_player can do
    // By default is the union of the piece's movement, discarding invalid states.
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

    // If the current state is valid or not; by default it is.
    public boolean isValid(){
        return true;
    }

    // Unless overriden, a player loses if she doesn't more available movements
    public Integer currentWinner(List<Movement> current_player_moves){
        if(current_player_moves.size()==0){
            assert(current_player==0 || current_player==1);
            return 1-current_player;
        }
        return null;
    }

    // Get the piece at the given position, or null.
    public Piece pieceAt(int x, int y){
        for(Piece pc : pieces){
            if(pc.x == x && pc.y == y) return pc;
        }
        return null;
    }

    // Moves a piece in the position (x,y) to the position (xx,yy)
    public void movePiece(int x, int y, int xx, int yy){
        Piece pc = pieceAt(x,y);
        if(pc==null) return;
        pc.x = xx;
        pc.y = yy;
    }

    // Must retrieve a clone of the current game
    // Should ALWAYS be overrided, for any subclass.
    public abstract Game cloneGame();


}
