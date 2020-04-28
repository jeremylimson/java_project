/**
 * Description : determines collision detection based on hit boxes drawn around each sprite
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Physics {

	public static boolean Collision(EntityA enta, EntityB entb) {

		if (enta.getBounds().intersects(entb.getBounds())) {
			return true;
		}

		return false;
	}

	// overload collision so that there is detection for both lists A and B
	public static boolean Collision(EntityB entb, EntityA enta) {

		if (entb.getBounds().intersects(enta.getBounds())) {
			return true;
		}

		return false;
	}
}
