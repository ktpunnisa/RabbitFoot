package utility;

public class MyNode implements Comparable<MyNode> {

	public Pair index;
	public int dis;

	public MyNode(Pair index, int dis) {
		this.index = index;
		this.dis = dis;
	}

	@Override
	public int compareTo(MyNode o) {
		if (this.getClass() == o.getClass()) {
			return this.dis - o.dis;
		}
		return -1;
	}

	@Override
	public String toString() {
		return index + "(" + Integer.toString(dis) + ")";
	}

	public Pair getIndex() {
		return index;
	}

	public void setIndex(Pair index) {
		this.index = index;
	}

	public int getDis() {
		return dis;
	}

	public void setDis(int dis) {
		this.dis = dis;
	}

}
