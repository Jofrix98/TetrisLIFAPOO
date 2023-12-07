package VueControleur;

import Modele.*;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    JTextField jt = new JTextField("");
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    private Executor ex =  Executors.newSingleThreadExecutor();

    public VC(GrilleSimple _modeleJoueur1, GrilleSimple _modeleJoueur2) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        modeleJoueur1 = _modeleJoueur1;
        modeleJoueur2 = _modeleJoueur2;
        partie = new Partie(modeleJoueur1, modeleJoueur2);
        setSize(700, 450);

        String imagePath = "data/Tetriss.jpeg";
        ImagePanel imagePanel = new ImagePanel(imagePath);
        add(imagePanel);

        JPanel jp = new JPanel();
        jp.setLayout(new OverlayLayout(jp));




        Dimension Dime2 = new Dimension(165, 50);
        jt.setPreferredSize(Dime2);
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp.add(jt);


        vueGrilleJoueur1 = new VueGrilleV1(partie); // composants swing, saccades
        JPanel panelvueGrilleJoueur1 = (JPanel) vueGrilleJoueur1;
        //vueGrille = new VueGrilleV2(modele); // composant AWT dédié
        Dimension newDimension = new Dimension(200, 410);
        panelvueGrilleJoueur1.setPreferredSize(newDimension);
        jp.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp.add(panelvueGrilleJoueur1);

        setContentPane(jp);

        vueGrilleJoueur2 = new VueGrilleV1(partie); // composants swing, saccades
        JPanel panelVueGrilleJoueur2 = (JPanel) vueGrilleJoueur2;
        //vueGrille = new VueGrilleV2(modele); // composant AWT dédié
        Dimension newDimension1 = new Dimension(200, 410);
        panelVueGrilleJoueur2.setPreferredSize(newDimension1);
        jp.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp.add(panelVueGrilleJoueur2);
        setContentPane(jp);

        vuePieceSuivante = new VuePieceSuivante(partie.getGrilleJoueur1());
        JPanel panelVuePieceSuivante = (JPanel) vuePieceSuivante;
        Dimension Dime = new Dimension(100, 100);
        panelVuePieceSuivante.setPreferredSize(Dime);



        jp.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jp.add((JPanel)panelVuePieceSuivante);

        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                //System.out.println("key event");
                if(e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (e.getKeyCode()) {


                        case KeyEvent.VK_SPACE:
                            partie.getGrilleJoueur2().getPieceCourante().rotation();

                            break;
                        case KeyEvent.VK_LEFT:
                            partie.getGrilleJoueur2().getPieceCourante().mvtGauche();
                            break;
                        case KeyEvent.VK_RIGHT:
                            partie.getGrilleJoueur2().getPieceCourante().mvtDroit();
                            break;
                        case KeyEvent.VK_DOWN:
                            partie.getOrdonnanceurSimple().setTempsExecution(100);

                        case KeyEvent.VK_E:
                            partie.getGrilleJoueur1().getPieceCourante().rotation();
                            break;
                        case KeyEvent.VK_A, KeyEvent.VK_Q:
                            partie.getGrilleJoueur1().getPieceCourante().mvtGauche();
                            break;
                        case KeyEvent.VK_D:
                            partie.getGrilleJoueur1().getPieceCourante().mvtDroit();
                            break;
                        case KeyEvent.VK_S:
                            partie.getOrdonnanceurSimple().setTempsExecution(100);
                    }
                }

                else if (e.getID() == KeyEvent.KEY_RELEASED) {
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        partie.getOrdonnanceurSimple().setTempsExecution(500); // Réinitialisation du temps d'exécution
                    }
                    if (e.getKeyCode() == KeyEvent.VK_S) {
                        partie.getOrdonnanceurSimple().setTempsExecution(500); // Réinitialisation du temps d'exécution
                    }
                }

                return false;
            }
        });


    }



    static long lastTime = System.currentTimeMillis();

    @Override
    public void update(Observable o, Object arg) { // rafraichissement de la vue

        SwingUtilities.invokeLater(new Runnable() {
            //@Override
            public void run() {
                vueGrilleJoueur1.update(o, arg);
                vueGrilleJoueur2.update(o, arg);
                vuePieceSuivante.update(o, arg);

                jt.setText("J1: " + modeleJoueur1.points + ';' +
                            "j2: " + modeleJoueur2.points );

                lastTime = System.currentTimeMillis();
            }
        });

    }

    public static void main(String[] args) {

        final ImageIcon icon = new ImageIcon("data/Tetriss.jpeg");


        SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    GrilleSimple mJ1 = new GrilleSimple();
                    GrilleSimple mJ2 = new GrilleSimple();
                    VC vc = new VC(mJ1, mJ2);
                    Partie partie = new Partie(mJ1, mJ2);
                    vc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    partie.addObserver(vc);
                    vc.setVisible(true);

                }

            }
        );
    }
}
