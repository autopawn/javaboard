package javaboard;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ExecutorRecorder extends Executor{
    public int runGame(Game init, Player[] players){
        int ganador = super.runGame(init, players);
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        String nombre = init.Nombre_Juego()+" "+players[0].Nombre+" "+players[1].Nombre+" "+ganador; //Se crea el string con el formato que pide la issue
        archivo = new File("history.txt");
        if (!archivo.exists()) { 
            try { //Si el archivo no existe, se crea uno nuevo y luego se escribe en el
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(nombre);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                System.out.println("No se pudo abrir el archivo");
            }

        } else {
            try { //Si el archivo ya existe, solo escribe en el
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(nombre);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                System.out.println("No se pudo abrir el archivo");
            }
        }
        return ganador;       
    }
}