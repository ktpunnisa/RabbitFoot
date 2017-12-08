package ui;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import game.GameMain;
import game.GameState;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import scene.SceneManager;

public class UIGameOver extends Canvas {
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	int selector = 0;
	private static final Font SCORE_FONT = new Font("Monospace", 80);
	private static final Font TITLE_FONT = new Font("Monospace", 40);
	private Thread gameOverAnimation;
	private boolean isGameOverRunning;
	
	public UIGameOver() {
		super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		this.addKeyEventHandler();
		//startGameOver();
	}
	
	private void addKeyEventHandler() {
		//TODO Fill Code
		this.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				if(selector==0){
					GameMain.newGame(GameState.diff);
				}
				else {
					SceneManager.gotoMenu();
				}
				stopGameOver();
			}
			else if(e.getCode() == KeyCode.ESCAPE) {
				stopGameOver();
				Platform.exit();
			}
			else if(e.getCode() == KeyCode.LEFT) {
				selector=(selector-1+2)%2;
			}
			else if(e.getCode() == KeyCode.RIGHT) {
				selector=(selector+1)%2;
			}
		});
	}
	
	public void startGameOver() {
		gameOverAnimation = new Thread(this::animationLoop, "GameOver Animation Thread");
		isGameOverRunning = true;
		gameOverAnimation.start();
	}

	public void stopGameOver() {
		isGameOverRunning = false;
	}
	
	private void animationLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isGameOverRunning) {
			long now = System.nanoTime();
			if (now - lastLoopStartTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;
				
				Platform.runLater(() -> updateAnimation(now));
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateAnimation(long now) {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gc.setFill(Color.DARKGOLDENROD);
		gc.fillRoundRect(SceneManager.SCENE_WIDTH/2-150, SceneManager.SCENE_HEIGHT/4-100, 300, 500, 100, 100);
		gc.setFill(Color.BURLYWOOD);
		gc.fillRoundRect(SceneManager.SCENE_WIDTH/2-140, SceneManager.SCENE_HEIGHT/4-90, 280, 480, 80, 80);
		gc.drawImage(new Image("file:res/UI/logo.png"), SceneManager.SCENE_WIDTH/2-125, SceneManager.SCENE_HEIGHT/4-50,250,100);
		gc.setFill(Color.DARKBLUE);
		gc.setFont(TITLE_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Score", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT/2-50);
		gc.setFont(SCORE_FONT);
		gc.fillText(""+GameState.score, SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT/2+25);
		
		gc.setFill(Color.CRIMSON);
		if(selector==0)
			gc.fillOval(SceneManager.SCENE_WIDTH / 2 - 105, SceneManager.SCENE_HEIGHT / 2 +55, 80, 80);
		else if(selector==1)
			gc.fillOval(SceneManager.SCENE_WIDTH / 2 + 15, SceneManager.SCENE_HEIGHT / 2 +55, 80, 80);		
		gc.setFill(Color.CORAL);
		gc.fillOval(SceneManager.SCENE_WIDTH / 2 - 100, SceneManager.SCENE_HEIGHT / 2 +60, 70, 70);
		gc.fillOval(SceneManager.SCENE_WIDTH / 2 + 20, SceneManager.SCENE_HEIGHT / 2 +60, 70, 70);
		gc.drawImage(new Image("file:res/UI/restart.png"), SceneManager.SCENE_WIDTH / 2 - 90,SceneManager.SCENE_HEIGHT / 2 +70,50,50);
		gc.drawImage(new Image("file:res/UI/menu.png"), SceneManager.SCENE_WIDTH / 2 +30,SceneManager.SCENE_HEIGHT / 2 +70,50,50);
	}
}
