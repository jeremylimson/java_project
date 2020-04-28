/**
 * Description : analyzes game object and returns a hit box to encapsulate the object
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 */
package com.game.src.main;

import java.awt.Rectangle;

public class GameObject {
	
	public double x, y;
	
	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;		
	}
	
	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int) x, (int) y, width, height);
	}

}
