package orgeval.rabia.tp2;

import java.util.Iterator;
import java.util.function.Consumer;

public class IterateurMots implements Iterator<String> {

    protected Object[] objet;
    protected int cursor;

    public IterateurMots(Object[] tab) {
        this.objet = tab;
        for (Object o : objet) {
            if (o == null) cursor++;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("L'itérateur ne peut pas enlever d'élément");
    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {

    }

    @Override
    public boolean hasNext() {
        return cursor + 1 < objet.length;
    }

    @Override
    public String next() {
        StringBuilder ret = new StringBuilder();
        while (objet[cursor] != null) {
            cursor++;
            ret.append(objet[cursor]);
        }
        return ret.toString();
    }
}
