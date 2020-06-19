package tictactoe;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class TicTacToeMark extends Piece {
    public TicTacToeMark(int player, int x, int y) {
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

        TicTacToe grid = (TicTacToe) state;
        List<Movement> movements = new LinkedList<Movement>();
        for (int x = 0;x<((GridGame) state).size_x;x++){
            for (int y = 0;y<((GridGame) state).size_y;y++){
                //returns a list of all posible movements (to empty squares inside the board)
                if (state.pieceAt(x,y).player == 2 && ((TicTacToe) state).isInside(x,y)) {
                    Game newBoard = grid.cloneGame();
                    newBoard.current_player = 1-newBoard.current_player;

                    newBoard.pieceAt(x,y).player = newBoard.current_player;
                    movements.add(new Movement(Movement.moveCommand(0,0,x,y),newBoard));

                }
            }
        }

        return movements;

    }


    @Override
    public Piece clonePiece() {
        TicTacToeMark newMark = new TicTacToeMark(player,x,y);
        return newMark;
    }
}
