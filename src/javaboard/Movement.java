package javaboard;

// A movement is a String command associated to a new resulting game state
public class Movement {
    public String command;
    public Game result;

    // Movement constructor
    public Movement(String command, Game result){
        this.command = command;
        this.result  = result;
    }

    // The string representation for a movement command
    public static String moveCommand(int x, int y, int xx, int yy){
        char xn  = (char)('a'+x);
        char xxn = (char)('a'+xx);
        return ""+xn+y+xxn+yy;
    }

}
