package orgeval.rabia.tp2;

public class MotsCroisesAvecHeritage<T> extends Grille<T> {

    public MotsCroisesAvecHeritage(int hauteur, int largeur) {
        super(hauteur, largeur);
    }

    public boolean estCaseNoire(int lig, int col) {
        return coordCorrectes(lig, col) && this.tab[lig - 1][col - 1].isNoire();
    }

    public void setCaseNoire(int lig, int col, boolean b) {
        if (coordCorrectes(lig, col)) this.tab[lig - 1][col - 1].setNoire(b);
        else throw new AssertionError("Cette case n'existe pas");
    }

    public Object getDefinition(int lig, int col, boolean b) {
        if (b) return this.tab[lig - 1][col - 1].getHorizontal();
        else return this.tab[lig - 1][col - 1].getVertical();
    }

    public void setDefinition(int lig, int col, boolean b, T s) {
        if (b) this.tab[lig - 1][col - 1].setHorizontal(s);
        else this.tab[lig - 1][col - 1].setVertical(s);
    }

    public Object getSolution(int lig, int col) {
        return this.tab[lig - 1][col - 1].getSolution();
    }

    public void setSolution(int lig, int col, T lettre) {
        this.tab[lig - 1][col - 1].setSolution(lettre);
    }

    public Object getProposition(int lig, int col) {
        return this.tab[lig - 1][col - 1].getProposition();
    }

    public void setProposition(int lig, int col, T lettre) {
        this.tab[lig - 1][col - 1].setProposition(lettre);
    }
}