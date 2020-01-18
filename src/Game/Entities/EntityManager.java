package Game.Entities;

import java.awt.Graphics;
import java.util.ArrayList;

import Game.Entities.DynamicEntities.Player;
import Game.Entities.StaticEntities.PipeSet;
import Main.Handler;

public class EntityManager {

	private Handler handler;
	private Player player;

	ArrayList<PipeSet> pipes;

	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		pipes = new ArrayList<>();

	}

	public void tick() {
		if(pipes.size() == 1) {
			int mult = 150;
			while(pipes.size() < 4) {
				addPipe(new PipeSet(handler,handler.getWidth() + mult, 0));
				mult += 150;
			}

		}
		for (PipeSet p: pipes) {
			p.tick();
		}
		player.tick();
	}

	public void render(Graphics g) {
		for (PipeSet p: pipes) {
			p.render(g);
		}
		player.render(g);
	}

	public void addPipe(PipeSet e) {
		pipes.add(e);
	}

	public void removePipe(PipeSet e) {
		pipes.remove(e);
	}

	public Player getPlayer() {
		return player;
	}



}
