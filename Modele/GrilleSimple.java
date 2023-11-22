package Modele;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {

    public final int TAILLE = 20;

    private CaseSimple[][] tabcase;

    private CaseSimple pieceCourante = new CaseSimple(this,5,5);

    public Piece bei;

    public ArrayList<Piece> tabPiece;

    public int[][] matGrille;


    //Commentaire test
    public GrilleSimple() {

        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur
        tabPiece = new ArrayList<Piece>();
        matGrille = new int[this.TAILLE][this.TAILLE];
        nouvellePiece();
    }

    public void nouvellePiece(){
        int etendue = 7;
        int numero = (int) (Math.random() * (double)etendue) + 1;

        switch (numero) {
            case 1:
                bei = new PieceFormeC(this);
                break;
            case 2:
                bei = new PieceFormeI(this);
                break;
            case 3:
                bei = new PieceFormeJ(this);
                break;
            case 4:
                bei = new PieceFormeL(this);
                break;
            case 5:
                bei = new PieceFormeS(this);
                break;
            case 6:
                bei = new PieceFormeT(this);
                break;
            case 7:
                bei = new PieceFormeZ(this);
                break;

        }

        tabPiece.add(bei);
        System.out.println(tabPiece.size());
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
        incrementerPiece();
        for(int i=0;i<tabPiece.size();i++){
            tabPiece.get(i).run();
        }
        //pieceCourante.run();
        setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
        notifyObservers();

    }


    public CaseSimple getPieceCourante() {
        return pieceCourante;
    }
    public void incrementerPiece(){
        if(bottomLastPiece()){
            nouvellePiece();

        }
    }

    public boolean bottomLastPiece(){
        if(tabPiece.get(tabPiece.size()-1).getDY() == 0){
            return true;
        }

        return false;
    }
}
