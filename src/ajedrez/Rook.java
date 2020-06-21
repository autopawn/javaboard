package ajedrez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Rook extends Piece{

    public Rook(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] dl = {-1,-2,-3,-4,-5,-6,-7}; // alternatives for delta y (or x) on up (or left)
        int[] dr = {1,2,3,4,5,6,7}; // alternatives for delta y (or x) on down (or right)

        //upward movements
        for(int dy : dl){
            int yy = y + dy;
            if(grid.isInside(x,yy)){ //piece on the board
                if(grid.pieceAt(x,yy)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,x,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                }
                else if(grid.pieceAt(x,yy).player==grid.current_player) break; //there is player's piece
                else if(grid.pieceAt(x,yy) instanceof King) break;
                else if(grid.pieceAt(x,yy).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == x && iter.y == yy){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,x,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                    break;
                }
            }
            else break;
        }

        //downward movements
        for(int dy : dr){
            int yy = y + dy;
            if(grid.isInside(x,yy)){ //piece on the board
                if(grid.pieceAt(x,yy)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,x,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                }
                else if(grid.pieceAt(x,yy).player==grid.current_player) break; //there is player's piece
                else if(grid.pieceAt(x,yy) instanceof King) break;
                else if(grid.pieceAt(x,yy).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == x && iter.y == yy){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,x,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                    break;
                }
            }
            else break;
        }

        //left movements
        for(int dx : dl){
            int xx = x + dx;
            if(grid.isInside(xx,y)){ //piece on the board
                if(grid.pieceAt(xx,y)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,y);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));
                }
                else if(grid.pieceAt(xx,y).player==grid.current_player) break; //there is player's piece
                else if(grid.pieceAt(xx,y) instanceof King) break;
                else if(grid.pieceAt(xx,y).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == y){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,y);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));
                    break;
                }
            }
            else break;
        }

        //right movements
        for(int dx : dr){
            int xx = x + dx;
            if(grid.isInside(xx,y)){ //piece on the board
                if(grid.pieceAt(xx,y)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,y);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));
                }
                else if(grid.pieceAt(xx,y).player==grid.current_player) break; //there is player's piece
                else if(grid.pieceAt(xx,y) instanceof King) break;
                else if(grid.pieceAt(xx,y).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == y){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,y);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));
                    break;
                }
            }
            else break;
        }

        return moves;
    }

    @Override
    public String asciiRepresentation(){
        if(player==0) return "♖ ";
        if(player==1) return "♜ ";
        return "? ";
    }

    // Clone the Rook so that the clone is a Rook instance
    @Override
    public Piece clonePiece(){
        Rook other = new Rook(player,x,y);
        return other;
    }
}