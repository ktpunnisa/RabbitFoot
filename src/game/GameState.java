package game;


import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import character.CharacterHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import map.MapHolder;
//import window.SceneManager;
import scene.SceneManager;
import utility.RandomGenerator;

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
	}
	

}
