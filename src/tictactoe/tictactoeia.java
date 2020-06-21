package tictactoe;
import javaboard.Game;
import javaboard.Movement;
import javaboard.PlayerCPURandom;
import java.util.*;

public class tictactoeia extends PlayerCPURandom {
    @Override
    //chequea si se puede ganar y si no hace un movimiento aleatorio
    public Movement pickMovement(Game state, List<Movement> options, List<String> input_commands){
        Movement mov_ganador = checkmov_ganador((tictactoe) state);
        if (mov_ganador != null){
            return mov_ganador;
        }

        Random rand = new Random();
        if (options.size() == 0){
            int randomx = rand.nextInt(2);
            int randomy = rand.nextInt(2);
            state.pieceAt(randomx,randomy).player = state.current_player;
            Movement randomMove = new Movement(Movement.moveCommand(0,0,randomx,randomy),state);
            return randomMove;
        }else {
            return options.get(rand.nextInt(options.size()));
        }

    }

    private Movement checkmov_ganador(tictactoe state) {
        int player1ptje = 0;
        int player2ptje = 0;
        ArrayList<int[]> cordewin = new ArrayList<int[]>();

        //Ve las columnas
        for(int col = 0;col<3;col++){
            player1ptje = 0;
            player2ptje = 0;
             int cordey = 0;
            for (int fila = 0;fila<3;fila++){
                switch (state.pieceAt(col,fila).player){
                    case 0:
                        player1ptje++;
                        break;
                    case 1:
                        player2ptje++;
                        break;
                    case 2:
                        cordey = fila;
                        break;

                }
            }
            
            if ((player1ptje == 2 || player2ptje == 2)&&state.pieceAt(col,cordey).player == 2){
                int[] corde = {1-state.current_player,col,cordey};
                cordewin.add(corde);
            }
            }
        //chequea las filas
        for(int fila = 0;fila<3;fila++){
            player1ptje = 0;
            player2ptje = 0;
            int cordex = 0;
            for (int col = 0;col<3;col++){
                switch (state.pieceAt(col,fila).player){
                    case 0:
                        player1ptje++;
                        break;
                    case 1:
                        player2ptje++;
                        break;
                    case 2:
                        cordex = col;
                        break;
                }
            }
            if ((player1ptje == 2 || player2ptje == 2) && state.pieceAt(cordex,fila).player == 2 && player1ptje+player2ptje != 3){
                int[] corde = {1-state.current_player,cordex,fila};
                cordewin.add(corde);
            }
        }

        //revisa de derecha a izq
        int vacio = 0;
        player1ptje = 0;
        player2ptje = 0;
        for(int fila = 0;fila<3;fila++){
            switch (state.pieceAt(2-fila,fila).player){
                case 0:
                    player1ptje++;
                    break;
                case 1:
                    player2ptje++;
                    break;
                case 2:
                    vacio = fila;
                    break;

            }
        }
        if ((player1ptje == 2 || player2ptje == 2) && state.pieceAt(2-vacio,vacio).player == 2 && player1ptje+player2ptje != 3){
            int[] coord = {1-state.current_player,2-vacio,vacio};
            cordewin.add(coord);
        }

        //revisa de izq a der
        vacio = 0;
        player1ptje = 0;
        player2ptje = 0;
        for(int fila = 0;fila<3;fila++){
                switch (state.pieceAt(fila,fila).player){
                    case 0:
                        player1ptje++;
                        break;
                    case 1:
                        player2ptje++;
                        break;
                    case 2:
                        vacio = fila;
                        break;

                }
        }
        if ((player1ptje == 2 || player2ptje == 2) && state.pieceAt(vacio,vacio).player == 2 && player1ptje+player2ptje != 3){
            int[] corde = {1-state.current_player,vacio,vacio};
            cordewin.add(corde);
        }


        if (cordewin.size() == 0) {return null;}
        cordewin.sort(Comparator.comparing(a->a[1]));
        if (state.current_player == 1){
            Collections.reverse(cordewin);
        }
        

        int xCord = cordewin.get(0)[1];
        int yCord = cordewin.get(0)[2];


        state.pieceAt(xCord,yCord).player = 1-state.current_player;
        state.current_player = 1-state.current_player;


        Movement winningMove = new Movement(Movement.moveCommand(0,0,xCord,yCord),state);
        return winningMove;
    }



    @Override
    public String victoryMessage(){
        return "Ganar es Ganar";
    }
}