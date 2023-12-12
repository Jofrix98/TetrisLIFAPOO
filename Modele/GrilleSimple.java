package Modele;
import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {
    public final int LONGUEUR = 22;
    public final int LARGEUR = 10;
    public java.awt.Color[][] matGrille;
    private FilePieces filePiecesSuivantes;
    private Piece pieceCourante;
    private PieceSuivante pieceSuivante;
    private Partie partie;
    private OrdonnanceurSimple ordonnanceurSimple;
    private int points;

    public GrilleSimple() {

        ordonnanceurSimple = new OrdonnanceurSimple(this);
        ordonnanceurSimple.start();

        matGrille = new java.awt.Color[this.LARGEUR][this.LONGUEUR]; // Grille de couleurs

        for (int x = 0; x < this.LARGEUR; x ++){
            for(int y = 0; y < this.LONGUEUR; y ++){
                matGrille[x][y] = java.awt.Color.BLACK; // Grille initialisée à noir
            }
        }

        // Gestion pièce courante, suivante et file d'attente
        filePiecesSuivantes = new FilePieces(this);
        pieceSuivante = new PieceSuivante();
        pieceCourante = pieceSuivante.nouvellePiece(this, partie);

    }

    public OrdonnanceurSimple getOrdonnanceurSimple(){
        return ordonnanceurSimple;
    }

    // Gestion partie
    public void setPartie(Partie _partie){
        partie = _partie;
    }
    public Partie getPartie(){
        return partie;
    }

    // Gestion file d'attente
    public Piece getPieceSuivante() {
        return filePiecesSuivantes.getPieceSuivante();
    }
    public Piece getDeuxiemePiece() {
        return filePiecesSuivantes.getDeuxiemePiece();
    }
    public Piece getTroisiemePiece() {
        return filePiecesSuivantes.getTroisiemePiece();
    }
    public void incrementerFilePiece() {
        filePiecesSuivantes.incrementerPiece();
    }

    // Gestion piece courante
    public Piece getPieceCourante() {
        return pieceCourante;
    }
    public void incrementerPiece(){
        if(pieceCourante.bottomLastPiece()){
            pieceCourante = getPieceSuivante();
            incrementerFilePiece();
        }
    }

    // Gestion effacer lignes
    public boolean ligneEstPleine(int ligne) {
        for (int x = 0; x < LARGEUR; x++) {
            if (matGrille[x][ligne] == java.awt.Color.BLACK) {
                return false;
            }
        }
        points += 100;
        return true;
    }

    public void effacerLigne(int ligne) {
        for (int y = ligne; y > 0; y--) {
            for (int x = 0; x < LARGEUR; x++) {
                matGrille[x][y] = matGrille[x][y - 1];
            }
        }
        for (int x = 0; x < LARGEUR; x++) {
            matGrille[x][0] = java.awt.Color.BLACK;
        }
    }

    // Gestion grille pleine
    public boolean grillePleine(){
        for (int i = 0; i < LARGEUR; i++){
            if(matGrille[i][0]!= java.awt.Color.BLACK){
                return true;
            }
        }
        return false;
    }

    // Gestion points
    public int getPoints(){
        return points;
    }

    // Run de la grille
    public void run() {
        pieceCourante.chute();
        incrementerPiece();

        for (int y = 0; y < LONGUEUR; y++) {
            if (ligneEstPleine(y)) {
                effacerLigne(y);
            }
        }
        setChanged();
        notifyObservers();
    }
}
