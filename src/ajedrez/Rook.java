

package ajedrez;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Rook extends Piece {

    public Rook(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    /* Rook Rules:
    A rook moves any number of vacant squares horizontally or vertically. 
    The idea is to stop adding posible moves if the next square is not empty
    so i splitted the problem in 4 problems, because you have to check in up down left and right starting at center.
    For different reasons, the different options stops adding posible moves in different times*/ 
    
    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] dys = {1,2,3,4,5,6,7};
        
        // Case 1 Vertical Move_1

        for(int dy : dys){
            int yy = y - dy;

            // If the move has a piece, and is from the current player
            if(grid.pieceAt(x,yy) != null && grid.pieceAt(x,yy).player == grid.current_player){
                break;
            }

            // Capture Option
            if(grid.pieceAt(x,yy) != null && grid.pieceAt(x,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                
                // Capture
                cpy.pieceAt(x,yy).player = cpy.current_player + 2;      // new state change piece player, no one has control of it  
                cpy.movePiece(x,yy,8,7);                                // new state moves the piece to captured zone
                
                //Move Piece
                cpy.movePiece(x,y,x,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                break;
            }

            // Movement for empty Square
            if(grid.isInside(x,yy) && grid.pieceAt(x,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,x,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));              
            }   
        }


        // Case 2 Vertical Move_2

        for(int dy : dys){
            int yy = y + dy;

            // If the move has a piece, and is from the current player
            if(grid.pieceAt(x,yy) != null && grid.pieceAt(x,yy).player == grid.current_player){
                break;
            }

            // Capture Option
            if(grid.pieceAt(x,yy) != null && grid.isInside(x,yy) && grid.pieceAt(x,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                
                // Capture
                cpy.pieceAt(x,yy).player = cpy.current_player + 2;      // new state change piece player, no one has control of it  
                cpy.movePiece(x,yy,8,7);                                // new state moves the piece to captured zone
                
                //Move Piece
                cpy.movePiece(x,y,x,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
                break;
            }

            // Movement for empty square
            if(grid.isInside(x,yy) && grid.pieceAt(x,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,x,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));              
            }   
        }

        // Case 3 Horizontal Move_1

        for(int dy : dys){
            int xx = x - dy;

            // If the move has a piece, and is from the current player
            if(grid.pieceAt(xx,y) != null && grid.pieceAt(xx,y).player == grid.current_player){
                break;
            }
            if(grid.pieceAt(xx,y) != null && grid.isInside(xx,y) && grid.pieceAt(xx,y).player != grid.current_player){
                Game cpy = grid.cloneGame();
                cpy.pieceAt(xx,y).player = cpy.current_player + 2;      // new state change piece player, no one has control of it
                cpy.movePiece(xx,y,8,7);                                // new state moves the piece to captured zone 
                cpy.movePiece(x,y,xx,y);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));
                break;
            }
            if(grid.isInside(xx,y) && grid.pieceAt(xx,y) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,y);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));              
            }   
        }

        // Case 4 Horizontal Move_2

        for(int dy : dys){
            int xx = x + dy;

            // If the move has a piece, and is from the current player
            if(grid.pieceAt(xx,y) != null && grid.pieceAt(xx,y).player == grid.current_player){
                break;
            }

            // Capture Option
            if(grid.pieceAt(xx,y) != null && grid.isInside(xx,y) && grid.pieceAt(xx,y).player != grid.current_player){
                Game cpy = grid.cloneGame();
                
                // Capture
                cpy.pieceAt(xx,y).player = cpy.current_player + 2;      // new state change piece player, no one has control of it 
                cpy.movePiece(xx,y,8,7);                                // new state moves the piece to captured zone   
                
                // Move Piece
                cpy.movePiece(x,y,xx,y);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));
                break;
            }

            // Movement for empty square
            if(grid.isInside(xx,y) && grid.pieceAt(xx,y) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,y);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,y),cpy));              
            }   
        }

        return moves;
    }

    @Override
    public String asciiRepresentation(){
        if(player == 0 || player == 3){
            return "♖ ";
        }
        if(player == 1 || player == 2){
            return "♜ ";
        }
        return "??";
    }       

    @Override
    public Piece clonePiece(){
     Rook other = new Rook(player,x,y);
        return other;
    }
}