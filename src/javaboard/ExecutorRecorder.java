package javaboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ExecutorRecorder extends Executor{
    public int runGame(Game init, Player[] players){

        int winnervalue = super.runGame(init, players);

        //Creating some variables
        File txt;
        FileWriter write;
        PrintWriter line;
        String data = init.game_name() + " " + players[0].player_name + " " + players[1].player_name + " " + winnervalue;

        //Writes on txt file
        txt = new File("history.txt");

        if(txt.exists()){ // if history.txt exists
            try{
                write = new FileWriter(txt,true);
                line = new PrintWriter(write);
                line.println(data);
                line.close();
                write.close();
            } catch (IOException ex){
                System.out.println("Error: You have a problem with your file");
            }
        } else{
            try{ // if history.txt doesn't exists
                txt.createNewFile();
                write = new FileWriter(txt,true);
                line = new PrintWriter(write);
                line.println(data);
                line.close();
                write.close();
            } catch (IOException ex){
                System.out.println("Error: You have a problem with your file");
            }            
        }

        return winnervalue;
    }    
}