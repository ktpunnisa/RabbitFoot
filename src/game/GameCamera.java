package game;

import application.Main;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import java.lang.Math;

public class GameCamera {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	
	public Group view;
	public Thread gameCamera;
	public static boolean isTracking;
	
	public GameCamera(Group view)
	{
		this.view = view;
		this.isTracking = false;
		//temp
		//view.setScaleX(3);
		//view.setScaleY(3);
	}
	public void startTrack()
	{
		gameCamera = new Thread(this::tracking, "Game Camera Thread");
		//gameCamera.start();
		//this.isTracking = true;
		Node Rabbit = view.getChildren().get(0);
		Bounds boundsInScene = Rabbit.localToScreen(Rabbit.getBoundsInLocal());
		//view.setTranslateX(view.getTranslateX()+1);
		view.setTranslateX(100);
		view.setTranslateY(100);
	}
	public void stopTrack()
	{
		this.isTracking = false;
	}
	public void tracking()
	{
		Bounds boundsInScene;
		Node Rabbit = view.getChildren().get(0);
		long lastLoopStartTime = System.nanoTime();
		while (isTracking) {
			long now = System.nanoTime();
			if (now - lastLoopStartTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;
				
				boundsInScene = Rabbit.localToScene(Rabbit.getBoundsInLocal());
				//view.setTranslateX(view.getTranslateX()+1);
				view.setTranslateX(boundsInScene.getMaxX());
				view.setTranslateY(boundsInScene.getMaxY());
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
