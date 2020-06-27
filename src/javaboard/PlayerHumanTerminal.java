package javaboard;

import java.util.List;
import java.util.Scanner;

import java.io.*;

// A human player that picks movements from the terminal using their command strings
public class PlayerHumanTerminal extends Player {

    @SuppressWarnings("resource")
    @Override
    public Movement pickMovement(Game state, List<Movement> options, List<String> input_commands){
        assert(options.size()>0);

        // Print available moves
        System.out.print("Moves :");
        for(Movement mov : options){
            System.out.print(" "+mov.command);
        }
        System.out.println();

        // Expect command
        Movement input_move = null;
        Scanner scan_in = new Scanner(System.in);
        while(true){
            System.out.println("You can insert a movement, or insert 'save' or 'exit' for saving or closing the current match.");
            System.out.println("> Insert a movement or command:");
            String input = scan_in.nextLine();

            for(Movement mov : options){
                if(mov.command.equals(input)){
                    input_move = mov;
                }
            }
            // Command "exit" enables the user to close current match and load or create a different game.
            if (input.equals("exit")) {
                System.out.println("Exit current match? Any unsaved progress will be lost. Type 'yes' to confirm.");
                String exit_selection = scan_in.nextLine();
                if (exit_selection.equals("yes")){
                    Movement exit_command = new Movement(exit_selection, state);
                    input_move = exit_command;
                }
                else continue;
            }
            // Command "save" for making a save file for the ongoing match.
            if (input.equals("save")) {
                System.out.println("> Specify number of slot to save at (1 to 9). Type anything else to return to move selection");
                String number = scan_in.nextLine();
                try{
                    Integer slot = Integer.parseInt(number);
                 
                    if (slot > 0 && slot<10){
                        try {
                            String selected_slot = "src/fivefieldkono/FFKSave"+number+".ser";
                            Game save_game = state.cloneGame();
                            FileOutputStream save_file = new FileOutputStream(selected_slot);
                            ObjectOutputStream save_to = new ObjectOutputStream(save_file);
                            save_to.writeObject(save_game);
                            save_to.close();
                            save_file.close();
                            System.out.println("Saved!");
                        } catch (IOException SerializableError) {
                            SerializableError.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("returning to movement selection...");
                    }
                } catch (NumberFormatException wrong_number){
                    System.out.println("Not a valid number.");
                }
                continue;
            }
            // Valid command, break
            if(input_move != null) break;

            // Try again
            System.out.println("> Invalid move!");
        }

        return input_move;
    }

    @Override
    public String victoryMessage(){
        return "Show me your moves!";
    }
}
