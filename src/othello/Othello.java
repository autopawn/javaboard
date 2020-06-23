package othello;

import java.util.LinkedList;
import java.util.List;

import javaboard.Evaluable;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Othello extends GridGame implements Evaluable{

    public Othello(){
        size_x = 8;
        size_y = 8;
        pieces = new LinkedList<Piece>();

        //Jugador negro
        pieces.add(new OthelloPiece(0,3,3));
        pieces.add(new OthelloPiece(0,4,4));

        //Jugador blanco
        pieces.add(new OthelloPiece(1,4,3));
        pieces.add(new OthelloPiece(1,3,4));
    }

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        Integer result = super.currentWinner(current_player_moves);
        if(result != null){
            this.current_player = 1-current_player;
            current_player_moves = this.getMovements();
            Integer resultother = super.currentWinner(current_player_moves);
            if(resultother != null){
                int pieceb = 0;
                int piecew = 0;
                for(Piece pc : pieces){
                    if(pc.player == 0){
                        pieceb++;
                    }
                    else if(pc.player == 1){
                        piecew++;
                    }
                }
                if(pieceb <= piecew){
                    return 1;
                }
                else return 0;
            }
            this.current_player = 1-current_player;
            return 2;
        }
        return null;
    }
    // Clone the FoxAndHounds so that the clone is a FoxAndHounds instance
    @Override
    public Game cloneGame(){
        GridGame clone = new Othello();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }

    @Override
    public String toString(){
        StringBuilder sbu = new StringBuilder();

        // First row with letters
        sbu.append("\n");
        sbu.append("   ");
        for(int x=0;x<size_x;x++){
            String xlabel = ""+((char)('a'+x));
            sbu.append(xlabel+" ");
        }
        sbu.append("\n");

        // Other parts of the board
        for(int y=0;y<size_y;y++){
            // Number label
            String ylabel = ""+y;
            if(ylabel.length()<2) sbu.append(" ");
            sbu.append(ylabel+" ");

            // Each cell
            for(int x=0;x<size_x;x++){
                Piece pc_in_cell = pieceAt(x,y);
                if(pc_in_cell!=null){
                    // Draw piece
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else{
                    // Draw cell
                    sbu.append("⬛");
                }
            }
            sbu.append("\n");
        }
        return sbu.toString();
    }

    // Default evaluation function, so that PlayerCPUEval works for FoxAndHounds
    @Override
    public float defaultEvaluationFunction(){
        // How good is the state for the fox:
        float eval = 0;
        // Get the fox and how near it is to the other side of the board
        Piece fox = null;
        for(Piece pc : pieces){
            if(pc.player==0) fox = pc;
        }
        eval += 7-fox.y;
        // Hound position
        for(Piece pc : pieces){
            if(pc.player==1){
                if(pc.y >= fox.y){
                    // It is very good for the fox if is has passed by the hounds
                    eval += 0.5;
                }else{
                    // It is better for the fox if the hounds are far in x
                    eval += 0.05*Math.abs(pc.x-fox.x);
                }
            }
        }
        // Negate eval if the current player is not the fox
        if(current_player!=0) eval = -eval;
        // Return eval
        return eval;
    }
}