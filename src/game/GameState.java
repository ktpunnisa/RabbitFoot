package game;

import character.CharacterHolder;
import map.MapHolder;

public class GameState {
	
	public static CharacterHolder character;
	public static MapHolder map;
	
	public GameState(MapHolder m,CharacterHolder c)
	{
		this.map=m;
		this.character=c;
	}
}
