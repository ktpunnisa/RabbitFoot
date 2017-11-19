package utility;

import java.util.Random;

import map.MapHolder;
import map.NormalBlock;


public class RandomGenerator {
	
	private static Random rand = new Random(9999L);

	public static int random(int from, int to)
	{
		return from + RandomGenerator.rand.nextInt(to - from);
	} 
	public static Pair randomIndex() {
		int x = RandomGenerator.random(0, 12);
		int y = RandomGenerator.random(0, 12);
		while(!(MapHolder.mapData.get(x).get(y) instanceof NormalBlock)) {
			x = RandomGenerator.random(0, 12);
			y = RandomGenerator.random(0, 12);
		}
		return new Pair(x,y); 
	}
}