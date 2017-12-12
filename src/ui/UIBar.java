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
	private ImageView board;
	private ImageView itemBoard;
	private static Rectangle itemView;
	private static Text score;
	private Font font;
	private static Rectangle tb;
	private static Timeline inverseTimeBar;

	public UIBar() {
		this.font = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/8bit.ttf"), 35);

		tb = new Rectangle(300, 30);
		inverseTimeBar = new Timeline();
		inverseTimeBar.getKeyFrames()
				.add(new KeyFrame(Duration.ZERO, new KeyValue(tb.widthProperty(), 300, Interpolator.LINEAR)));
		inverseTimeBar.getKeyFrames()
				.add(new KeyFrame(Duration.seconds(15), new KeyValue(tb.widthProperty(), 0, Interpolator.LINEAR)));

		inverseTimeBar.getKeyFrames()
				.add(new KeyFrame(Duration.ZERO, new KeyValue(tb.translateYProperty(), -30, Interpolator.EASE_BOTH)));
		inverseTimeBar.getKeyFrames().add(
				new KeyFrame(Duration.millis(200), new KeyValue(tb.translateYProperty(), 20, Interpolator.EASE_BOTH)));

		inverseTimeBar.getKeyFrames().add(new KeyFrame(Duration.seconds(15), ae -> {
			Platform.runLater(() -> SceneManager.mainFrame.getChildren().remove(tb));
		}));
		inverseTimeBar.setCycleCount(1);

		itemView = new Rectangle(15, 15, 80, 100);
		itemView.setFill(Color.valueOf("4e4a4e"));
		Platform.runLater(() -> this.getChildren().add(itemView));

		itemBoard = new ImageView(new Image(ClassLoader.getSystemResourceAsStream("ui/itemBoard.png")));
		itemBoard.setTranslateX(5);
		itemBoard.setTranslateY(0);
		Platform.runLater(() -> this.getChildren().add(itemBoard));

		board = new ImageView(new Image(ClassLoader.getSystemResourceAsStream("ui/board.png")));
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
			Platform.runLater(() -> itemView.setFill(Color.valueOf("4e4a4e")));
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

	public static void addTimeBar() {
		inverseTimeBar.stop();
		tb.setWidth(300);
		tb.setArcHeight(15);
		tb.setArcWidth(15);
		tb.setFill(Color.CHARTREUSE);
		tb.setStroke(Color.GREEN);
		tb.setStrokeWidth(3);
		tb.setTranslateX(140);
		Platform.runLater(() -> SceneManager.mainFrame.getChildren().add(tb));

		Platform.runLater(() -> inverseTimeBar.play());
	}

	public static void removeTimeBar() {
		Platform.runLater(() -> SceneManager.mainFrame.getChildren().remove(tb));
	}

	public static Rectangle getItemView() {
		return itemView;
	}

	public static void setItemView(Rectangle itemView) {
		UIBar.itemView = itemView;
	}

	public static Text getScore() {
		return score;
	}

	public static void setScore(Text score) {
		UIBar.score = score;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public static Rectangle getTb() {
		return tb;
	}

	public static void setTb(Rectangle tb) {
		UIBar.tb = tb;
	}

	public ImageView getBoard() {
		return board;
	}

	public void setBoard(ImageView board) {
		this.board = board;
	}

	public ImageView getItemBoard() {
		return itemBoard;
	}

	public void setItemBoard(ImageView itemBoard) {
		this.itemBoard = itemBoard;
	}

}
