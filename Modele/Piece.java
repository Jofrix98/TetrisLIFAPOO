package Modele;

import javax.swing.*;
import java.awt.*;

public abstract class Piece implements Runnable{

    protected int id;
    protected int x;
    protected int y;

    private boolean mvtDroitt;
    private OrdonnanceurSimple ordoPiece;
    protected int dY = 1;
    protected boolean [][] tabBool;
    protected GrilleSimple grille;
    protected Partie partie;

    public Piece(GrilleSimple _grille, Partie _partie){
        partie = _partie;
        ordoPiece = new OrdonnanceurSimple(this);
        ordoPiece.start();
        ordoPiece.setTempsExecution(10);
        grille = _grille;

    }

    public java.awt.Color couleurPiece;


    public void run(){
        if(mvtDroitt){
            x += 1;
            mvtDroitt = false;
        }

    }

    public void chute(){
        boolean stop = false;
        int nextY = y;
        int nextX = x;

        nextY += dY;

        stop = checkCollision();


        if (!stop) {
            y = nextY;
            //x = nextX;

        } else {
            dY = 0;
        }

        if(dY == 0){
            fusionAvecMatGrille();
        }
    }

    public void action() {
        dY *= -1;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public boolean getTabBooli(int i,int j){
        return tabBool[i][j];
    }

    public abstract void rotation();


    public int getDY(){
        return dY;
    }

    public int getLignes() {
        return tabBool.length;
    }

    public int getColonnes(){
        return tabBool[0].length;
    }

    public void mvtDroit(){
        boolean position_valide = true;
        for(int i=0;i< getLignes();i++){
            for(int j=0;j< getColonnes();j++){
                if (tabBool[i][j] && ((i + x + 1 == grille.LARGEUR) || (grille.matGrille[i+x+1][j+y] != java.awt.Color.BLACK))) {
                    position_valide = false;
                }
            }
        }

        if(position_valide){

            position_valide = true;

        }

        mvtDroitt = position_valide;

    }

    public void mvtGauche(){
        boolean position_valide = true;
        for(int i=0;i< getLignes();i++){
            for(int j=0;j< getColonnes();j++){
                if (tabBool[i][j] && ((i + x - 1 < 0) || (grille.matGrille[i+x-1][j+y] != java.awt.Color.BLACK))) {
                    position_valide = false;
                }
            }
        }

        if(position_valide){

            x-=1;

        }
    }

    public boolean pieceAuFond(){
        if(y+1 == grille.LONGUEUR){
            return true;
        }
        else {
            return false;
        }
    }


    public boolean checkCollision(){
        boolean stop = false;
        for(int i=0;i< getLignes();i++){
            for(int j=0;j< getColonnes();j++){
                if (tabBool[i][j] && ((j + y + 1 == grille.LONGUEUR) || (grille.matGrille[i+x][j+1+y] != java.awt.Color.BLACK))) {
                    stop = true;
                }
            }
        }

        return stop;
    }

    public void fusionAvecMatGrille(){
        for(int i=0;i< getLignes();i++){
            for(int j=0;j< getColonnes();j++){
                if (tabBool[i][j]) {
                    grille.matGrille[i+x][j+y] = couleurPiece;
                }
            }
        }
    }


    public void descenteRapide(){

        grille.getOrdonnanceurSimple().setTempsExecution(100);

    }

    public boolean estAuSommet(){
        for(int i=0;i< getLignes();i++){
            for(int j=0;j< getColonnes();j++){
                if (tabBool[i][j] && (j + y - 1 <= 0) ) {

                    return true;
                }
            }
        }

        return false;

    }

}


