package item;

import image.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public class Shit extends Item {

	public Shit(Pair p) {
		super(p);
	}

	@Override
	public ImagePattern getBlockImage() {
		return ImageLoader.SpeedBlock;
	}

	@Override
	public Image getItemImage() {
		return ImageLoader.SpeedImage;
	}
	
}
