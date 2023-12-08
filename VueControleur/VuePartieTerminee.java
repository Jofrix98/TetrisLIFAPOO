/*package VueControleur;

import Modele.GrilleSimple;
import Modele.Partie;
import Modele.PieceFormeI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;



public class VuePartieTerminee extends JPanel implements Observer {
    JPanel[][] tab;
    GrilleSimple modele;


    public VuePartieTerminee(GrilleSimple _modele) {
        modele = _modele;
        int largeur = 700;
        int longueur = 450;
        setLayout(new GridLayout(largeur, longueur));
        Border blackline = BorderFactory.createLineBorder(Color.red,1);
        //setBorder(blackline);
        tab = new JPanel[largeur][modele.LONGUEUR];

        for(int j = 0; j<modele.LONGUEUR;j++){
            for (int i = 0; i < largeur; i++) {

                JPanel caseG = new JPanel();
                tab[i][j] = caseG;
                caseG.setBorder(blackline);
                add(caseG);
            }
        }

    }

    public void dessineGrilleSwing(JPanel[][] tab){
        int largeur = 700;
        int longueur = 450;
        for(int i = 0; i< largeur;i++){
            for (int j = 0; j < longueur; j++) {

                tab[i][j].setBackground(Color.red);

            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.dessineGrilleSwing(tab);

    }

}
*/


