/**
 * Description : grabs textures from sprite sheet and renders them into the game
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 */
package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {

	public BufferedImage player, trash, shoot, hostile;

	private SpriteSheet ss;

	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());

		getTextures();
	}

	private void getTextures() {
		player = ss.grabImage(2, 2, 32, 32);
		trash = ss.grabImage(3, 1, 32, 32);
		shoot = ss.grabImage(3, 2, 32, 32);
		hostile = ss.grabImage(1, 2, 32, 32);
	}
}
