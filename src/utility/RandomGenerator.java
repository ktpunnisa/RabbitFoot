package utility;

import java.util.Random;

import map.MapHolder;
import map.NormalBlock;


public class RandomGenerator {
	
	private static Random rand = new Random(1236265L);

	public static int random(int from, int to)
	{
		return from + RandomGenerator.rand.nextInt(to - from);
	} 
	public static Pair randomIndex() {
		int x = RandomGenerator.random(0, 1100007)%13;
		int y = RandomGenerator.random(0, 1100007)%13;
		while(MapHolder.typeBlock[y][x] == 1 || MapHolder.typeBlock[y][x] == 2) {
			x = RandomGenerator.random(0, 1100007)%13;
			y = RandomGenerator.random(0, 1100007)%13;
		}
		return new Pair(x,y); 
	}
	
	public static void setSeed(long seed) {
		rand = new Random(seed);
	}
}