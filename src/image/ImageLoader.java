package image;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class ImageLoader {
	public static final int WOLF_SIZE = 60;
	public static final int RABBIT_SIZE = 40;
	private static List<Image> wimg;
	private static List<Image> wimgInv;
	private static List<Image> wimgInv2s;
	private static List<Image> wimgStun;

	private static List<Image> rimg;
	private static List<Image> rimgInv;
	private static List<Image> rimgInv2s;
	private static List<Image> rimgInvis;

	private static ImagePattern invisBlock;
	private static Image invisImage;

	private static ImagePattern bombBlock;
	private static Image bombImage;

	private static ImagePattern speedBlock;
	private static Image speedImage;

	private static ImagePattern jumpBlock;
	private static ImagePattern normalBlock;
	private static ImagePattern normalBlockCarrot;
	private static ImagePattern normalBlockPotion;
	private static ImagePattern trapBlock;

	public static void initailize() {
		wimg = new ArrayList<>();
		wimgInv = new ArrayList<>();
		wimgInv2s = new ArrayList<>();
		wimgStun = new ArrayList<>();

		rimg = new ArrayList<>();
		rimgInv = new ArrayList<>();
		rimgInv2s = new ArrayList<>();
		rimgInvis = new ArrayList<>();

		for (int i = 1; i <= 4; i++) {
			wimg.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolf_" + i + ".png"), WOLF_SIZE,
					WOLF_SIZE, false, false));
			wimgInv.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolfInverse_" + i + ".png"),
					WOLF_SIZE, WOLF_SIZE, false, false));
			wimgInv2s.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolf2s_" + i + ".png"), WOLF_SIZE,
					WOLF_SIZE, false, false));
			if (i <= 2)
				wimgStun.add(new Image(ClassLoader.getSystemResourceAsStream("character/wolfstun_" + i + ".png"),
						WOLF_SIZE, WOLF_SIZE, false, false));
		}

		for (int i = 1; i <= 4; i++) {
			rimg.add(new Image(ClassLoader.getSystemResourceAsStream("character/rabbit_" + i + ".png"), RABBIT_SIZE,
					RABBIT_SIZE, false, false));
			rimgInv.add(new Image(ClassLoader.getSystemResourceAsStream("character/rabbitInverse_" + i + ".png"),
					RABBIT_SIZE, RABBIT_SIZE, false, false));
			rimgInv2s.add(new Image(ClassLoader.getSystemResourceAsStream("character/rabbit2s_" + i + ".png"),
					RABBIT_SIZE, RABBIT_SIZE, false, false));
			rimgInvis.add(new Image(ClassLoader.getSystemResourceAsStream("character/rabbitIm_" + i + ".png"),
					RABBIT_SIZE, RABBIT_SIZE, false, false));
		}

		invisBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/Invis.png")), 0, 0, 1, 1,
				true);
		invisImage = new Image(ClassLoader.getSystemResourceAsStream("item/Invis.png"));

		bombBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/IBomb.png")), 0, 0, 1, 1,
				true);
		bombImage = new Image(ClassLoader.getSystemResourceAsStream("item/IBomb.png"));

		speedBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/Speed.png")), 0, 0, 1, 1,
				true);
		speedImage = new Image(ClassLoader.getSystemResourceAsStream("item/Speed.png"));

		jumpBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/jump.png")), 0, 0, 1, 1,
				true);
		normalBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/grass.png")), 0, 0, 1, 1,
				true);
		normalBlockCarrot = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/carrot.png")), 0, 0,
				1, 1, true);
		normalBlockPotion = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/potion.png")), 0, 0,
				1, 1, true);
		trapBlock = new ImagePattern(new Image(ClassLoader.getSystemResourceAsStream("block/trap.png")), 0, 0, 1, 1,
				true);
	}

	public static List<Image> getWimg() {
		return wimg;
	}

	public static void setWimg(List<Image> wimg) {
		ImageLoader.wimg = wimg;
	}

	public static List<Image> getWimgInv() {
		return wimgInv;
	}

	public static void setWimgInv(List<Image> wimgInv) {
		ImageLoader.wimgInv = wimgInv;
	}

	public static List<Image> getWimgInv2s() {
		return wimgInv2s;
	}

	public static void setWimgInv2s(List<Image> wimgInv2s) {
		ImageLoader.wimgInv2s = wimgInv2s;
	}

	public static List<Image> getWimgStun() {
		return wimgStun;
	}

	public static void setWimgStun(List<Image> wimgStun) {
		ImageLoader.wimgStun = wimgStun;
	}

	public static List<Image> getRimg() {
		return rimg;
	}

	public static void setRimg(List<Image> rimg) {
		ImageLoader.rimg = rimg;
	}

	public static List<Image> getRimgInv() {
		return rimgInv;
	}

	public static void setRimgInv(List<Image> rimgInv) {
		ImageLoader.rimgInv = rimgInv;
	}

	public static List<Image> getRimgInv2s() {
		return rimgInv2s;
	}

	public static void setRimgInv2s(List<Image> rimgInv2s) {
		ImageLoader.rimgInv2s = rimgInv2s;
	}

	public static List<Image> getRimgInvis() {
		return rimgInvis;
	}

	public static void setRimgInvis(List<Image> rimgInvis) {
		ImageLoader.rimgInvis = rimgInvis;
	}

	public static ImagePattern getInvisBlock() {
		return invisBlock;
	}

	public static void setInvisBlock(ImagePattern invisBlock) {
		ImageLoader.invisBlock = invisBlock;
	}

	public static Image getInvisImage() {
		return invisImage;
	}

	public static void setInvisImage(Image invisImage) {
		ImageLoader.invisImage = invisImage;
	}

	public static ImagePattern getBombBlock() {
		return bombBlock;
	}

	public static void setBombBlock(ImagePattern bombBlock) {
		ImageLoader.bombBlock = bombBlock;
	}

	public static Image getBombImage() {
		return bombImage;
	}

	public static void setBombImage(Image bombImage) {
		ImageLoader.bombImage = bombImage;
	}

	public static ImagePattern getSpeedBlock() {
		return speedBlock;
	}

	public static void setSpeedBlock(ImagePattern speedBlock) {
		ImageLoader.speedBlock = speedBlock;
	}

	public static Image getSpeedImage() {
		return speedImage;
	}

	public static void setSpeedImage(Image speedImage) {
		ImageLoader.speedImage = speedImage;
	}

	public static ImagePattern getJumpBlock() {
		return jumpBlock;
	}

	public static void setJumpBlock(ImagePattern jumpBlock) {
		ImageLoader.jumpBlock = jumpBlock;
	}

	public static ImagePattern getNormalBlock() {
		return normalBlock;
	}

	public static void setNormalBlock(ImagePattern normalBlock) {
		ImageLoader.normalBlock = normalBlock;
	}

	public static ImagePattern getNormalBlockCarrot() {
		return normalBlockCarrot;
	}

	public static void setNormalBlockCarrot(ImagePattern normalBlockCarrot) {
		ImageLoader.normalBlockCarrot = normalBlockCarrot;
	}

	public static ImagePattern getNormalBlockPotion() {
		return normalBlockPotion;
	}

	public static void setNormalBlockPotion(ImagePattern normalBlockPotion) {
		ImageLoader.normalBlockPotion = normalBlockPotion;
	}

	public static ImagePattern getTrapBlock() {
		return trapBlock;
	}

	public static void setTrapBlock(ImagePattern trapBlock) {
		ImageLoader.trapBlock = trapBlock;
	}

}
