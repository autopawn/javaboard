package pegsolitaire;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

// Clase que contiene los métodos y atributos de una pieza del tablero
public class PegPiece extends Piece
{

    // Constructor de PegPiece
    public PegPiece(int player, int x, int y)
    {
        super(player,x,y);
    }

    // Override de la función getMovements, el override es necesario para establecer las reglas de movimiento de PegSolitaire
    @Override
    public List<Movement> getMovements(Game state)
    {
        // Se crea un estado del juego para poder usar los métodos de gridgame
        GridGame grid = (GridGame) state;

        // Se crea una nueva linkedlist de movimientos, a esta lista se le agregarán los movimientos posibles mas adelante
        List<Movement> moves = new LinkedList<Movement>();

        // Hay dos posibilidades de movimiento para x y para y, o sea: dos hacia arriba, dos hacia abajo, dos hacia la derecha y
        // y dos hacia la izquierda.
        int[] ds = {-2,2};
        for(int d : ds)
            {
                // Se crean las posiciones a las que se podría mover la pieza
                int xd = x + d;
                int yd = y + d;

                // Para que un movimiento vertical sea válido, deben cumplirse las siguientes condiciones:
                // -La posible posición a la que se moverá la pieza debe estar dentro del tablero (isInside(x,yd))
                // -No debe haber una pieza en la posible posición a la que se moverá la pieza (pieceAt(x,yd) == null)
                // -En la celda entre la posible posición a la que se moverá la pieza y la posición actual de la pieza, debe haber
                // una pieza (pieceAt(x,y + d/2) != null)
                if(grid.isInside(x,yd) && grid.pieceAt(x,yd)==null && grid.pieceAt(x, y + d/2)!=null)
                {
                    // Se crea una copia del estado del juego para pasarlo como posible estado nuevo a la lista de movimientos
                    Game cpy = grid.cloneGame();

                    // En el estado cpy, se mueve la pieza si es que el movimiento es válido
                    cpy.movePiece(x,y,x,yd);

                    // Se crea una linkedlist auxiliar a la que se le agregarán las piezas no borradas
                    LinkedList<Piece> aux = new LinkedList<Piece>();

                    // A la linkedlist auxiliar se le agregan todas las piezas que no son la que estaba entre la posición original
                    // de la pieza y la posición a la que se movió, dicha pieza no se agrega pues debe ser eliminada (la pieza que
                    // se movió saltó sobre esa pieza)
                    for(Piece pc : cpy.pieces)
                    {
                        if(!(pc.x == x && pc.y == (y + d/2)))
                        {
                            aux.add(pc);
                        }
                    }

                    // La lista de piezas del estado cpy se iguala a la lista auxiliar
                    cpy.pieces = aux;

                    // A la lista de movimientos, se le agrega el movimiento con el estado cpy y el comando sería la posición a la
                    // que se movió la pieza en el estado cpy
                    moves.add(new Movement(Movement.moveCommand(x,y,x,yd),cpy));

                }
                // Para los movimientos horizontales, se hace exactamente lo mismo que para las verticales pero se usan el eje horizontal
                if(grid.isInside(xd,y) && grid.pieceAt(xd,y)==null && grid.pieceAt(x + d/2, y)!=null)
                {

                    Game cpy = grid.cloneGame();

                    cpy.movePiece(x,y,xd,y);

                    LinkedList<Piece> aux = new LinkedList<Piece>();

                    for(Piece pc : cpy.pieces)
                    {
                        if(!(pc.x == (x + d/2) && pc.y == y))
                        {
                            aux.add(pc);
                        }
                    }

                    cpy.pieces = aux;

                    moves.add(new Movement(Movement.moveCommand(x,y,xd,y),cpy));

                }

            }

        // Se retorna la lista de movimientos para que el jugador pueda elegir uno
        return moves;

    }

    // Override de la función clonePiece, igual a la de foxhounds y fivefieldkono pero se le cambió el tipo
    @Override
    public Piece clonePiece()
    {
        PegPiece other = new PegPiece(player,x,y);
        return other;
    }

    // Override de la función asciiRepresentation. Como solo hay un jugador (el jugador 0), solo se necesita un símbolo para este
    // jugador, si llegar a ocurrir que hay otro jugador (highly unlikely) se retornaría el símbolo '??'
    @Override
    public String asciiRepresentation()
    {
        if(player == 0)
        {
            return  "■ ";
        }
        return "??";
    }
}