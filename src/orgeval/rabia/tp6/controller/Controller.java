package orgeval.rabia.tp6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import orgeval.rabia.tp6.model.MotsCroises;
import orgeval.rabia.tp6.model.dao.MotsCroisesDao;

public class Controller {

    private MotsCroises motsCroises;
    @FXML
    private CheckBox box;
    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize() {
        //HashMap<Integer, String> map = MotsCroisesDao.dispoMotsCroises();
        gridpane();
    }

    private void gridpane() {
        TextField model = (TextField) gridPane.getChildren().get(0);
        gridPane.getChildren().clear();

        motsCroises = MotsCroisesDao.getMotsCroises(MotsCroisesDao.getMotsCroisesRandom());
        for (int l = 1; l <= motsCroises.getHauteur(); l++) {
            for (int c = 1; c <= motsCroises.getLargeur(); c++) {
                TextField field = new TextField();
                field.setOnMouseClicked(this::click);
                field.getStyleClass().add("field");
                field.setPrefSize(model.getPrefWidth(), model.getPrefHeight());
                field.setTooltip(new Tooltip(getTooltip(l, c)));
                if (motsCroises.estCaseNoire(l, c)) {
                    field.getStyleClass().add("field-noir");
                    field.setEditable(false);
                }

                gridPane.add(field, c - 1, l - 1);
            }
        }
    }

    private String getTooltip(int l, int c) {
        StringBuilder ret = new StringBuilder();
        ret.append("Ligne ").append(l).append(", colonne :").append(c);

        if (motsCroises.getDefinition(l, c, true) != null && motsCroises.getDefinition(l, c, false) != null)
            ret.append(" (horizontal / vertical) : ").append(motsCroises.getDefinition(l, c, true)).append(" / ").append(motsCroises.getDefinition(l, c, false));
        else if (motsCroises.getDefinition(l, c, true) != null)
            ret.append(" (horizontal) : ").append(motsCroises.getDefinition(l, c, true));
        else if (motsCroises.getDefinition(l, c, false) != null)
            ret.append(" (vertical) : ").append(motsCroises.getDefinition(l, c, false));
        else return null;
        return ret.toString();
    }

    @FXML
    public void click(MouseEvent e) {
        if (e.getButton() == MouseButton.MIDDLE) {
            TextField field = (TextField) e.getSource();
            int l = (int) field.getProperties().get("gridpane-row") + 1;
            int c = (int) field.getProperties().get("gridpane-column") + 1;
            motsCroises.reveler(l, c);
            field.textProperty().bind(motsCroises.propositionProperty(l, c));
        }
    }

}
