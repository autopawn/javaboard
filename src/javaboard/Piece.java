package javaboard;

import java.util.List;

// A piece in the board
public abstract class Piece implements java.io.Serializable {
    // Piece position
    public int x,y;
    // Current piece player
    public int player;

    public Piece(int player, int x, int y){
        this.player = player;
        this.x = x;
        this.y = y;
    }

    // Representation of the piece as a lenght 2 String
    public abstract String asciiRepresentation();

    // Get a list of the movements available for a player if this piece is on the board
    public abstract List<Movement> getMovements(Game state);

    // Clone a piece
    // Should ALWAYS be overrided, for any subclass.
    public abstract Piece clonePiece();
}
