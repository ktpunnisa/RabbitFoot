package item;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public class Shit extends Item {

	public Shit(Pair p) {
		super(p);
	}

	@Override
	public Image getBlockImage() {
		// TODO Auto-generated method stub
		return new Image(ClassLoader.getSystemResourceAsStream("block/Shit.png"));
	}

	@Override
	public Image getItemImage() {
		// TODO Auto-generated method stub
		return new Image(ClassLoader.getSystemResourceAsStream("item/Shit.png"));
	}
	
}
