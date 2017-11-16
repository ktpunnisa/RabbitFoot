package model;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import utility.Pair;

public class Rabbit extends Animal {
	public Rectangle ob = new Rectangle(30, 30);
	public SequentialTransition sq = new SequentialTransition();
	public Animal thisRabbit = this;
	public Rabbit(int x,int y) 
	{
		this.index = new Pair(x,y);
		ob.setTranslateX(MapHolder.map[index.getY()][index.getX()].position.getX()-ob.getWidth()/2);
		ob.setTranslateY(MapHolder.map[index.getY()][index.getX()].position.getY()-ob.getHeight()/2);
		sq.setCycleCount(1);
		sq.setOnFinished(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event){
		          sq.getChildren().clear();
		          Path path = new Path(); 
			      MoveTo moveTo = new MoveTo(ob.getTranslateX() + ob.getWidth()/2, ob.getTranslateY()+ ob.getHeight()/2);
			      Pair nextIndex = new Pair(nextBlock().getX(),nextBlock().getY());
			      Point2D nextPoint = MapHolder.map[nextBlock().getY()][nextBlock().getX()].position;
			      LineTo lineTo = new LineTo(nextPoint.getX(), nextPoint.getY());
			      path.getElements().add(moveTo); 
			      path.getElements().add(lineTo);        
			      PathTransition pathTransition = new PathTransition();
			      pathTransition.setDuration(Duration.millis(1000));
			      pathTransition.setNode(ob); 
			      pathTransition.setPath(path);  
			      pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
			      pathTransition.setCycleCount(1);
			      pathTransition.setAutoReverse(false); 
		          sq.getChildren().add(pathTransition);
		          if(!sq.getChildren().isEmpty())
		        		  sq.play();
		          setIndex(nextIndex);
		          MapHolder.map[nextIndex.getY()][nextIndex.getX()].runEvent(thisRabbit);
		    }
		});
	    sq.play();
	}
	
	public void move()
	{
		
	}
	public Pair nextBlock() {
		return MapHolder.map[index.getY()][index.getX()].coor[this.direction];
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
}
