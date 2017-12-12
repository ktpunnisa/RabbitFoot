package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import scene.SceneManager;
import ui.UIBar;
import ui.UIGame;
import ui.UIGameOver;
import javafx.util.Duration;

public class GameMain {

	private static GameState state;
	private static GameLogic logic;
	private static UIGame gameUI;
	private static UIGameOver gameOverUI;

	public static void newGame(int diff) {
		state = new GameState(diff);
		gameUI = new UIGame();
		logic = new GameLogic(state);
		SceneManager.gotoScene(gameUI);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), ae -> {
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
		UIBar.removeTimeBar();
		GameSound.stopSoundBG();
		Platform.runLater(() -> SceneManager.gotoScene(gameOverUI));
	}

	public static GameState getState() {
		return state;
	}

	public static void setState(GameState state) {
		GameMain.state = state;
	}

	public static GameLogic getLogic() {
		return logic;
	}

	public static void setLogic(GameLogic logic) {
		GameMain.logic = logic;
	}

	public static UIGame getGameUI() {
		return gameUI;
	}

	public static void setGameUI(UIGame gameUI) {
		GameMain.gameUI = gameUI;
	}

	public static UIGameOver getGameOverUI() {
		return gameOverUI;
	}

	public static void setGameOverUI(UIGameOver gameOverUI) {
		GameMain.gameOverUI = gameOverUI;
	}

}
