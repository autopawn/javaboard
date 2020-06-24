package javaboard;

import java.util.List;
import java.util.Scanner;

// A human player that picks movements from the terminal using their command strings
public class PlayerHumanTerminal extends Player {

    public PlayerHumanTerminal(String nombre){
        this.nombre=nombre;
    }

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

        // Expect comand
        Movement input_move = null;
        Scanner scan_in = new Scanner(System.in);
        while(true){
            System.out.println("> Insert movement:");
            String input = scan_in.nextLine();

            for(Movement mov : options){
                if(mov.command.equals(input)){
                    input_move = mov;
                }
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
