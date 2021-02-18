package orgeval.rabia.tp6.model;

public class Grille<T> {
    private final int hauteur, largeur;
    protected Object[][] cellules;

    public Grille(int hauteur, int largeur) {
        assert hauteur >= 0;
        assert largeur >= 0;
        this.hauteur = hauteur;
        this.largeur = largeur;
        cellules = new Object[hauteur][largeur];
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public boolean coordCorrectes(int lig, int col) {
        return 1 <= lig && lig <= getHauteur()
                && 1 <= col && col <= getLargeur();
    }

    public void setCellule(int lig, int col, T ch) {
        assert coordCorrectes(lig, col);
        cellules[lig - 1][col - 1] = ch;
    }

    @SuppressWarnings("unchecked")
    public T getCellule(int lig, int col) {
        assert coordCorrectes(lig, col);
        return (T) cellules[lig - 1][col - 1];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int l = 1; l <= this.getHauteur(); l++) {
            for (int c = 1; c <= this.getLargeur(); c++) {
                if (c > 1) res.append('|');
                res.append(this.getCellule(l, c));
            }
            res.append('\n');
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Grille<String> maGrille = new Grille<>(3, 5);
        for (int l = 1; l <= maGrille.getHauteur(); l++) {
            String texteLigne = Integer.toString(l);
            for (int c = 1; c <= maGrille.getLargeur(); c++) {
                maGrille.setCellule(l, c, texteLigne + ',' + c);
            }
        }
        System.out.println(maGrille);
    }
}
