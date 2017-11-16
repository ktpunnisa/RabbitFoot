package model;

import javafx.scene.paint.Color;

public class TrapBlock extends Block {

	public TrapBlock(int x,int y) {
		super(x,y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void runEvent(Animal animal)
	{
		
	}
	@Override
	public void loadImage()
	{
		this.hexagon.setFill(Color.RED);
	}
}
