package tictactoe;

import java.util.LinkedList;
import java.util.List;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;
import javaboard.Game;

public class TPiece extends Piece {

    public TPiece(int player, int x, int y) {
        super(player,x,y);
    }

    // Override the getMovement of Piece

    // Here we go through the entire board and check if we can place a piece there
    // If possible then add that position as just a coordinate
    // we don't care if what piece is being moved, just the place where it is going to be placed


    @Override
    public List<Movement> getMovements(Game state){

        GridGame grid = (GridGame) state;

        List<Movement> moves = new LinkedList<Movement>();
        for(int yy=0; yy < grid.size_y;yy++){
        for(int xx=0; xx < grid.size_x;xx++){
        if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){
            Game cpy = grid.cloneGame();
            cpy.movePiece(4 + cpy.current_player, 5, xx, yy);
            cpy.current_player = 1 - cpy.current_player;
            char xn  = (char)('a'+xx);
            moves.add(new Movement(""+xn+yy,cpy));
            }
        }
        }

        return moves;
    }

    // Override representations to mathc tictactoe characters

    @Override
    public String asciiRepresentation(){
        if(player==0){
            return "O ";
        }
        if(player==1){
            return "X ";
        }

        return "??";
    }

    // Override clonePiece to get pieces

    @Override
    public Piece clonePiece(){
        TPiece other = new TPiece(player,x,y);
        return other;
    }
    
}