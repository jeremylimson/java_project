/**
 * Description : handles player (rendering, position, movement)
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;

public class Player extends GameObject implements EntityA {

	// initialize and declare position of character
	private double velX = 0;
	private double velY = 0;

	// initialize and declare score
	public static int score = 0;

	private Game game;
	private Textures tex;
	private Controller c;

	public Player(double x, double y, Textures tex, Game game, Controller c) {
		super(x, y); // every time a new object is loaded, use GameObject constructor
		this.tex = tex;
		this.game = game;
		this.c = c;	// this solved the null pointer exception, unknown why super did not  work
	}

	// used for smoother player movement and faster response time
	public void tick() {
		x += velX;
		y += velY;

		// sets bounds for player movement
		if (x <= 0)
			x = 0;
		if (x >= 610)
			x = 610;
		if (y <= 0)
			y = 0;
		if (y >= 435)
			y = 435;

		// detects collision and removes sprite when player hits trash
		for (int i = 0; i < game.eb.size(); i++) {
			Enemy tempEnt = game.eb.get(i);

			if (Physics.Collision(this, tempEnt)) {
				c.removeEntity(tempEnt);	// removes trash after collected
				score++;	// every time trash is picked up, score increments
				AudioPlayer.getSound("collect").play();	// plays sound effect when trash collected
			}
		}
	}

	// draws hit box around player character
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	// draws character sprite to be loaded
	public void render(Graphics g) {
		g.drawImage(tex.player, (int) x, (int) y, null);
	}

	// gets position of player
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	// sets position of player
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	// setters for smooth arrow key controls
	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	// return score value to Game class
	public static int setScore() {
		return score;
	}
}
