package orgeval.td2;

public class TexteModifiable extends TexteConsultable {

    public TexteModifiable(String texte) {
        super(texte);
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}