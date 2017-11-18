package map;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class TrapBlock extends Block{
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
	public void checkEvent() {
		// TODO Auto-generated method stub
		
	}
}
