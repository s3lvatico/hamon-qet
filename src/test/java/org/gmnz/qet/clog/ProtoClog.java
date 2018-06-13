package org.gmnz.qet.clog;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.gmnz.util.incubation.ServerSocketListener;
import org.gmnz.util.incubation.ServerSocketTask;


public class ProtoClog implements ServerSocketListener {

	ServerSocketTask serverSocketTask;
	GeneratorTask generatorTask;




	ProtoClog() {
		serverSocketTask = new ServerSocketTask("192.168.1.109", 19756, this);
		ConsoleClfLineCollector collector = new ConsoleClfLineCollector();
		ClfLineGenerator generator = new ClfLineGenerator();
		generatorTask = new GeneratorTask(generator, collector);
	}




	void run() {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(generatorTask);
		exec.execute(serverSocketTask);
	}




	@Override
	public void serverShutdownRequested() {
		generatorTask.stop();
	}




	public static void main(String[] args) {
		ProtoClog pc = new ProtoClog();
		pc.run();
	}


}
