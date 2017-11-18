package game;

import application.Main;
import character.CharacterHolder;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import ui.UIGame;

import java.lang.Math;

public class GameCamera {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	
	public UIGame gameUI;
	public Thread gameCamera;
	public boolean isTracking;
	
	public GameCamera(UIGame gameUI)
	{
		this.gameUI = gameUI;
		this.isTracking = false;
	}
	public void startTrack()
	{
		gameCamera = new Thread(this::tracking, "Game Camera Thread");
		gameCamera.start();
		this.isTracking = true;
	}
	public void stopTrack()
	{
		this.isTracking = false;
	}
	public void tracking()
	{
		Bounds boundsInScene;
		
		long lastLoopStartTime = System.nanoTime();
		while (isTracking) {
			long now = System.nanoTime();
			if (now - lastLoopStartTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;
				
				Node rabbitBody = CharacterHolder.aniData.get(0).body;
				//boundsInScene = rabbitBody.localToScene(rabbitBody.getBoundsInLocal());
				gameUI.setTranslateX(gameUI.getScene().getWidth()/2 - rabbitBody.getTranslateX());
				gameUI.setTranslateY(gameUI.getScene().getHeight()/2 - rabbitBody.getTranslateY());
				
				//Main.gameDisplay.setRotate(45 * CharacterHolder.aniData.get(0).direction - 45);
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
