package game;


import character.CharacterHolder;
import item.ItemHolder;
import javafx.scene.canvas.Canvas;
import map.MapHolder;

public class GameState extends Canvas
{
	
	private CharacterHolder character;
	private MapHolder map;
	private static boolean inverse;
	private static long timeInverse;
	private static boolean invis;

	private static int score;
	private static int diff;
	
	public GameState(int diff)
	{
		inverse = false;
		invis = false;
		timeInverse = 0;
		score = 0;
		GameState.diff = diff;
		map = new MapHolder();
		character = new CharacterHolder(diff);
		ItemHolder.setItemData(null);
	}

	public CharacterHolder getCharacter() {
		return character;
	}

	public void setCharacter(CharacterHolder character) {
		this.character = character;
	}

	public MapHolder getMap() {
		return map;
	}

	public void setMap(MapHolder map) {
		this.map = map;
	}

	public static boolean isInverse() {
		return inverse;
	}

	public static void setInverse(boolean inverse) {
		GameState.inverse = inverse;
	}

	public static long getTimeInverse() {
		return timeInverse;
	}

	public static void setTimeInverse(long timeInverse) {
		GameState.timeInverse = timeInverse;
	}

	public static boolean isInvis() {
		return invis;
	}

	public static void setInvis(boolean invis) {
		GameState.invis = invis;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameState.score = score;
	}

	public static int getDiff() {
		return diff;
	}

	public static void setDiff(int diff) {
		GameState.diff = diff;
	}
	
	
}
