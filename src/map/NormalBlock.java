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

public class NormalBlock extends Block
{

	Boolean hasCarrot;
	Boolean hasPotion;
	Boolean hasItem;
	public NormalBlock(int x, int y, int c) 
	{
		super(x, y, c);
		hasCarrot = false;
		hasPotion = false;
		hasItem = false;
		loadImage();
	}

	@Override
	public void loadImage() 
	{
		if(hasCarrot) {
			Platform.runLater(() -> this.hexagon.setFill(ImageLoader.normalBlockCarrot));
		}
		else if(hasPotion){
			Platform.runLater(() -> this.hexagon.setFill(ImageLoader.normalBlockPotion));
		}
		else if(hasItem) {
			Platform.runLater(() -> this.hexagon.setFill(MapHolder.item.get(index).getBlockImage()));
		}
		else {
			Platform.runLater(() -> this.hexagon.setFill(ImageLoader.normalBlock));
		}
		Platform.runLater(() -> this.hexagon.setStrokeWidth(3));
		Platform.runLater(() -> this.hexagon.setStroke(Color.BLACK));
	}

	@Override
	public void checkEvent(Animal animal) 
	{
		if(animal instanceof Rabbit && hasCarrot) 
		{
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
		
		if(animal instanceof Rabbit && hasPotion) 
		{
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

	public Boolean getHasPotion() 
	{
		return hasPotion;
	}

	public void setHasPotion(Boolean hasPotion) 
	{
		this.hasPotion = hasPotion;
	}

	public Boolean getHasCarrot() 
	{
		return hasCarrot;
	}

	public void setHasCarrot(Boolean hasCarrot) 
	{
		this.hasCarrot = hasCarrot;
	}
}
