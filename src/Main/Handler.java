package Main;

import Game.Entities.EntityManager;
import Game.World.WorldManager;
import Input.KeyManager;
import Input.MouseManager;
import Resources.MusicHandler;

public class Handler {

	private GameSetup game;
	private WorldManager worldManager;
	private EntityManager entityManager;
	
	
	public MusicHandler getMusicHandler() {
		return game.getMusicHandler();
	}

	public Handler(GameSetup game) {
		this.game = game;
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public GameSetup getGame() {
		return game;
	}

	public void setGame(GameSetup game) {
		this.game = game;
	}

	public WorldManager getWorldManager() {
		return worldManager;
	}

	public void setWorldManager(WorldManager worldManager) {
		this.worldManager = worldManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	

}
