package javaboard;

import java.util.List;

public abstract class Piece {
    public int x,y;
    public int player;

    public Piece(int player, int x, int y){
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public abstract String asciiRepresentation();

    public abstract List<Movement> getMovements(Game state);

    public abstract Piece clonePiece();
}
