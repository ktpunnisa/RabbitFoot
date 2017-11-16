package model;

import javafx.scene.canvas.GraphicsContext;
import utility.Pair;

public abstract class Animal implements IRenderable {
	public static int[] dx = {1,0,-1,0};
	public static int[] dy = {0,1,0,-1};
	public int speed;
	public int direction;
	public Pair index;
	public int z;
	public Animal()
	{
		//construct all
	}
	public void turnLeft()
	{
		this.direction = (this.direction-1+6)%6;//direction-1
	}
	public void turnRight()
	{
		this.direction = (this.direction+1+6)%6;//direction+1
	}
	public void moveTo()
	{
		//animate
	}
	public abstract Pair nextBlock();
	@Override
	public int getZ() {
		return z;
	}
	@Override
	public abstract void draw(GraphicsContext gc) ;
	@Override
	public abstract boolean isVisible();
	public Pair getIndex() {
		return index;
	}
	public void setIndex(Pair index) {
		this.index = index;
	}
	
}
