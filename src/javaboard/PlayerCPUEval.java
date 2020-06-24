package javaboard;

import java.util.List;

/* A simple general AI that just picks the option that has a better evaluation function. */
public class PlayerCPUEval extends Player {

    public PlayerCPUEval(){
        this.player_name="PlayerCPUEval";//siempre tendra este nombre un jugador de este tipo
    }
    

    // Pick a movement using the evaluation function
    @Override
    public Movement pickMovement(Game state, List<Movement> options, List<String> input_commands){
        assert(options.size()>0);

        int my_player = state.current_player;

        // Pick best movement
        float best_eval = 0;
        Movement best_option = null;

        for(Movement mov : options){
            Evaluable resulting_state = (Evaluable) mov.result;

            // Get evaluation of the resulting state
            float eval = resulting_state.defaultEvaluationFunction();

            // Opponent's gain is loss
            if(my_player != mov.result.current_player){
                eval = -resulting_state.defaultEvaluationFunction();
            }

            if(best_option==null || eval>best_eval){
                best_eval   = eval;
                best_option = mov;
            }
        }

        return best_option;
    }

    @Override
    public String victoryMessage(){
        return "Every puzzle has an answer.";
    }

}
