package model;

import javafx.scene.canvas.GraphicsContext;
import utility.Pair;

public class Rabbit extends Animal {
	public Rabbit() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public void move()
	{
		
	}
	public Pair nextBlock() {
		return Block.coorBlock(this.index,this.direction);
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
}
