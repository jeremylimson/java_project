/**
 * Description : handles object shot by player (rendering, movement, & collision)
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;

public class Cannonball extends GameObject implements EntityA {

	private double x;
	private double y;
	private double cannonY;
	private double cannonX;
	private Textures tex;

	public Cannonball(double x, double y, Textures tex) {
		super(x, y);
		cannonX = x;
		cannonY = y;
		this.tex = tex;
	}

	// when ball is shot, it moves up on the screen
	public void tick() {
		cannonY -= 10;
	}

	// render the ball
	public void render(Graphics g) {
		g.drawImage(tex.shoot, (int) cannonX, (int) cannonY, null);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}