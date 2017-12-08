package map;

import character.Animal;
import javafx.scene.paint.Color;

public class VoidBlock extends Block {

	public VoidBlock(int x, int y, int c) {
		super(x, y, c);
		loadImage();
	}

	@Override
	public void loadImage() {
		this.hexagon.setFill(Color.TRANSPARENT);
	}

	@Override
	public void checkEvent(Animal animal) {
		
	}

}
