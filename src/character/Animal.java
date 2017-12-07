package character;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utility.Pair;

public abstract class Animal {
	
	public Pair index;
	// speed rabbit when jump , speed wolf when walk
	public double speed;  
	public int direction;
	public int z;
	
	public ImageView body;
	public SequentialTransition sq;
	public List<Image> img = new ArrayList<>();
	public List<Image> imgInv = new ArrayList<>();
	public List<Image> imgInv2s = new ArrayList<>();
	public boolean isRunning;
	public boolean inverse;
	
	public Animal(Pair index, double speed, int direction, int z,boolean inverse)
	{
		this.index = index;
		this.speed = speed;
		this.direction = direction;
		this.z = z;
		this.inverse = inverse;
		
		body = new ImageView();
		sq = new SequentialTransition();
	}
	
	//animation
	public abstract void startRunning();
	public abstract void stopRunning();
	public abstract void runLoop(boolean jumpNow);
	public abstract boolean isVisible();
	
	public abstract Pair nextBlock();
	public void turnLeft()
	{
		this.direction = (this.direction - 1 + 6 ) % 6;
	}
	public void turnRight()
	{
		this.direction = (this.direction + 1 + 6 ) % 6;
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
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}

	public boolean isInverse() {
		return inverse;
	}

	public void setInverse(boolean inverse) {
		this.inverse = inverse;
	}



	
	
	
}
