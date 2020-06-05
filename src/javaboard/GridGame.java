package javaboard;

public abstract class GridGame extends Game {

    public int size_x, size_y;

    @Override
    public String toString(){
        StringBuilder sbu = new StringBuilder();
        sbu.append("\n");
        sbu.append("   ");
        for(int x=0;x<size_x;x++){
            String xlabel = ""+((char)('a'+x));
            sbu.append(xlabel+" ");
        }
        sbu.append("\n");

        for(int y=0;y<size_y;y++){
            String ylabel = ""+y;
            if(ylabel.length()<2) sbu.append(" ");
            sbu.append(ylabel+" ");

            for(int x=0;x<size_x;x++){
                Piece pc_in_cell = pieceAt(x,y);
                if(pc_in_cell!=null){
                    // Draw piece
                    String repr = pc_in_cell.asciiRepresentation();
                    sbu.append(repr);
                }else{
                    // Draw cell
                    if((x+y)%2==0) sbu.append("■ ");
                    else sbu.append("□ ");
                }
            }
            sbu.append("\n");
        }
        return sbu.toString();
    }

    public boolean isInside(int x, int y){
        return x>=0 && y>=0 && x<size_x && y<size_y;
    }

}
