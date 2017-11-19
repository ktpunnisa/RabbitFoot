package game;

import character.CharacterHolder;
import map.MapHolder;

public class GameState {
	
	public CharacterHolder character;
	public MapHolder map;
	
	public GameState(int diff)
	{
		map = new MapHolder();
		map.genMap(diff);
		character = new CharacterHolder();
		character.genAnimal(diff);
	}
}
