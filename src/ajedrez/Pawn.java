

package ajedrez;

import java.util.LinkedList;
import java.util.List;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Pawn extends Piece {

    public Pawn(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    /* Pawn Rules:
    1.- A pawn moves straight forward one square, if that square is vacant, Pawns cannot move backwards.
    2.- If it has not yet moved, a pawn also has the option of moving two squares straight forward, provided both squares are vacant. 
    3.- Pawns are the only pieces that capture differently from how they move. A pawn can capture an enemy piece on either of the two 
        squares diagonally in front of the pawn (but cannot move to those squares if they are vacant).*/

    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();
        
        // For Rule 1

        int yy;

        if(grid.current_player == 0){ yy = y-1;}
        else { yy = y + 1;}
        
        // Rule 3 Capturing

        int [] dxs = {-1,1};
        for(int dx : dxs){
            int xx = x+dx;
            
            // If the move has a piece, and is not from the current player
            if (grid.pieceAt(xx, yy) != null && grid.isInside(xx,yy) && grid.pieceAt(xx, yy).player != grid.current_player){
                Game cpy = grid.cloneGame();
                // Capture
                cpy.pieceAt(xx,yy).player=cpy.current_player+2;       // new state change piece player, no one has control of it 
                cpy.movePiece(xx,yy,8,7);                             // new state moves the piece to captured zone

                // Move the piece        
                cpy.movePiece(x,y,xx,yy);                             // new state moves this piece
                cpy.current_player = 1 - cpy.current_player;          // new state changes player

                // Append new movement to the movement list
                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
            } 
        }

        // Rule 1, move just one square

        if(grid.isInside(x,yy) && grid.pieceAt(x, yy) == null){
            
            Game cpy = grid.cloneGame();
            // Move the piece
            cpy.movePiece(x,y,x,yy);                          // new state moves this piece
            cpy.current_player = 1 - cpy.current_player;      // new state changes player

            // Append new movement to the movement list
            moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
        }
        
        // Rule 2, Extra moving
        // Pawns starts in y = 1 (For player 1) and y = 6 (For player 0), so if they are in that row, means that they havnt move yet, so they can move 2 squares
        
        if(y == 1 | y == 6){
            if (y==1){ yy = y+2;
            }else if (y == 6){ yy = y-2;}
            if(grid.isInside(x,yy) && grid.pieceAt(x, yy) == null){
            
                Game cpy = grid.cloneGame();
                
                //Move Piece
                cpy.movePiece(x,y,x,yy);                         // new state moves this piece
                cpy.current_player = 1 - cpy.current_player;     // new state changes player
    
                // Append new movement to the movement list
                moves.add(new Movement(Movement.moveCommand(x,y,x,yy),cpy));
            }            
        }
                  

        return moves;
    }

    @Override
    public String asciiRepresentation(){
        if(player==0 || player==3){
            return "♙ ";
        }
        if(player==1 || player ==2){
            return "♟ ";
        }
        return "??";
    }
    
    @Override
    public Piece clonePiece(){
        Pawn other = new Pawn(player,x,y);
        return other;
    }
}