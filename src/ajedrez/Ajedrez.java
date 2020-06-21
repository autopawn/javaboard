package ajedrez;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Ajedrez extends GridGame{

    public Ajedrez(){
        size_x = 8;
        size_y = 8;
        pieces = new LinkedList<Piece>();
        pieces.add(new Rook(0,0,7));
        pieces.add(new Rook(0,7,7));
        pieces.add(new Rook(1,0,0));
        pieces.add(new Rook(1,7,0));
        pieces.add(new Knight(0,1,7));
        pieces.add(new Knight(0,6,7));
        pieces.add(new Knight(1,1,0));
        pieces.add(new Knight(1,6,0));
        pieces.add(new Bishop(0,2,7));
        pieces.add(new Bishop(0,5,7));
        pieces.add(new Bishop(1,2,0));
        pieces.add(new Bishop(1,5,0));
        pieces.add(new Queen(0,3,7));
        pieces.add(new Queen(1,3,0));
        pieces.add(new King(0,4,7));
        pieces.add(new King(1,4,0));

        int[] abc = {0,1,2,3,4,5,6,7};
        for(int i : abc){
            pieces.add(new Pawn(0,i,6));
            pieces.add(new Pawn(1,i,1));
        }
    }

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        return null;
    }

    // Clone the Ajedrez so that the clone is a Ajedrez instance
    @Override
    public Game cloneGame(){
        GridGame clone = new Ajedrez();
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