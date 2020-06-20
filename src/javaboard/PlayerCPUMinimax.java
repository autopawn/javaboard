package javaboard;

import java.util.List;

public class PlayerCPUMinimax extends Player {
    //Pickmovement for Minimax, and initialize of variables
    @Override
    public Movement pickMovement(Game state, List<Movement> options, List<String> input_commands) {
        assert(options.size()>0);
        float highestvalue = Integer.MIN_VALUE;
        float lowestvalue = Integer.MIN_VALUE;
        float move = minimax(4, lowestvalue,highestvalue,true, options,state,null);
        Movement tempmov = null;
        for(Movement mov : options){ //compare all the childs in options whith the best movement returned by minimax
            Evaluable resulting_state = (Evaluable) mov.result;
            float tempEval = -resulting_state.defaultEvaluationFunction();
            if(tempEval == -move){
                return mov;
            }
        }
        return tempmov;
    }
    //Victory message for Minimax AI
    @Override
    public String victoryMessage(){
        return "AI Will Rule the world!.";
    }
    //minimax Algorithm, is a recursive AI algorithm to choose the best movement
    private float minimax(int depth, float alpha, float beta, boolean Mplayer, List<Movement> options, Game state, Movement moveItself){
        //when depth = 0, the Movement is evaluated
        if (depth == 0){
            float eval = evaluator(moveItself);
            return eval;
        }
        //Maximizing part of the algorithm Minimax, this maximize the Movement of the player.
        if(Mplayer){
            int my_player = state.current_player;
            float highestvalue = Integer.MIN_VALUE;
            float fathermov = Integer.MAX_VALUE;
            for(Movement mov : options){
                Game stateup = updateState(mov); //update the state with te movement
                List<Movement> ChildMovs = updateMovs(state); //generate the children of the movement
                for(Movement movchild : ChildMovs){ 
                    float evaluate = minimax(depth-1, alpha, beta, false, ChildMovs, stateup, movchild); //Minimax fot the children, but the minimizing part
                    if(my_player != movchild.result.current_player){
                        evaluate = -evaluate;
                    }
                    if(highestvalue <= evaluate){
                        highestvalue = evaluate;
                        if(fathermov >= evaluator(mov)){ //keep the best option and his Movement father
                            fathermov = evaluator(mov);
                        }
                        
                    }
                }
            }
            return fathermov;
        }
        //Minimizing part if the algorithm, this minimize the worst value of Movement
        else{
            int my_player = state.current_player;
            float lowestvalue = Integer.MAX_VALUE;
            float fathermov = Integer.MAX_VALUE;
            for(Movement mov : options){
                Game stateup = updateState(mov); //update the state with te movement
                List<Movement> ChildMovs = updateMovs(state); //generate the children of the movement, but the maximizing part
                for(Movement movchild : ChildMovs){
                    float evaluate = minimax(depth-1, alpha, beta, true, ChildMovs, stateup, movchild); //Minimax fot the children
                    if(my_player != movchild.result.current_player){
                        evaluate = -evaluate;
                    }
                    if(lowestvalue >= evaluate){
                        lowestvalue = evaluate;
                        if(fathermov >= evaluator(mov)){ //keep the best option and his Movement father
                            fathermov = evaluator(mov);
                        }
                    }
                }
            }
            return fathermov;
        }
    }
    //return the list of movements (or childrens Movements), of the state
    private List<Movement> updateMovs(Game state) {
        List<Movement> moves = state.getMovements();
        return moves;  
    }
    //update the state with the given movement
    private Game updateState(Movement mov){
        Game state = mov.result;
        return state;
    }
    //evaluate the movement
    private float evaluator(Movement mov){
        Evaluable resulting_state = (Evaluable) mov.result;
        float eval = resulting_state.defaultEvaluationFunction();
        return eval;

    }
}
