package harehounds;

import javaboard.Evaluable;
import javaboard.Game;
import javaboard.Movement;
import javaboard.Piece;

// Represents a Game within a grid
public abstract class GridChange extends Game {

    public int size_x, size_y;

    // Graphical representation of a grid
    @Override
    public String toString(){
        StringBuilder sbu = new StringBuilder();
        int trax = 0;
        int tray = 0;
        int varia = 0;
        // First row with letters
        sbu.append("\n");
        sbu.append("   ");
        for(int y=0;y<size_y+4;y++){
            //Hay que saltarse un espacio para poner la coordenada.
            int paridady = y%2;
            if(paridady!=0){
              tray += 1;
              String ylabel = ""+((char)(' '));
              sbu.append(ylabel+" ");
            }
            else{
              String ylabel = ""+(y-tray);
              sbu.append(ylabel+" ");
            }
        }
        sbu.append("\n");


        // Other parts of the board
        for(int x=0;x<size_x+2;x++){
            // Number label

            int paridadx = x%2;
            tray = 0;
            if(paridadx!=0){
              //Hay que saltarse un espacio para poner la coordenada
              trax += 1;
              String xlabel = ""+((char)(' '));
              if(xlabel.length()<2) sbu.append(" ");
              sbu.append(xlabel+" ");

            }
            else{
              String xlabel = ""+((char)('a'+x-trax));
              if(xlabel.length()<2) sbu.append(" ");
              sbu.append(xlabel+" ");

            }



            // Each cell
            //Las siguientes condiciones son todo lo necesario para adaptar el tablero de FoxAndHounds al de HareAndHounds
            for(int y=0;y<size_y+4;y++){
                int paridady = y%2;
                if(paridadx==0){
                  if(paridady==0){
                    Piece pc_in_cell = pieceAt(x-trax,y-tray);
                    if(pc_in_cell!=null){
                        // Draw piece
                        String repr = pc_in_cell.asciiRepresentation();
                        sbu.append(repr);
                    }
                    else{
                        if(((x==0||x==4||x==1||x==3)&&(y==0||y==8))||((x==0||x==4)&&(y==1||y==7))){
                          sbu.append("  ");
                        }
                        else{
                          sbu.append("▓ ");
                        }
                    }
                  }
                  else{

                    tray += 1;
                    if(((x==0||x==4||x==1||x==3)&&(y==0||y==8))||((x==0||x==4)&&(y==1||y==7))){
                      sbu.append("  ");
                    }
                    else{
                      sbu.append("- ");
                    }
                  }
                }
                else{
                  if(y==0||y==8){
                    sbu.append("  ");
                  }
                  else{
                    if(paridady==0){
                      sbu.append("| ");
                      varia += 1;
                    }
                    else{
                      if(varia%2==0){
                        sbu.append("/ ");
                      }
                      else{
                        sbu.append("\\ ");
                      }
                    }
                  }
                }
            }

            sbu.append("\n");
        }
        return sbu.toString();
    }
    // Wheter a given position is inside the board
    public boolean isInside(int x, int y){
        boolean checkear = true;
        //Como no es una cuadricula perfecta, hay que considerar los espacios que quedan sin uso.
        if((x==0 || x==2) && (y==0 || y==4)){
          checkear = false;
        }
        return x>=0 && y>=0 && x<size_x && y<size_y && checkear;
    }
}
