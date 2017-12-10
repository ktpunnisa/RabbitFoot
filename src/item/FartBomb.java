package item;

import image.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public class FartBomb extends Item {

	public FartBomb(Pair p) {
		super(p);
	}

	@Override
	public ImagePattern getBlockImage() {
		return ImageLoader.BombBlock;
	}

	@Override
	public Image getItemImage() {
		return ImageLoader.BombImage;
	}
}
