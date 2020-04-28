/**
 * Description : creates entity A that refers to player
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 */
package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityA {

	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	
}
