package tictactoe;

import java.util.List;
import java.util.LinkedList;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Piece;
import javaboard.Movement;

public class TicTacToe extends GridGame{
    public TicTacToe(){
        size_x = 3;
        size_y = 3;
        pieces = new LinkedList<Piece>();
        //Add 5 possible marks for each player
        for (int i = 0; i < 5; i++){
            pieces.add(new Mark(0,4,4));
            pieces.add(new Mark(1,5,4));
        }
    }

    //Cloning game to make a TicTacToe instance
    @Override
    public Game cloneGame(){
        GridGame tttClone = new TicTacToe();
        tttClone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            tttClone.pieces.add(pc.clonePiece());
        }
        tttClone.current_player = current_player;
        tttClone.size_x = size_x;
        tttClone.size_y = size_y;
        return tttClone;
    }

    //Overriding getMovements to get only available movements for the player
    @Override
    public List<Movement> getMovements(){
        List<Movement> marks = new LinkedList<Movement>();
        List<Movement> pieceMarks = new LinkedList<Movement>();
        for(Piece pc : pieces){
            if(pc.player != current_player) continue;
            pieceMarks = pc.getMovements(this);
        }
        for(Movement move : pieceMarks) {
            if(!move.result.isValid()) continue;
            marks.add(move);
        }
        return marks;
    }

    //Overriding currentWinner to adjust javaboard win conditions to TicTacToe game
    @Override
    public Integer currentWinner(List<Movement> playerMoves){
        Game tttGrid = cloneGame();
        //When the board is full returns a Tie
        if (isBoardFull(this)){
            System.out.println("Tie.");
            System.exit(0);
            return 2;
        }
        //When a player wins returns player number
        if(checkWinner(tttGrid) != 3){
            return checkWinner(tttGrid);
        }
        //When there is no winner returns null
        else{
            return null;
        }
    }

    //Checks if there's a player that completed a column, row or diagonal.
    private int checkWinner(Game tttGrid){
        //Check columns
        for(int row = 0; row < 3; row++){
            if (tttGrid.pieceAt(0,row) != null && tttGrid.pieceAt(1,row) != null && tttGrid.pieceAt(2,row) != null 
            && (tttGrid.pieceAt(0,row).player == tttGrid.pieceAt(1,row).player) && (tttGrid.pieceAt(1,row).player == tttGrid.pieceAt(2,row).player)){
                return pieceAt(0,row).player;
            }
        }
        //Check rows
        for(int col = 0; col < 3; col++){
            if (tttGrid.pieceAt(col,0) != null && tttGrid.pieceAt(col,1) != null && tttGrid.pieceAt(col,2) != null 
            && (tttGrid.pieceAt(col,0).player == tttGrid.pieceAt(col,1).player) && (tttGrid.pieceAt(col,1).player == tttGrid.pieceAt(col,2).player)){
                return pieceAt(col,0).player;
            }
        }
        //Check diagonals
        if (tttGrid.pieceAt(0,0) != null && tttGrid.pieceAt(1,1) != null && tttGrid.pieceAt(2,2) != null
        && (tttGrid.pieceAt(0,0).player == tttGrid.pieceAt(1,1).player) && (tttGrid.pieceAt(1,1).player == tttGrid.pieceAt(2,2).player)){
            return pieceAt(1,1).player;
        }
        if (tttGrid.pieceAt(0,2) != null && tttGrid.pieceAt(1,1) != null && tttGrid.pieceAt(2,0) != null
        && (tttGrid.pieceAt(0,2).player == tttGrid.pieceAt(1,1).player) && (tttGrid.pieceAt(1,1).player == tttGrid.pieceAt(2,0).player)){
            return pieceAt(1,1).player;
        }
        return 3;
    }

    //Checks if the javaboard is full to see if the match is a tie.
    public boolean isBoardFull(Game tttGrid){
        for(int x = 0; x < size_x; x++){
            for (int y = 0; y < size_y ; y++){
                if (tttGrid.pieceAt(x,y) == null){
                    return false;
                }
            }
        }
        return true;
    }

    //Overriding toString to change board drawing
    @Override
    public String toString(){
        StringBuilder sbu = new StringBuilder();
        // First row with letters
        sbu.append("\n");
        sbu.append("   ");
        for(int x= 0;x<size_x;x++){
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
            // Each cell
            for(int x=0;x<size_x;x++){
                Piece pc_in_cell = pieceAt(x,y);
                if(pc_in_cell!=null){
                    // Draw piece
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else{
                    // Draw cell
                    sbu.append("□ ");
                }
            }
            sbu.append("\n");
        }
        return sbu.toString();
    }
}
