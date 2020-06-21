package javaboard;

import java.util.List;

/* A player, whetever it is human or CPU controlled is the entity that makes decisions
generally trying to win the game. */
public abstract class Player {
    //Player's name
    String Player_Name;

    // From a list of options pick one, given the current state.
    public abstract Movement pickMovement(Game state, List<Movement> options, List<String> input_commands);

    // String to be retrieved when this player archieves victory
    public abstract String victoryMessage();

}
