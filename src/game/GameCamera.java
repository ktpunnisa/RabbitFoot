package game;

import application.Main;
import character.CharacterHolder;
import javafx.application.Platform;
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
            		Node rabbitBody = CharacterHolder.aniData.get(0).body;
				gameUI.setTranslateX(gameUI.getScene().getWidth()/2 - rabbitBody.getTranslateX());
				gameUI.setTranslateY(gameUI.getScene().getHeight()/2 - rabbitBody.getTranslateY());
            }
        });
	}
	
	public void rotateMap(int val)
	{
		return;
		/*Rotate r = new Rotate();
		r.setAngle(120*val);
		r.setPivotX(CharacterHolder.aniData.get(0).body.getTranslateX());
		r.setPivotY(CharacterHolder.aniData.get(0).body.getTranslateY());
		UIGame.globalMap.getTransforms().add(r);*/
	}
}
