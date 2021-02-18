package orgeval.rabia.tp6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Mots Croisés");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(orgeval.rabia.tp6.Main.class.getResource("grille.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 1000);
            scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm());
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
