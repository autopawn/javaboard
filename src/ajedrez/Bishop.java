package ajedrez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Bishop extends Piece{

    public Bishop(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] d = {1,2,3,4,5,6,7};

        //northwest move
        for(int i : d){
            int xx = x - i;
            int yy = y - i;
            if(grid.isInside(xx,yy)){ //piece on the board
                if(grid.pieceAt(xx,yy)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                }
                else if(grid.pieceAt(xx,yy).player==grid.current_player) break; //there is player's piece
                else if(grid.pieceAt(xx,yy) instanceof King) break;
                else if(grid.pieceAt(xx,yy).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == yy){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                    break;
                }
            }
            else break;
        }

        //southeast move
        for(int i : d){
            int xx = x + i;
            int yy = y + i;
            if(grid.isInside(xx,yy)){ //piece on the board
                if(grid.pieceAt(xx,yy)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                }
                else if(grid.pieceAt(xx,yy).player==grid.current_player) break; //there is player's piece
                else if(grid.pieceAt(xx,yy) instanceof King) break;
                else if(grid.pieceAt(xx,yy).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == yy){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                    break;
                }
            }
            else break;
        }

        //northeast move
        for(int i : d){
            int xx = x + i;
            int yy = y - i;
            if(grid.isInside(xx,yy)){ //piece on the board
                if(grid.pieceAt(xx,yy)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                }
                else if(grid.pieceAt(xx,yy).player==grid.current_player) break; //there is player's piece
                else if(grid.pieceAt(xx,yy) instanceof King) break;
                else if(grid.pieceAt(xx,yy).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == yy){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                    break;
                }
            }
            else break;
        }

        //southwest move
        for(int i : d){
            int xx = x - i;
            int yy = y + i;
            if(grid.isInside(xx,yy)){ //piece on the board
                if(grid.pieceAt(xx,yy)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                }
                else if(grid.pieceAt(xx,yy).player==grid.current_player) break; //there is player's piece
                else if(grid.pieceAt(xx,yy) instanceof King) break;
                else if(grid.pieceAt(xx,yy).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == yy){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                    break;
                }
            }
            else break;
        }

        return moves;
    }

    @Override
    public String asciiRepresentation(){
        if(player==0) return "♗ ";
        if(player==1) return "♝ ";
        return "? ";
    }

    // Clone the Bishop so that the clone is a Bishop instance
    @Override
    public Piece clonePiece(){
        Bishop other = new Bishop(player,x,y);
        return other;
    }
}