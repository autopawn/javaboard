package javaboard;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

//Esta es la clase que se pide crear, que extiende de Executor.
public class ExecutorRecorder extends Executor{
    public int runGame(Game init, Player[] players){
        int winner = super.runGame(init, players);
        File file;
        FileWriter write;
        PrintWriter line;
        // Se crea la String que es la linea con el formato pedido para el texto.
        String line_format = init.Game_Name()+" "+players[0].name_player+" "+players[1].name_player+" "+winner;
        file = new File("history.txt"); //Se crea el archivo .txt con el nombre pedido.
        /* Para escribir en el archivo tenemos dos casos. En el primero, que el archivo no exista, en tal caso
        se debe crear el archivo y luego escribir en él la linea con el formato pedido. El segundo caso, es que
        el archivo ya exista, y por ende hay que seguir escribiendo en él. En ambos casos se puede tener un error
        de lectura tipo "IOException", para entregar de manera más "elegante" el error, implementamos manejo de excepciones visto en clases. */
        if(!file.exists()){ //Caso en que el archivo no existe.
            try{
               file.createNewFile();
                write = new FileWriter(file, true);
                line = new PrintWriter(write);
                line.println(line_format);
                line.close();
                write.close();             
            } catch (IOException ex) {
            System.out.println("The file could not be opened.");
            }
        } else { //Caso que el archivo ya exista.
            try {
                write = new FileWriter(file, true);
                line = new PrintWriter(file);
                line.println(line_format);
                line.close();
                write.close();
            } catch (IOException ex) {
              System.out.println("The file could not be opened.");  
            }              
        }
        return winner;
    }
}
