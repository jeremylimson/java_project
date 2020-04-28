/**
 * Description : this class loads an audio file and plays it while the game is running
 * this does not execute audio files as threads so only one sound effect plays at a time
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class AudioPlayer {

	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	public static Map<String, Music> soundMap = new HashMap<String, Music>();

	public static void load() {
		// load in all sounds
		try {
			// shoot sound effect
			soundMap.put("collect", new Music("res/collect_sound_effect.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			// trash collect sound effect
			soundMap.put("shoot", new Music("res/shoot_sound_effect.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			// player damage sound effect
			soundMap.put("hit", new Music("res/hit_sound_effect.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public static Music getSound(String key) {
		return soundMap.get(key);
	}
}
