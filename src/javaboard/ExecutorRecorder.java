package javaboard;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class ExecutorRecorder extends Executor {

    @Override
    public int runGame(Game init, Player[] players){
        Integer winner = super.runGame(init, players);  //obtenemos el indice del ganador llamando a runGame de Executor
        String line = init.name + " " + players[0].name + " " + players[1].name + " " + winner + "\n"; // el string nombre juego, jugador 1, jugador 2, indice ganador
        FileWriter flwriter = null;
        File file = new File("history.txt");
        if(file.exists()){ //Analizamos si el archivo history existe
            try{  //De existir escribimos la linea obtenida anteriormente
                flwriter = new FileWriter(file, true);
                flwriter.write(line);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally{
                if (flwriter != null){
                    try{
                        flwriter.close();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }    
        else{ //de no existir, se crea el archivo y se escribe la linea
            try{
                file.createNewFile();
                flwriter = new FileWriter(file,true);
                flwriter.write(line);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                if (flwriter != null) {
                    try {
                        flwriter.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }    
        }    
        return winner; //retornamos el mismo valor de Executor para no causar problemas en el resto del codigo
    }
    
}