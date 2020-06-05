package javaboard;

import java.util.List;
import java.util.Random;

public class PlayerCPURandom extends Player {

    Random rand;

    public PlayerCPURandom(){
        rand = new Random();
    }

    public PlayerCPURandom(int seed){
        rand = new Random(seed);
    }

    @Override
    public Movement pickMovement(List<Movement> options, List<String> input_commands){
        assert(options.size()>0);
        int p = rand.nextInt(options.size());
        return options.get(p);
    }

    @Override
    public String victoryMessage(){
        return "ALL YOUR BASE ARE BELONG TO US!";
    }

}
