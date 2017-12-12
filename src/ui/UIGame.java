package ui;

import character.CharacterHolder;
import item.ItemHolder;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import map.MapHolder;

public class UIGame extends Group {

	public UIGame() {
		Platform.runLater(() -> this.getChildren().add(MapHolder.getMapGroup()));
		Platform.runLater(() -> this.getChildren().add(CharacterHolder.getAniData().get(0).getBody()));
		Platform.runLater(() -> this.getChildren().add(CharacterHolder.getAniGroup()));
		CharacterHolder.getAniGroup().translateXProperty().bind(MapHolder.getMapGroup().translateXProperty());
		CharacterHolder.getAniGroup().translateYProperty().bind(MapHolder.getMapGroup().translateYProperty());
		UIBar bar = new UIBar();
		Platform.runLater(() -> this.getChildren().add(bar));
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.LEFT)) {
					CharacterHolder.getAniData().get(0).turnLeft();
				} else if (event.getCode().equals(KeyCode.RIGHT)) {
					CharacterHolder.getAniData().get(0).turnRight();
				} else if (event.getCode().equals(KeyCode.SPACE)) {
					ItemHolder.useItem();
				}
			}
		});
	}
}