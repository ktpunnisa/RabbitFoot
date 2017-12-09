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
import game.GameState;
import javafx.animation.Interpolator;
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
import utility.RandomGenerator;

public class Wolf extends Animal{
	
	public static int WOLF_SIZE = 60;
	public Wolf instance;
	private boolean isStun;
	private Pair gotoThis;
	
	public Wolf(Pair index, double speed, int direction, int z,boolean inverse) {
		super(index, speed, direction, z,inverse);
		this.instance = this;
		this.isStun = false;
		gotoThis = RandomGenerator.randomIndex();
	    for (int i = 1; i <= 4; i++) {
		    	img.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolf_"+i+".png"),WOLF_SIZE,WOLF_SIZE,false,false));
		    	imgInv.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolfInverse_"+i+".png"),WOLF_SIZE,WOLF_SIZE,false,false));
		    	imgInv2s.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolf2s_"+i+".png"),WOLF_SIZE,WOLF_SIZE,false,false));
	    }
	  	runPath.add(nextBlock());
	}

	@Override
	public void startRunning() {
	    Point2D a = MapHolder.mapData.get(index.getY()).get(index.getX()).position;
	    Platform.runLater(() -> body.setImage(img.get(0)));
	    Platform.runLater(() -> body.setTranslateX(a.getX()-WOLF_SIZE/2));
	    Platform.runLater(() -> body.setTranslateY(a.getY()-WOLF_SIZE/2));
		isRunning = true;
	  	animationThread = new Thread(this::animateLoop, "Wolf animating Thread");
		runThread = new Thread(this::runLoop, "Wolf running Thread");
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
		while (isRunning) {
			if(!runPath.isEmpty()) {
				Timeline timeline = new Timeline();
				int i=0;
				for(i=0;i<6;++i) {
					if(MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[i]!=null)
						if(MapHolder.mapData.get(index.getY()).get(index.getX()).nextBlock[i].equals(MapHolder.mapData.get(runPath.peek().getY()).get(runPath.peek().getX()).index))
							break;
				}
				if(i==6)	i=this.direction;	
				setAngle(i);
				this.direction = i;
				timeline.setCycleCount(1);
				Point2D a = MapHolder.mapData.get(runPath.peek().getY()).get(runPath.peek().getX()).position;
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (body.translateXProperty(), a.getX()-WOLF_SIZE/2, Interpolator.EASE_BOTH)));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (body.translateYProperty(), a.getY()-WOLF_SIZE/2, Interpolator.EASE_BOTH)));
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500 * speed), 
						new KeyValue (body.rotateProperty(), angle, Interpolator.EASE_BOTH)));
				timeline.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(nextBlock() != null)
							runPath.add(nextBlock());
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
		// TODO Auto-generated method stub
		//System.out.println("fox : "+Integer.toString(index.getX())+","+Integer.toString(index.getY()));
		Pair r = CharacterHolder.aniData.get(0).getIndex();
		Pair bestBlock = null;
		Boolean seeTrap = true;
		if(GameState.isImmortal) {
			r = gotoThis;
			while(r.distance(index)==0){
				gotoThis = RandomGenerator.randomIndex();
				r = gotoThis;
			}
		}
		if(isStun) {
			return index;
		}
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

	public boolean isStun() {
		return isStun;
	}

	public void setStun(boolean isStun) {
		this.isStun = isStun;
	}
}
