package fivefieldkono;

//import javaboard.Executor;
import javaboard.ExecutorRecorder;
import javaboard.Player;
//import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
//import javaboard.PlayerHumanTerminal;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        FiveFieldKono game = new FiveFieldKono();

        Player[] players = {new PlayerCPURandom(), new PlayerCPURandom()};

        //Executor exec = new Executor();//ahora debemos usar el recorder
        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
    }
}
