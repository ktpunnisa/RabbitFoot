package game;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class GameMain {
	
	public static void newGame() {
		// TODO fill code
		
	}
	
	public static void stopGameLogicAndAnimation() {
		// TODO fill code
		
	}

	public static void stopGame() {
		// TODO fill code
		
	}
	
	public static void gameOver() {
		// TODO fill code
		GameCamera.isTracking=false;
		Alert alert = new Alert(AlertType.NONE, 
                "Game Over!", 
                ButtonType.OK);

		alert.show();
	}

}
