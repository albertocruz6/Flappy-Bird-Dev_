package Main;

import Game.World.WorldManager;
import Input.KeyManager;

public class Handler {

	private GameSetup game;
	private WorldManager worldManager;
	
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

}
