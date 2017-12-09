package ui;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import game.GameMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import scene.SceneManager;

public class UIMenu extends Canvas {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	int diffSelector = 0;
	private static final Font TITLE_FONT = new Font("Monospace", 80);
	private static final Font MENU_FONT = new Font("Monospace", 40);
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
		gc.setFill(Color.ORANGE);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gc.setFill(Color.RED);
		if(diffSelector==0)
			gc.fillRect(SceneManager.SCENE_WIDTH / 5-85, SceneManager.SCENE_HEIGHT * 3 / 4 - 40, 170, 70);
		else if(diffSelector==1)
			gc.fillRect(SceneManager.SCENE_WIDTH / 2-85, SceneManager.SCENE_HEIGHT * 3 / 4 - 40, 170, 70);
		else
			gc.fillRect(SceneManager.SCENE_WIDTH*4 / 5-85, SceneManager.SCENE_HEIGHT * 3 / 4 - 40, 170, 70);
		gc.setFill(Color.WHITE);
		gc.drawImage(new Image("file:res/UI/logo.png"), SceneManager.SCENE_WIDTH / 2 - 250, SceneManager.SCENE_HEIGHT / 4 - 150);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(MENU_FONT);
		gc.fillText("Easy", SceneManager.SCENE_WIDTH / 5, SceneManager.SCENE_HEIGHT * 3 / 4);
		gc.fillText("Normal", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT * 3 / 4);
		gc.fillText("Hard", SceneManager.SCENE_WIDTH * 4 / 5, SceneManager.SCENE_HEIGHT * 3 / 4);
	}
}
