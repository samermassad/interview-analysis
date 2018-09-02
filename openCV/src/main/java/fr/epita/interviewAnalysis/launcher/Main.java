package fr.epita.interviewAnalysis.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nu.pattern.OpenCV;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// load the FXML resource
			FXMLLoader loader = new FXMLLoader(getClass().getResource("interviewAnalysis.fxml"));
			
			ApplicationController controller = new ApplicationController();
			loader.setController(controller);
			
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Interview Analysis");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			
			primaryStage.show();
			
			controller.init();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		// load the native OpenCV library
		OpenCV.loadShared();
		
		// load the ffmpeg library
//		ConfigurationService conf = ConfigurationService.getInstance();
//		System.load(conf.getConfigurationValue("ffmpeg.library.path"));
		
		// launch the app
		launch(args);
	}

}
