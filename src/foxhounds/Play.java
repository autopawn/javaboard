package foxhounds;

import javaboard.ExecutorRecorder;
import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();

        Player[] players = {new PlayerCPURandom(), new PlayerCPUEval()};

        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
    }
}
