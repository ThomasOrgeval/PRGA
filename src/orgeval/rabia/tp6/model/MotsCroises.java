package orgeval.rabia.tp6.model;

public class MotsCroises extends Grille {

    public MotsCroises(int hauteur, int largeur) {
        super(hauteur, largeur);
    }

    public boolean estCaseNoire(int lig, int col) {
        return coordCorrectes(lig, col) && this.tab[lig - 1][col - 1].isNoire();
    }

    public void setCaseNoire(int lig, int col, boolean b) {
        if (coordCorrectes(lig, col)) this.tab[lig - 1][col - 1].setNoire(b);
        else throw new AssertionError("Cette case n'existe pas");
    }

    public void setGrilleNoire() {
        for (int i = 1; i <= this.getHauteur(); i++) {
            for (int j = 1; j <= this.getLargeur(); j++) {
                setCaseNoire(i, j, true);
                setSolution(i, j, '*');
            }
        }
    }

    public String getDefinition(int lig, int col, boolean b) {
        if (b) return this.tab[lig - 1][col - 1].getHorizontal();
        else return this.tab[lig - 1][col - 1].getVertical();
    }

    public void setDefinition(int lig, int col, boolean b, String s) {
        if (coordCorrectes(lig, col)) {
            if (b) this.tab[lig - 1][col - 1].setHorizontal(s);
            else this.tab[lig - 1][col - 1].setVertical(s);
        }
    }

    public char getSolution(int lig, int col) {
        return this.tab[lig - 1][col - 1].getSolution();
    }

    public void setSolution(int lig, int col, char lettre) {
        this.tab[lig - 1][col - 1].setSolution(lettre);
    }

    public char getProposition(int lig, int col) {
        return this.tab[lig - 1][col - 1].getProposition();
    }

    public void setProposition(int lig, int col, char lettre) {
        this.tab[lig - 1][col - 1].setProposition(lettre);
    }

    public String getIndex(int lig, int col) {
        return this.tab[lig - 1][col - 1].getIndex();
    }

    public void setIndex(int lig, int col, String s) {
        this.tab[lig - 1][col - 1].setIndex(s);
    }
}
