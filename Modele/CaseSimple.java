package Modele;

public class CaseSimple implements Runnable {

    private int x ;
    private int y;
    private int dY = 1;

    private int codeCouleur = 5;
    private GrilleSimple grille;

    public CaseSimple(GrilleSimple _grille, int _x, int _y) {
        grille = _grille;
        x = _x;
        y =_y;
    }

    public void action() {
        dY *= -1;
    }

    public void run() {
        int nextY = y;
        int nextX = x;

        nextY += dY;

        if (grille.validationPosition(nextX, nextY)) {
            y = nextY;
            x = nextX;
            //System.out.println("pos" + x + " "+ y);
        } else {
            dY *= 0;
        }


    }

    public int getCodeCouleur() {
        return codeCouleur;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }



}
