package uet.oop.bomberman.entities.tile;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Tile {

	protected Board _board;
	
	public Portal(int x, int y, Board board, Sprite sprite) {
		super(x, y, sprite);
		_board = board;
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Bomber ) {
			
			if(_board.detectNoEnemies() == false)
				return false;
			
			if(e.getXTile() == getX() && e.getYTile() == getY()) {
				if(_board.detectNoEnemies()) {
					try {
						AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Game.class.getResource("/music/complete.wav"));
						Clip clip = AudioSystem.getClip();
						clip = AudioSystem.getClip();
						clip.open(audioInputStream);
						clip.start();
						audioInputStream.close();
					 
					    } catch (Exception ex) {
					      System.out.println(ex.getMessage());
					    }
					
					_board.nextLevel();
				}
					
			}
			
			return true;
		}
		
		return false;
	}

}
