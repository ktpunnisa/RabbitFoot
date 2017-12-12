package item;

import character.Animal;
import character.CharacterHolder;
import character.Wolf;
import exception.NoItemException;
import game.GameMain;
import game.GameSound;
import game.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ui.UIBar;
import utility.Pair;

public class ItemHolder {
	private static Item itemData;

	public ItemHolder() {
		itemData = null;
	}

	public static Item getItemData() {
		return itemData;
	}

	public static void setItemData(Item itemData) {
		ItemHolder.itemData = itemData;
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(500 * CharacterHolder.getAniData().get(0).getSpeed()), ae -> {
					updateItem();
				}));
		timeline.setCycleCount(1);
		Platform.runLater(() -> timeline.play());
	}

	private static void updateItem() {
		UIBar.changeItemView(itemData);
	}

	public void add(Item item) {
		itemData = item;
	}

	public void remove() {
		itemData = null;
	}

	public static void useItem() throws NoItemException {
		if (itemData == null) {
			throw new NoItemException();
		}
		if (itemData instanceof ItemInvis) {
			GameState.setInvis(true);
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000), ae -> {
				GameState.setInvis(false);
			}));
			timeline.setCycleCount(1);
			Platform.runLater(() -> timeline.play());
		} else if (itemData instanceof ItemBomb) {
			ImageView b = new ImageView(
					new Image(ClassLoader.getSystemResourceAsStream("item/bomb.png"), 120, 120, false, false));
			Platform.runLater(() -> b.setTranslateX(340));
			Platform.runLater(() -> b.setTranslateY(240));
			Platform.runLater(() -> GameMain.getGameUI().getChildren().add(1, b));
			GameSound.playSoundExplosion();
			Pair r = CharacterHolder.getAniData().get(0).getIndex();
			for (Animal a : CharacterHolder.getAniData().subList(1, CharacterHolder.getAniData().size())) {
				if (r.distance(a.getIndex()) <= 2) {
					((Wolf) a).setStun(true);
				}
			}
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000), ae -> {
				for (Animal a : CharacterHolder.getAniData().subList(1, CharacterHolder.getAniData().size())) {
					((Wolf) a).setStun(false);
				}
			}));
			timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), ae -> {
				Platform.runLater(() -> GameMain.getGameUI().getChildren().remove(1));
			}));
			timeline.setCycleCount(1);
			Platform.runLater(() -> timeline.play());
		} else if (itemData instanceof ItemSpeed) {
			CharacterHolder.getAniData().get(0).setSpeed(0.6);
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(4000), ae -> {
				CharacterHolder.getAniData().get(0).setSpeed(1.4);
			}));
			Platform.runLater(() -> timeline.play());
		}
		itemData = null;
		updateItem();
	}

}
