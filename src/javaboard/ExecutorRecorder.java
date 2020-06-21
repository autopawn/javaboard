package javaboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExecutorRecorder extends Executor{
    public int runGame(Game init, Player[] players){
        int rg = super.runGame(init,players);
        File filename = new File("history.txt");
        String name = init.Game_Name()+" "+players[0].name+" "+players[1].name+" "+rg;
        
        try {
            if (!filename.exists()) {
                filename.createNewFile();
            } 
            FileWriter fooWriter = new FileWriter(filename,true);
            fooWriter.write(name+"\n");
            fooWriter.close();
            } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
          }
        return rg;
        }
}

