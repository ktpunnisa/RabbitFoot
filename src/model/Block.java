package model;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import utility.Pair;

public class Block implements IRenderable {
	public static int count=0;
	public Pair index;
	public int id;
	public Point2D position;
	public Polygon hexagon;
	public Pair coor[] = new Pair[6];

	public Block(int x,int y)
	{
		this.id = Block.count++;
		this.index = new Pair(x,y);
		for(int i = 0;i < 6; i++) {
			this.coor[i] = coorBlock(this.index,i);
		}
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
	
	public static Pair coorBlock(Pair ind, int direct) {
		int x = ind.getX();
		int y = ind.getY();
		Pair coor;
		if(x <= 6) {
			if(direct == 0)			coor = new Pair(x-1,y-1); // top left
			else if(direct == 1)	coor = new Pair(x-1,y); // top right
			else if(direct == 2)	coor = new Pair(x,y+1); // right
			else if(direct == 3)	coor = new Pair(x+1,y+1); // down right
			else if(direct == 4)	coor = new Pair(x+1,y); // down left
			else if(direct == 5)	coor = new Pair(x,y-1); // left
		}
		else if(x == 7) {
			if(direct == 0)			coor = new Pair(x-1,y-1); // top left
			else if(direct == 1)	coor = new Pair(x-1,y); // top right
			else if(direct == 2)	coor = new Pair(x,y+1); // right
			else if(direct == 3)	coor = new Pair(x+1,y); // down right
			else if(direct == 4)	coor = new Pair(x+1,y-1); // down left
			else if(direct == 5)	coor = new Pair(x,y-1); // left
		}
		else {
			if(direct == 0)			coor = new Pair(x-1,y); // top left
			else if(direct == 1)	coor = new Pair(x-1,y+1); // top right
			else if(direct == 2)	coor = new Pair(x,y+1); // right
			else if(direct == 3)	coor = new Pair(x+1,y); // down right
			else if(direct == 4)	coor = new Pair(x+1,y-1); // down left
			else if(direct == 5)	coor = new Pair(x,y-1); // left
		}
		coor = new Pair(x,y);
		return coor;
		
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
	

}

