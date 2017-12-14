package character;

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
import javafx.util.Duration;
import map.JumpBlock;
import map.MapHolder;
import scene.SceneManager;
import utility.Pair;

public class Rabbit extends Animal {

	private Rabbit instance;

	public Rabbit(Pair index, double speed, int direction, boolean inverse) {
		super(index, speed, direction, inverse);
		this.instance = this;
		Platform.runLater(() -> body.setImage(ImageLoader.getRimg().get(0)));
		Platform.runLater(() -> body.setTranslateX(SceneManager.SCENE_WIDTH / 2 - ImageLoader.RABBIT_SIZE / 2));
		Platform.runLater(() -> body.setTranslateY(SceneManager.SCENE_HEIGHT / 2 - ImageLoader.RABBIT_SIZE / 2));
		Platform.runLater(() -> body.setRotate(angle));
		runPath.add(nextBlock());
	}

	@Override
	public void startRunning() {
		Point2D t = MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()).getPosition();
		Point2D nodeInScene = GameMain.getGameUI().localToScene(t);
		Platform.runLater(
				() -> MapHolder.getMapGroup().setTranslateX(SceneManager.SCENE_WIDTH / 2 - nodeInScene.getX()));
		Platform.runLater(
				() -> MapHolder.getMapGroup().setTranslateY(SceneManager.SCENE_HEIGHT / 2 - nodeInScene.getY()));
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
	protected void animateLoop() {
		long lastAnimateTime = System.currentTimeMillis();
		long animateTime = (long) (100 * getSpeed());
		int i = 0;
		while (isRunning) {
			long now = System.currentTimeMillis();
			if (now - lastAnimateTime >= animateTime) {
				lastAnimateTime += animateTime;
				int t = i++;
				if (isInverse()) {
					if ((GameState.getTimeInverse() + 15) - GameLogic.getSeconds() <= 2) {
						Platform.runLater(() -> body.setImage(ImageLoader.getRimgInv2s().get(t % 4)));
					} else {
						Platform.runLater(() -> body.setImage(ImageLoader.getRimgInv().get(t % 4)));
					}
				} else if (GameState.isInvis()) {
					Platform.runLater(() -> body.setImage(ImageLoader.getRimgInvis().get(t % 4)));
				} else {
					Platform.runLater(() -> body.setImage(ImageLoader.getRimg().get(t % 4)));
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
	protected void runLoop() {
		while (isRunning) {
			if (!runPath.isEmpty()) {
				Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				Point2D t = MapHolder.getMapData().get(runPath.peek().getY()).get(runPath.peek().getX()).getPosition();
				timeline.getKeyFrames()
						.add(new KeyFrame(Duration.millis(500 * getSpeed()),
								new KeyValue(MapHolder.getMapGroup().translateXProperty(),
										SceneManager.SCENE_WIDTH / 2 - t.getX(), Interpolator.EASE_BOTH)));
				timeline.getKeyFrames()
						.add(new KeyFrame(Duration.millis(500 * getSpeed()),
								new KeyValue(MapHolder.getMapGroup().translateYProperty(),
										SceneManager.SCENE_HEIGHT / 2 - t.getY(), Interpolator.EASE_BOTH)));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * getSpeed()),
						new KeyValue(body.rotateProperty(), angle, Interpolator.EASE_BOTH)));
				timeline.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if (nextBlock() != null) {
							runPath.add(nextBlock());
						} else {
							GameSound.playSoundDie();
							GameMain.stopGame();
						}
						if (!runPath.isEmpty() && GameLogic.isGameRunning()) {
							MapHolder.getMapData().get(runPath.peek().getY()).get(runPath.peek().getX())
									.checkEvent(instance);
						}
					}
				});
				Platform.runLater(() -> timeline.play());
				setIndex(runPath.poll());
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected Pair nextBlock() {
		if (MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()) instanceof JumpBlock) {
			int jumpDi = ((JumpBlock) MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()))
					.getDirection();
			if (Math.abs(getDirection() - jumpDi) == 3) {
				return MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()).getNextBlock()[getDirection()];
			}
			setAngle(jumpDi);
			setDirection(jumpDi);
			return ((JumpBlock) MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX())).getJumpTo();
		}
		return MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()).getNextBlock()[getDirection()];
	}
}
