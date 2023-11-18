package Modele;

public class PieceFormeC extends Piece{

    public PieceFormeC(GrilleSimple _grille) {
        super(_grille);
        tabBool = new boolean[4][4];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
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

    public void rotation(){}
}
