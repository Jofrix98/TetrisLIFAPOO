package Modele;

import java.util.Observable;

public class Partie extends Observable implements Runnable {
    private GrilleSimple grilleJoueur1;
    private GrilleSimple grilleJoueur2;
    private FilePieces filePiecesSuivantes;
    private Piece pieceCourante;
    private OrdonnanceurSimple ordonnanceurSimple;
    public final int LONGUEUR = 22;
    public final int LARGEUR = 10;

    public Partie(GrilleSimple _grilleJoueur1, GrilleSimple _grilleJoueur2) {
        ordonnanceurSimple = new OrdonnanceurSimple(this);
        ordonnanceurSimple.start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur
        grilleJoueur1 = _grilleJoueur1;
        grilleJoueur2 = _grilleJoueur2;
        filePiecesSuivantes = grilleJoueur1.getFilePiecesSuivantes();
        grilleJoueur2.setFilePiecesSuivantes(filePiecesSuivantes);
        grilleJoueur1.setPartie(this);
        grilleJoueur2.setPartie(this);
    }

    public OrdonnanceurSimple getOrdonnanceurSimple(){
        return ordonnanceurSimple;
    }

    public GrilleSimple getGrilleJoueur1() {
        return grilleJoueur1;
    }

    public GrilleSimple getGrilleJoueur2() {
        return grilleJoueur2;
    }
    public void run(){
        grilleJoueur1.run();
        //grilleJoueur2.run();
        setChanged();
        notifyObservers();
    }

}
