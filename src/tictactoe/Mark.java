package tictactoe;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Mark extends Piece{
    public Mark (int player, int x, int y){
        super(player,x,y);
    }

    @Override
    public String asciiRepresentation(){
        if (player == 0){
            return "X ";
        }
        else if (player == 1){
            return "○ ";
        }
        else{
            return "□ ";
        }
    }

    @Override
    public List<Movement> getMovements(Game state){
        GridGame tttGrid = (GridGame) state;
        List<Movement> marks = new LinkedList<Movement>();

        for (int x = 0; x < tttGrid.size_x; x++){
            for (int y = 0; y < tttGrid.size_y; y++){
                if (tttGrid.pieceAt(x,y) == null && tttGrid.isInside(x,y)){
                    Game tttBoard = tttGrid.cloneGame();
                    //We move the player's mark from outside the board to desired position
                    tttBoard.movePiece(4 + tttBoard.current_player, 4, x, y);
                    //Change players each turn
                    tttBoard.current_player = 1 - tttBoard.current_player;
                    marks.add(new Movement(Movement.moveCommand(0, 0, x, y), tttBoard));
                }
            }
        }
        return marks;
    }

    @Override
    public Piece clonePiece(){
        Mark other = new Mark (player,x,y);
        return other;
    }

}