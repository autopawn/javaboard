package simplewars;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;
public class Play {

    // Play a Simplewars game using the Executor
    public static void main(String[] args) {
        String player_choice;
        
        while(true){
            Scanner in = new Scanner(System.in);
            System.out.println("Elija el bando: \n\n1. Zombie\n\n2. Soldier");  
            String bando = in.next();
            if(bando.equals("Zombie") || bando.equals("Soldier")){ // check for equality, not identity
                player_choice = bando;
                break;
            }
            else{
                System.out.println("Bando inválido, asegúrese de escribir correctamente el bando");
            }
        }
        Simplewars game = new Simplewars(player_choice);
        Player[] players = {new PlayerHumanTerminal(), new PlayerCPUEval()};

        Executor exec = new Executor();
        System.out.println("\nComenzando partida de Simple Wars");
        
        exec.runGame(game,players);
    }
}
