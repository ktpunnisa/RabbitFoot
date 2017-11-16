package model;

import javafx.scene.canvas.GraphicsContext;
import utility.Pair;

public abstract class Animal implements IRenderable {
	public static int[] dx = {1,0,-1,0};
	public static int[] dy = {0,1,0,-1};
	public int speed;
	public int direction;
	public Pair position; //เป็นindexของช่อง6 เหลี่ยม
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
