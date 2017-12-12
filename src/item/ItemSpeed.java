package item;

import image.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class ItemSpeed implements Item {

	@Override
	public ImagePattern getBlockImage() {
		return ImageLoader.getSpeedBlock();
	}

	@Override
	public Image getItemImage() {
		return ImageLoader.getSpeedImage();
	}

}
