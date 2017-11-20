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
	
	public CharacterHolder()
	{
		aniData = FXCollections.<Animal>observableArrayList();
		Rabbit r = new Rabbit(new Pair(5,12), 1.2, 1, 0);
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
		/*for(int i=0;i<diff;++i) {
			Pair tmp = RandomGenerator.randomIndex();
			while(tmp.distance(aniData.get(0).index)<=5) {
				tmp = RandomGenerator.randomIndex();
			}
			Wolf w = new Wolf(tmp, 1, 1, 0);
			aniData.add(w);
		}*/
	}
	public void add(Animal animal)
	{
		
	}
	public void remove(Animal animal)
	{
		
	}
}