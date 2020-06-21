package javaboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ExecutorRecorder extends Executor{

    //Sobreescribimos para que además de darnos el ganador también escriba en el history.txt
    @Override
    public int runGame(Game init, Player[] players){
        int winner = super.runGame(init, players);
        File archi = new File("history.txt");
        if (!archi.exists()){
            try{ // Si no existe lo creamos 
                archi.createNewFile();
                FileWriter fWriter = new FileWriter(archi,true);
                PrintWriter pWriter = new PrintWriter(fWriter);
                //Añadimos la linea al texto
                pWriter.println(init.gameName()+" "+players[0].playerName+" "+players[1].playerName+" "+winner); 
                pWriter.close();
                fWriter.close();
            } 
            catch(IOException ex){
                System.out.println("Error con el archivo");
            }
        }
        else{
            try{ // Si no existe lo creamos 
                FileWriter fWriter = new FileWriter(archi,true);
                PrintWriter pWriter = new PrintWriter(fWriter);
                //Añadimos la linea al texto
                pWriter.println(init.gameName()+" "+players[0].playerName+" "+players[1].playerName+" "+winner); 
                pWriter.close();
                fWriter.close();
            } 
            catch(IOException ex){
                System.out.println("Error con el archivo");
            }
        }
        

        return winner;
    }
}
