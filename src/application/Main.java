package application;

import character.CharacterHolder;
import game.GameState;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import map.MapHolder;
import ui.UIGame;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		//using scenemanager later
		MapHolder map = new MapHolder();
		map.genMap(0);
		CharacterHolder character = new CharacterHolder();
		character.genAnimal(0);
		GameState state = new GameState(map, character);
		UIGame gameDisplay = new UIGame(state);
		
		Scene sc = new Scene(gameDisplay);
		primaryStage.setScene(sc);
		sc.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.isPrimaryButtonDown())
			    {
					//((Polygon)MapHolder.mapData.get(0).get(0).hexagon).setFill(Color.RED);
					CharacterHolder.aniData.get(0).turnLeft();

			    }
			    else if(mouseEvent.isSecondaryButtonDown())
			    {
			    	CharacterHolder.aniData.get(0).turnRight();
			    }
			}
			});
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
