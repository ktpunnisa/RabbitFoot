package game;

import java.util.Optional;

import item.ItemHolder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import scene.SceneManager;
import ui.UIGame;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import map.MapHolder;

public class GameMain {
	
	private static GameState state;
	private static GameLogic logic;
	public static GameCamera camera;
	public static UIGame gameUI;
	
	public static void newGame(int diff) {
		state = new GameState(diff);
		gameUI = new UIGame(state);
		camera = new GameCamera(gameUI);
		logic = new GameLogic(gameUI, state, camera);
		SceneManager.gotoScene(gameUI);
		//logic.startGame();
		//state.startState();
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
				ae -> {
					logic.startGame();
					state.startState();
				}));
		timeline.setCycleCount(1);
		timeline.play();
	}
	
	public static void stopGame() {
		logic.stopGame();
		//Platform.runLater(GameMain::gameOver);
		state.stopState();
	}
	
	public static void gameOver() {
		// TODO fill code
		Alert alert = new Alert(AlertType.NONE, "Game Over!", ButtonType.OK);
		alert.showAndWait();
	}

}
