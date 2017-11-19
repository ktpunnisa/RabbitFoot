package utility;

import java.util.Objects;

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
	@Override
	public boolean equals(Object o) { 
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof Pair)) {
            return false;
        }
         
        Pair c = (Pair) o;
         
        // Compare the data members and return accordingly 
		return this.x==c.x && this.y==c.y;
	}
	@Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
	@Override
	public String toString(){
		return Integer.toString(x) +","+ Integer.toString(y);
	}
	
	public int distance(Pair o) {
		return Math.abs(this.getX()-o.getX())+Math.abs(this.getY()-o.getY());
	}
	

}
