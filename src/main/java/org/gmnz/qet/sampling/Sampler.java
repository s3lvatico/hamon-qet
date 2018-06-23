package org.gmnz.qet.sampling;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Sampler {

// private SampleHolder sampleHolder;

	private CommonLogFileSamplerTask samplerTask;

	private ExecutorService exec;



	public Sampler(String targetFileName, SampleHolder sampleHolder) throws FileSamplerException {
// this.sampleHolder = sampleHolder;
		samplerTask = new CommonLogFileSamplerTask(targetFileName, sampleHolder);
		exec = Executors.newSingleThreadExecutor();
	}



	public void start() {
		exec.execute(samplerTask);
		exec.shutdown();
	}



	public void stop() {
		samplerTask.setSamplingActive(false);
	}

}
