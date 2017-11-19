package game;

import character.CharacterHolder;
import map.MapHolder;

public class GameState {
	
	public CharacterHolder character;
	public MapHolder map;
	public static int score = 0;

	public GameState(int diff, int wolf) {
		// TODO Auto-generated constructor stub
		map = new MapHolder();
		map.genMap(diff);
		character = new CharacterHolder();
		character.genAnimal(diff,wolf);
	}

}
