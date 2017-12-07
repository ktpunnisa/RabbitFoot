
package game;

import application.Main;
import character.CharacterHolder;
import character.Rabbit;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	public static boolean isTrackDone;
	private Timeline timeline;
	public GameCamera(UIGame gameUI)
	{
		this.gameUI = gameUI;
		this.isTracking = false;
		isTrackDone = true;
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
			if(!cameraQueue.isEmpty() && isTrackDone) {
				System.out.println("start track");
				isTrackDone = false;
				timeline = new Timeline();
				timeline.setCycleCount(1);
				Point2D t = MapHolder.mapData.get(cameraQueue.peek().getY()).get(cameraQueue.peek().getX()).position;
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * CharacterHolder.aniData.get(0).speed), 
						new KeyValue (UIGame.globalMap.translateXProperty(), SceneManager.SCENE_WIDTH/2 - t.getX())));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * CharacterHolder.aniData.get(0).speed), 
						new KeyValue (UIGame.globalMap.translateYProperty(), SceneManager.SCENE_HEIGHT/2 - t.getY())));
				timeline.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							CharacterHolder.aniData.get(0).runThread.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				Platform.runLater(() -> timeline.play());
				cameraQueue.clear();
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
