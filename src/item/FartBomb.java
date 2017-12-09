package item;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public class FartBomb extends Item {

	public FartBomb(Pair p) {
		super(p);
	}

	@Override
	public Image getBlockImage() {
		// TODO Auto-generated method stub
		return new Image(ClassLoader.getSystemResourceAsStream("block/FartBomb.png"));
	}

	@Override
	public Image getItemImage() {
		// TODO Auto-generated method stub
		return new Image(ClassLoader.getSystemResourceAsStream("item/FartBomb.png"));
	}
}
