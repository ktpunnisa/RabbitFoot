package model;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Drawer {
	MapHolder map;
	CharacterHolder character;
	Canvas all;
	public Drawer(Stage primaryStage, MapHolder map, CharacterHolder character) {
		this.map=map;
		this.character=character;
		all = new Canvas(primaryStage.getWidth(), primaryStage.getHeight());
	}
	public void drawAll(Stage primaryStage)
	{
		drawMap();
		drawAnimal();
		VBox rootView = new VBox();
		rootView.getChildren().add(all);
		Scene scene = new Scene(rootView);
		primaryStage.setScene(scene);
	}
	private void drawMap()
	{
		GraphicsContext gc = all.getGraphicsContext2D();
	}
	private void drawAnimal()
	{
		GraphicsContext gc = all.getGraphicsContext2D();
	}
}
