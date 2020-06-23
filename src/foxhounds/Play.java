package foxhounds;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;
import javaboard.ExecutorRecorder;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();
        String Nombre_Usuario;
        Scanner User = new Scanner(System.in);//Se pide nombre por consola
        System.out.println("Ingrese su nombre: ");
        Nombre_Usuario = User.nextLine();
        Player[] players = {new PlayerHumanTerminal(Nombre_Usuario), new PlayerCPUEval()};

        // Se crea objeto de la clase nueva, para luego correr el juego desde ese objeto
        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
        User.close();
    }   
    
}
