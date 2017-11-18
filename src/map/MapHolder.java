package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;
import utility.Pair;

public class MapHolder {
	public static final double BLOCK_SIZE = 60.0;
	public static ObservableList<List<Block>> mapData;
	public static int[][] voidBlock = new int[][] {
		{0,0,0,0,0,0,0,1,1,1,1,1,1},
		{0,0,0,0,0,0,0,0,1,1,1,1,1},
		{0,0,0,0,0,0,0,0,0,1,1,1,1},
		{0,0,0,0,0,0,0,0,0,0,1,1,1},
		{0,0,0,0,1,1,1,0,0,0,0,1,1},
		{0,0,0,0,1,1,1,1,0,0,0,0,1},
		{0,0,0,0,1,1,1,1,1,0,0,0,0},
		{0,0,0,0,1,1,1,1,0,0,0,0,1},
		{0,0,0,0,1,1,1,0,0,0,0,1,1},
		{0,0,0,0,0,0,0,0,0,0,1,1,1},
		{0,0,0,0,0,0,0,0,0,1,1,1,1},
		{0,0,0,0,0,0,0,0,1,1,1,1,1},
		{0,0,0,0,0,0,0,1,1,1,1,1,1}
	};
	public MapHolder()
	{
		
	}
	public void genMap(int diff)
	{
		int count = 0;
		mapData = FXCollections.<List<Block>>observableArrayList();
		for(int i=-6;i<=6;++i)
		{
			List<Block> tempRow = new ArrayList<>();
			for(int j=0;j<13-Math.abs(i);++j)
			{
				Block temp;
				if(voidBlock[i+6][j]==0) {
					temp = new NormalBlock(j,i+6,count);
				}
				else if(voidBlock[i+6][j]==1) {
					temp = new VoidBlock(j,i+6,count);
				}
				else if(voidBlock[i+6][j]==2) {
					temp = new JumpBlock(j,i+6,count);
				}
				else {
					temp = new TrapBlock(j,i+6,count);
				}
				
				if(i%2==0) {
					temp.position=new Point2D(BLOCK_SIZE/2+(Math.abs(i)/2+j)*BLOCK_SIZE , ((i+6)/2)*Math.sqrt(3)*BLOCK_SIZE + BLOCK_SIZE/Math.sqrt(3));
					temp.hexagon=draw(BLOCK_SIZE/2+(Math.abs(i)/2+j)*BLOCK_SIZE,((i+6)/2)*Math.sqrt(3)*BLOCK_SIZE);
				}
				else {
					temp.position=new Point2D((Math.abs(i)+1+2*j)/2*BLOCK_SIZE , ((i+6)/2)*Math.sqrt(3)*BLOCK_SIZE +5*BLOCK_SIZE/(2*Math.sqrt(3)));
					temp.hexagon=draw((Math.abs(i)+1+2*j)/2*BLOCK_SIZE, ((i+6)/2)*Math.sqrt(3)*BLOCK_SIZE +3*BLOCK_SIZE/(2*Math.sqrt(3)));
				}
				temp.loadImage();
				tempRow.add(temp);
				count++;
			}
			mapData.add(tempRow);
		}
	}
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
	public void deleteTrap(int x, int y)
	{
		
	}
}
