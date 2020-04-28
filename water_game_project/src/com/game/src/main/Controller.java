/**
 * Description : handles entity spawning, movement, and shooting behavior
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Controller {

	private LinkedList<Player> ea = new LinkedList<Player>();
	private LinkedList<Enemy> eb = new LinkedList<Enemy>();
	private LinkedList<Cannonball> ec = new LinkedList<Cannonball>();

	Random r = new Random();

	EntityA enta;
	EntityB entb;
	EntityA entc;

	private Textures tex;

	public Controller(Textures tex, Game game) {
		this.tex = tex;
	}

	// generates enemy trash count
	public void createEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count; i++) {
			addEntity(new Enemy(r.nextInt(640), -10, tex));
		}
	}

	public void tick() {
		// A CLASS
		for (int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);

			enta.tick();
		}

		// B CLASS
		for (int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);

			entb.tick();
		}

		// C CLASS
		for (int i = 0; i < ec.size(); i++) {
			entc = ec.get(i);

			entc.tick();
		}
	}

	public void render(Graphics g) {
		// A CLASS
		for (int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);

			enta.render(g);
		}

		// B CLASS
		for (int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);

			entb.render(g);
		}

		// C CLASS
		for (int i = 0; i < ec.size(); i++) {
			entc = ec.get(i);

			entc.render(g);
		}
	}

	// add or remove player
	public void removeEntity(Player block) {
		ea.remove(block);
	}

	// add or remove cannon ball
	public void addEntity(Cannonball block) {
		ec.add(block);
	}

	public void removeEntity(Cannonball block) {
		ec.remove(block);
	}

	// add or remove trash
	public void addEntity(Enemy block) {
		eb.add(block);
	}

	public void removeEntity(Enemy block) {
		eb.remove(block);
	}

	public LinkedList<Player> getEntityA() {
		return ea;
	}

	public LinkedList<Enemy> getEntityB() {
		return eb;
	}
}
