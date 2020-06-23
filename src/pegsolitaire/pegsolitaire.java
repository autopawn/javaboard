package pegsolitaire;

import java.util.LinkedList;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Piece;

public class pegsolitaire extends GridGame {

    public pegsolitaire(){
        size_x=7;
        size_y=7;
        pieces= new LinkedList<Piece>();
        pieces.add(new fixita(0,2,0));
        pieces.add(new fixita(0,3,0));
        pieces.add(new fixita(0,4,0));
        pieces.add(new fixita(0,2,1));
        pieces.add(new fixita(0,3,1));
        pieces.add(new fixita(0,4,1));
        pieces.add(new fixita(0,0,2));
        pieces.add(new fixita(0,1,2));
        pieces.add(new fixita(0,2,2));
        pieces.add(new fixita(0,3,2));
        pieces.add(new fixita(0,4,2));
        pieces.add(new fixita(0,5,2));
        pieces.add(new fixita(0,6,2));
        pieces.add(new fixita(0,0,3));
        pieces.add(new fixita(0,1,3));
        pieces.add(new fixita(0,2,3));
        pieces.add(new fixita(0,4,3));
        pieces.add(new fixita(0,5,3));
        pieces.add(new fixita(0,6,3));
        pieces.add(new fixita(0,0,4));
        pieces.add(new fixita(0,1,4));
        pieces.add(new fixita(0,2,4));
        pieces.add(new fixita(0,3,4));
        pieces.add(new fixita(0,4,4));
        pieces.add(new fixita(0,5,4));
        pieces.add(new fixita(0,6,4));
        pieces.add(new fixita(0,2,5));
        pieces.add(new fixita(0,3,5));
        pieces.add(new fixita(0,4,5));
        pieces.add(new fixita(0,2,6));
        pieces.add(new fixita(0,3,6));
        pieces.add(new fixita(0,4,6));
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
            
            for(int x=0;x<size_x;x++){
                Piece pc_in_cell = pieceAt(x,y);
                if (pc_in_cell==null){
                    if (x==0 & y==0){
                        sbu.append("  ");
                    } 
                    else if(x==1 & y==0){
                        sbu.append("  ");
                    }
                    else if(x==0 & y==1){
                        sbu.append("  ");
                    }
                    else if(x==1 & y==1){
                        sbu.append("  ");
                    }
                    else if(x==5 & y==0){
                        sbu.append("  ");
                    }
                    else if(x==6 & y==0){
                        sbu.append("  ");
                    }
                    else if(x==5 & y==1){
                        sbu.append("  ");
                    }
                    else if(x==6 & y==1){
                        sbu.append("  ");
                    }
                    else if(x==0 & y==5){
                        sbu.append("  ");
                    }
                    else if(x==0 & y==6){
                        sbu.append("  ");
                    }
                    else if(x==1 & y==5){
                        sbu.append("  ");
                    }
                    else if(x==1 & y==6){
                        sbu.append("  ");
                    }
                    else if(x==5 & y==5){
                        sbu.append("  ");
                    }
                    else if(x==6 & y==5){
                        sbu.append("  ");
                    }
                    else if (x==5 & y==5){
                        sbu.append("  ");
                    }
                    else if(x==6 & y==6){
                        sbu.append("  ");
                    }
                    else if(x==5 & y==6){
                        sbu.append("  ");
                    }
                    else{
                        sbu.append("□ ");
                    }
                }
                else{
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }
            }
            sbu.append("\n");
        }
        return sbu.toString();
    }

    // Clone el pegsolitaire asi el clon es una instancia del juego.
    @Override
    public Game cloneGame(){
        GridGame clone = new pegsolitaire();
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