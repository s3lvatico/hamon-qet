package org.gmnz.qet.clog;


import java.util.List;
import java.util.concurrent.TimeUnit;


class GeneratorTask implements Runnable {

	private final ClfLineGenerator generator;
	private final ClfLineCollector collector;

	private static final long SLEEP_TIME_MS = 1000;
	private static final double DUTY_CYCLE = .35;
	private static final double CYCLE_TIME_MS = 30000;

	private static final int LOW_TRAFFIC = 2;
	private static final int HIGH_TRAFFIC = 15;

	private boolean active;

	private long tsStart;




	GeneratorTask(ClfLineGenerator generator, ClfLineCollector collector) {
		this.generator = generator;
		this.collector = collector;
		active = true;
	}




	void stop() {
		active = false;
	}




	@Override
	public void run() {
		tsStart = System.currentTimeMillis();
		while (active) {
			sleep();
			int linesToGenerate = getTrafficRate();
			List<String> logLines = generator.generateClfLines(linesToGenerate);
			collector.receiveLines(logLines);
		}
	}




	private int getTrafficRate() {
		long elapsed = System.currentTimeMillis() - tsStart;
		double cycleProgress = (elapsed % CYCLE_TIME_MS) / (double) CYCLE_TIME_MS;
		return cycleProgress < DUTY_CYCLE ? LOW_TRAFFIC : HIGH_TRAFFIC;
	}




	private void sleep() {
		Thread.yield();
		try {
			TimeUnit.MILLISECONDS.sleep(SLEEP_TIME_MS);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}




	@Deprecated
	public static void main(String[] args) throws Exception {
		final int CYCLE_TIME_MS = 180000;
		long t0 = System.currentTimeMillis();
		System.out.printf("t0 <%d>%n", t0);
		TimeUnit.MILLISECONDS.sleep(5000);
		long t1 = System.currentTimeMillis();
		System.out.printf("t1 <%d>%n", t1);
		long elapsed = t1 - t0;
		System.out.printf("elapsed <%d>%n", elapsed);
		double dutyCyclePosition = (elapsed % CYCLE_TIME_MS) / (double) CYCLE_TIME_MS * 100;
		System.out.printf("dutyCyclePosition <%f>%n", dutyCyclePosition);
	}
}