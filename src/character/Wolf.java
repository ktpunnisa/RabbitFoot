package character;

import game.GameMain;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import map.MapHolder;
import map.NormalBlock;
import utility.Pair;

public class Wolf extends Animal{

	public Wolf(Pair index, int speed, int direction, int z) {
		super(index, speed, direction, z);
		// TODO Auto-generated constructor stub
		Image wolf = new Image("file:res/animal/wolf_1.png", 60, 60, false, false);
		body.setImage(wolf);
		body.setTranslateX(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getX()-30);
		body.setTranslateY(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getY()-30);
		sq.setCycleCount(1);
		sq.setOnFinished(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event){
		          sq.getChildren().clear();
		          Path path = new Path(); 
			      MoveTo moveTo = new MoveTo(body.getTranslateX() + 30, body.getTranslateY()+ 30);
			      Pair nextIndex = new Pair(nextBlock().getX(),nextBlock().getY());
			      Point2D nextPoint = MapHolder.mapData.get(nextBlock().getY()).get(nextBlock().getX()).position;
			      LineTo lineTo = new LineTo(nextPoint.getX(), nextPoint.getY());
			      path.getElements().add(moveTo); 
			      path.getElements().add(lineTo);        
			      PathTransition pathTransition = new PathTransition();
			      pathTransition.setDuration(Duration.millis(1000));
			      pathTransition.setNode(body); 
			      pathTransition.setPath(path);  
			     // pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
			      pathTransition.setCycleCount(1);
			      pathTransition.setAutoReverse(false); 
		          sq.getChildren().add(pathTransition);
		          if(!sq.getChildren().isEmpty())
		        		  sq.play();
		          setIndex(nextIndex);
		          MapHolder.mapData.get(nextIndex.getY()).get(nextIndex.getX()).checkEvent();
		    }
		});
	    sq.play();
	}

	@Override
	public void startRuning() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopRuning() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runLoop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pair nextBlock() {
		// TODO Auto-generated method stub
		//System.out.println("fox : "+Integer.toString(index.getX())+","+Integer.toString(index.getY()));
		Pair r = CharacterHolder.aniData.get(0).getIndex();
		int min = 100;
		Pair bestBlock = null;
		for(int i = 0; i < 6; i++) {
			Pair nextW = MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[i];
			if(nextW != null) {
				//System.out.println("nextFox : "+Integer.toString(nextW.getX())+","+Integer.toString(nextW.getY()));
				if(MapHolder.mapData.get(nextW.getY()).get(nextW.getX()) instanceof NormalBlock) {
					int dis = Animal.distanceRW(r,nextW);
					if(dis < min) {
						bestBlock = nextW;
						min = dis;
					}
				}
			}
		}
		//System.out.println("bestBox : "+Integer.toString(bestBlock.getX())+","+Integer.toString(bestBlock.getY()));
		return bestBlock;
	}
	
	

}
