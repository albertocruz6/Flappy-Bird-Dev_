package Game.GameStates;

import java.awt.Graphics;

import Main.Handler;
import Resources.Images;

public class WinState extends State{

	private int xcoord;
	
	public WinState(Handler handler) {
		super(handler);
		
		xcoord = 0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		backMove(g);

	}

}
