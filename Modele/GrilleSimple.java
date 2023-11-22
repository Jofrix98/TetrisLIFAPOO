package Modele;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {

    public final int TAILLE = 20;

    private Piece pieceCourante;


    public java.awt.Color[][] matGrille;


    //Commentaire test
    public GrilleSimple() {

        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur
        matGrille = new java.awt.Color[this.TAILLE][this.TAILLE];
        for (int x = 0; x < this.TAILLE; x ++){
            for(int y = 0; y < this.TAILLE; y ++){
                matGrille[x][y] = java.awt.Color.BLACK;
            }
        }
        nouvellePiece();
    }

    public void nouvellePiece(){
        int etendue = 7;
        int numero = 2;/*(int) (Math.random() * (double)etendue) + 1;*/

        switch (numero) {
            case 1:
                pieceCourante = new PieceFormeC(this);
                break;
            case 2:
                pieceCourante = new PieceFormeI(this);
                break;
            case 3:
                pieceCourante = new PieceFormeJ(this);
                break;
            case 4:
                pieceCourante = new PieceFormeL(this);
                break;
            case 5:
                pieceCourante = new PieceFormeS(this);
                break;
            case 6:
                pieceCourante = new PieceFormeT(this);
                break;
            case 7:
                pieceCourante = new PieceFormeZ(this);
                break;

        }

    }

    public void action() {
        pieceCourante.action();
    }

    public boolean validationPosition(int _nextX, int _nextY) {
        return (_nextY>=0 && _nextY < TAILLE);
    }

    public void run() {
        pieceCourante.run();
        updateGrille();
        setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
        notifyObservers();

    }


    public Piece getPieceCourante() {
        return pieceCourante;
    }
    public void incrementerPiece(){
        if(bottomLastPiece()){
            nouvellePiece();

        }
    }

    public boolean bottomLastPiece(){
        if(pieceCourante.getDY() == 0){
            return true;
        }

        return false;
    }

    public boolean lignePleine(int index){
        int compt = 0;
            for(int j = 0; j < TAILLE; j++){
                if(matGrille[index][j] != Color.BLACK){
                    compt ++;
                }
            }
        if(compt == TAILLE-1){
                System.out.println("Oui c'est plein");
                return true;
            }

        return false;
    }

    public void decalerLigneGrille(int index){


        for(int i=0;i<TAILLE;i++){
            matGrille[index][i] = Color.BLACK;
        }

        for(int j=index;j>1;j--){
            for(int z = 0; z<TAILLE;z++){
                matGrille[j][z] = matGrille[j-1][z];
            }
        }

    }

    public void updateGrille(){
        for(int i=0;i<TAILLE;i++){
            if(lignePleine(i)){
                decalerLigneGrille(i);
            }
        }
    }


}
