package foxhounds;

//import javaboard.Executor;
import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
//import javaboard.PlayerHumanTerminal;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();

        Player[] players = {new PlayerCPURandom(), new PlayerCPUEval()};

        //Executor exec = new Executor();//ahora debemos usar el recorder
        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
    }
}
