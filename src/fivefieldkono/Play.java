package fivefieldkono;

import javaboard.ExecutorRecorder;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;
import javaboard.Player;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {
        FiveFieldKono game = new FiveFieldKono();
        String Nombre_Usuario;
        //Se pide nombre de usuario por consola
        Scanner User = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        Nombre_Usuario = User.nextLine();

        Player[] players = {new PlayerHumanTerminal(Nombre_Usuario), new PlayerCPURandom()};

        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
        User.close();
        
        
    }
    
}
