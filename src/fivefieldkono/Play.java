package fivefieldkono;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;

import java.util.Scanner;
import java.io.*;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        Scanner scan_command = new Scanner(System.in);
        while(true){
            System.out.println("Type 'load' to load a game from a savefile.");
            System.out.println("Type 'exit' to close the game.");
            System.out.println("Type anything else (or nothing at all) to start a new game.");
            System.out.println(">Please type according to the command you wish to use:");
            
            String input = scan_command.nextLine();
            if (input.equals("exit")) break;
            if (input.equals("load")) {
                System.out.println("> Specify number of slot to load from (1 to 9). Type anything else to return to move selection");
                String number = scan_command.nextLine();
                try{
                    Integer slot = Integer.parseInt(number);
                
                    if (slot > 0 && slot<10){
                        try {
                            FiveFieldKono load = null;
                            String selected_slot = "src/fivefieldkono/FFKSave"+number+".ser";
                            FileInputStream load_file = new FileInputStream(selected_slot);
                            ObjectInputStream load_from = new ObjectInputStream(load_file);
                            load = (FiveFieldKono) load_from.readObject();
                            load_from.close();
                            load_file.close();
                            System.out.println("The game has been loaded!");
                            Player[] players = {new PlayerHumanTerminal(), new PlayerCPURandom()};
                            Executor loaded_match = new Executor();
                            loaded_match.runGame(load, players);
                        } catch (IOException SerializableError) {
                            System.out.println("Couldn't find the save file...");
                            SerializableError.printStackTrace();
                        } catch (ClassNotFoundException ClassError) {
                            System.out.println("Invalid save file.");
                            ClassError.printStackTrace();
                        }
                    }
                } catch (NumberFormatException WrongNumber) {
                    System.out.println("Not a valid number.");
                }
            }
            else{
                FiveFieldKono game = new FiveFieldKono();

                Player[] players = {new PlayerHumanTerminal(), new PlayerCPURandom()};

                Executor exec = new Executor();
                exec.runGame(game,players);
            }
        }
        scan_command.close();
    }
}
