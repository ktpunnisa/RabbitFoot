package model;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Drawer {
	MapHolder map;
	CharacterHolder character;
	Canvas all;
	Stage primaryStage;
	Group ma;
	Group an;
	public Drawer(Stage primaryStage, MapHolder map, CharacterHolder character) {
		this.map=map;
		this.character=character;
		all = new Canvas(primaryStage.getWidth(), primaryStage.getHeight());
		this.primaryStage=primaryStage;
	}
	public void drawAll()
	{
		drawMap();
		drawAnimal();
		Group all = new Group();
		all.getChildren().add(ma);
		all.getChildren().add(an);
		Scene scene = new Scene(all);
		primaryStage.setScene(scene);
	}
	private void drawMap()
	{
		ma = new Group();
		
	}
	private void drawAnimal()
	{
		an = new Group();
		
	}
}
