package Main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import Display.DisplayScreen;
import Game.GameStates.GameState;
import Game.GameStates.GetReadyState;
import Game.GameStates.MenuState;
import Game.GameStates.State;
import Input.KeyManager;
import Input.MouseManager;
import Resources.Images;
import Resources.MusicHandler;


public class GameSetup implements Runnable{

	private DisplayScreen display;
	public int width, height;
	public String title;

	private boolean running;
	private Thread thread;


	private BufferStrategy bs;
	private Graphics g; 
	
	//States
	public State gameState, menuState, getReadyState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	//Handler 
	private Handler handler;
	
	public MusicHandler musicHandler;
	
	public GameSetup(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		//musicHandler = new MusicHandler(handler); uncomment this later to enable music
		
	}

	private void init() {
		display = new DisplayScreen(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Images img = new Images();
		handler = new Handler(this);
		menuState = new MenuState(handler);
		gameState = new GameState(handler);
		getReadyState = new GetReadyState(handler);
		
		State.setState(menuState); //change to menuState later
	
	}

	private void tick() {
		if (State.getState() != null) {
			State.getState().tick();
		}
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();

		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);

		//Draw Here!

		if (State.getState() != null) {
			State.getState().render(g);
		}
		//End Drawing
		bs.show();
		g.dispose();
	}



	public void run() {

		init();

		int fps = 60;
		double timePerTick = 1_000_000_000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks  = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			if (timer >= 1_000_000_000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}

		}

		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}


	public void reStart() {
		gameState = new GameState(handler);
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) return;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public MusicHandler getMusicHandler() {
		return musicHandler;
	}
	
	
	
	

}
