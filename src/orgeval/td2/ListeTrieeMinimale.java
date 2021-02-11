package orgeval.td2;

import java.util.TreeSet;

@SuppressWarnings("serial")
public class ListeTrieeMinimale extends TreeSet<TexteConsultable> implements ListeTriee {

    public ListeTrieeMinimale() {
        super();
    }

    @Override
    public boolean ajoutTrie(TexteConsultable texte) {
        return this.add(texte);
    }

    @Override
    public boolean retrait(TexteConsultable texte) {
        return this.remove(texte);
    }
}
