package Modele;

public class PieceFormeC extends Piece{

    public PieceFormeC(GrilleSimple _grille) {
        super(_grille);
        tabBool = new boolean[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }
        x = 5;
        y = 5;
        tabBool[1][3] = true;
        tabBool[2][3] = true;
        tabBool[1][2] = true;
        tabBool[2][2] = true;
    }

    public void rotation(){
        y -= 1;
    }

    public void run(){
        boolean stop = false;
        int nextY = y;
        int nextX = x;

        nextY += dY;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tabBool[i][j] && (j + y + 1 == grille.TAILLE)) {
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


}
