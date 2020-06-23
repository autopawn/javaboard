package pegsolitaire;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;
import java.util.Collection;
import java.util.Iterator;

public class PegPiece extends Piece {

    public PegPiece(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    
    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        //Las piezas se pueden mover horizontal o verticalmente con espaciado de 2
        int[] ds = {-2,2};
        //Movimiento horizontal
        for(int d : ds){
          int dx = x + d;
          //Si las piezas estan dentro de los límites establecidos
          if(grid.isInside(dx,y) && grid.pieceAt(dx,y)==null && grid.pieceAt(x+(d/2),y)!=null && (((1<dx)&&(dx<5))||((1<y)&&(y<5)))){
            Game cpy = grid.cloneGame();
            cpy.movePiece(x,y,dx,y);
            LinkedList<Piece> temp = new LinkedList<Piece>();
            //for que sirve para eliminar la pieza "media" por la cual pasa nuestra pieza que se mueve
            for (Piece elem : cpy.pieces){
              if(!(elem.x==x+d/2 && elem.y==y)){
                temp.add(elem);
              }
            }
            cpy.pieces = temp;
            moves.add(new Movement(Movement.moveCommand(x,y,dx,y),cpy));
          }
        }
        //Movimiento Vertical
        for(int d : ds){
          int dy = y + d;
          //Si las piezas estan dentro de los límites establecidos
          if(grid.isInside(x,dy) && grid.pieceAt(x,dy)==null && grid.pieceAt(x,y+(d/2))!=null && (((1<x)&&(x<5))||((1<dy)&&(dy<5)))){
            Game cpy = grid.cloneGame();
            cpy.movePiece(x,y,x,dy);
            LinkedList<Piece> temp = new LinkedList<Piece>();
            //for que sirve para eliminar la pieza "media" por la cual pasa nuestra pieza que se mueve
            for (Piece elem : cpy.pieces){
              if(!(elem.x==x && elem.y==y+d/2)){
                temp.add(elem);
              }
            }
            cpy.pieces = temp;
            moves.add(new Movement(Movement.moveCommand(x,y,x,dy),cpy));
          }
        }
        return moves;
    }
    //representación de cada pieza en ascii
    @Override
    public String asciiRepresentation(){
        return "■ ";
    }

    // Clone the PegPiece so that the clone is a PegPiece instance
    @Override
    public Piece clonePiece(){
        PegPiece other = new PegPiece(player,x,y);
        return other;
    }
}
