package scene;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ui.UIMenu;

public class SceneManager {
	
	private static Stage primaryStage;
	private static Canvas mainMenu;
	private static Scene mainScene;
	private static Pane mainFrame;
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 600;
	public SceneManager instance = this;
	
	public static void initialize(Stage stage) {
		mainMenu = new UIMenu();
		mainFrame = new Pane(mainMenu);
		mainFrame.setPrefWidth(SCENE_WIDTH);
		mainFrame.setPrefHeight(SCENE_HEIGHT);
		mainScene = new Scene(mainFrame);
		primaryStage = stage;
	}
	
	public static void gotoMenu() {
		primaryStage.setScene(mainScene);
		mainMenu.requestFocus();
	}

	public static void gotoScene(Group group) {
		Pane gameFrame = new Pane(group);
		//gameFrame.getChildren().add(cutScene());
		gameFrame.setPrefWidth(SCENE_WIDTH);
		gameFrame.setPrefHeight(SCENE_HEIGHT);
		Scene gameScene = new Scene(gameFrame);
		primaryStage.setScene(gameScene);
		group.requestFocus();
	}
	
	public static void gotoScene(Canvas gameFrame) {
		Pane t = new Pane();
		t.getChildren().add(gameFrame);
		//t.getChildren().add(cutScene());
		Scene gameScene = new Scene(new Pane(gameFrame));
		primaryStage.setScene(gameScene);
		gameFrame.requestFocus();
	}
	
	public static Canvas cutScene()
	{
		Canvas c = new Canvas();
		c.setWidth(SCENE_WIDTH);
		c.setHeight(SCENE_HEIGHT);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				boolean isCutting = true;
				long lastLoopStartTime = System.nanoTime();
				int w = -10;
				while (isCutting) {
					long now = System.nanoTime();
					if (now - lastLoopStartTime >= 1000000000 / 60) {
						lastLoopStartTime += 1000000000 / 60;
						int t=w++;
						Platform.runLater(() -> updateCutScene(c,t));
					}

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(w>=10) isCutting=false;
				}
			}
		}).start();
		return c;
	}
	private static void updateCutScene(Canvas c, int w) {
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		if(w<=0)
			gc.setGlobalAlpha((w+(double)10)/10.0);
		else
			gc.setGlobalAlpha(1-(double)w/10.0);
		System.out.println(gc.getGlobalAlpha());
		
	}
}
