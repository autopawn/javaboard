package foxhounds;

import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;
public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();
        String Usuario;
        Scanner User = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        Usuario = User.nextLine();
        Player[] players = {new PlayerHumanTerminal(Usuario), new PlayerCPUEval()};

        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
        User.close();
    }
}
