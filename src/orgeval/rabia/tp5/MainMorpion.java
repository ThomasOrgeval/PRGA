package orgeval.rabia.tp5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainMorpion extends Application {

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
	        primaryStage.setTitle("Morpions V1");
			FXMLLoader loader = new FXMLLoader() ;
            loader.setLocation(MainMorpion.class.getResource("morpion.fxml"));
            Parent root = loader.load();
			Scene scene = new Scene(root,280,150);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
