package javaboard;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class ExecutorRecorder extends Executor{
    public int runGame(Game init, Player[] players){
        //Useful data
        int num = super.runGame(init, players);
        File data;
        FileWriter writefile;
        PrintWriter line;
        

        //Winner data
        String winner = init.Game_name()+" "+players[0].Player_Name+" Vs "+players[1].Player_Name+" Player number: "+num+" WIN!!!!";

        //File name
        data = new File("history.txt");
        
        //Check if the file exists
        if (data.exists()) { 
            try {
                //Write the battle record if the file exists
                writefile = new FileWriter(data, true);
                line = new PrintWriter(writefile);
                line.println(winner);
                line.close();
                writefile.close();
            } catch (IOException ex) {
                System.out.println("bad news,the file is rottenn");
            }
        } else {
            try {
                //Create the file and then write the battle record
                data.createNewFile();
                writefile = new FileWriter(data, true);
                line = new PrintWriter(writefile);
                line.println(winner);
                line.close();
                writefile.close();
            } catch (IOException ex) {
                System.out.println("bad news,the file is rottenn");
            }
        }
        return num;       
    }
}