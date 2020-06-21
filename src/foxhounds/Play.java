package foxhounds;

import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();

        //Scanner tu recieve PlayerHumanTerminal's name
        String p;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the human player: ");
        p = in.nextLine();
        System.out.println("You entered string " + p);

        Player[] players = {new PlayerHumanTerminal(p), new PlayerCPUEval()};

        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
    }
}
