package orgeval.rabia.tp6.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MotsCroises implements SpecifMotsCroises {

    public Grille<Character> solution;
    private final Grille<StringProperty> proposition;
    private final Grille<String> horizontal, vertical;

    public MotsCroises(int hauteur, int largeur) {
        solution = new Grille<>(hauteur, largeur);
        proposition = new Grille<>(hauteur, largeur);
        horizontal = new Grille<>(hauteur, largeur);
        vertical = new Grille<>(hauteur, largeur);
        for (int lig = 1; lig <= getHauteur(); lig++)
            for (int col = 1; col <= getLargeur(); col++) {
                setCaseNoire(lig, col, true);
                setProposition(lig, col, ' ');
            }
    }

    public int getHauteur() {
        return solution.getHauteur();
    }

    public int getLargeur() {
        return solution.getLargeur();
    }

    public boolean coordCorrectes(int lig, int col) {
        return 1 <= lig && lig <= getHauteur()
                && 1 <= col && col <= getLargeur();
    }

    public boolean estCaseNoire(int lig, int col) {
        assert coordCorrectes(lig, col);
        return (solution.getCellule(lig, col) == '*');
    }

    public void setCaseNoire(int lig, int col, boolean noire) {
        assert coordCorrectes(lig, col);
        if (noire) solution.setCellule(lig, col, '*');
        else if (solution.getCellule(lig, col) == null) solution.setCellule(lig, col, ' ');
    }

    public char getSolution(int lig, int col) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        return solution.getCellule(lig, col);
    }

    public void setSolution(int lig, int col, char sol) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        solution.setCellule(lig, col, Character.toUpperCase(sol));
    }

    public char getProposition(int lig, int col) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        return propositionProperty(lig, col).get().charAt(0);
    }

    public void setProposition(int lig, int col, char c) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        proposition.setCellule(lig, col, new SimpleStringProperty(String.valueOf(c)));
    }

    public String getDefinition(int lig, int col, boolean horiz) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        if (horiz) return horizontal.getCellule(lig, col);
        else return vertical.getCellule(lig, col);
    }

    public void setDefinition(int lig, int col, boolean horiz, String def) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        if (horiz) horizontal.setCellule(lig, col, def);
        else vertical.setCellule(lig, col, def);
    }

    public StringProperty propositionProperty(int lig, int col) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        return this.proposition.getCellule(lig ,col);
    }

    public void reveler(int lig, int col) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        this.proposition.setCellule(lig, col, new SimpleStringProperty(String.valueOf(this.solution.getCellule(lig, col))));
    }

    @Override
    public String toString() {
        return "Solution\n" + solution
                + "\nProposition\n" + proposition
                + "\nHorizontal\n" + horizontal
                + "\nVertical\n" + vertical;
    }

}
