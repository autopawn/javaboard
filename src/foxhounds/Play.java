package foxhounds;

import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();

        Player[] players = {new PlayerCPURandom(), new PlayerCPUEval()};

        ExecutorRecorder exec = new ExecutorRecorder(); //Para utilizar las funciones de Recorder
        exec.runGame(game,players);
    }
}
