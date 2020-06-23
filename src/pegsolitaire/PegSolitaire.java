package pegsolitaire;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

/*
PegSolitaire es un juego en el cual se deben mover piezas de forma horizontal o vertical, 2 espacios cada vez.
Para esto, se debe pasar si o si por otra pieza anexada, la cual será eliminada luego del movimiento.
La condición de victoria es cuando queda solo una pieza y está posicionada en el centro
De lo contrario, si no quedan movimientos legales(que involucren piezas anexas), entonces se pierde.
*/
public class PegSolitaire extends GridGame{
    //Metodo PegSolitaire() crea las piezas en los respectivos lugares correspondientes

    public PegSolitaire(){
        size_x = 7;
        size_y = 7;
        pieces = new LinkedList<Piece>();
        for (int i=0;i<size_x;i++){
          for (int j=0;j<size_y;j++){
            if (((1<i)&&(i<5))||((1<j)&&(j<5))){
              if ((i!=3)||(j!=3)){
                pieces.add(new PegPiece(0,i,j));
              }
            }
          }
        }
    }

    //Misma función toString() solo que modificada para la correcta impresión del tablero
    @Override
    public String toString(){
        StringBuilder sbu = new StringBuilder();

        // First row with letters
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
                Piece pc_in_cell = pieceAt(x,y);
                if(pc_in_cell!=null){
                    // Draw PegSolitairePiece
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else{
                    // Draw cells
                    if ((((1<x)&&(x<5))||((1<y)&&(y<5)))){
                      sbu.append("□ ");
                    }
                    else{
                      sbu.append("  ");
                    }
                }
            }
            sbu.append("\n");
        }
        return sbu.toString();
    }


    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        for(Piece pc : pieces){
            // Si solo queda una pieza y está en la posición (3,3)
            if(pc.x==3 && pc.y==3 && pieces.size()==1) return 0;
        }
        // Condición de derrota
        if(current_player_moves.size()==0){
            assert(current_player==0);
            return 1-current_player;
        }
        //Condición para que el juego siga funcionando
        return null;
    }

    // Clone the PegSolitaire so that the clone is a PegSolitaire instance
    @Override
    public Game cloneGame(){
        GridGame clone = new PegSolitaire();
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
