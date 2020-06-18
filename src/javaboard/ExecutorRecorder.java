package javaboard;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
public class ExecutorRecorder extends Executor{
    public int runGame(Game init, Player[] players){
        int num = super.runGame(init, players);
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        String nombre = init.NameGame()+" "+players[0].name+" "+players[1].name+" "+num;
        archivo = new File("history.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(nombre);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                System.out.println("Error404: Ha habido un problema en la manipulacion del archivo");
            }
            
        } else {
            try {
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(nombre);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                System.out.println("Error404: Ha habido un problema en la manipulacion del archivo");
            }
        }
        return num;       
    }
}