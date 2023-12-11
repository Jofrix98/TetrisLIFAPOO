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

    //JPanel[][] tab;
    JLabel labelPartieTerminee;
    Partie partie;

    public VuePartieTerminee(Partie _partie) {
        partie = _partie;
        setLayout(new BorderLayout());
        setBackground(Color.black);

    }


    @Override
    public void update(Observable o, Object arg) {

        if(partie.partieTerminee() == 2){
            labelPartieTerminee = new JLabel("Partie terminée: Gagnant Joueur 2" );
        }

        if(partie.partieTerminee() == 1){
            labelPartieTerminee = new JLabel("Partie terminée: Gagnant Joueur 1");
        }

        if(partie.partieTerminee() == 3){
            labelPartieTerminee = new JLabel("Partie terminée: Aucun Gagnant");
        }
        Font maPolice = new Font("Arial", Font.BOLD, 16);

        // Définition de la police pour le JLabel
        labelPartieTerminee.setFont(maPolice);

        labelPartieTerminee.setForeground(Color.WHITE); // Couleur du texte
        labelPartieTerminee.setHorizontalAlignment(JLabel.CENTER); // Alignement au centre
        labelPartieTerminee.setVerticalAlignment(JLabel.CENTER);
        labelPartieTerminee.setPreferredSize(new Dimension(500, 400));
        add(labelPartieTerminee, BorderLayout.CENTER);
        // Afficher le message "Partie terminée" au milieu du bloc noir
        labelPartieTerminee.setVisible(true);
    }
}


