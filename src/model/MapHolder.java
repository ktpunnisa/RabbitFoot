package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import utility.Pair;

public class MapHolder {
	public static final List<Pair> voidBlockPair = Arrays.asList(
			new Pair(5,5),
			new Pair(5,6),
			new Pair(5,7),
			new Pair(5,8),
			new Pair(5,9),
			new Pair(6,5),
			new Pair(6,6),
			new Pair(6,7),
			new Pair(6,8),
			new Pair(6,9),
			new Pair(7,5),
			new Pair(7,6),
			new Pair(7,7),
			new Pair(7,8),
			new Pair(7,9),
			new Pair(8,6),
			new Pair(8,7),
			new Pair(8,8),
			new Pair(9,7)
	);
	public static Block[][] map;
	public MapHolder()
	{
		
	}
	public void genMap(int diff)
	{
		map = new Block[15][15];
		double width=60;
		for(int i=-6;i<=6;++i)
		{
			for(int j=0;j<13-Math.abs(i);++j)
			{
				int type=1;//0=void,1=normal,2=jump,3=trap
				if(voidBlockPair.contains(new Pair(j+1,i+7)))
					type=0;
				Block temp = new Block(j+1,i+7);
				if(type==0) temp=new VoidBlock(j+1,i+7);
				else if(type==2) temp=new JumpBlock(j+1,i+7);
				else if(type==3) temp=new TrapBlock(j+1,i+7);
				if(i%2==0) {
					temp.position=new Point2D(width/2+(Math.abs(i)/2+j)*width , ((i+6)/2)*Math.sqrt(3)*width + width/Math.sqrt(3));
					temp.hexagon=draw(width/2+(Math.abs(i)/2+j)*width,((i+6)/2)*Math.sqrt(3)*width,width);
				}
				else {
					temp.position=new Point2D((Math.abs(i)+1+2*j)/2*width , ((i+6)/2)*Math.sqrt(3)*width +5*width/(2*Math.sqrt(3)));
					temp.hexagon=draw((Math.abs(i)+1+2*j)/2*width, ((i+6)/2)*Math.sqrt(3)*width +3*width/(2*Math.sqrt(3)),width);
				}
				if(temp instanceof Block)
					temp.loadImage();
				else
					((VoidBlock)temp).loadImage();
				map[i+7][j+1]=temp;
			}
		}
		
	}
	public void destroyTrap(int blockIndex)
	{
		//if wolf are trapped, block will turn to normal
	}
	private Polygon draw(double x, double y,double width)
	{
		Polygon a = new Polygon();
		/*a.setFill(Color.AQUA);
		a.setStroke(Color.BLACK);*/
		a.setStrokeWidth(2);
		a.getPoints().addAll(x,y);
		a.getPoints().addAll(x+width/2,y+width/(2*Math.sqrt(3)));
		a.getPoints().addAll(x+width/2,y+Math.sqrt(3)*width/2);
		a.getPoints().addAll(x,y+2*width/Math.sqrt(3));
		a.getPoints().addAll(x-width/2,y+Math.sqrt(3)*width/2);
		a.getPoints().addAll(x-width/2,y+width/(2*Math.sqrt(3)));
		return a;
	}
}
