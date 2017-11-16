package model;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	public int z = 0;
	public int getZ();
	public void draw(GraphicsContext gc);
	public boolean isVisible();
}
