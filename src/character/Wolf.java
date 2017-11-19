package character;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;


import java.util.PriorityQueue;
import java.util.Set;

import game.GameMain;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
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
import utility.Pair;

class MyNode implements Comparable<MyNode>{
	Pair index;
	int dis;
	public MyNode(Pair index,int dis) {
		this.index = index;
		this.dis = dis;
	}
	@Override
	public int compareTo(MyNode o){
		if(this.getClass() ==  o.getClass()) {
			 return this.dis - o.dis;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return index+"("+Integer.toString(dis)+")";
	}
	
}
public class Wolf extends Animal{

	int id = 0;
	
	public Wolf(Pair index, double speed, int direction, int z) {
		super(index, speed, direction, z);
		// TODO Auto-generated constructor stub
		startRunning();
		//Pair nextIndex = new Pair(nextBlock().getX(),nextBlock().getY());
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
			      pathTransition.setDuration(Duration.millis(1000*speed));
			      pathTransition.setNode(body); 
			      pathTransition.setPath(path);  
			     // pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
			      pathTransition.setCycleCount(1);
			      pathTransition.setAutoReverse(false); 
		          sq.getChildren().add(pathTransition);
		          if(!sq.getChildren().isEmpty()) {
		        		  sq.play();
		        		  runLoop(true);
		          }
		          setIndex(nextIndex);
		          MapHolder.mapData.get(nextIndex.getY()).get(nextIndex.getX()).checkEvent();
		    }
		});
	    sq.play();
	}

	@Override
	public void startRunning() {
		// TODO Auto-generated method stub
		isRunning = true;
	    for (int i = 1; i <= 4; i++) {
	    	img.add(new Image("file:res/character/wolf_"+i+".png"));
	    }
	    //Image wolf = new Image("file:res/animal/wolf_1.png", 60, 60, false, false);
	  	body.setImage(img.get(0));
	  	body.setTranslateX(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getX()-30);
	  	body.setTranslateY(MapHolder.mapData.get(index.getY()).get(index.getX()).position.getY()-30);
	}

	@Override
	public void stopRunning() {
		// TODO Auto-generated method stub
		isRunning = false;
	}

	@Override
	public void runLoop(boolean x) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 4; i++) {
					body.setImage(img.get(i));
					try {
						Thread.sleep((long) ((1000*speed)/4));
					} catch (Exception e) {
						System.out.println("Some error occured!!! Thread cannot sleep");
						e.printStackTrace();
					}
				}
			}	
		}).start();
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
		if(MapHolder.mapData.get(r.getY()).get(r.getX()) instanceof JumpBlock) {
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
					if(MapHolder.mapData.get(nextW.getY()).get(nextW.getX()) instanceof NormalBlock) {
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
