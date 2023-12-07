/*package VueControleur;

import Modele.GrilleSimple;
import Modele.PieceFormeI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Observer;

class VueGrilleV2 extends JPanel implements Observer {

    private final static int TAILLE = 16;
    private GrilleSimple modele;

    private PieceFormeI bei = new PieceFormeI(modele);
    Canvas c;

    public VueGrilleV2(GrilleSimple _modele) {

        modele = _modele;
        setLayout(new BorderLayout());
        Dimension dim = new Dimension(TAILLE*modele.TAILLE,TAILLE*modele.TAILLE);
        //this.setPreferredSize(dim);



        //setBackground(Color.black);

        c = new Canvas() {


            public void paint(Graphics g) {


                for (int i = 0; i < modele.TAILLE; i++) {
                    for (int j = 0; j < modele.TAILLE; j++) {
                        //if (!(i == modele.getPieceCourante().getx() && j == modele.getPieceCourante().gety())) {
                        g.setColor(Color.BLACK);
                        g.fillRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
                        g.setColor(Color.BLACK);
                        g.drawRoundRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE, 1, 1);

                    }

                }



                for (int i = 0; i < modele.getPieceCourante().getLignes();i++) {
                    for (int j = 0; j < modele.getPieceCourante().getColonnes(); j++) {
                        if (modele.getPieceCourante().getTabBooli(i, j)) {
                            g.setColor(Color.magenta);
                            g.fillRect((i+modele.getPieceCourante().getX()) * TAILLE, ((modele.getPieceCourante().getColonnes()-j-1)+modele.getPieceCourante().getY()) * TAILLE, TAILLE, TAILLE);
                            g.setColor(Color.BLACK);
                            g.drawRoundRect((i+modele.getPieceCourante().getX()) * TAILLE, (j+modele.getPieceCourante().getY()) * TAILLE, TAILLE, TAILLE, 1, 1);
                        }

                    }
                }

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
*/