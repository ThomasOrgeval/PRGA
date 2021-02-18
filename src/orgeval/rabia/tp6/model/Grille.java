package orgeval.rabia.tp6.model;

public class Grille {

    protected int hauteur, largeur;
    protected Case[][] tab;

    public Grille(int hauteur, int largeur) {
        if (hauteur >= 0 && largeur >= 0) {
            this.hauteur = hauteur;
            this.largeur = largeur;
            this.tab = new Case[hauteur][largeur];
            for (int i = 0; i < hauteur; i++)
                for (int j = 0; j < largeur; j++) {
                    this.tab[i][j] = new Case();
                    this.tab[i][j].setIndex(i + ", " + j);
                }
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

    public Case getCellule(int lig, int col) {
        return this.tab[lig - 1][col - 1];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int l = 1; l <= this.getHauteur(); l++) {
            for (int c = 1; c <= this.getLargeur(); c++) {
                result.append(this.getCellule(l, c).getIndex());
                if (c != this.getLargeur()) result.append("|");
            }
            result.append("\n");
        }
        return result.toString();
    }
}