package game;


import character.CharacterHolder;
import item.ItemHolder;
import javafx.scene.canvas.Canvas;
import map.MapHolder;

public class GameState extends Canvas{
	
	public static int GAME_WIN = 1;
	public static int GAME_LOSE = 0;
	
	public CharacterHolder character;
	public MapHolder map;

	public static int result;
	public static int score;
	public static int diff;
	public static int level;
	public static boolean isImmortal;
	
	public GameState(int diff)
	{
		score = 0;
		map = new MapHolder();
		map.genMap(diff);
		character = new CharacterHolder();
		GameState.diff = diff;
		GameState.level = diff;
		isImmortal = false;
		CharacterHolder.genAnimal(diff);
		ItemHolder.itemData = null;
	}
	

}
