
import java.util.List;

import control.Controller;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.Block;
import model.CharacterHolder;
import model.IRenderable;
import model.MapHolder;
import model.Rabbit;
import utility.Pair;
import view.Drawer;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Controller control = new Controller();
		MapHolder map = new MapHolder();
		map.genMap(5);
		CharacterHolder character = new CharacterHolder();
		character.genAnimal(5);
		Drawer view = new Drawer(primaryStage, map, character);
		Group all = view.drawAll();
		/*for(int i=-6;i<=6;++i)
		{
			for(int j=0;j<13-Math.abs(i);++j)
			{
				if(i%2==1 || i%2 ==-1) {
				Line aa = new Line(map.map[i+6][j].position.getX(),0,map.map[i+6][j].position.getX(),map.map[i+6][j].position.getY());
				Line bb = new Line(0,map.map[i+6][j].position.getY(),map.map[i+6][j].position.getX(),map.map[i+6][j].position.getY());
				all.getChildren().add(aa);
				all.getChildren().add(bb);
				}
			}
		}*/
		all.getChildren().add( ((Rabbit)CharacterHolder.entities.get(0)).ob );
		Scene sc = new Scene(all);
		sc.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.isPrimaryButtonDown())
			    {
					character.entities.get(0).direction+=1;
					character.entities.get(0).direction%=4;

			    }
			    else if(mouseEvent.isSecondaryButtonDown())
			    {
			    		character.entities.get(0).direction-=1;
			    		while(character.entities.get(0).direction<0) character.entities.get(0).direction+=4;
			    		character.entities.get(0).direction%=4;
			    }
			}
			});
		primaryStage.setScene(sc);
		primaryStage.show();
		new AnimationTimer(){
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
			}
		};
	}

	public static void main(String[] args) {
		launch(args);
	}
}
		// print coor block
		/*
		Block[][] map = genBlock.getBlock();
		for(int i = 1;i<=13;i++) {
			if(i <=7) {
				for(int j=1;j<=i+6;j++) {
					System.out.println(i+","+j+" : ");
					Pair[] coor = map[i][j].getCoor();
					for(int k = 1;k<=6;k++) {
						System.out.print("("+Integer.toString(coor[k].getX())+","+Integer.toString(coor[k].getY()) +") ");
					}
					System.out.println();
				}
			}
			else {
				for(int j=1;j<=20-i;j++) {
					System.out.println(i+","+j+" : ");
					Pair[] coor = map[i][j].getCoor();
					for(int k = 1;k<=6;k++) {
						System.out.print("("+Integer.toString(coor[k].getX())+","+Integer.toString(coor[k].getY()) +") ");
					}
					System.out.println();
				}
			}
		}
		*/
