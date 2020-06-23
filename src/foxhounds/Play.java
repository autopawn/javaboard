package foxhounds;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
    	//se crea el objeto "game" de tipo clase Foxandhounds
        FoxAndHounds game = new FoxAndHounds();

        //Se delcaran los jugadores(lista) objeto, se llaman a los contsructores de los players
        //(arreglo tipo Player)
        Player[] players = {new PlayerCPURandom(), new PlayerCPUEval()};
        //Se crea el objeto tipo executer
        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}
