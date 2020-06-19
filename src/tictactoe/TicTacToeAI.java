package tictactoe;

import javaboard.Game;
import javaboard.Movement;
import javaboard.Player;

import java.util.*;

public class TicTacToeAI extends Player {
    @Override
    public Movement pickMovement(Game state, List<Movement> options, List<String> input_commands){
        //check if there is a winning move for either player
        Movement winningMove = checkWinningMove((TicTacToe) state);
        if (winningMove != null){
            return winningMove;
        }

        //if there is no winning move, it uses a random move
        Random rand = new Random();
        if (options.size() == 0){
            int randX = rand.nextInt(2);
            int randY = rand.nextInt(2);
            state.pieceAt(randX,randY).player = state.current_player;
            Movement randMove = new Movement(Movement.moveCommand(0,0,randX,randY),state);
            return randMove;
        }else {
            return options.get(rand.nextInt(options.size()));
        }

    }

    private Movement checkWinningMove(TicTacToe state) {
        //Returns a winning move coordinate and the player it belongs
        int player1score = 0;
        int player2score = 0;
        //winningCoord stores all the posible winning moves for either player
        ArrayList<int[]> winningCoord = new ArrayList<int[]>();//(player about to win,xCoord,yCoord)

        //Checks columns
        for(int col = 0;col<3;col++){
            player1score = 0;
            player2score = 0;
             int coordY = 0;
            for (int row = 0;row<3;row++){
                switch (state.pieceAt(col,row).player){
                    case 0:
                        player1score++;
                        break;
                    case 1:
                        player2score++;
                        break;
                    case 2:
                        coordY = row;
                        break;

                }
            }
            //if either player is about to win by placing a mark, save the coordinates
            //state.pieceAt(col,coordY).player == 2 ensures the saved coordinate is available
            if ((player1score == 2 || player2score == 2)&&state.pieceAt(col,coordY).player == 2){
                int[] coord = {1-state.current_player,col,coordY};
                winningCoord.add(coord);
            }
            }
        //Checks rows
        for(int row = 0;row<3;row++){
            player1score = 0;
            player2score = 0;
            int coordX = 0;
            for (int col = 0;col<3;col++){
                switch (state.pieceAt(col,row).player){
                    case 0:
                        player1score++;
                        break;
                    case 1:
                        player2score++;
                        break;
                    case 2:
                        coordX = col;
                        break;
                }
            }
            if ((player1score == 2 || player2score == 2) && state.pieceAt(coordX,row).player == 2 && player1score+player2score != 3){
                int[] coord = {1-state.current_player,coordX,row};
                winningCoord.add(coord);
            }
        }
        //Checks across left to right
        int emptyCoord = 0;
        player1score = 0;
        player2score = 0;
        for(int row = 0;row<3;row++){
                switch (state.pieceAt(row,row).player){
                    case 0:
                        player1score++;
                        break;
                    case 1:
                        player2score++;
                        break;
                    case 2:
                        emptyCoord = row;
                        break;

                }
        }
        if ((player1score == 2 || player2score == 2) && state.pieceAt(emptyCoord,emptyCoord).player == 2 && player1score+player2score != 3){
            int[] coord = {1-state.current_player,emptyCoord,emptyCoord};
            winningCoord.add(coord);
        }

        //Checks across right to left
        emptyCoord = 0;
        player1score = 0;
        player2score = 0;
        for(int row = 0;row<3;row++){
            switch (state.pieceAt(2-row,row).player){
                case 0:
                    player1score++;
                    break;
                case 1:
                    player2score++;
                    break;
                case 2:
                    emptyCoord = row;
                    break;

            }
        }
        if ((player1score == 2 || player2score == 2) && state.pieceAt(2-emptyCoord,emptyCoord).player == 2 && player1score+player2score != 3){
            int[] coord = {1-state.current_player,2-emptyCoord,emptyCoord};
            winningCoord.add(coord);
        }

        if (winningCoord.size() == 0) {return null;}
        winningCoord.sort(Comparator.comparing(a->a[1]));
        //sort all possible moves by who would win
        if (state.current_player == 1){
            Collections.reverse(winningCoord);
        }
        //the current player's move always end up first,so it will pick the winning move before the blocking move

        int xCoord = winningCoord.get(0)[1];
        int yCoord = winningCoord.get(0)[2];


        state.pieceAt(xCoord,yCoord).player = 1-state.current_player;
        state.current_player = 1-state.current_player;


        Movement winningMove = new Movement(Movement.moveCommand(0,0,xCoord,yCoord),state);
        return winningMove;
    }



    @Override
    public String victoryMessage(){
        return "IN YOUR FACE!";
    }
}
