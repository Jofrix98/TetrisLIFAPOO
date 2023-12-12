package VueControleur;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class VC extends JFrame implements Observer {

    Partie partie;
    GrilleSimple modeleJoueur1;
    GrilleSimple modeleJoueur2;
    Observer vueGrilleJoueur1;
    Observer vueGrilleJoueur2;
    Observer vuePieceSuivanteJoueur1;
    Observer vuePieceSuivanteJoueur2;
    Observer vuePartieTerminee;


    JPanel jp;
    JLabel jt = new JLabel("");
    JLabel labelJ1 = new JLabel("");
    JLabel labelJ2 = new JLabel("");
    JPanel panelvueGrilleJoueur1;
    JPanel panelVueGrilleJoueur2;
    JPanel panelVuePieceSuivanteJoueur1;
    JPanel panelVuePieceSuivanteJoueur2;
    JPanel panelvuePartieTerminee;
    JPanel panelCarreAffichagePoints;
    JPanel panelTitlePiecesSuivantesJ1;
    JPanel panelTitlePiecesSuivantesJ2;

    // Panel qui contient les visus suivantes du joueur 1 et 2 ici Grillepiecesuivante + label
    JPanel panelCVS12;


    public VC(GrilleSimple _modeleJoueur1, GrilleSimple _modeleJoueur2) {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        modeleJoueur1 = _modeleJoueur1;
        modeleJoueur2 = _modeleJoueur2;
        partie = new Partie(modeleJoueur1, modeleJoueur2);
        setSize(900, 450);

        vueGrilleJoueur1 = new VueGrilleV1(modeleJoueur1);
        vueGrilleJoueur2 = new VueGrilleV1(modeleJoueur2);
        vuePieceSuivanteJoueur1 = new VuePieceSuivante(partie.getGrilleJoueur1());
        vuePieceSuivanteJoueur2 = new VuePieceSuivante(partie.getGrilleJoueur2());
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

        Dimension DimePJ1 = new Dimension(140, 50);
        panelTitlePiecesSuivantesJ1 = new JPanel();
        panelTitlePiecesSuivantesJ1.setPreferredSize(DimePJ1);
        panelTitlePiecesSuivantesJ1.setLayout(new BorderLayout());
        panelTitlePiecesSuivantesJ1.add(labelJ1, BorderLayout.CENTER);
        panelTitlePiecesSuivantesJ1.setBackground(Color.BLACK);

        Dimension DimePJ2 = new Dimension(140, 50);
        panelTitlePiecesSuivantesJ2 = new JPanel();
        panelTitlePiecesSuivantesJ2.setPreferredSize(DimePJ2);
        panelTitlePiecesSuivantesJ2.setLayout(new BorderLayout());
        panelTitlePiecesSuivantesJ2.add(labelJ2, BorderLayout.CENTER);
        panelTitlePiecesSuivantesJ2.setBackground(Color.BLACK);

        jt.setForeground(Color.CYAN);
        labelJ1.setForeground(Color.CYAN);
        labelJ2.setForeground(Color.CYAN);
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp.add(panelCarreAffichagePoints);


        Font maPolice = new Font("Arial", Font.BOLD, 16);
        // Définition de la police pour le JLabel
        jt.setFont(maPolice);
        labelJ1.setFont(maPolice);
        labelJ2.setFont(maPolice);

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

        panelVuePieceSuivanteJoueur1 = (JPanel) vuePieceSuivanteJoueur1;
        Dimension Dime = new Dimension(100, 100);
        panelVuePieceSuivanteJoueur1.setPreferredSize(Dime);
        panelVuePieceSuivanteJoueur1.setBackground(Color.black);

        panelVuePieceSuivanteJoueur2 = (JPanel) vuePieceSuivanteJoueur2;
        Dimension DimeJ2 = new Dimension(100, 100);
        panelVuePieceSuivanteJoueur2.setPreferredSize(DimeJ2);
        panelVuePieceSuivanteJoueur2.setBackground(Color.black);

        panelvuePartieTerminee = (JPanel) vuePartieTerminee;
        jp.add(panelvuePartieTerminee);
        panelvuePartieTerminee.setVisible(false);

        panelCVS12 = new JPanel();
        panelCVS12.setLayout(new BoxLayout(panelCVS12, BoxLayout.Y_AXIS));
        Dimension DimePCVS = new Dimension(300, 300);
        panelCVS12.setPreferredSize(DimePCVS);
        panelCVS12.add(panelTitlePiecesSuivantesJ1);
        panelCVS12.add(panelVuePieceSuivanteJoueur1);

        panelCVS12.add(panelTitlePiecesSuivantesJ2);
        panelCVS12.add(panelVuePieceSuivanteJoueur2);

        jp.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelCVS12.setOpaque(false);
        jp.add(panelCVS12);

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

        setFocusable(true);
        requestFocusInWindow();
    }

    static long lastTime = System.currentTimeMillis();

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 if (partie.partieTerminee() == 0) {
                    vueGrilleJoueur1.update(o, arg);
                    vueGrilleJoueur2.update(o, arg);
                    vuePieceSuivanteJoueur1.update(o, arg);
                    vuePieceSuivanteJoueur2.update(o, arg);
                    jt.setText("<html>J1 : " + modeleJoueur1.getPoints() + "<br>J2 : " + modeleJoueur2.getPoints() + "</html>");
                    labelJ1.setText("    Joueur 1");
                    labelJ2.setText("    Joueur 2");
                    lastTime = System.currentTimeMillis();
                } else {
                    panelCVS12.setVisible(false);

                    panelCarreAffichagePoints.setVisible(false);
                    panelvueGrilleJoueur1.setVisible(false);
                    panelVueGrilleJoueur2.setVisible(false);
                    panelVuePieceSuivanteJoueur1.setVisible(false);
                    panelVuePieceSuivanteJoueur2.setVisible(false);
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

                String imagePath = "data/Tetriss.jpeg";
                ImagePanel imagePanel = new ImagePanel(imagePath);

                imagePanel.add(vc.jp);

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

