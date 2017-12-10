package character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Group;
import map.MapHolder;
import utility.Pair;
import utility.RandomGenerator;

public class CharacterHolder {
	
	public static List<Animal> aniData;
	public static Group aniGroup;
	public static boolean inverse;
	public static long timeInverse;
	public static boolean invis;
	
	public CharacterHolder(int diff)
	{
		aniGroup = new Group();
		inverse = false;
		invis = false;
		timeInverse = 0;
		aniData = new ArrayList<Animal>();
		Pair tmp = RandomGenerator.randomIndex();
		while(MapHolder.trap.contains(tmp)) 
		{
			tmp = RandomGenerator.randomIndex();
		}
		Rabbit r = new Rabbit(tmp, 1.4, 1,inverse);
		aniData.add(r);
		for(int i=0;i<diff;++i) 
		{
			tmp = RandomGenerator.randomIndex();
			while(tmp.distance(aniData.get(0).index)<=5 || MapHolder.trap.contains(tmp)) 
			{
				tmp = RandomGenerator.randomIndex();
			}
			Wolf w = new Wolf(tmp, 1.1, 1,inverse);
			aniData.add(w);
			Platform.runLater(()->aniGroup.getChildren().add(w.body));
		}
	}
	
	public void add(int n)
	{
		Pair tmp = RandomGenerator.randomIndex();
		for(int i=0;i<n;++i) {
			tmp = RandomGenerator.randomIndex();
			while(tmp.distance(aniData.get(0).index)<=5 || MapHolder.trap.contains(tmp)) 
			{
				tmp = RandomGenerator.randomIndex();
			}
			Wolf w = new Wolf(tmp, 1.1, 1,inverse);
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
}