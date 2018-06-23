package org.gmnz.qet.sampling;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;



class CommonLogFileSamplerTask implements Runnable {

	private SampleHolder sampleHolder;

	private BufferedReader in;

	private boolean samplingIsActive;

	private static final long SAMPLING_PERIOD_MS = 2000;



	public CommonLogFileSamplerTask(String targetFileName, SampleHolder sampleHolder) throws FileSamplerException {
		this.sampleHolder = sampleHolder;
		Path targetFilePath = Paths.get(targetFileName);
		try {
			in = new BufferedReader(new FileReader(targetFilePath.toFile()));
			samplingIsActive = true;
		}
		catch (FileNotFoundException e) {
			samplingIsActive = false;
			throw new FileSamplerException("can't initialize sampler", e);
		}
	}



	@Override
	public void run() {
		String sampledLine;
		while (samplingIsActive) {
			try {
				while ((sampledLine = in.readLine()) != null) {
					sampleHolder.holdSample(sampledLine);
				}
				sleep();
			}
			catch (IOException e) {
				e.printStackTrace();
				samplingIsActive = false;
			}
		}
	}



	private void sleep() {
		Thread.yield();
		try {
			TimeUnit.MILLISECONDS.sleep(SAMPLING_PERIOD_MS);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	void setSamplingActive(boolean samplingIsActive) {
		this.samplingIsActive = samplingIsActive;
	}

}
