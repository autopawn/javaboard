package pegsolitaire;

import java.util.List;
import javaboard.PlayerCPURandom;
import java.util.Scanner;

public class PlayerCPU extends PlayerCPURandom{
    // Override the CPU victory message so it works like a defeat message for the player
    @Override
    public String victoryMessage(){
        return "There are no more legal moves! You lost";
    }
}