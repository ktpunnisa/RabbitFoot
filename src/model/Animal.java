package model;

import javafx.scene.canvas.GraphicsContext;
import utility.Pair;

public abstract class Animal implements IRenderable {
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
}
