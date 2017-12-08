package ui;

import game.GameState;
import item.Antidote;
import item.FartBomb;
import item.Item;
import item.Shit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.text.Font;

public class UIBar extends Group{
	public static Rectangle itemView;
	public static Text score;
	public static Text time;
	Font font = Font.loadFont("file:res/fonts/8bit.ttf", 30);
	public UIBar()
	{
		itemView = new Rectangle(10,10,80,100);
		itemView.setFill(Color.TRANSPARENT);
		itemView.setStroke(Color.MAROON);
		itemView.setStrokeWidth(3);
		this.getChildren().add(itemView);
		ImageView status = new ImageView(new Image("file:res/bar.png"));
		this.getChildren().add(status);
		score=new Text("Score:");
		score.setFont(font);
		score.setFill(Color.WHITE);
		score.setStroke(Color.ORANGE);
		score.setTranslateX(400);
		score.setTranslateY(30);
		this.getChildren().add(score);
		time=new Text("Time:");
		time.setFont(font);
		time.setFill(Color.WHITE);
		time.setStroke(Color.ORANGE);
		time.setTranslateX(600);
		time.setTranslateY(30);
		this.getChildren().add(time);
	}
	public static void changeItemView(Item i)
	{
		if(i == null)
		{
			itemView.setFill(Color.TRANSPARENT);
		}
		else
		{
			if(i instanceof Antidote) 
			{
				itemView.setFill(new ImagePattern(((Antidote)i).getItemImage(),0,0,1,1,true));
			}
			else if(i instanceof FartBomb)
			{
				itemView.setFill(new ImagePattern(((FartBomb)i).getItemImage(),0,0,1,1,true));
			}
			else if(i instanceof Shit)
			{
				itemView.setFill(new ImagePattern(((Shit)i).getItemImage(),0,0,1,1,true));
			}
		}
	}
}
