package item;

import image.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Pair;

public class ItemInvis extends Item 
{
	public ItemInvis(Pair p) 
	{
		super(p);
	}
	
	@Override
	public ImagePattern getBlockImage() 
	{
		return ImageLoader.InvisBlock;
	}

	@Override
	public Image getItemImage() 
	{
		return ImageLoader.InvisImage;
	}

}
