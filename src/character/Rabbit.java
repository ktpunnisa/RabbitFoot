package character;

import java.util.ArrayList;
import java.util.List;

import game.GameLogic;
import game.GameMain;
import game.GameSound;
import game.GameState;
import image.ImageLoader;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import map.JumpBlock;
import map.MapHolder;
import scene.SceneManager;
import ui.UIGame;
import utility.Pair;

public class Rabbit extends Animal {
	public Rabbit instance;
	int id = 0;
	public Rabbit(Pair index, double speed, int direction, int z,boolean inverse) {
		super(index, speed, direction, z,inverse);
		this.instance=this;
		Platform.runLater(() -> body.setImage(ImageLoader.Rimg.get(0)));
		Platform.runLater(() -> body.setTranslateX(SceneManager.SCENE_WIDTH/2-ImageLoader.RABBIT_SIZE/2));
		Platform.runLater(() -> body.setTranslateY(SceneManager.SCENE_HEIGHT/2-ImageLoader.RABBIT_SIZE/2));
		runPath.add(nextBlock());
	}

	@Override
	public void startRunning() {
		Point2D t = MapHolder.mapData.get(index.getY()).get(index.getX()).position;
		Point2D nodeInScene = GameMain.gameUI.localToScene(t);
		Platform.runLater(() -> UIGame.globalMap.setTranslateX(SceneManager.SCENE_WIDTH/2 - nodeInScene.getX()));
		Platform.runLater(() -> UIGame.globalMap.setTranslateY(SceneManager.SCENE_HEIGHT/2 - nodeInScene.getY()));
		isRunning = true;
		animationThread = new Thread(this::animateLoop, "Rabbit animating Thread");
		runThread = new Thread(this::runLoop, "Rabbit running Thread");
		animationThread.start();
		runThread.start();
	}

	@Override
	public void stopRunning() {
		isRunning = false;
	}

	@Override
	public void animateLoop() {
		long lastAnimateTime = System.currentTimeMillis();
		long animateTime = (long) (100 * speed);
		int i = 0;
		while (isRunning) {
			long now = System.currentTimeMillis();
			if (now - lastAnimateTime >= animateTime) {
				lastAnimateTime += animateTime;
				int t=i++;
				if(inverse) {
					if((CharacterHolder.timeInverse+15) - GameLogic.seconds <=2) {
						Platform.runLater(() -> body.setImage(ImageLoader.RimgInv2s.get(t%4)));
					}
					else {
						Platform.runLater(() -> body.setImage(ImageLoader.RimgInv.get(t%4)));
					}
				}
				else if(GameState.isImmortal) {
					Platform.runLater(() -> body.setImage(ImageLoader.RimgInvis.get(t%4)));
				}
				else {
					Platform.runLater(() -> body.setImage(ImageLoader.Rimg.get(t%4)));
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void runLoop() {
		while (isRunning) {
			if(!runPath.isEmpty()) {
				Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				Point2D t = MapHolder.mapData.get(runPath.peek().getY()).get(runPath.peek().getX()).position;
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (UIGame.globalMap.translateXProperty(), SceneManager.SCENE_WIDTH/2 - t.getX(), Interpolator.EASE_BOTH)));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (UIGame.globalMap.translateYProperty(), SceneManager.SCENE_HEIGHT/2 - t.getY(), Interpolator.EASE_BOTH)));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (body.rotateProperty(), angle, Interpolator.EASE_BOTH)));
				timeline.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(nextBlock() != null)
							runPath.add(nextBlock());
						else {
							GameSound.playSoundDie();
							GameMain.stopGame();
						}
						if(!runPath.isEmpty() && GameLogic.isGameRunning)
							MapHolder.mapData.get(runPath.peek().getY()).get(runPath.peek().getX()).checkEvent(instance);
					}
				});
				Platform.runLater(() -> timeline.play());
				index = runPath.poll();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public Pair nextBlock() {
		if(MapHolder.mapData.get(index.getY()).get(index.getX()) instanceof JumpBlock) {
			int jumpDi = ((JumpBlock)MapHolder.mapData.get(index.getY()).get(index.getX())).direction;
			if(Math.abs(this.direction - jumpDi)==3) {
				return MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[direction];
			}
			setAngle(jumpDi);
			this.direction = jumpDi;
			return ((JumpBlock)MapHolder.mapData.get(index.getY()).get(index.getX())).jumpTo;
		}
		return MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[direction];
	}
}
