package game;

import javafx.scene.media.AudioClip;

public class GameSound {
	private static AudioClip soundJump;
	private static AudioClip soundDie;
	private static AudioClip soundBG;
	private static AudioClip soundEat;
	private static AudioClip soundWolf;
	private static AudioClip soundWolfDie;
	private static AudioClip soundFart;
	
	static {
		soundBG = new AudioClip("file:res/sound/happy.mp3");
		soundDie = new AudioClip("file:res/sound/fall.mp3");
		soundJump = new AudioClip("file:res/sound/CARTPOP.mp3");
		soundEat = new AudioClip("file:res/sound/pop.mp3");
		soundBG = new AudioClip("file:res/sound/happy.mp3");
		soundWolf = new AudioClip("file:res/sound/wolf6.mp3");
		soundWolfDie = new AudioClip("file:res/sound/wolf1.mp3");
		soundFart = new AudioClip("file:res/sound/fart.mp3");
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
	
	public static void playSoundWolfDie() {
		soundWolfDie.setCycleCount(1);
  	  	soundWolfDie.play(0.8);
	}
	
	public static void playSoundFart() {
		soundFart.setCycleCount(1);
  	  	soundFart.play(0.5);
	}
	
	public static void playSoundBG() {
		soundBG.setCycleCount(AudioClip.INDEFINITE);
		soundBG.play(0.3);
	}
	
	public static void stopSoundBG() {
		soundBG.stop();
	}

	
	
	
	

}
