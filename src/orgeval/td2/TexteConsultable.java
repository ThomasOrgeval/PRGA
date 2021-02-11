package orgeval.td2;

public class TexteConsultable implements Comparable<TexteConsultable> {

    protected String texte;

    public TexteConsultable(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return texte;
    }

    @Override
    public int compareTo(TexteConsultable autre) {
        return this.texte.compareTo(autre.texte);
    }
}


