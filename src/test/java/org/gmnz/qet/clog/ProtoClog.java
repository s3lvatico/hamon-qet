package org.gmnz.qet.clog;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.gmnz.util.ServerSocketListener;
import org.gmnz.util.ServerSocketTask;


public class ProtoClog implements ServerSocketListener {

	ServerSocketTask serverSocketTask;
	LogGenerationFacilityTask logGenerationFacilityTask;




	ProtoClog() {
		serverSocketTask = new ServerSocketTask("192.168.1.109", 19756, this);
		ConsoleClfLineCollector collector = new ConsoleClfLineCollector();
		ClfLineGenerator generator = new ClfLineGenerator();
		logGenerationFacilityTask = new LogGenerationFacilityTask(generator, collector);
	}




	void run() {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(logGenerationFacilityTask);
		exec.execute(serverSocketTask);
	}




	@Override
	public void serverShutdownRequested() {
		logGenerationFacilityTask.stop();
	}




	public static void main(String[] args) {
		ProtoClog pc = new ProtoClog();
		pc.run();

	}


}
