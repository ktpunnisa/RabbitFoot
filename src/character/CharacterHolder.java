package character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import map.Block;
import map.MapHolder;
import utility.Pair;
import utility.RandomGenerator;

public class CharacterHolder {
	public static ObservableList<Animal> aniData;
	private Comparator<Animal> comparator;
	public static boolean inverse;
	public static long timeInverse;
	
	public CharacterHolder()
	{
		inverse = false;
		timeInverse = 0;
		aniData = FXCollections.<Animal>observableArrayList();
		Rabbit r = new Rabbit(new Pair(5,12), 1.4, 1, 0,inverse);
		aniData.add(r);
		comparator = (Animal o1, Animal o2) -> {
			if(o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	public static void genAnimal(int diff)
	{
		//temp
		for(int i=0;i<diff;++i) {
			Pair tmp = RandomGenerator.randomIndex();
			while(tmp.distance(aniData.get(0).index)<=5 || MapHolder.trap.contains(tmp)) {
				tmp = RandomGenerator.randomIndex();
			}
			System.out.println("wolf#"+(i+1)+" : "+tmp);
			Wolf w = new Wolf(tmp, 1, 1, 0,inverse);
			aniData.add(w);
		}
	}
	public void add(Animal animal)
	{
		
	}
	public void remove(Animal animal)
	{
		
	}
	
	
}