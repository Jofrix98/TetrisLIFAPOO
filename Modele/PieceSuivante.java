package Modele;

import java.awt.Color;

public class PieceSuivante{
    public PieceSuivante(){
        //vide
    }
    public Piece nouvellePiece(GrilleSimple _gille, Partie _partie){ // on retourne une piece random
        int etendue = 7;
        int numero = (int) (Math.random() * (double)etendue) + 1;
        Piece pieceSuivante = null;
        switch (numero) {
            case 1:
                pieceSuivante = new PieceFormeC(_gille, _partie);

                break;
            case 2:
                pieceSuivante = new PieceFormeI(_gille, _partie);

                break;
            case 3:
                pieceSuivante = new PieceFormeJ(_gille,_partie);

                break;
            case 4:
                pieceSuivante = new PieceFormeL(_gille, _partie);

                break;
            case 5:
                pieceSuivante = new PieceFormeS(_gille, _partie);

                break;
            case 6:
                pieceSuivante = new PieceFormeT(_gille, _partie);

                break;
            case 7:
                pieceSuivante = new PieceFormeZ(_gille, _partie);

                break;

        }

        return pieceSuivante;

    }


}