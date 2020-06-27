package javaboard;

import java.util.List;

public class Executor {

    // Runs a game from the given initial state with the given players.
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
            if(winner != null) break;

            // Current player decides
            Movement pick = players[state.current_player].pickMovement(state,moves,null);

            // set winner to -1 (Nobody) if the human player chose to exit to load/new game menu.
            if (pick.command.equals("yes")){
                winner = -1;
                break;
            }

            // Update current state
            state = pick.result;
        }

        // Close current game with no winner
        if (winner==-1) return winner;
        // Print player's victory message
        System.out.println("Jugador "+winner+": "+players[winner].victoryMessage());

        return winner;
    }
}
