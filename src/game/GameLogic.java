package game;

import java.util.HashSet;
import java.util.Set;

import character.Animal;
import character.CharacterHolder;
import character.Rabbit;
import map.MapHolder;
import ui.UIGame;
import utility.Pair;
import utility.RandomGenerator;

public class GameLogic {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	
	private UIGame gameUI;
	private GameState state;
	private GameCamera camera;
	public static boolean isGameRunning;
	public static long seconds;
	
	public GameLogic(UIGame gameUI, GameState state, GameCamera camera)
	{
		this.gameUI = gameUI;
		this.state = state;
		this.camera = camera;
		this.camera.startTrack();
		this.state.startState();
		isGameRunning = false;
	}
	public void startGame() {
		isGameRunning = true;
		//canvas.setWordString(model.getCurrentWordString());
		new Thread(this::gameLoop, "Game Loop Thread").start();
		for(Animal a : CharacterHolder.aniData)
			a.startRunning();
	}

	public void stopGame() {
		camera.stopTrack();
		state.stopState();
		isGameRunning = false;
		for(Animal a:CharacterHolder.aniData)
			a.stopRunning();
	}
	
	private void gameLoop() {
		long lastLoopStartTime = System.nanoTime();
		long startTime = System.nanoTime();
		int i=0;
		while (isGameRunning) {
			long elapsedTime = System.nanoTime() - lastLoopStartTime;
			if (elapsedTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;
				seconds = (long)((double)(System.nanoTime() - startTime) / 1000000000.0);
				//System.out.println((int)seconds);
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
		RandomGenerator.setSeed(System.nanoTime());
		/*if(CharacterHolder.aniData.size()==1) {
			GameState.level+=GameState.diff;
			CharacterHolder.genAnimal(GameState.level);
		}*/
		if(CharacterHolder.aniData.size()>1) {
			Set<Animal> kill = new HashSet<>();
			for(Animal a : CharacterHolder.aniData.subList(1, CharacterHolder.aniData.size())) {
				if(a.index.equals(CharacterHolder.aniData.get(0).index)) {
					if(CharacterHolder.aniData.get(0).isInverse()) {
						System.out.println("Rabbit eat wolf!! @ "+ a.index);
						GameSound.playSoundWolfDie();
						kill.add(a);
						GameState.score+=10;
					}
					else {
						System.out.println("Wolf eat Rabbit!! @ "+ a.index);
						GameSound.playSoundWolf();
						GameMain.stopGame();
					}
				}
			}
			for(Animal a : kill) {
				//System.out.println("kill"+a.index);
				CharacterHolder.aniData.remove(a);
			}
		}
		while(MapHolder.carrot.size() < 10-GameState.diff) {
			MapHolder.createCarrot();
		}
		while(MapHolder.trap.size() < GameState.level) {
			MapHolder.createTrap();
		}
		if(seconds%10==0 && MapHolder.potionTime!=seconds && !CharacterHolder.inverse) {
			System.out.println(seconds+" seconds");
			if(MapHolder.potionTime!=0) {
				System.out.println("delete potion");
				MapHolder.deletePotion(true);
				
			}
			System.out.println("create potion");
			MapHolder.potionTime = seconds;
			MapHolder.createPotion();
		}
		if(CharacterHolder.timeInverse+15==seconds && CharacterHolder.inverse) {
			System.out.println("normal mode : "+ seconds);
			CharacterHolder.inverse = false;
			CharacterHolder.timeInverse = 0;
			for(Animal x : CharacterHolder.aniData) {
				x.setInverse(false);
				if(x instanceof Rabbit){
					x.setSpeed(1);
				}
				else {
					x.setSpeed(0.8);
				}
			}
		}
	}
}
