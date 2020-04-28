/**
 * Description : plays background music as a thread running simultaneously as game thread
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.audio;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class MusicPlayer implements Runnable {

	private ArrayList<String> musicFiles;
	private int currentSongIndex;
	
	// obtains music file
	public MusicPlayer(String... files) {
		musicFiles = new ArrayList<String>();
		for (String file : files)
			musicFiles.add("./res/" + file + ".wav");
	}
	
	// plays audio file as a clip and allows clip to be manipulated
	// e.g. change the volume with gain control
	private void playSound(String fileName) {
		try {
			File soundFile = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class,  format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10);
			clip.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// plays current song in index, acts like a sort of play list
	public void run() {
		playSound(musicFiles.get(currentSongIndex));
	}
	

}
