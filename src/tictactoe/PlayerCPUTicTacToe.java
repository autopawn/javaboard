package tictactoe;

import java.util.List;

import javaboard.PlayerCPURandom;
import javaboard.Game;
import javaboard.GridGame;
import javaboard.Movement;

public class PlayerCPUTicTacToe extends PlayerCPURandom{
    @Override
    public Movement pickMovement(Game state, List<Movement> options, List<String> input_commands){
        assert(options.size()>0);
        Movement CPUMove = null;
        CPUMove = CPUBestMove(state, options);
        return CPUMove;
    }  

    public Movement CPUBestMove(Game state, List<Movement> options){
        GridGame tttGrid = (GridGame) state; 
        Movement CPUMove = null;
    
        if (options.size() == 8){
            return CPUFirstMove(tttGrid);
        }

        else if (CPUWinMove(tttGrid, options) != null){
            return CPUWinMove(tttGrid, options);
        }

        else if (CPUdontLoseMove(tttGrid) != null){
            return CPUdontLoseMove(tttGrid);
        }

        else if (CPUIntelligentMove(tttGrid) != null){
            return CPUIntelligentMove(tttGrid);
        }

        else if (CPURandomMove(tttGrid) != null){
            return CPURandomMove(tttGrid);
        }
        else{
            return CPUMove;
        } 
    }

    public Movement CPUFirstMove(GridGame tttGrid){
        Movement CPUMove = null;
        //If player takes and edge CPU takes the middle
        if(tttGrid.pieceAt(0, 0) != null || tttGrid.pieceAt(2, 0) != null || tttGrid.pieceAt(2, 2) != null || tttGrid.pieceAt(0, 2) != null){
            Game tttBoard = tttGrid.cloneGame();
            tttBoard.movePiece(4 + tttBoard.current_player, 4, 1, 1);
            tttBoard.current_player = 1 - tttBoard.current_player;
            CPUMove = new Movement(Movement.moveCommand(0, 0, 1, 1), tttBoard);
            return CPUMove;
        }
        //If player does not take and edge CPU takes 0,0 edge
        else if(tttGrid.pieceAt(0, 0) == null && tttGrid.pieceAt(2, 0) == null && tttGrid.pieceAt(2, 2) == null && tttGrid.pieceAt(0, 2) == null){
            Game tttBoard = tttGrid.cloneGame();
            tttBoard.movePiece(4 + tttBoard.current_player, 4, 0, 0);
            tttBoard.current_player = 1 - tttBoard.current_player;
            CPUMove = new Movement(Movement.moveCommand(0, 0, 0, 0), tttBoard);
            return CPUMove;
        }
        else{
            return null;
        }
    }

    public Movement CPUWinMove(GridGame tttGrid, List<Movement> options){
        Movement CPUMove = null;
        for(int y = 0; y < tttGrid.size_y; y++){
            for(int x = 0; x < tttGrid.size_x; x++){
                if(tttGrid.pieceAt(x, y) == null){
                    Game tttBoard = tttGrid.cloneGame();
                    tttBoard.movePiece(4 + tttBoard.current_player, 4, x, y);
                    //If board is going to be fulfilled or the CPU can win with a mark, puts a mark there.
                    if(options.size() == 1 || isPlayerWin(tttBoard) == 1){
                        tttBoard.current_player = 1 - tttBoard.current_player;
                        char xn = (char)('a'+x);
                        CPUMove = new Movement(""+xn+y,tttBoard);
                        return CPUMove;
                    }
                    tttBoard.movePiece(x, y, 4 + tttBoard.current_player, 4);
                }
            }
        }
        return null; 
    }

    public Movement CPUdontLoseMove(GridGame tttGrid){
        Movement CPUMove = null;
        for(int y = 0; y < tttGrid.size_y; y++){
            for(int x = 0; x <tttGrid.size_x; x++){
                if(tttGrid.pieceAt(x, y)==null){
                    Game tttBoard = tttGrid.cloneGame();
                    tttBoard.movePiece(4 + (1 - tttBoard.current_player), 4, x, y);
                    //If enemy player is going to win with a mark, CPU puts a mark there.
                    if(isPlayerWin(tttBoard) == -1){
                        tttBoard.movePiece(x, y, 4 + (1 - tttBoard.current_player), 4);
                        tttBoard.movePiece(4 + tttBoard.current_player, 4, x, y);
                        tttBoard.current_player = 1 - tttBoard.current_player;
                        char xn = (char)('a'+x);
                        CPUMove = new Movement(""+xn+y,tttBoard);
                        return CPUMove;
                    }
                    tttBoard.movePiece(x, y, 4 + (1 - tttBoard.current_player), 4);
                }
            }
        }
        return null;
    }

    public Movement CPUIntelligentMove(GridGame tttGrid){
        Movement CPUMove = null;
        //If CPU has 0,0 edge, and there's no winning move yet, CPU marks another edge.
        if(tttGrid.pieceAt(0,0) != null && ((tttGrid.pieceAt(0, 0).player == tttGrid.current_player && tttGrid.pieceAt(0,2) == null)||(tttGrid.pieceAt(0, 0).player == tttGrid.current_player && tttGrid.pieceAt(2,0) == null))){
            Game tttBoard = tttGrid.cloneGame();
            tttBoard.movePiece(4 + tttBoard.current_player, 4, 0, 2);
            tttBoard.current_player = 1 - tttBoard.current_player;
            char xn = (char)('a'+0);
            CPUMove = new Movement(""+xn+0,tttBoard);
            return CPUMove;
        }
        return null;
    }

    public Movement CPURandomMove(GridGame tttGrid){
        Movement CPUMove = null;
        //If every other method returns null, CPU just marks an empty cell.
        for(int y = 0; y < tttGrid.size_y; y++){
            for(int x = 0; x < tttGrid.size_x; x++){
                if(tttGrid.pieceAt(x, y) == null){
                    Game tttBoard = tttGrid.cloneGame();
                    tttBoard.movePiece(4 + tttBoard.current_player, 4, x, y);
                    tttBoard.current_player = 1 - tttBoard.current_player;
                    char xn = (char)('a'+x);
                    CPUMove = new Movement(""+xn+y,tttBoard);
                    return CPUMove;
                }
            }
        }
        return null;
    }

    static int isPlayerWin(Game state){
        GridGame tttGrid = (GridGame) state;
        Integer currentWin = tttGrid.currentWinner(tttGrid.getMovements());
        if (currentWin != null){
            if(currentWin == tttGrid.current_player){
                return 1;
    
            } else if(currentWin == 1 - tttGrid.current_player) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String victoryMessage(){
        return "YOU'VE LOST AGAINST THE IA, WEAK HUMAN!";
    }
}
