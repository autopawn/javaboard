package simplewars;

import java.util.Scanner;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerHumanTerminal;

public class Play {
    public static void main(String[] args){

        System.out.println(ColorLibrary.GREEN + "Welcome to Simplewars, a basic java version of Advance Wars"+ ColorLibrary.RESET);
        System.out.println("To play please choose an option: ");
        System.out.println("Player vs player (1)");
        System.out.println("Player vs CPU    (2)");
        System.out.println("CPU vs CPU       (3)");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        input.close();
        if(userInput.equals("1")){
            Simplewars game = new Simplewars();
            Player[] players = {new PlayerHumanTerminal(), new PlayerHumanTerminal()};
            Executor exec = new Executor();
            exec.runGame(game, players);
        }
        if(userInput.equals("2")){
            Simplewars game = new Simplewars();
            Player[] players = {new PlayerHumanTerminal(), new PlayerCPUEval()};
            Executor exec = new Executor();
            exec.runGame(game, players);
        }
        if(userInput.equals("3")){
            Simplewars game = new Simplewars();
            Player[] players = {new PlayerCPUEval(), new PlayerCPUEval()};
            Executor exec = new Executor();
            exec.runGame(game, players);
        } 
    }
}