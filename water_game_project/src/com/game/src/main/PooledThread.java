/**
 * Description : executes threads and terminates if task is complete
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

public class PooledThread extends Thread {
	
	private static IDAssigner threadID = new IDAssigner(1);
	
	private ThreadPool pool;
	
	public PooledThread(ThreadPool pool) {
		super(pool, "PooledThread-" + threadID.next());
		this.pool = pool;
	}
	
	public void run () {
		while(!isInterrupted()) {
			Runnable task = null;
			try {
				task = pool.getTask();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(task == null) return;
			
			try {
				task.run();
			} catch (Throwable t) {
				pool.uncaughtException(this, t);
			}
			
		}
	}

}
