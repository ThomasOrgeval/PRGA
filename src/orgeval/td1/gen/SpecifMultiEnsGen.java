package orgeval.td1.gen;

public interface SpecifMultiEnsGen<T> {

    void ajouter(T val);

    boolean enlever(T val);

    int frequence(T val);

    boolean present(T val);

    int taille();

}
