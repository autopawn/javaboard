package pegsolitaire;


import javaboard.Player;

public class play {

    public static void main(String[] args){
        pegsolitaire Game = new pegsolitaire();
        Player[] players={new ganador(),new perdedor()};
        exe exec = new exe();
        exec.runGame(Game, players);
    }
    
}