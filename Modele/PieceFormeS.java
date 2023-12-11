package Modele;

import java.awt.*;

public class PieceFormeS extends Piece{

    public PieceFormeS(GrilleSimple _grille, Partie _partie){
        super(_grille, _partie);
        couleurPiece = java.awt.Color.RED;
        tabBool = new boolean[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tabBool[i][j] = false;
            }
        }
        x = (int) (Math.random() * ((double)(_grille.LARGEUR - 3)));
        y = 0;

        direction = new Direction();

        if(direction.getDirectionCourante() == Direction.DirectionPossible.up || direction.getDirectionCourante() == Direction.DirectionPossible.down){
            tabBool[0][0] = true;
            tabBool[1][0] = true;
            tabBool[1][1] = true;
            tabBool[2][1] = true;
        }

        if(direction.getDirectionCourante() == Direction.DirectionPossible.right || direction.getDirectionCourante() == Direction.DirectionPossible.left){
            tabBool[1][1] = true;
            tabBool[1][2] = true;
            tabBool[2][0] = true;
            tabBool[2][1] = true;
        }
    }




}
