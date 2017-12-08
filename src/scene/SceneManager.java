package scene;

import game.GameSound;
import item.ItemHolder;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import map.MapHolder;
import ui.UIMenu;

public class SceneManager {
	
	private static Stage primaryStage;
	private static Canvas mainMenu;
	private static Scene mainScene;
	private static Pane mainFrame;
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 600;

	public static void initialize(Stage stage) {
		mainMenu = new UIMenu();
		mainFrame = new Pane();
		mainFrame.setPrefWidth(SCENE_WIDTH);
		mainFrame.setPrefHeight(SCENE_HEIGHT);
		mainScene = new Scene(mainFrame);
		primaryStage = stage;
		primaryStage.setScene(mainScene);
	}
	
	public static void gotoMenu() {
		mainFrame.getChildren().clear();
		mainFrame.getChildren().add(mainMenu);
		mainMenu.requestFocus();
	}

	public static void gotoScene(Group group) {
		
		Rectangle cutR = new Rectangle(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
		cutR.setOpacity(0);
		mainFrame.getChildren().add(cutR);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, 
				new KeyValue (cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), 
				new KeyValue (cutR.opacityProperty(), 1, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300),
					ae -> {
						mainFrame.getChildren().remove(0);
						mainFrame.getChildren().add(0,group);
						group.requestFocus();
					}));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), 
				new KeyValue (cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
		timeline.setCycleCount(1);
		timeline.play();
	}
	
	public static void gotoScene(Canvas gameFrame) {
		Rectangle cutR = new Rectangle(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
		cutR.setOpacity(0);
		mainFrame.getChildren().add(cutR);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, 
				new KeyValue (cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), 
				new KeyValue (cutR.opacityProperty(), 1, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300),
					ae -> {
						mainFrame.getChildren().remove(0);
						mainFrame.getChildren().add(0,gameFrame);
						gameFrame.requestFocus();
					}));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), 
				new KeyValue (cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
		timeline.setCycleCount(1);
		timeline.play();
	}
}
/*
Pane gameFrame = new Pane(group);
gameFrame.setPrefWidth(SCENE_WIDTH);
gameFrame.setPrefHeight(SCENE_HEIGHT);
Rectangle cutR = new Rectangle(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
cutR.setOpacity(0);
if(currentPane!=null)
	currentPane.getChildren().add(cutR);
gameFrame.getChildren().add(cutR);
Timeline timeline = new Timeline();
timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, 
		new KeyValue (cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), 
		new KeyValue (cutR.opacityProperty(), 1, Interpolator.EASE_BOTH)));
timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300),
			ae -> {
				Scene gameScene = new Scene(gameFrame);
				primaryStage.setScene(gameScene);
				group.requestFocus();
			}));
timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600), 
		new KeyValue (cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
timeline.setCycleCount(1);
/*timeline.setOnFinished(new EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent event) {
		Scene gameScene = new Scene(gameFrame);
		primaryStage.setScene(gameScene);
		group.requestFocus();
	}
	
});
timeline.play();
public static void gotoScene(Group group) {
		Pane gameFrame = new Pane(group);
		gameFrame.setPrefWidth(SCENE_WIDTH);
		gameFrame.setPrefHeight(SCENE_HEIGHT);
		Scene gameScene = new Scene(gameFrame);
		primaryStage.setScene(gameScene);
		group.requestFocus();
	}*/