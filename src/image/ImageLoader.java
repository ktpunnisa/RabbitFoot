package image;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class ImageLoader 
{
	public static int WOLF_SIZE = 60;
	public static int RABBIT_SIZE = 40;
	public static List<Image> Wimg;
	public static List<Image> WimgInv;
	public static List<Image> WimgInv2s;
	public static List<Image> WimgStun;
	
	public static List<Image> Rimg;
	public static List<Image> RimgInv;
	public static List<Image> RimgInv2s;
	public static List<Image> RimgInvis;
	
	public static ImagePattern InvisBlock;
	public static Image InvisImage;
	
	public static ImagePattern BombBlock;
	public static Image BombImage;
	
	public static ImagePattern SpeedBlock;
	public static Image SpeedImage;
	
	public static ImagePattern jumpBlock;
	public static ImagePattern normalBlock;
	public static ImagePattern normalBlockCarrot;
	public static ImagePattern normalBlockPotion;
	public static ImagePattern trapBlock;
	
	public static void initailize() 
	{
		Wimg = new ArrayList<>();
		WimgInv = new ArrayList<>();
		WimgInv2s = new ArrayList<>();
		WimgStun = new ArrayList<>();
		
		Rimg = new ArrayList<>();
		RimgInv = new ArrayList<>();
		RimgInv2s = new ArrayList<>();
		RimgInvis = new ArrayList<>();
		
		for (int i = 1; i <= 4; i++) 
		{
		    	Wimg.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolf_"+i+".png"),WOLF_SIZE,WOLF_SIZE,false,false));
		    	WimgInv.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolfInverse_"+i+".png"),WOLF_SIZE,WOLF_SIZE,false,false));
		    	WimgInv2s.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolf2s_"+i+".png"),WOLF_SIZE,WOLF_SIZE,false,false));
		    	if(i<=2)
		    		WimgStun.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolfstun_"+i+".png"),WOLF_SIZE,WOLF_SIZE,false,false));
	    }
		
		for (int i = 1; i <= 4; i++) 
		{
			Rimg.add(new Image(ClassLoader.getSystemResourceAsStream("character/rabbit_"+i+".png"),RABBIT_SIZE,RABBIT_SIZE,false,false));
			RimgInv.add(new Image(ClassLoader.getSystemResourceAsStream("character/rabbitInverse_"+i+".png"),RABBIT_SIZE,RABBIT_SIZE,false,false));
			RimgInv2s.add(new Image(ClassLoader.getSystemResourceAsStream("character/rabbit2s_"+i+".png"),RABBIT_SIZE,RABBIT_SIZE,false,false));
			RimgInvis.add(new Image(ClassLoader.getSystemResourceAsStream("character/rabbitIm_"+i+".png"),RABBIT_SIZE,RABBIT_SIZE,false,false));
	    }
		
		InvisBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/Invis.png")),0,0,1,1,true);
		InvisImage = new Image(ClassLoader.getSystemResourceAsStream("item/Invis.png"));
		
		BombBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/IBomb.png")),0,0,1,1,true);
		BombImage = new Image(ClassLoader.getSystemResourceAsStream("item/IBomb.png"));
		
		SpeedBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/Speed.png")),0,0,1,1,true);
		SpeedImage = new Image(ClassLoader.getSystemResourceAsStream("item/Speed.png"));
		
		jumpBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/jump.png")),0,0,1,1,true);
		normalBlock=new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/grass.png")),0,0,1,1,true);
		normalBlockCarrot=new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/carrot.png")),0,0,1,1,true);
		normalBlockPotion=new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/potion.png")),0,0,1,1,true);
		trapBlock=new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/trap.png")),0,0,1,1,true);
	}
}
