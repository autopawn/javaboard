package simplewars;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import javaboard.Evaluable;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class Simplewars extends GridGame implements Evaluable {
    public String bando;
    public Simplewars(String bando){
        this.bando = bando;
        size_x = 15;
        size_y = 10;
        pieces = new LinkedList<Piece>();
        if(bando.equals("Zombie")){
        // Then PlayerTerminal chose Zombie, so cpu must be Soldier
            int cpu_pieces = 3;
            int player_pieces = 3;
            while(player_pieces > 0){
                Random rand = new Random();
                int randx = rand.nextInt(15);
                int randy = rand.nextInt(10);
                if(pieceAt(randx,randy) == null){
                    pieces.add(new Mage_Soldier(0,randx,randy,3));
                    player_pieces -= 1;
                }
            } 
            while(cpu_pieces > 0){
                Random rand = new Random();
                int randx = rand.nextInt(15);
                int randy = rand.nextInt(10);
                if(pieceAt(randx,randy) == null){
                    pieces.add(new Soldier(1,randx,randy,3));
                    cpu_pieces -= 1;
                }
            } 
        }
        else{
            //Then PlayerTerminal chose Soldier, then CPU must be Zombie
            int cpu_pieces = 3;
            int player_pieces = 3;
            while(player_pieces > 0){
                Random rand = new Random();
                int randx = rand.nextInt(15);
                int randy = rand.nextInt(10);
                if(pieceAt(randx,randy) == null){
                    pieces.add(new Soldier(0,randx,randy,3));
                    player_pieces -= 1;
                }
            } 
            while(cpu_pieces > 0){
                Random rand = new Random();
                int randx = rand.nextInt(16);
                int randy = rand.nextInt(11);
                if(pieceAt(randx,randy) == null){
                    pieces.add(new Mage_Soldier(1,randx,randy,3));
                    cpu_pieces -= 1;
                }
            } 
        }
        
    }

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        // If current player don't have pieces, then current player doesn't have moves, so we call superclass default win condition
        Integer result = super.currentWinner(current_player_moves);
        return result;
    }
    //Make simplewars instance
    @Override
    public Game cloneGame(){
        GridGame clone = new Simplewars(this.bando);
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }

    // Default evaluation function, so that PlayerCPUEval works for SimpleWars
    @Override
    public float defaultEvaluationFunction(){ 
        // How good is the state for the CPU:
        // return player_hp - cpu_hp. This attributes depends on player choice at Play.java
        int cpu_hp = 0;
        int player_hp = 0;
        if (this.bando.equals("Zombie")){ //Then CPU is Soldier and Player is Zombie    
            for(Piece pc : pieces){
                if(pc instanceof Soldier){ // then its an enemy piece
                    cpu_hp += ((Soldier)pc).hp;    
                }
                else if(pc instanceof Mage_Soldier) {
                    player_hp += ((Mage_Soldier)pc).hp;
                }
            }
        }
        else{
            for(Piece pc : pieces){
                if(pc instanceof Soldier){ // then its an enemy piece
                    player_hp += ((Soldier)pc).hp;    
                }
                else if(pc instanceof Mage_Soldier) {
                    cpu_hp += ((Mage_Soldier)pc).hp;
                }
            }
        }    
        int return_value = player_hp - cpu_hp;
        return return_value;
    }

}
