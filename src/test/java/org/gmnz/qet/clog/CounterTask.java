package org.gmnz.qet.clog;


import java.util.concurrent.TimeUnit;


public class CounterTask implements Runnable {

	private boolean active;




	public CounterTask() {
		active = true;
	}




	public void setInactive() {
		active = false;
	}




	@Override
	public void run() {
		int count = 0;
		while (active) {
			System.out.printf("%d%n", ++count);
			Thread.yield();
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			}
			catch (InterruptedException e) {
				System.err.println("interrupted!");
			}
		}
	}

}
