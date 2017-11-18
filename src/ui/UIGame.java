package ui;

import java.util.List;

import character.Animal;
import character.CharacterHolder;
import character.Rabbit;
import game.GameState;
import javafx.scene.Group;
import javafx.scene.Node;
import map.Block;
import map.MapHolder;

public class UIGame extends Group {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	
	private GameState state;
	
	public UIGame(GameState state)
	{
		this.state = state;
		this.getChildren().add(drawMap());
		this.getChildren().add(drawAnimal());
	}
	
	public Group drawMap()
	{
		Group temp = new Group();
		for(List<Block> row : MapHolder.mapData)
			for(Block b : row)
				temp.getChildren().add(b.hexagon);
		return temp;
	}
	public Group drawAnimal()
	{
		Group temp = new Group();
		for(Animal a : CharacterHolder.aniData)
				temp.getChildren().add(a.body);
		return temp;
	}
}
