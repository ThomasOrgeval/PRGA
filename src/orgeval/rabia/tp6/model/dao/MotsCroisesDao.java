package orgeval.rabia.tp6.model.dao;

import orgeval.rabia.tp6.model.MotsCroises;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MotsCroisesDao {

    public static ArrayList<String> dispoMotsCroises() {
        ArrayList<String> list = new ArrayList<>();
        ResultSet reqSelection = ConnectDB.execReqSelection("select nom_grille from tp5_grille");
        try {
            while (reqSelection.next()) {
                list.add(reqSelection.getString(1));
            }
        } catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select num_grille, nom_grille from tp5_grille");
            e.printStackTrace();
        }
        ConnectDB.connectClose();
        return list;
    }

    public static int getMotsCroisesRandom() {
        ResultSet set = ConnectDB.execReqSelection("select num_grille from tp5_grille order by rand() limit 1");
        int ret = 0;
        try {
            if (set.next()) ret = set.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.connectClose();
        return ret;
    }

    public static MotsCroises getMotsCroises(int numGrille) {
        MotsCroises motsCroises = null;
        ResultSet reqSelection = ConnectDB.execReqSelection("select hauteur, largeur from tp5_grille where num_grille = '" + numGrille + "'");
        try {
            if (reqSelection.next()) motsCroises = new MotsCroises(reqSelection.getInt(1), reqSelection.getInt(2));
        } catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select largeur, hauteur from tp5_grille where num_grille = '" + numGrille + "'");
            e.printStackTrace();
        }

        reqSelection = ConnectDB.execReqSelection("select t5m.ligne, t5m.colonne, t5m.horizontal, t5m.solution, t5m.definition from tp5_grille\n" +
                "inner join tp5_mot t5m on tp5_grille.num_grille = t5m.num_grille where tp5_grille.num_grille = '" + numGrille + "'");
        assert motsCroises != null;
        try {
            while (reqSelection.next()) {
                char[] chars = reqSelection.getString(4).toCharArray();
                int lig = reqSelection.getInt(1), col = reqSelection.getInt(2);

                motsCroises.setDefinition(lig, col, reqSelection.getInt(3) == 1, reqSelection.getString(5));
                for (int i = 0; i < chars.length; i++) {
                    if (reqSelection.getInt(3) == 1) {
                        motsCroises.setSolution(lig, col + i, chars[i]); // Horizontal
                        motsCroises.setCaseNoire(lig, col + i, false);
                    } else {
                        motsCroises.setSolution(lig + i, col, chars[i]); // Vertical
                        motsCroises.setCaseNoire(lig + i, col, false);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur extraireGrille()");
            e.printStackTrace();
        }
        ConnectDB.connectClose();
        return motsCroises;
    }

}
