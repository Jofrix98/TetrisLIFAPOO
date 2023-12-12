package Modele;

import java.awt.*;
import java.util.Random;
public class PieceFormeC extends Piece{

    public PieceFormeC(GrilleSimple _grille, Partie _partie) {
        super(_grille, _partie);
        Color[] couleursDisponibles = {Color.YELLOW, Color.CYAN, Color.GREEN, Color.ORANGE, Color.PINK, Color.RED, Color.BLUE, Color.WHITE}; // Jaune et Violet
        couleurPiece = couleursDisponibles[new Random().nextInt(couleursDisponibles.length)];
        tabBool = new boolean[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }

        x = (int) (Math.random() * ((double)(_grille.LARGEUR - 4)));
        y = 0;

        tabBool[1][0] = true;
        tabBool[2][0] = true;
        tabBool[1][1] = true;
        tabBool[2][1] = true;
    }

    public void rotation(){
        y -= 1;
    }



}
