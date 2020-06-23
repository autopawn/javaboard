package damas;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerHumanTerminal;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a Damas (checkerss) using the Executor
    public static void main(String[] args) {
        Damas game = new Damas();

        Player[] players = {new PlayerHumanTerminal(), new PlayerCPURandom()};
        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}
