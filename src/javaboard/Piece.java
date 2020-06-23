package javaboard;

import java.io.*;
import java.util.List;

// piezas en la tabla
public abstract class Piece implements java.io.Serializable {
    // posicion de la pieza
    public int x,y;
    // la pieza que tiene el jugador actual
    public int player;
    //modifica los valores de la superclase
    //player es el jugador (x,y ) es la posicion el tablero
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
