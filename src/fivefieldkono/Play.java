package fivefieldkono;

//import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPURandom;
import javaboard.ExecutorRecorder;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        FiveFieldKono game = new FiveFieldKono();
        String User;
        Scanner re = new Scanner(System.in);
        System.out.println("Ingress your username: ");
        User = re.nextLine();

        Player[] players = {new PlayerHumanTerminal(User), new PlayerCPURandom()};

        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
        re.close();
    }
}
