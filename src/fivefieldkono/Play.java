package fivefieldkono;

import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        FiveFieldKono game = new FiveFieldKono();

        Player[] players = {new PlayerCPURandom(), new PlayerCPURandom()};

        ExecutorRecorder exec = new ExecutorRecorder(); //Para utilizar las opciones de Recorder
        exec.runGame(game,players);
    }
}
