package Modele;

public abstract class Piece {
    protected int x;
    protected int y;
    private int dY = 1;
    protected boolean [][] tabBool;
    protected GrilleSimple grille;

    public Piece(GrilleSimple _grille){
        grille = _grille;
        initialiser();
    }

    public void initialiser(){
        tabBool = new boolean[5][5];
    }

    public void run() {
        boolean stop = false;
        int nextY = y;
        int nextX = x;

        nextY += dY;

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(tabBool[i][j] == true && (j+y+1 == grille.TAILLE )){
                    stop = true;
                }
            }
        }

        if (!stop) {
            y = nextY;
            x = nextX;
            //System.out.println("pos" + x + " "+ y);
        } else {
            dY = 0;
        }


    }


    public void action() {
        dY *= -1;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public boolean getTabBooli(int i,int j){
        return tabBool[i][j];
    }
}


