package foxhounds;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();

        Player[] players = {new PlayerCPURandom(), new PlayerHumanTerminal()};

        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}
