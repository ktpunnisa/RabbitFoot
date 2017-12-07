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
	public static Pair potion = new Pair(-1,-1);
	public static long potionTime = 0;
	public static int count = 0;
	public static int[][] typeBlock = new int[][] {
		{0,0,0,0,0,0,0,1,1,1,1,1,1},
		{0,3,3,3,3,3,3,0,1,1,1,1,1},
		{0,3,3,3,3,3,3,3,0,1,1,1,1},
		{0,3,3,2,0,0,2,3,3,0,1,1,1},
		{0,3,3,0,1,1,1,0,3,3,0,1,1},
		{0,3,3,0,1,1,1,1,0,3,3,0,1},
		{0,3,3,2,1,1,1,1,1,2,3,3,0},
		{0,3,3,0,1,1,1,1,0,3,3,0,1},
		{0,3,3,0,1,1,1,0,3,3,0,1,1},
		{0,3,3,2,0,0,2,3,3,0,1,1,1},
		{0,3,3,3,3,3,3,3,0,1,1,1,1},
		{0,3,3,3,3,3,3,0,1,1,1,1,1},
		{0,0,0,0,0,0,0,1,1,1,1,1,1}
	};
	
	public MapHolder()
	{
		count = 0;
		mapData = FXCollections.<List<Block>>observableArrayList();
	}
	
	public void genMap(int diff)
	{
		for(int i=-6;i<=6;++i)
		{
			List<Block> tempRow = new ArrayList<>();
			for(int j=0;j<13-Math.abs(i);++j){
				Block temp;
				if(typeBlock[i+6][j]==0 ||typeBlock[i+6][j]==3) {
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
				tempRow.add(temp);
				count++;
			}
			mapData.add(tempRow);
		}
	}
	
	public static void createCarrot() 
	{
		Pair tmp = RandomGenerator.randomIndex();
		while(carrot.contains(tmp) || trap.contains(tmp) || tmp.equals(potion)) {
			tmp = RandomGenerator.randomIndex();
		}
		//System.out.println(Integer.toString(carrot.size())+":"+tmp);
		carrot.add(tmp);
		((NormalBlock)mapData.get(tmp.getY()).get(tmp.getX())).hasCarrot = true;
		mapData.get(tmp.getY()).get(tmp.getX()).loadImage();
	}
	
	public static void createTrap() 
	{
		Pair tmp = RandomGenerator.randomIndex();
		while(trap.contains(tmp) || carrot.contains(tmp) || tmp.equals(potion)) {
			tmp = RandomGenerator.randomIndex();
		}
		trap.add(tmp);
		System.out.println("Trap"+Integer.toString(trap.size())+":"+tmp);
		//do something to change normal to trap
		TrapBlock tb = new TrapBlock(tmp.getX(), tmp.getY(), mapData.get(tmp.getY()).get(tmp.getX()).id);
		tb.position = mapData.get(tmp.getY()).get(tmp.getX()).position;
		tb.hexagon = mapData.get(tmp.getY()).get(tmp.getX()).hexagon;
		mapData.get(tmp.getY()).set(tmp.getX(), tb);
		mapData.get(tmp.getY()).get(tmp.getX()).loadImage();
		
	}
	
	public static void deleteTrap(Pair index)
	{
		trap.remove(index);
		System.out.println("delete Trap"+Integer.toString(trap.size())+":"+index);
		//do something to change normal to trap
		NormalBlock nb = new NormalBlock(index.getX(), index.getY(), mapData.get(index.getY()).get(index.getX()).id);
		nb.position = mapData.get(index.getY()).get(index.getX()).position;
		nb.hexagon = mapData.get(index.getY()).get(index.getX()).hexagon;
		mapData.get(index.getY()).set(index.getX(), nb);
		mapData.get(index.getY()).get(index.getX()).loadImage();
		
	}
	
	public static void createPotion() 
	{
		Pair tmp = RandomGenerator.randomIndex();
		while(carrot.contains(tmp) || trap.contains(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		potion = tmp;
		System.out.println("potion:" + tmp);
		((NormalBlock)mapData.get(potion.getY()).get(potion.getX())).hasPotion = true;
		mapData.get(potion.getY()).get(potion.getX()).loadImage();
	}

	public static void deletePotion(boolean loadMap) {
		if(loadMap) {
			((NormalBlock)mapData.get(potion.getY()).get(potion.getX())).hasPotion = false;
			mapData.get(potion.getY()).get(potion.getX()).loadImage();
		}
		potion = new Pair(-1,-1);
		potionTime = 0;
	}

}
