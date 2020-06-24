package foxhounds;

import java.util.LinkedList;
import java.util.List;

import javaboard.Evaluable;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

/*
FoxAndHounds is a game where player 0, that controls a Fox, tries to reach
the other side of the board; while player 1, that controls 4 hounds, tries
to stop him.
The fox can move diagonally forward and backwards, the hounds can only move forward.
There is no eating.
*/
public class FoxAndHounds extends GridGame implements Evaluable {

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
    public String GameName(){
        return "FoxAndHounds";//nombre del juego

    }

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        // If the fox reaches the top side of the board, player 0 wins
        for(Piece pc : pieces){
            if(pc.player==0 && pc.y==0) return 0;
        }
        // Test default win condition
        Integer result = super.currentWinner(current_player_moves);
        return result;
    }

    // Clone the FoxAndHounds so that the clone is a FoxAndHounds instance
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

    // Default evaluation function, so that PlayerCPUEval works for FoxAndHounds
    @Override
    public float defaultEvaluationFunction(){
        // How good is the state for the fox:
        float eval = 0;
        // Get the fox and how near it is to the other side of the board
        Piece fox = null;
        for(Piece pc : pieces){
            if(pc.player==0) fox = pc;
        }
        eval += 7-fox.y;
        // Hound position
        for(Piece pc : pieces){
            if(pc.player==1){
                if(pc.y >= fox.y){
                    // It is very good for the fox if is has passed by the hounds
                    eval += 0.5;
                }else{
                    // It is better for the fox if the hounds are far in x
                    eval += 0.05*Math.abs(pc.x-fox.x);
                }
            }
        }
        // Negate eval if the current player is not the fox
        if(current_player!=0) eval = -eval;
        // Return eval
        return eval;
    }

}
