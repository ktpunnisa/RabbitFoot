package ui;

import character.CharacterHolder;
import exception.NoItemException;
import item.ItemHolder;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
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
					try {
						ItemHolder.useItem();
					} catch (NoItemException e) {
						Timeline timeline = new Timeline();
						timeline.getKeyFrames()
								.add(new KeyFrame(Duration.ZERO, new KeyValue(UIBar.getItemView().fillProperty(),
										Color.valueOf("4e4a4e"), Interpolator.EASE_BOTH)));
						timeline.getKeyFrames()
								.add(new KeyFrame(Duration.millis(500), new KeyValue(UIBar.getItemView().fillProperty(),
										Color.DARKGREY, Interpolator.EASE_BOTH)));
						timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue(
								UIBar.getItemView().fillProperty(), Color.valueOf("4e4a4e"), Interpolator.EASE_BOTH)));
						timeline.setCycleCount(1);
						Platform.runLater(() -> timeline.play());
					}
				}
			}
		});
	}
}