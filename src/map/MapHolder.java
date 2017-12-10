package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import item.ItemInvis;
import item.ItemBomb;
import item.Item;
import item.ItemSpeed;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import utility.Pair;
import utility.RandomGenerator;

public class MapHolder {
	
	public static final double BLOCK_SIZE = 60.0;
	public static ObservableList<List<Block>> mapData;
	public static Group mapGroup;
	public static Set<Pair> carrot;
	public static Set<Pair> trap;
	public static Map<Pair,Item> item;
	public static Pair potion;
	public static long potionTime;
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
		carrot = new HashSet<Pair>();
		trap = new HashSet<Pair>();
		item = new HashMap<Pair, Item>();
		potion = new Pair(-1,-1);
		potionTime = 0;
		count = 0;
		mapData = FXCollections.<List<Block>>observableArrayList();
		mapGroup = new Group();
		genMap();
		for(List<Block> row : MapHolder.mapData)
			for(Block b : row)
				mapGroup.getChildren().add(b.hexagon);
	}
	
	public void genMap()
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
		while(carrot.contains(tmp) || trap.contains(tmp) || tmp.equals(potion) || item.containsKey(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		carrot.add(tmp);
		((NormalBlock)mapData.get(tmp.getY()).get(tmp.getX())).hasCarrot = true;
		mapData.get(tmp.getY()).get(tmp.getX()).loadImage();
	}
	
	public static void createTrap() 
	{
		Pair tmp = RandomGenerator.randomIndex();
		while(trap.contains(tmp) || carrot.contains(tmp) || tmp.equals(potion) || item.containsKey(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		trap.add(tmp);
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
		NormalBlock nb = new NormalBlock(index.getX(), index.getY(), mapData.get(index.getY()).get(index.getX()).id);
		nb.position = mapData.get(index.getY()).get(index.getX()).position;
		nb.hexagon = mapData.get(index.getY()).get(index.getX()).hexagon;
		mapData.get(index.getY()).set(index.getX(), nb);
		mapData.get(index.getY()).get(index.getX()).loadImage();
		
	}
	
	public static void createPotion() 
	{
		Pair tmp = RandomGenerator.randomIndex();
		while(carrot.contains(tmp) || trap.contains(tmp) || item.containsKey(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		potion = tmp;
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
	
	public static void createItem(int type) 
	{
		Pair tmp = RandomGenerator.randomIndex();
		while(carrot.contains(tmp) || trap.contains(tmp) || tmp.equals(potion) || item.containsKey(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		if(type==0)
			item.put(tmp, new ItemInvis(tmp));
		if(type==1)
			item.put(tmp, new ItemBomb(tmp));
		if(type==2)
			item.put(tmp, new ItemSpeed(tmp));
		((NormalBlock)mapData.get(tmp.getY()).get(tmp.getX())).hasItem = true;
		mapData.get(tmp.getY()).get(tmp.getX()).loadImage();
	}

	public static void deleteItem(Pair index) {
		item.remove(index);
		((NormalBlock)mapData.get(index.getY()).get(index.getX())).hasItem = false;
		mapData.get(index.getY()).get(index.getX()).loadImage();
	}

}
