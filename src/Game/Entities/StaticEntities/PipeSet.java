package Game.Entities.StaticEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Main.Handler;
import Resources.Images;

public class PipeSet extends StaticBase {

	private static final int SAFE_SPACE_SIZE = 128;
	
	private Rectangle upperR, lowerR, safeR;

	private Random rand = new Random();
	private int initValue;
	private int moveX;
	private boolean validPoint, passed;


	public PipeSet(Handler handler, float x, float y) {
		super(handler, x, y, 64, 700);

		moveX = 2;

		upperR = new Rectangle(0,0,0,0);
		lowerR = new Rectangle(0,0,0,0);
		safeR = new Rectangle(0,0,0,0);

		validPoint = true;
		passed = false;
		
		initializeRectangle();

	}

	@Override
	public void tick() {
		move();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g.drawImage(Images.pipeDown, upperR.x, upperR.y, upperR.width, upperR.height,null);
		g2.draw(upperR);
		g2.setColor(Color.red);
		g2.draw(safeR);
		g2.setColor(Color.black);
		g.drawImage(Images.pipeUp, lowerR.x, lowerR.y, lowerR.width, lowerR.height,null);
		g2.draw(lowerR);
		

	}

	@Override
	public void move() {
		//Method to move the whole entity
		//First it checks if it reaches limit
		if(getX() <= -getWidth()) {
			//if it reaches edge reset randomized y location
			initializeRectangle();
			setX(handler.getWidth());
			getUpperR().x = handler.getWidth();
			getSafeR().x = handler.getWidth();
			getLowerR().x = handler.getWidth();
			validPoint = true;
			passed = false;
			
		}
		this.x -= moveX;
		updateRectangleX();
	}

	public void initializeRectangle() {

		//initValue = SAFE_SPACE_SIZE + rand.nextInt(this.getHeight() - SAFE_SPACE_SIZE*2 - 150);
		initValue = handler.getHeight()/2  - 20;

		//Initialize rectangle in randomized y position
		upperR.height = initValue;
		upperR.width = this.getWidth();
		upperR.x = (int) x;
		upperR.y =  0;

		safeR.height = SAFE_SPACE_SIZE;
		safeR.width = this.getWidth();
		safeR.x = (int) x;
		safeR.y =  initValue;

		lowerR.height = handler.getHeight() - initValue +  safeR.height ;
		lowerR.width = this.getWidth();
		lowerR.x = (int) x;
		lowerR.y =  safeR.height + initValue;

	}

	public void updateRectangleX() {
		//Update X variables of the pipe boundaries 
		upperR.x -= moveX;
		safeR.x -= moveX;
		lowerR.x -= moveX;

	}
	
	//GETTERS AND SETTERS
	public Rectangle getUpperR() {
		return upperR;
	}

	public Rectangle getLowerR() {
		return lowerR;
	}

	public Rectangle getSafeR() {
		return safeR;
	}

	public boolean isValidPoint() {
		return validPoint;
	}

	public void setValidPoint(boolean validPoint) {
		this.validPoint = validPoint;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}



}
