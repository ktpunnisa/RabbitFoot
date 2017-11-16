package model;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import utility.Pair;

public class Rabbit extends Animal {
	public Rectangle ob = new Rectangle(30, 30);
	public SequentialTransition sq = new SequentialTransition();
	public Rabbit(int x,int y) 
	{
		position = new Pair(x,y);
		ob.setTranslateX(MapHolder.map[position.getX()][position.getY()].position.getX()-ob.getWidth()/2);
		ob.setTranslateY(MapHolder.map[position.getX()][position.getY()].position.getY()-ob.getHeight()/2);
		sq.setCycleCount(1);
		sq.setOnFinished(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event){
		          sq.getChildren().clear();
		          Path path2 = new Path(); 
			      MoveTo moveTo2 = new MoveTo(ob.getTranslateX() + ob.getWidth()/2, ob.getTranslateY()+ ob.getHeight()/2); 
			      LineTo a = new LineTo(ob.getTranslateX() + ob.getWidth()/2 + dx[direction], ob.getTranslateY() + ob.getHeight()/2+dy[direction]); 
			      path2.getElements().add(moveTo2); 
			      path2.getElements().add(a);        
			      PathTransition pathTransition2 = new PathTransition();
			      pathTransition2.setDuration(Duration.millis(100));
			      pathTransition2.setNode(ob); 
			      pathTransition2.setPath(path2);  
			      pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
			      pathTransition2.setCycleCount(1);
			      pathTransition2.setAutoReverse(false); 
		          sq.getChildren().add(pathTransition2);
		          if(!sq.getChildren().isEmpty())
		        		  sq.play();
		    }
		});
	    sq.play();
	}
	
	public void move()
	{
		
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
