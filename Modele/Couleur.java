package Modele;

import java.awt.Color;
import java.util.Random;

public class Couleur extends java.awt.Color {
    private static final Color[] COULEURS_DISPONIBLES = {Color.YELLOW, Color.CYAN, Color.GREEN, Color.ORANGE, Color.PINK, Color.RED, Color.BLUE, Color.MAGENTA, Color.WHITE};

    public Couleur() {
        // Choix aléatoire parmi les couleurs disponibles
        super(COULEURS_DISPONIBLES[new Random().nextInt(COULEURS_DISPONIBLES.length)].getRGB());
    }

    public Couleur(int rgb) {
        // Choix aléatoire parmi les couleurs disponibles
        super(rgb);
    }

    public Couleur setBlack() {
        return new Couleur(Color.BLACK.getRGB()); // Create a new instance of Couleur with black color
    }
}
