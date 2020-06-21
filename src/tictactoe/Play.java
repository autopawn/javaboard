package tictactoe;
import javaboard.*; 
//tome como ejemplo el Fox and Hounds
public class Play {
    public static void main(String[] args) {
        tictactoe game = new tictactoe();
        Executor exec = new Executor();
        Player[] players = {new tictactoeia(),new tictactoeia()};

        exec.runGame(game,players);

    }
}