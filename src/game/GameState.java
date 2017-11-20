package game;

import character.CharacterHolder;
import map.MapHolder;

public class GameState {
	
	public static int GAME_WIN = 1;
	public static int GAME_LOSE = 0;
	
	public CharacterHolder character;
	public MapHolder map;

	public static int result;
	public static int score = 0;
	public static int diff;
	public static int level;
	
	public GameState(int diff)
	{
		map = new MapHolder();
		map.genMap(diff);
		character = new CharacterHolder();
		GameState.diff = diff;
		CharacterHolder.genAnimal(diff);
	}

}
