package VueControleur;

import Modele.GrilleSimple;
import Modele.Partie;
import Modele.PieceFormeI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;



public class VueGrilleV1 extends JPanel implements Observer {
    JPanel[][] tab;
    GrilleSimple modele;


    public VueGrilleV1(GrilleSimple _modele) {
        modele = _modele;

        setLayout(new GridLayout(modele.LONGUEUR, modele.LARGEUR));
        Border blackline = BorderFactory.createLineBorder(Color.gray,1);
        //setBorder(blackline);
        tab = new JPanel[modele.LARGEUR][modele.LONGUEUR];

        for(int j = 0; j<modele.LONGUEUR;j++){
            for (int i = 0; i < modele.LARGEUR; i++) {
                JPanel caseG = new JPanel();
                tab[i][j] = caseG;
                caseG.setBorder(blackline);
                add(caseG);
            }
        }

    }

    public void dessineGrilleSwing(JPanel[][] tab){
        for(int i = 0; i< modele.LARGEUR;i++){
            for (int j = 0; j < modele.LONGUEUR; j++) {

                tab[i][j].setBackground(Color.black);

                if(modele.matGrille[i][j] != Color.BLACK){
                    tab[i][j].setBackground(modele.matGrille[i][j]);
                }

            }
        }
    }
    public void DessinePieceSwing(JPanel[][] tab){
        //debut
        for (int i = 0; i < modele.getPieceCourante().getLignes();i++){
            for(int j=0;j< modele.getPieceCourante().getColonnes();j++){
                if(modele.getPieceCourante().getTabBooli(i,j)){
                    tab[i+modele.getPieceCourante().getX()][j+ modele.getPieceCourante().getY()].setBackground(modele.getPieceCourante().couleurPiece);
                }

            }
        }
        //fin
    }
    @Override
    public void update(Observable o, Object arg) {
        this.dessineGrilleSwing(tab);
        this.DessinePieceSwing(tab);

    }

}



