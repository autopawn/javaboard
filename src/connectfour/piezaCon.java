package connectfour;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.Movement;
import javaboard.Piece;

public class piezaCon extends Piece {
    public piezaCon(int player, int x, int y){
        super(player,x,y);
    }

    @Override
    public String asciiRepresentation(){
        if(player==0){
            return "☼ ";
        }
        if(player==1){
            return "♦ ";
        }
        return "??";
    }

    @Override
    public Piece clonePiece(){
        piezaCon other = new piezaCon(player,x,y);
        return other;
    }

    public List<Movement> getMovements(Game state){
        List<Movement> moves = new LinkedList<Movement>();
        return moves;
    }
}