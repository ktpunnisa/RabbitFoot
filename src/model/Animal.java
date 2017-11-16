package model;

import javafx.scene.canvas.GraphicsContext;

public abstract class Animal implements IRenderable {
	public int speed;
	public int direction;
	public int position; //เป็นindexของช่อง6 เหลี่ยม
	public int z;
	public Animal()
	{
		//construct all
	}
	public void turnLeft()
	{
		//direction-1
	}
	public void turnRight()
	{
		//direction+1
	}
	public void moveTo()
	{
		//animate
	}
	@Override
	public int getZ() {
		return z;
	}
	@Override
	public abstract void draw(GraphicsContext gc) ;
	@Override
	public abstract boolean isVisible();
}
