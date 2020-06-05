package javaboard;

public class Movement {
    public String command;
    public Game result;

    public Movement(String command, Game result){
        this.command = command;
        this.result  = result;
    }

    public static String moveCommand(int x, int y, int xx, int yy){
        char xn  = (char)('a'+x);
        char xxn = (char)('a'+xx);
        return ""+xn+y+xxn+yy;
    }

}
