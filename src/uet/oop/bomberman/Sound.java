package uet.oop.bomberman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
   public void Sound() {
		
   }
   public void start()
   {
	   try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Game.class.getResource("/music/back1.wav"));
			clip = AudioSystem.getClip();
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			audioInputStream.close();
		 
		    } catch (Exception ex) {
		      System.out.println(ex.getMessage());
		    }
   }
   public void stop() {
	   clip.close();
   }
}
