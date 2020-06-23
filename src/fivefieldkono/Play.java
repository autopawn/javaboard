package fivefieldkono;
import java.io.*;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPURandom;

public class Play {

    // Play a FiveFieldKono using the Executor
    public static void main(String[] args) {

        Player[] players = {new PlayerCPURandom(), new PlayerCPURandom()};
        FiveFieldKono game = new FiveFieldKono(players);

        Executor exec = new Executor();
        exec.runGame(game,players);
        Serializable(game, players),
    }
    //Serializacion de los objetos
    public Serializable (Game game, Player[] players){
        //objeto vacio tipo FiveFieldLKono
        FiveFieldKono juego = new FiveFieldKono;
        //defino el estado de juego actual
        juego = game;
        //objeto vacion tipo players
        Player[] jugadores = {new PlayerCPURandom(), new PlayerCPURandom()};
        //se definen los estados
        jugadores[0] = players[0];
        jugadores[1] = players[1];
        //Se cre un archivo .ser fuera, contiene los bytes.
        try{
        FileOutputStream fileOut = new FileOutputStream("save_game.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(juego);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved in /fivefieldkono/save_game.ser");
        }catch (IOException i) {i.printStackTrace();}   
    }
    //Clase de deserializacion, transforma los bytes de vuelta al programa
    public class Deserializacion {

    	//Objeto que apunta a Null
    	FiveFieldKono game = null;
    	Player[] jugadores = null;
      	try {
        	FileInputStream fileIn = new FileInputStream("save_game.ser");
        	//clase ObjectInputStream para leer el archivo de bytes
        	ObjectInputStream in = new ObjectInputStream(fileIn);
        	game = (FiveFieldKono) in.readObject();
        	jugadores = (Player) in.readObject();

        	//Cierra los metodos
        	in.close();
        	fileIn.close();
        //exepciones
      	}catch (IOException i) {i.printStackTrace();return;
        }catch (ClassNotFoundException c) {System.out.println("No se ha podido encontrar la clase");
         c.printStackTrace();
         return;
      	}
	}
}
