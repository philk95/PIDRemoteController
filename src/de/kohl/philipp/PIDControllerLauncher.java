package de.kohl.philipp;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PIDControllerLauncher extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		URL resource = getClass().getResource("PIDController.fxml");
		Parent root = FXMLLoader.load(resource);

		Scene scene = new Scene(root, 300, 300);

		stage.setTitle("PID Remote Controller");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
