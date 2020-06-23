package pegsolitaire;

import javaboard.PlayerHumanTerminal;
//Se crea para mostrar el caso de derrota
public class perdedor extends PlayerHumanTerminal{
    @Override
    public String victoryMessage(){
        return "dou";
    }
}