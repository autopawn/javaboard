package foxhounds;

import javaboard.Executor;
import javaboard.ExecutorRecorder;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;

public class Play {

    // Play a FoxAndHounds game using the Executor
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of PlayerHumanTerminal: ");
        int cant = scanner.nextInt();
        System.out.print("\n");
        FoxAndHounds game = new FoxAndHounds();
        if (cant == 0){
            Player[] players = {new PlayerCPURandom(), new PlayerCPUEval()};
            ExecutorRecorder exec = new ExecutorRecorder();
            exec.runGame(game,players);
        }
        else if (cant == 1){
            Scanner scanner1 = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = scanner1.nextLine();
            System.out.print("\n");
            Player[] players = {new PlayerHumanTerminal(name), new PlayerCPUEval()};
            ExecutorRecorder exec = new ExecutorRecorder();
            exec.runGame(game,players);
        }
        else if (cant == 2){
            Scanner scanner2 = new Scanner(System.in);
            System.out.print("Enter the name 1: ");
            String name1 = scanner2.nextLine();
            System.out.print("\n");
            System.out.print("Enter the name 2: ");
            String name2 = scanner2.nextLine();
            System.out.print("\n");
            Player[] players = {new PlayerHumanTerminal(name1), new PlayerHumanTerminal(name2)};
            ExecutorRecorder exec = new ExecutorRecorder();
            exec.runGame(game,players);
        }
    }
}
