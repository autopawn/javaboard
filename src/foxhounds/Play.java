package foxhounds;

import javaboard.ExecutorRecorder;
import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;
import java.util.*;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        String player_name;//Aqui es donde se pregunta por el nombre del player(en caso de que sea humano)

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter players name: ");
        player_name = sc.nextLine();

        FoxAndHounds game = new FoxAndHounds();

        Player[] players = {new PlayerHumanTerminal(player_name) , new PlayerCPUEval()};

        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
    }
}
