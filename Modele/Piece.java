package Modele;

import javax.swing.*;
import java.awt.*;

public abstract class Piece {

    protected int id;
    protected int x;

    protected int y;

    protected int dY = 1;
    protected boolean [][] tabBool;
    protected GrilleSimple grille;


    public Piece(GrilleSimple _grille){
        grille = _grille;
    }

    public java.awt.Color couleurPiece;

    public void run(){
        boolean stop = false;
        int nextY = y;
        int nextX = x;

        nextY += dY;

        stop = checkCollision();

        if (!stop) {
            y = nextY;
            x = nextX;
            //System.out.println("pos" + x + " "+ y);
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
        x += 1;
    }

    public void mvtGauche(){
        x -= 1;
    }

    public boolean pieceAuFond(){
        if(y+1 == grille.TAILLE){
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
                if (tabBool[i][j] && ((j + y + 1 == grille.TAILLE) || (grille.matGrille[i+x][j+1+y] != java.awt.Color.BLACK))) {
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

    public void pause(){

    }

}