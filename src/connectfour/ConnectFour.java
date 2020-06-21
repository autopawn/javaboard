package connectfour;

import java.util.LinkedList;
import java.util.List;

import javaboard.Evaluable;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class ConnectFour extends GridGame implements Evaluable {

    public ConnectFour(){
        size_x = 7;
        size_y = 6;
        pieces = new LinkedList<Piece>();
        for(int i=0; i<2; i++){
            int n;
            if(i==0){
                n=-1;
            }
            else{
                n=-2;
            }
            for(int j=1; j<22; j += 2){
                pieces.add(new Pieza(i, n, j));
            }
        }
    }

    @Override
    public float defaultEvaluationFunction() {
        return 0;
    }

    @Override
    public Game cloneGame() {
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
    public List<Movement> getMovements(){//modificado para que solo muestre los movimientos de una ficha
        List<Movement> moves = new LinkedList<Movement>();
        int cont = 0;
        for(Piece pc : pieces){
            if(pc.player != current_player) continue;
            List<Movement> piece_moves = pc.getMovements(this);
            for(Movement mov : piece_moves) {
                if(!mov.result.isValid()) continue;
                moves.add(mov);
                cont += 1;
            }
            if(cont == 7) break;
        }
        return moves;
    }

    @Override
    public Integer currentWinner(List<Movement> current_playerPieces) {
        /*aqui comienza la cordillera del currentwinner, por alguna razon el for no funcionaba de forma correcta y hacia que
        el jugador ganara con solo 2 fichas asi que tuve que crear la cordillera para solucionarlo*/
        // El primer jugador que conecta 4 gana
        for(Piece pc : pieces){
            int x = pc.x;
            int y= pc.y;
            for(int i=0; i<2; i++){
                if(pc.player == i){

                    if(pieceAt(x+1, y-1) != null){
                        for(Piece pv : pieces){
                            if(pv.x == x+1 && pv.y == y-1 && pv.player == i){
                                x = pv.x;
                                y = pv.y;
                                if(pieceAt(x+1, y-1) != null){
                                    for(Piece pb : pieces){
                                        if(pb.x == x+1 && pb.y == y-1 && pb.player == i){
                                            x = pb.x;
                                            y = pb.y;
                                            if(pieceAt(x+1, y-1) != null){
                                                for(Piece pn : pieces){
                                                    if(pn.x == x+1 && pn.y == y-1 && pn.player == i){
                                                        Integer result = i;
                                                        return result;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(pieceAt(x, y+1) != null){
                        for(Piece pv : pieces){
                            if(pv.x == x && pv.y == y+1 && pv.player == i){
                                x = pv.x;
                                y = pv.y;
                                if(pieceAt(x, y+1) != null){
                                    for(Piece pb : pieces){
                                        if(pb.x == x && pb.y == y+1 && pb.player == i){
                                            x = pb.x;
                                            y = pb.y;
                                            if(pieceAt(x, y+1) != null){
                                                for(Piece pn : pieces){
                                                    if(pn.x == x && pn.y == y+1 && pn.player == i){  
                                                        Integer result = i;
                                                        return result;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(pieceAt(x+1, y) != null){
                        for(Piece pv : pieces){
                            if(pv.x == x+1 && pv.y == y && pv.player == i){
                                x = pv.x;
                                y = pv.y;
                                if(pieceAt(x+1, y) != null){
                                    for(Piece pb : pieces){
                                        if(pb.x == x+1 && pb.y == y && pb.player == i){
                                            x = pb.x;
                                            y = pb.y;
                                            if(pieceAt(x+1, y) != null){
                                                for(Piece pn : pieces){
                                                    if(pn.x == x+1 && pn.y == y && pn.player == i){
                                                        Integer result = i;
                                                        return result;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(pieceAt(x-1, y-1) != null){
                        for(Piece pv : pieces){
                            if(pv.x == x-1 && pv.y == y-1 && pv.player == i){
                                x = pv.x;
                                y = pv.y;
                                if(pieceAt(x-1, y-1) != null){
                                    for(Piece pb : pieces){
                                        if(pb.x == x-1 && pb.y == y-1 && pb.player == i){
                                            x = pb.x;
                                            y = pb.y;
                                            if(pieceAt(x-1, y-1) != null){
                                                for(Piece pn : pieces){
                                                    if(pn.x == x-1 && pn.y == y-1 && pn.player == i){
                                                        Integer result = i;
                                                        return result;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
}
