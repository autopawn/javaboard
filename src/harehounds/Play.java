package harehounds;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        HareAndHounds game = new HareAndHounds();

        Player[] players = {new PlayerCPUEval(), new PlayerCPUEval()};

        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}
