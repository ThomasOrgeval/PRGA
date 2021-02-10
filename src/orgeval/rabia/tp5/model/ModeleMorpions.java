package orgeval.rabia.tp5.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModeleMorpions implements SpecifModeleMorpions {

    protected ReadOnlyIntegerWrapper[][] morpion = new ReadOnlyIntegerWrapper[TAILLE][TAILLE];
    protected ReadOnlyIntegerWrapper coups = new ReadOnlyIntegerWrapper(0);
    protected Etat etat;
    private final StringProperty symboleJ = new SimpleStringProperty();

    public ModeleMorpions() {
        for (int l = 0; l < TAILLE; ++l) {
            for (int c = 0; c < TAILLE; ++c) {
                this.morpion[l][c] = new ReadOnlyIntegerWrapper();
                this.morpion[l][c].set(0);
            }
        }
        this.etat = Etat.J1_JOUE;
        setSymboleJ(symboleJoueur(1));
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
        return this.coups.get();
    }

    @Override
    public boolean estFinie() {
        return this.etat != Etat.J1_JOUE && this.etat != Etat.J2_JOUE;
    }

    @Override
    public boolean estCoupAutorise(int ligne, int colonne) {
        return !this.estFinie() && 0 < ligne && ligne <= TAILLE && 0 < colonne && colonne <= TAILLE && this.morpion[ligne - 1][colonne - 1].get() == 0;
    }

    @Override
    public void jouerCoup(int ligne, int colonne) {
        if (this.estCoupAutorise(ligne, colonne)) {
            this.morpion[ligne - 1][colonne - 1].set(this.getJoueur());
            this.coups.set(this.coups.get() + 1);

            if (this.victoire() == 1) {
                this.etat = Etat.J1_VAINQUEUR;
                setSymboleJ(" ");
            } else if (this.victoire() == 2) {
                this.etat = Etat.J2_VAINQUEUR;
                setSymboleJ(" ");
            } else if (this.coups.get() == Math.pow(TAILLE, 2)) {
                this.etat = Etat.MATCH_NUL;
                setSymboleJ(" ");
            } else if (this.etat == Etat.J1_JOUE) {
                this.etat = Etat.J2_JOUE;
                setSymboleJ(symboleJoueur(2));
            } else {
                this.etat = Etat.J1_JOUE;
                setSymboleJ(symboleJoueur(1));
            }
        }
    }

    private int victoire() {
        for (int i = 0; i < TAILLE; i++) {
            int v1 = 1, v2 = 1;
            for (int j = 0; j < TAILLE; j++) {
                v1 *= this.morpion[i][j].get();
                v2 *= this.morpion[j][i].get();
            }

            if (v1 == Math.pow(2, TAILLE) || v2 == Math.pow(2, TAILLE)) return 2;
            if (v1 == 1 || v2 == 1) return 1;
        }

        int v1 = 1, v2 = 1;
        for (int i = 0; i < TAILLE; i++) {
            v1 *= this.morpion[i][i].get();
            v2 *= this.morpion[i][TAILLE - 1 - i].get();
        }
        if (v1 == Math.pow(2, TAILLE) || v2 == Math.pow(2, TAILLE)) return 2;
        if (v1 == 1 || v2 == 1) return 1;

        return 0;
    }

    public ReadOnlyIntegerProperty nbCoupsProperty() {
        return coups.getReadOnlyProperty();
    }

    public StringProperty getSymboleJ() {
        return this.symboleJ;
    }

    public String getSymboleJValue() {
        return symboleJ.get();
    }

    private void setSymboleJ(String s) {
        symboleJ.set(s);
    }

    public String symboleJoueur(int val) {
        switch (val) {
            case 1:
                return "x";
            case 2:
                return "o";
            default:
                return " ";
        }
    }

    public ReadOnlyIntegerProperty morpionProperty(int ligne, int colonne) {
        return morpion[ligne - 1][colonne - 1].getReadOnlyProperty();
    }

    private int getVal(int ligne, int colonne) {
        return morpion[ligne - 1][colonne - 1].get();
    }

    private void setVal(int ligne, int colonne, int val) {
        morpion[ligne - 1][colonne - 1].set(val);
    }
}
