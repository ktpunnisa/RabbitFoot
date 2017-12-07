package item;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public class Antidote extends Item {

	public Antidote(Pair p) {
		super(p);
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("file:res/block/Antidote.png");
	}

}
