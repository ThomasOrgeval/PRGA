package orgeval.rabia.tp6.controller;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import orgeval.rabia.tp6.model.MotsCroises;
import orgeval.rabia.tp6.model.dao.MotsCroisesDao;

public class Controller {

    private MotsCroises motsCroises;
    @FXML
    private TextField field;
    @FXML
    private Button button, finish;
    @FXML
    private CheckBox box;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label name;

    @FXML
    private void initialize() {
        //HashMap<Integer, String> map = MotsCroisesDao.dispoMotsCroises();
        name.setText("Mots croisé sans nom");
        gridPane.setPrefSize(800, 800);
        gridPane.setMinSize(600, 600);
        gridPane.setMaxSize(800, 800);

        motsCroises = MotsCroisesDao.getMotsCroises(MotsCroisesDao.getMotsCroisesRandom());
        for (int l = 1; l <= motsCroises.getHauteur(); l++) {
            /*StackPane pane = new StackPane();
            pane.setStyle("-fx-border-color: red;");
            gridPane.add(pane, l-1, 0);*/
            for (int c = 1; c <= motsCroises.getLargeur(); c++) {
                StackPane stackPane = new StackPane();
                stackPane.setId(l + ":" + c);
                stackPane.setOnMouseClicked(this::click);
                stackPane.getStyleClass().add("sp");
                if (motsCroises.estCaseNoire(l, c)) stackPane.getStyleClass().add("sp-noir");
                gridPane.add(stackPane, c - 1, l - 1);

                Label label = new Label();
                label.setText(Character.toString(motsCroises.getSolution(l, c))/*motsCroises.getIndex(l, c)*/);
                stackPane.getChildren().add(label);
            }
        }
        for (int l = 1; l <= motsCroises.getHauteur(); l++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(2, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(2, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }

        field.setEditable(false);
        field.getStyleClass().add("field");
        button.setDisable(true);
        box.setDisable(true);
        finish.setOnAction(event -> finish());
    }

    private void finish() {

    }

    private void click(MouseEvent e) {
        Node n = (Node) e.getSource();
        StackPane sp = (StackPane) n;
        Label l = (Label) sp.getChildren().get(0);
        System.out.println(n.getId());

        field.setEditable(true);
        button.setDisable(false);
        box.setDisable(false);
        button.setOnMouseClicked(event -> setText(l, field.getText()));
    }

    private void setText(Label l, String s) {
        if (!s.isEmpty()) {
            l.setText(s);
            field.clear();
            field.setEditable(false);
            button.setDisable(true);
            box.setDisable(true);
        } else button.setOnMouseClicked(event -> setText(l, field.getText()));
    }

}
