package Game.GameStates;

import java.awt.Graphics;

import Input.MouseManager;
import Main.Handler;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	

	//Assorted Numbers
	private int xcoord, desiredLimit, fall, reb_1, fall_1, lastX, lastX_1, displace;
	private boolean initialized;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUimanager(uiManager);
		
		//Title Appear variables
		fall = -100;
		reb_1 = 0;
		fall_1 = 0;
		desiredLimit = 75;

		//Background movement 
		xcoord = 0;

		//Buttons 
		initialized = false;
		displace = handler.getHeight() + 100;
	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		uiManager.tick();

		if(initialized) {
			uiManager.addObjects(new UIImageButton(handler.getWidth()/2 - 200,displace,100,60,Images.buttonStart ,new ClickListlener() {
				
				@Override
				public void onClick() {
					handler.getMouseManager().setUimanager(null);
	                handler.getGame().reStart();
					System.out.println("lol");
					State.setState(handler.getGame().gameState); //change to getReadyState later
				}
			}));
		}
	}

	@Override
	public void render(Graphics g) {
		//g.drawImage(Images.background, 0, 0, 554, 700, null); 
		backMove(g);
		titleAppear(g);
		buttonsAppear(g);
		uiManager.Render(g);
	}


	//Making the title Appear
	private void titleAppear(Graphics g) {
		if (fall < desiredLimit) {
			g.drawImage(Images.titleIcon, handler.getWidth()/4, fall, 275, 110, null);
			fall += 5;
		}
		else if(reb_1 < 60) {
			g.drawImage(Images.titleIcon, handler.getWidth()/4, desiredLimit - reb_1, 275, 110, null);	
			reb_1 +=2;
			lastX = desiredLimit - reb_1;
		}
		else if(fall_1 < 40) {
			g.drawImage(Images.titleIcon, handler.getWidth()/4, lastX + fall_1,275, 110,null);
			fall_1 += 2;
			lastX_1 = lastX + fall_1;
		}
		else {
			g.drawImage(Images.titleIcon, handler.getWidth()/4, lastX_1, 275, 110, null);

		}

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

	//Making buttons appear
	private void buttonsAppear(Graphics g) {
		if(displace > handler.getHeight() - 200) {
			g.drawImage(Images.startButton, handler.getWidth()/2 - 200, displace, 100, 60, null);
			g.drawImage(Images.scoreButton, handler.getWidth()/2 + 100, displace, 100, 60, null);
			displace--;
		}
		else {
			g.drawImage(Images.startButton, handler.getWidth()/2 - 200, displace, 100, 60, null);
			g.drawImage(Images.scoreButton, handler.getWidth()/2 + 100, displace, 100, 60, null);
			initialized = true;
			
		}
	}
}
