package tictactoe;


import java.util.LinkedList;
import java.util.List;

import javaboard.*;

public class Play {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Executor exec = new Executor();
        Player[] players = {new TicTacToeAI(),new TicTacToeAI()};

        exec.runGame(game,players);

    }
}
