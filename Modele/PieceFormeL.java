package Modele;

import java.awt.*;

public class PieceFormeL extends Piece{

    public PieceFormeL(GrilleSimple _grille, Partie _partie) {
        super(_grille, _partie);
        tabBool = new boolean[3][3];
        couleurPiece = java.awt.Color.ORANGE;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tabBool[i][j] = false;
            }
        }
        x = (int) (Math.random() * ((double)(_grille.LARGEUR - 3)));
        y = 0;

        direction = new Direction();

        if(direction.getDirectionCourante() == Direction.DirectionPossible.up ){
            tabBool[1][0] = true;
            tabBool[2][0] = true;
            tabBool[2][1] = true;
            tabBool[2][2] = true;
        }

        if(direction.getDirectionCourante() == Direction.DirectionPossible.down){
            tabBool[1][0] = true;
            tabBool[1][1] = true;
            tabBool[1][2] = true;
            tabBool[2][2] = true;
        }
        if(direction.getDirectionCourante() == Direction.DirectionPossible.left){
            tabBool[0][1] = true;
            tabBool[1][1] = true;
            tabBool[2][1] = true;
            tabBool[2][0] = true;
        }

        if(direction.getDirectionCourante() == Direction.DirectionPossible.right){
            tabBool[0][1] = true;
            tabBool[1][1] = true;
            tabBool[2][1] = true;
            tabBool[0][2] = true;
        }
    }

    public void rotation(){
        int[][] tabBoolint = new int[3][3];
        int[][] res = new int[3][3];

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(tabBool[i][j]){
                    tabBoolint[i][j] = 1;
                }

            }

        }




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
