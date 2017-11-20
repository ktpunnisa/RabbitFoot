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
	public int prevDirection;
	public int prevDegree;
	
	public GameCamera(UIGame gameUI)
	{
		this.gameUI = gameUI;
		this.isTracking = false;
		this.prevDirection = CharacterHolder.aniData.get(0).direction;
		this.prevDegree = 0;
		Rotate r = new Rotate();
		r.setAngle(-30);
		r.setPivotX(MapHolder.mapData.get(CharacterHolder.aniData.get(0).index.getY()).get(CharacterHolder.aniData.get(0).index.getX()).position.getX());
		r.setPivotY(MapHolder.mapData.get(CharacterHolder.aniData.get(0).index.getY()).get(CharacterHolder.aniData.get(0).index.getX()).position.getY());
		Platform.runLater(() -> gameUI.getTransforms().add(r));
		Platform.runLater(() -> CharacterHolder.aniData.get(0).body.getTransforms().add(new Rotate(30,Rabbit.RABBIT_SIZE/2,Rabbit.RABBIT_SIZE/2)));
		
		//RotateTransition rabbitRotation = new RotateTransition(Duration.millis(1000*CharacterHolder.aniData.get(0).speed),CharacterHolder.aniData.get(0).body);
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
            		Bounds test = rabbitBody.localToScene(rabbitBody.getBoundsInLocal());
            		double x = gameUI.getScene().getWidth()/2-(test.getMinX()+test.getMaxX())/2;
            		double y = gameUI.getScene().getHeight()/2-(test.getMinY()+test.getMaxY())/2;
            		Platform.runLater(() -> gameUI.setTranslateX(gameUI.getTranslateX() + x));
            		Platform.runLater(() -> gameUI.setTranslateY(gameUI.getTranslateY() + y));
            		/*Rabbit rabbit = (Rabbit) CharacterHolder.aniData.get(0);
            		if(prevDirection != rabbit.direction) {
            			RotateTransition rabbitRotation = new RotateTransition(Duration.millis(1000*rabbit.speed),rabbit.body);
	            		int degree = (60*(rabbit.direction+5))%360;
	            		if(prevDegree==0 && degree==300) degree = -60;
	            		rabbitRotation.setToAngle(degree);
	            		rabbitRotation.setCycleCount(1);
	                 rabbitRotation.play();
	                 
	                Rotate r = new Rotate();
	         		r.setAngle(60*(prevDirection-rabbit.direction));
	         		r.setPivotX(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getX());
	         		r.setPivotY(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getY());
	         		gameUI.getTransforms().add(r);
	                prevDirection = rabbit.direction;
	                prevDegree = (degree+360)%360;
            		}*/
            }
        });
	}
	
	public void rotateMap(int val)
	{   
		Rabbit rabbit = (Rabbit) CharacterHolder.aniData.get(0);
		RotateTransition rabbitRotation = new RotateTransition(Duration.millis(1000*rabbit.speed),rabbit.body);
		int degree = (60*(rabbit.direction+5))%360;
		rabbitRotation.setToAngle(degree);
		rabbitRotation.setCycleCount(1);
		rabbitRotation.play();
		prevDegree = (degree+360)%360;
		Rotate r = new Rotate();
		r.setAngle(60*val);
		r.setPivotX(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getX());
		r.setPivotY(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getY());
		gameUI.getTransforms().add(r);
		/*Rectangle a = new Rectangle(10,10);
		a.setTranslateX(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getX()-5);
		a.setTranslateY(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getY()-5);
		UIGame.globalMap.getChildren().add(a);*/
		/*Rotate r = new Rotate();
		//r.setAngle(60*val);
		r.setPivotX(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getX());
		r.setPivotY(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getY());
		gameUI.getTransforms().add(r);
		 Timeline timeline = new Timeline(
	                new KeyFrame(Duration.ZERO, new KeyValue(r.angleProperty(), 0)),
	                new KeyFrame(Duration.seconds(1), new KeyValue(r.angleProperty(), 60*val)));
		 Platform.runLater(() -> timeline.play());*/
	}
}
