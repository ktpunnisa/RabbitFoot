package model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MapHolder {

	public static Block[][] map;
	public MapHolder()
	{
		
	}
	public void genMap(int diff)
	{
		map = new Block[15][15];
		double width=60;
		int count=0;
		for(int i=-6;i<=6;++i)
		{
			for(int j=0;j<13-Math.abs(i);++j)
			{
				Block temp = new Block();
				temp.index=count++;
				if(i%2==0) {
					temp.position=new Point2D(width/2+(Math.abs(i)/2+j)*width , ((i+6)/2)*Math.sqrt(3)*width + width/Math.sqrt(3));
					temp.hexagon=draw(width/2+(Math.abs(i)/2+j)*width,((i+6)/2)*Math.sqrt(3)*width,width);
				}
				else {
					temp.position=new Point2D((Math.abs(i)+1+2*j)/2*width , ((i+6)/2)*Math.sqrt(3)*width +5*width/(2*Math.sqrt(3)));
					temp.hexagon=draw((Math.abs(i)+1+2*j)/2*width, ((i+6)/2)*Math.sqrt(3)*width +3*width/(2*Math.sqrt(3)),width);
				}
				map[i+6][j]=temp;
			}
		}
		
	}
	public void destroyTrap(int blockIndex)
	{
		//if wolf are trapped, block will turn to normal
	}
	public Block[][] getMap() {
		return map;
	}
	private Polygon draw(double x, double y,double width)
	{
		Polygon a = new Polygon();
		a.setFill(Color.AQUA);
		a.setStroke(Color.BLACK);
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
