package pegsolitaire;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerHumanTerminal;
import javaboard.PlayerCPURandom;
import javaboard.PlayerCPUEval;

public class PlayPeg {
    public static void main(String[] args){

        PegSolitaire game = new PegSolitaire();

        Player[] players = {new PlayerHuman(), new PlayerCPU()};

        Executor exec = new Executor();

        exec.runGame(game,players);
    }
}