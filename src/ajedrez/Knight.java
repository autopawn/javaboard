package ajedrez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Knight extends Piece{

    public Knight(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] d = {1,2};

        //L moves (1,2,3 and 4)
        for(int i : d){
            int aux = 3 - i; //if i is 1, aux is 2. if i is 2, aux is 1.
            int xx = x + i;
            int y1 = y - aux;
            int y2 = y + aux;

            if(grid.isInside(xx,y1)){ //piece on the board
                if(grid.pieceAt(xx,y1)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,y1);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y1),cpy));
                }
                else if(grid.pieceAt(xx,y1).player==grid.current_player); //there is player's piece
                else if(grid.pieceAt(xx,y1) instanceof King);
                else if(grid.pieceAt(xx,y1).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == y1){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,y1);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y1),cpy));
                }
            }

            if(grid.isInside(xx,y2)){ //piece on the board
                if(grid.pieceAt(xx,y2)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,y2);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y2),cpy));
                }
                else if(grid.pieceAt(xx,y2).player==grid.current_player); //there is player's piece
                else if(grid.pieceAt(xx,y2) instanceof King);
                else if(grid.pieceAt(xx,y2).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == y2){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,y2);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y2),cpy));
                }
            }
        }

        //L moves (5,6,7 and 8)
        for(int i : d){
            int aux = 3 - i; //if i is 1, aux is 2. if i is 2, aux is 1.
            int xx = x - i;
            int y1 = y - aux;
            int y2 = y + aux;

            if(grid.isInside(xx,y1)){ //piece on the board
                if(grid.pieceAt(xx,y1)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,y1);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y1),cpy));
                }
                else if(grid.pieceAt(xx,y1).player==grid.current_player); //there is player's piece
                else if(grid.pieceAt(xx,y1) instanceof King);
                else if(grid.pieceAt(xx,y1).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == y1){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,y1);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y1),cpy));
                }
            }

            if(grid.isInside(xx,y2)){ //piece on the board
                if(grid.pieceAt(xx,y2)==null){ // there is not Piece
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,xx,y2);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y2),cpy));
                }
                else if(grid.pieceAt(xx,y2).player==grid.current_player); //there is player's piece
                else if(grid.pieceAt(xx,y2) instanceof King);
                else if(grid.pieceAt(xx,y2).player!=grid.current_player){ //there is opponent's piece
                    Game cpy = grid.cloneGame();

                    //Iterator for piece capture
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == xx && iter.y == y2){
                            itr.remove();
                            break;
                        }
                    }

                    cpy.movePiece(x,y,xx,y2);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,xx,y2),cpy));
                }
            }
        }

        return moves;
    }

    @Override
    public String asciiRepresentation(){
        if(player==0) return "♘ ";
        if(player==1) return "♞ ";
        return "? ";
    }

    // Clone the Knight so that the clone is a Knight instance
    @Override
    public Piece clonePiece(){
        Knight other = new Knight(player,x,y);
        return other;
    }
}