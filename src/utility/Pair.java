package utility;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import map.MapHolder;

public class Pair {
	private int x;
	private int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Pair(Pair pair) {
		this.x = pair.x;
		this.y = pair.y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Pair)) {
			return false;
		}

		Pair c = (Pair) o;

		return this.x == c.x && this.y == c.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return Integer.toString(x) + "," + Integer.toString(y);
	}

	public int distance(Pair o) {
		Queue<Pair> q = new LinkedList<Pair>();
		Map<Pair, Integer> ans = new HashMap<>();
		q.add(this);
		ans.put(this, 0);
		while (!q.isEmpty()) {
			Pair ind = q.poll();
			if (ind.equals(o)) {
				break;
			}
			for (int i = 0; i < 6; i++) {
				Pair next = MapHolder.getMapData().get(ind.getY()).get(ind.getX()).getNextBlock()[i];
				if (next != null && !ans.containsKey(next)) {
					ans.put(next, ans.get(ind) + 1);
					q.add(next);
				}
			}
		}
		return ans.get(o);
	}
}
