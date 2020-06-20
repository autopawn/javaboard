package ConnectFour;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

/*
The players take turns moving one of their pieces one square diagonally.
The first player to move all of their pieces to their opponent's starting positions wins.
*/

public class ConnectFour extends GridGame {

    public ConnectFour(){
        size_x = 7;
        size_y = 6;
        pieces = new LinkedList<Piece>();
    }

    public int recursiveCount(Integer c, int x, int y, int i, int j){
        if (c == 3) return c;
        if (this.isInside(x+i,y+j) && this.pieceAt(x+i,y+j) != null && this.pieceAt(x+i,y+j).player == 1 - current_player){
            return recursiveCount(c+1,x+i,y+j,i,j);
        }
        return 0; 
    }


    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        int c = 0;
        for (int x = 0; x < 7; x++){
            for (int y = 0; y < 6; y++){
                if (this.pieceAt(x,y) != null && this.pieceAt(x,y).player == 1 - current_player){
                    for (int i = -1; i < 2; i++){
                        for (int j = -1; j < 2; j++){
                            if (i == 0 && j == 0) continue;
                            if (this.isInside(x+i,y+j) && this.pieceAt(x+i,y+j) != null && this.pieceAt(x+i,y+j).player == 1 - current_player){
                                if (recursiveCount(c+1,x+i,y+j,i,j) == 3){
                                    return (1 - current_player);
                                }
                            }
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
        GridGame grid = (GridGame) this;
        char[] letters = {'a','b','c','d','e','f','g'};
        int[] eje_x = {0,1,2,3,4,5,6};
        
        for (int x : eje_x){
            int c = 5;
            while (grid.pieceAt(x,c) != null){
                c -= 1;
            }
            if (c >= 0){
                Game cpy = grid.cloneGame();
                cpy.pieces.add(new ConnectPiece(grid.current_player,x,c));
                cpy.current_player = 1 - cpy.current_player;
                String s = String.valueOf(c);
                moves.add(new Movement(letters[x]+s,cpy));
            }
            
        }
        return moves;
    }

    // Clone the FiveFieldKono so that the clone is a FiveFieldKono instance
    @Override
    public Game cloneGame(){
        GridGame clone = new ConnectFour();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }

    // Overriden toString method to draw the board in another way
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