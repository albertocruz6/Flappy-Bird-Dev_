package Game.Entities.DynamicEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Game.Entities.EntityBase;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Player extends EntityBase {

	private Animation yBird, rBird;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32);
		
		yBird = new Animation(200, Images.yPlayer);
		rBird = new Animation(200, Images.rPlayer);

	}

	@Override
	public void tick() {
		yBird.tick();
		rBird.tick();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(yBird.getCurrentFrame(), (int) x, (int)y, getWidth(), getHeight(), null) ;
		g2.setColor(Color.black);
		g2.draw(bounds);
	}

	@Override
	public void move() {
		
	}

}
