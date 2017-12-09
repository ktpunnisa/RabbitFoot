package application;

import game.GameMain;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import scene.SceneManager;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoMenu();
			primaryStage.setTitle("Catch me if you can!");
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> {
			Platform.exit();
			System.exit(0);
		});
	}
	
	@Override
	public void stop() throws Exception {
		GameMain.stopGame();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}