/**
 * Description : handles trash (rendering, movement, & collision)
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB {

	Random r = new Random();
	private int speed = (r.nextInt(2) + 2);

	private Textures tex;

	public Enemy(double x, double y, Textures tex) {
		super(x, y);
		this.tex = tex;
	}

	// describes movement of "enemy" trash sprite
	public void tick() {
		y += speed; // moves trash down screen with random speeds

		// sets bounds for trash movement
		if (x <= 0)
			x = 0;
		if (x >= 610)
			x = 610;
		
		// resets enemy trash if bottom of screen is reached
		if (y > (Game.HEIGHT * Game.SCALE)) {
			speed = (r.nextInt(2) + 2); // after spawn at the top of screen, assign new speed
			y = 0;
			x = r.nextInt(Game.WIDTH * Game.SCALE); // randomizes its horizontal position after reseting position
		}

	}

	// draws trash
	public void render(Graphics g) {
		g.drawImage(tex.trash, (int) x, (int) y, null);
	}

	// draws hit box for trash
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	// gets position of trash sprite
	public double getY() {
		return y;
	}

	public double getX() {
		return x;
	}
}
