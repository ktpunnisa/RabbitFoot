package scene;

import javafx.stage.Stage;

public class SceneManager {
	
	private static Stage primaryStage;
	
	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}
}
