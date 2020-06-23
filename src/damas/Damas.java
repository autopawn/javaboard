package damas;

import java.util.LinkedList;
import java.util.List;
import javaboard.Evaluable;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;


public class Damas extends GridGame  {

    public Damas(){
        size_x = 8;
        size_y = 8;
        pieces = new LinkedList<Piece>();
        
        // PLAYER 0
        // CPU 1
        
        //ORIGINAL BOARD
        pieces.add(new NormalPiece(0,0,5));
        pieces.add(new NormalPiece(0,2,5));
        pieces.add(new NormalPiece(0,4,5));
        pieces.add(new NormalPiece(0,6,5));
        pieces.add(new NormalPiece(0,1,6));
        pieces.add(new NormalPiece(0,3,6));
        pieces.add(new NormalPiece(0,5,6));
        pieces.add(new NormalPiece(0,7,6));
        pieces.add(new NormalPiece(0,0,7));
        pieces.add(new NormalPiece(0,2,7));
        pieces.add(new NormalPiece(0,4,7));
        pieces.add(new NormalPiece(0,6,7));
        pieces.add(new NormalPiece(1,1,0));
        pieces.add(new NormalPiece(1,3,0));
        pieces.add(new NormalPiece(1,5,0));
        pieces.add(new NormalPiece(1,7,0));
        pieces.add(new NormalPiece(1,0,1));
        pieces.add(new NormalPiece(1,2,1));
        pieces.add(new NormalPiece(1,4,1));
        pieces.add(new NormalPiece(1,6,1));
        pieces.add(new NormalPiece(1,1,2));
        pieces.add(new NormalPiece(1,3,2));
        pieces.add(new NormalPiece(1,5,2));
        pieces.add(new NormalPiece(1,7,2));
    
    }      
       

    // Override toString() for showing captured pieces, after the board.
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
                    // Draw cell
                    if((x+y)%2==0) sbu.append("■ ");
                    else sbu.append("□ ");
                }
            }
            sbu.append("\n");
            
        }

        sbu.append("\n");

        //Add counter of captured cpu pieces   
        sbu.append("Player 0 (YOU) captures : ");
        for(Piece pc : pieces){
            if(pc.player == 2){
                sbu.append(pc.asciiRepresentation());
            }
        }
        sbu.append("\n");
        
        //Add counter of captured player pieces
        sbu.append("Player 1 (CPU) captures : ");
        for(Piece pc : pieces){
            if(pc.player == 3){
                sbu.append(pc.asciiRepresentation());
            }
        }
        sbu.append("\n");

        return sbu.toString();
    }

    @Override
    public Game cloneGame(){
        GridGame clone = new Damas();
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
