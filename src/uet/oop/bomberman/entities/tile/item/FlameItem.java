package uet.oop.bomberman.entities.tile.item;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {

	public FlameItem(int x, int y, int level, Sprite sprite) {
		super(x, y, level, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Bomber) {
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Game.class.getResource("/music/flame.wav"));
				Clip clip = AudioSystem.getClip();
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				audioInputStream.close();
			 
			    } catch (Exception ex) {
			      System.out.println(ex.getMessage());
			    }
			((Bomber) e).addItem(this);
			remove();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void setValues() {
		_active = true;
		Game.addBombRadius(1);
	}
}
