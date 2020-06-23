package javaboard;

import java.util.List;
import java.util.Random;

// A general CPU that picks movements at random
public class PlayerCPURandom extends Player {

    // Random number generator
    Random rand;

    // Generate using a random seed
    public PlayerCPURandom(){
        rand = new Random();
        //Se guarda el nombre del juego si es  igual a CPURandom
        this.Nombre = "CPURandom"; 
    }

    // Generate using a given seed
    public PlayerCPURandom(int seed){
        rand = new Random(seed);
    }

    // Pick a movement at random
    @Override
    public Movement pickMovement(Game state, List<Movement> options, List<String> input_commands){
        assert(options.size()>0);
        int p = rand.nextInt(options.size());
        return options.get(p);
    }

    @Override
    public String victoryMessage(){
        return "ALL YOUR BASE ARE BELONG TO US!";
    }

}
