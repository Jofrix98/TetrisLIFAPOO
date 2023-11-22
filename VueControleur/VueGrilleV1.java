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

    public void dessineGrilleSwing(JPanel[][] tab){
        for(int i = 0; i< modele.TAILLE;i++){
            for (int j = 0; j < modele.TAILLE; j++) {

                tab[i][j].setBackground(Color.black);

            }
        }
    }
    public void DessinePieceSwing(JPanel[][] tab, int index){
        //debut
        for (int i = 0; i < modele.tabPiece.get(index).getLignes();i++){
            for(int j=0;j< modele.tabPiece.get(index).getColonnes();j++){
                if(modele.tabPiece.get(index).getTabBooli(i,j)){
                    tab[i+modele.tabPiece.get(index).getX()][j+ modele.tabPiece.get(index).getY()].setBackground(modele.tabPiece.get(index).couleurPiece);
                }

            }
        }
        //fin
    }
    public void dessinePieces(JPanel[][] tab){
        for(int i = 0; i<modele.tabPiece.size();i++){
            this.DessinePieceSwing(tab, i);
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        this.dessineGrilleSwing(tab);
        this.dessinePieces(tab);

    }
}
