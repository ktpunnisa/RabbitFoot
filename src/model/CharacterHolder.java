package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CharacterHolder {
	private List<Animal> entities;
	private Comparator<Animal> comparator;
	public CharacterHolder()
	{
		comparator = (Animal o1, Animal o2) -> {
			if(o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	public void genAnimal(int diff)
	{
		entities = new ArrayList<Animal>();
	}
	public void add(Animal animal)
	{
		entities.add(animal);
		Collections.sort(entities, comparator);
	}
	public void update()
	{
		//entities.remove(i);
	}
}
