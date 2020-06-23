package pegsolitaire;

import java.util.List;
import java.util.LinkedList;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class fixita extends Piece {

    public fixita(int player,int x,int y) {
        super(player, x, y);// Call parent constructor
    }
    //La Ficha se mueve si tiene una ficha adelante, atras, izquierda o derecha y la salta.
    @Override
    public List<Movement> getMovements(Game state) {
        GridGame grid = (GridGame) state;
        //Lista de Movimiento
        List<Movement> moves = new LinkedList<Movement>();
        int[] dxys= {-2,2};//Alternativas que tiene la ficha para x e y
            for (int dxy: dxys){//caso en que se mueva en x
                int xx = x + dxy;
                int yy = y + dxy;
                //Se asegura si esta dentro de los bordes del tablero y si lo adyacente es pieza.
                //caso en que se mueva en x
                if(grid.isInside(xx, y) && grid.pieceAt(xx, y)==null && grid.pieceAt(x+(dxy/2),y)!=null && ((((1<xx)&&(xx<5))||((1<y)&&(y<5))))){
                    //Clona el estado actual para ser usado en el movimiento
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x, y, xx, y);
                    //se crea una Lista enlazada temporal, la cual almacena la ficha usada.
                    LinkedList<Piece> fixita2= new LinkedList<Piece>();
                    for (Piece pieza : cpy.pieces){
                        if(!((pieza.x==x+(dxy/2))&& (pieza.y == y))){
                            fixita2.add(pieza);
                        }
                    }

                    cpy.pieces = fixita2;
                    //agrega el nuevo movimiento a la lista de movimientos.
                    moves.add(new Movement(Movement.moveCommand(x, y, xx, y),cpy));
                }
                //caso en que se mueva en y
                else if(grid.isInside(x, yy) && grid.pieceAt(x, yy)==null && grid.pieceAt(x,y+(dxy/2))!=null && ((((1<x)&&(x<5))||((1<yy)&&(yy<5))))){
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x, y, x, yy);
                    LinkedList<Piece> fixita2= new LinkedList<Piece>();
                    for (Piece pieza : cpy.pieces){
                        if(!((pieza.x==x) && (pieza.y == y+(dxy/2)))){
                            fixita2.add(pieza);
                        }
                    }
                    cpy.pieces = fixita2;
                    moves.add(new Movement(Movement.moveCommand(x, y, x, yy),cpy));
                }
            }
        return moves;
    }
    

    @Override
    public String asciiRepresentation() {
        return "F ";
    }
    //Clona la ficha asi el clon es una instancia de ficha
    @Override
    public Piece clonePiece() {
        fixita other = new fixita(player,x,y);
        return other;
    }
}