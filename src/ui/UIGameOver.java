package ui;

import game.GameMain;
import game.GameSound;
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
	private int selector;
	private Font SCORE_FONT;
	private Font TITLE_FONT;
	private Font GAMEOVER_FONT;
	private Thread gameOverAnimation;
	private boolean isGameOverRunning;
	private int finalScore;

	public UIGameOver() {
		super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		SCORE_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/8bit.ttf"), 150);
		TITLE_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/8bit.ttf"), 30);
		GAMEOVER_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/8bit.ttf"), 50);
		this.selector = 0;
		this.finalScore = 0;
		this.addKeyEventHandler();
	}

	private void addKeyEventHandler() {
		this.setOnKeyPressed((KeyEvent e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (selector == 0) {
					GameMain.newGame(GameState.getDiff());
				} else {
					SceneManager.gotoMenu();
				}
				stopGameOver();
			} else if (e.getCode() == KeyCode.ESCAPE) {
				stopGameOver();
				Platform.exit();
			} else if (e.getCode() == KeyCode.LEFT) {
				GameSound.playSoundEat();
				selector = (selector - 1 + 2) % 2;
			} else if (e.getCode() == KeyCode.RIGHT) {
				GameSound.playSoundEat();
				selector = (selector + 1) % 2;
			}
		});
	}

	public void startGameOver() {
		gameOverAnimation = new Thread(this::animationLoop, "GameOver Animation Thread");
		isGameOverRunning = true;
		gameOverAnimation.start();
		this.finalScore = GameState.getScore();
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
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gc.setFill(Color.ORANGERED);
		gc.fillRoundRect(SceneManager.SCENE_WIDTH / 2 - 150, SceneManager.SCENE_HEIGHT / 4 - 100, 300, 500, 100, 100);
		gc.setFill(Color.ORANGE);
		gc.fillRoundRect(SceneManager.SCENE_WIDTH / 2 - 140, SceneManager.SCENE_HEIGHT / 4 - 90, 280, 480, 80, 80);
		gc.setFont(GAMEOVER_FONT);
		gc.setFill(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("GAME OVER", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT / 2 - 150);
		gc.setFont(TITLE_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Score", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT / 2 - 80);
		gc.setFont(SCORE_FONT);
		gc.fillText("" + finalScore, SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT / 2 + 30);

		gc.setFill(Color.RED);
		if (selector == 0)
			gc.fillOval(SceneManager.SCENE_WIDTH / 2 - 105, SceneManager.SCENE_HEIGHT / 2 + 65, 80, 80);
		else if (selector == 1)
			gc.fillOval(SceneManager.SCENE_WIDTH / 2 + 15, SceneManager.SCENE_HEIGHT / 2 + 65, 80, 80);
		gc.setFill(Color.ORANGERED);
		gc.fillOval(SceneManager.SCENE_WIDTH / 2 - 100, SceneManager.SCENE_HEIGHT / 2 + 70, 70, 70);
		gc.fillOval(SceneManager.SCENE_WIDTH / 2 + 20, SceneManager.SCENE_HEIGHT / 2 + 70, 70, 70);
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("ui/restart.png")),
				SceneManager.SCENE_WIDTH / 2 - 90, SceneManager.SCENE_HEIGHT / 2 + 80, 50, 50);
		gc.drawImage(new Image(ClassLoader.getSystemResourceAsStream("ui/menu.png")), SceneManager.SCENE_WIDTH / 2 + 30,
				SceneManager.SCENE_HEIGHT / 2 + 80, 50, 50);
	}
}
