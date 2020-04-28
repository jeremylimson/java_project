/**
 * Description : main script used to run game and load and display graphics and sprite behaviors
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 */
package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

import com.game.src.audio.MusicPlayer;

public class Game extends Canvas implements Runnable {

	// initialize variables to open the game window
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "H2 Oh No!";

	private boolean running = false;
	private Thread thread;

	// buffers the whole window
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;

	private boolean is_shooting = false;

	// initializes number of enemies on start up
	Random r = new Random();
	private int enemy_count = 6;
	private int enemy_killed = 0;

	// initializes in game sprite to be buffered from sprite sheet
	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;

	// list for Entity A is player
	// list for Entity B is enemy
	public LinkedList<Player> ea;
	public LinkedList<Enemy> eb;
	public LinkedList<Cannonball> ec;

	// initialize states for menu interface
	public static enum STATE {
		MENU, GAME, HELP
	};

	public static STATE State = STATE.MENU;

	public void init() { // initializes sprite sheet
		BufferedImageLoader loader = new BufferedImageLoader();

		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png"); // loads sprite sheet
			background = loader.loadImage("/background.png"); // loads background
		} catch (IOException e) {
			e.printStackTrace(); // checks for error
		}

		// initialize graphics, player, controller, and menu
		tex = new Textures(this);
		c = new Controller(tex, this);
		p = new Player(200, 200, tex, this, c); // sets initial position of player
		menu = new Menu();

		ea = c.getEntityA();
		eb = c.getEntityB();

		// enables input from keyboard and mouse
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());

		// spawns initial trash
		c.createEnemy(enemy_count);
	}

	private synchronized void stop() { // keeps game from running infinitely
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	long totalTime = 0;
	
	public void run() {
		running = true;
		init();
		// anti-aliasing for graphics and text
		Graphics2D g = (Graphics2D) image.getGraphics();

		// limits graphic distortion
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		requestFocus(); // automatically makes the game window active
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; // used to account for dropped frames
		// int updates = 0;
		// int frames = 0;
		long timer = System.currentTimeMillis();
		
		/*
		long currTime = 0;
		long prevTime = System.currentTimeMillis();	
		*/
		
		while (running) { // loop runs the actual game
			
			/* this gets calculates 1000 milliseconds (1 second) intervals for clock
			currTime = System.currentTimeMillis() - prevTime;
			totalTime += currTime;
			if (totalTime >= 1000) {
				System.out.println(totalTime);
				g.drawString("Time: " + totalTime, 10, 50);
				totalTime = 0;
			}
			prevTime = System.currentTimeMillis();
			*/
			
			long now = System.nanoTime();

			delta += (now - lastTime) / ns; // difference in time represents lag
			//System.out.println(delta);
			lastTime = now; // sets max FPS regardless of computer capability
			if (delta >= 1) {
				tick();
				// updates++;
				delta--;
			}
			render();
			// frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000; // this prevents the loop from being re-entered
				// System.out.println(updates + " Ticks, FPS " + frames);
				// updates = 0; // reset updates and frames after displaying
				// frames = 0; // useful for displaying frame drops
			}
		}
		stop();
	}

	// accounts for game updates e.g. sprite movement
	private void tick() {
		if (State == STATE.GAME) { // if Play Button from menu is pressed
			p.tick();
			c.tick();
		}
	}

	// accounts for loading upcoming sprite
	private void render() {
		// handles all buffering
		BufferStrategy bs = this.getBufferStrategy(); // refers to Canvas when class was created

		if (bs == null) {
			this.createBufferStrategy(3); // uses 3 buffers to load screen updates
			return;
		}

		Graphics g = bs.getDrawGraphics(); // draws out buffers
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0, 0, null);

		if (State == STATE.GAME) { // if Play Button from menu is pressed

			c.render(g);
			p.render(g);

			// displays score counter
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.drawString("Score: " + Player.setScore(), 10, 30); // displays updates to score
			
			// FIXME
			g.drawString("Time: " + GameClock.setClock(), 10, 50);	// displays remaining time
			

		} else if (State == STATE.MENU) { // renders menu if play is not selected
			menu.render(g);
		} else if (State == STATE.HELP) { // game instructions on help screen

			g.setColor(Color.WHITE);
			g.setFont(new Font("Karmatic Arcade", Font.PLAIN, 20));
			g.drawString("Help! The water has become polluted...", 40, 100);
			g.drawString("Collect all the trash before the time", 40, 150);
			g.drawString("runs out and the damage is irreversible.", 40, 175);
			g.drawString("Use arrow keys to move and space to", 40, 200);
			g.drawString("shoot cannons", 40, 225);
			g.drawString("AND WATCH OUT FOR ANGRY SHARKS!!!", 40, 275);
			g.drawString("They're not too happy about what's ", 40, 325);
			g.drawString("happened to their home!", 40, 350);

			// back button prompt
			g.setFont(new Font("Karmatic Arcade", Font.PLAIN, 16));
			g.drawString("Back to menu press ESC ", 175, 450);
		}

		g.dispose();
		bs.show();
	}

	// enables keys to be used for movement and input
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// allows back option in help splash screen and in game
		if (State == STATE.HELP || State == STATE.GAME) {
			if (key == KeyEvent.VK_ESCAPE) {
				State = STATE.MENU;
			}
		}

		// enable player movement via arrow keys and shoot via space bar
		if (State == STATE.GAME) {

			if (key == KeyEvent.VK_RIGHT) {
				p.setVelX(5);
			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			} else if (key == KeyEvent.VK_DOWN) {
				p.setVelY(5);
			} else if (key == KeyEvent.VK_UP) {
				p.setVelY(-5);
			} else if (key == KeyEvent.VK_SPACE && !is_shooting) {
				c.addEntity(new Cannonball(p.getX(), p.getY(), tex));
				is_shooting = true;
				AudioPlayer.getSound("shoot").play();
			}
		}
	}

	// player stops moving when arrow key(s) are released
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;
		}
	}

	public static void main(String args[]) {
		// initiates two threads to execute one for game and one for background music
		ThreadPool pool = new ThreadPool(3);

		// this loads sound effects excluding background music
		AudioPlayer.load();

		// creates new instance of game
		Game game = new Game();
		
		// this sets unchanging variables for the games display
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		// this opens the game in a window of determined size
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game); // loads game into created window
		frame.pack(); // automatically resizes to accepted dimensions
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows exit button to work
		frame.setResizable(false); // prevents resizing of gaming window
		frame.setLocationRelativeTo(null); // prevents location of window from being set
		frame.setVisible(true);

		// create object in Music Player
		MusicPlayer player = new MusicPlayer("background_music");
		pool.runTask(player); // executes music player thread
		pool.runTask(game); // executes game thread
		pool.join(); // ends both threads for background music and game
		
		
	}
	
	// getters and setters for enemy count
	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	// gets sprite sheet
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
}