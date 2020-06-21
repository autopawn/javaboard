package javaboard;

import java.io.*;
import java.util.*;

public class ExecutorRecorder extends Executor{
    private String player0 = null;
    private String player1 = null;
    private String game_name;

    // CONSTRUCTOR EXECUTORRECORDER
    public ExecutorRecorder(String game_name, Player[] players){
        // Game name of the builder
        this.game_name=game_name;
        for (int i=0; i<2; i++){
            // Check if players are human type
            if (players[i] instanceof PlayerHumanTerminal){
                Scanner in = new Scanner(System.in);
                System.out.println("Ingrese el nombre del jugador humano:");
                if (i==0){
                    this.player0 = in.nextLine();   // To player 0
                }
                else{
                    this.player1 = in.nextLine();   // To player 1
                }
            }
        }

        // Players are different from humans to player0 and player1;
        if (player0 == null){
            this.player0 = players[0].toString();
        }
        if (player1 == null){
            this.player1 = players[1].toString();

        }
    }

    // Method to add or create game history
    public void history(Integer winner){
        try{
            int i=0;    // File checker exists or not
            File file = new File("history.txt");

            if(!file.exists()){
                file.createNewFile();       // Create file
                i=1;
            }

            FileWriter wrt = new FileWriter("history.txt", true);
            PrintWriter line = new PrintWriter(wrt);

            if (i == 1){
                // History.txt header
                String texto = centerString(90, "¡GAME HISTORY!");
                line.println("\n"+texto);
                String texto2 = "__________________________________________________________________________________________";
                line.println(texto2);
                String GN = centerString(25, "Game Name");
                String P0 = centerString(20, "Player0");
                String P1 = centerString(20, "Player1");
                String W = centerString(20, "Winner");
                String texto1 = "|"+GN+"|"+P0+"|"+P1+"|"+W+"|";
                line.println(texto1);
                String texto3 = "|_________________________|____________________|____________________|____________________|";
                line.println(texto3);
            }

            // Add current game history
            String GN = centerString(25, game_name);    // Maximum character size is 25 for game name
            String P0 = centerString(20, player0);      // Maximum character size is 20 for player0 name
            String P1 = centerString(20, player1);      // Maximum character size is 20 for player1 name
            String W;
            if(winner == 0){
                W = centerString(20,"Player0");
            }
            else{
                W = centerString(20,"Player1");
            }
            String texto = "|"+GN+"|"+P0+"|"+P1+"|"+W+"|";

            // Text is added to History.txt
            line.println(texto);

            // wtr and line closes
            wrt.close();
            line.close();
        }
        catch (Exception er){
            System.out.println(er.getMessage());
        }
    }

    // Method to centralize a string in a specific size of space
    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
}