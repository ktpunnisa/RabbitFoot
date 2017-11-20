package map;

import character.Animal;
import character.CharacterHolder;
import game.GameState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class NormalBlock extends Block{

	Boolean hasCarrot;
	public NormalBlock(int x, int y, int c) {
		super(x, y, c);
		hasCarrot = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		Image img;
		if(hasCarrot) {
			img = new Image("file:res/block/carrot.png");
		}
		else {
			img = new Image("file:res/block/grass.png");
		}
		this.hexagon.setFill(new ImagePattern(img,0,0,1,1,true));
		//this.hexagon.setFill(Color.ORANGE);
		this.hexagon.setStrokeWidth(3);
		this.hexagon.setStroke(Color.BLACK);
	}

	@Override
	public void checkEvent() {
		// TODO Auto-generated method stub
		if(this.index.equals(CharacterHolder.aniData.get(0).index) && hasCarrot) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//System.out.println("eat me!");
						setHasCarrot(false);
						MapHolder.carrot.remove(index);
						MapHolder.createCarrot(); 
						Thread.sleep(1000);
						GameState.score++;
						//System.out.println(Integer.toString(GameState.score));
						MapHolder.mapData.get(index.getY()).get(index.getX()).loadImage();
					} catch (Exception e) {
						System.out.println("Some error occured!!! can't eat");
						e.printStackTrace();
					}
				}	
			}).start();
			
			
		}
		
		
	}

	public Boolean getHasCarrot() {
		return hasCarrot;
	}

	public void setHasCarrot(Boolean hasCarrot) {
		this.hasCarrot = hasCarrot;
		
	}
	

}
