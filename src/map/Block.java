package map;

import character.Animal;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import utility.Pair;

public abstract class Block {
	
	public static final double BLOCK_SIZE = 60.0;
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
			else if(MapHolder.typeBlock[nextBlock[i].getY()][nextBlock[i].getX()]==1) nextBlock[i] = null;
		}
		if(y%2==0) {
			this.position = new Point2D(BLOCK_SIZE/2+(Math.abs(y-6)/2+x)*BLOCK_SIZE , ((y)/2)*Math.sqrt(3)*BLOCK_SIZE + BLOCK_SIZE/Math.sqrt(3));
			this.hexagon = draw(BLOCK_SIZE/2+(Math.abs(y-6)/2+x)*BLOCK_SIZE,((y)/2)*Math.sqrt(3)*BLOCK_SIZE);
		}
		else {
			this.position = new Point2D((Math.abs(y-6)+1+2*x)/2*BLOCK_SIZE , ((y)/2)*Math.sqrt(3)*BLOCK_SIZE +5*BLOCK_SIZE/(2*Math.sqrt(3)));
			this.hexagon = draw((Math.abs(y-6)+1+2*x)/2*BLOCK_SIZE, ((y)/2)*Math.sqrt(3)*BLOCK_SIZE +3*BLOCK_SIZE/(2*Math.sqrt(3)));
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
	public abstract void checkEvent(Animal animal);
	
	private Polygon draw(double x, double y)
	{
		Polygon a = new Polygon();
		a.setStrokeWidth(2);
		a.getPoints().addAll(x,y);
		a.getPoints().addAll(x+BLOCK_SIZE/2,y+BLOCK_SIZE/(2*Math.sqrt(3)));
		a.getPoints().addAll(x+BLOCK_SIZE/2,y+Math.sqrt(3)*BLOCK_SIZE/2);
		a.getPoints().addAll(x,y+2*BLOCK_SIZE/Math.sqrt(3));
		a.getPoints().addAll(x-BLOCK_SIZE/2,y+Math.sqrt(3)*BLOCK_SIZE/2);
		a.getPoints().addAll(x-BLOCK_SIZE/2,y+BLOCK_SIZE/(2*Math.sqrt(3)));
		return a;
	}
}
