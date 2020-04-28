package com.game.src.main;

import java.util.Timer;
import java.util.TimerTask;

public class Clock {
	
	public static void main(String[] args) {
	
		Clock clock = new Clock();
		clock.runTimer();
		
	}
	
	Timer timer = new Timer();
	int i = 60;	// start clock at 60 seconds
	
	TimerTask task = new TimerTask() {
		public void run() {
			i--;	// clock counts down
			System.out.println(i);
		}
	};
	
	public void runTimer() {
		timer.schedule(task, 0, 1000);	// timer task executes every second
	}
	
}