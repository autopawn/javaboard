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
        String Usuario;
        Scanner User = new Scanner(System.in);
        System.out.println("Enter your username: ");
        Usuario = User.nextLine();

        Player[] players = {new PlayerHumanTerminal(Usuario), new PlayerCPURandom()};

        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
        User.close();
    }
}
