package model;

import javafx.scene.paint.Color;

public class JumpBlock extends Block {
	public int destination;
	public JumpBlock(int x,int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void runEvent(Animal animal)
	{
		
	}
	@Override
	public void loadImage()
	{
		this.hexagon.setFill(Color.CHARTREUSE);
	}
}
