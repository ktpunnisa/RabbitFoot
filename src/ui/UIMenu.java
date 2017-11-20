package ui;

import game.GameMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class UIMenu extends Pane {
	
	public ImageView diffSelector[];
	public UIMenu()
	{
		diffSelector = new ImageView[3];
		int Allwidth = 50;
		for(int i=0;i<3;++i) {
			Image temp = new Image("file:res/menu/B"+(i+1)+".png");
			diffSelector[i] = new ImageView(temp);
			final int diff = i;
			diffSelector[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(event.getButton().equals(MouseButton.PRIMARY)){
			            GameMain.newGame(diff+1);
			        }
				}
			});
			diffSelector[i].relocate(Allwidth, 450);
			Allwidth+=temp.getWidth()+50;
		}
		this.getChildren().addAll(diffSelector);
		addKeyEventHandler();
	}
	
	private void addKeyEventHandler() {
		
	}
}
