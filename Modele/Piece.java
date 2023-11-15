package Modele;

public abstract class Piece {
    private int x;
    private int y;
    protected boolean [][] tabBool;
    protected GrilleSimple grille;

    public Piece(){
        initialiser();
    }

    public void initialiser(){
        tabBool = new boolean[5][5];
    }
}
