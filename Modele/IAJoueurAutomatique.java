package Modele;

public class IAJoueurAutomatique implements IA{
    @Override
    public int choisirMouvement(GrilleSimple grille) {
        int etendue = 3;
        int mouvementIA = (int) (Math.random() * (double)etendue);
        return mouvementIA - 1;
    }
}
