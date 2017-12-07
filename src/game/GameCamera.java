
package game;

import application.Main;
import character.CharacterHolder;
import character.Rabbit;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import map.MapHolder;
import scene.SceneManager;
import ui.UIGame;
import utility.Pair;

import java.lang.Math;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameCamera {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	
	public UIGame gameUI;
	public static Queue<Pair> cameraQueue;
	public Thread gameCamera;
	public boolean isTracking;
	
	public GameCamera(UIGame gameUI)
	{
		this.gameUI = gameUI;
		this.isTracking = false;
		cameraQueue = new LinkedBlockingQueue<Pair>();
		Point2D t = MapHolder.mapData.get(CharacterHolder.aniData.get(0).index.getY()).get(CharacterHolder.aniData.get(0).index.getX()).position;
		Point2D nodeInScene = gameUI.localToScene(t);
		UIGame.globalMap.setTranslateX(SceneManager.SCENE_WIDTH/2 - nodeInScene.getX());
		UIGame.globalMap.setTranslateY(SceneManager.SCENE_HEIGHT/2 - nodeInScene.getY());
	}
	public void startTrack()
	{
		this.isTracking = true;
		gameCamera = new Thread(this::tracking, "Game Camera Thread");
		//gameCamera.start();
	}
	public void stopTrack()
	{
		this.isTracking = false;
	}
	public void tracking()
	{
		while (isTracking) {
			if(!cameraQueue.isEmpty()) {
				Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				Point2D t = MapHolder.mapData.get(cameraQueue.peek().getY()).get(cameraQueue.peek().getX()).position;
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * CharacterHolder.aniData.get(0).speed), 
						new KeyValue (UIGame.globalMap.translateXProperty(), SceneManager.SCENE_WIDTH/2 - t.getX())));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * CharacterHolder.aniData.get(0).speed), 
						new KeyValue (UIGame.globalMap.translateYProperty(), SceneManager.SCENE_HEIGHT/2 - t.getY())));
				Platform.runLater(() -> timeline.play());
				cameraQueue.poll();
				System.out.println("play");
			}
			try {
				Thread.sleep(1);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
