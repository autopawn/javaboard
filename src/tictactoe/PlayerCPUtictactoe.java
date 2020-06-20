package tictactoe;

import javaboard.Movement;
import javaboard.Player;
import javaboard.Game;
import javaboard.GridGame;
import java.util.List;

public class PlayerCPUtictactoe extends Player{

    // pick Movement only uses evaluate to get the best move

    @Override
    public Movement pickMovement(Game state, List<Movement> options, List<String> input_commands){
        assert(options.size()>0);
        Movement bestMove = null;
        bestMove = Evaluate(state, options);
        return bestMove;
    }

    @Override
    public String victoryMessage(){
        return "That was easy...";
    }

    // We check who wins the game, we need this in this way to get values for our evaluation
    // This are remnants of a failed minmax implementation...


    static int check(Game state){

        GridGame grid = (GridGame) state;

        if(grid.currentWinner(grid.getMovements()) != null && grid.currentWinner(grid.getMovements()) == grid.current_player){
            return 10;

        } else if(grid.currentWinner(grid.getMovements()) != null && grid.currentWinner(grid.getMovements()) == 1 - grid.current_player) {
            return -10;
        }
        return 0;
    }

    // Here we evaluate what is the best move


    public Movement Evaluate(Game state, List<Movement> movList){


        GridGame grid = (GridGame) state;

        Movement bestMove = null;

        // If it is the first move for the cpu try to get the edge, or they are already being played, then the middle.

        if(movList.size() == 8 && (grid.pieceAt(0, 0) != null || grid.pieceAt(0, 2) != null || grid.pieceAt(2, 2) != null || grid.pieceAt(2, 0) != null)){
            Game cpy = grid.cloneGame();
            cpy.movePiece(4+cpy.current_player, 5, 1, 1);
            cpy.current_player = 1 - cpy.current_player;
            char xn = (char)('a'+1);
            bestMove = new Movement(""+xn+1,cpy);
            return bestMove;
        }

        if(movList.size() == 8 && (grid.pieceAt(0, 0) == null && grid.pieceAt(0, 2) == null && grid.pieceAt(2, 2) == null && grid.pieceAt(2, 0) == null)){
            Game cpy = grid.cloneGame();
            cpy.movePiece(4+cpy.current_player, 5, 0, 0);
            cpy.current_player = 1 - cpy.current_player;
            char xn = (char)('a'+0);
            bestMove = new Movement(""+xn+0,cpy);
            return bestMove;
        }

        // Check every place, if we win by putting a piece there, then do it

        for(int y = 0; y < grid.size_y; y++){
            for(int x = 0; x < grid.size_x; x++){
                if(grid.pieceAt(x, y) == null){
                    Game cpy = grid.cloneGame();

                    

                    cpy.movePiece(4+cpy.current_player, 5, x, y);


                    //Checks if board if about to be filled up, then does it.

                    if(movList.size() == 1){
                        cpy.current_player = 1 - cpy.current_player;
                        char xn = (char)('a'+x);
                        bestMove = new Movement(""+xn+y,cpy);
                        return bestMove;
                    }

                    if(check(cpy) == 10){
                        cpy.current_player = 1 - cpy.current_player;
                        char xn = (char)('a'+x);
                        bestMove = new Movement(""+xn+y,cpy);
                        return bestMove;
                    }

                    grid.movePiece(x, y, 4+cpy.current_player, 5);

                
                }
            }
        }

        // Check every place, if we lose, then try to avoid it by putting a piece where the other player would win.

        for(int y = 0; y < grid.size_y; y++){
            for(int x = 0; x <grid.size_x; x++){
                if(grid.pieceAt(x, y)==null){
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(4+1-cpy.current_player, 5, x, y);

                    if(check(cpy) == -10){
                        cpy.movePiece(x, y, 4+1-cpy.current_player, 5);
                        cpy.movePiece(4+cpy.current_player, 5, x, y);
                        cpy.current_player = 1 - cpy.current_player;
                        char xn = (char)('a'+x);
                        bestMove = new Movement(""+xn+y,cpy);
                        return bestMove;
                    }
                    cpy.movePiece(x, y, 4+1-cpy.current_player, 5);
                }

            }
        }

        // If we secured an edge try to get another edge, that way we can get the advantage

        if(grid.pieceAt(0,0) != null && grid.pieceAt(0, 0).player == grid.current_player && grid.pieceAt(0,2) == null){
            Game cpy = grid.cloneGame();
            cpy.movePiece(4+cpy.current_player, 5, 0, 2);
            cpy.current_player = 1 - cpy.current_player;
            char xn = (char)('a'+0);
            bestMove = new Movement(""+xn+0,cpy);
            return bestMove;
        }

        if(grid.pieceAt(0,0) != null && grid.pieceAt(0, 0).player == grid.current_player && grid.pieceAt(2,0) == null){
            Game cpy = grid.cloneGame();
            cpy.movePiece(4+cpy.current_player, 5, 2, 0);
            cpy.current_player = 1 - cpy.current_player;
            char xn = (char)('a'+0);
            bestMove = new Movement(""+xn+0,cpy);
            return bestMove;
        }

        // If we don't have any of the situations stated above, just fill the board wherever is available.

        for(int y = 0; y < grid.size_y; y++){

            for(int x = 0; x < grid.size_x; x++){
                if(grid.pieceAt(x, y) == null){
                    Game cpy = grid.cloneGame();
                    cpy.movePiece(4+cpy.current_player, 5, x, y);
                    cpy.current_player = 1 - cpy.current_player;
                    char xn = (char)('a'+x);
                    bestMove = new Movement(""+xn+y,cpy);
                    return bestMove;
                }
            }
        }

        return bestMove;


    }
    
}