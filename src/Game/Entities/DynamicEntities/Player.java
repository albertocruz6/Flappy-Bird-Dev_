package Game.Entities.DynamicEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Game.Entities.EntityBase;
import Game.Entities.StaticEntities.PipeSet;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Player extends EntityBase {

	private Animation yBird, rBird, currentAnim;
	public static boolean alive;
	public int score = 0;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32);

		yBird = new Animation(200, Images.yPlayer);
		rBird = new Animation(200, Images.rPlayer);


		//
		alive = true;
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
		//		g2.setColor(Color.green);
		//		g2.draw(upBounds);
		//		g2.draw(leftBounds);
		//		g2.draw(rightBounds);
		//		g2.draw(lowerBounds);
	}

	@Override
	public void move() {
		checkCollisions();
	}

	private void checkCollisions() {
		Player player = this;
		ArrayList<PipeSet> pipesSpawned = handler.getEntityManager().getPipes();

		for (PipeSet pipeSet : pipesSpawned) {
			if(player.bounds.intersects(pipeSet.getSafeR()) &&
			(!player.bounds.intersects(pipeSet.getUpperR()) && !player.bounds.intersects(pipeSet.getLowerR()))) {
				//safe!
				score++;
			}
			else if(player.bounds.intersects(pipeSet.getUpperR()) || player.bounds.intersects(pipeSet.getLowerR())) {
				alive = false;
				System.out.println("DED");
				
			}
		}
	}
}
