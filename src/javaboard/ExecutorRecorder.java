package javaboard;
import java.io.*;


public class ExecutorRecorder extends Executor{
    @Override
    public int runGame(Game init,Player[] players){
        Integer winner =super.runGame(init,players);//obtenemos el indice del ganador llamando al metodo runGame de la clase padre Executor
        String escribir = init.nombre + " "+players[0].nombre+" "+players[1].nombre +" "+winner+"\n";//creamos el string a escribir en el archivo
        

        BufferedWriter bw = null;//escritura de archivos sin sobreescribir (stack overflow).
    FileWriter fw = null;

    try {
        File file = new File("history.txt");
        // Si el archivo no existe, se crea!
        if (!file.exists()) {
            file.createNewFile();
        }
        // flag true, indica adjuntar información al archivo.
        fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        bw.write(escribir);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
                        //Cierra instancias de FileWriter y BufferedWriter
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    return winner;//retorna mismo valor de la clase padre
    }
}