package Modele;

public class PieceFormeT extends Piece{

    public PieceFormeT(GrilleSimple _grille) {
        super(_grille);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }
        x = 9;
        y = 5;
        tabBool[0][0] = true;
        tabBool[1][0] = true;
        tabBool[2][0] = true;
        tabBool[1][1] = true;
    }

}
