package Game.World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.Entities.EntityManager;
import Game.Entities.StaticEntities.PipeSet;
import Main.Handler;
import Resources.Images;

public class WorldManager {

	private Handler handler;

	private EntityManager entityManager;

	private int xcoord;
	
	private Rectangle upperBound, lowerBound;

	public WorldManager(Handler handler, EntityManager entityManager) {
		this.handler = handler;
		this.entityManager = entityManager;    
		this.entityManager.addPipe(new PipeSet(handler, this.handler.getWidth(), 20));
		
		upperBound = new Rectangle(0, 0, handler.getWidth(), 10);
		lowerBound = new Rectangle(0, handler.getHeight() - 145, handler.getWidth(), 145);

		
		xcoord = 0;
	}

	public void tick() {
		xcoord++;
		if(xcoord == 549) {
			xcoord = 0;
		}
	}

	public void render(Graphics g) {
		
		g.drawImage(Images.background, -xcoord, 0, 554, 700, null);
		g.drawImage(Images.floor, -xcoord, handler.getHeight() - 150, handler.getWidth(), 150, null);

		if(xcoord > 0) {
			g.drawImage(Images.background,-xcoord + 549, 0, 550, 700, null);
			g.drawImage(Images.floor, -xcoord + 549, handler.getHeight() - 150, handler.getWidth(), 150, null);

		}

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.draw(lowerBound);
		g2.draw(upperBound);
	}

	public Rectangle getUpperBound() {
		return upperBound;
	}

	public Rectangle getLowerBound() {
		return lowerBound;
	}

}
