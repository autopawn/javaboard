package ajedrez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Pawn extends Piece{

    public Pawn(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        //white pieces
        if(grid.pieceAt(x,y).player==0){

            //two squares move
            if(y==6 && grid.pieceAt(x,y-1)==null && grid.pieceAt(x,y-2)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,x,y-2);
                cpy.current_player = 1 - cpy.current_player;
                moves.add(new Movement(Movement.moveCommand(x,y,x,y-2),cpy));
            }

            //diagonal left move
            if(grid.isInside(x-1,y-1)){
                if(grid.pieceAt(x-1,y-1)==null);
                else if(grid.pieceAt(x-1,y-1) instanceof King);
                else if(grid.pieceAt(x-1,y-1).player != grid.current_player){
                    if(y==1){ //pawn promotion to Queen
                        Game cpy = grid.cloneGame();
    
                        //Iterator for pieces remove
                        Iterator<Piece> itr = cpy.pieces.iterator();
                        while(itr.hasNext()){
                            Piece iter = itr.next();
                            if((iter.x == x && iter.y == y)||(iter.x == x-1 && iter.y == y-1)){
                                itr.remove();
                            }
                        }
    
                        //add queen
                        cpy.pieces.add(new Queen(cpy.current_player,x-1,y-1));
    
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,x-1,y-1),cpy));
                    }
                    else{
                        Game cpy = grid.cloneGame();

                        //Iterator for piece capture
                        Iterator<Piece> itr = cpy.pieces.iterator();
                        while(itr.hasNext()){
                            Piece iter = itr.next();
                            if(iter.x == x-1 && iter.y == y-1){
                                itr.remove();
                                break;
                            }
                        }

                        cpy.movePiece(x,y,x-1,y-1);
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,x-1,y-1),cpy));
                    }
                    
                }
            }

            //diagonal right move
            if(grid.isInside(x+1,y-1)){
                if(grid.pieceAt(x+1,y-1)==null);
                else if(grid.pieceAt(x+1,y-1) instanceof King);
                else if(grid.pieceAt(x+1,y-1).player != grid.current_player){
                    if(y==1){ //pawn promotion to Queen
                        Game cpy = grid.cloneGame();
    
                        //Iterator for pieces remove
                        Iterator<Piece> itr = cpy.pieces.iterator();
                        while(itr.hasNext()){
                            Piece iter = itr.next();
                            if((iter.x == x && iter.y == y)||(iter.x == x+1 && iter.y == y-1)){
                                itr.remove();
                            }
                        }
    
                        //add queen
                        cpy.pieces.add(new Queen(cpy.current_player,x+1,y-1));
    
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,x+1,y-1),cpy));
                    }
                    else{
                        Game cpy = grid.cloneGame();

                        //Iterator for piece capture
                        Iterator<Piece> itr = cpy.pieces.iterator();
                        while(itr.hasNext()){
                            Piece iter = itr.next();
                            if(iter.x == x+1 && iter.y == y-1){
                                itr.remove();
                                break;
                            }
                        }
    
                        cpy.movePiece(x,y,x+1,y-1);
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,x+1,y-1),cpy));
                    }
                }
            }

            //normal move
            if(y!=0 && grid.pieceAt(x,y-1)==null){
                if(y==1){ //pawn promotion to Queen
                    Game cpy = grid.cloneGame();

                    //Iterator for piece remove
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == x && iter.y == y){
                            itr.remove();
                            break;
                        }
                    }

                    //add queen
                    cpy.pieces.add(new Queen(cpy.current_player,x,y-1));

                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,x,y-1),cpy));
                }
                else{
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,x,y-1);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,x,y-1),cpy));
                }
            }
        }
        //black pieces
        else if(grid.pieceAt(x,y).player==1){

            //two squares move
            if(y==1 && grid.pieceAt(x,y+1)==null && grid.pieceAt(x,y+2)==null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,x,y+2);
                cpy.current_player = 1 - cpy.current_player;
                moves.add(new Movement(Movement.moveCommand(x,y,x,y+2),cpy));
            }

            //diagonal left move
            if(grid.isInside(x+1,y+1)){
                if(grid.pieceAt(x+1,y+1)==null);
                else if(grid.pieceAt(x+1,y+1) instanceof King);
                else if(grid.pieceAt(x+1,y+1).player != grid.current_player){
                    if(y==6){ //pawn promotion to Queen
                        Game cpy = grid.cloneGame();
    
                        //Iterator for pieces remove
                        Iterator<Piece> itr = cpy.pieces.iterator();
                        while(itr.hasNext()){
                            Piece iter = itr.next();
                            if((iter.x == x && iter.y == y)||(iter.x == x+1 && iter.y == y+1)){
                                itr.remove();
                            }
                        }
    
                        //add queen
                        cpy.pieces.add(new Queen(cpy.current_player,x+1,y+1));
    
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,x+1,y+1),cpy));
                    }
                    else{
                        Game cpy = grid.cloneGame();

                        //Iterator for piece capture
                        Iterator<Piece> itr = cpy.pieces.iterator();
                        while(itr.hasNext()){
                            Piece iter = itr.next();
                            if(iter.x == x+1 && iter.y == y+1){
                                itr.remove();
                                break;
                            }
                        }
    
                        cpy.movePiece(x,y,x+1,y+1);
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,x+1,y+1),cpy));
                    }
                }
            }

            //diagonal right move
            if(grid.isInside(x-1,y+1)){
                if(grid.pieceAt(x-1,y+1)==null);
                else if(grid.pieceAt(x-1,y+1) instanceof King);
                else if(grid.pieceAt(x-1,y+1).player != grid.current_player){
                    if(y==6){ //pawn promotion to Queen
                        Game cpy = grid.cloneGame();
    
                        //Iterator for pieces remove
                        Iterator<Piece> itr = cpy.pieces.iterator();
                        while(itr.hasNext()){
                            Piece iter = itr.next();
                            if((iter.x == x && iter.y == y)||(iter.x == x-1 && iter.y == y+1)){
                                itr.remove();
                            }
                        }
    
                        //add queen
                        cpy.pieces.add(new Queen(cpy.current_player,x-1,y+1));
    
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,x-1,y+1),cpy));
                    }
                    else{
                        Game cpy = grid.cloneGame();

                        //Iterator for piece capture
                        Iterator<Piece> itr = cpy.pieces.iterator();
                        while(itr.hasNext()){
                            Piece iter = itr.next();
                            if(iter.x == x-1 && iter.y == y+1){
                                itr.remove();
                                break;
                            }
                        }
    
                        cpy.movePiece(x,y,x-1,y+1);
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,x-1,y+1),cpy));
                    }
                }
            }

            //normal move
            if(y!=7 && grid.pieceAt(x,y+1)==null){
                if(y==6){ //pawn promotion to Queen
                    Game cpy = grid.cloneGame();

                    //Iterator for piece remove
                    Iterator<Piece> itr = cpy.pieces.iterator();
                    while(itr.hasNext()){
                        Piece iter = itr.next();
                        if(iter.x == x && iter.y == y){
                            itr.remove();
                            break;
                        }
                    }

                    //add queen
                    cpy.pieces.add(new Queen(cpy.current_player,x,y+1));

                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,x,y+1),cpy));
                }
                else{
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x,y,x,y+1);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x,y,x,y+1),cpy));
                }
            }
        }

        return moves;
    }

    @Override
    public String asciiRepresentation(){
        if(player==0) return "♙ ";
        if(player==1) return "♟ ";
        return "a ";
    }

    // Clone the Pawn so that the clone is a Pawn instance
    @Override
    public Piece clonePiece(){
        Pawn other = new Pawn(player,x,y);
        return other;
    }
}