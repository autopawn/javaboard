package othello;

import java.util.LinkedList;
import java.util.List;

import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;
import javaboard.Piece;

public class OthelloPiece extends Piece{

    public OthelloPiece(int player, int x, int y){
        super(player,x,y);
    }

    // A Fox can move diagonally forward and backwards
    @Override
    public List<Movement> getMovements(Game state){
        
        GridGame grid = (GridGame) state;
        List<Movement> moves = new LinkedList<Movement>();
        //Almacena las piezas que se deben cambiar de color
        List<Piece> exchange =new LinkedList<Piece>();
        int xxi = 0;
        int yyj = 0;
        boolean equal = false;
        int[] dys = {-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7};
        for(int dy : dys){
            int[] dxs = {-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7};
            for(int dx :dxs){
                int xx = x + dx;
                int yy = y + dy;
                // Check inside bounds and that there is not piece
                if(grid.isInside(xx,yy) && grid.pieceAt(xx,yy)==null){

                    //clonar el juego
                    Game cpy = grid.cloneGame();                    
                    cpy.current_player = 1 - grid.current_player; 

                    //Se revisa si alrededor del posible movimiento se encuentra una pieza del color contrario.
                    for (int i = -1; i < 2; i++){
                        for (int j = -1; j < 2; j++){
                            if (i == 0 && j == 0) continue;
                            xxi = xx+i;
                            yyj = yy+j;
                            if (grid.isInside(xxi, yyj) && grid.pieceAt(xxi,yyj) != null && grid.pieceAt(xxi, yyj).player == 1 - grid.current_player){ 
                                //Busca hacia la derecha
                                if(xxi > xx && yyj == yy){
                                    //Guarda la pieza que se encuentra al lado del posible movimiento
                                    exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj));
                                    for(int der = 1; der < grid.size_x; der++){
                                        if(grid.isInside(xxi+der,yyj) && grid.pieceAt(xxi+der,yyj)!=null && grid.pieceAt(xxi+der,yyj).player == 1-grid.current_player){
                                            //Si la pieza es del color contrario al jugador actual se almacena para cambiarla de color
                                            exchange.add(new OthelloPiece(cpy.current_player,xxi+der,yyj));
                                        }
                                        //Si se encuentra una pieza del color del jugador se mueve la pieza y se agrega una nueva a la posición anterior del mismo color del jugador actual
                                        if(grid.isInside((xxi+der),yyj) && grid.pieceAt((xxi+der),yyj)!=null && grid.pieceAt((xxi+der),yyj).player == grid.current_player){
                                            cpy.movePiece(x,y,xx,yy);
                                            cpy.pieces.add(new OthelloPiece(grid.current_player,x,y));
                                            //Se buscan las piezas en el tablero que se deben cambiar de color 
                                            for(Piece pc : exchange){
                                                for(Piece pc1 : cpy.pieces){
                                                    if(pc1.player == pc.player && pc1.x == pc.x && pc1.y == pc.y){
                                                        cpy.pieces.remove(pc1);
                                                        cpy.pieces.add(new OthelloPiece(grid.current_player,pc.x,pc.y));
                                                        break;
                                                    }
                                                }
                                            }
                                            //Si el movimiento ya se encuentra almacenado en la lista de movimientos no se vuelve a insertar
                                            for(Movement move: moves){
                                                if(move.command.equals(Movement.moveCommand(x,y,xx,yy))){
                                                    equal = true;
                                                    break;
                                                }
                                            }
                                            if(!equal){
                                                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                                                exchange.clear();
                                                break;
                                            }
                                            equal = false;
                                            exchange.clear();
                                            break; 
                                        }
                                        //Cuando existe un espacio vacío en el tablero el movimiento no es válido
                                        if(grid.pieceAt(xxi+der,yyj) == null){
                                            exchange.clear();
                                            break;
                                        }
                                    }
                                    exchange.clear();
                                }
                                //Busca hacia la izquierda
                                else if(xxi < xx && (yyj) == yy){
                                    exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj));
                                    for(int izq = 1; izq < grid.size_x; izq++){
                                        if(grid.isInside(xxi-izq, yyj) && grid.pieceAt(xxi-izq,yyj)!=null && grid.pieceAt(xxi-izq,yyj).player == 1-grid.current_player){
                                            exchange.add(new OthelloPiece(cpy.current_player,xxi-izq,yyj));
                                        }
                                        if(grid.isInside(xxi-izq, yyj) && grid.pieceAt(xxi-izq,yyj)!=null && grid.pieceAt(xxi-izq,yyj).player == grid.current_player){
                                            cpy.movePiece(x,y,xx,yy);
                                            cpy.pieces.add(new OthelloPiece(grid.current_player,x,y));
                                            for(Piece pc : exchange){
                                                for(Piece pc1 : cpy.pieces){
                                                    if(pc1.player == pc.player && pc1.x == pc.x && pc1.y == pc.y){
                                                        cpy.pieces.remove(pc1);
                                                        cpy.pieces.add(new OthelloPiece(grid.current_player,pc.x,pc.y));
                                                        break; 
                                                    }
                                                }
                                            }
                                            for(Movement move: moves){
                                                if(move.command.equals(Movement.moveCommand(x,y,xx,yy))){
                                                    equal = true;
                                                    break;
                                                }
                                            }
                                            if(!equal){
                                                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                                                exchange.clear();
                                                break;
                                            }
                                            equal = false;
                                            exchange.clear();
                                            break;     
                                        }
                                        if(grid.pieceAt(xxi-izq,yyj) == null){
                                            exchange.clear();
                                            break;
                                        }
                                    }
                                    exchange.clear();
                                }
                                //Busca hacia arriba
                                else if((yyj < yy && xxi == xx)){
                                    exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj));
                                    for(int arriba = 1; arriba < grid.size_x; arriba++){
                                        if(grid.isInside(xxi,yyj-arriba) && grid.pieceAt(xxi,yyj-arriba)!=null && grid.pieceAt(xxi,yyj-arriba).player == 1-grid.current_player){
                                            exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj-arriba));
                                        }
                                        if(grid.isInside(xxi,yyj-arriba) && grid.pieceAt(xxi,yyj-arriba)!=null && grid.pieceAt(xxi,yyj-arriba).player == grid.current_player){
                                            cpy.movePiece(x,y,xx,yy);
                                            cpy.pieces.add(new OthelloPiece(grid.current_player,x,y));
                                            for(Piece pc : exchange){
                                                for(Piece pc1 : cpy.pieces){
                                                    if(pc1.player == pc.player && pc1.x == pc.x && pc1.y == pc.y){
                                                        cpy.pieces.remove(pc1);
                                                        cpy.pieces.add(new OthelloPiece(grid.current_player,pc.x,pc.y));
                                                        break;   
                                                    }
                                                }
                                            }
                                            for(Movement move: moves){
                                                if(move.command.equals(Movement.moveCommand(x,y,xx,yy))){
                                                    equal = true;
                                                    break;
                                                }
                                            }
                                            if(!equal){
                                                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                                                exchange.clear();
                                                break;
                                            }
                                            equal = false;
                                            exchange.clear();
                                            break; 
                                        }
                                        if(grid.pieceAt(xxi,yyj-arriba) == null){
                                            exchange.clear();
                                            break;
                                        }
                                    }
                                    exchange.clear();
                                }
                                //Busca hacia abajo
                                else if((yyj > yy && xxi == xx)){
                                    exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj));
                                    for(int abajo = 1; abajo < grid.size_x; abajo++){
                                        if(grid.isInside(xxi, yyj+abajo) && grid.pieceAt(xxi,yyj+abajo)!=null && grid.pieceAt(xxi,yyj+abajo).player == 1-grid.current_player){
                                            exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj+abajo));
                                        }
                                        if(grid.isInside(xxi,yyj+abajo) && grid.pieceAt(xxi,yyj+abajo)!=null && grid.pieceAt(xxi,yyj+abajo).player == grid.current_player){
                                            cpy.movePiece(x,y,xx,yy);
                                            cpy.pieces.add(new OthelloPiece(grid.current_player,x,y));
                                            for(Piece pc : exchange){
                                                for(Piece pc1 : cpy.pieces){
                                                    if(pc1.player == pc.player && pc1.x == pc.x && pc1.y == pc.y){
                                                        cpy.pieces.remove(pc1);
                                                        cpy.pieces.add(new OthelloPiece(grid.current_player,pc.x,pc.y));
                                                        break;     
                                                    }
                                                }
                                            }
                                            for(Movement move: moves){
                                                if(move.command.equals(Movement.moveCommand(x,y,xx,yy))){
                                                    equal = true;
                                                    break;
                                                }
                                            }
                                            if(!equal){
                                                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                                                exchange.clear();
                                                break;
                                            }
                                            equal = false;
                                            exchange.clear();
                                            break; 
                                        }
                                        if(grid.pieceAt(xxi,yyj+abajo) == null){
                                            exchange.clear();
                                            break;
                                        }
                                    }
                                    exchange.clear();
                                }
                                //Busca en diagonal arriba a la derecha
                                else if((xxi > xx && yyj < yy)){
                                    exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj));
                                    for(int ard = 1; ard < grid.size_x; ard++){
                                        if(grid.isInside(xxi+ard,yyj-ard) && grid.pieceAt(xxi+ard,yyj-ard)!=null && grid.pieceAt(xxi+ard,yyj-ard).player == 1-grid.current_player){
                                            exchange.add(new OthelloPiece(cpy.current_player,xxi+ard,yyj-ard));
                                        }
                                        if(grid.isInside(xxi+ard,yyj-ard) && grid.pieceAt(xxi+ard,yyj-ard)!=null && grid.pieceAt(xxi+ard,yyj-ard).player == grid.current_player){
                                            cpy.movePiece(x,y,xx,yy);
                                            cpy.pieces.add(new OthelloPiece(grid.current_player,x,y));
                                            for(Piece pc : exchange){
                                                for(Piece pc1 : cpy.pieces){
                                                    if(pc1.player == pc.player && pc1.x == pc.x && pc1.y == pc.y){
                                                        cpy.pieces.remove(pc1);
                                                        cpy.pieces.add(new OthelloPiece(grid.current_player,pc.x,pc.y));
                                                        break;
                                                    }
                                                }
                                            }
                                            for(Movement move: moves){
                                                if(move.command.equals(Movement.moveCommand(x,y,xx,yy))){
                                                    equal = true;
                                                    break;
                                                }
                                            }
                                            if(!equal){
                                                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                                                exchange.clear();
                                                break;
                                            }
                                            equal = false;
                                            exchange.clear();
                                            break; 
                                        }
                                        if(grid.pieceAt(xxi+ard,yyj-ard) == null){
                                            exchange.clear();
                                            break;
                                        }
                                    }
                                    exchange.clear();
                                }
                                //Busca en diagonal arriba a la izquierda
                                else if((xxi < xx && yyj < yy)){
                                    exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj));
                                    for(int  ari = 1; ari < grid.size_x; ari++){
                                        if(grid.isInside(xxi-ari, yyj-ari) && grid.pieceAt(xxi-ari,yyj-ari)!=null && grid.pieceAt(xxi-ari,yyj-ari).player == 1-grid.current_player){
                                            exchange.add(new OthelloPiece(cpy.current_player,xxi-ari,yyj-ari));
                                        }
                                        if(grid.isInside(xxi-ari,yyj-ari) && grid.pieceAt(xxi-ari,yyj-ari)!=null && grid.pieceAt(xxi-ari,yyj-ari).player == grid.current_player){
                                            cpy.movePiece(x,y,xx,yy);
                                            cpy.pieces.add(new OthelloPiece(grid.current_player,x,y));
                                            for(Piece pc : exchange){
                                                for(Piece pc1 : cpy.pieces){
                                                    if(pc1.player == pc.player && pc1.x == pc.x && pc1.y == pc.y){
                                                        cpy.pieces.remove(pc1);
                                                        cpy.pieces.add(new OthelloPiece(grid.current_player,pc.x,pc.y));
                                                        break;
                                                    }
                                                }
                                            }
                                            for(Movement move: moves){
                                                if(move.command.equals(Movement.moveCommand(x,y,xx,yy))){
                                                    equal = true;
                                                    break;
                                                }
                                            }
                                            if(!equal){
                                                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                                                exchange.clear();
                                                break;
                                            }
                                            equal = false;
                                            exchange.clear();
                                            break; 
                                        }
                                        if(grid.pieceAt(xxi-ari,yyj-ari) == null){
                                            exchange.clear();
                                            break;
                                        }
                                    }
                                    exchange.clear();
                                }
                                //Busca diagonal abajo a la derecha
                                else if((xxi > xx && yyj > yy)){
                                    exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj));
                                    for(int abd = 1; abd < grid.size_x; abd++){
                                        if(grid.isInside(xxi+abd, yyj+abd) && grid.pieceAt(xxi+abd,yyj+abd)!=null && grid.pieceAt(xxi+abd,yyj+abd).player == 1-grid.current_player){
                                            exchange.add(new OthelloPiece(cpy.current_player,xxi+abd,yyj+abd));
                                        }
                                        if(grid.isInside(xxi+abd,yyj+abd) && grid.pieceAt(xxi+abd,yyj+abd)!=null && grid.pieceAt(xxi+abd,yyj+abd).player == grid.current_player){
                                            cpy.movePiece(x,y,xx,yy);
                                            cpy.pieces.add(new OthelloPiece(grid.current_player,x,y));
                                            for(Piece pc : exchange){
                                                for(Piece pc1 : cpy.pieces){
                                                    if(pc1.player == pc.player && pc1.x == pc.x && pc1.y == pc.y){
                                                        cpy.pieces.remove(pc1);
                                                        cpy.pieces.add(new OthelloPiece(grid.current_player,pc.x,pc.y));
                                                        break;
                                                    }
                                                }
                                            }
                                            for(Movement move: moves){
                                                if(move.command.equals(Movement.moveCommand(x,y,xx,yy))){
                                                    equal = true;
                                                    break;
                                                }
                                            }
                                            if(!equal){
                                                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                                                exchange.clear();
                                                break;
                                            }
                                            equal = false;
                                            exchange.clear();
                                            break;  
                                        }
                                        if(grid.pieceAt(xxi+abd,yyj+abd) == null){
                                            exchange.clear();
                                            break;
                                        }
                                    }
                                    exchange.clear();
                                }
                                //Busca diagonal abajo a la izquierda
                                else if((xxi < xx && yyj > yy)){
                                    exchange.add(new OthelloPiece(cpy.current_player,xxi,yyj));
                                    for(int abi = 1; abi < grid.size_x; abi++){
                                        if(grid.isInside(xxi-abi, yyj+abi) && grid.pieceAt(xxi-abi,yyj+abi)!=null && grid.pieceAt(xxi-abi,yyj+abi).player == 1-grid.current_player){
                                            exchange.add(new OthelloPiece(cpy.current_player,xxi-abi,yyj+abi));
                                        }
                                        if(grid.isInside(xxi-abi,yyj+abi) && grid.pieceAt(xxi-abi,yyj+abi)!=null && grid.pieceAt(xxi-abi,yyj+abi).player == grid.current_player){
                                            cpy.movePiece(x,y,xx,yy);
                                            cpy.pieces.add(new OthelloPiece(grid.current_player,x,y));
                                            for(Piece pc : exchange){
                                                for(Piece pc1 : cpy.pieces){
                                                    if(pc1.player == pc.player && pc1.x == pc.x && pc1.y == pc.y){
                                                        cpy.pieces.remove(pc1);
                                                        cpy.pieces.add(new OthelloPiece(grid.current_player,pc.x,pc.y));
                                                        break;
                                                    }
                                                }
                                            }
                                            for(Movement move: moves){
                                                if(move.command.equals(Movement.moveCommand(x,y,xx,yy))){
                                                    equal = true;
                                                    break;
                                                }
                                            }
                                            if(!equal){
                                                moves.add(new Movement(Movement.moveCommand(x,y,xx,yy),cpy));
                                                exchange.clear();
                                                break;
                                            }
                                            equal = false;
                                            exchange.clear();
                                            break; 
                                        }
                                        if(grid.pieceAt(xxi-abi,yyj+abi) == null){
                                            exchange.clear();
                                            break;
                                        }
                                    }
                                    exchange.clear();
                                }
                            }       
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
            return "⚉ ";
        }
        if(player==1){
            return "⚇ ";
        }
        return "??";
    }

    @Override
    public Piece clonePiece(){
        OthelloPiece other = new OthelloPiece(player,x,y);
        return other;
    }
}
