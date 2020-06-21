package simplewars;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javaboard.*;

public class Simplewars extends GridGame implements Evaluable{
    public Simplewars(){
        size_x = 15;
        size_y = 10;
        pieces = new LinkedList<Piece>();
        //Agregamos las piezas de los 2 jugadores en posiciones aleaotorias y null
        for(int pla = 0; pla < 2; pla++){
            for(int i = 0; i < 4; i++){
                Random rand = new Random();
                int posX = rand.nextInt(15);
                int posY = rand.nextInt(10);
                if(pieceAt(posX,posY) == null){
                    pieces.add(new Soldier( pla, posX, posY, 3));
                }
            }        
        }
    }

    //Funcion para remover de la lista a piezas con HP menor o igual a 0
    public void removerMuertos(){
        Iterator<Piece> itr = pieces.iterator();
        while(itr.hasNext()){
            Soldier muerto = (Soldier) itr.next();
            if(muerto.health <= 0){
                itr.remove();
            }
        }
    }


    //Funcion para atacar a piezas enemigas adyacentes a una tuya
    public void damage(int x, int y){
        if(pieceAt(x+1, y)!=null){
            Piece un = pieceAt(x+1,y);
            Soldier unidad = (Soldier) un;
            if(unidad.player != current_player){
                unidad.health--;
            }
        }
        if(pieceAt(x-1, y)!=null){
            Piece un = pieceAt(x-1,y);
            Soldier unidad = (Soldier) un;
            if(unidad.player != current_player){
                unidad.health--;
            }
        }
        if(pieceAt(x, y+1)!=null){
            Piece un = pieceAt(x,y+1);
            Soldier unidad = (Soldier) un;
            if(unidad.player != current_player){
                unidad.health--;
            }
        }
        if(pieceAt(x, y-1)!=null){
            Piece un = pieceAt(x,y-1);
            Soldier unidad = (Soldier) un;
            if(unidad.player != current_player){
                unidad.health--;
            }
        }
        removerMuertos();  
    }


    @Override
    public Game cloneGame() {
        GridGame clone = new Simplewars();
        clone.pieces =  new LinkedList<Piece>();
        for (Piece pc : pieces){
            clone.pieces.add(pc.clonePiece());
        }
        clone.current_player = current_player;
        clone.size_x = size_x;
        clone.size_y = size_y;
        return clone;
    }


    @Override
    public Integer currentWinner(List<Movement> current_player_moves){
        if(current_player_moves.size()==0){
            assert(current_player==0 || current_player==1);
            return 0;
        }
        Integer result = super.currentWinner(current_player_moves);
        return result;
    }


    @Override
    public float defaultEvaluationFunction() {
        
        float eval = 0;
        int hpCurrent = 0;
        int hpEnemy = 0;

        for(Piece pc : pieces){
            if(pc.player == current_player){
                hpCurrent += ((Soldier)pc).health;
            }
            else{
                hpEnemy += ((Soldier)pc).health;
            }
        }
        eval = hpCurrent - hpEnemy;
        return eval;
    }
}