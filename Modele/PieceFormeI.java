package Modele;

import java.awt.*;

public class PieceFormeI extends  Piece {

    public PieceFormeI(GrilleSimple _grille) {
        super(_grille);
        couleurPiece = java.awt.Color.BLUE;
        tabBool = new boolean[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }
        x = 5;
        y = 0;
        tabBool[0][1] = true;
        tabBool[1][1] = true;
        tabBool[2][1] = true;
        tabBool[3][1] = true;
    }

    public void rotation(){
        int[][] tabBoolint = new int[4][4];
        int[][] res = new int[4][4];

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(tabBool[i][j]){
                    tabBoolint[i][j] = 1;
                }
                System.out.print(tabBoolint[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res[4 - 1- j][i] = tabBoolint[i][j];
            }
        }


        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(res[i][j] == 1){
                    tabBool[i][j] = true;
                }
            }
        }

        y -= 1;

    }

}
