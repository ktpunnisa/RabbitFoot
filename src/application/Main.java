package application;

import java.awt.DisplayMode;

import org.omg.PortableInterceptor.DISCARDING;

import character.CharacterHolder;
import game.GameCamera;
import game.GameMain;
import game.GameState;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import map.MapHolder;
import ui.UIGame;
import scene.SceneManager;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoMenu();
			primaryStage.setTitle("Catch me if you can!");
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		GameMain.stopGame();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}