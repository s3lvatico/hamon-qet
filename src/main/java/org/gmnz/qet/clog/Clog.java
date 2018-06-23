package org.gmnz.qet.clog;



import org.gmnz.util.ServerSocketListener;
import org.gmnz.util.ServerSocketTask;
import org.gmnz.util.SocketUtil;

import java.io.IOException;
import java.io.PrintStream;
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



	private static void printSynopsis(PrintStream out) {
		out.println("usage:");
		out.println("\tClog <filename>");
		out.println("\t\twrite random CLF log lines to target file");
		out.println("\tClog -stop");
		out.println("\t\tshut down the random log lines generator");
	}



	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("ERROR: wrong number of arguments");
			printSynopsis(System.err);
			System.exit(1);
		}
		if (args[0].equalsIgnoreCase("--stop")) {
			System.out.println("stop invoked");
			shutdown();
		}
		else {
			String targetFileName = args[0];
			System.out.printf("generating random CLF lines into file <%s>%n", targetFileName);
			Clog c = new Clog(targetFileName);
			c.start();
		}
	}
}