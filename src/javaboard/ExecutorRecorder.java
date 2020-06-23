package javaboard;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ExecutorRecorder extends Executor{
    public int runGame(Game init, Player[] players){
        int Nombre_ganador = super.runGame(init, players);
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        //Se crea el string de la issue
        String nombre = init.NombreJuego()+" "+players[0].Nombre+" "+players[1].Nombre+" "+Nombre_ganador; 
        archivo = new File("history.txt");
        if (!archivo.exists()) { 
            //Si el archivo no existe, se crea uno nuevo
            try { 
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
            //Si el archivo ya existe, se escribe sobre el
            try { 
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(nombre);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                System.out.println("No se pudo abrir el archivo");
            }
        }
        return Nombre_ganador;       
    }
}