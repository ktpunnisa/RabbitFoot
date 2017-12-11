package game;


import character.CharacterHolder;
import item.ItemHolder;
import javafx.scene.canvas.Canvas;
import map.MapHolder;

public class GameState extends Canvas
{
	
	public CharacterHolder character;
	public MapHolder map;

	public static int result;
	public static int score;
	public static int diff;
	
	public GameState(int diff)
	{
		score = 0;
		GameState.diff = diff;
		map = new MapHolder();
		character = new CharacterHolder(diff);
		ItemHolder.itemData = null;
	}
}
