package pegsolitaire;

import javaboard.PlayerHumanTerminal;
//se crea para el caso de victoria
public class ganador extends PlayerHumanTerminal{
    @Override
    public String victoryMessage(){
        return "Fasil con s de sencillo";
    }
}