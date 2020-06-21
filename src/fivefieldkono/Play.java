package fivefieldkono;

import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPURandom;
import javaboard.PlayerCPUEval;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        //Solo jugaran maximo 2 jugadores reales, en caso de que jueguen solo bots, se omite
        //Se puede cambiar el turno en que jugara el jugador
        String player_name1;
        String player_name2;
        int num_player;
        Scanner entry = new Scanner(System.in);
        System.out.println("Number of Real Players");
        num_player = entry.nextInt();
        if(num_player==0){
            //Jugaran las IAS
            FiveFieldKono game = new FiveFieldKono();
            Player[] players = {new PlayerCPURandom(), new PlayerCPUEval()};
            ExecutorRecorder exec = new ExecutorRecorder();
            exec.runGame(game,players);
        }
        else if(num_player==1){
            Scanner tipo1 = new Scanner(System.in);
            System.out.println("Player Name: ");
            player_name1 = tipo1.nextLine();

            FiveFieldKono game = new FiveFieldKono();
            Player[] players = {new PlayerHumanTerminal(player_name1), new PlayerCPUEval()};
            ExecutorRecorder exec = new ExecutorRecorder();
            exec.runGame(game,players);
        }
        else if(num_player==2){
            Scanner tipo2 = new Scanner(System.in);
            System.out.println("Player1 and Player 2 Name: ");
            player_name1 = tipo2.nextLine();
            player_name2 = tipo2.nextLine();

            FiveFieldKono game = new FiveFieldKono();
            Player[] players = {new PlayerHumanTerminal(player_name1), new PlayerHumanTerminal(player_name2)};
            ExecutorRecorder exec = new ExecutorRecorder();
            exec.runGame(game,players);
        }
    }
}
