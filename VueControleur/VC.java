package VueControleur;

import Modele.GrilleSimple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VC extends JFrame implements Observer {

    JTextField jt = new JTextField("");

    JButton jb = new JButton("Rotation");

    JButton jb2 = new JButton("Droite");

    JButton jb3 = new JButton("Gauche");

    GrilleSimple modele;

    Observer vueGrille;

    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

    private Executor ex =  Executors.newSingleThreadExecutor();

    public VC(GrilleSimple _modele) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        modele = _modele;

        setSize(450, 400);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(jt, BorderLayout.NORTH);
        jp.add(jb, BorderLayout.SOUTH);
        jp.add(jb2, BorderLayout.EAST);
        jp.add(jb3, BorderLayout.WEST);

        vueGrille = new VueGrilleV1(modele); // composants swing, saccades
        //vueGrille = new VueGrilleV2(modele); // composant AWT dédié

        jp.add((JPanel)vueGrille, BorderLayout.CENTER);
        setContentPane(jp);

        jb3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                modele.getPieceCourante().mvtGauche();

            }

        });
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modele.getPieceCourante().mvtDroit();
            }

        });

        jb.addActionListener(new ActionListener() { //évènement bouton : object contrôleur qui réceptionne
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.getPieceCourante().rotation();
            }
        });


        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                System.out.println("key event");
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    System.out.println(e.getKeyCode());
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
