package orgeval.rabia.tp6.model;

public interface SpecifMotsCroises {

    // Nombre de rangées
    int getHauteur();

    // Nombre de colonnes
    int getLargeur();

    // Accesseurs aux cases noires
    boolean estCaseNoire(int lig, int col);

    void setCaseNoire(int lig, int col, boolean noire);

    // Accesseurs à la grille de solution
    char getSolution(int lig, int col);

    void setSolution(int lig, int col, char sol);

    // Accesseurs à la grille du joueur
    char getProposition(int lig, int col);

    void setProposition(int lig, int col, char prop);

    // Accesseurs aux définitions.
    // Le paramètre "horiz" est "true" pour les définitions
    // horizontales, "false" pour les définitions verticales?
    String getDefinition(int lig, int col, boolean horiz);

    void setDefinition(int lig, int col, boolean horiz, String def);
}
