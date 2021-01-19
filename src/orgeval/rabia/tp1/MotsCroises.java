package orgeval.rabia.tp1;

public class MotsCroises extends Grille{

    protected boolean[][] noire;
    protected Character[][] solution, proposition;
    protected String[][] horizontal;

    public MotsCroises(int hauteur, int largeur) {
        super(hauteur, largeur);
        this.noire = new boolean[hauteur][largeur];
        this.solution = new Character[hauteur][largeur];
        this.proposition = new Character[hauteur][largeur];
        this.horizontal = new String[hauteur][largeur];
    }

    public boolean estCaseNoire(int lig, int col) {
        return coordCorrectes(lig, col) && this.noire[lig - 1][col - 1];
    }

    public void setCaseNoire(int lig, int col, boolean b) {
        if(coordCorrectes(lig, col)) this.noire[lig - 1][col - 1] = b;
        else throw new AssertionError("Cette case n'existe pas");
    }

    public Object getDefinition(int lig, int col, boolean b) {
        if (b) return this.horizontal[lig - 1][col - 1];
        else return this.tab[lig - 1][col - 1];
    }

    public void setDefinition(int lig, int col, boolean b, String s) {
        if (b) this.horizontal[lig - 1][col - 1] = s;
        else this.tab[lig - 1][col - 1] = s;
    }

    public Object getSolution(int lig, int col) {
        return this.solution[lig - 1][col - 1];
    }

    public void setSolution(int lig, int col, char lettre) {
        this.solution[lig - 1][col - 1] = lettre;
    }

    public Object getProposition(int lig, int col) {
        return this.proposition[lig - 1][col - 1];
    }

    public void setProposition(int lig, int col, char lettre) {
        this.proposition[lig - 1][col - 1] = lettre;
    }
}
