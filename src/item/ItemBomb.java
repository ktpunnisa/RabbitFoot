package item;

import image.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class ItemBomb implements Item {

	@Override
	public ImagePattern getBlockImage() {
		return ImageLoader.getBombBlock();
	}

	@Override
	public Image getItemImage() {
		return ImageLoader.getBombImage();
	}
}
