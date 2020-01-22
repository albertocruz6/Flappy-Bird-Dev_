package Game.Entities.DynamicEntities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import Game.Entities.EntityBase;
import Game.Entities.StaticEntities.PipeSet;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Player extends EntityBase {

	private Animation yBird, rBird, currentAnim;
	private static boolean alive, passing, justScored, justJumped,  callJump, initJump, falling;
	private int score, yInit, yCurr;


	//Numbers for point animation
	private int firstY, secondY, xDisplacement;


	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32);

		yBird = new Animation(200, Images.yPlayer);
		rBird = new Animation(200, Images.rPlayer);


		//Character Variables
		score = 0;
		alive = true;
		initJump = false; 
		callJump = false;

		//Coin animation variables
		firstY = (int) y;
		secondY = 0;
		xDisplacement = (int) x;
	}

	@Override
	public void tick() {
		playerAnim();

		move();


	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(yBird.getCurrentFrame(), (int) x, (int)y, getWidth(), getHeight(), null) ;
//		g2.setColor(Color.black);
//		g2.draw(bounds);
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
		Random rand  = new Random();
		
		if (handler.getKeyManager().jump && !initJump) {
			handler.getMusicHandler().playWing();
			falling = false;
			callJump = true;
			jump();
			System.out.println("1");
			
		}
		else if(initJump && !justJumped && !falling) {
			jump();
			System.out.println("2");
		}
		else if(justJumped && falling) {
			justJumped = false;
			initJump = false;
			falling();
			System.out.println("3");
		}
		else if(falling){
			falling();
			System.out.println("4");
		}
		
//		} else {
//			y += 10;
//			bounds.y += 10;
//		}
	}


	private void playerAnim() {
		yBird.tick();
		rBird.tick();
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
			secondY = 0;
			xDisplacement = (int) x;
			justScored = false;
		}
	}
	
	//JUMP
	private void jump() {
		if(callJump) {
			yInit = (int) y;
			initJump  = true;
			callJump = false;
		}
		y -= 10;
		bounds.y -= 10;
		yCurr = (int) y;
		if(yCurr <= yInit - 60) {
			justJumped = true;
			falling = true;
		}
		
		
	}
	
	private void falling() {
		y += 3;
		bounds.y += 3;
	}

	
	//GETTERS AND SETTERS 
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		Player.alive = alive;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		Player.falling = falling;
	}

}
