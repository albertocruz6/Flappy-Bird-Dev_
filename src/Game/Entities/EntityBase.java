package Game.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;

public abstract class EntityBase {
	
	protected Handler handler;
	protected float x, y;
	private int width, height;
	
	protected Rectangle bounds, upBounds, leftBounds, rightBounds, lowerBounds;
	
	public EntityBase(Handler handler, float x, float y, int width ,int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle((int) x, (int) y, width, height);
		
		upBounds = new Rectangle((int)x, (int)y , width, height/6);
		leftBounds = new Rectangle((int)x, (int)y, width/6, height);
		rightBounds = new Rectangle((int)x + 5*width/6, (int)y, width/6, height);
		lowerBounds = new Rectangle((int)x, (int)y+ 5*height/6, width, height/6);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void move();
	
	//BOUNDS
	public Rectangle getUpperBounds() {
		return upBounds;
	}
	
	//GETTERS AND SETTERS
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	

}
