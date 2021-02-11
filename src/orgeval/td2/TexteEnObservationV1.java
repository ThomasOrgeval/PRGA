package orgeval.td2;

import java.util.*;

public class TexteEnObservationV1 extends TexteModifiable {

    protected Set<ListeTrieeDurableV1> ensembleObservateur;

    public TexteEnObservationV1(String texte) {
        super(texte);
        this.ensembleObservateur = new HashSet<>();
    }

    public void ajouterObs(ListeTrieeDurableV1 lt) {
        this.ensembleObservateur.add(lt);
    }

    public void enleverObs(ListeTrieeDurableV1 lt) {
        this.ensembleObservateur.remove(lt);
    }

    @Override
    public void setTexte(String texte) {
        this.texte = texte;
        this.signalerChangement();
    }

    private void signalerChangement() {
        for (ListeTrieeDurableV1 lt : ensembleObservateur) {
            lt.modifSignalee(this);
        }
    }

}
