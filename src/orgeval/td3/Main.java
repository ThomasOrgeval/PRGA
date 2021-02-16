package orgeval.td3;

import orgeval.td3.modele.Auteur;
import orgeval.td3.modele.Livre;
import orgeval.td3.modele.dao.AuteurDAO;
import orgeval.td3.modele.dao.LivreDAO;
import orgeval.td3.modele.dao.LivreNormDAO;

public class Main {

    public static void main(String[] args) {
        LivreNormDAO.insLivres(LivreDAO.reqDistinctLivre());
        AuteurDAO.insAuteur(LivreDAO.reqDistinctAuteurLivre());
        Auteur auteur = new Auteur("Combaluzier", "Jeanne");
        auteur.getLivres();
        auteur.addLivre(new Livre("3-85774-186-3", "Mon nouveau livre"));
        System.out.println(auteur.getLivres());
    }

}
