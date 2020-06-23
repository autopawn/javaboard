package pegsolitaire;

import java.util.List;
import java.util.Scanner;
import javaboard.PlayerHumanTerminal;
//Creada para modificar el mensaje al ganar, ya que no se pueden modificar los elementos del javaboard
public class PlayerMessage extends PlayerHumanTerminal{
    @Override
    public String victoryMessage(){
        return "Congratulations! You win!";
    }
}
