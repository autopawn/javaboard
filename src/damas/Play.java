package damas;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;

public class Play {
    public static void main(String[] args) {
        Damas game = new Damas();
        Player[] players = {new PlayerHumanTerminal(), new PlayerCPURandom()};
        Executor exec = new Executor();
        exec.runGame(game, players);
    }
}