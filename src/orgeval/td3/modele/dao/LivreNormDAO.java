package orgeval.td3.modele.dao;

import orgeval.td3.modele.Livre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivreNormDAO {

    public static void insLivres(ArrayList<Livre> livres) {
        for (Livre livre : livres) {
            if (!exist(livre)) ConnectDB.execReqMaj("insert into LIVRE_NORMALISE values('" + livre.getIsbn() + "', '" + livre.getTitre() + "')");
        }
    }

    private static boolean exist(Livre livre) {
        try {
            ResultSet set = ConnectDB.execReqSelection("select * from LIVRE_NORMALISE where ISBN='" + livre.getIsbn() + "' and titre='" + livre.getTitre() + "'");
            if (set.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void insLivre(Livre livre) {
        ConnectDB.execReqMaj("insert into LIVRE_NORMALISE values('" + livre.getIsbn() + "', '" + livre.getTitre() + "')");
    }
}
