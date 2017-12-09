package map;

import character.Animal;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class VoidBlock extends Block {

	public VoidBlock(int x, int y, int c) {
		super(x, y, c);
		loadImage();
	}

	@Override
	public void loadImage() {
		Platform.runLater(() -> this.hexagon.setFill(Color.TRANSPARENT));
	}

	@Override
	public void checkEvent(Animal animal) {
		
	}

}
