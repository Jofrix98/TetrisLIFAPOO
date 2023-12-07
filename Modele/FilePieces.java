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
        filePieces = new LinkedList<>();
        remplirFilePiecesSuivantes();
    }

    private void remplirFilePiecesSuivantes() {
        filePieces.add(pieceSuivante.nouvellePiece(grille, grille.getPartie()));
        filePieces.add(pieceSuivante.nouvellePiece(grille, grille.getPartie()));
        filePieces.add(pieceSuivante.nouvellePiece(grille, grille.getPartie()));
    }

    public Piece getPieceSuivante() {
        return filePieces.peek();
    }

    public void incrementerPiece() {
        filePieces.poll(); // Retire la pièce suivante de la file.
        filePieces.add(pieceSuivante.nouvellePiece(grille, grille.getPartie())); // Ajoute une nouvelle pièce à la fin de la file.
    }

    public void setPieceSuivante(Piece _pieceSuivante){
        filePieces.add(_pieceSuivante);
    }
}
