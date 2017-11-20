package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;
import utility.Pair;
import utility.RandomGenerator;

public class MapHolder {
	public static final double BLOCK_SIZE = 60.0;
	public static ObservableList<List<Block>> mapData;
	public static Set<Pair> carrot = new HashSet<Pair>();
	public static Set<Pair> trap = new HashSet<Pair>();
	public static int count = 0;
	public static int[][] typeBlock = new int[][] {
		{0,0,0,0,0,0,0,1,1,1,1,1,1},
		{0,0,0,0,0,0,0,0,1,1,1,1,1},
		{0,0,0,0,0,0,0,0,0,1,1,1,1},
		{0,0,0,2,0,0,2,0,0,0,1,1,1},
		{0,0,0,0,1,1,1,0,0,0,0,1,1},
		{0,0,0,0,1,1,1,1,0,0,0,0,1},
		{0,0,0,2,1,1,1,1,1,2,0,0,0},
		{0,0,0,0,1,1,1,1,0,0,0,0,1},
		{0,0,0,0,1,1,1,0,0,0,0,1,1},
		{0,0,0,2,0,0,2,0,0,0,1,1,1},
		{0,0,0,0,0,0,0,0,0,1,1,1,1},
		{0,0,0,0,0,0,0,0,1,1,1,1,1},
		{0,0,0,0,0,0,0,1,1,1,1,1,1}
	};
	public MapHolder()
	{
		
	}
	public void genMap(int diff)
	{
		count = 0;
		mapData = FXCollections.<List<Block>>observableArrayList();
		for(int i=-6;i<=6;++i)
		{
			List<Block> tempRow = new ArrayList<>();
			for(int j=0;j<13-Math.abs(i);++j)
			{
				Block temp;
				if(typeBlock[i+6][j]==0) {
					temp = new NormalBlock(j,i+6,count);
				}
				else if(typeBlock[i+6][j]==1) {
					temp = new VoidBlock(j,i+6,count);
				}
				else if(typeBlock[i+6][j]==2) {
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
		//createTrap();
		
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
	
	public static void createCarrot() {
		Pair tmp = RandomGenerator.randomIndex();
		while(carrot.contains(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		//System.out.println(Integer.toString(carrot.size())+":"+tmp);
		carrot.add(tmp);
		((NormalBlock)mapData.get(tmp.getY()).get(tmp.getX())).hasCarrot = true;
		mapData.get(tmp.getY()).get(tmp.getX()).loadImage();
	}
	public static void createTrap() {
		Pair tmp = RandomGenerator.randomIndex();
		while(trap.contains(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		//do something to change normal to trap
		TrapBlock tb = new TrapBlock(tmp.getX(), tmp.getY(), count++);
		int i = tmp.getY() - 6;
		int j = tmp.getX();
		double x = 0;
		double y = 0;
		if(tmp.getY()%2==0) {
			tb.position=new Point2D(BLOCK_SIZE/2+(Math.abs(i)/2+j)*BLOCK_SIZE , ((i+6)/2)*Math.sqrt(3)*BLOCK_SIZE + BLOCK_SIZE/Math.sqrt(3));
			x = BLOCK_SIZE/2+(Math.abs(i)/2+j)*BLOCK_SIZE;
			y = ((i+6)/2)*Math.sqrt(3)*BLOCK_SIZE;
		}
		else {
			tb.position=new Point2D((Math.abs(i)+1+2*j)/2*BLOCK_SIZE , ((i+6)/2)*Math.sqrt(3)*BLOCK_SIZE +5*BLOCK_SIZE/(2*Math.sqrt(3)));
			x = (Math.abs(i)+1+2*j)/2*BLOCK_SIZE;
			y = ((i+6)/2)*Math.sqrt(3)*BLOCK_SIZE +3*BLOCK_SIZE/(2*Math.sqrt(3));
		}
		Polygon a = new Polygon();
		a.setStrokeWidth(2);
		a.getPoints().addAll(x,y);
		a.getPoints().addAll(x+BLOCK_SIZE/2,y+BLOCK_SIZE/(2*Math.sqrt(3)));
		a.getPoints().addAll(x+BLOCK_SIZE/2,y+Math.sqrt(3)*BLOCK_SIZE/2);
		a.getPoints().addAll(x,y+2*BLOCK_SIZE/Math.sqrt(3));
		a.getPoints().addAll(x-BLOCK_SIZE/2,y+Math.sqrt(3)*BLOCK_SIZE/2);
		a.getPoints().addAll(x-BLOCK_SIZE/2,y+BLOCK_SIZE/(2*Math.sqrt(3)));
		tb.hexagon=a;
		tb.loadImage();
		mapData.get(tmp.getY()).set(tmp.getX(), tb);
	}
	public static void deleteTrap(Pair index)
	{
		//do something to change trap to normal
		
	}
	
}
