package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import utility.Pair;
import view.Drawer;

public class CharacterHolder {
	public static List<Animal> entities;
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
		//temp
		Rabbit r = new Rabbit(7,5);
		r.speed=1;
		entities.add(r);
	}
	public void add(Animal animal)
	{
		entities.add(animal);
		Collections.sort(entities, comparator);
	}
	public void remove(Animal animal)
	{
		if(animal instanceof Rabbit)
			Drawer.drawedAnimal.getChildren().remove(((Rabbit)animal).ob);
		entities.remove(animal);
	}
}
