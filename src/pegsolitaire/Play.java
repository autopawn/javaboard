package pegsolitaire;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerHumanTerminal;
import javaboard.PlayerCPURandom;
import javaboard.PlayerCPUEval;

// Clase principal, donde se dan las instrucciones para ejecutar el juego
public class Play
{
    public static void main(String[] args) {

        // Nueva estado de Pegsolitaire
        PegSolitaire game = new PegSolitaire();

        // Se printea un saludo amigable :)
        game.greetings();

        // Lista de jugadores, si bien Senku se juega solo, se crea la lista con dos jugadores para que el método currentwinner
        // funcione correctamente.
        Player[] players = {new PlayerCPURandom(), new PlayerCPURandom()};

        // Nuevo Executor
        Executor exec = new Executor();

        // Se llama al método runGame de Executor, esto funciona como se explicó en clases
        exec.runGame(game,players);
    }
}