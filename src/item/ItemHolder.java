package item;

import java.util.HashMap;
import java.util.Map;

import game.GameSound;
import game.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ui.UIBar;
import utility.Pair;

public class ItemHolder {
	public static Item itemData;
	public ItemHolder()
	{
		itemData = null;
	}
	public static Item getItemData() {
		return itemData;
	}
	public static void setItemData(Item itemData) {
		ItemHolder.itemData = itemData;
		updateItem();
	}
	private static void updateItem()
	{
		UIBar.changeItemView(itemData);
	}
	public void add(Item item)
	{
		itemData = item;
	}
	public void remove()
	{
		itemData = null;
	}
	public static void useItem()
	{
		if(itemData==null) return;
		if(itemData instanceof Antidote) 
		{
			//change rabbit image animation
			GameState.isImmortal = true;
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000),
					ae -> {
						GameState.isImmortal = false;	
					}));
			timeline.setCycleCount(1);
			timeline.play();
		}
		else if(itemData instanceof FartBomb)
		{
			
		}
		else if(itemData instanceof Shit)
		{
			
		}
		itemData=null;
		updateItem();
	}
}
