package scene;

import game.GameSound;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.UIMenu;

public class SceneManager {
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 600;

	private static Stage primaryStage;
	private static UIMenu mainMenu;
	private static Scene mainScene;
	public static Pane mainFrame;

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
		GameSound.playSoundMenu();
		Platform.runLater(() -> mainFrame.getChildren().clear());
		Platform.runLater(() -> mainFrame.getChildren().add(mainMenu));
		mainMenu.startMenu();
		Platform.runLater(() -> mainMenu.requestFocus());
	}

	public static void gotoScene(Group group) {
		Rectangle cutR = new Rectangle(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
		Platform.runLater(() -> cutR.setOpacity(0));
		Platform.runLater(() -> mainFrame.getChildren().add(cutR));
		Timeline timeline = new Timeline();
		timeline.getKeyFrames()
				.add(new KeyFrame(Duration.ZERO, new KeyValue(cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(300), new KeyValue(cutR.opacityProperty(), 1, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), ae -> {
			Platform.runLater(() -> mainFrame.getChildren().remove(0));
			Platform.runLater(() -> mainFrame.getChildren().add(0, group));
			Platform.runLater(() -> group.requestFocus());
		}));
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(1000), new KeyValue(cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), ae -> {
			Platform.runLater(() -> mainFrame.getChildren().remove(cutR));
		}));
		timeline.setCycleCount(1);
		Platform.runLater(() -> timeline.play());
	}

	public static void gotoScene(Canvas gameFrame) {
		Rectangle cutR = new Rectangle(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
		Platform.runLater(() -> cutR.setOpacity(0));
		Platform.runLater(() -> mainFrame.getChildren().add(cutR));
		Timeline timeline = new Timeline();
		timeline.getKeyFrames()
				.add(new KeyFrame(Duration.ZERO, new KeyValue(cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(300), new KeyValue(cutR.opacityProperty(), 1, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), ae -> {
			Platform.runLater(() -> mainFrame.getChildren().remove(0));
			Platform.runLater(() -> mainFrame.getChildren().add(0, gameFrame));
			Platform.runLater(() -> gameFrame.requestFocus());
		}));
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(1000), new KeyValue(cutR.opacityProperty(), 0, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), ae -> {
			Platform.runLater(() -> mainFrame.getChildren().remove(cutR));
		}));
		timeline.setCycleCount(1);
		Platform.runLater(() -> timeline.play());
	}
}