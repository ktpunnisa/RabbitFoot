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
		int x = RandomGenerator.random(0, 1100007)%13;
		int y = RandomGenerator.random(0, 1100007)%13;
		while(MapHolder.typeBlock[x][y] != 0) {
			x = RandomGenerator.random(0, 1100007)%13;
			y = RandomGenerator.random(0, 1100007)%13;
		}
		return new Pair(y,x); 
	}
}