package ui;

import item.ItemInvis;
import item.ItemBomb;

import item.Item;
import item.ItemSpeed;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import scene.SceneManager;

public class UIBar extends Group {
	public static Rectangle itemView;
	public static Text score;
	private Font font;
	public static Rectangle tb;

	public UIBar() {
		this.font = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/8bit.ttf"), 35);
		ImageView itemBoard = new ImageView(new Image(ClassLoader.getSystemResourceAsStream("ui/itemBoard.png")));
		itemBoard.setTranslateX(5);
		itemBoard.setTranslateY(0);
		Platform.runLater(() -> this.getChildren().add(itemBoard));
		itemView = new Rectangle(15, 15, 80, 100);
		itemView.setFill(Color.TRANSPARENT);
		Platform.runLater(() -> this.getChildren().add(itemView));

		ImageView board = new ImageView(new Image(ClassLoader.getSystemResourceAsStream("ui/board.png")));
		board.setTranslateX(500);
		board.setTranslateY(0);
		Platform.runLater(() -> this.getChildren().add(board));
		score = new Text();
		score.setFont(font);
		score.setFill(Color.WHITE);
		score.setTranslateY(50);
		Platform.runLater(() -> this.getChildren().add(score));
	}

	public static void setScore(String s) {
		Platform.runLater(() -> score.setText(s));
		Platform.runLater(() -> score.setTranslateX(284 / 2 + 500 - score.getLayoutBounds().getWidth() / 2));
	}

	public static void changeItemView(Item i) {
		if (i == null) {
			Platform.runLater(() -> itemView.setFill(Color.TRANSPARENT));
		} else {
			if (i instanceof ItemInvis) {
				Platform.runLater(
						() -> itemView.setFill(new ImagePattern(((ItemInvis) i).getItemImage(), 0, 0, 1, 1, true)));
			} else if (i instanceof ItemBomb) {
				Platform.runLater(
						() -> itemView.setFill(new ImagePattern(((ItemBomb) i).getItemImage(), 0, 0, 1, 1, true)));
			} else if (i instanceof ItemSpeed) {
				Platform.runLater(
						() -> itemView.setFill(new ImagePattern(((ItemSpeed) i).getItemImage(), 0, 0, 1, 1, true)));
			}
		}
	}

	public static void addTimeBar(int sec) {
		tb = new Rectangle(300, 30);
		tb.setArcHeight(15);
		tb.setArcWidth(15);
		tb.setFill(Color.CHARTREUSE);
		tb.setStroke(Color.GREEN);
		tb.setStrokeWidth(3);
		tb.setTranslateX(140);
		Platform.runLater(() -> SceneManager.mainFrame.getChildren().add(tb));
		Timeline timeline = new Timeline();
		timeline.getKeyFrames()
				.add(new KeyFrame(Duration.ZERO, new KeyValue(tb.widthProperty(), 300, Interpolator.LINEAR)));
		timeline.getKeyFrames()
				.add(new KeyFrame(Duration.seconds(sec), new KeyValue(tb.widthProperty(), 0, Interpolator.LINEAR)));

		timeline.getKeyFrames()
				.add(new KeyFrame(Duration.ZERO, new KeyValue(tb.translateYProperty(), -30, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(200), new KeyValue(tb.translateYProperty(), 20, Interpolator.EASE_BOTH)));

		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(sec), ae -> {
			Platform.runLater(() -> SceneManager.mainFrame.getChildren().remove(tb));
		}));
		timeline.setCycleCount(1);
		Platform.runLater(() -> timeline.play());
	}
}
