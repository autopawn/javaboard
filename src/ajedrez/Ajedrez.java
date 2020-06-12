package ajedrez;

import java.util.LinkedList;
import java.util.List;
import java.util.*;
import javaboard.Evaluable;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;
import java.util.ArrayList;

/*
FoxAndHounds is a game where player 0, that controls a Peon, tries to reach
the other side of the board; while player 1, that controls 4 hounds, tries
to stop him.
The fox can move diagonally forward and backwards, the hounds can only move forward.
There is no eating.
*/
public class Ajedrez extends GridGame  {

    /*List<String> captured_0 = new ArrayList<String>(5);
    List<String> captured_1 = new ArrayList<String>(5);
    String captured_0.add("hola");*/

    public Ajedrez(){
        size_x = 8;
        size_y = 8;
        pieces = new LinkedList<Piece>();
        
        pieces.add(new Peon0(0,0,6));
        pieces.add(new Peon0(0,1,6));
        pieces.add(new Peon0(0,2,6));
        pieces.add(new Peon0(0,3,6));
        pieces.add(new Peon0(0,4,6));
        pieces.add(new Peon0(0,5,6));
        pieces.add(new Peon0(0,6,6));
        pieces.add(new Peon0(0,7,6));

        pieces.add(new Rook0(0,0,7));
        pieces.add(new Rook0(0,7,7));

        pieces.add(new Knight0(0,1,7));
        pieces.add(new Knight0(0,6,7));

        pieces.add(new Bishop0(0,2,7));
        pieces.add(new Bishop0(0,5,7)); 

        pieces.add(new Queen0(0,3,7));
        pieces.add(new King0(0,4,7));


        pieces.add(new Peon1(1,0,1));
        pieces.add(new Peon1(1,1,1));
        pieces.add(new Peon1(1,2,1));
        pieces.add(new Peon1(1,3,1));
        pieces.add(new Peon1(1,4,1));
        pieces.add(new Peon1(1,5,1));
        pieces.add(new Peon1(1,6,1));
        pieces.add(new Peon1(1,7,1));

        pieces.add(new Rook1(1,0,0));
        pieces.add(new Rook1(1,7,0));

        pieces.add(new Knight1(1,1,0));
        pieces.add(new Knight1(1,6,0)); 

        pieces.add(new Bishop1(1,2,0));
        pieces.add(new Bishop1(1,5,0)); 

        pieces.add(new Queen1(1,3,0));
        pieces.add(new King1(1,4,0)); 
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
                    // Draw cell
                    if((x+y)%2==0) sbu.append("■ ");
                    else sbu.append("□ ");
                }
            }
            sbu.append("\n");
            
        }
        sbu.append("\n");
        sbu.append("Piezas Capturadas Player 1: ");
        for(Piece pc : pieces){
            if(pc.player == 2){
                sbu.append(pc.asciiRepresentation());
            }
        }
        sbu.append("\n");
        sbu.append("Piezas Capturadas Player 0: ");
        for(Piece pc : pieces){
            if(pc.player == 3){
                sbu.append(pc.asciiRepresentation());
            }
        }
        sbu.append("\n");
        return sbu.toString();
    }

   
    
    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        // If the fox reaches the top side of the board, player 0 wins
        for(Piece pc : pieces){
            if(pc.player==0 && pc.y==0) return 0;
        }
        // Test default win condition
        Integer result = super.currentWinner(current_player_moves);
        return result;
    }

    // Clone the FoxAndHounds so that the clone is a FoxAndHounds instance
    @Override
    public Game cloneGame(){
        GridGame clone = new Ajedrez();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }
    
    
    /*@Override
    public void movePiece(int x, int y, int xx, int yy){
        Piece pc = pieceAt(x,y);
        Piece target = pieceAt(xx,yy);

        if(pc==null){ 
            return;
        }else{
        pc.x = xx;
        pc.y = yy;
        if(target != null){
            if(target.player != current_player){
                target.x=8;
                target.y=7;
                captured_0.add(target.asciiRepresentation());
            }
        }
        //captured_0.add(target.asciiRepresentation());
        }
    }*/

    // Default evaluation function, so that PlayerCPUEval works for FoxAndHounds


}