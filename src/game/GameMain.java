package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import scene.SceneManager;
import ui.UIGame;
import ui.UIGameOver;
import javafx.util.Duration;

public class GameMain {
	
	private static GameState state;
	private static GameLogic logic;
	public static UIGame gameUI;
	public static UIGameOver gameOverUI;
	
	public static void newGame(int diff) {
		state = new GameState(diff);
		gameUI = new UIGame();
		logic = new GameLogic(state);
		SceneManager.gotoScene(gameUI);
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
		gameOverUI = new UIGameOver();
		gameOverUI.startGameOver();
		GameSound.stopSoundBG();
		Platform.runLater(() -> SceneManager.gotoScene(gameOverUI));
	}
}
