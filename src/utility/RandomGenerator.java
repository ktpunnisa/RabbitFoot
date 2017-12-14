package utility;

import java.util.Random;

import character.CharacterHolder;
import map.MapHolder;

public class RandomGenerator {
	private static Random rand = new Random(System.nanoTime());

	public static int random(int from, int to) {
		return from + RandomGenerator.rand.nextInt(to - from);
	}

	public static Pair randomIndex() {
		int x = RandomGenerator.random(0, 1100007) % 13;
		int y = RandomGenerator.random(0, 1100007) % 13;
		while (MapHolder.typeBlock[y][x] == 1 || MapHolder.typeBlock[y][x] == 2) {
			x = RandomGenerator.random(0, 1100007) % 13;
			y = RandomGenerator.random(0, 1100007) % 13;
		}
		return new Pair(x, y);
	}

	public static void setSeed(long seed) {
		rand = new Random(System.nanoTime());
	}

	public static Pair generateIndexRabbit(int direction) {
		Pair tmp = RandomGenerator.randomIndex();
		while (MapHolder.getTrap().contains(tmp)
				|| MapHolder.getMapData().get(tmp.getY()).get(tmp.getX()).getNextBlock()[direction] == null) {
			tmp = RandomGenerator.randomIndex();
		}
		return tmp;
	}

	public static Pair generateIndexWolf() {
		Pair tmp = RandomGenerator.randomIndex();
		while (tmp.distance(CharacterHolder.getAniData().get(0).getIndex()) <= 5 || MapHolder.getTrap().contains(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		return tmp;
	}

	public static Pair generateIndexBlock() {
		Pair tmp = RandomGenerator.randomIndex();
		while (MapHolder.getCarrot().contains(tmp) || MapHolder.getTrap().contains(tmp)
				|| tmp.equals(MapHolder.getPotion()) || MapHolder.getItem().containsKey(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		return tmp;
	}
}