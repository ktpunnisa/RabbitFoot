package character;

import game.GameCamera;
import game.GameLogic;
import game.GameMain;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import map.JumpBlock;
import map.MapHolder;
import scene.SceneManager;
import ui.UIGame;
import utility.Pair;

public class Rabbit extends Animal {
	
	public static int RABBIT_SIZE = 40;
	public Rabbit instance;
	int id = 0;
	
	public Rabbit(Pair index, double speed, int direction, int z,boolean inverse) {
		super(index, speed, direction, z,inverse);
		this.instance=this;
		for (int i = 1; i <= 4; i++) {
			img.add(new Image("file:res/character/rabbit_"+i+".png",RABBIT_SIZE,RABBIT_SIZE,false,false));
	    }
		Platform.runLater(() -> body.setImage(img.get(0)));
		Platform.runLater(() -> body.setTranslateX(SceneManager.SCENE_WIDTH/2-RABBIT_SIZE/2));
		Platform.runLater(() -> body.setTranslateY(SceneManager.SCENE_HEIGHT/2-RABBIT_SIZE/2));
		runPath.add(nextBlock());
	}

	@Override
	public void startRunning() {
		Point2D t = MapHolder.mapData.get(index.getY()).get(index.getX()).position;
		Point2D nodeInScene = GameMain.gameUI.localToScene(t);
		UIGame.globalMap.setTranslateX(SceneManager.SCENE_WIDTH/2 - nodeInScene.getX());
		UIGame.globalMap.setTranslateY(SceneManager.SCENE_HEIGHT/2 - nodeInScene.getY());
		isRunning = true;
		animationThread = new Thread(this::animateLoop, "Rabbit animating Thread");
		runThread = new Thread(this::runLoop, "Rabbit running Thread");
		animationThread.start();
		runThread.start();
	}

	@Override
	public void stopRunning() {
		isRunning = false;
	}

	@Override
	public void animateLoop() {
		long lastAnimateTime = System.currentTimeMillis();
		long animateTime = (long) (100 * speed);
		int i = 0;
		while (isRunning) {
			long now = System.currentTimeMillis();
			if (now - lastAnimateTime >= animateTime) {
				lastAnimateTime += animateTime;
				int t=i++;
				if(inverse) {
					if((CharacterHolder.timeInverse+15) - GameLogic.seconds <=2) {
						Platform.runLater(() -> body.setImage(imgInv2s.get(t%4)));
					}
					else {
						Platform.runLater(() -> body.setImage(imgInv.get(t%2)));
					}
				}
				else {
					Platform.runLater(() -> body.setImage(img.get(t%4)));
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void runLoop() {
		while (isRunning) {
			if(!runPath.isEmpty()) {
				Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				Point2D t = MapHolder.mapData.get(runPath.peek().getY()).get(runPath.peek().getX()).position;
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (UIGame.globalMap.translateXProperty(), SceneManager.SCENE_WIDTH/2 - t.getX(), Interpolator.EASE_BOTH)));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (UIGame.globalMap.translateYProperty(), SceneManager.SCENE_HEIGHT/2 - t.getY(), Interpolator.EASE_BOTH)));

				timeline.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(nextBlock() != null)
							runPath.add(nextBlock());
						else {
							GameMain.stopGame();
						}
						if(!runPath.isEmpty())
							MapHolder.mapData.get(runPath.peek().getY()).get(runPath.peek().getX()).checkEvent(instance);
					}
				});
				Platform.runLater(() -> timeline.play());
				index = runPath.poll();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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

/*sq.setCycleCount(1);
sq.setOnFinished(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event){
          sq.getChildren().clear();
          
          if(!GameLogic.isGameRunning) return;
          if(nextBlock()==null) {GameMain.stopGame();}
          
          //System.out.println("Rabit : "+ nextBlock());
          
          Path path = new Path(); 
	      MoveTo moveTo = new MoveTo(body.getTranslateX() + RABBIT_SIZE/2, body.getTranslateY() + RABBIT_SIZE/2);
	      nextIndex = new Pair(nextBlock().getX(),nextBlock().getY());
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
          MapHolder.mapData.get(nextIndex.getY()).get(nextIndex.getX()).checkEvent(instance);
    }
});
Platform.runLater(() -> sq.play());*/
