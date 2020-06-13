

package ajedrez;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Queen  extends Piece {

    public Queen(int player, int x, int y){
        super(player,x,y); // Call parent constructor
    }

    /* Queen Rules:
    The queen moves any number of vacant squares horizontally, vertically, or diagonally. */
    
    @Override
    public List<Movement> getMovements(Game state){

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

        // Case 5 : Check in North-East
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

        // Case 6 : Check in South-East

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

        // Case 7 : Check in North-West

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

        // Case 8 : Check in South-West

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
            return "♕ ";
        }
        if(player == 1 || player == 2){
            return "♛ ";
        }
        return "??";
    }       
    
    @Override
    public Piece clonePiece(){
        Queen other = new Queen(player,x,y);
        return other;
    }
}