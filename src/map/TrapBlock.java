package map;

import character.Animal;
import character.Rabbit;
import game.GameMain;
import game.GameSound;
import image.ImageLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TrapBlock extends Block
{

	public TrapBlock(int x, int y, int c) 
	{
		super(x, y, c);
		loadImage();
	}

	@Override
	public void loadImage() 
	{
		Platform.runLater(() -> this.hexagon.setFill(ImageLoader.getTrapBlock()));
		Platform.runLater(() -> this.hexagon.setStrokeWidth(3));
		Platform.runLater(() -> this.hexagon.setStroke(Color.BLACK));
	}

	@Override
	public void checkEvent(Animal animal) 
	{
		if(animal instanceof Rabbit) {
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500*animal.getSpeed()), 
					ae -> {
						GameSound.playSoundDie();
						GameMain.stopGame();
					}));
			timeline.setCycleCount(1);
			Platform.runLater(() -> timeline.play());
		}
		
	}
}
