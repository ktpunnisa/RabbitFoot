package ui;

import game.GameState;
import item.Antidote;
import item.FartBomb;
import item.Item;
import item.Shit;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import scene.SceneManager;
import javafx.scene.text.Font;

public class UIBar extends Group{
	public static Rectangle itemView;
	public static Text score;
	public static Text time;
	Font font = Font.loadFont("file:res/fonts/8bit.ttf", 35);
	public UIBar()
	{
		itemView = new Rectangle(10,10,80,100);
		itemView.setFill(Color.WHITESMOKE);
		itemView.setStroke(Color.valueOf("504200"));
		itemView.setStrokeWidth(5);
		this.getChildren().add(itemView);
		
		ImageView board = new ImageView(new Image("file:res/ui/board.png"));
		board.setTranslateX(570);
		board.setTranslateY(-10);
		this.getChildren().add(board);
		score=new Text("Score:");
		score.setFont(font);
		score.setFill(Color.WHITE);
		score.setTranslateX(580);
		score.setTranslateY(70);
		this.getChildren().add(score);
		time=new Text("Time:");
		time.setFont(font);
		time.setFill(Color.WHITE);
		time.setTranslateX(580);
		time.setTranslateY(120);
		this.getChildren().add(time);
	}
	public static void changeItemView(Item i)
	{
		if(i == null)
		{
			Platform.runLater(() -> itemView.setFill(Color.WHITESMOKE));
		}
		else
		{
			if(i instanceof Antidote) 
			{
				Platform.runLater(() -> itemView.setFill(new ImagePattern(((Antidote)i).getItemImage(),0,0,1,1,true)));
			}
			else if(i instanceof FartBomb)
			{
				Platform.runLater(() -> itemView.setFill(new ImagePattern(((FartBomb)i).getItemImage(),0,0,1,1,true)));
			}
			else if(i instanceof Shit)
			{
				Platform.runLater(() -> itemView.setFill(new ImagePattern(((Shit)i).getItemImage(),0,0,1,1,true)));
			}
		}
	}
	public static void addTimeBar(int sec)
	{
		Rectangle tb = new Rectangle(500,30);
		tb.setTranslateX(150);
		SceneManager.mainFrame.getChildren().add(tb);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, 
				new KeyValue (tb.widthProperty(), 500, Interpolator.LINEAR)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(sec), 
				new KeyValue (tb.widthProperty(), 0, Interpolator.LINEAR)));
		
		timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, 
				new KeyValue (tb.translateYProperty(), -20, Interpolator.EASE_BOTH)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), 
				new KeyValue (tb.translateYProperty(), 40, Interpolator.EASE_BOTH)));
		
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(sec),
					ae -> {
						SceneManager.mainFrame.getChildren().remove(tb);
					}));
		timeline.setCycleCount(1);
		timeline.play();
	}
}
