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

	public static final double RABBIT_SPEED = 1.4;
	public static final double WOLF_SPEED = 1.1;
	private static List<Animal> aniData;
	private static Group aniGroup;
	
	public CharacterHolder(int diff) {
		aniGroup = new Group();
		aniData = new ArrayList<Animal>();
		int direction = RandomGenerator.random(0, 5);
		Rabbit r = new Rabbit(RandomGenerator.generateIndexRabbit(direction), RABBIT_SPEED, direction, GameState.isInverse());
		aniData.add(r);
		add(diff);
	}

	public void add(int n) {
		for (int i = 0; i < n; ++i) {
			Wolf w = new Wolf(RandomGenerator.generateIndexWolf(), WOLF_SPEED, 1, GameState.isInverse());
			aniData.add(w);
			w.startRunning();
			Platform.runLater(() -> aniGroup.getChildren().add(w.body));
		}
	}

	public void remove(Animal animal) {
		((Wolf) animal).stopRunning();
		aniData.remove(animal);
		Platform.runLater(() -> aniGroup.getChildren().remove(animal.body));
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