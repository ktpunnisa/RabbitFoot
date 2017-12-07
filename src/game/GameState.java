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

public class GameState extends Canvas{
	
	public static int GAME_WIN = 1;
	public static int GAME_LOSE = 0;
	
	public CharacterHolder character;
	public MapHolder map;
	private Thread gameState;
	private boolean isStateRunning;
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	
	private static final Font TEXTFONT = new Font("Monospace", 30);

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
		GameState.level = diff;
		CharacterHolder.genAnimal(diff);
		this.isStateRunning = false;
		startState();
	}
	
	public void startState() {
		GameSound.playSoundBG();
		gameState = new Thread(this::stateLoop, "Game Animation Thread");
		isStateRunning = true;
		gameState.start();
	}

	public void stopState() {
		isStateRunning = false;
		GameSound.stopSoundBG();
	}

	private void stateLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isStateRunning) {
			long now = System.nanoTime();
			if (now - lastLoopStartTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;

				updateState(now);
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateState(long now) {
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		// TODO fill code
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(0,0,SceneManager.SCENE_WIDTH,80);		
		gc.setFont(TEXTFONT);
		gc.setFill(Color.BLACK);
		gc.fillText("Score: " + score,10,10+fontLoader.getFontMetrics(TEXTFONT).getLineHeight());
		if(CharacterHolder.inverse) {
			gc.fillText("Inverse mode", 
						SceneManager.SCENE_WIDTH-10-fontLoader.computeStringWidth("Inverse mode", TEXTFONT),
						10+fontLoader.getFontMetrics(TEXTFONT).getLineHeight());
		}
		else {
			gc.fillText("Normal mode", 
					SceneManager.SCENE_WIDTH-10-fontLoader.computeStringWidth("Normal mode", TEXTFONT),
					10+fontLoader.getFontMetrics(TEXTFONT).getLineHeight());
		}
	}
	
	

}
