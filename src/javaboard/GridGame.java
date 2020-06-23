package javaboard;

import java.io.*;


// Represents a Game within a grid
public abstract class GridGame extends Game {
    //es el tamaño del tablero
    public int size_x, size_y;

    // Graphical representation of a grid
    @Override
    public String toString(){
        //permite declarar un string mutable(stringbuilder)
        StringBuilder sbu = new StringBuilder();

        // primera fila de letras
        sbu.append("\n");
        sbu.append("   ");
        for(int x=0;x<size_x;x++){
            String xlabel = ""+((char)('a'+x));
            sbu.append(xlabel+" ");
        }
        sbu.append("\n");

        // Other parts of the board
        for(int y=0;y<size_y;y++){
            // Number label
            String ylabel = ""+y;
            if(ylabel.length()<2) sbu.append(" ");
            sbu.append(ylabel+" ");

            // Each cell
            for(int x=0;x<size_x;x++){
                //para conocer la posicion actual del jugador(creo)
                Piece pc_in_cell = pieceAt(x,y);
                if(pc_in_cell!=null){
                    // Draw piece
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else{
                    // Draw cell
                    if((x+y)%2==0) sbu.append("■ ");
                    else sbu.append("□ ");
                }
            }
            sbu.append("\n");
        }//retorna un string
        return sbu.toString();
    }

    // Wheter a given position is inside the board
    //comprueba que ciertamnete las posicon (x,y) esta detro del tablero. Retora un bool
    public boolean isInside(int x, int y){
        return x>=0 && y>=0 && x<size_x && y<size_y;
    }

}
