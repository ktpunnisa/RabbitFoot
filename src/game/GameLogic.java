package game;

import java.util.HashSet;
import java.util.Set;

import character.Animal;
import character.CharacterHolder;
import character.Rabbit;
import character.Wolf;
import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import map.MapHolder;
import ui.UIBar;
import ui.UIGame;
import utility.Pair;
import utility.RandomGenerator;

public class GameLogic {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	
	private UIGame gameUI;
	private GameState state;
	public static boolean isGameRunning;
	public static long seconds;
	
	public GameLogic(UIGame gameUI, GameState state)
	{
		this.gameUI = gameUI;
		this.state = state;
		isGameRunning = false;
	}
	public void startGame() {
		isGameRunning = true;
		new Thread(this::gameLoop, "Game Loop Thread").start();
		for(Animal a : CharacterHolder.aniData)
			a.startRunning();
	}

	public void stopGame() {
		isGameRunning = false;
		for(Animal a:CharacterHolder.aniData)
			a.stopRunning();
	}
	
	private void gameLoop() {
		long lastLoopStartTime = System.nanoTime();
		long startTime = System.nanoTime();
		while (isGameRunning) {
			long elapsedTime = System.nanoTime() - lastLoopStartTime;
			if (elapsedTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;
				seconds = (long)((System.nanoTime() - startTime) / 1000000000.0);
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
		Platform.runLater(()->UIBar.score.setText("Score: "+GameState.score));
		if(CharacterHolder.aniData.size()<GameState.diff+1) {
			state.character.add();
		}
		if(CharacterHolder.aniData.size()>1 && !GameState.isImmortal) {
			Set<Animal> kill = new HashSet<>();
			for(Animal a : CharacterHolder.aniData.subList(1, CharacterHolder.aniData.size())) {
				if(a.index.equals(CharacterHolder.aniData.get(0).index)) {
					if(CharacterHolder.aniData.get(0).isInverse()) {
						GameSound.playSoundWolfDie();
						kill.add(a);
						GameState.score+=10;
					}
					else if(!((Wolf)a).isStun()) {
						GameSound.playSoundWolf();
						GameMain.stopGame();
					}
				}
			}
			for(Animal a : kill) {
				Platform.runLater(() -> CharacterHolder.aniGroup.getChildren().remove(a.body));
				((Wolf)a).stopRunning();
				state.character.remove(a);
			}
		}
		while(MapHolder.carrot.size() < 10-GameState.diff) {
			MapHolder.createCarrot();
		}
		while(MapHolder.trap.size() < GameState.level) {
			MapHolder.createTrap();
		}
		if(seconds%10==0 && MapHolder.potionTime!=seconds && !CharacterHolder.inverse) {
			if(MapHolder.potionTime!=0) {
				MapHolder.deletePotion(true);
				
			}
			MapHolder.potionTime = seconds;
			MapHolder.createPotion();
		}
		if(CharacterHolder.timeInverse+15==seconds && CharacterHolder.inverse) {
			CharacterHolder.inverse = false;
			CharacterHolder.timeInverse = 0;
			for(Animal x : CharacterHolder.aniData) {
				x.setInverse(false);
				if(x instanceof Rabbit){
					x.setSpeed(1.4);
				}
				else {
					x.setSpeed(1.1);
				}
			}
		}
		if(MapHolder.item.size() <=2) {
			MapHolder.createItem(RandomGenerator.random(0, 3));
		}
	}
}
