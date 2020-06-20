package simplewars;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;
public class Pares{
    int x;
    int y;
    int depth; // 0 <= depth <= 4
    public Pares(int p, int q, int depth){
        this.x = p;
        this.y = q;
        this.depth = depth;
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Pares){
            Pares p = (Pares) o;
            return p.x == this.x && p.y == this.y;
        }
        return false;
    }
}