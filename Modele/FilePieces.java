package Modele;
import java.util.LinkedList;
import java.util.Queue;

public class FilePieces {
    private Queue<Piece> filePieces;
    private PieceSuivante pieceSuivante;
    GrilleSimple grille;
    public FilePieces(GrilleSimple _grille) {
        grille = _grille;
        pieceSuivante = new PieceSuivante();
        filePieces = new LinkedList<>(); // Création d'une liste liée
        remplirFilePiecesSuivantes();
    }

    private void remplirFilePiecesSuivantes() {
        filePieces.add(pieceSuivante.nouvellePiece(grille, grille.getPartie())); // Ajout premiere piece en attente
        filePieces.add(pieceSuivante.nouvellePiece(grille, grille.getPartie())); // Ajout deuxieme piece en attente
        filePieces.add(pieceSuivante.nouvellePiece(grille, grille.getPartie())); // Ajout troisieme piece en attente
    }

    public Piece getPieceSuivante() {
        return filePieces.peek();
    } // On recupere le premier élement

    public void incrementerPiece() {
        filePieces.poll(); // Retire la pièce suivante de la file.
        filePieces.add(pieceSuivante.nouvellePiece(grille, grille.getPartie())); // Ajout d'une nouvelle pièce à la fin de la file.
    }
    public Piece getDeuxiemePiece() { // On recupere le deuxieme element
        Queue<Piece> copieFile = new LinkedList<>(filePieces); // On fait une copie pour ne pas perdre la queue actuelle
        copieFile.poll();
        return copieFile.peek();
    }

    public Piece getTroisiemePiece() { // On recupere le troisieme element
        Queue<Piece> copieFile = new LinkedList<>(filePieces);
        copieFile.poll();
        copieFile.poll();
        return copieFile.peek();
    }
}
