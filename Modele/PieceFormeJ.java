package Modele;

public class PieceFormeJ extends Piece{

    public PieceFormeJ(GrilleSimple _grille) {
        super(_grille);
        tabBool = new boolean[3][3];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }
        x = 5;
        y = 5;
        tabBool[3][0] = true;
        tabBool[3][1] = true;
        tabBool[1][1] = true;
        tabBool[2][1] = true;
    }

    public void rotation(){}
}
