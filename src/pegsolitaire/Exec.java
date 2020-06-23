package pegsolitaire;

import java.util.List;
import javaboard.Executor;
import javaboard.Player;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;
import javaboard.Movement;
//Extensión de "Executor" para que el mensaje final de victoria o derrota no indique la presencia de algún jugador0 o jugador1
public class Exec extends Executor{
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
            if(winner != null) break;

            // Current player decides
            Movement pick = players[state.current_player].pickMovement(state,moves,null);

            // Update current state
            state = pick.result;
        }

        // Print player's victory message without "jugador 0" or "jugador 1"
        System.out.println(players[winner].victoryMessage());

        return winner;
    }
}
