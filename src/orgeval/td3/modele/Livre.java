package orgeval.td3.modele;

public class Livre {

    protected String isbn, titre;

    public Livre(String isbn, String titre) {
        this.isbn = isbn;
        this.titre = titre;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }
}
