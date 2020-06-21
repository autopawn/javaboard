package fivefieldkono;

import javaboard.Executor;
import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        FiveFieldKono game = new FiveFieldKono();

        Player[] players = {new PlayerCPURandom(), new PlayerCPURandom()};

        // Builder ExecutorRecorder, with game name and array of players
        ExecutorRecorder game_history = new ExecutorRecorder("FiveFieldKono", players);

        Executor exec = new Executor();
        // The winning variable is saved
        Integer winner = exec.runGame(game,players);

        // Game history with game winner
        game_history.history(winner);
    }
}
