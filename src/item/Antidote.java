package item;

import image.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public class Antidote extends Item {

	public Antidote(Pair p) {
		super(p);
	}
	
	@Override
	public ImagePattern getBlockImage() {
		return ImageLoader.AntidoteBlock;
	}

	@Override
	public Image getItemImage() {
		return ImageLoader.AntidoteImage;
	}

}
