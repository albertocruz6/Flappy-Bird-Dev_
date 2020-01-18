package Game.GameStates;

import java.awt.Graphics;

import Game.Entities.EntityManager;
import Game.Entities.DynamicEntities.Player;
import Game.Entities.StaticEntities.PipeSet;
import Game.World.WorldManager;
import Main.Handler;
import Resources.Images;

public class GameState extends State{

	WorldManager worldManager;
	EntityManager entityManager;
	Player player;
	
	public GameState(Handler handler) {
		super(handler);
		//Set-up of player and managers
		this.player = new Player(handler, 30, handler.getHeight()/2);
		this.entityManager = new EntityManager(handler, player);
		this.worldManager = new WorldManager(handler, entityManager);
		
		
		this.handler.setEntityManager(entityManager);
		this.handler.setWorldManager(worldManager);
		
	}

	@Override
	public void tick() {
		this.worldManager.tick();
		this.entityManager.tick();
		
		if(!handler.getEntityManager().getPlayer().alive) {
			State.setState(handler.getGame().menuState);
		}
	}

	@Override
	public void render(Graphics g) {
		this.worldManager.render(g);
		this.entityManager.render(g);
		
	}

}
