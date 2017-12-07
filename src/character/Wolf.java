package character;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;


import java.util.PriorityQueue;
import java.util.Set;

import game.GameLogic;
import game.GameMain;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import map.JumpBlock;
import map.MapHolder;
import map.NormalBlock;
import map.TrapBlock;
import scene.SceneManager;
import ui.UIGame;
import utility.MyNode;
import utility.Pair;

public class Wolf extends Animal{
	
	public static int WOLF_SIZE = 60;
	public Wolf(Pair index, double speed, int direction, int z,boolean inverse) {
		super(index, speed, direction, z,inverse);
	    for (int i = 1; i <= 4; i++) {
	    	img.add(new Image("file:res/character/wolf_"+i+".png",WOLF_SIZE,WOLF_SIZE,false,false));
	    	imgInv.add(new Image("file:res/character/wolfInverse_"+i+".png",WOLF_SIZE,WOLF_SIZE,false,false));
	    	imgInv2s.add(new Image("file:res/character/wolf2s_"+i+".png",WOLF_SIZE,WOLF_SIZE,false,false));
	    }
	  	body.setImage(img.get(0));
	  	body.setTranslateX(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getX()-WOLF_SIZE/2);
	  	body.setTranslateY(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getY()-WOLF_SIZE/2);
	  	runPath.add(nextBlock());
		//startRunning();
	}

	@Override
	public void startRunning() {
		isRunning = true;
	  	animationThread = new Thread(this::animateLoop, "Wolf animating Thread");
		runThread = new Thread(this::runLoop, "Wolf running Thread");
		Thread moveThread = new Thread(this::moveLoop, "Wolf move Thread");
		animationThread.start();
		runThread.start();
		moveThread.start();
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
						Platform.runLater(() -> body.setImage(imgInv.get(t%4)));
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
		long lastRunTime = System.currentTimeMillis();
		long runTime = (long) (500 * speed);
		while (isRunning) {
			long now = System.currentTimeMillis();
			if (now - lastRunTime >= runTime) {
				lastRunTime += runTime;
				System.out.println("Wolf @ "+index);
				if(!runPath.isEmpty())
					index = runPath.remove();
				try {
					runPath.add(nextBlock());
				} catch(Exception e) {
					System.out.println("Wolf went wrong");
				}
				if(!runPath.isEmpty())
					MapHolder.mapData.get(runPath.peek().getY()).get(runPath.peek().getX()).checkEvent(this);
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	public void moveLoop() {
		Pair prev = index;
		while (isRunning) {
			/*if(prev!=runPath.peek() && runPath!=null) {
				Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				if(MapHolder.mapData == null) System.out.println("fasdfffffffff");
				Point2D t = MapHolder.mapData.get(runPath.peek().getY()).get(runPath.peek().getX()).position;
				System.out.println(t+ " "+UIGame.globalMap.localToScene(t) );
				Point2D tt = UIGame.globalMap.localToScene(t);
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (body.translateXProperty(), tt.getX())));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (body.translateYProperty(), tt.getY())));
				Platform.runLater(() -> timeline.play());
				prev = runPath.peek();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
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
		Pair bestBlock = null;
		Boolean seeTrap = true;
		/*if(index.distance(r)>=5){
			seeTrap = true;
		}*/
		//if(!seeTrap)	System.out.println("not see trap");
		if(MapHolder.mapData.get(r.getY()).get(r.getX()) instanceof JumpBlock ||
				MapHolder.mapData.get(r.getY()).get(r.getX()) instanceof TrapBlock ) {
			return bestBlock = lowNextBlock();
		}
		PriorityQueue<MyNode> q = new PriorityQueue<MyNode>(); 
		Set<Pair> mark = new HashSet<Pair>();
		Map<Pair,MyNode> ans = new HashMap<>();	
		q.add(new MyNode(index,index.distance(r)));
		ans.put(index, new MyNode(index,0));
		while(!q.isEmpty()) {
			MyNode w = q.poll();
			//System.out.println(w.index);
			if(mark.contains(w.index)) {
				continue;
			}
			mark.add(w.index);
			if(w.index.equals(r)) {
				break;
			}
			for(int i = 0; i < 6; i++) {
				Pair nextW = MapHolder.mapData.get(w.index.getY()).get(w.index.getX()).nextBlock[i];
				if(nextW != null && !mark.contains(nextW)) {
					if(MapHolder.mapData.get(nextW.getY()).get(nextW.getX()) instanceof NormalBlock ||
							(MapHolder.mapData.get(nextW.getY()).get(nextW.getX()) instanceof TrapBlock && !seeTrap)) {
						int dis = nextW.distance(r);
						if(ans.containsKey(nextW) && (ans.get(w.index).dis+1 < ans.get(nextW).dis)){
							ans.replace(nextW, new MyNode(w.index,ans.get(w.index).dis+1));
							q.add(new MyNode(nextW,dis));
							//System.out.println(nextW+"(1):"+ans.get(nextW));
						}
						if(!ans.containsKey(nextW)){
							ans.put(nextW, new MyNode(w.index,ans.get(w.index).dis+1));
							q.add(new MyNode(nextW,dis));
							//System.out.println(nextW+"(2):"+ans.get(nextW));
						}
					}
				}
			}
		}
		try {
			//System.out.println("Find!");
			Pair tmp = r;
			while(ans.get(tmp).index!=index) {
				//System.out.println(ans.get(tmp).index);
				tmp = ans.get(tmp).index;
			}
			//System.out.println("best : "+tmp);
			bestBlock = tmp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lowNextBlock();
		}
		
		if(inverse) {
			Pair tmp = bestBlock;
			for(int i=0;i<6;i++) {
				Pair nextBlock = MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[i];
				if(nextBlock == null)	continue;
				if(MapHolder.typeBlock[nextBlock.getY()][nextBlock.getX()] !=3)	continue;				
				if(MapHolder.mapData.get(nextBlock.getY()).get(nextBlock.getX()) instanceof NormalBlock ||
							(MapHolder.mapData.get(nextBlock.getY()).get(nextBlock.getX()) instanceof TrapBlock && !seeTrap)) {
					if(nextBlock.distance(bestBlock) > tmp.distance(bestBlock)){
						tmp = nextBlock;
					}
					else if(nextBlock.distance(bestBlock) == tmp.distance(bestBlock) && nextBlock.distance(r) > tmp.distance(r)) {
						tmp = nextBlock;
					}
				}
			}
			bestBlock = tmp;
		}	
		return bestBlock;
	}
	
	Pair lowNextBlock() {
		Pair r = CharacterHolder.aniData.get(0).getIndex();
		Pair bestBlock = null;
		int min = 100;
		for(int i = 0; i < 6; i++) {
			Pair nextW = MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[i];
			if(nextW != null) {
				if(MapHolder.mapData.get(nextW.getY()).get(nextW.getX()) instanceof NormalBlock) {
					int dis = nextW.distance(r);
					if(dis < min) {
						bestBlock = nextW;
						min = dis;
					}
				}
			}
		}
		return bestBlock;
	}
}

//Pair nextIndex = new Pair(nextBlock().getX(),nextBlock().getY());
/*sq.setCycleCount(1);
sq.setOnFinished(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event){
          sq.getChildren().clear();
          if(!GameLogic.isGameRunning) return;
          Path path = new Path(); 
	      MoveTo moveTo = new MoveTo(body.getTranslateX() + WOLF_SIZE/2, body.getTranslateY()+ WOLF_SIZE/2);
	      Pair nextIndex = new Pair(nextBlock().getX(),nextBlock().getY());
	      if(nextIndex.equals(getIndex())) return;
	      Point2D nextPoint = MapHolder.mapData.get(nextBlock().getY()).get(nextBlock().getX()).position;
	      LineTo lineTo = new LineTo(nextPoint.getX(), nextPoint.getY());
	      path.getElements().add(moveTo); 
	      path.getElements().add(lineTo);        
	      PathTransition pathTransition = new PathTransition();
	      pathTransition.setDuration(Duration.millis(1000*speed));
	      pathTransition.setNode(body); 
	      pathTransition.setPath(path);  
	      pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
	      pathTransition.setCycleCount(1);
	      pathTransition.setAutoReverse(false); 
          sq.getChildren().add(pathTransition);
          if(!sq.getChildren().isEmpty()) {
        	  	  Platform.runLater(() -> {
        	  		  sq.play();
        	  		  runLoop(true);
        	  	  });
          }
          setIndex(nextIndex);
          MapHolder.mapData.get(nextIndex.getY()).get(nextIndex.getX()).checkEvent(instance);
    }
});
Platform.runLater(() -> sq.play());*/
