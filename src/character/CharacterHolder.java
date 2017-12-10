package character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import map.MapHolder;
import ui.UIGame;
import utility.Pair;
import utility.RandomGenerator;

public class CharacterHolder {
	public static List<Animal> aniData;
	private Comparator<Animal> comparator;
	public static boolean inverse;
	public static long timeInverse;
	public static Group aniGroup;
	public CharacterHolder()
	{
		aniGroup = new Group();
		inverse = false;
		timeInverse = 0;
		aniData = new ArrayList<Animal>();
		Rabbit r = new Rabbit(new Pair(5,12), 1.4, 1, 0,inverse);
		aniData.add(r);
		comparator = (Animal o1, Animal o2) -> {
			if(o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	public void genAnimal(int diff)
	{
		for(int i=0;i<diff;++i) {
			Pair tmp = RandomGenerator.randomIndex();
			while(tmp.distance(aniData.get(0).index)<=5 || MapHolder.trap.contains(tmp)) {
				tmp = RandomGenerator.randomIndex();
			}
			Wolf w = new Wolf(tmp, 1.1, 1, 0,inverse);
			aniData.add(w);
			Platform.runLater(()->aniGroup.getChildren().add(w.body));
		}
	}
	public void add()
	{
		Pair tmp = RandomGenerator.randomIndex();
		while(tmp.distance(aniData.get(0).index)<=5 || MapHolder.trap.contains(tmp)) {
			tmp = RandomGenerator.randomIndex();
		}
		Wolf w = new Wolf(tmp, 1.1, 1, 0,inverse);
		aniData.add(w);
		w.startRunning();
		Platform.runLater(()->aniGroup.getChildren().add(w.body));
	}
	public void remove(Animal animal)
	{
		aniData.remove(animal);
		Platform.runLater(()->aniGroup.getChildren().remove(animal.body));
	}
	
	
}