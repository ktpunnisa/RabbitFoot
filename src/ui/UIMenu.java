package ui;

import game.GameMain;
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

public class UIMenu extends Canvas {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	int diffSelector = 0;
	private static final Font MENU_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/8bit.ttf"), 60);
	private Thread menuAnimation;
	private boolean isMenuRunning;
	
	public UIMenu() {
		super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		diffSelector = 0;
		this.addKeyEventHandler();
	}
	
	private void addKeyEventHandler() {
		//TODO Fill Code
		this.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				GameMain.newGame(diffSelector%3+1);
				stopMenu();
			}
			else if(e.getCode() == KeyCode.ESCAPE) {
				stopMenu();
				Platform.exit();
				System.exit(0);
			}
			else if(e.getCode() == KeyCode.LEFT) {
				diffSelector=(diffSelector-1+3)%3;
			}
			else if(e.getCode() == KeyCode.RIGHT) {
				diffSelector=(diffSelector+1)%3;
			}
		});
	}
	
	public void startMenu() {
		menuAnimation = new Thread(this::animationLoop, "Menu Animation Thread");
		isMenuRunning = true;
		menuAnimation.start();
	}

	public void stopMenu() {
		isMenuRunning = false;
	}
	
	private void animationLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isMenuRunning) {
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
		gc.setFill(Color.valueOf("dbfff9"));
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		
		gc.setFill(Color.valueOf("003b64"));
		gc.setStroke(Color.valueOf("007cd2"));
		gc.setLineWidth(20);
		gc.fillRoundRect(25, 50, 750, 500, 60, 60);
		gc.strokeRoundRect(25, 50, 750, 500, 60, 60);
		gc.setFill(Color.RED);
		if(diffSelector==0)
			gc.fillRoundRect(SceneManager.SCENE_WIDTH *3 / 16-105, SceneManager.SCENE_HEIGHT * 3 / 4-35, 200, 70, 40,40);
		else if(diffSelector==1)
			gc.fillRoundRect(SceneManager.SCENE_WIDTH / 2-105, SceneManager.SCENE_HEIGHT * 3 / 4-35, 200, 70, 40,40);
		else
			gc.fillRoundRect(SceneManager.SCENE_WIDTH*13 / 16-105, SceneManager.SCENE_HEIGHT * 3 / 4-35, 200, 70, 40,40);
		gc.setFill(Color.WHITE);
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("ui/logo.png")), SceneManager.SCENE_WIDTH / 2 - 300, SceneManager.SCENE_HEIGHT / 4 - 100);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(MENU_FONT);
		gc.fillText("Easy", SceneManager.SCENE_WIDTH * 3 / 16, SceneManager.SCENE_HEIGHT * 3 / 4+18);
		gc.fillText("Normal", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT * 3 / 4+18);
		gc.fillText("Hard", SceneManager.SCENE_WIDTH * 13 / 16, SceneManager.SCENE_HEIGHT * 3 / 4+18);
	}
}
