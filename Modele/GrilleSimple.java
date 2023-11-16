package Modele;

import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {

    public final int TAILLE = 20;

    private CaseSimple[][] tabcase;

    private CaseSimple pieceCourante = new CaseSimple(this,5,5);

    public Piece bei = new PieceFormeZ(this);

    public GrilleSimple() {

        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur

    }

    public void action() {
        pieceCourante.action();
        bei.action();
        //if pieceCourante.
    }

    public boolean validationPosition(int _nextX, int _nextY) {
        return (_nextY>=0 && _nextY < TAILLE);
    }

    public void run() {

        pieceCourante.run();
        bei.run();
        setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
        notifyObservers();

    }

    public CaseSimple getPieceCourante() {
        return pieceCourante;
    }

    public Piece getPiece(){
        return bei;
    }
}
