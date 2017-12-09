package map;

import character.Animal;
import character.CharacterHolder;
import character.Rabbit;
import game.GameLogic;
import game.GameSound;
import game.GameState;
import item.Antidote;
import item.FartBomb;
import item.Item;
import item.ItemHolder;
import item.Shit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import ui.UIBar;

public class NormalBlock extends Block{

	Boolean hasCarrot;
	Boolean hasPotion;
	Boolean hasItem;
	public NormalBlock(int x, int y, int c) {
		super(x, y, c);
		hasCarrot = false;
		hasPotion = false;
		hasItem = false;
		loadImage();
	}

	@Override
	public void loadImage() {
		Image img;
		if(hasCarrot) {
			img = new Image(ClassLoader.getSystemResourceAsStream("block/carrot.png"));
		}
		else if(hasPotion){
			img = new Image(ClassLoader.getSystemResourceAsStream("block/potion.png"));
		}
		else if(hasItem) {
			img = MapHolder.item.get(index).getBlockImage();
		}
		else {
			img = new Image(ClassLoader.getSystemResourceAsStream("block/grass.png"));
		}
		Platform.runLater(() -> this.hexagon.setFill(new ImagePattern(img,0,0,1,1,true)));
		Platform.runLater(() -> this.hexagon.setStrokeWidth(3));
		Platform.runLater(() -> this.hexagon.setStroke(Color.BLACK));
	}

	@Override
	public void checkEvent(Animal animal) {
		if(animal instanceof Rabbit && hasCarrot) {
			setHasCarrot(false);
			MapHolder.carrot.remove(index);
			MapHolder.createCarrot(); 
 			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500 * animal.speed),
 					ae -> {
 						GameState.score++;
 				 		loadImage();
 				 		GameSound.playSoundEat();
 					}));
			timeline.setCycleCount(1);
			Platform.runLater(() -> timeline.play());
			
		}
		
		if(animal instanceof Rabbit && hasPotion) {
			System.out.println("inverse mode @ "+ GameLogic.seconds);
			//UIBar.addTimeBar(15);
			setHasPotion(false);
			MapHolder.deletePotion(false);
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500*animal.speed),
					ae -> {
						loadImage();
						GameSound.playSoundEat();
						CharacterHolder.inverse = true;
						CharacterHolder.timeInverse = GameLogic.seconds;
						for(Animal x : CharacterHolder.aniData) {
							x.setInverse(true);
							if(x instanceof Rabbit){
								x.setSpeed(0.9);
							}
							else {
								x.setSpeed(1.5);
							}
						}		
					}));
			timeline.setCycleCount(1);
			Platform.runLater(() -> timeline.play());
				
		}
		
		if(animal instanceof Rabbit && hasItem)
		{
			if(ItemHolder.itemData!=null) return;
			Item i = MapHolder.item.get(index);
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500*animal.speed),
					ae -> {
						MapHolder.deleteItem(index);
						GameSound.playSoundEat();
						ItemHolder.setItemData(i);
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
