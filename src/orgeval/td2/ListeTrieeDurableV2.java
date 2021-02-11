package orgeval.td2;

public class ListeTrieeDurableV2 extends ListeTrieeMinimale implements Observateur {

    public void ajoutTrieDurable(TexteEnObservationV2 txt) {
        boolean resultat = ajoutTrie(txt);
        if (resultat) {
            txt.ajouterObs(this);
        }
    }

    @Override
    public void modifSignalee(TexteEnObservationV2 txt) {
        boolean etaitPresent = this.retrait(txt);
        if (etaitPresent) {
            this.ajoutTrie(txt);
        }
    }
}
