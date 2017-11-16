
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Block;
import model.CharacterHolder;
import model.Drawer;
import model.IRenderable;
import model.MapHolder;
//import model.GenBlock;
import utility.Pair;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		MapHolder map = new MapHolder();
		map.genMap(5);
		CharacterHolder character = new CharacterHolder();
		character.genAnimal(5);
		Drawer view = new Drawer(primaryStage, map, character);
		view.drawAll();
		primaryStage.show();
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
