package model;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import utility.Pair;

public class Block implements IRenderable {
	public int index;
	public Point2D position;
	public Polygon hexagon;

	public Block()
	{
		
	}
	public int getIndex() {
		return index;
	}

	public Point2D getPosition()
	{
		return position;
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

//public int color;
	//protected Pair index;
	//protected Pair[] coor;
	//protected int direction; // 1:top left 2:top right 3:right 4:down right 5:down left 6:left
	
	/*public Block(int x,int y){
		this.index = new Pair(x,y);
		this.coor = new Pair[10]; 
		if(x <= 6) {
			this.coor[1] = new Pair(x-1,y-1); // top left
			this.coor[2] = new Pair(x-1,y); // top right
			this.coor[3] = new Pair(x,y+1); // right
			this.coor[4] = new Pair(x+1,y+1); // down right
			this.coor[5] = new Pair(x+1,y); // down left
			this.coor[6] = new Pair(x,y-1); // left
		}
		else if(x == 7) {
			this.coor[1] = new Pair(x-1,y-1); // top left
			this.coor[2] = new Pair(x-1,y); // top right
			this.coor[3] = new Pair(x,y+1); // right
			this.coor[4] = new Pair(x+1,y); // down right
			this.coor[5] = new Pair(x+1,y-1); // down left
			this.coor[6] = new Pair(x,y-1); // left
		}
		else {
			this.coor[1] = new Pair(x-1,y); // top left
			this.coor[2] = new Pair(x-1,y+1); // top right
			this.coor[3] = new Pair(x,y+1); // right
			this.coor[4] = new Pair(x+1,y); // down right
			this.coor[5] = new Pair(x+1,y-1); // down left
			this.coor[6] = new Pair(x,y-1); // left
		}
	} */
/*public Pair[] getCoor() {
return coor;
}*/
