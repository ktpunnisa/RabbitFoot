package map;

import character.Animal;
import character.CharacterHolder;
import character.Rabbit;
import game.GameLogic;
import game.GameSound;
import game.GameState;
import image.ImageLoader;
import item.Item;
import item.ItemHolder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import ui.UIBar;

public class NormalBlock extends Block {

	public boolean hasCarrot;
	public boolean hasPotion;
	public boolean hasItem;

	public NormalBlock(int x, int y, int c) {
		super(x, y, c);
		hasCarrot = false;
		hasPotion = false;
		hasItem = false;
		loadImage();
	}

	@Override
	public void loadImage() {
		if (hasCarrot) {
			Platform.runLater(() -> this.hexagon.setFill(ImageLoader.getNormalBlockCarrot()));
		} else if (hasPotion) {
			Platform.runLater(() -> this.hexagon.setFill(ImageLoader.getNormalBlockPotion()));
		} else if (hasItem) {
			Platform.runLater(() -> this.hexagon.setFill(MapHolder.getItem().get(index).getBlockImage()));
		} else {
			Platform.runLater(() -> this.hexagon.setFill(ImageLoader.getNormalBlock()));
		}
		Platform.runLater(() -> this.hexagon.setStrokeWidth(3));
		Platform.runLater(() -> this.hexagon.setStroke(Color.BLACK));
	}

	@Override
	public void checkEvent(Animal animal) {
		if (animal instanceof Rabbit && hasCarrot) {
			setHasCarrot(false);
			MapHolder.getCarrot().remove(index);
			MapHolder.createCarrot();
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500 * animal.getSpeed()), ae -> {
				GameState.setScore(GameState.getScore() + 1);
				loadImage();
				GameSound.playSoundEat();
			}));
			timeline.setCycleCount(1);
			Platform.runLater(() -> timeline.play());

		}

		if (animal instanceof Rabbit && hasPotion) {
			setHasPotion(false);
			MapHolder.deletePotion(false);
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500 * animal.getSpeed()), ae -> {
				loadImage();
				GameSound.playSoundEat();
				GameState.setInverse(true);
				GameState.setTimeInverse(GameLogic.getSeconds());
				for (Animal x : CharacterHolder.getAniData()) {
					x.setInverse(true);
					if (x instanceof Rabbit) {
						x.setSpeed(0.9);
					} else {
						x.setSpeed(1.5);
					}
				}
				UIBar.addTimeBar(15);
			}));
			timeline.setCycleCount(1);
			Platform.runLater(() -> timeline.play());

		}

		if (animal instanceof Rabbit && hasItem) {
			if (ItemHolder.getItemData() != null)
				return;
			Item i = MapHolder.getItem().get(index);
			ItemHolder.setItemData(i);
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500 * animal.getSpeed()), ae -> {
				MapHolder.deleteItem(index);
				GameSound.playSoundEat();
			}));
			timeline.setCycleCount(1);
			Platform.runLater(() -> timeline.play());
		}
	}

	public Boolean getHasPotion() {
		return hasPotion;
	}

	public void setHasPotion(Boolean hasPotion) {
		this.hasPotion = hasPotion;
	}

	public Boolean getHasCarrot() {
		return hasCarrot;
	}

	public void setHasCarrot(Boolean hasCarrot) {
		this.hasCarrot = hasCarrot;
	}
}
