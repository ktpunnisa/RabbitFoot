package scene;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.UIMenu;

public class SceneManager {
	
	private static Stage primaryStage;
	private static Group mainMenu;
	private static Scene mainScene;
	private static Pane mainFrame;
	public static final int SCENE_WIDTH = 500;
	public static final int SCENE_HEIGHT = 500;
	
	public static void initialize(Stage stage) {
		mainMenu = new UIMenu();
		mainFrame = new Pane(mainMenu);
		mainFrame.setPrefWidth(SCENE_WIDTH);
		mainFrame.setPrefHeight(SCENE_HEIGHT);
		mainScene = new Scene(mainFrame);
		primaryStage = stage;
		//primaryStage.show();
	}
	
	public static void gotoMenu() {
		//TODO Fill Code
		primaryStage.setScene(mainScene);
		mainMenu.requestFocus();
	}

	public static void gotoScene(Group group) {
		//TODO Fill Code
		Pane gameFrame = new Pane(group);
		gameFrame.setPrefWidth(SCENE_WIDTH);
		gameFrame.setPrefHeight(SCENE_HEIGHT);
		Scene gameScene = new Scene(gameFrame);
		primaryStage.setScene(gameScene);
		group.requestFocus();
	}
}
