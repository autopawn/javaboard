package fivefieldkono;

import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;

import java.util.Scanner;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        FiveFieldKono game = new FiveFieldKono();

        //Scanner to recieve PlayerHumanTerminal's name
        String p;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the human player: ");
        p = in.nextLine();
        System.out.println("You entered string " + p);
        in.close();

        Player[] players = {new PlayerHumanTerminal(p), new PlayerCPURandom()};

        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
    }
}
