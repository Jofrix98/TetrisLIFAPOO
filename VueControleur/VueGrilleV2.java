package VueControleur;

import Modele.GrilleSimple;
import Modele.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Observer;

class VueGrilleV2 extends JPanel implements Observer {

    private final static int TAILLE = 16;
    private Partie modele;
    Canvas c;

    public VueGrilleV2(Partie _modele) {

        modele = _modele;
        setLayout(new BorderLayout());
        Dimension dim = new Dimension(TAILLE*modele.LARGEUR,TAILLE*modele.LONGUEUR);
        //this.setPreferredSize(dim);



        //setBackground(Color.black);

        c = new Canvas() {


            public void paint(Graphics g) {


                for (int i = 0; i < modele.LARGEUR; i++) {
                    for (int j = 0; j < modele.LONGUEUR; j++) {
                        //if (!(i == modele.getPieceCourante().getx() && j == modele.getPieceCourante().gety())) {
                        g.setColor(Color.BLACK);
                        g.fillRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
                        g.setColor(Color.WHITE);
                        g.drawRoundRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE, 1, 1);

                    }

                }

                g.setColor(Color.BLUE);
                g.fillRect(modele.getGrilleJoueur1().getPieceCourante().getX() * TAILLE, modele.getGrilleJoueur1().getPieceCourante().getY() * TAILLE, TAILLE, TAILLE);

            }
        };

        c.setPreferredSize(dim);
        add(c, BorderLayout.CENTER);
    }


    @Override
    public void update(Observable o, Object arg) {

        BufferStrategy bs = c.getBufferStrategy(); // bs + dispose + show : double buffering pour éviter les scintillements
        if(bs == null) {
            c.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        c.paint(g); // appel de la fonction pour dessiner
        g.dispose();
        //Toolkit.getDefaultToolkit().sync(); // forcer la synchronisation
        bs.show();
    }
}
