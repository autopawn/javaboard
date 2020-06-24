package javaboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;

public class ExecutorRecorder extends Executor{
    @Override
    public int runGame(Game init, Player[] players){
        //FileWriter file_writer = null;
        Integer num_player_winner = super.runGame(init, players);
        File file_history = new File("history.txt");
        String line_winner = ""+init.GameName()+" game: Player 0: "+players[0].player_name+" versus Player 1: "+players[1].player_name+"... Player "+num_player_winner+" wins!!!\n";
        if (file_history.exists()){//si el archivo existe 
            try{//entonces
                FileWriter file_writer = new FileWriter(file_history, true);
                file_writer.write(line_winner);
                file_writer.close();
            }
            catch (IOException ex){
                System.out.println("Something went wrong. F");
            }

        } else { //si no
            try{
                file_history.createNewFile();
                FileWriter file_writer = new FileWriter(file_history, true);
                file_writer.write(line_winner);
                file_writer.close();


            }
            catch (IOException ex){
                System.out.println("Something went wrong. F");
            }
        
        }
        return num_player_winner;
    
}
}
