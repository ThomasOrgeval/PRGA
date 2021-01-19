package orgeval.rabia.tp1;

public class Grille {

    protected int hauteur, largeur;
    protected String[][] tab;

    public Grille(int hauteur, int largeur) {
        if (hauteur >= 0 && largeur >= 0) {
            this.hauteur = hauteur;
            this.largeur = largeur;
            this.tab = new String[hauteur][largeur];
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
        return this.tab[lig - 1][col - 1];
    }

    public void setCellule(int lig, int col, String ch) {
        if (coordCorrectes(lig, col)) this.tab[lig - 1][col - 1] = ch;
        else throw new AssertionError("Cette case n'existe pas");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int l = 1; l <= this.getHauteur(); l++) {
            for (int c = 1; c <= this.getLargeur(); c++) {
                result.append(this.getCellule(l, c));
                if (c != this.getLargeur()) result.append("|");
            }
            result.append("\n");
        }
        return result.toString();
    }
}