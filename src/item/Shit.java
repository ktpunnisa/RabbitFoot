package item;

import javafx.scene.image.Image;
import utility.Pair;

public class Shit extends Item {

	public Shit(Pair p) {
		super(p);
	}

	@Override
	public Image getBlockImage() {
		return new Image(ClassLoader.getSystemResourceAsStream("block/Shit.png"));
	}

	@Override
	public Image getItemImage() {
		return new Image(ClassLoader.getSystemResourceAsStream("item/Shit.png"));
	}
	
}
