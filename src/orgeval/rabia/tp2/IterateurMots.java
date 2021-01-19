package orgeval.rabia.tp2;

import java.util.Iterator;

public class IterateurMots implements Iterator<String> {

    protected Object[] objet;
    protected int cursor;

    public IterateurMots(Object[] tab) {
        this.objet = tab;
        this.cursor = 0;

        int i = 0;
        while (objet[i] == null && i != objet.length-1) {
            cursor++;
            i++;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("L'itérateur ne peut pas enlever d'élément");
    }

    @Override
    public boolean hasNext() {
        return cursor < objet.length-1;
    }

    @Override
    public String next() {
        StringBuilder ret = new StringBuilder();
        while (objet[cursor] != null) {
            ret.append(objet[cursor]);
            cursor++;
        }
        while (objet[cursor] == null && cursor != objet.length-1) cursor++;
        return ret.toString();
    }
}
