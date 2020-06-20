package tictactoe;

import javaboard.PlayerHumanTerminal;
import javaboard.Player;
import javaboard.Executor;


public class Play {

    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();

        Player[] players = {new PlayerHumanTerminal(), new PlayerCPUtictactoe()};
                Executor exec = new Executor();
                exec.runGame(game,players);

    }
    
}