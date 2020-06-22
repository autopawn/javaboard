package damas;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;


public class White extends Piece{
    public White(int player, int x, int y) {
        super(player, x, y);}
    @Override
    public List<Movement> getMovements(Game state) {
        GridGame grid = (GridGame) state;
        List<Movement> moves = new LinkedList<Movement>();
        int[] dxs = {-1,1};
        for (int dx : dxs) {
            int xx = x + dx;
            int yy = y + 1;

            if (grid.pieceAt(xx, yy) != null && grid.isInside(xx, yy) && grid.pieceAt(xx, yy).player != grid.current_player) {
                if ((xx - x) > 0 && (yy - y) > 0 && grid.pieceAt(xx+1, yy+1) == null && grid.isInside(xx+1, yy+1)) {
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(xx, yy, 8, 0);
                    if(y==5){
                        cpy.pieces.add(new QueenWhite(1, xx+1, yy+1));
                        cpy.movePiece(x, y, 8, 7);
                    }
                    else{
                        cpy.movePiece(x, y, xx + 1, yy + 1);
                    }
                    if(grid.pieceAt(xx, yy+2) != null && grid.isInside(xx, yy+2) && grid.pieceAt(xx-1, yy+3) == null && grid.isInside(xx-1, yy+3) && grid.pieceAt(xx, yy+2).player != grid.current_player){
                        cpy.current_player = cpy.current_player;
                    }
                    else if(grid.pieceAt(xx+2, yy+2) != null && grid.isInside(xx+2, yy+2) && grid.pieceAt(xx+3,yy+3) == null && grid.isInside(xx+3,yy+3) && grid.pieceAt(xx+2, yy+2).player != grid.current_player){
                        cpy.current_player = cpy.current_player;
                    }
                    else{
                        cpy.current_player = 1 - cpy.current_player;
                    }
                    moves.add(new Movement(Movement.moveCommand(x, y, xx, yy), cpy));
                } 
                else if ((xx - x) < 0 && (yy - y) > 0 && grid.pieceAt(xx-1, yy+1) == null && grid.isInside(xx-1, yy+1)) {
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(xx, yy, 8, 0);
                    if(y==5){
                        cpy.pieces.add(new QueenWhite(1, xx-1, yy+1));
                        cpy.movePiece(x, y, 8, 7);
                    }
                    else{
                        cpy.movePiece(x, y, xx - 1, yy + 1);
                    }
                    if(grid.pieceAt(xx-2, yy+2) != null && grid.isInside(xx-2, yy+2) && grid.pieceAt(xx-3, yy+3) == null && grid.isInside(xx-3, yy+3) && grid.pieceAt(xx-2, yy+2).player != grid.current_player){
                        cpy.current_player = cpy.current_player;
                    }
                    else if(grid.pieceAt(xx, yy+2) != null && grid.isInside(xx, yy+2) && grid.pieceAt(xx+1,yy+3) == null && grid.isInside(xx+1,yy+3) && grid.pieceAt(xx+1, yy+3).player != grid.current_player){
                        cpy.current_player = cpy.current_player;
                    }
                    else{
                        cpy.current_player = 1 - cpy.current_player;
                    }
                    moves.add(new Movement(Movement.moveCommand(x, y, xx, yy), cpy));
                }    
            }
            else{
                if( y==6 && grid.pieceAt(xx, yy) == null && grid.isInside(xx,yy)){
                    if((xx-x)<0 && (yy-y)<0){ 
                        Game cpy = grid.cloneGame(); 
                        cpy.movePiece(x,y,8,7); 
                        cpy.pieces.add(new QueenWhite(1, xx, yy));
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy)); 
                    } 
                    else if((xx-x)>0 && (yy-y)<0){ 
                        Game cpy = grid.cloneGame(); 
                        cpy.movePiece(x,y,8,7); 
                        cpy.pieces.add(new QueenWhite(1, xx, yy));
                        cpy.current_player = 1 - cpy.current_player;
                        moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy)); 
                    } 
                }                     
                else if (grid.isInside(xx, yy) && grid.pieceAt(xx, yy) == null) {
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(x, y, xx, yy);
                    cpy.current_player = 1 - cpy.current_player;
                    moves.add(new Movement(Movement.moveCommand(x, y, xx, yy), cpy));
                }
            }
        }
    return moves;
    }
    @Override
    public String asciiRepresentation(){
        return "⛂ ";}
    @Override
    public Piece clonePiece(){
        White other = new White(player,x,y);
        return other;}
}