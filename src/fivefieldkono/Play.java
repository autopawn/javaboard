package fivefieldkono;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPURandom;

public class Play {
    public static void main(String[] args) {
        FiveFieldKono game = new FiveFieldKono();

        Player[] players = {new PlayerCPURandom(), new PlayerCPURandom()};

        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}
