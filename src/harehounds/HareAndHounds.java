package harehounds;

import java.util.LinkedList;
import java.util.List;

import javaboard.Evaluable;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

/*
HareAndHounds is a game where player 0, that controls a hare, tries to reach
the other side of the board; while player 1, that controls 3 hounds, tries
to stop him.
The hare can move in all directions, the hounds can only move forward and sideways.
The movement must respect the lines of the board.
There is no eating (yet).
*/
public class HareAndHounds extends GridChange implements Evaluable {

    public HareAndHounds(){
        size_x = 3;
        size_y = 5;
        pieces = new LinkedList<Piece>();
        pieces.add(new Hound(1,1,0));
        pieces.add(new Hound(1,0,1));
        pieces.add(new Hound(1,2,1));
        pieces.add(new Hare(0,1,4));
    }

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        // If the hare reaches the left side of the board, player 0 wins
        for(Piece pc : pieces){
            if(pc.player==0 && pc.y==0) return 0;
        }
        // Test default win condition
        Integer result = super.currentWinner(current_player_moves);
        return result;
    }

    // Clone the HareAndHounds so that the clone is a HareAndHounds instance
    @Override
    public Game cloneGame(){
        GridChange clone = new HareAndHounds();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }

    // Default evaluation function, so that PlayerCPUEval works for HarendHounds
    @Override
    public float defaultEvaluationFunction(){
        // How good is the state for the hare:
        float eval = 0;
        // Get the hare and how near it is to the other side of the board
        Piece hare = null;
        for(Piece pc : pieces){
            if(pc.player==0) hare = pc;
        }
        eval += 4-hare.y;
        // Hound position
        for(Piece pc : pieces){
            if(pc.player==1){
                if(pc.y >= hare.y){
                    // It is very good for the hare if is has passed by the hounds
                    eval += 4.0;
                }else{
                    // It is better for the hare if the hounds are far in x
                    eval += 0.05*Math.abs(pc.x-hare.x);
                }
            }
        }
        // Negate eval if the current player is not the hare
        if(current_player!=0) eval = -eval;
        // Return eval
        return eval;
    }

}
