
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
		
		Drawer view = new Drawer(map, character);
		Group all = view.drawAll();
		Scene sc = new Scene(all);
		sc.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.isPrimaryButtonDown())
			    {
					CharacterHolder.entities.get(0).turnLeft();

			    }
			    else if(mouseEvent.isSecondaryButtonDown())
			    {
			    		CharacterHolder.entities.get(0).turnRight();
			    }
			}
			});
		primaryStage.setScene(sc);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
/*for(int i=-6;i<=6;++i)
{
	for(int j=0;j<13-Math.abs(i);++j)
	{
		if(i%2==0) {
		Line aa = new Line(map.map[i+7][j+1].position.getX(),0,map.map[i+7][j+1].position.getX(),map.map[i+7][j+1].position.getY());
		Line bb = new Line(0,map.map[i+7][j+1].position.getY(),map.map[i+7][j+1].position.getX(),map.map[i+7][j+1].position.getY());
		all.getChildren().add(aa);
		all.getChildren().add(bb);
		}
	}
}*/
