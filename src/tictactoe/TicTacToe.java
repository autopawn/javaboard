package tictactoe;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

import java.util.LinkedList;
import java.util.List;

public class TicTacToe extends GridGame {
    private int winner = 2;
    public TicTacToe(){
        size_x = 3;
        size_y = 3;
        pieces = new LinkedList<Piece>();
        int winner = 3;
        //fill the board with player 2 pieces (blank squares)
        for(int x = 0;x<size_x;x++){
            for (int y = 0;y<size_y;y++){
                pieces.add(new TicTacToeMark(2,x,y));
            }
        }

    }

    public int getWinner(){
        return winner;
    }
    public void setWinner(int winner){
        this.winner = winner;
    }

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

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        //if any player has a score of 3(3 consecutive marks), said player wins
        if (getMaxScore(0,this) == 3){
            return 0;
        }
        if (getMaxScore(1,this) == 3){
            return 1;
        }
        //if the board is filled up and there is no winner, a draw is declared
        if (isFull(this)){
            System.out.println("It's a tie!");
            System.exit(0);
            return null;
        }

        return null;
    }
    private int getMaxScore(int player, GridGame state){
        //gets the player maximum score across columns,rows and diagonals
        int maxScore = 0;
        int playerScore = 0;
        //check columns
        for(int col = 0;col<3;col++){
            for (int row = 0;row<3;row++){
                if (state.pieceAt(col,row).player == player){
                    playerScore++;
                }
            }
            if (playerScore>maxScore){
                maxScore = playerScore;
            }
            playerScore = 0;
        }
        //check rows;
        playerScore = 0;
        for(int row = 0;row<3;row++){
            for (int col = 0;col<3;col++){
                if (state.pieceAt(col,row).player == player){
                    playerScore++;
                }
            }
            if (playerScore>maxScore){
                maxScore = playerScore;
            }
            playerScore = 0;
        }
        //check cross
        playerScore = 0;
        for(int row = 0;row<3;row++){
            if(state.pieceAt(row,row).player == player){
                playerScore++;
            }
        }
        if (playerScore>maxScore){
            maxScore = playerScore;
        }
        //check cross
        playerScore = 0;
        for(int row = 0;row<3;row++){
            if(state.pieceAt(2-row,row).player == player){
                playerScore++;
            }
        }
        if (playerScore>maxScore){
            maxScore = playerScore;
        }
        return maxScore;
    }
    private boolean isFull(TicTacToe state){
        //checks if all the spaces on the board are used
        for(int x = 0;x<3;x++){
            for (int y = 0;y<3;y++){
                if (state.pieceAt(x,y).player == 2){
                    return false;
                }
            }
        }
        return true;
    }


    //we override toString to give spacing to the board labels
    @Override
    public String toString(){
        StringBuilder sbu = new StringBuilder();

        // First row with letters
        sbu.append("\n");
        sbu.append("   ");
        for(int x=0;x<size_x;x++){
            String xlabel = ""+((char)('a'+x));
            sbu.append(" "+xlabel+" ");
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
                    if((x+y)%2==0) sbu.append("■ ");
                    else sbu.append("□ ");
                }
            }
            sbu.append("\n");
        }
        return sbu.toString();
    }

}
