package ConnectFour;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;

public class Play {

    // Play a ConnectFour game using the Executor
    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();

        Player[] players = {new PlayerHumanTerminal(), new PlayerHumanTerminal()};

        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}