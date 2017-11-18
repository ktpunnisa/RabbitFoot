package map;

import character.Animal;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import utility.Pair;

public abstract class Block {
	public Pair index;
	public int id;
	public Point2D position;
	public Polygon hexagon;
	public Pair[] nextBlock;
	public Block(int x,int y, int c)
	{
		this.id = c;
		this.index = new Pair(x,y);
		this.nextBlock = getNextBlock(this.index);
		for(int i=0;i<6;++i) {
			if(nextBlock[i].getX()<0 || nextBlock[i].getY() < 0 || nextBlock[i].getX()>=13 || nextBlock[i].getY()>=13) nextBlock[i] = null;
			else if(MapHolder.voidBlock[nextBlock[i].getY()][nextBlock[i].getX()]==1) nextBlock[i] = null;
		}
	}
	
	public Pair[] getNextBlock(Pair ind) {
		int x = ind.getX();
		int y = ind.getY();
		Pair temp[];
		if(y <= 5) {
			temp = new Pair[]{
					new Pair(x-1,y-1),
					new Pair(x,y-1),
					new Pair(x+1,y),
					new Pair(x+1,y+1),
					new Pair(x,y+1),
					new Pair(x-1,y)
			};
		}
		else if(y == 6) {
			temp = new Pair[]{
					new Pair(x-1,y-1),
					new Pair(x,y-1),
					new Pair(x+1,y),
					new Pair(x,y+1),
					new Pair(x-1,y+1),
					new Pair(x-1,y)
			};
		}
		else {
			temp = new Pair[]{
					new Pair(x,y-1),
					new Pair(x+1,y-1),
					new Pair(x+1,y),
					new Pair(x,y+1),
					new Pair(x-1,y+1),
					new Pair(x-1,y)
			};
		}
		return temp;
		
	}
	public abstract void loadImage();
	public abstract void checkEvent();
}
