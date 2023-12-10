package VueControleur;

import Modele.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectStreamClass;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VuePartieTerminee extends JPanel implements Observer {

    JPanel[][] tab;
    JLabel labelPartieTerminee;
    Partie partie;

    public VuePartieTerminee(Partie _partie) {
        partie = _partie;
        setLayout(new GridLayout(1, 1));
        tab = new JPanel[1][1];

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                JPanel caseG = new JPanel();
                tab[i][j] = caseG;
                add(caseG);
            }
        }


    }

    public void dessineFondNoir(JPanel[][] tab) {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                tab[i][j].setBackground(Color.black);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        dessineFondNoir(tab);
        if(partie.partieTerminee() == 2){
            labelPartieTerminee = new JLabel("Partie terminée: Gagnant Joueur 2" );
        }

        if(partie.partieTerminee() == 1){
            labelPartieTerminee = new JLabel("Partie terminée: Gagnant Joueur 1");
        }

        if(partie.partieTerminee() == 3){
            labelPartieTerminee = new JLabel("Partie terminée: Aucun Gagnant");
        }
        labelPartieTerminee.setForeground(Color.WHITE); // Couleur du texte
        labelPartieTerminee.setHorizontalAlignment(JLabel.CENTER); // Alignement au centre
        labelPartieTerminee.setVerticalAlignment(JLabel.CENTER);
        labelPartieTerminee.setPreferredSize(new Dimension(500, 500));
        tab[0][0].setLayout(new BorderLayout());
        tab[0][0].add(labelPartieTerminee);
        // Afficher le message "Partie terminée" au milieu du bloc noir
        labelPartieTerminee.setVisible(true);
    }
}

