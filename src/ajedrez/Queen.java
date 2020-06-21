package ajedrez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Queen extends Piece{

    public Queen(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] d = {1,2,3,4,5,6,7};

        //right move
        for(int i : d){
            int xx = x + i;
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

        //left move
        for(int i : d){
            int xx = x - i;
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

        //up move
        for(int i : d){
            int yy = y - i;
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

        //down move
        for(int i : d){
            int yy = y + i;
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
        if(player==0) return "♕ ";
        if(player==1) return "♛ ";
        return "? ";
    }

    // Clone the Queen so that the clone is a Queen instance
    @Override
    public Piece clonePiece(){
        Queen other = new Queen(player,x,y);
        return other;
    }
}