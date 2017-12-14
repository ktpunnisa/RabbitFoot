package map;

import character.Animal;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import utility.Pair;

public abstract class Block {

	private Pair index;
	private int id;
	private Point2D position;
	private Polygon hexagon;
	private Pair[] nextBlock;

	public Block(int x, int y, int c) {
		this.id = c;
		this.index = new Pair(x, y);
		this.nextBlock = getNextBlock(this.index);
		for (int i = 0; i < 6; ++i) {
			if (nextBlock[i].getX() < 0 || nextBlock[i].getY() < 0 || nextBlock[i].getX() >= 13
					|| nextBlock[i].getY() >= 13)
				nextBlock[i] = null;
			else if (MapHolder.typeBlock[nextBlock[i].getY()][nextBlock[i].getX()] == 1)
				nextBlock[i] = null;
		}
		if (y % 2 == 0) {
			this.position = new Point2D(MapHolder.BLOCK_SIZE / 2 + (Math.abs(y - 6) / 2 + x) * MapHolder.BLOCK_SIZE,
					((y) / 2) * Math.sqrt(3) * MapHolder.BLOCK_SIZE + MapHolder.BLOCK_SIZE / Math.sqrt(3));
			this.hexagon = draw(MapHolder.BLOCK_SIZE / 2 + (Math.abs(y - 6) / 2 + x) * MapHolder.BLOCK_SIZE,
					((y) / 2) * Math.sqrt(3) * MapHolder.BLOCK_SIZE);
		} else {
			this.position = new Point2D((Math.abs(y - 6) + 1 + 2 * x) / 2 * MapHolder.BLOCK_SIZE,
					((y) / 2) * Math.sqrt(3) * MapHolder.BLOCK_SIZE + 5 * MapHolder.BLOCK_SIZE / (2 * Math.sqrt(3)));
			this.hexagon = draw((Math.abs(y - 6) + 1 + 2 * x) / 2 * MapHolder.BLOCK_SIZE,
					((y) / 2) * Math.sqrt(3) * MapHolder.BLOCK_SIZE + 3 * MapHolder.BLOCK_SIZE / (2 * Math.sqrt(3)));
		}
	}

	private Pair[] getNextBlock(Pair ind) {
		int x = ind.getX();
		int y = ind.getY();
		Pair temp[];
		if (y <= 5) {
			temp = new Pair[] { new Pair(x - 1, y - 1), new Pair(x, y - 1), new Pair(x + 1, y), new Pair(x + 1, y + 1),
					new Pair(x, y + 1), new Pair(x - 1, y) };
		} else if (y == 6) {
			temp = new Pair[] { new Pair(x - 1, y - 1), new Pair(x, y - 1), new Pair(x + 1, y), new Pair(x, y + 1),
					new Pair(x - 1, y + 1), new Pair(x - 1, y) };
		} else {
			temp = new Pair[] { new Pair(x, y - 1), new Pair(x + 1, y - 1), new Pair(x + 1, y), new Pair(x, y + 1),
					new Pair(x - 1, y + 1), new Pair(x - 1, y) };
		}
		return temp;

	}

	public abstract void loadImage();

	public abstract void checkEvent(Animal animal);

	private Polygon draw(double x, double y) {
		Polygon a = new Polygon();
		a.setStrokeWidth(2);
		a.getPoints().addAll(x, y);
		a.getPoints().addAll(x + MapHolder.BLOCK_SIZE / 2, y + MapHolder.BLOCK_SIZE / (2 * Math.sqrt(3)));
		a.getPoints().addAll(x + MapHolder.BLOCK_SIZE / 2, y + Math.sqrt(3) * MapHolder.BLOCK_SIZE / 2);
		a.getPoints().addAll(x, y + 2 * MapHolder.BLOCK_SIZE / Math.sqrt(3));
		a.getPoints().addAll(x - MapHolder.BLOCK_SIZE / 2, y + Math.sqrt(3) * MapHolder.BLOCK_SIZE / 2);
		a.getPoints().addAll(x - MapHolder.BLOCK_SIZE / 2, y + MapHolder.BLOCK_SIZE / (2 * Math.sqrt(3)));
		return a;
	}

	public Pair getIndex() {
		return index;
	}

	public void setIndex(Pair index) {
		this.index = index;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public Polygon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Polygon hexagon) {
		this.hexagon = hexagon;
	}

	public Pair[] getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(Pair[] nextBlock) {
		this.nextBlock = nextBlock;
	}
	
	
}
