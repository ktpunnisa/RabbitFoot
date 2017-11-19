package game;

import application.Main;
import character.CharacterHolder;
import character.Rabbit;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import map.MapHolder;
import ui.UIGame;

import java.lang.Math;

public class GameCamera {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	
	public UIGame gameUI;
	public Thread gameCamera;
	public boolean isTracking;
	public int degree;
	public double pivotx;
	public double pivoty;
	
	public GameCamera(UIGame gameUI)
	{
		this.gameUI = gameUI;
		this.isTracking = false;
		
		//looking for rabbit di and set*** 
		Rotate r = new Rotate();
		r.setAngle(-30);
		pivotx = MapHolder.mapData.get(CharacterHolder.aniData.get(0).index.getY()).get(CharacterHolder.aniData.get(0).index.getX()).position.getX();
		r.setPivotX(MapHolder.mapData.get(CharacterHolder.aniData.get(0).index.getY()).get(CharacterHolder.aniData.get(0).index.getX()).position.getX());
		pivoty = MapHolder.mapData.get(CharacterHolder.aniData.get(0).index.getY()).get(CharacterHolder.aniData.get(0).index.getX()).position.getY();
		r.setPivotY(MapHolder.mapData.get(CharacterHolder.aniData.get(0).index.getY()).get(CharacterHolder.aniData.get(0).index.getX()).position.getY());
		gameUI.getTransforms().add(r);
		this.degree = -30;
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
            		double x = Math.abs(rabbitBody.getTranslateX() - pivotx);
            		double y = Math.abs(rabbitBody.getTranslateY() - pivoty);
            		double xx = x * Math.cos(Math.toRadians(degree)) + y * Math.sin(Math.toRadians(degree)) + pivotx;
            		double yy = x * Math.sin(Math.toRadians(degree)) - y * Math.cos(Math.toRadians(degree)) + pivoty;
            		Platform.runLater(() -> gameUI.setTranslateX(gameUI.getScene().getWidth()/2 - xx));
            		Platform.runLater(() -> gameUI.setTranslateY(gameUI.getScene().getHeight()/2 - yy));
            }
        });
	}
	
	public void rotateMap(int val)
	{
		Node Rabbits = CharacterHolder.aniData.get(0).body;
		Rotate r = new Rotate();
		r.setAngle(60*val);
		degree+=60*val;
		r.setPivotX(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getX());
		pivotx = MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getX();
		r.setPivotY(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getY());
		pivoty = MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getY();
		gameUI.getTransforms().add(r);
		Rectangle a = new Rectangle(10,10);
		a.setTranslateX(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getX()-5);
		a.setTranslateY(MapHolder.mapData.get(Rabbit.nextIndex.getY()).get(Rabbit.nextIndex.getX()).position.getY()-5);
		UIGame.globalMap.getChildren().add(a);
		System.out.println(Rabbits.getTranslateX()+ Rabbit.RABBIT_SIZE/2 + " " +Rabbits.getTranslateY()+ Rabbit.RABBIT_SIZE/2);
	}
}
