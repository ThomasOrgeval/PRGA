package orgeval.rabia.tp6.controller;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import orgeval.rabia.tp6.model.MotsCroises;
import orgeval.rabia.tp6.model.dao.MotsCroisesDao;

public class Controller {

    private MotsCroises motsCroises;
    @FXML
    private CheckBox box;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button button;
    @FXML
    private ComboBox<String> combo;

    @FXML
    private void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList(MotsCroisesDao.dispoMotsCroises());
        combo.setItems(list);
        gridpane(MotsCroisesDao.getMotsCroisesRandom());
    }

    private void gridpane(int n) {
        TextField model = (TextField) gridPane.getChildren().get(0);
        gridPane.getChildren().clear();

        motsCroises = MotsCroisesDao.getMotsCroises(n);
        for (int l = 1; l <= motsCroises.getHauteur(); l++) {
            for (int c = 1; c <= motsCroises.getLargeur(); c++) {
                TextField field = new TextField();
                field.setOnMouseClicked(this::click);
                field.setOnKeyPressed(this::write);
                field.setPrefSize(model.getPrefWidth(), model.getPrefHeight());
                field.setTooltip(new Tooltip(getTooltip(l, c)));
                field.setEditable(false);
                if (motsCroises.estCaseNoire(l, c)) {
                    field.getStyleClass().add("field-black");
                    field.setFocusTraversable(false);
                } else field.getStyleClass().add("field");

                gridPane.add(field, c - 1, l - 1);
            }
        }

        button.setOnAction(this::newMots);
    }

    private void newMots(ActionEvent e) {
        gridpane(MotsCroisesDao.getMotsCroisesRandom());
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
            field.getStyleClass().add("field-green");
            movePlacement(field);
        }
    }

    @FXML
    public void write(KeyEvent e) {
        TextField field = (TextField) e.getSource();
        int l = (int) field.getProperties().get("gridpane-row") + 1;
        int c = (int) field.getProperties().get("gridpane-column") + 1;

        if (e.getCode().isLetterKey()) {
            motsCroises.setProposition(l, c, e.getText().toUpperCase().charAt(0));
            field.textProperty().bind(motsCroises.propositionProperty(l, c));
            field.getStyleClass().removeAll("field-green", "field-red");
            movePlacement(field);
        } else if (e.getCode().isArrowKey()) {
            switch (e.getCode()) {
                case UP:
                    ((BehaviorSkinBase) field.getSkin()).getBehavior().traverseUp();
                    break;
                case DOWN:
                    ((BehaviorSkinBase) field.getSkin()).getBehavior().traverseDown();
                    break;
                case LEFT:
                    ((BehaviorSkinBase) field.getSkin()).getBehavior().traverseLeft();
                    break;
                case RIGHT:
                    ((BehaviorSkinBase) field.getSkin()).getBehavior().traverseRight();
                    break;
            }
        } else if (e.getCode().equals(KeyCode.ENTER)) {
            if (motsCroises.getProposition(l, c) == motsCroises.getSolution(l, c))
                field.getStyleClass().add("field-green");
            else field.getStyleClass().add("field-red");
            movePlacement(field);
        } else if (e.getCode().equals(KeyCode.BACK_SPACE)) {
            motsCroises.setProposition(l, c, ' ');
            field.textProperty().bind(motsCroises.propositionProperty(l, c));
            field.getStyleClass().removeAll("field-green", "field-red");
            if (field.getSkin() instanceof BehaviorSkinBase) {
                if (box.isSelected()) ((BehaviorSkinBase) field.getSkin()).getBehavior().traverseUp();
                else ((BehaviorSkinBase) field.getSkin()).getBehavior().traverseLeft();
            }
        } else field.clear();
    }

    private void movePlacement(TextField field) {
        if (field.getSkin() instanceof BehaviorSkinBase) {
            if (box.isSelected()) ((BehaviorSkinBase) field.getSkin()).getBehavior().traverseDown();
            else ((BehaviorSkinBase) field.getSkin()).getBehavior().traverseRight();
        }
    }

}
