

package ajedrez;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Bishop extends Piece {

    public Bishop (int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    /* Bishop Rules:
    A bishop moves any number of vacant squares diagonally.*/   

    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();
        
        /* The idea is to stop adding posible moves if the next square is not empty
        so i splitted the problem in 4 problems, because you have to check in 4 diagonals starting at center.
        For different reasons, the different diagonals stops adding posible moves in different times*/ 
        
        // Case 1 : Check in North-East 

        int[] dys = {1,2,3,4,5,6,7};     
        for(int dy : dys){
            int xx = x + dy;
            int yy = y + dy;

            // Check if the square is not empty and the piece is from the current player
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }

            // Capture Option
            if(grid.pieceAt(xx,yy) != null && grid.isInside(xx,yy) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                // Capture
                cpy.pieceAt(xx,yy).player = cpy.current_player+2;     // new state change piece player, no one has control of it 
                cpy.movePiece(xx,yy,8,7);                             // new state moves the piece to captured zone
                
                // Move Piece
                cpy.movePiece(x,y,xx,yy);                             // new state moves the piece 
                cpy.current_player = 1 - cpy.current_player;          // new state changes current player       
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                break;
            }

            // Movement for empty Square
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }

        // Case 2 : Check in South-East

        for(int dy : dys){
            int xx = x + dy;
            int yy = y - dy;

            // Check if the square is not empty and the piece from the current player
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }

            // Capture Option
            if(grid.pieceAt(xx,yy) != null && grid.isInside(xx,yy) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                // Capture
                cpy.pieceAt(xx,yy).player = cpy.current_player+2;     // new state change piece player, no one has control of it 
                cpy.movePiece(xx,yy,8,7);                             // new state moves the piece to captured zone
                
                // Move Piece
                cpy.movePiece(x,y,xx,yy);                             // new state moves the piece 
                cpy.current_player = 1 - cpy.current_player;          // new state changes current player       
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                break;
            }

            // Movement for empty Square
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }

        // Case 3 : Check in North-West

        for(int dy : dys){
            int xx = x - dy;
            int yy = y + dy;
            // Check if the square is not empty and the piece is from the current player
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }

            // Capture Option
            if(grid.pieceAt(xx,yy) != null && grid.isInside(xx,yy) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                // Capture
                cpy.pieceAt(xx,yy).player = cpy.current_player+2;     // new state change piece player, no one has control of it 
                cpy.movePiece(xx,yy,8,7);                             // new state moves the piece to captured zone
                
                // Move Piece
                cpy.movePiece(x,y,xx,yy);                             // new state moves the piece 
                cpy.current_player = 1 - cpy.current_player;          // new state changes current player       
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                break;
            }

            // Movement for empty Square
            if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy) == null){
                Game cpy = grid.cloneGame();
                cpy.movePiece(x,y,xx,yy);           
                cpy.current_player = 1 - cpy.current_player;                 
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));              
            }   
        }

        // Case 3 : Check in South-West

        for(int dy : dys){
            int xx = x - dy;
            int yy = y - dy;
            // Check if the square is not empty and the piece is from the current player
            if(grid.pieceAt(xx,yy) != null && grid.pieceAt(xx,yy).player == grid.current_player){
                break;
            }

            // Capture Option
            if(grid.pieceAt(xx,yy) != null && grid.isInside(xx,yy) && grid.pieceAt(xx,yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                // Capture
                cpy.pieceAt(xx,yy).player = cpy.current_player + 2;     // new state change piece player, no one has control of it 
                cpy.movePiece(xx,yy,8,7);                               // new state moves the piece to captured zone
                
                // Move Piece
                cpy.movePiece(x,y,xx,yy);                               // new state moves the piece 
                cpy.current_player = 1 - cpy.current_player;            // new state changes current player       
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                break;
            }

            // Movement for empty Square
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
        if(player == 0 || player == 3){
            return "♗ ";
        }
        if(player == 1 || player == 2){
            return "♝ ";
        }
        return "??";
    }        
        
    @Override
    public Piece clonePiece(){
     Bishop  other = new Bishop (player,x,y);
        return other;
    }
}