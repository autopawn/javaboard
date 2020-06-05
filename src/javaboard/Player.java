package javaboard;

import java.util.List;

public abstract class Player {

    public abstract Movement pickMovement(List<Movement> options, List<String> input_commands);

    public abstract String victoryMessage();

}
