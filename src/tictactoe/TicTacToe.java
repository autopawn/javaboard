package tictactoe;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;


public class TicTacToe extends GridGame {

    // We create the tictactoe, with enough pieces for every player to place in the board
    // The pieces are placed outside the board at the start with their positions differint in +1 x
    // That way we can access both of them easily with every iteration.

    public TicTacToe(){
        size_x = 3;
        size_y = 3;
        pieces = new LinkedList<Piece>();
        pieces.add(new TPiece(0,4,5));
        pieces.add(new TPiece(0,4,5));
        pieces.add(new TPiece(0,4,5));
        pieces.add(new TPiece(0,4,5));
        pieces.add(new TPiece(0,4,5));
        pieces.add(new TPiece(1,5,5));
        pieces.add(new TPiece(1,5,5));
        pieces.add(new TPiece(1,5,5));
        pieces.add(new TPiece(1,5,5));
        pieces.add(new TPiece(1,5,5));
    }

    // Check if board is full

    public boolean IsFull() {

        boolean isFull = true;

        Game grid = cloneGame();

        for(int x = 0; x < size_x; x++){
            for (int y = 0; y < size_y; y++){
                if(grid.pieceAt(x,y) == null){
                    isFull = false;
                }
            }
        }

        return isFull;

    }


    // Here we check for every single way of wining tictactoe

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        Game grid = cloneGame();

        // If the board is full, then just exit the program.

        if(IsFull()){
            System.out.println("It's a Draw.");
            System.exit(0);
            return 2;
        }
        for(int i = 0; i < 3; i++){
            if( grid.pieceAt(0,i)!=null && grid.pieceAt(1,i)!=null && grid.pieceAt(2,i)!=null && (grid.pieceAt(0,i).player == grid.pieceAt(1,i).player) && 
            (grid.pieceAt(1,i).player == grid.pieceAt(2,i).player) ){
                return pieceAt(0,i).player;
            };
        }

        for(int i = 0; i < 3; i++){
            if( grid.pieceAt(i,0)!=null && grid.pieceAt(i,1)!=null && grid.pieceAt(i,2)!=null && (grid.pieceAt(i,0).player == grid.pieceAt(i,1).player) && 
            (grid.pieceAt(i,1).player == grid.pieceAt(i,2).player) ){
                return pieceAt(i,0).player;
            };
        }

        if( grid.pieceAt(0,0)!=null && grid.pieceAt(1,1)!=null && grid.pieceAt(2,2)!=null && (grid.pieceAt(0,0).player == grid.pieceAt(1,1).player) && 
            (grid.pieceAt(1,1).player == grid.pieceAt(2,2).player) ){
                return pieceAt(0,0).player;
        };

        if( grid.pieceAt(2,0)!=null && grid.pieceAt(1,1)!=null && grid.pieceAt(0,2)!=null && (grid.pieceAt(2,0).player == grid.pieceAt(1,1).player) && 
            (grid.pieceAt(1,1).player == grid.pieceAt(0,2).player) ){
                return pieceAt(2,0).player;
        };
        
        return null;
    }

    // We create a new instance of the game, that we can manipulate later.

    @Override
    public Game cloneGame(){
        GridGame clone = new TicTacToe();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }

    // Overriden toString method to draw the board in another way
    @Override
    public String toString(){
            StringBuilder sbu = new StringBuilder();
    
            // First row with letters
            sbu.append("\n");
            sbu.append("   ");
            for(int x=0;x<size_x;x++){
                String xlabel = ""+((char)('a'+x));
                sbu.append(xlabel+" ");
            }
            sbu.append("\n");
    
            // Other parts of the board
            for(int y=0;y<size_y;y++){
                // Number label
                String ylabel = ""+y;
                if(ylabel.length()<2) sbu.append(" ");
                sbu.append(ylabel+" ");
                for(int x=0; x<size_x;x++){
                    Piece pc_in_cell = pieceAt(x,y);
                    if(pc_in_cell!=null){
                        String repr = pc_in_cell.asciiRepresentation();
                        sbu.append(repr);
                    }
                    // If nothing there just place space
                    else{
                        sbu.append("  ");
                    }
                }
    
                sbu.append("\n");
            }
        return sbu.toString();
    }

    // Override Movement method, because we use only one piece at a time
    // we don't want to see more than the moves available for 1 piece

    @Override
    public List<Movement> getMovements(){
        List<Movement> moves = new LinkedList<Movement>();
        List<Movement> piece_moves = new LinkedList<Movement>();
        for(Piece pc : pieces){
            if(pc.player != current_player) continue;
            piece_moves = pc.getMovements(this);
        }
        for(Movement mov : piece_moves) {
            if(!mov.result.isValid()) continue;
            moves.add(mov);
        }
        return moves;
    }
    

    
}