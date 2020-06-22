package pegsolitaire;

import java.util.LinkedList;
import java.util.List;

import javaboard.*;

public class PegSolitaire extends GridGame
{

    // Arreglo de posiciones donde pueden ir piezas, es static para que se pueda acceder a este arreglo desde cualquier método
    // de la clase
    public static int[][] validPos = {{0,2},{0,3},{0,4},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{2,5},{2,6},{3,0},{3,1},{3,2},{3,4},{3,5},{3,6},{4,0},{4,1},{4,2},{4,3},{4,4},{4,5},{4,6},{5,2},{5,3},{5,4},{6,2},{6,3},{6,4}};

    //Constructor de la clase, esta 'hardcodeado' pues el tablero tiene parámetros específicos para funcionar
    public PegSolitaire()
    {
        // Se define el tamaño del tablero
        size_x = 7;
        size_y = 7;

        // Se crea una linkedlist en la cual se agregarán las piezas que estarán en el tablero en un principio
        pieces = new LinkedList<Piece>();

        // Se recorre la lista de posiciones válidas y se agregan las piezas a la lista de piezas, en la posición indicada
        // en la lista de posiciones válidas, y todas al jugador 0 pues solo este jugará.
        for(int[] ps : validPos)
        {
            pieces.add(new PegPiece(0,ps[0],ps[1]));
        }
    }

    //Como el tablero de este juego no es cuadrado, se debe hacer override
    //a la función toString, que entrega el tablero a dibujar
    @Override
    public String toString()
    {
        //Se crea un stringbuilder que contendrá el tablero
        StringBuilder sbu = new StringBuilder();

        //Se agrega a sbu la primera fila (letras)
        sbu.append("\n");
        sbu.append("   ");
        for(int x=0;x<size_x;x++){
            String xlabel = ""+((char)('a'+x));
            sbu.append(xlabel+" ");
        }
        sbu.append("\n");

        //Se agrega el resto de filas
        for(int y = 0; y < size_y; y++)
        {
            // ylabel contendrá el número de la fila
            String ylabel = ""+y;
            sbu.append(ylabel+"  ");

            // Se recorren las columnas para agregar al sbu las piezas del tablero, si hay una pieza en la celda, se dibuja, si no hay
            // se dibuja un punto indicando que es un espacio al que se puede mover.

            // Primero se recorren las primeras tres columnas, puesto que las filas de la 0 a la 2 y de la 6 a la 7 no se debe dibujar
            // nada (está fuera del tablero).

            for(int x = 0; x < 2; x++)
            {
                Piece pc_in_cell = pieceAt(x,y);
                if(pc_in_cell!=null)
                {
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else if(y >= 2 && y < 5){
                    sbu.append(". ");
                }else{
                    sbu.append("  ");
                }
            }

            // Se recorre de la columna 3 a la 5, aquí se debe dibujar una pieza en cada celda menos en la del medio (inicialmente)
            for(int x = 2; x < 5; x++)
            {
                Piece pc_in_cell = pieceAt(x,y);
                if(pc_in_cell!=null)
                {
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else{
                    sbu.append(". ");
                }
            }

            // Se recorren las últimas dos columnas, aquí solo se deben dibujar piezas entre las filas 3 a la 5, en el resto no se
            // debe dibujar nada dado que esas celdas están fuera del tablero)
            for(int x = 5; x < 7; x++)
            {
                Piece pc_in_cell = pieceAt(x,y);
                if(pc_in_cell!=null)
                {
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else if(y >= 2 && y < 5){
                    sbu.append(". ");
                }else{
                    sbu.append("  ");
                }
            }

            sbu.append("\n");

        }

    return sbu.toString();

    }

    // Override del método cloneGame, es la misma que en foxandhounds pero se cambian los tipos.
    @Override
    public Game cloneGame()
    {
        PegSolitaire clone = new PegSolitaire();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces)
        {
            clone.pieces.add(pc.clonePiece());
        }
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }

    // Override de la función isInside, debe hacerse puesto que el isInside original solo funciona para tableros cuadrados
    @Override
    public boolean isInside(int x, int y)
    {
        // Solo están dentro del tablero las posiciones que:

        // Estén en las primeras dos columnas y estén entre las filas 3 y 5
        if(x >= 0 && x < 2 && y >= 2 && y < 5)
        {
            return true;
        // Estén en las columnas tres y cinco y que estén entre las filas 0 y 6
        }else if(x >= 2 && x < 5 && y >= 0 && y < 7)
        {
            return true;
        // Estén en las últimas dos columnas y estén entre las filas 3 y 5
        }else if(x >= 5 && x < 7 && y >= 2 && y < 5)
        {
            return true;
        }

        // Si no se cumple ninguno de los casos anteriores, la posición no está dentro del tablero
        return false;
    }

    // Override de la función currentwinner, es necesaria para establecer las reglas de ganar y perder del juego
    @Override
    public Integer currentWinner(List<Movement> current_player_moves)
    {
        // Se gana cuando quede solo una pieza en la LinkedList de piezas del estado
        if(pieces.size() == 1)
        {
            // Se retorna que el ganador es el jugador actual (recordar que el jugador actual siempre es el 0, el 1 nunca juega)
            return current_player;
        }
        // Se pierde cuando no quedan movimientos que hacer (cuando se gana también pasa esto, pero como es un else if
        // no hay problema)
        else if(current_player_moves.size()==0)
        {
            // Se retorna que el ganador es 1 menos el jugador actual, que como siempre es 0, significa que gana el jugador 1
            return 1-current_player;
        }
        // Si no ha pasado ninguna de las dos cosas anteriores, se retorna que el ganador es null, y se sigue ejecutando el juego
        return null;
    }

    // Saludo amigable, se usará al iniciar el juego
    public void greetings()
    {
        System.out.println("Bienvenido a PegSolitaire. Puede mover su pieza horizontal o verticalmente siempre y cuando haya una pieza entre su posición y la posición a la que quiera moverse, y que la posición a la que se quiera mover esté vacía, ¡buena suerte!.");
    }

}