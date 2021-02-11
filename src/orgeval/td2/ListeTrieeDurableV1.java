package orgeval.td2;

@SuppressWarnings("serial")
public class ListeTrieeDurableV1 extends ListeTrieeMinimale {

    public ListeTrieeDurableV1() {
        super();
    }

    public boolean ajoutTrieDurable(TexteEnObservationV1 txt) {
        boolean resultat = ajoutTrie(txt);
        if (resultat) {
            txt.ajouterObs(this);
        }
        return resultat;
    }

    // modifSignalee(txt) est appelée pour signaler que l'élément
    // txt a changé, d'où le risque pour la liste de ne plus être
    // trié. Il faut donc enlever puis rajouter à nouveau
    // l'élément afin de garantir l'état trié croissant.
    public void modifSignalee(TexteEnObservationV1 txt) {
        boolean etaitPresent = this.retrait(txt);
        if (etaitPresent) {
            this.ajoutTrie(txt);
        }
    }
}
