package foxhounds;

import javaboard.Executor;
import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();

        Player[] players = {new PlayerCPURandom(), new PlayerCPUEval()};

        // Builder ExecutorRecorder, with game name and array of players
        ExecutorRecorder game_history = new ExecutorRecorder("FoxAndHounds", players);

        Executor exec = new Executor();
        // The winning variable is saved
        Integer winner = exec.runGame(game,players);

        // Game history with game winner
        game_history.history(winner);
    }
}
