package simplewars;

import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.PlayerCPURandom;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;
public class Play {

    // Play a Simplewars game using the Executor
    public static void main(String[] args) {
        String player_choice;
        
        while(true){
            Scanner in = new Scanner(System.in);
            while(true){
                System.out.println("\n- Jugar                   - Instrucciones");
                String comm = in.next();
                if(comm.equals("Jugar")){
                    break;
                }
                else{
                    System.out.println("\nSimple Wars es un juego en el que cada equipo cuenta con 3 soldados, estas unidades son capaces de moverse hasta un máximo de 4 casillas y sólamente con movimientos verticales y horizontales, notar que un movimiento es inválido si: \n 1.- No está en rango \n 2.- Hay un enemigo en la casilla \n 3.- La casilla excede el tamaño del tablero.\n\n El objetivo del juego es dañar a las unidades enemigas, al mover tus piezas en una casilla adyacente a una pieza rival les quitarás 1 de HP, gana quien deje al equipo rival sin piezas restantes. ¡Ten en consideración que cada pieza tiene 3 de HP!");
                }
            }
            System.out.println("\nElija el bando: \n\n1. Zombie\n\n2. Soldier");  
            String bando = in.next();
            if(bando.equals("Zombie") || bando.equals("Soldier")){ // check for equality, not identity
                player_choice = bando;
                break;
            }
            else{
                System.out.println("Bando inválido, asegúrese de escribir correctamente el bando");
            }
        }
        Simplewars game = new Simplewars(player_choice);
        Player[] players = {new PlayerHumanTerminal(), new PlayerCPUEval()};

        Executor exec = new Executor();
        System.out.println("\nComenzando partida de Simple Wars");
        
        exec.runGame(game,players);
    }
}
