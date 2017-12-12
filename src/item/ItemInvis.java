package item;

import image.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class ItemInvis implements Item {

	@Override
	public ImagePattern getBlockImage() {
		return ImageLoader.getInvisBlock();
	}

	@Override
	public Image getItemImage() {
		return ImageLoader.getInvisImage();
	}

}
