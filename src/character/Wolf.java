package character;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import game.GameLogic;
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
import map.NormalBlock;
import map.TrapBlock;
import utility.MyNode;
import utility.Pair;
import utility.RandomGenerator;

public class Wolf extends Animal {

	public Wolf instance;
	private boolean isStun;
	private Pair gotoThis;

	public Wolf(Pair index, double speed, int direction, boolean inverse) {
		super(index, speed, direction, inverse);
		this.instance = this;
		this.isStun = false;
		gotoThis = RandomGenerator.randomIndex();
		runPath.add(nextBlock());
	}

	@Override
	public void startRunning() {
		if (!GameLogic.isGameRunning())
			return;
		Point2D a = MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()).getPosition();
		Platform.runLater(() -> body.setImage(ImageLoader.getWimg().get(0)));
		Platform.runLater(() -> body.setTranslateX(a.getX() - ImageLoader.WOLF_SIZE / 2));
		Platform.runLater(() -> body.setTranslateY(a.getY() - ImageLoader.WOLF_SIZE / 2));
		isRunning = true;
		animationThread = new Thread(this::animateLoop, "Wolf animating Thread");
		runThread = new Thread(this::runLoop, "Wolf running Thread");
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
						Platform.runLater(() -> body.setImage(ImageLoader.getWimgInv2s().get(t % 4)));
					} else {
						Platform.runLater(() -> body.setImage(ImageLoader.getWimgInv().get(t % 4)));
					}
				} else if (isStun) {
					Platform.runLater(() -> body.setImage(ImageLoader.getWimgStun().get(t % 2)));
				} else {
					Platform.runLater(() -> body.setImage(ImageLoader.getWimg().get(t % 4)));
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
				int i = 0;
				for (i = 0; i < 6; ++i) {
					if (MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()).getNextBlock()[i] != null) {
						if (MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()).getNextBlock()[i].equals(
								MapHolder.getMapData().get(runPath.peek().getY()).get(runPath.peek().getX()).getIndex())) {
							break;
						}
					}
				}
				if (i == 6) {
					i = getDirection();
				}
				setAngle(i);
				setDirection(i);
				timeline.setCycleCount(1);
				Point2D a = MapHolder.getMapData().get(runPath.peek().getY()).get(runPath.peek().getX()).getPosition();
				timeline.getKeyFrames()
						.add(new KeyFrame(Duration.millis(500 * getSpeed()), new KeyValue(body.translateXProperty(),
								a.getX() - ImageLoader.WOLF_SIZE / 2, Interpolator.EASE_BOTH)));
				timeline.getKeyFrames()
						.add(new KeyFrame(Duration.millis(500 * getSpeed()), new KeyValue(body.translateYProperty(),
								a.getY() - ImageLoader.WOLF_SIZE / 2, Interpolator.EASE_BOTH)));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * getSpeed()),
						new KeyValue(body.rotateProperty(), angle, Interpolator.EASE_BOTH)));
				timeline.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if (nextBlock() != null)
							runPath.add(nextBlock());
						if (!runPath.isEmpty())
							MapHolder.getMapData().get(runPath.peek().getY()).get(runPath.peek().getX())
									.checkEvent(instance);
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

		Pair r = CharacterHolder.getAniData().get(0).getIndex();
		Pair bestBlock = null;
		Boolean seeTrap = true;
		if (GameState.isInvis()) {
			r = gotoThis;
			while (r.distance(getIndex()) == 0) {
				gotoThis = RandomGenerator.randomIndex();
				r = gotoThis;
			}
		}
		if (isStun) {
			return getIndex();
		}
		if (MapHolder.getMapData().get(r.getY()).get(r.getX()) instanceof JumpBlock
				|| MapHolder.getMapData().get(r.getY()).get(r.getX()) instanceof TrapBlock) {
			return bestBlock = lowNextBlock();
		}
		PriorityQueue<MyNode> q = new PriorityQueue<MyNode>();
		Set<Pair> mark = new HashSet<Pair>();
		Map<Pair, MyNode> ans = new HashMap<>();
		q.add(new MyNode(getIndex(), getIndex().distance(r)));
		ans.put(getIndex(), new MyNode(getIndex(), 0));
		while (!q.isEmpty()) {
			MyNode w = q.poll();
			if (mark.contains(w.getIndex())) {
				continue;
			}
			mark.add(w.getIndex());
			if (w.getIndex().equals(r)) {
				break;
			}
			for (int i = 0; i < 6; i++) {
				Pair nextW = MapHolder.getMapData().get(w.getIndex().getY()).get(w.getIndex().getX()).getNextBlock()[i];
				if (nextW != null && !mark.contains(nextW)) {
					if (MapHolder.getMapData().get(nextW.getY()).get(nextW.getX()) instanceof NormalBlock
							|| (MapHolder.getMapData().get(nextW.getY()).get(nextW.getX()) instanceof TrapBlock
									&& !seeTrap)) {
						int dis = nextW.distance(r);
						if (ans.containsKey(nextW) && (ans.get(w.getIndex()).getDis() + 1 < ans.get(nextW).getDis())) {
							ans.replace(nextW, new MyNode(w.getIndex(), ans.get(w.getIndex()).getDis() + 1));
							q.add(new MyNode(nextW, dis));
						}
						if (!ans.containsKey(nextW)) {
							ans.put(nextW, new MyNode(w.getIndex(), ans.get(w.getIndex()).getDis() + 1));
							q.add(new MyNode(nextW, dis));
						}
					}
				}
			}
		}
		try {
			Pair tmp = r;
			while (ans.get(tmp).getIndex() != getIndex()) {
				tmp = ans.get(tmp).getIndex();
			}
			bestBlock = tmp;
		} catch (Exception e) {
			e.printStackTrace();
			lowNextBlock();
		}

		if (isInverse()) {
			Pair tmp = bestBlock;
			for (int i = 0; i < 6; i++) {
				Pair nextBlock = MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()).getNextBlock()[i];
				if (nextBlock == null) {
					continue;
				}
				if (MapHolder.typeBlock[nextBlock.getY()][nextBlock.getX()] != 3) {
					continue;
				}
				if (MapHolder.getMapData().get(nextBlock.getY()).get(nextBlock.getX()) instanceof NormalBlock
						|| (MapHolder.getMapData().get(nextBlock.getY()).get(nextBlock.getX()) instanceof TrapBlock
								&& !seeTrap)) {
					if (nextBlock.distance(bestBlock) > tmp.distance(bestBlock)) {
						tmp = nextBlock;
					} else if (nextBlock.distance(bestBlock) == tmp.distance(bestBlock)
							&& nextBlock.distance(r) > tmp.distance(r)) {
						tmp = nextBlock;
					}
				}
			}
			bestBlock = tmp;
		}
		return bestBlock;
	}

	private Pair lowNextBlock() {
		Pair r = CharacterHolder.getAniData().get(0).getIndex();
		Pair bestBlock = null;
		int min = 100;
		for (int i = 0; i < 6; i++) {
			Pair nextW = MapHolder.getMapData().get(getIndex().getY()).get(getIndex().getX()).getNextBlock()[i];
			if (nextW != null) {
				if (MapHolder.getMapData().get(nextW.getY()).get(nextW.getX()) instanceof NormalBlock) {
					int dis = nextW.distance(r);
					if (dis < min) {
						bestBlock = nextW;
						min = dis;
					}
				}
			}
		}
		return bestBlock;
	}

	public boolean isStun() {
		return isStun;
	}

	public void setStun(boolean isStun) {
		this.isStun = isStun;
	}
}
