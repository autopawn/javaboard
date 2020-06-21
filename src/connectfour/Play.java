package connectfour;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        connectfour game = new connectfour();

        Player[] players = {new PlayerHumanTerminal(), new PlayerCPURandom()};

        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}