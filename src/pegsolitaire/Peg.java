package pegsolitaire;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Peg extends Piece{
    
    public Peg(int player, int x, int y){
        super(player,x,y);
    }
    @Override
    public String asciiRepresentation(){
        return "𐤈 ";
    }
    
    @Override
    public List<Movement> getMovements(Game state){
        GridGame grid = (GridGame) state;
        
        List<Movement> moves = new LinkedList<Movement>();

        // A peg can move horizontally and vertically by 2 
        int[] dxs = {-2,2};
        int[] dys= {-2,2};
                
        // We first iterate in the horizontal movement possibilities of the peg
        for (int dx : dxs){
            int xx = x + dx;
            /* We have to check 3 conditions to make a movement:
               The cell where the peg moves is inside the board
               The cell where the peg moves is empty
               And the piece that is being eaten exists */
            if (grid.isInside(xx,y) && grid.pieceAt(xx,y)==null && grid.pieceAt(x+dx/2, y)!=null){
                // Clone the current state to use it in the movement
                Game copy = grid.cloneGame();
                copy.movePiece(x, y, xx, y);
                // We create a list with the remaining pieces so that the eaten Peg its removed from the available pieces
                List<Piece> remaining = new LinkedList<Piece>();
                
                for(Piece p : copy.pieces){
                    // We add every piece except the one that was eaten
                    if(!(p.x==(x +dx/2) && p.y == y)){
                        remaining.add(p);
                    }
                }
                // Assign to the copy pieces of the game the remaining ones
                copy.pieces = remaining;
                // Append new movement to the movement list
                moves.add(new Movement(Movement.moveCommand(x, y, xx, y),copy));
            } 
        }
        // Same as the horizontal case but in vertical movement
        for (int dy : dys){
            int yy = y + dy;

            if(grid.isInside(x,yy) && grid.pieceAt(x,yy)==null && grid.pieceAt(x,y+dy/2)!=null){
                Game copy = grid.cloneGame();
                copy.movePiece(x,y,x,yy);

                List<Piece> remaining = new LinkedList<Piece>();

                for(Piece p : copy.pieces){
                    if(!(p.x==x && p.y == (y +dy/2))){
                        remaining.add(p);
                    }
                }
                copy.pieces = remaining;

                moves.add(new Movement(Movement.moveCommand(x, y, x, yy),copy));
            }
        }

        return moves;
    }
    // Clone the Peg so that the clone is a Peg instance
    @Override
    public Piece clonePiece(){
        Peg other = new Peg(player,x,y);
        return other;
    }
}