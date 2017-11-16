package model;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import utility.Pair;

public class Block implements IRenderable {
	public static int count = 0;
	public Pair index;
	public int id;
	public Point2D position;
	public Polygon hexagon;
	public Pair[] coor;

	public Block(int x,int y)
	{
		this.id = Block.count++;
		this.index = new Pair(x,y);
		this.coor = coorBlock(this.index);
		
	}
	
	public Pair[] coorBlock(Pair ind) {
		int x = ind.getX();
		int y = ind.getY();
		Pair temp[];
		if(y <= 6) {
			temp = new Pair[]{
					new Pair(x-1,y-1),
					new Pair(x,y-1),
					new Pair(x+1,y),
					new Pair(x+1,y+1),
					new Pair(x,y+1),
					new Pair(x-1,y)
			};
		}
		else if(y == 7) {
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
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
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
	public Pair getIndex() {
		return index;
	}

	public int getId() {
		return id;
	}

	public Point2D getPosition() {
		return position;
	}

	public static int getCount() {
		return count;
	}
	
	public Polygon getHexagon() {
		return hexagon;
	}
	
	public Pair[] getCoor() {
		return coor;
	}

}

