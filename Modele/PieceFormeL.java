package Modele;

import java.awt.*;

public class PieceFormeL extends Piece{

    public PieceFormeL(GrilleSimple _grille) {
        super(_grille);
        tabBool = new boolean[3][3];
        couleurPiece = Color.ORANGE;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tabBool[i][j] = false;
            }
        }
        x = 5;
        y = 5;
        tabBool[0][1] = true;
        tabBool[1][1] = true;
        tabBool[2][1] = true;
        tabBool[2][0] = true;
    }

    public void rotation(){
        int[][] tabBoolint = new int[3][3];
        int[][] res = new int[3][3];

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(tabBool[i][j]){
                    tabBoolint[i][j] = 1;
                }
                System.out.print(tabBoolint[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[3 - 1- j][i] = tabBoolint[i][j];
            }
        }

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tabBool[i][j] = false;
            }
        }

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(res[i][j] == 1){
                    tabBool[i][j] = true;
                }
            }
        }

        y -= 1;
    }

}
