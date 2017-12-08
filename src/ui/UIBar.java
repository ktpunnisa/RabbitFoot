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
import javafx.util.Duration;

public class UIBar extends Group{
	public static Rectangle itemView;
	public static Label score;
	public UIBar()
	{
		itemView = new Rectangle(10,10,80,100);
		itemView.setFill(Color.TRANSPARENT);
		itemView.setStroke(Color.MAROON);
		itemView.setStrokeWidth(3);
		this.getChildren().add(itemView);
		ImageView status = new ImageView(new Image("file:res/bar.png"));
		this.getChildren().add(status);
		score=new Label("Score:");
		score.setFont(new Font("Arial", 30));
		score.setTextFill(Color.WHITE);
		score.setTranslateX(600);
		score.setTranslateY(10);
		this.getChildren().add(score);
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
