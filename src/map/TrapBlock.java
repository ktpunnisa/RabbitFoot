package map;

import character.Animal;
import character.CharacterHolder;
import character.Rabbit;
import character.Wolf;
import game.GameMain;
import game.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class TrapBlock extends Block{
	int i;
	public TrapBlock(int x, int y, int c) {
		super(x, y, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		Image img = new Image("file:res/block/trap.png");
		this.hexagon.setFill(new ImagePattern(img,0,0,1,1,true));
		//this.hexagon.setFill(Color.ORANGE);
		this.hexagon.setStrokeWidth(3);
		this.hexagon.setStroke(Color.BLACK);
	}

	@Override
	public void checkEvent(Animal animal) {
		if(animal instanceof Rabbit) {
			System.out.println("Rabbit die !!");
			//MapHolder.deleteTrap(index);
			GameMain.stopGame();
		}
		if(animal instanceof Wolf) {
			System.out.println("Wolf die !!");
			CharacterHolder.aniData.remove(animal);
			MapHolder.deleteTrap(index);
		}
		
	}
}
