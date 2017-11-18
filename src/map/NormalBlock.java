package map;

import character.Animal;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class NormalBlock extends Block{

	public NormalBlock(int x, int y, int c) {
		super(x, y, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		Image img = new Image("res/block/grass.png");
		this.hexagon.setFill(new ImagePattern(img,0,0,1,1,true));
		//this.hexagon.setFill(Color.ORANGE);
		this.hexagon.setStrokeWidth(3);
		this.hexagon.setStroke(Color.BLACK);
	}

	@Override
	public void checkEvent() {
		// TODO Auto-generated method stub
		
	}

}
