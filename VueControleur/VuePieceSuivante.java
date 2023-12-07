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


    public VuePieceSuivante(GrilleSimple _modele) {
        modele = _modele;
        int TAILLE_X_PIECE_SUIVANTE = modele.getPieceSuivante().getLignes();
        int TAILLE_Y_PIECE_SUIVANTE = modele.getPieceSuivante().getColonnes();
        setLayout(new GridLayout(TAILLE_X_PIECE_SUIVANTE, TAILLE_Y_PIECE_SUIVANTE));
        Border blackline = BorderFactory.createLineBorder(Color.gray,1);
        //setBorder(blackline);
        tab = new JPanel[TAILLE_X_PIECE_SUIVANTE][TAILLE_Y_PIECE_SUIVANTE];

        for (int i = 0; i < TAILLE_X_PIECE_SUIVANTE; i++) {
            for (int j = 0; j < TAILLE_Y_PIECE_SUIVANTE; j++) {
                JPanel caseG = new JPanel();
                tab[i][j] = caseG;
                caseG.setBorder(blackline);
                add(caseG);
            }
        }
    }

    public void dessineGrilleSwing(JPanel[][] tab){
        int TAILLE_X_PIECE_SUIVANTE = modele.getPieceSuivante().getLignes();
        int TAILLE_Y_PIECE_SUIVANTE = modele.getPieceSuivante().getColonnes();
        for(int i = 0; i< TAILLE_X_PIECE_SUIVANTE;i++){
            for (int j = 0; j < TAILLE_Y_PIECE_SUIVANTE; j++) {
                tab[i][j].setBackground(Color.black);
            }
        }
    }

    public void dessinePieceSwing(JPanel[][] tab){
        //debut
        for (int i = 0; i < modele.getPieceSuivante().getLignes();i++){
            for(int j = 0; j < modele.getPieceSuivante().getColonnes();j++){
                if(modele.getPieceSuivante().getTabBooli(i,j)){
                    tab[i][j].setBackground(modele.getPieceSuivante().couleurPiece);
                }

            }
        }
        //fin
    }



    @Override
    public void update(Observable o, Object arg) {
        this.dessineGrilleSwing(tab);
        this.dessinePieceSwing(tab);
    }
}
