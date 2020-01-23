package Game.GameStates;

import java.awt.Graphics;

import Main.Handler;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;

public class GetReadyState extends State{

	private UIManager uiManager;

	//INITIALIZE ANIMATION
	private boolean initialized, tapInitialized, up;
	private int readyFall, tapRise, upDown, floorRise;

	public GetReadyState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUimanager(uiManager);

		//Initialized values
		initialized = false;
		tapInitialized = false;
		readyFall = -140;
		tapRise = handler.getHeight();
		upDown = 0;
		up = true;
		floorRise = handler.getHeight();

	}

	@Override
	public void tick() {
		//PLAYER ANIMATION
		handler.getEntityManager().getPlayer().getCurrentAnim().tick();
		
		if(initialized) {
			if(handler.getKeyManager().jump) {
				handler.getMouseManager().setUimanager(null);
				handler.getGame().reStart();		
				//handler.getGame().gameState = new GameState(handler);
				State.setState(handler.getGame().gameState); //change to getReadyState later

			}
		} 
	}

	@Override
	public void render(Graphics g) {
		backMove(g);
		screenInit(g);
		g.drawImage(handler.getEntityManager().getPlayer().getCurrentAnim().getCurrentFrame(),
				(int) handler.getEntityManager().getPlayer().getX(),
				(int) handler.getEntityManager().getPlayer().getY(), 
				handler.getEntityManager().getPlayer().getWidth(),
				handler.getEntityManager().getPlayer().getHeight(), null) ;
	}

	private void screenInit(Graphics g) {
		//FLOOR RISE
		if(floorRise > handler.getHeight() -150)
			floorRise -= 4;
		g.drawImage(Images.floor, 0, floorRise, handler.getWidth(), 150, null);
		//READY ICON
		if(readyFall < 31)
			readyFall += 2;
		g.drawImage(Images.readyIcon, handler.getWidth()/4, readyFall, 275, 110, null);
		if(readyFall >= 30) 
			initialized = true;
		//TAP ICON
		if(tapRise > handler.getHeight()/2 && !tapInitialized)
			tapRise -= 4;
		else
			tapInitialized = true;
		g.drawImage(Images.tapIcon, handler.getWidth()/4, tapRise + upDown, 275, 110, null);
		g.drawImage(Images.flapIcon, handler.getWidth()/2 , tapRise + upDown -50, 50, 50, null);
		if(tapInitialized) {
			if(upDown < 30 && up)
				upDown++;
			else if(upDown > 0) {
				up = false;
				upDown--;				
			}
			else if(upDown == 0)
				up = true;
		}

	}

}
