package tictactoe;
import java.util.LinkedList;
import java.util.List;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class tictactoexd extends Piece {
    public tictactoexd(int player, int x, int y) {
        super(player,x,y);
    }
    @Override
    public String asciiRepresentation() {
        if (player==0) {
            return " X ";
        }else if (player==1){
            return " ○ ";
        }
        else {
            return " ■ ";
        }
    }

    @Override
    public List<Movement> getMovements(Game state) {
        //lista con los movimientos posibles
        tictactoe grid = (tictactoe) state;
        List<Movement> movements = new LinkedList<Movement>();
        for (int x = 0;x<((GridGame) state).size_x;x++){
            for (int y = 0;y<((GridGame) state).size_y;y++){
                if (state.pieceAt(x,y).player == 2 && ((tictactoe) state).isInside(x,y)) {
                    Game tabla_nueva = grid.cloneGame();
                    tabla_nueva.current_player = 1-tabla_nueva.current_player;

                    tabla_nueva.pieceAt(x,y).player = tabla_nueva.current_player;
                    movements.add(new Movement(Movement.moveCommand(0,0,x,y),tabla_nueva));

                }
            }
        }

        return movements;

    }


    @Override
    public Piece clonePiece() {
        tictactoexd newMark = new tictactoexd(player,x,y);
        return newMark;
    }
}