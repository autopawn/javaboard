package pegsolitaire;

import javaboard.PlayerCPURandom;
//Extendemos de PlayerCPURandom solo para modificar el mensaje al momento de perder, será nuestro "2" jugador teórico, pero en la realidad solo habrá un jugador
public class LosePlayer extends PlayerCPURandom{
    @Override
    public String victoryMessage(){
        return "Oh DAMM! You Lose";
    }
}