package game;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameSound {
	private static AudioClip soundJump;
	private static AudioClip soundDie;
	private static MediaPlayer soundBG;
	private static AudioClip soundEat;
	private static AudioClip soundWolf;
	private static AudioClip soundWolfDie;
	private static AudioClip soundFart;
	private static AudioClip soundExplosion;
	private static MediaPlayer soundMenu;

	static {
		soundBG = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/happy.mp3").toString()));
		soundDie = new AudioClip(ClassLoader.getSystemResource("sound/fall.mp3").toString());
		soundJump = new AudioClip(ClassLoader.getSystemResource("sound/CARTPOP.mp3").toString());
		soundEat = new AudioClip(ClassLoader.getSystemResource("sound/pop.mp3").toString());
		soundWolf = new AudioClip(ClassLoader.getSystemResource("sound/wolf6.mp3").toString());
		soundWolfDie = new AudioClip(ClassLoader.getSystemResource("sound/wolf1.mp3").toString());
		soundFart = new AudioClip(ClassLoader.getSystemResource("sound/fart.mp3").toString());
		soundExplosion = new AudioClip(ClassLoader.getSystemResource("sound/explosion.mp3").toString());
		soundMenu = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/menu.mp3").toString()));
	}

	public static void playSoundDie() {
		soundDie.setCycleCount(1);
		soundDie.play(0.5);
	}

	public static void playSoundJump() {
		soundJump.setCycleCount(1);
		soundJump.play(0.8);
	}

	public static void playSoundEat() {
		soundEat.setCycleCount(1);
		soundEat.play(0.8);
	}

	public static void playSoundWolf() {
		soundWolf.setCycleCount(1);
		soundWolf.play(0.8);
	}

	public static void playSoundExplosion() {
		soundExplosion.setCycleCount(1);
		soundExplosion.play(0.8);
	}

	public static void playSoundFart() {
		soundFart.setCycleCount(1);
		soundFart.play(0.5);
	}

	public static void playSoundWolfDie() {
		soundWolfDie.setCycleCount(1);
		soundWolfDie.play(0.8);
	}

	public static void playSoundBG() {
		soundBG.setCycleCount(AudioClip.INDEFINITE);
		soundBG.play();
	}

	public static void stopSoundBG() {
		soundBG.stop();
	}

	public static void playSoundMenu() {
		soundMenu.setCycleCount(MediaPlayer.INDEFINITE);
		soundMenu.play();
	}

	public static void stopSoundMenu() {
		soundMenu.stop();
	}
}
