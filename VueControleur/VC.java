package VueControleur;

import Modele.GrilleSimple;
import Modele.PieceFormeC;
import Modele.PieceFormeI;
import Modele.PieceFormeJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VC extends JFrame implements Observer {

    JTextField jt = new JTextField("");


    GrilleSimple modele;

    Observer vueGrille;

    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

    private Executor ex =  Executors.newSingleThreadExecutor();

    public VC(GrilleSimple _modele) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        modele = _modele;

        setSize(370, 400);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(jt, BorderLayout.NORTH);

        vueGrille = new VueGrilleV1(modele); // composants swing, saccades
        //vueGrille = new VueGrilleV2(modele); // composant AWT dédié

        jp.add((JPanel)vueGrille, BorderLayout.CENTER);
        setContentPane(jp);



        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {

                if(e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_SPACE:

                            modele.getPieceCourante().rotation();
                            break;
                        case KeyEvent.VK_LEFT:

                            modele.getPieceCourante().mvtGauche();
                            break;
                        case KeyEvent.VK_RIGHT:

                            modele.getPieceCourante().mvtDroit();

                            break;
                        case KeyEvent.VK_DOWN:
                            modele.getOrdonnanceurSimple().setTempsExecution(100);

                    }
                }

                else if (e.getID() == KeyEvent.KEY_RELEASED) {

                    switch(e.getKeyCode()){
                        case KeyEvent.VK_DOWN:
                            modele.getOrdonnanceurSimple().setTempsExecution(500);
                            break;
                    }
                }

                // Renvoyer `true` pour dire que l'event est consommé
                // (=> il ne sera traité par personne d'autre)
                // ou `false` pour propager l'event au prochain Listener
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
                vueGrille.update(o, arg);

                jt.setText("Elapsed time : " + (System.currentTimeMillis() - lastTime) + "ms - x = " + modele.getPieceCourante().getX() + " y = " + modele.getPieceCourante().getY());
                lastTime = System.currentTimeMillis();

            }
        });

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

                                       public void run() {
                                           GrilleSimple m = new GrilleSimple();
                                           VC vc = new VC(m);
                                           vc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                           m.addObserver(vc);
                                           vc.setVisible(true);

                                       }
                                   }
        );
    }
}