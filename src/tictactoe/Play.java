package tictactoe;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerHumanTerminal;
import javaboard.PlayerCPURandom;

public class Play {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        Player[] players = {new PlayerHumanTerminal(), new PlayerCPUTicTacToe()};

        Executor exec = new Executor();
        exec.runGame(game,players);
    }
}