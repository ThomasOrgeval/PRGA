package orgeval.td2;

import java.util.HashSet;
import java.util.Set;

public class TexteEnObservationV2 extends TexteModifiable implements EnObservation {

    protected Set<Observateur> ensembleObservateur;

    public TexteEnObservationV2(String texte) {
        super(texte);
        this.ensembleObservateur = new HashSet<>();
    }

    @Override
    public void ajouterObs(Observateur o) {
        this.ensembleObservateur.add(o);
    }

    @Override
    public void enleverObs(Observateur o) {
        this.ensembleObservateur.remove(o);
    }

    @Override
    public void setTexte(String texte) {
        this.texte = texte;
        this.signalerChangement();
    }

    @Override
    public void signalerChangement() {
        for (Observateur o : ensembleObservateur) {
            o.modifSignalee(this);
        }
    }
}
