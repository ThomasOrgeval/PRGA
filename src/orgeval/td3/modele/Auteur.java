package orgeval.td3.modele;

import orgeval.td3.modele.dao.AuteurDAO;

import java.util.ArrayList;

public class Auteur {

    protected String nom, prenom;
    private ArrayList<Livre> livresEcrits;

    public Auteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public ArrayList<Livre> getLivres() {
        if (livresEcrits == null) livresEcrits = AuteurDAO.getLivresEcritsPar(this);
        return livresEcrits;
    }

    public boolean addLivre(Livre livre) {
        if (livresEcrits.contains(livre)) return false;
        livresEcrits.add(livre);
        AuteurDAO.insLivre(livre, this);
        return true;
    }

}
