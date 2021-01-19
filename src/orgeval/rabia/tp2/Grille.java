package orgeval.rabia.tp2;

public class Grille<E> {

    protected int hauteur, largeur;
    protected E[][] tab;

    public Grille(int hauteur, int largeur) {
        if (hauteur >= 0 && largeur >= 0) {
            this.hauteur = hauteur;
            this.largeur = largeur;
            this.tab = (E[][]) new Object[hauteur][largeur];
        } else throw new AssertionError("Une variable n'est pas bonne");
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public boolean coordCorrectes(int lig, int col) {
        return lig >= 1 && lig <= this.getHauteur() && col >= 1 && col <= this.getLargeur();
    }

    public String getCellule(int lig, int col) {
        return (String) this.tab[lig - 1][col - 1];
    }

    public void setCellule(int lig, int col, String ch) {
        if (coordCorrectes(lig, col)) this.tab[lig - 1][col - 1] = (E) ch;
        else throw new AssertionError("Cette case n'existe pas");
    }

    @Override
    public String toString() {
        return "Grille{" +
                "hauteur=" + tab.length +
                ", largeur=" + tab[0].length +
                '}';
    }
}