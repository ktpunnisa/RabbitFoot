package map;

import character.Animal;
import javafx.scene.paint.Color;

public class NormalBlock extends Block{

	public NormalBlock(int x, int y, int c) {
		super(x, y, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		this.hexagon.setFill(Color.ORANGE);
		this.hexagon.setStrokeWidth(3);
		this.hexagon.setStroke(Color.BLACK);
	}

	@Override
	public void checkEvent() {
		// TODO Auto-generated method stub
		
	}

}
