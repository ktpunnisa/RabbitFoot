package game;

import character.Animal;
import character.CharacterHolder;
import character.Rabbit;
import character.Wolf;
import javafx.application.Platform;
import map.MapHolder;
import ui.UIBar;
import utility.RandomGenerator;

public class GameLogic 
{	
	private GameState state;
	private static boolean isGameRunning;
	private static long seconds;
	
	public GameLogic(GameState state)
	{
		this.state = state;
		isGameRunning = false;
	}
	
	public void startGame() 
	{
		isGameRunning = true;
		new Thread(this::gameLoop, "Game Loop Thread").start();
		for(Animal a : CharacterHolder.getAniData())
			a.startRunning();
	}

	public void stopGame() 
	{
		isGameRunning = false;
		for(Animal a:CharacterHolder.getAniData())
			a.stopRunning();
	}
	
	private void gameLoop() 
	{
		long startTime = System.nanoTime();
		while (isGameRunning) 
		{
			seconds = (long)((System.nanoTime() - startTime) / 1000000000.0);
			updateGame();
			try 
			{
				Thread.sleep(1);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private void updateGame()
	{
		Platform.runLater(()->UIBar.setScore("Score: " + GameState.getScore()));
		
		if(CharacterHolder.getAniData().size() < GameState.getDiff()+1) 
		{
			state.getCharacter().add(GameState.getDiff()+1-CharacterHolder.getAniData().size());
		}
		if(CharacterHolder.getAniData().size() > 1) 
		{
			for(Animal a : CharacterHolder.getAniData().subList(1, CharacterHolder.getAniData().size())) 
			{
				if(a.getIndex().equals(CharacterHolder.getAniData().get(0).getIndex())) 
				{
					if(CharacterHolder.getAniData().get(0).isInverse()) 
					{
						GameSound.playSoundWolf();
						state.getCharacter().remove(a);
						GameState.setScore(GameState.getScore()+10);
					}
					else if(!((Wolf)a).isStun() && !GameState.isInvis()) 
					{
						GameSound.playSoundWolf();
						GameMain.stopGame();
					}
				}
			}
		}
		while(MapHolder.getCarrot().size() < 10-GameState.getDiff()) 
		{
			MapHolder.createCarrot();
		}
		while(MapHolder.getTrap().size() < GameState.getDiff()) 
		{
			MapHolder.createTrap();
		}
		if(seconds%10==0 && MapHolder.getPotionTime()!=seconds && !GameState.isInverse()) 
		{
			if(MapHolder.getPotionTime() != 0) 
			{
				MapHolder.deletePotion(true);
			}
			MapHolder.setPotionTime(seconds);
			MapHolder.createPotion();
		}
		if(GameState.getTimeInverse()+15==seconds && GameState.isInverse()) 
		{
			GameState.setInverse(false);
			GameState.setTimeInverse(0);
			for(Animal x : CharacterHolder.getAniData()) 
			{
				x.setInverse(false);
				if(x instanceof Rabbit)
				{
					x.setSpeed(1.4);
				}
				else 
				{
					x.setSpeed(1.1);
				}
			}
		}
		if(MapHolder.getItem().size() <= 2) 
		{
			MapHolder.createItem(RandomGenerator.random(0, 3));
		}
	}

	public static long getSeconds() {
		return seconds;
	}

	public static void setSeconds(long seconds) {
		GameLogic.seconds = seconds;
	}

	public static boolean isGameRunning() {
		return isGameRunning;
	}

	public static void setGameRunning(boolean isGameRunning) {
		GameLogic.isGameRunning = isGameRunning;
	}
	
	
}
