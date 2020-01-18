package Game.Entities.StaticEntities;

import java.awt.Graphics;

import Game.Entities.EntityBase;
import Main.Handler;

public abstract class StaticBase extends EntityBase {

	public StaticBase(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void move();

}
