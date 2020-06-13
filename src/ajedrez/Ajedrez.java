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


public class Ajedrez extends GridGame  {

    public Ajedrez(){
        // Size of the board
        size_x = 8;
        size_y = 8;
        pieces = new LinkedList<Piece>(); // Linked list with pieces
        
        /* Pawn Test ------------
        pieces.add(new Pawn(0,0,0));
        pieces.add(new Pawn(0,7,0));
        pieces.add(new Pawn(0,0,6));
        pieces.add(new Pawn(0,7,5));
        pieces.add(new Pawn(0,4,4));
        pieces.add(new Knight(1,3,3));
        pieces.add(new Knight(0,5,3));
        --------------------------*/
        /* Rook Test ------------
        pieces.add(new Rook(0,0,0));
        pieces.add(new Rook(0,7,0));
        pieces.add(new Rook(0,0,7));
        pieces.add(new Rook(0,7,7));
        pieces.add(new Rook(0,4,4));
        pieces.add(new Knight(1,4,2));
        pieces.add(new Knight(1,6,4));
        pieces.add(new Knight(0,3,4));
        pieces.add(new Knight(1,2,4));
        pieces.add(new Knight(0,4,6));
        pieces.add(new Knight(0,5,7));
        --------------------------*/
        /* Bishop Test ------------
        pieces.add(new Bishop(0,0,0));
        pieces.add(new Bishop(0,7,0));
        pieces.add(new Bishop(0,0,7));
        pieces.add(new Bishop(0,7,7));
        pieces.add(new Pawn(1,4,4));
        pieces.add(new Pawn(0,3,4));
        pieces.add(new Pawn(1,4,3));
        pieces.add(new Pawn(0,3,3));
        --------------------------*/
        /* Knight Test ------------
        pieces.add(new Knight(0,0,0));
        pieces.add(new Knight(0,7,0));
        pieces.add(new Knight(0,0,7));
        pieces.add(new Knight(0,7,7));
        pieces.add(new Knight(0,4,4));
        pieces.add(new Pawn(0,6,3));
        pieces.add(new Pawn(1,6,5));
        pieces.add(new Pawn(0,5,2));
        pieces.add(new Pawn(1,3,2));
        pieces.add(new Pawn(0,2,3));
        pieces.add(new Pawn(1,2,5));
        pieces.add(new Pawn(0,3,6));
        pieces.add(new Pawn(1,5,6));
        --------------------------*/
        /* King Test ------------
        pieces.add(new King(0,0,0));
        pieces.add(new King(0,7,0));
        pieces.add(new King(0,0,7));
        pieces.add(new King(0,7,7));
        pieces.add(new King(0,3,3));
        pieces.add(new Pawn(0,3,4));
        pieces.add(new Pawn(1,3,2));
        pieces.add(new Pawn(0,4,3));
        pieces.add(new Pawn(1,2,3));
        pieces.add(new Pawn(0,2,2));
        pieces.add(new Pawn(1,4,4));
        pieces.add(new Pawn(0,4,2));
        pieces.add(new Pawn(1,2,4));
        --------------------------*/
        /* Queen Test ------------
        pieces.add(new Queen(0,0,0));
        pieces.add(new Queen(0,7,0));
        pieces.add(new Queen(0,0,7));
        pieces.add(new Queen(0,7,7));
        pieces.add(new Pawn(1,4,4));
        pieces.add(new Pawn(0,3,4));
        pieces.add(new Pawn(1,4,3));
        pieces.add(new Pawn(0,3,3));
        pieces.add(new Pawn(1,4,0));
        pieces.add(new Pawn(0,0,4));
        pieces.add(new Pawn(1,4,7));
        pieces.add(new Pawn(0,7,4));
        --------------------------*/
        
    
        for(int i = 0 ; i < 8 ; i++){
            pieces.add(new Pawn(0,i,6)); // Creates Pawns for player 0
            pieces.add(new Pawn(1,i,1)); // Creates Pawns for player 1
        }


        // Creates Rooks Knights, Bishops, Queen and King for player 0
        pieces.add(new Rook(0,0,7));
        pieces.add(new Rook(0,7,7));

        pieces.add(new Knight(0,1,7));
        pieces.add(new Knight(0,6,7));

        pieces.add(new Bishop(0,2,7));
        pieces.add(new Bishop(0,5,7)); 

        pieces.add(new Queen(0,3,7));
        pieces.add(new King(0,4,7));

    
        // Creates Rooks Knights, Bishops, Queen and King for player 0
        pieces.add(new Rook(1,0,0));
        pieces.add(new Rook(1,7,0));

        pieces.add(new Knight(1,1,0));
        pieces.add(new Knight(1,6,0)); 

        pieces.add(new Bishop(1,2,0));
        pieces.add(new Bishop(1,5,0)); 

        pieces.add(new Queen(1,3,0));
        pieces.add(new King(1,4,0)); 
        
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

        // Added a part to show captured pieces.
        sbu.append("\n");
        sbu.append("Piezas Capturadas Player 0: ");
        for(Piece pc : pieces){
            if(pc.player == 2){
                sbu.append(pc.asciiRepresentation());
            }
        }
        sbu.append("\n");
        sbu.append("Piezas Capturadas Player 1: ");
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

}