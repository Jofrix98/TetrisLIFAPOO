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
    public final int TAILLE_PIECE_SUIVANTE = 20;

    public VuePieceSuivante(GrilleSimple _modele) {
        modele = _modele;
        setLayout(new GridLayout(TAILLE_PIECE_SUIVANTE, TAILLE_PIECE_SUIVANTE));
        Border blackline = BorderFactory.createLineBorder(Color.gray,1);
        //setBorder(blackline);
        tab = new JPanel[TAILLE_PIECE_SUIVANTE][TAILLE_PIECE_SUIVANTE];

        for (int i = 0; i < TAILLE_PIECE_SUIVANTE; i++) {
            for (int j = 0; j < TAILLE_PIECE_SUIVANTE; j++) {
                JPanel caseG = new JPanel();
                tab[i][j] = caseG;
                caseG.setBorder(blackline);
                add(caseG);
            }
        }
    }

    public void DessinePieceSuivanteSwing(JPanel[][] tab) {
        Piece pieceSuivante = modele.getPieceSuivante();
        for (int i = 0; i < pieceSuivante.getLignes(); i++) {
            for (int j = 0; j < pieceSuivante.getColonnes(); j++) {
                tab[i][j].setBackground(pieceSuivante.getTabBooli(i, j) ?
                        pieceSuivante.couleurPiece : Color.BLACK);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.DessinePieceSuivanteSwing(tab);
    }
}
