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

	//Making the background move
	private void backMove(Graphics g) {
		xcoord++;
		if(xcoord == 549) {
			xcoord = 0;
		}
		g.drawImage(Images.background, -xcoord, 0, 554, 700, null);
		if(xcoord > 0) {
			g.drawImage(Images.background,-xcoord + 549, 0, 550, 700, null);
		}
	}
}
