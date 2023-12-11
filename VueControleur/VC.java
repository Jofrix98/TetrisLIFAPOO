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

public class VC extends JFrame implements Observer {

    Partie partie;
    GrilleSimple modeleJoueur1;
    GrilleSimple modeleJoueur2;
    Observer vueGrilleJoueur1;
    Observer vueGrilleJoueur2;
    Observer vuePieceSuivante;
    Observer vuePartieTerminee;
    JPanel jp;
    JLabel jt = new JLabel("");
    JPanel panelvueGrilleJoueur1;
    JPanel panelVueGrilleJoueur2;
    JPanel panelVuePieceSuivante;
    JPanel panelvuePartieTerminee;

    JPanel panelCarreAffichagePoints;
    private Executor ex = Executors.newSingleThreadExecutor();

    public VC(GrilleSimple _modeleJoueur1, GrilleSimple _modeleJoueur2) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        modeleJoueur1 = _modeleJoueur1;
        modeleJoueur2 = _modeleJoueur2;
        partie = new Partie(modeleJoueur1, modeleJoueur2);
        setSize(700, 450);

        vueGrilleJoueur1 = new VueGrilleV1(modeleJoueur1);
        vueGrilleJoueur2 = new VueGrilleV1(modeleJoueur2);
        vuePieceSuivante = new VuePieceSuivante(partie.getGrilleJoueur1());
        vuePartieTerminee = new VuePartieTerminee(partie);

        jp = new JPanel();
        jp.setLayout(new OverlayLayout(jp));
        jp.setOpaque(false);


        Dimension Dime2 = new Dimension(140, 50);
        panelCarreAffichagePoints = new JPanel();
        panelCarreAffichagePoints.setPreferredSize(Dime2);
        panelCarreAffichagePoints.setLayout(new BorderLayout());
        panelCarreAffichagePoints.add(jt, BorderLayout.CENTER);
        panelCarreAffichagePoints.setBackground(Color.BLACK);
        jt.setForeground(Color.CYAN);
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp.add(panelCarreAffichagePoints);

        Font maPolice = new Font("Arial", Font.BOLD, 16);

        // Définition de la police pour le JLabel
        jt.setFont(maPolice);





        panelvueGrilleJoueur1 = (JPanel) vueGrilleJoueur1;
        Dimension newDimension = new Dimension(200, 410);
        panelvueGrilleJoueur1.setPreferredSize(newDimension);
        panelvueGrilleJoueur1.setOpaque(false);
        jp.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp.add(panelvueGrilleJoueur1);

        panelVueGrilleJoueur2 = (JPanel) vueGrilleJoueur2;
        Dimension newDimension1 = new Dimension(200, 410);
        panelVueGrilleJoueur2.setPreferredSize(newDimension1);
        panelVueGrilleJoueur2.setOpaque(false);
        jp.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp.add(panelVueGrilleJoueur2);

        panelVuePieceSuivante = (JPanel) vuePieceSuivante;
        Dimension Dime = new Dimension(100, 100);
        panelVuePieceSuivante.setPreferredSize(Dime);
        jp.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jp.add(panelVuePieceSuivante);

        panelvuePartieTerminee = (JPanel) vuePartieTerminee;
        /*Dimension dimPartieTerminee = new Dimension(800, 450);
        panelvuePartieTerminee.setPreferredSize(dimPartieTerminee);*/
        jp.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp.add(panelvuePartieTerminee);
        panelvuePartieTerminee.setVisible(false);
        setContentPane(jp);
        // Ajouter un KeyListener pour le joueur 1
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        partie.getGrilleJoueur2().getPieceCourante().rotation();
                        break;
                    case KeyEvent.VK_LEFT:
                        partie.getGrilleJoueur2().getPieceCourante().mvtGauche();
                        break;
                    case KeyEvent.VK_RIGHT:
                        partie.getGrilleJoueur2().getPieceCourante().mvtDroit();
                        break;
                    case KeyEvent.VK_DOWN:
                        partie.getGrilleJoueur2().getOrdonnanceurSimple().setTempsExecution(100);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    partie.getGrilleJoueur2().getOrdonnanceurSimple().setTempsExecution(500);
                }
            }
        });

        // Ajouter un KeyListener pour le joueur 2
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_E:
                        partie.getGrilleJoueur1().getPieceCourante().rotation();
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_Q:
                        partie.getGrilleJoueur1().getPieceCourante().mvtGauche();
                        break;
                    case KeyEvent.VK_D:
                        partie.getGrilleJoueur1().getPieceCourante().mvtDroit();
                        break;
                    case KeyEvent.VK_S:
                        partie.getGrilleJoueur1().getOrdonnanceurSimple().setTempsExecution(100);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    partie.getGrilleJoueur1().getOrdonnanceurSimple().setTempsExecution(500);
                }
            }
        });

        // Assurez-vous que la fenêtre a le focus pour recevoir les événements clavier
        setFocusable(true);
        requestFocusInWindow();
    }

    static long lastTime = System.currentTimeMillis();

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if(partie.partieTerminee() == 0){
                    vueGrilleJoueur1.update(o, arg);
                    vueGrilleJoueur2.update(o, arg);
                    vuePieceSuivante.update(o, arg);

                    jt.setText("<html>J1 : " + modeleJoueur1.getPoints() + "<br>J2 : " + modeleJoueur2.getPoints() + "</html>");


                    lastTime = System.currentTimeMillis();
                }

                else{
                    panelCarreAffichagePoints.setVisible(false);
                    panelvueGrilleJoueur1.setVisible(false);
                    panelVueGrilleJoueur2.setVisible(false);
                    panelVuePieceSuivante.setVisible(false);
                    panelvuePartieTerminee.setVisible(true);
                    vuePartieTerminee.update(o, arg);

                    setContentPane(jp);
                }
            }
        });
    }

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GrilleSimple mJ1 = new GrilleSimple();
                GrilleSimple mJ2 = new GrilleSimple();
                VC vc = new VC(mJ1, mJ2);


                // Créez une instance d'ImagePanel avec l'image souhaitée
                String imagePath = "data/Tetriss.jpeg"; // Remplacez par le chemin de votre image
                ImagePanel imagePanel = new ImagePanel(imagePath);

                // Ajoutez le panneau jp à l'ImagePanel
                imagePanel.add(vc.jp);

                // Définissez l'ImagePanel comme le contenu principal du JFrame
                vc.setContentPane(imagePanel);

                Partie partie = new Partie(mJ1, mJ2);
                vc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                partie.getGrilleJoueur1().addObserver(vc);
                partie.getGrilleJoueur2().addObserver(vc);
                vc.setVisible(true);
            }
        });
    }
}
