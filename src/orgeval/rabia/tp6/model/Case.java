package orgeval.rabia.tp6.model;

public class Case {

    protected boolean noire;
    protected char solution, proposition;
    protected String horizontal, vertical, index;

    public Case() {
        this.index = "";
        this.noire = false;
        this.solution = ' ';
        this.proposition = ' ';
        this.horizontal = "";
        this.vertical = "";
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public boolean isNoire() {
        return noire;
    }

    public void setNoire(boolean noire) {
        this.noire = noire;
    }

    public char getSolution() {
        return solution;
    }

    public void setSolution(char solution) {
        this.solution = solution;
    }

    public char getProposition() {
        return proposition;
    }

    public void setProposition(char proposition) {
        this.proposition = proposition;
    }

    public String getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(String horizontal) {
        this.horizontal = horizontal;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    @Override
    public String toString() {
        return getIndex() + " | " + isNoire() + ", " + getSolution();
    }
}
