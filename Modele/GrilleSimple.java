package Modele;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.LinkedList;
import java.util.Queue;


public class GrilleSimple extends Observable implements Runnable {

    public final int TAILLE = 20;
    private FilePieces filePiecesSuivantes;
    private Piece pieceCourante;
    private PieceSuivante pieceSuivante;
    private OrdonnanceurSimple ordonnanceurSimple;

    public java.awt.Color[][] matGrille;


    //Commentaire test
    public GrilleSimple() {
        ordonnanceurSimple = new OrdonnanceurSimple(this);
        ordonnanceurSimple.start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur
        matGrille = new java.awt.Color[this.TAILLE][this.TAILLE];
        for (int x = 0; x < this.TAILLE; x ++){
            for(int y = 0; y < this.TAILLE; y ++){
                matGrille[x][y] = java.awt.Color.BLACK;
            }
        }
        filePiecesSuivantes = new FilePieces(this);
        pieceSuivante = new PieceSuivante();

        pieceCourante = pieceSuivante.nouvellePiece(this);
    }

    public Piece getPieceSuivante() {
        return filePiecesSuivantes.getPieceSuivante();
    }

    public void incrementerFilePiece() {
        filePiecesSuivantes.incrementerPiece();
    }

    public OrdonnanceurSimple getOrdonnanceurSimple(){
        return ordonnanceurSimple;
    }

    public void action() {
        pieceCourante.action();
    }

    public boolean validationPosition(int _nextX, int _nextY) {
        return (_nextY>=0 && _nextY < TAILLE);
    }

    public void run() {
        pieceCourante.chute();
        incrementerPiece();

        for (int y = 0; y < TAILLE; y++) {
            if (ligneEstPleine(y)) {
                effacerLigne(y);
            }
        }
        //System.out.println(terminerPartie());
        setChanged();
        notifyObservers();
    }


    public Piece getPieceCourante() {
        return pieceCourante;
    }
    public void incrementerPiece(){
        if(bottomLastPiece()){
            pieceCourante = getPieceSuivante();
            incrementerFilePiece();
        }
    }
    public boolean bottomLastPiece(){
        if(pieceCourante.getDY() == 0){
            return true;
        }

        return false;
    }

    public boolean ligneEstPleine(int ligne) {
        for (int x = 0; x < TAILLE; x++) {
            if (matGrille[x][ligne] == java.awt.Color.BLACK) {
                return false;
            }
        }
        return true;
    }

    public void effacerLigne(int ligne) {
        for (int y = ligne; y > 0; y--) {
            for (int x = 0; x < TAILLE; x++) {
                matGrille[x][y] = matGrille[x][y - 1];
            }
        }
        for (int x = 0; x < TAILLE; x++) {
            matGrille[x][0] = java.awt.Color.BLACK;
        }
    }

    public boolean terminerPartie(){
        boolean res = false;
        for(int x = 0; x < TAILLE; x++){
            if(matGrille[x][0] != java.awt.Color.BLACK){
                res = true;
                break;
            }
            else{
                res = false;
            }
        }
        return res;
    }

}
