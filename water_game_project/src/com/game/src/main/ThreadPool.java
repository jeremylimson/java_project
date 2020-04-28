/**
 * Description : creates thread groups to be executed simultaneously
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 * 
 */
package com.game.src.main;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool extends ThreadGroup {

	private static IDAssigner poolID = new IDAssigner(1);
	
	private boolean alive;
	private List<Runnable> taskQueue;
	private int id;
	
	// number of threads is the number of tasks that can run at the same time
	public ThreadPool(int numThreads) {
		super("ThreadPool-" + poolID.next());
		this.id = poolID.getCurrentID();
		setDaemon(true);	// exit thread group when main thread exits
		taskQueue = new LinkedList<Runnable>();
		alive = true;
		for (int i = 0; i < numThreads; i++) {
			new PooledThread(this).start();
		}
	}
	
	// closes thread and terminates all tasks associated with pool
	public synchronized void close() {
		if(!alive) return;
		alive = false;
		taskQueue.clear();
		interrupt();
	}
	
	// allows pending task to run after other tasks are terminated
	public void join() {
		synchronized(this) {
			alive = false;
			notifyAll();
		}
		
		Thread [] threads = new Thread[activeCount()];
		int count = enumerate(threads);
		for (int i = 0; i < count; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}
	}
	
	// threads can only have one task at a time
	protected synchronized void runTask(Runnable task) {
		if(!alive) throw new IllegalStateException("ThreadPool-" + id + " is dead");
		if(task != null) {
			taskQueue.add(task);
			notify();
		}
	}
	
	
	
	public synchronized Runnable getTask() throws InterruptedException{
		while(taskQueue.size() == 0) {
			if(!alive) return null;
			wait();
		}
		return taskQueue.remove(0);
	}
}
