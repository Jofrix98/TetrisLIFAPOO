package Modele;


public abstract class Piece implements Runnable{

    protected int x;
    protected int y;
    protected boolean [][] tabBool;
    public java.awt.Color couleurPiece;
    protected int dY = 1;
    protected Direction direction;
    private boolean mvtDroitt;
    private OrdonnanceurSimple ordoPiece;
    protected GrilleSimple grille;
    protected Partie partie;

    public Piece(GrilleSimple _grille, Partie _partie){
        partie = _partie;
        ordoPiece = new OrdonnanceurSimple(this);
        ordoPiece.start();
        ordoPiece.setTempsExecution(10);
        grille = _grille;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public boolean getTabBooli(int i,int j){
        return tabBool[i][j];
    }


    public int getDY(){
        return dY;
    }

    public int getLignes() {
        return tabBool.length;
    }

    public int getColonnes(){
        return tabBool[0].length;
    }

    public boolean bottomLastPiece(){
        if(this.getDY() == 0){
            return true;
        }
        return false;
    }

    public void chute(){
        boolean stop;
        int nextY = y;
        nextY += dY;
        stop = checkCollision();

        if (!stop) {
            y = nextY;
        }
        else {
            dY = 0;
        }
        if(dY == 0){
            fusionAvecMatGrille();
        }
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

    public void rotation(){
        int[][] tabBoolint = new int[getLignes()][getColonnes()];
        int[][] res = new int[getLignes()][getColonnes()];
        boolean[][] newtabBool = new boolean[getLignes()][getColonnes()];

        boolean positionValide = true;

        for(int i=0;i<getLignes();i++){
            for(int j=0;j<getColonnes();j++){
                if(tabBool[i][j]){
                    tabBoolint[i][j] = 1;
                }

            }

        }

        for (int i = 0; i < getLignes(); i++) {
            for (int j = 0; j < getColonnes(); j++) {
                res[getColonnes() - 1- j][i] = tabBoolint[i][j];
            }
        }

        for(int i=0;i<getLignes();i++){
            for(int j=0;j<getColonnes();j++){
                newtabBool[i][j] = false;
            }
        }

        for(int i=0;i<getLignes();i++){
            for(int j=0;j<getColonnes();j++){
                if(res[i][j] == 1){
                    newtabBool[i][j] = true;
                }
            }
        }

        for(int i=0;i< getLignes();i++){
            for(int j=0;j< getColonnes();j++){
                if (newtabBool[i][j] && (((i+x) >= grille.LARGEUR) ||
                        ((i+x) < 0)  ||
                        ((j+y) >= grille.LONGUEUR) ||
                        ((j+y) <= 0) ||
                        (grille.matGrille[i+x][j+y] != java.awt.Color.BLACK))) {
                    positionValide = false;
                }
            }
        }

        if(positionValide){
            tabBool = newtabBool;
        }

        y -= 1;

    }

    public void run(){
        if(mvtDroitt){
            x += 1;
            mvtDroitt = false;
        }
    }
}


