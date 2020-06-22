package damas;

import java.util.LinkedList;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Piece;


/*Drama con el comer si o si, */

public class Damas extends GridGame{
    int veces=0;

    public Damas(){
        size_x = 8;
        size_y = 8;
        pieces = new LinkedList<Piece>();
        pieces.add(new White(1,3,0));
        pieces.add(new White(1,5,0));
        pieces.add(new White(1,7,0));
        pieces.add(new White(1,1,0));
        pieces.add(new White(1,6,1));
        pieces.add(new White(1,4,1));
        pieces.add(new White(1,2,1));
        pieces.add(new White(1,0,1));
        pieces.add(new White(1,3,2));
        pieces.add(new White(1,5,2));
        pieces.add(new White(1,7,2));
        pieces.add(new White(1,1,2));
        pieces.add(new Black(0,0,5));
        pieces.add(new Black(0,2,5));
        pieces.add(new Black(0,4,5));
        pieces.add(new Black(0,6,5));
        pieces.add(new Black(0,1,6));
        pieces.add(new Black(0,3,6));
        pieces.add(new Black(0,5,6));
        pieces.add(new Black(0,7,6));
        pieces.add(new Black(0,0,7));
        pieces.add(new Black(0,2,7));
        pieces.add(new Black(0,4,7));
        pieces.add(new Black(0,6,7));
    }
    @Override
    public Game cloneGame() {
        GridGame clone = new Damas();
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