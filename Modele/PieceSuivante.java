package Modele;

import java.awt.Color;

public class PieceSuivante{
    public PieceSuivante(){
        //vide
    }
    public Piece nouvellePiece(GrilleSimple _gille){
        int etendue = 7;
        int numero = (int) (Math.random() * (double)etendue) + 1;
        Piece pieceSuivante = null;
        switch (numero) {
            case 1:
                pieceSuivante = new PieceFormeC(_gille);
                System.out.println("C");
                break;
            case 2:
                pieceSuivante = new PieceFormeI(_gille);
                System.out.println("I");
                break;
            case 3:
                pieceSuivante = new PieceFormeJ(_gille);
                System.out.println("J");
                break;
            case 4:
                pieceSuivante = new PieceFormeL(_gille);
                System.out.println("L");
                break;
            case 5:
                pieceSuivante = new PieceFormeS(_gille);
                System.out.println("S");
                break;
            case 6:
                pieceSuivante = new PieceFormeT(_gille);
                System.out.println("T");
                break;
            case 7:
                pieceSuivante = new PieceFormeZ(_gille);
                System.out.println("Z");
                break;

        }

        return pieceSuivante;

    }


}