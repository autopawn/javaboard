package ConnectFour;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;



//por alguna rara razon no me deja crear el archivo pieza.java asi que improbise.
public class piesa extends Piece {
    
    
    // The string representation for a movement command
    public static String moveCommand( int xx){
        char xxn = (char)('a'+xx);
        return ""+xxn;
    }
    
    public piesa(int player, int x,int y){ 
        super(player,x,y); // Call parent constructor    
    }
   
    @Override
    public List<Movement> getMovements(Game state){
        // Implicit cast state to use GridGame methods
        GridGame grid = (GridGame) state;

        // Movement list
        List<Movement> moves = new LinkedList<Movement>();

        int[] altura = {5,4,3,2,1,0};
        int[] ancho ={0,1,2,3,4,5,6};
        //Game cpy = grid.cloneGame();

        //int jugO = 0;     
        //int jugL = 0;  
        for(int an : ancho){
            if(!grid.isInside(x,1)){
                for (int al : altura){
                    if(grid.pieceAt(an,al) == null){
                        if(player==0){
                            Game cpy=grid.cloneGame();
                            //piece.add(new piesa(0,an,al));
                            cpy.movePiece(-2, y, an, al);
                            
                            
                            moves.add(new Movement (moveCommand(an),cpy));
                            cpy.current_player= 1-cpy.current_player;
                            //jugO+=2;
                            break;
                        }
                        else{
                            Game cpy=grid.cloneGame();
                            cpy.movePiece(-1, y, an, al);
                            
                            
                            moves.add(new Movement (moveCommand(an),cpy));
                            cpy.current_player= 1-cpy.current_player;
                            //jugL +=2;
                            break;
                        }
                    }
                }
            }
        }   
        
        return moves;
    }

    @Override
    public String asciiRepresentation(){
        if(player==0){
            return "🔴 "; //🔴 
        }
        if(player==1){
            return "🔵 "; //🔵
        }
        return "??";
    }

    // Clone the piesa so that the clone is a piesa instance
    @Override
    public Piece clonePiece(){
        piesa other = new piesa(player,x,y);
        return other;
    }
}
