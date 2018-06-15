package org.gmnz.qet.clog;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.gmnz.util.incubation.ServerSocketListener;
import org.gmnz.util.incubation.ServerSocketTask;
import org.gmnz.util.incubation.SocketUtil;


public class CounterMain implements ServerSocketListener {

	CounterTask counterTask;
	private ServerSocketTask serverSocketTask;




	CounterMain() {
		counterTask = new CounterTask();
		serverSocketTask = new ServerSocketTask("localhost", 6675, this);
	}




	void execute() {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(serverSocketTask);
		exec.execute(counterTask);
		exec.shutdown();
	}




	@Override
	public void serverShutdownRequested() {
		counterTask.setInactive();
	}




	private static void stopCounter() {
		try {
			SocketUtil.execute("localhost", 6675, ORDER_66);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}




	public static void main(String[] args) {
		if (args.length == 1 && args[0].equals("stop")) {
			CounterMain.stopCounter();
			System.exit(0);
		}
		new CounterMain().execute();
	}


}
