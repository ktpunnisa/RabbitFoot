package item;

import image.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public class ItemBomb extends Item {

	public ItemBomb(Pair p) {
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
