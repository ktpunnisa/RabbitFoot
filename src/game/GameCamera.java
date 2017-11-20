package game;

import application.Main;
import character.CharacterHolder;
import character.Rabbit;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import map.MapHolder;
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
		this.isTracking = true;
		gameCamera = new Thread(this::tracking, "Game Camera Thread");
		gameCamera.start();
	}
	public void stopTrack()
	{
		this.isTracking = false;
	}
	public void tracking()
	{	
		long lastLoopStartTime = System.nanoTime();
		while (isTracking) {
			long now = System.nanoTime();
			if (now - lastLoopStartTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;
				updateMap();
			}
			
			try {
				Thread.sleep(1);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateMap()
	{
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            		//System.out.println(CharacterHolder.aniData.size());
            		Node rabbitBody = CharacterHolder.aniData.get(0).body;
				gameUI.setTranslateX(gameUI.getScene().getWidth()/2 - rabbitBody.getTranslateX());
				gameUI.setTranslateY(gameUI.getScene().getHeight()/2 - rabbitBody.getTranslateY());
            }
        });
	}
}
