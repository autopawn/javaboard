package simplewars;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;
public class Soldier extends Piece {
    int hp;
    public Soldier(int player, int x, int y, int hp){
        super(player,x,y); // Call parent constructor
        this.hp = hp;
    }

    @Override
     public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;
        // Movement list
        List<Movement> moves = new LinkedList<Movement>();
        //visited  list stores possible player pieces moves
        List<Pares> visited = new ArrayList<Pares>();
        Queue<Pares> queue = new LinkedList<Pares>();
        Pares par_inicial = new Pares(this.x, this.y, 0);
        queue.add(par_inicial); // We add the start node (current piece position)
        boolean initial = true;
        //"BFS" attempt
        while(!queue.isEmpty()){
            Pares curr_par = queue.poll();
            // Only checks cells where cell depth is <= 4 
            if (curr_par.depth < 4){
                //We check for adjacent cells (not diagonal) for each cell stored in queue 
                int[] adjacency = {-1,1};
                for(int dy: adjacency){
                    if(curr_par.y + dy <= 9 && curr_par.y  + dy>= 0){ // y value is valid if and only if it's between [0-9]
                        // A cell is valid if there's no piece in that position
                        if(grid.pieceAt(curr_par.x, curr_par.y + dy) == null){
                            Pares new_pair = new Pares(curr_par.x,curr_par.y+dy,curr_par.depth+1);
                            // Add cell to queue if and only if it's not in queue already
                            // We use iterator so we can add objects in queue while iterating (if we use for loop -> 💀)
                            Iterator<Pares> itr = queue.iterator();
                            boolean flag = true;
                            while(itr.hasNext()){
                                Pares comparison_pair = itr.next();
                                if(comparison_pair.equals(new_pair)){
                                    flag = false;
                                }
                            }
                            if(flag == true){
                                queue.add(new_pair);
                            }
                        }
                    }
                }
                for(int dx: adjacency){
                    if(curr_par.x  +dx <= 14 && curr_par.x +dx >= 0){ // x value is valid if and only if it's between [0-15]
                        if(grid.pieceAt(curr_par.x +dx , curr_par.y) == null){
                            Pares new_pair = new Pares(curr_par.x+dx,curr_par.y,curr_par.depth+1);   
                            Iterator<Pares> itr = queue.iterator();
                            boolean flag = true;
                            while(itr.hasNext()){
                                Pares comparison_pair = itr.next();
                                if(comparison_pair.equals(new_pair)){
                                    flag = false;
                                }
                            }
                            if(flag == true){
                                queue.add(new_pair);
                            }
                        }
                    }    
                }
            }
                ListIterator<Pares> iterator = visited.listIterator();
                boolean flag = true;
                while(iterator.hasNext()){
                    Pares par = iterator.next();
                    if(par.equals(curr_par)){
                        flag = false;
                        break;
                    }
                }
                if(flag == true && initial == false){
                        iterator.add(curr_par);

                }
            //Ignore the first cell, because that's the initial state (current piece position)
            if(initial){
                initial = false;
            }

        }
        //possible_moves has only valid moves, so no need to check pieceAt() or isInside()
        for(Pares possible_moves : visited){
                Game cpy = grid.cloneGame();
                cpy.movePiece(this.x,this.y,possible_moves.x, possible_moves.y); 
                LinkedList<Piece> temp_arr = new LinkedList<Piece>();
                for(Piece soldado: cpy.pieces){
                    if (soldado instanceof Mage_Soldier){
                        //For each soldier, we make an imaginary square (made by adjacent cells), if the new position of the current piece is 
                        //in the square, then we reduce its health by 1 using .update() method
                        int[] moves_arr = {-1,0,1};
                        for(int adjacency_dy: moves_arr){
                            for(int adjacency_dx: moves_arr){
                                if(soldado.x + adjacency_dx == possible_moves.x && soldado.y + adjacency_dy == possible_moves.y){
                                    ((Mage_Soldier)soldado).update();
                                }
                            }
                        }
                        //No need to store dead units, so we only add soldier to temp_arr if it has hp > 0 
                        if (((Mage_Soldier)soldado).hp > 0){
                            temp_arr.add(soldado);
                        }  
                    }
                    else{
                        temp_arr.add(soldado);
                    }
                    
                }
                cpy.pieces = temp_arr;
                cpy.current_player = 1 - cpy.current_player;  // new state changes player
                moves.add(new Movement(Movement.moveCommand(this.x,this.y,possible_moves.x,possible_moves.y),cpy));
        }
        return moves;
     }

    @Override
    public String asciiRepresentation(){
        return "💂";
    }
    @Override
    public Piece clonePiece(){
        Soldier other = new Soldier(player,x,y,hp);
        return other;
    }
    public void update(){
        this.hp -= 1;
    }
}