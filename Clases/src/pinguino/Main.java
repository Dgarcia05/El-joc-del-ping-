package pinguino;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaPrincipal.fxml"));
	    Parent root = loader.load();

	    Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("El Juego del Pingüino");
	    primaryStage.show();
	}

    public static void main(String[] args) {
        launch(args);
    }
}
