package utility;

public class Pair {
	private int x;
	private int y;
	public Pair(int x,int y) {
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
	

}
