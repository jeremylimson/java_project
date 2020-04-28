/**
 * Description : implements a menu graphic when game is initially run
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("Karmatic Arcade", Font.PLAIN, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("H2 Oh No!", Game.WIDTH / 2, 100);
		
		Font fnt1 = new Font("Karmatic Arcade", Font.PLAIN, 26);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 7, playButton.y + 30);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 7, helpButton.y + 30);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 7, quitButton.y + 30);
		g2d.draw(quitButton);
		
	}

}
