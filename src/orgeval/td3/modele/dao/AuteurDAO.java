package orgeval.td3.modele.dao;

import orgeval.td3.modele.Auteur;
import orgeval.td3.modele.Livre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuteurDAO {

    public static void insAuteur(ArrayList<Auteur> auteurs) {
        for (Auteur auteur : auteurs) {
            if (!exist(auteur)) ConnectDB.execReqMaj("insert into AUTEUR_LIVRE values('" + auteur.getNom() + "', '" + auteur.getPrenom() + "')");
        }
    }

    private static boolean exist(Auteur auteur) {
        try {
            ResultSet set = ConnectDB.execReqSelection("select * from AUTEUR_LIVRE where nom_auteur='" + auteur.getNom() + "', prenom_auteur='" + auteur.getPrenom() + "'");
            if (set.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Livre> getLivresEcritsPar(Auteur auteur) {
        Livre livre;
        ArrayList<Livre> livres = new ArrayList<>();
        ResultSet set = ConnectDB.execReqSelection("select isbn, titre from LIVRE where nom_auteur = '" + auteur.getNom() + "' and prenom_auteur = '" + auteur.getPrenom() + "'");
        try {
            while (set.next()) {
                livre = new Livre(set.getString(1), set.getString(2));
                livres.add(livre);
            }
            ConnectDB.connectClose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    public static void insLivre(Livre livre, Auteur auteur) {
        ConnectDB.execReqMaj("insert into AUTEUR_LIVRE values('" + livre.getIsbn() + "', '" + auteur.getNom() + "', '" + auteur.getPrenom() + "')");
        LivreNormDAO.insLivre(livre);
    }
}
