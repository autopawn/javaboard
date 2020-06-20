package ConnectFour;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.Movement;
import javaboard.Piece;

public class ConnectPiece extends Piece {
    public ConnectPiece(int player, int x, int y){
        super(player,x,y);
    }

    // Creado por obligación lolen
    @Override
    public List<Movement> getMovements(Game state){
        List<Movement> moves = new LinkedList<Movement>();
        return moves;
    }

    // Draw cute UTF-8 characters for the KonoPiece
    @Override
    public String asciiRepresentation(){
        if(player==0){
            return "⚉ ";
        }
        if(player==1){
            return "⚇ ";
        }
        return "??";
    }

    @Override
    public Piece clonePiece(){
        ConnectPiece other = new ConnectPiece(player,x,y);
        return other;
    }
}