package orgeval.rabia.tp3.modele;

import orgeval.rabia.tp1.MotsCroises;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChargerGrille {

    private static Connection connection;

    public ChargerGrille() {
        try {
            connection = connecterBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connecterBD() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_prga_2021", "root", "");
    }

    public Map<Integer, String> grillesDisponibles() {
        Map<Integer, String> map = new HashMap<>();
        ResultSet reqSelection = execReqSelection("select num_grille, nom_grille from tp5_grille");
        try {
            while (reqSelection.next()) {
                map.put(reqSelection.getInt(1), reqSelection.getString(2));
            }
        } catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select num_grille, nom_grille from tp5_grille");
            e.printStackTrace();
        }
        fermerConnexionBd();
        return map;
    }

    public static MotsCroises extraireGrille(int numGrille) {
        MotsCroises motsCroises = null;
        ResultSet reqSelection = execReqSelection("select hauteur, largeur from tp5_grille where num_grille = '" + numGrille + "'");
        try {
            if (reqSelection.next()) motsCroises = new MotsCroises(reqSelection.getInt(1), reqSelection.getInt(2));
        } catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select largeur, hauteur from tp5_grille where num_grille = '" + numGrille + "'");
            e.printStackTrace();
        }

        reqSelection = execReqSelection("select t5m.ligne, t5m.colonne, t5m.horizontal, t5m.solution from tp5_grille\n" +
                "inner join tp5_mot t5m on tp5_grille.num_grille = t5m.num_grille where tp5_grille.num_grille = '" + numGrille + "'");
        assert motsCroises != null;
        motsCroises.setGrilleNoire();
        try {
            while (reqSelection.next()) {
                char[] chars = reqSelection.getString(4).toCharArray();
                int lig = reqSelection.getInt(1), col = reqSelection.getInt(2);

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
        fermerConnexionBd();
        return motsCroises;
    }

    public static void fermerConnexionBd() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Erreur sur fermeture connexion");
        }
    }

    // Fonction pour select des requêtes SQL
    public static ResultSet execReqSelection(String laRequete) {
        new ChargerGrille();
        ResultSet resultatReq = null;
        try {
            Statement requete = connection.createStatement();
            resultatReq = requete.executeQuery(laRequete);
        } catch (Exception e) {
            System.out.println("Erreur requête : " + laRequete);
        }
        return resultatReq;
    }

    // Fonction pour insérer/update des requêtes SQL
    public static int execReqMaj(String laRequete) {
        new ChargerGrille();
        int nbMaj = 0;
        try {
            Statement s = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            nbMaj = s.executeUpdate(laRequete);
            s.close();
        } catch (Exception er) {
            er.printStackTrace();
            System.out.println("Échec requête : " + laRequete);
        }
        return nbMaj;
    }

}
