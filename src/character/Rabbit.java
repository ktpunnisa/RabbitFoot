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
import utility.Pair;

public class Rabbit extends Animal {

	public Rabbit(Pair index, int speed, int direction, int z) {
		super(index, speed, direction, z);
		Image rabbit = new Image("file:res/animal/rabbit_1.png", 40, 40, false, false);
		body.setImage(rabbit);
		body.setTranslateX(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getX()-20);
		body.setTranslateY(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getY()-20);
		sq.setCycleCount(1);
		sq.setOnFinished(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event){
		          sq.getChildren().clear();
		          if(nextBlock()==null) {GameMain.gameOver();}
		          Path path = new Path(); 
			      MoveTo moveTo = new MoveTo(body.getTranslateX() + 20, body.getTranslateY()+ 20);
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
		return MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[direction];
	}
	

}
