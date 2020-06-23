package pegsolitaire;

import java.util.List;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;

public class PlayerHuman extends PlayerHumanTerminal{
    // Override the victory message so it fits better with the game
    @Override
    public String victoryMessage(){
        return "Congratulations! You won Peg Solitaire";
    }
}