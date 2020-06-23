package javaboard;
import java.io.*;

public interface Evaluable{

    // Interface intended for Games that have a default evaluation function.
    // The evaluation function should retrieve greater values if the state is believed to be more favourable for the current_player
    public abstract float defaultEvaluationFunction();
}
