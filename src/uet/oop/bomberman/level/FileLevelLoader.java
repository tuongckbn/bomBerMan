package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Doll;
import uet.oop.bomberman.entities.character.enemy.Kondoria;
import uet.oop.bomberman.entities.character.enemy.Minvo;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.*;
import uet.oop.bomberman.entities.tile.item.*;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		String path = "/levels/" + "Level" + level + ".txt";
		InputStream is = FileLevelLoader.class.getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		try {
			line = br.readLine();
			String[] nums = line.split(" ");
			_level = level;
			_height = Integer.parseInt(nums[1]);
			_width = Integer.parseInt(nums[2]);
			_map = new char[_height][_width];
			for(int i=0; i<_height; i++) {
				line = br.readLine();
				for(int j=0; j<line.length(); j++) {
					_map[i][j] = line.charAt(j);
				}
			}
			
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
		
		for (int y = 0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {
				int pos = x + y * _width;
				
				//Thêm wall
				if (_map[y][x] == '#')
				{
				_board.addEntity(pos, new Wall(x, y, Sprite.wall));
				}
				
				//thêm grass
				if (_map[y][x] == ' ')
				_board.addEntity(pos, new Grass(x, y, Sprite.grass));

				// thêm Bomber
				if (_map[y][x] == 'p') {
					_board.addCharacter(new Bomber(Coordinates.tileToPixel(x),
							Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
					Screen.setOffset(0, 0);
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
				}
				if (_map[y][x] == 'n') {
					_board.addCharacter(new Bomber(Coordinates.tileToPixel(x),
							Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
					Screen.setOffset(0, 0);
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
				}

				// thêm Enemy
				if (_map[y][x] == '1') {
					_board.addCharacter(new Balloon(Coordinates.tileToPixel(x),
							Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
				}

				if (_map[y][x] == '2') {
					_board.addCharacter(new Oneal(Coordinates.tileToPixel(x),
							Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
				}
				
				if (_map[y][x] == '3') {
					for(int i=0; i<3; i++)
					_board.addCharacter(new Doll(Coordinates.tileToPixel(x),
							Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
				}
				
				if (_map[y][x] == '4') {
					_board.addCharacter(new Minvo(Coordinates.tileToPixel(x),
							Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
				}
				
				if (_map[y][x] == '5') {
					_board.addCharacter(new Kondoria(Coordinates.tileToPixel(x),
							Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
				}
				
//				if (_map[y][x] == '6') {
//					_board.addCharacter(new Ovape(Coordinates.tileToPixel(x),
//							Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
//					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
//				}

				// thêm Brick
				if (_map[y][x] == '*') {
					_board.addEntity(pos,
							new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Brick(x, y, Sprite.brick)));
				}

				// thêm Item kèm Brick che phủ ở trên
				if (_map[y][x] == 'b') {
					_board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass),
							new BombItem(x, y, _level, Sprite.powerup_bombs), new Brick(x, y, Sprite.brick)));
				}

				if (_map[y][x] == 'f') {
					_board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass),
							new FlameItem(x, y ,_level, Sprite.powerup_flames), new Brick(x, y, Sprite.brick)));
				}

				if (_map[y][x] == 's') {
					_board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass),
							new SpeedItem(x, y,_level, Sprite.powerup_speed), new Brick(x, y, Sprite.brick)));
				}
				
				//Thêm portal
				if (_map[y][x] == 'x') {
					_board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass),
							new Portal(x, y,_board, Sprite.portal), new Brick(x, y, Sprite.brick)));
				}
			}
		}
	}

}
