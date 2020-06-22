package damas;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class QueenWhite extends Piece{
    public QueenWhite(int player, int x, int y) {
        super(player, x, y);
    }
    @Override
    public List<Movement> getMovements(Game state) {
        GridGame grid = (GridGame) state;
        List<Movement> moves = new LinkedList<Movement>();


        int[] dss = {1, 2, 3, 4, 5, 6 ,7};
        //caso1: Superior Derecho, se seguirá la misma logica que las fichas(Black y White)
        //caso1: Superior Derecho
        for(int ds:dss){
            int xx=x+ds;
            int yy=y-ds;
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx+1,yy-1) == null && grid.isInside(xx,yy) && grid.isInside(xx+1,yy-1) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.movePiece(xx,yy,8,7);
                cpy.movePiece(x,y,xx+1,yy-1);
                cpy.current_player = 1 - cpy.current_player;
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                break;
            }
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }
        //caso1: Superior Izquierdo
        for(int ds:dss){
            int xx=x-ds;
            int yy=y-ds;
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx-1,yy-1) == null && grid.isInside(xx,yy) && grid.isInside(xx-1,yy-1) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.movePiece(xx,yy,8,7);
                cpy.movePiece(x,y,xx-1,yy-1);
                cpy.current_player = 1 - cpy.current_player;
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                break;
            }
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }
        //caso3: Inferior Derecho
        for(int ds:dss){
            int xx=x+ds;
            int yy=y+ds;
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx+1,yy+1) == null && grid.isInside(xx,yy) && grid.isInside(xx+1,yy+1) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.movePiece(xx,yy,8,7);
                cpy.movePiece(x,y,xx+1,yy+1);
                cpy.current_player = 1 - cpy.current_player;
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                break;
            }
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }
        //caso3: Inferior Izquierdo
        for(int ds:dss){
            int xx=x-ds;
            int yy=y+ds;
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx-1,yy+1) == null && grid.isInside(xx,yy) && grid.isInside(xx-1,yy+1) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.movePiece(xx,yy,8,7);
                cpy.movePiece(x,y,xx-1,yy+1);
                cpy.current_player = 1 - cpy.current_player;
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                break;
            }
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }
        return moves;
    }

    @Override
    public String asciiRepresentation(){
        return "⛃ ";}
    
    @Override
    public Piece clonePiece(){
        QueenWhite other = new QueenWhite(player,x,y);
        return other;
    }

    
}