package es.fra.sm;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.google.inject.Guice;
import com.google.inject.Injector;

import es.fra.sm.samples.GuiceFXMLLoader;
import es.fra.sm.samples.SampleController;
import es.fra.sm.samples.SampleModule;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	private final Injector	injector	= Guice.createInjector(new SampleModule());

	@Override
	public void start(Stage stage) throws Exception {
		// Create a new Guice-based FXML Loader
		final GuiceFXMLLoader loader = new GuiceFXMLLoader(this.injector);
		// Ask to load the Sample.fxml file, injecting an instance of a
		// SampleController
		final Parent root = (Parent) loader.load("/fxml/Sample.fxml",
				SampleController.class);

		// Finish constructing the scene
		final Scene scene = new Scene(root, 320, 240);
		// Load up the CSS stylesheet
		scene.getStylesheets().add(
				this.getClass().getResource("/styles/fxmlapp.css").toString());
		// Show the window
		stage.setScene(scene);
		stage.setTitle("Guiced");
		stage.show();
	}

}
