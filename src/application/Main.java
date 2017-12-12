package application;

import game.GameMain;
import image.ImageLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scene.SceneManager;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			ImageLoader.initailize();
			SceneManager.initialize(primaryStage);
			SceneManager.gotoMenu();
			primaryStage.resizableProperty().setValue(Boolean.FALSE);
			primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.setTitle("Rabbit's Foot");
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