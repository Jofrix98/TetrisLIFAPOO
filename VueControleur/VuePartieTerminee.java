package VueControleur;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectStreamClass;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VuePartieTerminee extends JFrame implements Observer {



    public VuePartieTerminee(GrilleSimple _modeleJoueur1, GrilleSimple _modeleJoueur2) {

        setSize(700, 450);

        String imagePath = "data/Tetriss.jpeg";
        ImagePanel imagePanel = new ImagePanel(imagePath);
        add(imagePanel);

    }

    @Override
    public void update(Observable o, Object arg) {
        String imagePath = "data/Tetriss.jpeg";
        ImagePanel imagePanel = new ImagePanel(imagePath);
        add(imagePanel);
    }
}
