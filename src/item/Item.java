package item;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public abstract class Item 
{
	private Pair index;
	public Item(Pair p)
	{
		index = p;
	}
	public abstract ImagePattern getBlockImage();
	public abstract Image getItemImage();
	public Pair getIndex() {
		return index;
	}
	public void setIndex(Pair index) {
		this.index = index;
	}
}
