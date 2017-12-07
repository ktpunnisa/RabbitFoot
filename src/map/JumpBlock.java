package map;

import character.Animal;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public class JumpBlock extends Block {
	public Pair jumpTo;
	public int direction;
	public JumpBlock(int x, int y, int c) {
		super(x, y, c);
		loadImage();
		if(x==3 && y==3) {
			this.jumpTo = new Pair(6,9);
			this.direction = 3;
		}
		else if(x==6 && y==3) {
			this.jumpTo = new Pair(3,9);
			this.direction = 4;
		}
		else if(x==3 && y==6) {
			this.jumpTo = new Pair(9,6);
			this.direction = 2;
		}
		else if(x==9 && y==6) {
			this.jumpTo = new Pair(3,6);
			this.direction = 5;
		}
		else if(x==3 && y==9) {
			this.jumpTo = new Pair(6,3);
			this.direction = 1;
		}
		else if(x==6 && y==9) {
			this.jumpTo = new Pair(3,3);
			this.direction = 0;
		}
	}

	@Override
	public void loadImage() {
		Image img = new Image("file:res/block/jump.png");
		this.hexagon.setFill(new ImagePattern(img,0,0,1,1,true));
		//this.hexagon.setFill(Color.ORANGE);
		this.hexagon.setStrokeWidth(3);
		this.hexagon.setStroke(Color.BLACK);
	}

	@Override
	public void checkEvent(Animal animal) {
		// TODO Auto-generated method stub
		
	}
}
