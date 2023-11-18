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
                modele.bei.mvtGauche();

            }

        });
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modele.bei.mvtDroit();
                ex.execute(new Runnable() {
                  @Override
                  public void run() {

                  }
                });
            }

        });

        jb.addActionListener(new ActionListener() { //évènement bouton : object contrôleur qui réceptionne
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.bei.rotation();
                ex.execute(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { //évènement clavier : object contrôleur qui réceptionne
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE: modele.action();
                }
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

                jt.setText("Elapsed time : " + (System.currentTimeMillis() - lastTime) + "ms - x = " + modele.getPieceCourante().getx() + " y = " + modele.getPieceCourante().gety());
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
