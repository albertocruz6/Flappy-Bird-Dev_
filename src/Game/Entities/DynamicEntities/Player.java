package Game.Entities.DynamicEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import Game.Entities.EntityBase;
import Game.Entities.StaticEntities.PipeSet;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Player extends EntityBase {

	private Animation yBird, rBird, currentAnim;
	public static boolean alive, passing, justScored;
	public int score = 0;
	
	
	//Numbers for point animation
	private int firstY, staticY, secondY, xDisplacement;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32);

		yBird = new Animation(200, Images.yPlayer);
		rBird = new Animation(200, Images.rPlayer);


		//Character life boolean
		alive = true;
		
		//Coin animation variables
		firstY = (int) y;
		staticY = 0;
		secondY = 0;
		xDisplacement = (int) x;
	}

	@Override
	public void tick() {
		playerAnim();
		move();

	}

	private void playerAnim() {
		yBird.tick();
		rBird.tick();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(yBird.getCurrentFrame(), (int) x, (int)y, getWidth(), getHeight(), null) ;
		g2.setColor(Color.black);
		g2.draw(bounds);
		if(justScored)
			pointAnimation(g);
		
		
		//		g2.setColor(Color.green);
		//		g2.draw(upBounds);
		//		g2.draw(leftBounds);
		//		g2.draw(rightBounds);
		//		g2.draw(lowerBounds);
	}

	@Override
	public void move() {
		checkCollisions();

		if(handler.getKeyManager().jump) {
			handler.getMusicHandler().playWing();	
			y -= 10;
		}
	}

	private void checkCollisions() {
		Player player = this;
		ArrayList<PipeSet> pipesSpawned = handler.getEntityManager().getPipes();

		for (PipeSet pipeSet : pipesSpawned) {
			if(player.bounds.intersects(pipeSet.getSafeR()) &&
					(!player.bounds.intersects(pipeSet.getUpperR()) && !player.bounds.intersects(pipeSet.getLowerR()))) {
				//safe!
				passing = true;
				pipeSet.setPassed(true);
			}
			else if(player.bounds.intersects(pipeSet.getUpperR()) || player.bounds.intersects(pipeSet.getLowerR())) {
				alive = false;
				System.out.println("DED");
				score = 0;
			}
			else if(!player.bounds.intersects(pipeSet.getSafeR())){
				if(pipeSet.isValidPoint() && passing && pipeSet.isPassed()) {
					score++;
					pipeSet.setValidPoint(false);
					passing = false;
					handler.getMusicHandler().playPoint();
					justScored = true;
				}
			}
		}
	}
	
	private void pointAnimation(Graphics g) {
		if(firstY > (int) y - 30) {
			g.drawImage(Images.onePoint, xDisplacement, firstY, 15, 25, null);
			firstY-= 2;
			secondY = firstY;
			xDisplacement++;
		}
		else if(secondY < handler.getHeight()) {
			g.drawImage(Images.onePoint, xDisplacement, secondY, 15, 25, null);
			secondY += 40;
			xDisplacement++;
		}
		else {
			firstY = (int) y;
			staticY = 0;
			secondY = 0;
			xDisplacement = (int) x;
			justScored = false;
		}
	}

}
