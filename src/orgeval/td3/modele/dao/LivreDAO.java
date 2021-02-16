package orgeval.td3.modele.dao;

import orgeval.td3.modele.Auteur;
import orgeval.td3.modele.Livre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivreDAO {

    public static ArrayList<Livre> reqDistinctLivre() {
        Livre livre;
        ArrayList<Livre> livres = new ArrayList<>();
        ResultSet set = ConnectDB.execReqSelection("select distinct isbn, titre from LIVRE");
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

    public static ArrayList<Auteur> reqDistinctAuteurLivre() {
        Auteur aut;
        ArrayList<Auteur> auteurs = new ArrayList<>();
        ResultSet set = ConnectDB.execReqSelection("select distinct nom_auteur, prenom_auteur from LIVRE");
        try {
            while (set.next()) {
                aut = new Auteur(set.getString(1), set.getString(2));
                auteurs.add(aut);
            }
            ConnectDB.connectClose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auteurs;
    }

}
