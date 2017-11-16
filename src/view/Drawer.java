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
		double width=30;
		for(int i=-6;i<=6;++i)
		{
			if(i%2==0)
			{
				for(int j=0;j<13-Math.abs(i);++j)
				{
					ma.getChildren().add(draw(width/2+(Math.abs(i)/2+j)*width,((i+6)/2)*Math.sqrt(3)*width,width));
				}
			}
			else
			{
				for(int j=0;j<13-Math.abs(i);++j)
				{
					ma.getChildren().add(draw((Math.abs(i)+1+2*j)/2*width, ((i+6)/2)*Math.sqrt(3)*width +3*width/(2*Math.sqrt(3)),width));
				}
			}
		}
		
	}
	private void drawAnimal()
	{
		an = new Group();
		an.getChildren().add(draw(0,0,50));
	}
	private Polygon draw(double x, double y,double width)
	{
		Polygon a = new Polygon();
		a.setFill(Color.AQUA);
		a.setStroke(Color.BLACK);
		a.setStrokeWidth(2);
		a.getPoints().addAll(x,y);
		a.getPoints().addAll(x+width/2,y+width/(2*Math.sqrt(3)));
		a.getPoints().addAll(x+width/2,y+Math.sqrt(3)*width/2);
		a.getPoints().addAll(x,y+2*width/Math.sqrt(3));
		a.getPoints().addAll(x-width/2,y+Math.sqrt(3)*width/2);
		a.getPoints().addAll(x-width/2,y+width/(2*Math.sqrt(3)));
		return a;
	}
}
