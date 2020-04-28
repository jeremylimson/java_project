/**
 * Description assigns primary key of an entity
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 */
package com.game.src.main;

public class IDAssigner {

	private int baseID;
	
	public IDAssigner(int baseID) {
		this.baseID = baseID;
	}
	
	public int next() {
		return baseID++;
	}
	
	public int getCurrentID() {
		return baseID;
	}
}
