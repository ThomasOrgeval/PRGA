package orgeval.rabia.tp2;

public class Case<T> {

    protected boolean noire;
    protected T index, solution, proposition, horizontal, vertical;

    public boolean isNoire() {
        return noire;
    }

    public void setNoire(boolean noire) {
        this.noire = noire;
    }

    public T getSolution() {
        return solution;
    }

    public void setSolution(T solution) {
        this.solution = solution;
    }

    public T getProposition() {
        return proposition;
    }

    public void setProposition(T proposition) {
        this.proposition = proposition;
    }

    public T getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(T horizontal) {
        this.horizontal = horizontal;
    }

    public T getVertical() {
        return vertical;
    }

    public void setVertical(T vertical) {
        this.vertical = vertical;
    }

    public T getIndex() {
        return index;
    }

    public void setIndex(T index) {
        this.index = index;
    }
}
