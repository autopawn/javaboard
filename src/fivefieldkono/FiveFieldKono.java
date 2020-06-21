package fivefieldkono;

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

public class FiveFieldKono extends GridGame {

    // Starting positions of player 0
    public static int[][] base0 = {{0,3},{0,4},{1,4},{2,4},{3,4},{4,4},{4,3}};
    // Starting positions of player 1
    public static int[][] base1 = {{0,1},{0,0},{1,0},{2,0},{3,0},{4,0},{4,1}};

    public FiveFieldKono(){
        size_x = 5;
        size_y = 5;
        pieces = new LinkedList<Piece>();

        // Create a player 0 piece on player 0 starting positions
        for(int[] ps : base0){
            pieces.add(new KonoPiece(0,ps[0],ps[1]));
        }
        // Create a player 1 piece on player 1 starting positions
        for(int[] ps : base1){
            pieces.add(new KonoPiece(1,ps[0],ps[1]));
        }

    }
    

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        { // Count base0 positions covered by player 1
            int covered0 = 0;
            for(int[] ps : base0){
                for(Piece pc : pieces){
                    if(pc.player==1 && pc.x==ps[0] && pc.y==ps[1]){
                        covered0 += 1;
                        break;
                    }
                }
            }
            if(covered0==7) return 1;
        }

        { // Count base1 positions covered by player 0
            int covered1 = 0;
            for(int[] ps : base1){
                for(Piece pc : pieces){
                    if(pc.player==0 && pc.x==ps[0] && pc.y==ps[1]){
                        covered1 += 1;
                        break;
                    }
                }
            }
            if(covered1==7) return 0;
        }

        // Check default winning condition
        Integer result = super.currentWinner(current_player_moves);
        return result;
    }

    // Clone the FiveFieldKono so that the clone is a FiveFieldKono instance
    @Override
    public Game cloneGame(){
        GridGame clone = new FiveFieldKono();
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
                    // Draw cell if its a starting point, otherwise draw blank
                    int symbol = -1;
                    for(int[] ps : base0){
                        if(x==ps[0] && y==ps[1]) symbol=0;
                    }
                    for(int[] ps : base1){
                        if(x==ps[0] && y==ps[1]) symbol=1;
                    }
                    if(symbol==0) sbu.append("◡ ");
                    else if(symbol==1) sbu.append("◠ ");
                    else sbu.append("  ");
                }
            }
            sbu.append("\n");
        }
        return sbu.toString();
    }

    @Override
    public String Game_name(){
         return "FiveFieldKono";
    }

}
