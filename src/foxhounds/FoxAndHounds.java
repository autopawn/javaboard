package foxhounds;

import java.util.LinkedList;
import java.util.List;

import javaboard.Evaluable;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

/*
FoxAndHounds is a game where player 0, that controls a Fox, tries to reach
the other side of the board; while player 1, that controls 4 hounds, tries
to stop him.
The fox can move diagonally forward and backwards, the hounds can only move forward.
There is no eating.
*/
public class FoxAndHounds extends GridGame implements Evaluable {

    public FoxAndHounds(){
        size_x = 8;
        size_y = 8;
        pieces = new LinkedList<Piece>();
        pieces.add(new Hound(1,1,0));
        pieces.add(new Hound(1,3,0));
        pieces.add(new Hound(1,5,0));
        pieces.add(new Hound(1,7,0));
        pieces.add(new Fox(0,0,7));
    }

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        // If the fox reaches the top side of the board, player 0 wins
        for(Piece pc : pieces){
            if(pc.player==0 && pc.y==0) return 0;
        }
        // Test default win condition
        Integer result = super.currentWinner(current_player_moves);
        return result;
    }

    // Clone the FoxAndHounds so that the clone is a FoxAndHounds instance
    @Override
    public Game cloneGame(){
        GridGame clone = new FoxAndHounds();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }

    // Default evaluation function, so that PlayerCPUEval works for FoxAndHounds
    @Override
    public float defaultEvaluationFunction(){
        // How good is the state for the fox:
        float eval = 0;

        /*new array that contents first the piece fox, and after the posible movementes
        in breath first of the fox*/ 
        List<Piece> nodes = new LinkedList<Piece>();

        //indicate when the fox comes the goal
        boolean goal = false; 

        //search for the piece fox to add to the nodes
        for(Piece pc : pieces){
            if(pc.player==0) {
                nodes.add(pc);
            }
        }

        // Breadth First Search handmade
        while (goal == false){

            //copy the pieces in the order of tree level
            List<Piece> nodes_copy = new LinkedList<Piece>(nodes);
            //remove the posible movements of the tree level
            nodes.clear();
            //one step is +1 points to eval
            eval += 1;

            //traverse the tree level
            for (Piece nod : nodes_copy){

                //Sure that the fox has movemets to the right forward side
                if (nod.x <= 6){
                    //List add one posible movement diagonal forward, the backward let the eval worse
                    nodes.add(new Fox(0,nod.x+1,nod.y-1));
                
                    //flaks the hounds nows that this position is unreachable
                    for(Piece pcr : pieces){
                        //find a hound!!
                        if(pcr.player==1){
                            if((pcr.x == nod.x+1)&&(pcr.y == nod.y-1)){
                                //if the position is unreachable the node is remove of the list
                                nodes.remove(nodes.size()-1);
                            }
                        }
                    }
                }

                //Sure that the fox has movemets to the left forward side
                if (nod.x >= 1){
                    nodes.add(new Fox(0,nod.x-1,nod.y-1));
                
                    //flaks the hounds nows that this position is unreachable
                    for(Piece pcl : pieces){
                        //find a hound!!
                        if(pcl.player==1){
                            if((pcl.x == nod.x-1)&&(pcl.y == nod.y-1)){
                                //if the position is unreachable the node is remove of the list
                                nodes.remove(nodes.size()-1);
                            }
                        }
                    }
                }

                //determine if the fox reached the goal
                for (Piece nod1 : nodes){
                    if (nod1.y == 0){
                        goal = true;
                    }
                }
            }
            ;
            //if nodes is empty means that the hounds are blocked the way to the goal 
            if (nodes.isEmpty()){ 
                eval += 1; //this increases the value of eval since the target is unreachable
                break;
            }    
        }

        // Negate eval if the current player is the fox
        if(current_player==0) eval = -eval;
        // Return -eval
        return eval;
    }

}