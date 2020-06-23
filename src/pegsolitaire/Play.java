package pegsolitaire;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;

public class Play {

    // Play a PegSolitaire game using the Executor
    public static void main(String[] args) {
        PegSolitaire game = new PegSolitaire();
        //Dos jugadores, PlayerMessage extiende de PlayerHumanTerminal(jugador activo) y LosePlayer que sólo estará presente en el código para entregar el mensaje de derrota.
        Player[] players = {new PlayerMessage(),new LosePlayer()};

        Exec exec = new Exec();
        exec.runGame(game,players);
    }
}
