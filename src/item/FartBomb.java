package item;

import javafx.scene.image.Image;
import utility.Pair;

public class FartBomb extends Item {

	public FartBomb(Pair p) {
		super(p);
	}

	@Override
	public Image getBlockImage() {
		return new Image(ClassLoader.getSystemResourceAsStream("block/FartBomb.png"));
	}

	@Override
	public Image getItemImage() {
		return new Image(ClassLoader.getSystemResourceAsStream("item/FartBomb.png"));
	}
}
