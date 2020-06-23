package othello;

import javaboard.Player;
import javaboard.PlayerCPURandom;

public class Play {

    public static void main(String[] args) {
        Othello game = new Othello();

        Player[] players = {new PlayerCPURandom(), new PlayerCPURandom()};

        ExecutorOthello exec = new ExecutorOthello();
        exec.runGame(game,players);
    }
}
