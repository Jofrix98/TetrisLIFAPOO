package Modele;

import java.awt.*;
import java.util.Random;

public class PieceFormeI extends  Piece {

    public PieceFormeI(GrilleSimple _grille, Partie _partie) {
        super(_grille, _partie);

        // Gestion position initiale
        x = (int) (Math.random() * ((double)(_grille.LARGEUR - 4)));
        y = 0;

        // Gestion couleur random
        Color[] couleursDisponibles = {Color.YELLOW, Color.CYAN, Color.GREEN, Color.ORANGE, Color.PINK, Color.RED, Color.BLUE, Color.WHITE}; // Jaune et Violet
        couleurPiece = couleursDisponibles[new Random().nextInt(couleursDisponibles.length)];

        // Gestion forme et direction initiale
        tabBool = new boolean[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }

        direction = new Direction();

        if(direction.getDirectionCourante() == Direction.DirectionPossible.up || direction.getDirectionCourante() == Direction.DirectionPossible.down){
            tabBool[1][0] = true;
            tabBool[1][1] = true;
            tabBool[1][2] = true;
            tabBool[1][3] = true;
        }

        if(direction.getDirectionCourante() == Direction.DirectionPossible.right || direction.getDirectionCourante() == Direction.DirectionPossible.left){
            tabBool[0][1] = true;
            tabBool[1][1] = true;
            tabBool[2][1] = true;
            tabBool[3][1] = true;
        }
    }


}
