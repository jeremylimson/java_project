/**
 * Description : enables mouse input on menu splash screen
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		// Play Button
		if(mx >= Game.WIDTH / 2+ 120 && mx <= Game.WIDTH / 2 + 220) {
			if(my >= 150 && my <= 200) {
				// Pressed Play Button
				Game.State = Game.STATE.GAME;
			}
		}
		
		// Help Button
		if(mx >= Game.WIDTH / 2+ 120 && mx <= Game.WIDTH / 2 + 220) {
			if(my >= 250 && my <= 300) {
				// Pressed Play Button
				Game.State = Game.STATE.HELP;
			}
		}
		
		// Quit Button
		if(mx >= Game.WIDTH / 2+ 120 && mx <= Game.WIDTH / 2 + 220) {
			if(my >= 350 && my <= 400) {
				// Pressed Play Button
				System.exit(1);
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
