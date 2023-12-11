package Modele;
public class Direction {
    public enum DirectionPossible { up, down, right, left };
    private DirectionPossible directionCourante;
    public Direction(){
        choisirDirectionAleatroire();
    }
    public void choisirDirectionAleatroire(){
        int etendue = DirectionPossible.values().length;
        int numero = (int) (Math.random() * etendue);
        directionCourante = DirectionPossible.values()[numero];
    }
    public DirectionPossible getDirectionCourante(){
        return directionCourante;
    }
}
