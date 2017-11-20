package ui;

import game.GameMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class UIMenu extends Group {
	
	public Button next;
	public UIMenu()
	{
		next = new Button("Next!");
		this.getChildren().add(next);
		addKeyEventHandler();
	}
	
	private void addKeyEventHandler() {
		next.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            GameMain.newGame(5,1);
		        }
		    }
		});
	}
}
