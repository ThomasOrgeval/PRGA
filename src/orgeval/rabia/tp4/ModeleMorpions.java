package orgeval.rabia.tp4;

public class ModeleMorpions implements SpecifModeleMorpions {

    protected int[][] morpion;
    protected int coups;
    protected Etat etat;

    public ModeleMorpions() {
        this.morpion = new int[TAILLE][TAILLE];
        for (int l = 0; l < TAILLE; ++l) {
            for (int c = 0; c < TAILLE; ++c) {
                this.morpion[l][c] = 0;
            }
        }
        this.coups = 0;
        this.etat = Etat.J1_JOUE;
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
        return !this.estFinie() && 0 < ligne && ligne <= TAILLE && 0 < colonne && colonne <= TAILLE && this.morpion[ligne - 1][colonne - 1] == 0;
    }

    @Override
    public void jouerCoup(int ligne, int colonne) {
        if (this.estCoupAutorise(ligne, colonne)) {
            this.morpion[ligne - 1][colonne - 1] = this.getJoueur();
            this.coups++;

            if (this.victoire() == 1) this.etat = Etat.J1_VAINQUEUR;
            else if (this.victoire() == 2) this.etat = Etat.J2_VAINQUEUR;
            else if (this.coups == Math.pow(TAILLE, 2)) this.etat = Etat.MATCH_NUL;
            else if (this.etat == Etat.J1_JOUE) this.etat = Etat.J2_JOUE;
            else this.etat = Etat.J1_JOUE;
        }
    }

    private int victoire() {
        for (int i = 0; i < TAILLE; i++) {
            int v1 = 1, v2 = 1;
            for (int j = 0; j < TAILLE; j++) {
                v1 *= this.morpion[i][j];
                v2 *= this.morpion[j][i];
            }

            if (v1 == Math.pow(2, TAILLE) || v2 == Math.pow(2, TAILLE)) return 2;
            if (v1 == 1 || v2 == 1) return 1;
        }

        int v1 = 1, v2 = 1;
        for (int i = 0; i < TAILLE; i++) {
            v1 *= this.morpion[i][i];
            v2 *= this.morpion[i][TAILLE - 1 - i];
        }
        if (v1 == Math.pow(2, TAILLE) || v2 == Math.pow(2, TAILLE)) return 2;
        if (v1 == 1 || v2 == 1) return 1;

        return 0;
    }
}
