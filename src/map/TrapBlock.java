package map;

import character.Animal;
import character.CharacterHolder;
import character.Rabbit;
import character.Wolf;
import game.GameMain;
import game.GameSound;
import game.GameState;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import scene.SceneManager;
import ui.UIGame;

public class TrapBlock extends Block{

	public TrapBlock(int x, int y, int c) {
		super(x, y, c);
		loadImage();
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		Image img = new Image("file:res/block/trap.png");
		Platform.runLater(() -> this.hexagon.setFill(new ImagePattern(img,0,0,1,1,true)));
		Platform.runLater(() -> this.hexagon.setStrokeWidth(3));
		Platform.runLater(() -> this.hexagon.setStroke(Color.BLACK));
	}

	@Override
	public void checkEvent(Animal animal) {
		if(animal instanceof Rabbit) {
			System.out.println("Rabbit die !!");
			//MapHolder.deleteTrap(index);
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500*animal.speed), 
					ae -> {
						GameSound.playSoundDie();
						GameMain.stopGame();
					}));
			timeline.setCycleCount(1);
			Platform.runLater(() -> timeline.play());
		}
		
	}
}
