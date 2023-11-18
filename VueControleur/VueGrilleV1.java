package VueControleur;

import Modele.GrilleSimple;
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
        setLayout(new GridLayout(modele.TAILLE, modele.TAILLE));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        //setBorder(blackline);
        tab = new JPanel[modele.TAILLE][modele.TAILLE];

        for(int j = 0; j<modele.TAILLE;j++){
            for (int i = 0; i < modele.TAILLE; i++) {
                JPanel caseG = new JPanel();
                tab[i][j] = caseG;
                caseG.setBorder(blackline);
                add(caseG);
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        for(int i = 0; i<modele.TAILLE;i++){
            for (int j = 0; j < modele.TAILLE; j++) {

                tab[i][j].setBackground(Color.black);

            }
        }

        for (int i = 0; i <4;i++){
            for(int j=0;j<4;j++){
                if(modele.bei.getTabBooli(i,j) == true && modele.validationPosition(i+modele.bei.getX(),j+ modele.bei.getY()) ){
                    tab[i+modele.bei.getX()][j+ modele.bei.getY()].setBackground(Color.ORANGE);
                }

            }
        }

    }
}
