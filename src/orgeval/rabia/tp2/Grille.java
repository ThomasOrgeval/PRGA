package orgeval.rabia.tp2;

public class Grille<T> {

    protected int hauteur, largeur;
    protected Case<T>[][] tab;

    public Grille(int hauteur, int largeur) {
        if (hauteur >= 0 && largeur >= 0) {
            this.hauteur = hauteur;
            this.largeur = largeur;
            this.tab = (Case<T>[][]) new Case[hauteur][largeur];
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

    public Case<T> getCellule(int lig, int col) {
        return this.tab[lig - 1][col - 1];
    }

    public void setCellule(int lig, int col, T ch) {
        if (coordCorrectes(lig, col)) {
            this.tab[lig - 1][col - 1] = new Case<>();
            this.tab[lig - 1][col - 1].setIndex(ch);
        }
        else throw new AssertionError("Cette case n'existe pas");
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int l = 1; l <= this.getHauteur(); l++) {
            for (int c = 1; c <= this.getLargeur(); c++) {
                ret.append(this.getCellule(l, c).getIndex());
                if (c != this.getLargeur()) ret.append("|");
            }
            ret.append("\n");
        }
        return ret.toString();
    }

    public IterateurMots iterateurMots(boolean horizontal, int num) {
        IterateurMots iterateurMots;
        if (horizontal) iterateurMots = new IterateurMots(this.tab[num]);
        else {
            Object[] tab2 = new Object[getHauteur()];
            for (int i = 1; i <= getHauteur(); i++) {
                tab2[i] = tab[i][num];
            }
            iterateurMots = new IterateurMots(tab2);
        }
        return iterateurMots;
    }
}