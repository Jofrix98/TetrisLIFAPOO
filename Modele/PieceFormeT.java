package Modele;

import java.awt.*;
import java.util.Random;

public class PieceFormeT extends Piece{

    public PieceFormeT(GrilleSimple _grille, Partie _partie) {
        super(_grille, _partie);

        // Gestion position initiale
        x = (int) (Math.random() * ((double)(_grille.LARGEUR - 3)));
        y = 0;

        // Gestion couleur random
        Color[] couleursDisponibles = {Color.YELLOW, Color.CYAN, Color.GREEN, Color.ORANGE, Color.PINK, Color.RED, Color.BLUE, Color.WHITE}; // Jaune et Violet
        couleurPiece = couleursDisponibles[new Random().nextInt(couleursDisponibles.length)];

        // Gestion forme et direction initiale
        tabBool = new boolean[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tabBool[i][j] = false;
            }
        }

        direction = new Direction();

        if(direction.getDirectionCourante() == Direction.DirectionPossible.up ){
            tabBool[0][1] = true;
            tabBool[1][1] = true;
            tabBool[1][0] = true;
            tabBool[1][2] = true;
        }

        if(direction.getDirectionCourante() == Direction.DirectionPossible.down){
            tabBool[0][0] = true;
            tabBool[0][1] = true;
            tabBool[0][2] = true;
            tabBool[1][1] = true;
        }
        if(direction.getDirectionCourante() == Direction.DirectionPossible.left){
            tabBool[1][0] = true;
            tabBool[0][1] = true;
            tabBool[1][1] = true;
            tabBool[2][1] = true;
        }

        if(direction.getDirectionCourante() == Direction.DirectionPossible.right){
            tabBool[0][1] = true;
            tabBool[1][1] = true;
            tabBool[2][1] = true;
            tabBool[1][2] = true;
        }
    }

}
