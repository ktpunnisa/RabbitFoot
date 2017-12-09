package item;

import javafx.scene.image.Image;
import utility.Pair;

public abstract class Item {
	public Pair index;
	public Item(Pair p)
	{
		index = p;
	}
	public abstract Image getBlockImage();
	public abstract Image getItemImage();
}
