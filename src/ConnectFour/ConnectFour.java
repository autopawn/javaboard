package ConnectFour;

import java.util.LinkedList;
import java.util.List;

import javaboard.Evaluable;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;


public class ConnectFour extends GridGame implements Evaluable{

    public ConnectFour(){
        size_x=7;
        size_y=6;
        //int temp= 0;
        pieces = new LinkedList<Piece>(); //no pude crear las piesas como movimientos validos, haci que improvise
        pieces.add(new piesa(1,-1,0));  //separados en un espacio para evitar que esten conectadas
        pieces.add(new piesa(1,-1,2));
        pieces.add(new piesa(1,-1,4));
        pieces.add(new piesa(1,-1,6));
        pieces.add(new piesa(1,-1,8));
        pieces.add(new piesa(1,-1,10));
        pieces.add(new piesa(1,-1,12));
        pieces.add(new piesa(1,-1,14));
        pieces.add(new piesa(1,-1,16));
        pieces.add(new piesa(1,-1,18));
        pieces.add(new piesa(1,-1,20));
        pieces.add(new piesa(1,-1,22));
        pieces.add(new piesa(1,-1,24));
        pieces.add(new piesa(1,-1,26));
        pieces.add(new piesa(1,-1,28));
        pieces.add(new piesa(1,-1,30));
        pieces.add(new piesa(1,-1,32));
        pieces.add(new piesa(1,-1,34));
        pieces.add(new piesa(1,-1,36));
        pieces.add(new piesa(1,-1,38));
        pieces.add(new piesa(1,-1,40));
        pieces.add(new piesa(1,-1,42));
        pieces.add(new piesa(1,-1,44));
        pieces.add(new piesa(1,-1,46));
        pieces.add(new piesa(1,-1,48));
        pieces.add(new piesa(1,-1,50));
        pieces.add(new piesa(1,-1,52));//me pase con las piesas 
        pieces.add(new piesa(1,-1,54));
        pieces.add(new piesa(1,-1,56));
        //otro jugador
        pieces.add(new piesa(0,-2,0));
        pieces.add(new piesa(0,-2,2));
        pieces.add(new piesa(0,-2,4));
        pieces.add(new piesa(0,-2,6));
        pieces.add(new piesa(0,-2,8));
        pieces.add(new piesa(0,-2,10));
        pieces.add(new piesa(0,-2,12));
        pieces.add(new piesa(0,-2,14));
        pieces.add(new piesa(0,-2,16));
        pieces.add(new piesa(0,-2,18));
        pieces.add(new piesa(0,-2,20));
        pieces.add(new piesa(0,-2,22));
        pieces.add(new piesa(0,-2,24));
        pieces.add(new piesa(0,-2,26));
        pieces.add(new piesa(0,-2,28));
        pieces.add(new piesa(0,-2,30));
        pieces.add(new piesa(0,-2,32));
        pieces.add(new piesa(0,-2,34));
        pieces.add(new piesa(0,-2,36));
        pieces.add(new piesa(0,-2,38));
        pieces.add(new piesa(0,-2,40));
        pieces.add(new piesa(0,-2,42));
        pieces.add(new piesa(0,-2,44));
        pieces.add(new piesa(0,-2,46));
        pieces.add(new piesa(0,-2,48));
        pieces.add(new piesa(0,-2,50));
        pieces.add(new piesa(0,-2,52));
        pieces.add(new piesa(0,-2,54));
        pieces.add(new piesa(0,-2,56));
        //
        

        //for(int ju=0; ju<2 ; ju++){
          //  for(int ve=0 ; ve < 28 ; ve++){
            //    pieces.add(new piesa(ju , temp - ju , ve));
            //}
        //}
    }



    // Clone the ConnectFour so that the clone is a ConnectFour instance
    @Override
    public Game cloneGame(){
        GridGame clone = new ConnectFour();
        clone.pieces = new LinkedList<Piece>();
        for(Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }

    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        //si un jugador conecta 4 gana
        //esto será un asco
        for(int jug=0;jug<2;jug++){
            for (Piece pc : pieces){
                int x=pc.x;
                int y=pc.y;
                if(pieceAt(x+1,y) !=null && pc.player == jug){
                    for(Piece a :pieces){   //horizontal
                        if(a.x == x+1 && a.y==y && a.player==jug && pieceAt(x+2,y) != null){
                            for(Piece b : pieces){
                                if(b.x == x+2 && b.y== y && b.player ==jug && pieceAt(x+3,y) !=null){
                                    for(Piece c:pieces){
                                        if(c.x == x+3 && c.y == y && c.player == jug){
                                            return jug;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
                if(pieceAt(x,y-1) !=null && pc.player ==jug){
                    for(Piece a :pieces){ // vertical
                        if(a.x == x && a.y==y-1 && a.player==jug && pieceAt(x,y-2) != null){
                            for(Piece b : pieces){
                                if(b.x == x && b.y== y-2 && b.player ==jug && pieceAt(x,y-3) !=null){
                                    for(Piece c:pieces){
                                        if(c.x == x && c.y-3 == y && c.player == jug){
                                            return jug;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
                if(pieceAt(x+1,y+1) != null && pc.player ==jug){
                    for(Piece a :pieces){ //diagonal bajando
                        if(a.x == x+1 && a.y==y+1 && a.player==jug && pieceAt(x+2,y+2) != null){
                            for(Piece b : pieces){
                                if(b.x == x+2 && b.y== y+2 && b.player ==jug && pieceAt(x+3,y+3) !=null){
                                    for(Piece c:pieces){
                                        if(c.x == x+3 && c.y+3 == y && c.player == jug){
                                            return jug;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
                if(pieceAt(x+1,y-1) !=null && pc.player ==jug){
                    for(Piece a :pieces){ //diagonal subiendo
                        if(a.x == x+1 && a.y==y-1 && a.player==jug && pieceAt(x+2,y-2) != null){
                            for(Piece b : pieces){
                                if(b.x == x+2 && b.y== y-2 && b.player ==jug && pieceAt(x+3,y-3) !=null){
                                    for(Piece c:pieces){
                                        if(c.x == x+3 && c.y-3 == y && c.player == jug){
                                            return jug;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        
        Integer result = super.currentWinner(current_player_moves);
        return result;
    }

   // Default evaluation function, so that PlayerCPUEval works for ConnectFour
    @Override
    public float defaultEvaluationFunction(){
        
        // How good is the state for the fox:
        float eval = 0;
        // Get the fox and how near it is to the other side of the board
        Piece fox = null;
        for(Piece pc : pieces){
            if(pc.player==0) fox = pc;
        }
        eval += 7-fox.y;
        // Hound position
        for(Piece pc : pieces){
            if(pc.player==1){
                if(pc.y >= fox.y){
                    // It is very good for the fox if is has passed by the hounds
                    eval += 0.5;
                }else{
                    // It is better for the fox if the hounds are far in x
                    eval += 0.05*Math.abs(pc.x-fox.x);
                }
            }
        }
        // Negate eval if the current player is not the fox
        if(current_player!=0) eval = -eval;
        // Return eval
        
        return eval;
    }
}