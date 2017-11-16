package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import model.Animal;
import model.CharacterHolder;
import model.MapHolder;
import model.Rabbit;

public class Drawer {
	public static Group drawedMap;
	public static Group drawedAnimal;
	public Drawer(MapHolder map, CharacterHolder character) {
		
	}
	public Group drawAll()
	{
		Group all = new Group();
		drawedMap=drawMap();
		all.getChildren().add(drawedMap);
		drawedAnimal=drawAnimal();
		all.getChildren().add(drawedAnimal);
		return all;
	}
	private Group drawMap()
	{
		Group temp = new Group();
		for(int i=-6;i<=6;++i)
		{
			for(int j=0;j<13-Math.abs(i);++j)
			{
				temp.getChildren().add(MapHolder.map[i+7][j+1].hexagon);
			}
		}
		return temp;
	}
	private Group drawAnimal()
	{
		Group temp = new Group();
		for(Animal a : CharacterHolder.entities) 
		{
			if(a instanceof Rabbit)
				temp.getChildren().add( ((Rabbit)a).ob );
		}
		return temp;
	}
}
