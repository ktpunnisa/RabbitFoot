package map;

import character.Animal;
import javafx.scene.paint.Color;

public class VoidBlock extends Block {

	public VoidBlock(int x, int y, int c) {
		super(x, y, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		this.hexagon.setFill(Color.TRANSPARENT);
	}

	@Override
	public void checkEvent(Animal animal) {
		// TODO Auto-generated method stub
		
	}

}
