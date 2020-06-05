package javaboard;

import java.util.List;

public class Executor {

    public int runGame(Game init, Player[] players){

        Game state = init;

        Integer winner = null;

        while(true){
            // Print current state
            System.out.println(state);

            // Get movements for current player
            List<Movement> moves = state.getMovements();

            // Check if a player won
            winner = state.currentWinner(moves);
            if(winner != null) break;

            // Current player decides
            Movement pick = players[state.current_player].pickMovement(moves,null);

            // Update current state
            state = pick.result;
        }

        System.out.println("Jugador "+winner+": "+players[winner].victoryMessage());

        return winner;
    }
}
