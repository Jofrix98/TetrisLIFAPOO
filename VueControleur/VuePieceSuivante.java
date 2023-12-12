package VueControleur;

import Modele.GrilleSimple;
import Modele.Piece;
import Modele.PieceFormeI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VuePieceSuivante extends JPanel implements Observer {
    JPanel[][] tab;
    GrilleSimple modele;
    int TAILLE_LIGNES = 4;
    int TAILLE_COLONNES = 14;

    public VuePieceSuivante(GrilleSimple _modele) {
        modele = _modele;
        setLayout(new GridLayout(TAILLE_LIGNES, TAILLE_COLONNES));
        Border blackline = BorderFactory.createLineBorder(Color.gray,1);
        tab = new JPanel[TAILLE_LIGNES][TAILLE_COLONNES];

        for (int i = 0; i < TAILLE_LIGNES; i++) {
            for (int j = 0; j < TAILLE_COLONNES; j++) {
                JPanel caseG = new JPanel();
                tab[i][j] = caseG;
                caseG.setBorder(blackline);
                add(caseG);
            }
        }
    }

    public void dessineGrilleSwing(JPanel[][] tab){
        for(int i = 0; i< TAILLE_LIGNES;i++){
            for (int j = 0; j < TAILLE_COLONNES; j++) {
                tab[i][j].setBackground(Color.black);
            }
        }
    }

    public void dessinePieceSwing(JPanel[][] tab) {
        int espaceEntrePieces = 1; // Nombre de colonnes d'espace entre les pièces

        // Dessine la première pièce
        for (int i = 0; i < modele.getPieceSuivante().getLignes(); i++) {
            for (int j = 0; j < modele.getPieceSuivante().getColonnes(); j++) {
                if (modele.getPieceSuivante().getTabBooli(i, j)) {
                    tab[i][j].setBackground(modele.getPieceSuivante().couleurPiece);
                }
            }
        }

        // Dessine l'espace entre la première et la deuxième pièce
        for (int i = 0; i < modele.getPieceSuivante().getLignes(); i++) {
            for (int j = modele.getPieceSuivante().getColonnes(); j < modele.getPieceSuivante().getColonnes() + espaceEntrePieces; j++) {
                tab[i][j].setBackground(Color.BLACK);
            }
        }

        // Dessine la deuxième pièce
        for (int i = 0; i < modele.getDeuxiemePiece().getLignes(); i++) {
            for (int j = modele.getPieceSuivante().getColonnes() + espaceEntrePieces; j < modele.getPieceSuivante().getColonnes() + espaceEntrePieces + modele.getDeuxiemePiece().getColonnes(); j++) {
                if (modele.getDeuxiemePiece().getTabBooli(i, j - (modele.getPieceSuivante().getColonnes() + espaceEntrePieces))) {
                    tab[i][j].setBackground(modele.getDeuxiemePiece().couleurPiece);
                }
            }
        }

        // Dessine l'espace entre la deuxième et la troisième pièce
        for (int i = 0; i < modele.getTroisiemePiece().getLignes(); i++) {
            for (int j = modele.getPieceSuivante().getColonnes() + espaceEntrePieces + modele.getDeuxiemePiece().getColonnes(); j < modele.getPieceSuivante().getColonnes() + espaceEntrePieces + modele.getDeuxiemePiece().getColonnes() + espaceEntrePieces; j++) {
                tab[i][j].setBackground(Color.BLACK);
            }
        }

        // Dessine la troisième pièce
        for (int i = 0; i < modele.getTroisiemePiece().getLignes(); i++) {
            for (int j = modele.getPieceSuivante().getColonnes() + espaceEntrePieces + modele.getDeuxiemePiece().getColonnes() + espaceEntrePieces; j < modele.getPieceSuivante().getColonnes() + espaceEntrePieces + modele.getDeuxiemePiece().getColonnes() + espaceEntrePieces + modele.getTroisiemePiece().getColonnes(); j++) {
                if (modele.getTroisiemePiece().getTabBooli(i, j - (modele.getPieceSuivante().getColonnes() + espaceEntrePieces + modele.getDeuxiemePiece().getColonnes() + espaceEntrePieces))) {
                    tab[i][j].setBackground(modele.getTroisiemePiece().couleurPiece);
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.dessineGrilleSwing(tab);
        this.dessinePieceSwing(tab);
    }
}
