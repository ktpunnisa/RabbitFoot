package ui;

import java.util.List;

import character.CharacterHolder;
import game.GameState;
import item.ItemHolder;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import map.Block;
import map.MapHolder;

public class UIGame extends Group 
{
		
	public UIGame()
	{
		this.getChildren().add(MapHolder.mapGroup);
		this.getChildren().add(CharacterHolder.aniData.get(0).body);
		this.getChildren().add(CharacterHolder.aniGroup);
		CharacterHolder.aniGroup.translateXProperty().bind(MapHolder.mapGroup.translateXProperty());
		CharacterHolder.aniGroup.translateYProperty().bind(MapHolder.mapGroup.translateYProperty());
		UIBar bar = new UIBar();
		this.getChildren().add(bar);
		this.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent event) 
			{
				if(event.getCode().equals(KeyCode.LEFT)) {
					CharacterHolder.aniData.get(0).turnLeft();
				}
				else if(event.getCode().equals(KeyCode.RIGHT)) {
					CharacterHolder.aniData.get(0).turnRight();
				}
				else if(event.getCode().equals(KeyCode.SPACE)) {
					ItemHolder.useItem();
				}
			}
		});
	}
}