package Game.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Game.Entities.EntityManager;
import Game.Entities.DynamicEntities.Player;
import Game.World.WorldManager;
import Main.Handler;
import Resources.Images;

public class GameState extends State{

	WorldManager worldManager;
	EntityManager entityManager;
	Player player;

	public GameState(Handler handler) {
		super(handler);
		//Set-up of player and managers
		this.player = new Player(handler, 30, handler.getHeight()/2);
		this.entityManager = new EntityManager(handler, player);
		this.worldManager = new WorldManager(handler, entityManager);


		this.handler.setEntityManager(entityManager);
		this.handler.setWorldManager(worldManager);

	}

	@Override
	public void tick() {
		this.worldManager.tick();
		this.entityManager.tick();

		if(!handler.getEntityManager().getPlayer().isAlive()) {
			handler.getMusicHandler().playHit();
			State.setState(handler.getGame().menuState);
			handler.getMusicHandler().playDead();
			
		}
		
		if (player.getScore() == 1000) {
			System.out.println("YOU WIN");
			System.out.println("Score: " + player.getScore());
			State.setState(handler.getGame().winState);
			
		}
		if (handler.getGame().getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) { 
			State.setState(handler.getGame().menuState); 
		} 
	}

	@Override
	public void render(Graphics g) {
		this.worldManager.render(g);
		this.entityManager.render(g);
		renderScore(g);
	}

	public void renderScore(Graphics g) {

		if (player.getScore() < 10) {
			g.drawImage(Images.numbers[player.getScore()], handler.getWidth()/ 2  - 5, 65,
					Images.numbers[player.getScore()].getWidth() * 2,Images.numbers[player.getScore()].getHeight() * 2,null);
		}
		
		if (player.getScore() >= 10 && player.getScore() < 100) {
			
			 //Draws First Digit in number
			g.drawImage(Images.numbers[player.getScore() / 10], handler.getWidth()/ 2  - 19, 65,
					Images.numbers[player.getScore() / 10].getWidth() * 2,Images.numbers[player.getScore() / 10].getHeight() * 2,null);
			
			 //Draws Last Digit in number
			g.drawImage(Images.numbers[player.getScore() % 10], handler.getWidth()/ 2 + 4, 64, 
					Images.numbers[player.getScore() % 10].getWidth() * 2,Images.numbers[player.getScore() % 10].getHeight() * 2,null);


		}
		if (player.getScore() >= 100 && player.getScore() < 1000) {
			// Finds total number of digits - 1 
		    int digits = (int) Math.log10(player.getScore()); 
		   
		    //Draws First Digit in number
			g.drawImage(Images.numbers[(int)(player.getScore() / Math.pow(10, digits))], handler.getWidth()/ 2  - 19, 65,
					Images.numbers[(int)(player.getScore() / Math.pow(10, digits))].getWidth() * 2,
					Images.numbers[(int)(player.getScore() / Math.pow(10, digits))].getHeight() * 2,null);
			
			 //Draws Second/Middle Digit in number
			g.drawImage(Images.numbers[(player.getScore()/10) % 10], handler.getWidth()/ 2 + 4, 64, 
					Images.numbers[(player.getScore()/10) % 10].getWidth() * 2,Images.numbers[(player.getScore()/10) % 10].getHeight() * 2,null);
			
			 //Draws Last Digit in number
			g.drawImage(Images.numbers[player.getScore() % 10], handler.getWidth()/ 2 + 30, 62, 
					Images.numbers[player.getScore() % 10].getWidth() * 2,Images.numbers[player.getScore() % 10].getHeight() * 2,null);
			

		}
	}

}
