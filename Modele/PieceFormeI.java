package Modele;

public class PieceFormeI extends  Piece {

    public PieceFormeI() {
        super();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }

        tabBool[0][0] = true;
        tabBool[1][0] = true;
        tabBool[2][0] = true;
        tabBool[3][0] = true;


    }


}
