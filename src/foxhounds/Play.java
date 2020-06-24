package foxhounds;

import javaboard.ExecutorRecorder;//se importa ExecutorRecorder para usar su override del metodo rungame de Executor
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();

        Player[] players = {new PlayerCPURandom(), new PlayerCPUEval()};

        ExecutorRecorder exec = new ExecutorRecorder();//se remplaza el la clase Executor por ExecutorRecorder
        exec.runGame(game,players);
    }
}
