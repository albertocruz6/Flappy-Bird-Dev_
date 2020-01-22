package Game.GameStates;

import java.awt.Graphics;

import Main.Handler;
import Resources.Images;

public abstract class State {
	
	protected Handler handler;
	protected static int xcoord;
	private static State currentState = null;

	public State (Handler handler) {
		this.handler = handler;
		xcoord = 0;
	}
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	public static void backMove(Graphics g) {
		xcoord++;
		if(xcoord == 549) {
			xcoord = 0;
		}
		g.drawImage(Images.background, -xcoord, 0, 554, 700, null);
		if(xcoord > 0) {
			g.drawImage(Images.background,-xcoord + 549, 0, 550, 700, null);
		}
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);

}
