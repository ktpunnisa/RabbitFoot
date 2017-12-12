package character;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import exception.NoItemException;
import javafx.scene.image.ImageView;
import utility.Pair;

public abstract class Animal {

	private Pair index;
	private double speed;
	private int direction;
	protected Queue<Pair> runPath;

	protected Thread animationThread;
	protected Thread runThread;
	protected ImageView body;
	protected boolean isRunning;
	private boolean inverse;
	protected int angle;
	private int angles[] = { -30, 30, 90, 150, 210, 270 };

	public Animal(Pair index, double speed, int direction, boolean inverse) {
		this.index = index;
		this.speed = speed;
		this.direction = direction;
		this.angle = angles[direction];
		this.inverse = inverse;
		this.body = new ImageView();
		this.runPath = new LinkedBlockingQueue<Pair>();
	}

	public abstract void startRunning();

	public abstract void stopRunning();

	protected abstract void animateLoop();

	protected abstract void runLoop();

	protected abstract Pair nextBlock() throws NoItemException;

	public void turnLeft() {
		this.direction = (this.direction - 1 + 6) % 6;
		this.angle -= 60;
	}

	public void turnRight() {
		this.direction = (this.direction + 1 + 6) % 6;
		this.angle += 60;
	}

	public Pair getIndex() {
		return index;
	}

	public void setIndex(Pair index) {
		this.index = index;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double d) {
		this.speed = d;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isInverse() {
		return inverse;
	}

	public void setInverse(boolean inverse) {
		this.inverse = inverse;
	}

	public void setAngle(int newDirection) {
		int x = (newDirection - this.direction + 6) % 6;
		if (x == 4)
			x = -2;
		else if (x == 5)
			x = -1;
		this.angle += x * 60;
	}

	public Pair getRunPath() {
		return runPath.peek();
	}

	public ImageView getBody() {
		return body;
	}

	public void setBody(ImageView body) {
		this.body = body;
	}

	public boolean isRunning() {
		return isRunning;
	}

}
