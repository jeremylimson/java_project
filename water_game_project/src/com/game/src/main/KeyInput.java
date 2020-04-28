/**
 * Description : keyboard detection for faster keystroke response time
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	Game game;

	public KeyInput(Game game) {
		this.game = game;
	}

	// these two methods are "turned on/off" by keystroke and directs program to
	// methods in Game class
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
}
