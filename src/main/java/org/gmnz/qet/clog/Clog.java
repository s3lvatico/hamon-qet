package org.gmnz.qet.clog;



import org.gmnz.util.ServerSocketListener;
import org.gmnz.util.ServerSocketTask;
import org.gmnz.util.SocketUtil;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Clog implements ServerSocketListener {

	private static final String HOSTNAME = "localhost";

	private static final int PORT = 19756;

	FileClfLineCollector collector;

	LogGenerationFacilityTask logGenerationFacilityTask;

	private ExecutorService exec;

	private ServerSocketTask serverSocketTask;



	Clog(String targetFileName) {
		try {
			collector = new FileClfLineCollector(targetFileName);
		}
		catch (LineCollectorCreationException e) {
			e.printStackTrace(); // ma non dovrebbe accadere perché i controlli li faccio prima
		}
		exec = Executors.newCachedThreadPool();
	}



	void start() {
		serverSocketTask = new ServerSocketTask(HOSTNAME, PORT, this);
		logGenerationFacilityTask = new LogGenerationFacilityTask(new ClfLineGenerator(), collector);

		exec.execute(serverSocketTask);
		exec.execute(logGenerationFacilityTask);

		exec.shutdown();
	}



	@Override
	public void serverShutdownRequested() {
		logGenerationFacilityTask.stop();
		collector.shutdown();
	}



	private static void shutdown() {
		try {
			SocketUtil.execute(HOSTNAME, PORT, ServerSocketListener.ORDER_66);
		}
		catch (IOException e) {
			System.err.println(
					"Could not properly issue the shutdown command due to a " + e.getClass().getName() + " being thrown.");
		}
	}



	public static void main(String[] args) {
		// TODO logica di avviamento
		// TODO logica di arresto
	}
}