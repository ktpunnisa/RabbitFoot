package map;

import character.Animal;
import character.CharacterHolder;
import character.Wolf;
import game.GameMain;
import game.GameState;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class TrapBlock extends Block{
	int i;
	public TrapBlock(int x, int y, int c) {
		super(x, y, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		Image img = new Image("file:res/block/trap.png");
		this.hexagon.setFill(new ImagePattern(img,0,0,1,1,true));
		//this.hexagon.setFill(Color.ORANGE);
		this.hexagon.setStrokeWidth(3);
		this.hexagon.setStroke(Color.BLACK);
	}

	@Override
	public void checkEvent(Animal animal) {
		if(animal instanceof Wolf) {
			
		}
		
	}
}
/*if(index.distance(CharacterHolder.aniData.get(0).index) >=10) {
	MapHolder.deleteTrap(index);
	MapHolder.createTrap();
}
if(index.equals((CharacterHolder.aniData.get(0).index))){
	new Thread(new Runnable() {
		@Override
		public void run() {
			try { 
				Thread.sleep(1000);
				//System.out.println(Integer.toString(GameState.score));
				GameMain.gameOver();
			} catch (Exception e) {
				System.out.println("Some error occured!!! can't die");
				e.printStackTrace();
			}
		}	
	}).start();
}
int size = CharacterHolder.aniData.size();
for(i = size-1;i>=1;i--) {
	if(index.equals((CharacterHolder.aniData.get(i).index))) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try { 
					Thread.sleep(1000);
					//System.out.println(Integer.toString(GameState.score));
					CharacterHolder.aniData.remove(i);
				} catch (Exception e) {
					System.out.println("Some error occured!!! can't die");
					e.printStackTrace();
				}
			}	
		}).start();
	}
}*/