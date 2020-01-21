package Game.GameStates;

import java.awt.Graphics;

import Main.Handler;
import Resources.Images;

public class GetReadyState extends State{

	private int xcoord;
	
	public GetReadyState(Handler handler) {
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
