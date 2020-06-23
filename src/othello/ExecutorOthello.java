package othello;

import java.util.List;
import javaboard.Executor;
import javaboard.Game;
import javaboard.Movement;
import javaboard.Player;

public class ExecutorOthello extends Executor{

    // Runs a game from the given initial state with the given players.
    @Override
    public int runGame(Game init, Player[] players){

        Game state = init;

        // The current winner, we use null when there is no winner yet
        Integer winner = null;

        while(true){
            // Print current state
            System.out.println(state);

            // Get movements for current player
            List<Movement> moves = state.getMovements();

            // Check if a player won
            winner = state.currentWinner(moves);
            if(winner != null){
                if(winner == 2){
                    state.current_player = 1- state.current_player;
                    moves = state.getMovements();
                }else if(winner != 2)break;
            }    

            // Current player decides 
            Movement pick = players[state.current_player].pickMovement(state,moves,null);

            // Update current state
            state = pick.result;
        }

        // Print player's victory message
        System.out.println("Jugador "+winner+": "+players[winner].victoryMessage());

        return winner;
    }
}