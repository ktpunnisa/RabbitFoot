package ui;

import game.GameState;
import item.Antidote;
import item.FartBomb;
import item.Item;
import item.Shit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class UIBar extends Group{
	public static Rectangle itemView;
	public UIBar()
	{
		itemView = new Rectangle(80, 100);
		itemView.setFill(Color.TRANSPARENT);
		itemView.setStroke(Color.BROWN);
		itemView.setStrokeWidth(3);
		this.getChildren().add(itemView);
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
				itemView.setFill(new ImagePattern(((Antidote)i).getImage(),0,0,1,1,true));
			}
			else if(i instanceof FartBomb)
			{
				itemView.setFill(new ImagePattern(((FartBomb)i).getImage(),0,0,1,1,true));
			}
			else if(i instanceof Shit)
			{
				itemView.setFill(new ImagePattern(((Shit)i).getImage(),0,0,1,1,true));
			}
		}
	}
}
