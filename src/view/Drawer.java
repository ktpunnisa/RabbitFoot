package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import model.CharacterHolder;
import model.MapHolder;

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
		for(int i=0;i<13;++i)
		{
			for(int j=0;j<13-Math.abs(i);++j)
			{
				ma.getChildren().add(map.getMap()[i][j].hexagon);
			}
		}
		
	}
	private void drawAnimal()
	{
		an = new Group();
	}
}
