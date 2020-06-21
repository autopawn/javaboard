package tictactoe;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;
import java.util.LinkedList;
import java.util.List;

//tablero
public class tictactoe extends GridGame {

    public int win = 2;
    public tictactoe(){
        size_x = 3;
        size_y = 3;
        pieces = new LinkedList<Piece>();
        for(int x = 0;x<size_x;x++){
            for (int y = 0;y<size_y;y++){

                pieces.add(new tictactoexd(2,x,y));
            }
        }

    }

    public int getwin(){
        return win;
    }
    public void setwin(int ganador){
        this.win = ganador;
    }

    @Override
    public Game cloneGame(){
        GridGame clone = new tictactoe();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }

    @Override
    //ve si algun jugador gana(3 figuras consecutivas) o hay empate
    public Integer currentWinner(List<Movement> current_player_moves){
        if (puntaje(0,this) == 3){
            return 0;
        }
        if (puntaje(1,this) == 3){
            return 1;
        }
        if (Full(this)){
            System.out.println("Empate");
            System.exit(0);
            return null;
        }

        return null;
    }
    private int puntaje(int player, GridGame state){
        int ptje_act = 0;
        int ptje_max = 0;
        
        //horizontal;
        for(int fila = 0;fila<3;fila++){
            for (int col = 0;col<3;col++){
                if (state.pieceAt(col,fila).player == player){
                    ptje_act++;
                }
            }
            if (ptje_act>ptje_max){
                ptje_max = ptje_act;
            }
            ptje_act = 0;
        }

        //vertical
        for(int col = 0;col<3;col++){
            for (int fila = 0;fila<3;fila++){
                if (state.pieceAt(col,fila).player == player){
                    ptje_act++;
                }
            }
            if (ptje_act>ptje_max){
                ptje_max = ptje_act;
            }
            ptje_act = 0;
        }

        //diagonal 1
        ptje_act= 0;
        for(int fila = 0;fila<3;fila++){
            if(state.pieceAt(2-fila,fila).player == player){
                ptje_act++;
            }
        }
        if (ptje_act>ptje_max){
            ptje_max= ptje_act;
        }

        //diagonal 2
        ptje_act = 0;
        for(int fila = 0;fila<3;fila++){
            if(state.pieceAt(fila,fila).player == player){
                ptje_act++;
            }
        }
        if (ptje_act>ptje_max){
            ptje_max = ptje_act;
        }
        
        return ptje_max;
    }

    //ve si la tabla esta llena
    private boolean Full(tictactoe state){
        for(int x = 0;x<3;x++){
            for (int y = 0;y<3;y++){
                if (state.pieceAt(x,y).player == 2){
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public String toString(){
        StringBuilder sbuilder = new StringBuilder();

        //fila con letras
        sbuilder.append("\n");
        sbuilder.append("   ");
        for(int x=0;x<size_x;x++){
            String eti_x = ""+((char)('a'+x));
            sbuilder.append(" "+eti_x+" ");
        }
        sbuilder.append("\n");

        for(int y=0;y<size_y;y++){
            // numeros
            String eti_y = ""+y;
            if(eti_y.length()<2) sbuilder.append(" ");
            sbuilder.append(eti_y+" ");

            // dibuja celdas y piezas
            for(int x=0;x<size_x;x++){
                Piece pieza = pieceAt(x,y);
                if(pieza!=null){ //piezas
                    String ascii = pieza.asciiRepresentation();
                    sbuilder.append(ascii);
                }else{ //celdas
                    if((x+y)%2==0) sbuilder.append("■ ");
                    else sbuilder.append("□ ");
                }
            }
            sbuilder.append("\n");
        }
        return sbuilder.toString();
    }

}