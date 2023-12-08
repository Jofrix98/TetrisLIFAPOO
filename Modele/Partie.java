package Modele;

import java.util.Observable;

public class Partie {
    private GrilleSimple grilleJoueur1;
    private GrilleSimple grilleJoueur2;
    private FilePieces filePiecesSuivantes;
    private Piece pieceCourante;
    public final int LONGUEUR = 22;
    public final int LARGEUR = 10;

    public Partie(GrilleSimple _grilleJoueur1, GrilleSimple _grilleJoueur2) {

        grilleJoueur1 = _grilleJoueur1;
        grilleJoueur2 = _grilleJoueur2;
        //grilleJoueur2.setPieceCourante(grilleJoueur1.getPieceCourante());
        grilleJoueur1.setPartie(this);
        grilleJoueur2.setPartie(this);
    }

    public GrilleSimple getGrilleJoueur1() {
        return grilleJoueur1;
    }

    public GrilleSimple getGrilleJoueur2() {
        return grilleJoueur2;
    }

    public boolean partieTerminee(){
        if(grilleJoueur1.grillePleine() || grilleJoueur2.grillePleine()){

            return true;
        }
        return false;
    }



}
