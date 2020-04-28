package com.game.src.main;

import java.util.Timer;
import java.util.TimerTask;

public class GameClock {
	
	public static long timer = 60;
	
	public static int setClock() {
		GameClock.runTimer();
		return (int) timer;
	}
	
	// FIXME
	public static void runTimer() {
		Timer clk = new Timer();
		
		
		TimerTask task = new TimerTask() {
			public void run() {
				timer--;	// clock decrements
				System.out.println(timer);
			}
		};
		clk.schedule(task, 0, 1000);	// timer task executes every second	
	}
}