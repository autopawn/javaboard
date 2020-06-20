package javaboard;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ExecutorRecorder extends Executor{
    @Override
    public int runGame(Game init, Player[] players){
        int winner = super.runGame(init, players);
        File history = new File("history.txt");
        
        if (history.exists()){ //Se revisa si es que el archivo ya existe, en caso de que exista, se agrega la linea, sino, se crea.
            try{
                FileWriter line = new FileWriter(history,true);
                String line_to_write = init.game_name+" "+players[0].player_name+" "+players[1].player_name+" "+winner+"\n";// El formata correspondiente a la linea que hay que escribir.
                line.write(line_to_write);
                line.close();
            }
            catch(IOException ex){
                System.out.println("Error404: Ha habido un problema en la manipulacion del archivo");
            }
        }
        else{
            try{
                FileWriter line = new FileWriter(history,true);
                history.createNewFile();
                String line_to_write = init.game_name+" "+players[0].player_name+" "+players[1].player_name+" "+winner+"\n";
                line.write(line_to_write);
                line.close();
            }
            catch(IOException ex){
                System.out.println("Error404: Ha habido un problema en la manipulacion del archivo");
            }

        }
        return winner;
    }
}
