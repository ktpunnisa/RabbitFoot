package item;

import java.util.HashMap;
import java.util.Map;

import character.Animal;
import character.CharacterHolder;
import character.Wolf;
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
			Pair r = CharacterHolder.aniData.get(0).index;
			for(Animal a : CharacterHolder.aniData.subList(1, CharacterHolder.aniData.size())) {
				if(r.distance(a.index) <= 2) {
					((Wolf)a).setStun(true);
				}
			}
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000),
					ae -> {
						for(Animal a : CharacterHolder.aniData.subList(1, CharacterHolder.aniData.size())) {
							((Wolf)a).setStun(false);
							
						}
					}));
			timeline.setCycleCount(1);
			timeline.play();
		}
		else if(itemData instanceof Shit)
		{
			CharacterHolder.aniData.get(0).setSpeed(0.6);
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(4000),
					ae -> {
						CharacterHolder.aniData.get(0).setSpeed(1.4);
					}));
		}
		itemData=null;
		updateItem();
	}
}
