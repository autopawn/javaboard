package connectfour;
import java.util.LinkedList;
import java.util.List;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class connectfour extends GridGame {

    public connectfour(){
        size_x = 7;
        size_y = 6;
        pieces = new LinkedList<Piece>();
    }
    
    public int segundaBusqueda(int x, int y, int i, int j){
        int cont = 0;
        for (int a = 1; a <= 2; a++){
            if (this.isInside(x+a*i,y+a*j)){
                if (this.pieceAt(x+a*i,y+a*j) != null){
                    if (this.pieceAt(x+a*i,y+a*j).player == 1 - current_player){
                        cont += 1;
                    }
                }
            }
        }
        return cont; 
    }
    
    public Integer primeraBusqueda(int x, int y){
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (i == 0 && j == 0) continue;
                if (this.isInside(x+i,y+j)){
                    if (this.pieceAt(x+i,y+j) != null){
                        if (this.pieceAt(x+i,y+j).player == 1 - current_player){
                            if (segundaBusqueda(x+i,y+j,i,j) == 2){
                                return (1 - current_player);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        for (int x = 0; x < size_x; x++){
            for (int y = 0; y < size_y; y++){
                if (this.pieceAt(x,y) != null){
                    if (this.pieceAt(x,y).player == 1 - current_player){
                        Integer busqueda = primeraBusqueda(x,y);
                        if (busqueda != null){
                            return busqueda;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Movement> getMovements(){
        List<Movement> moves = new LinkedList<Movement>();
        char[] letras = {'a','b','c','d','e','f','g'};
        int y;
        for (int x = 0; x < this.size_x; x++){
            y = 5;
            while (this.pieceAt(x,y) != null){
                y = y-1;
            }
            if (y < 0) continue;
            Game cpy = this.cloneGame();
            String s = String.valueOf(y);
            moves.add(new Movement(letras[x]+s,cpy));
            cpy.pieces.add(new piezaCon(this.current_player,x,y));
            cpy.current_player = 1 - cpy.current_player;
            
        }
        return moves;
    }

    @Override
    public Game cloneGame(){
        GridGame clone = new connectfour();
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
                    // Draw piece
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else{
                    sbu.append("  ");
                }
            }
            sbu.append("\n");
        }
        return sbu.toString();
    }

}