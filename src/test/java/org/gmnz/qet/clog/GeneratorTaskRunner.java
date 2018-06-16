package org.gmnz.qet.clog;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class GeneratorTaskRunner {

	LogGenerationFacilityTask task;

	final int taskDurationSeconds;




	GeneratorTaskRunner(int taskDurationSeconds) {
		task = new LogGenerationFacilityTask(new ClfLineGenerator(), new ConsoleClfLineCollector());
		this.taskDurationSeconds = taskDurationSeconds;
	}




	GeneratorTaskRunner() {
		this(5);
	}




	void exerciseGenerator() {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(task);
		exec.shutdown();
		try {
			TimeUnit.SECONDS.sleep(taskDurationSeconds);
			task.stop();
			exec.awaitTermination(taskDurationSeconds, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end of task");
	}




	public static void main(String[] args) {
		GeneratorTaskRunner runner = new GeneratorTaskRunner();
		runner.exerciseGenerator();
	}

}
