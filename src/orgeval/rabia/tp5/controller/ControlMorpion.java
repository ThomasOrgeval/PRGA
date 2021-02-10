package orgeval.rabia.tp5.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import orgeval.rabia.tp5.model.ModeleMorpions;

import java.awt.*;

public class ControlMorpion {

    private ModeleMorpions morpions;
    @FXML
    private final GridPane gridPane = new GridPane();
    @FXML
    private Label nbCoups;
    @FXML
    private Label etatJeu;
    @FXML
    private Label joueur;

    @FXML
    private void initialize() {
        morpions = new ModeleMorpions();
        for (Node n : gridPane.getChildren()) {
            n.setOnMouseClicked(this::click);
            /*if (n instanceof Label) {
                Label l = (Label) n;
                int ligne = (int) l.getProperties().get("gridpane-row") + 1;
                int colonne = (int) l.getProperties().get("gridpane-column") + 1;
                modele.casePlateauProperty(ligne, colonne).addListener((obs, oldV, newV) -> {
                    l.setText(modele.symboleJoueur(newV.intValue()));
                })
            }*/
        }

        morpions.nbCoupsProperty().addListener(
                ((observable, oldValue, newValue) -> majCoups(newValue.intValue()))
        );
        calculEtatJeu();
        nbCoups.setText(String.valueOf(morpions.getNombreCoups()));
        joueur.setText(morpions.getSymboleJValue());
    }

    private void click(MouseEvent e) {
        Node n = (Node) e.getSource();
        int ligne = ((int) n.getProperties().get("gridpane-row")) + 1;
        int colonne = ((int) n.getProperties().get("gridpane-column")) + 1;
        morpions.jouerCoup(ligne, colonne);
        calculEtatJeu();
        System.out.println("Coup joué : " + ligne + "/" + colonne);
        System.out.println("résultat: " + morpions.getEtatJeu());
    }

    private void calculEtatJeu() {
        switch (morpions.getEtatJeu()) {
            case J1_JOUE:
                etatJeu.setText("Joueur 1");
                break;
            case J2_JOUE:
                etatJeu.setText("Joueur 2");
                break;
            case MATCH_NUL:
                etatJeu.setText("Match nul");
                break;
            case J1_VAINQUEUR:
                etatJeu.setText("Victoire joueur 1");
                break;
            case J2_VAINQUEUR:
                etatJeu.setText("Victoire joueur 2");
                break;
        }
    }

    private void majCoups(int nb) {
        if (nb == 0) nbCoups.setText("aucun coup joué");
        else {
            String ch;
            if (nb == 1) ch = " coup joué";
            else ch = " coups joués";
            nbCoups.setText(nb + ch);
        }
    }

}
