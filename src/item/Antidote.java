package item;

import javafx.scene.image.Image;
import utility.Pair;

public class Antidote extends Item {

	public Antidote(Pair p) {
		super(p);
	}
	
	@Override
	public Image getBlockImage() {
		return new Image(ClassLoader.getSystemResourceAsStream("block/Antidote.png"));
	}

	@Override
	public Image getItemImage() {
		return new Image(ClassLoader.getSystemResourceAsStream("item/Antidote.png"));
	}

}
