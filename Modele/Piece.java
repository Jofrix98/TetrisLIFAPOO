package Modele;

public abstract class Piece {
    protected int x;
    protected int y;
    protected int dY = 1;
    protected boolean [][] tabBool;
    protected GrilleSimple grille;

    public Piece(GrilleSimple _grille){
        grille = _grille;
    }

    public abstract void run();


    public void action() {
        dY *= -1;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public boolean getTabBooli(int i,int j){
        return tabBool[i][j];
    }

    public abstract void rotation();

    public int getLignes() {
        return tabBool.length;
    }

    public int getColonnes(){
        return tabBool[0].length;
    }

    public void mvtDroit(){
        x += 1;
        y -= 1;
    }

    public void mvtGauche(){
        x -= 1;
        y -= 1;
    }
}


