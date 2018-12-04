package uet.oop.bomberman;



import uet.oop.bomberman.gui.Frame;

public class BombermanGame extends Thread {
	private Frame frame;
	public void run() {
		frame=new Frame();
		
	}
	public void close()
	{
		frame.dispose();
	}
}
