package javaboard;
import java.io.*;


// A movement is a String command associated to a new resulting game state
//el movimiento se asocia a los nuevos resultados del estado del juego
public class Movement implements java.io.Serializable{
    //variables
    public String command;
    public Game result;

    // Movement constructor
    public Movement(String command, Game result){
        //refiriendoce a los miembros de mas arriba
        //cambia los valores de la clase padre
        //this. se refiere a la instantacia en que se encuentra
        this.command = command;
        this.result  = result;
    }

    // The string representation for a movement command
    //string que se usa para repesentar los comandos de movimiento
    //(x,y) es la posicion donde se encuentran y (xx,yy) es donde se va
    public static String moveCommand(int x, int y, int xx, int yy){
        char xn  = (char)('a'+x);
        char xxn = (char)('a'+xx);
        return ""+xn+y+xxn+yy;
    }

}
