package pegsolitaire;

import java.util.LinkedList;
import java.util.List;

import javaboard.*;

public class PegSolitaire extends GridGame{
    // Constructor of the game
    public PegSolitaire(){
        size_x = 7;
        size_y = 7;
        pieces = new LinkedList<Piece>();
        // Add Peg pieces in the board by sections
        for(int x = 2; x<=4; x++){
            for(int y = 0; y<=1; y++){
                pieces.add(new Peg(0,x,y));
            }
        }
        for(int x = 0; x<=6; x++){
            for(int y = 2; y<=4; y++){
                if(!(x==3 && y==3)){
                    pieces.add(new Peg(0,x,y));
                }
            }
        }
        for(int x = 2; x<=4; x++){
            for(int y = 5; y<=6; y++){
                pieces.add(new Peg(0,x,y));
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder build = new StringBuilder();

        // First row with letters
        build.append("\n");
        build.append("   ");
        for(int x=0;x<size_x;x++){
            String xlabel = ""+((char)('a'+x));
            build.append(xlabel+" ");
        }
        build.append("\n");

        // Other parts of the board
        for(int y=0;y<size_y;y++){
            String ylabel = ""+y;
            build.append(ylabel+"  ");
            /* We put the piece representation in the place where it has to be (in the board)
               When a piece eats another, in the place of the eaten piece we put a dot "."  */
            for(int x = 0; x<=1; x++){
                Piece pc_in_cell = pieceAt(x, y);
                if(pc_in_cell!=null){
                    String rep = pc_in_cell.asciiRepresentation();
                    build.append(rep);
                }
                else if(y>=2 && y<=4){
                    build.append(". ");
                }
                else{
                    build.append("  ");
                }
            }
            for(int x = 2; x<=4; x++){
                Piece pc_in_cell = pieceAt(x, y);
                if(pc_in_cell!=null){
                    String rep = pc_in_cell.asciiRepresentation();
                    build.append(rep);
                }
                else{
                    build.append(". ");
                }
            }
            for(int x = 5; x<=6; x++){
                Piece pc_in_cell = pieceAt(x, y);
                if(pc_in_cell!=null){
                    String rep = pc_in_cell.asciiRepresentation();
                    build.append(rep);
                }
                else if(y>=2 && y<=4){
                    build.append(". ");
                }
                else{
                    build.append("  ");
                }
            }

            build.append("\n");
        }
        return build.toString();
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
    // Override the isInside method because the board isn't a square
    @Override
    public boolean isInside(int x, int y){
        return((x>=2 && x<=4 && y>=0 && y<=1) || (x>=0 && x<=6 && y>=2 && y<=4) || (x>=2 && x<=4 && y>=5 && y<=6));
    }
}
