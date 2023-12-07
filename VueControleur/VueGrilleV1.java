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
    Partie modele;


    public VueGrilleV1(Partie _modele) {
        modele = _modele;

        setLayout(new GridLayout(modele.getGrilleJoueur1().LONGUEUR, modele.getGrilleJoueur1().LARGEUR));
        Border blackline = BorderFactory.createLineBorder(Color.gray,1);
        //setBorder(blackline);
        tab = new JPanel[modele.getGrilleJoueur1().LARGEUR][modele.getGrilleJoueur1().LONGUEUR];

        for(int j = 0; j<modele.getGrilleJoueur1().LONGUEUR;j++){
            for (int i = 0; i < modele.getGrilleJoueur1().LARGEUR; i++) {

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

                if(modele.getGrilleJoueur1().matGrille[i][j] != Color.BLACK){
                    tab[i][j].setBackground(modele.getGrilleJoueur1().matGrille[i][j]);
                }

            }
        }
    }
    public void DessinePieceSwing(JPanel[][] tab){
        //debut
        for (int i = 0; i < modele.getGrilleJoueur1().getPieceCourante().getLignes();i++){
            for(int j=0;j< modele.getGrilleJoueur1().getPieceCourante().getColonnes();j++){
                if(modele.getGrilleJoueur1().getPieceCourante().getTabBooli(i,j)){
                    tab[i+modele.getGrilleJoueur1().getPieceCourante().getX()][j+ modele.getGrilleJoueur1().getPieceCourante().getY()].setBackground(modele.getGrilleJoueur1().getPieceCourante().couleurPiece);
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



