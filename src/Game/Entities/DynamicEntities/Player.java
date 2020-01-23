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
	private int score, yInit, yCurr, gravityValue;
	Random rand  = new Random();
	private final boolean yAnim  = rand.nextBoolean();

	//Numbers for point animation
	private int firstY, secondY, xDisplacement;


	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32);

		yBird = new Animation(200, Images.yPlayer);
		rBird = new Animation(200, Images.rPlayer);

		//Character Variables
		score = 0;
		gravityValue = 2;
		alive = true;
		initJump = false; 
		callJump = false;
		//Character Animation
		if(yAnim)
			currentAnim = yBird;
		else
			currentAnim = rBird;
		//Coin animation variables
		firstY = (int) y;
		secondY = 0;
		xDisplacement = (int) x;
	}

	@Override
	public void tick() {
		move();
		playerAnim();
	}

	@Override
	public void render(Graphics g) {
		
		//g.drawImage(currentAnim.getCurrentFrame(), (int) x, (int)y, getWidth(), getHeight(), null) ;
		
		if(initJump) {
			g.drawImage(Images.rotate(currentAnim.getSpecificFrame(2), -45), (int) x, (int)y, getWidth(), getHeight(), null) ;
		}
		else if(justJumped && falling) {
			g.drawImage(Images.rotate(currentAnim.getSpecificFrame(1), -25), (int) x, (int)y, getWidth(), getHeight(), null) ;
		}
		else
			g.drawImage(Images.rotate(currentAnim.getSpecificFrame(0), 25), (int) x, (int)y, getWidth(), getHeight(), null) ;

		if(justScored)
			pointAnimation(g);

		//CODE TO VISUALIZE BOUNDARIES
		//      Graphics2D g2 = (Graphics2D) g;
		//		g2.setColor(Color.black);
		//		g2.draw(bounds);
		//		g2.setColor(Color.green);
		//		g2.draw(upBounds);
		//		g2.draw(leftBounds);
		//		g2.draw(rightBounds);
		//		g2.draw(lowerBounds);
	}

	@Override
	public void move() {
		checkCollisions();

		//If player calls for button and it's not jumping at all
		if (handler.getKeyManager().jump && !initJump) {
			handler.getMusicHandler().playWing();
			falling = false;
			callJump = true;
			jump();
		}
		//If player has finished the jump but has started it
		else if(initJump && !justJumped && !falling) {
			jump();		
		}
		//If it finished the jump
		else if(justJumped && falling) {
			justJumped = false;
			initJump = false;
			falling();	
		}
		//Naturally the player is always falling 
		else {
			falling();
		}
	}

	//JUMP MECHANIC
	private void jump() {
		//If button is called set new threshold for y
		if(callJump) {
			//Set y to start jump
			yInit = (int) y;
			//Start jump
			initJump  = true;
			//Await for another call to jump function by player
			callJump = false;
		}
		//MOVE PLAYER Y
		y -= 10;
		bounds.y -= 10;
		//Update current Y
		yCurr = (int) y;
		//if it reaches threshold limit it completed the jump
		if(yCurr <= yInit - 60) {
			//finished jumping
			justJumped = true;
			//gravity immediately starts affecting
			falling = true;
		}


	}

	//FALL (GRAVITY)
	private void falling() {
		falling = true;
		y += gravityValue;
		bounds.y += gravityValue;
	}

	//CHARACTER ANIMATIONS
	private void playerAnim() {
		yBird.tick();
		rBird.tick();
	}

	//CHECK COLLISIONS
	private void checkCollisions() {
		Player player = this;
		ArrayList<PipeSet> pipesSpawned = handler.getEntityManager().getPipes();

		for (PipeSet pipeSet : pipesSpawned) {
			if(player.bounds.intersects(pipeSet.getSafeR()) &&
					(!player.bounds.intersects(pipeSet.getUpperR()) && !player.bounds.intersects(pipeSet.getLowerR()))) {
				//SAFE!
				passing = true;
				pipeSet.setPassed(true);
			}
			else if(player.bounds.intersects(pipeSet.getUpperR()) || player.bounds.intersects(pipeSet.getLowerR())) {
				//HITS A PIPE
				alive = false;
				System.out.println("DED");
				score = 0;
				firstY = (int) y;
				secondY = 0;
				xDisplacement = (int) x;
			}
			else if(player.bounds.intersects(handler.getWorldManager().getLowerBound()) || player.bounds.intersects(handler.getWorldManager().getUpperBound())) {
				//HITS THE FLOOR or TOP OF THE SCREEN
				alive = false;
				System.out.println("DED");
				score = 0;
				firstY = (int) y;
				secondY = 0;
				xDisplacement = (int) x;
			}
			else if(!player.bounds.intersects(pipeSet.getSafeR())){
				//SUCESFULLY SCORE
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

	//
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

	public Animation getCurrentAnim() {
		return currentAnim;
	}

}
