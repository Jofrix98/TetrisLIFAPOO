package Modele;

public class PieceFormeL extends Piece{

    public PieceFormeL(GrilleSimple _grille) {
        super(_grille);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }
        x = 5;
        y = 5;
        tabBool[0][0] = true;
        tabBool[0][1] = true;
        tabBool[0][2] = true;
        tabBool[1][2] = true;
    }
}
