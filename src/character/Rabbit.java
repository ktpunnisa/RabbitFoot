package character;

import application.Main;
import game.GameMain;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import map.JumpBlock;
import map.MapHolder;
import utility.Pair;

public class Rabbit extends Animal {
	int id = 0;
	public Rabbit(Pair index, double speed, int direction, int z) {
		super(index, speed, direction, z);
		startRunning();
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
			      if(MapHolder.mapData.get(index.getY()).get(index.getX()) instanceof JumpBlock &&
			    		  MapHolder.mapData.get(nextIndex.getY()).get(nextIndex.getX()) instanceof JumpBlock) {
			    	  pathTransition.setDuration(Duration.millis(1000*speed));
			      }
			      else {
			    	  pathTransition.setDuration(Duration.millis(1000));
			      }
			      pathTransition.setNode(body); 
			      pathTransition.setPath(path);  
			      pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
			      pathTransition.setCycleCount(1);
			      pathTransition.setAutoReverse(false); 
		          sq.getChildren().add(pathTransition);
		          if(!sq.getChildren().isEmpty()) {
		        	  	  Platform.runLater(() -> {
		        	  		    sq.play();
		        	          if(MapHolder.mapData.get(index.getY()).get(index.getX()) instanceof JumpBlock &&
				    		            MapHolder.mapData.get(nextIndex.getY()).get(nextIndex.getX()) instanceof JumpBlock) {
		        		              runLoop(true);
				                }
		        	          else {
		        		              runLoop(false);
		        	          }
		        	  	  });
		          }
		          setIndex(nextIndex);
		          MapHolder.mapData.get(nextIndex.getY()).get(nextIndex.getX()).checkEvent();
		    }
		});
		Platform.runLater(() -> sq.play());
	    startRunning();
	}

	@Override
	public void startRunning() {
		// TODO Auto-generated method stub
		isRunning = true;
		for (int i = 1; i <= 4; i++) {
			img.add(new Image("file:res/character/rabbit_"+i+".png"));
	    }
		body.setImage(img.get(0));
		body.setTranslateX(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getX()-20);
		body.setTranslateY(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getY()-20);
	}

	@Override
	public void stopRunning() {
		// TODO Auto-generated method stub
		isRunning = false;
	}

	@Override
	public void runLoop(boolean jumpNow) {
		// TODO Auto-generated method stub
		double[] sleepJump = {0.1,0.1,0.6,0.1};
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 4; i++) {
						updateRabbit(i);
					try {
						if(jumpNow) {
							Thread.sleep((long) ((1000*speed)*sleepJump[i]));
						}
						else {
							Thread.sleep((long) ((1000)/4));
						}
					} catch (Exception e) {
						System.out.println("Some error occured!!! Thread cannot sleep");
						e.printStackTrace();
					}
				}
			}	
		}).start();
	}
	
	private void updateRabbit(int i)
	{
		Platform.runLater(() -> body.setImage(img.get(i)));
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pair nextBlock() {
		if(MapHolder.mapData.get(index.getY()).get(index.getX()) instanceof JumpBlock) {
			//System.out.println("JumpBox"+index);
			int jumpDi = ((JumpBlock)MapHolder.mapData.get(index.getY()).get(index.getX())).direction;
			//System.out.println("DiR:"+Integer.toString(direction)+" DiJ"+Integer.toString(jumpDi));
			if(Math.abs(this.direction - jumpDi)==3) {
				return MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[direction];
			}
			this.direction = jumpDi;
			return ((JumpBlock)MapHolder.mapData.get(index.getY()).get(index.getX())).jumpTo;
		}
		return MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[direction];
	}

	
	

}
