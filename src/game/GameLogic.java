package game;

import character.Animal;
import character.CharacterHolder;
import map.MapHolder;
import ui.UIGame;
import utility.RandomGenerator;

public class GameLogic {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	
	private UIGame gameUI;
	private GameState state;
	private GameCamera camera;
	public static boolean isGameRunning;
	
	public GameLogic(UIGame gameUI, GameState state, GameCamera camera)
	{
		this.gameUI = gameUI;
		this.state = state;
		this.camera = camera;
		this.camera.startTrack();
		isGameRunning = false;
	}
	public void startGame() {
		isGameRunning = true;
		//canvas.setWordString(model.getCurrentWordString());
		new Thread(this::gameLoop, "Game Loop Thread").start();
	}

	public void stopGame() {
		camera.stopTrack();
		isGameRunning = false;
	}
	
	private void gameLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isGameRunning) {
			long elapsedTime = System.nanoTime() - lastLoopStartTime;
			if (elapsedTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;

				updateGame();
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateGame()
	{
		/*RandomGenerator.setSeed(System.nanoTime());
		
		if(CharacterHolder.aniData.size()==1) {
			GameState.level+=GameState.diff;
			CharacterHolder.genAnimal(GameState.level);
		}
		if(CharacterHolder.aniData.size()>1)
			for(Animal a : CharacterHolder.aniData.subList(1, CharacterHolder.aniData.size())) {
				if(a.index.equals(CharacterHolder.aniData.get(0).index)) {
					GameMain.stopGame();
				}
			}
		while(MapHolder.carrot.size() < 10-GameState.diff) {
			MapHolder.createCarrot();
		}
		while(MapHolder.trap.size() < CharacterHolder.aniData.size()) {
			MapHolder.createTrap();
		}*/
	}
}
