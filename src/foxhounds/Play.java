package foxhounds;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerCPUMinimax; //Miminax

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();

        Player[] players = {new PlayerCPUMinimax(),new PlayerCPURandom()};    //Minimax For player0
        //Player[] players = {new PlayerCPURandom(), new PlayerCPUMinimax()}; //Minimax For player1

        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}
