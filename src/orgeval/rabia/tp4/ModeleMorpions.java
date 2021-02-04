package orgeval.rabia.tp4;

public class ModeleMorpions implements SpecifModeleMorpions {

    protected int[][] morpion;
    protected int coups;
    protected Etat etat;

    public ModeleMorpions() {
        this.morpion = new int[TAILLE][TAILLE];
        for (int l = 1; l <= TAILLE; ++l) {
            for (int c = 1; c <= TAILLE; ++c) {
                this.setVal(l, c, 0);
            }
        }
        this.coups = 0;
        this.etat = Etat.J1_JOUE;
    }

    private void setVal(int ligne, int colonne, int val) {
        this.morpion[ligne - 1][colonne - 1] = val;
    }

    private int getVal(int ligne, int colonne) {
        return this.morpion[ligne - 1][colonne - 1];
    }

    @Override
    public Etat getEtatJeu() {
        return this.etat;
    }

    @Override
    public int getJoueur() {
        return this.etat == Etat.J1_JOUE ? 1 : this.etat == Etat.J2_JOUE ? 2 : 0;
    }

    @Override
    public int getVainqueur() {
        return this.etat == Etat.J1_VAINQUEUR ? 1 : this.etat == Etat.J2_VAINQUEUR ? 2 : 0;
    }

    @Override
    public int getNombreCoups() {
        return this.coups;
    }

    @Override
    public boolean estFinie() {
        return this.etat != Etat.J1_JOUE && this.etat != Etat.J2_JOUE;
    }

    @Override
    public boolean estCoupAutorise(int ligne, int colonne) {
        return !this.estFinie() && this.coupValide(ligne, colonne) && this.getVal(ligne, colonne) == 0;
    }

    private boolean coupValide(int ligne, int colonne) {
        return 1 <= ligne && ligne <= TAILLE && 1 <= colonne && colonne <= TAILLE;
    }

    @Override
    public void jouerCoup(int ligne, int colonne) {
        assert !this.estFinie();
        assert this.estCoupAutorise(ligne, colonne);
        if (this.estCoupAutorise(ligne, colonne)) {
            this.setVal(ligne, colonne, this.getJoueur());
            this.coups++;
            this.setEtat();
        }
    }

    private void setEtat() {
        if (this.victoire() == 1) this.etat = Etat.J1_VAINQUEUR;
        else if (this.victoire() == 2) this.etat = Etat.J2_VAINQUEUR;
        else if (this.coups == 9) this.etat = Etat.MATCH_NUL;
        else if (this.etat == Etat.J1_JOUE) this.etat = Etat.J2_JOUE;
        else this.etat = Etat.J1_JOUE;
    }

    private int victoire() {
        for (int i = 1; i <= TAILLE; i++) {
            int v1 = 1, v2 = 1;
            for (int j = 1; j <= TAILLE; j++) {
                v1 *= this.getVal(i, j);
                v2 *= this.getVal(j, i);
            }

            if (v1 == 8 || v2 == 8) return 2;
            if (v1 == 1 || v2 == 1) return 1;
        }

        int v1 = 1, v2 = 1;
        for (int i = 1; i <= TAILLE; i++) {
            v1 *= this.getVal(i, i);
            v2 *= this.getVal(i, 4 - i);
        }
        if (v1 == 8 || v2 == 8) return 2;
        if (v1 == 1 || v2 == 1) return 1;

        return 0;
    }
}
