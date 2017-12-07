package item;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public abstract class Item {
	public Pair index;
	public Item(Pair p)
	{
		index = p;
	}
	public abstract Image getImage();
}
