package simplewars;

import java.util.LinkedList;
import java.util.List;

import javaboard.*;

public class Soldier extends Piece{
    public int health;

    public Soldier(int player, int x, int y, int vida) {
        super(player,x,y);
        health = vida;
    }

    @Override
    public List<Movement> getMovements(Game state) {
        GridGame grid = (GridGame) state;
        List<Movement> moves = new LinkedList<Movement>();
        int move_max = 4;
        int[] dys = {-4,-3,-2,-1,0,1,2,3,4};
        for(int dy : dys){
            int[] dxs = {-4,-3,-2,-1,0,1,2,3,4};
            for(int dx : dxs){
                int xx = x + dx;
                int yy = y + dy;
                if(grid.isInside(xx, yy) && grid.pieceAt(xx, yy)==null && (Math.abs(dx) + Math.abs(dy)) <= move_max)
                {
                    Simplewars cpy = (Simplewars) grid.cloneGame();
                    
                    cpy.movePiece(x, y, xx, yy);
                    cpy.damage(xx,yy);
                    cpy.current_player = 1 - cpy.current_player;
                    
                    moves.add(new Movement(Movement.moveCommand(x, y, xx, yy),cpy));
                }
            }
        }
        if(health > 0){
            if(player == 0){
                System.out.println("ⓢ  HP: " + ColorLibrary.RED + health + ColorLibrary.RESET); 
            }
            if(player == 1){
                System.out.println("🅢  HP: "+ ColorLibrary.RED + health + ColorLibrary.RESET);
            }
        } 
        return moves;
    }
    
    
    @Override
    public String asciiRepresentation(){
        if(player == 0){
            return "ⓢ ";
        }
        else{
            return "🅢 ";
        }
    }

    @Override
    public Piece clonePiece(){
        Soldier unit = new Soldier(player,x,y,health);
        return unit; 
    }
}
