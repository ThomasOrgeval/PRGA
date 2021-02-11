package orgeval.td2;

public interface EnObservation {

    void ajouterObs(Observateur o);

    void enleverObs(Observateur o);

    void signalerChangement();

}
