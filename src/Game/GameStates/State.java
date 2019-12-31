package Game.GameStates;

import java.awt.Graphics;

import Main.GameSetup;
import Main.Handler;

public abstract class State {
	
	protected Handler handler;
	private static State currentState = null;

	public State (Handler handler) {
		this.handler = handler;
	}
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	
	
	
	
	
	public abstract void tick();
	public abstract void render(Graphics g);

}
