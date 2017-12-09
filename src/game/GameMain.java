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
import ui.UIGameOver;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import map.MapHolder;

public class GameMain {
	
	private static GameState state;
	private static GameLogic logic;
	public static UIGame gameUI;
	public static UIGameOver gameOverUI;
	
	public static void newGame(int diff) {
		state = new GameState(diff);
		gameUI = new UIGame(state);
		gameOverUI = new UIGameOver();
		logic = new GameLogic(gameUI, state);
		SceneManager.gotoScene(gameUI);
		//logic.startGame();
		//state.startState();
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
				ae -> {
					logic.startGame();
					GameSound.playSoundBG();
					
				}));
		timeline.setCycleCount(1);
		Platform.runLater(() -> timeline.play());
	}
	
	public static void stopGame() {
		logic.stopGame();
		gameOverUI.startGameOver();
		GameSound.stopSoundBG();
		Platform.runLater(() -> SceneManager.gotoScene(gameOverUI));
	}
	
	public static void gameOver() {
		// TODO fill code
		Alert alert = new Alert(AlertType.NONE, "Game Over!", ButtonType.OK);
		alert.showAndWait();
	}

}
