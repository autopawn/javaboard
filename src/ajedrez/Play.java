
package ajedrez;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerHumanTerminal;
import javaboard.PlayerCPURandom;

public class Play {

    // Play Ajedrez using the Executor
    public static void main(String[] args) {
        Ajedrez game = new Ajedrez();

        Player[] players = {new PlayerHumanTerminal(), new PlayerCPURandom()};
        
        Executor exec = new Executor();
        exec.runGame(game,players);

    }
}
