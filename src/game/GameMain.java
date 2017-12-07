package game;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import scene.SceneManager;
import ui.UIGame;
import javafx.scene.control.Alert.AlertType;

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
		logic.startGame();
		state.startState();
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
