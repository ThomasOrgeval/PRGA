package orgeval.rabia.tp6.model.dao;

import java.sql.*;

public class ConnectDB {

    private static Connection connection;

    private static void connectDB() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_prga_2021", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fonction pour select des requêtes SQL
    public static ResultSet execReqSelection(String req) {
        connectDB();
        ResultSet resultatReq = null;
        try {
            Statement requete = connection.createStatement();
            resultatReq = requete.executeQuery(req);
        } catch (Exception e) {
            System.out.println("Erreur requête : " + req);
        }
        return resultatReq;
    }

    // Fonction pour insérer/update des requêtes SQL
    public static void execReqMaj(String req) {
        connectDB();
        try {
            Statement s = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.executeUpdate(req);
            s.close();
            connectClose();
        } catch (Exception er) {
            er.printStackTrace();
            System.out.println("Échec requête : " + req);
        }
    }

    public static void connectClose() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Erreur fermeture db");
        }
    }

}
