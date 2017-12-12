package character;

import java.util.ArrayList;
import java.util.List;

import game.GameState;
import javafx.application.Platform;
import javafx.scene.Group;
import map.MapHolder;
import utility.Pair;
import utility.RandomGenerator;

public class CharacterHolder {
	
	private static List<Animal> aniData;
	private static Group aniGroup;
	
	public CharacterHolder(int diff)
	{
		aniGroup = new Group();
		aniData = new ArrayList<Animal>();
		Pair tmp = RandomGenerator.randomIndex();
		int direct = RandomGenerator.random(0, 5);
		while(MapHolder.getTrap().contains(tmp) || MapHolder.getMapData().get(tmp.getY()).get(tmp.getX()).nextBlock[direct] == null) 
		{
			tmp = RandomGenerator.randomIndex();
		}
		Rabbit r = new Rabbit(tmp, 1.4, direct,GameState.isInverse());
		aniData.add(r);
		for(int i=0;i<diff;++i) 
		{
			tmp = RandomGenerator.randomIndex();
			while(tmp.distance(aniData.get(0).getIndex())<=5 || MapHolder.getTrap().contains(tmp)) 
			{
				tmp = RandomGenerator.randomIndex();
			}
			Wolf w = new Wolf(tmp, 1.1, 1,GameState.isInverse());
			aniData.add(w);
			Platform.runLater(()->aniGroup.getChildren().add(w.body));
		}
	}
	
	public void add(int n)
	{
		Pair tmp = RandomGenerator.randomIndex();
		for(int i=0;i<n;++i) {
			tmp = RandomGenerator.randomIndex();
			while(tmp.distance(aniData.get(0).getIndex())<=5 || MapHolder.getTrap().contains(tmp)) 
			{
				tmp = RandomGenerator.randomIndex();
			}
			Wolf w = new Wolf(tmp, 1.1, 1,GameState.isInverse());
			aniData.add(w);
			w.startRunning();
			Platform.runLater(()->aniGroup.getChildren().add(w.body));
		}
	}
	
	public void remove(Animal animal)
	{
		((Wolf)animal).stopRunning();
		aniData.remove(animal);
		Platform.runLater(()->aniGroup.getChildren().remove(animal.body));
	}
	
	public static List<Animal> getAniData() {
		return aniData;
	}

	public static void setAniData(List<Animal> aniData) {
		CharacterHolder.aniData = aniData;
	}

	public static Group getAniGroup() {
		return aniGroup;
	}

	public void setAniGroup(Group aniGroup) {
		CharacterHolder.aniGroup = aniGroup;
	}
	
	
}