package fivefieldkono;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        String player_name;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter players name: ");
        player_name = sc.nextLine();
        
        FiveFieldKono game = new FiveFieldKono();

        Player[] players = {new PlayerCPURandom(), new PlayerCPURandom()};

        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}
