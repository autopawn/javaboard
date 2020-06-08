package fivefieldkono;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class FiveFieldKono extends GridGame {

    public static int[][] base0 = {{0,3},{0,4},{1,4},{2,4},{3,4},{4,4},{4,3}};
    public static int[][] base1 = {{0,1},{0,0},{1,0},{2,0},{3,0},{4,0},{4,1}};

    public FiveFieldKono(){
        size_x = 5;
        size_y = 5;
        pieces = new LinkedList<Piece>();

        for(int[] ps : base0){
            pieces.add(new KonoPiece(0,ps[0],ps[1]));
        }
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

        Integer result = super.currentWinner(current_player_moves);
        return result;
    }

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

    @Override
    public String toString(){
        StringBuilder sbu = new StringBuilder();
        sbu.append("\n");
        sbu.append("   ");
        for(int x=0;x<size_x;x++){
            String xlabel = ""+((char)('a'+x));
            sbu.append(xlabel+" ");
        }
        sbu.append("\n");

        for(int y=0;y<size_y;y++){
            String ylabel = ""+y;
            if(ylabel.length()<2) sbu.append(" ");
            sbu.append(ylabel+" ");

            for(int x=0;x<size_x;x++){
                Piece pc_in_cell = pieceAt(x,y);
                if(pc_in_cell!=null){
                    // Draw piece
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else{
                    // Draw cell
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

}
