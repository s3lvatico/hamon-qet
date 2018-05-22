package org.gmnz.qet;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;



public class TextFileLineSampler implements Runnable {

	private SampleHolder<String> sampleHolder;
	private final Path targetFilePath;
	private final Charset charset;
	private final int samplingPeriod;
	private static final int DEFAULT_SAMPLING_PERIOD_MS = 3000;

	private boolean samplingIsActive;

	private boolean errorRaised;




	public TextFileLineSampler(String targetFileName) {
		charset = Charset.forName("UTF-8");
		samplingIsActive = true;
		targetFilePath = Paths.get(targetFileName);
		samplingPeriod = DEFAULT_SAMPLING_PERIOD_MS;
		sampleHolder = new SampleHolder<>();
		errorRaised = false;
	}




	@Override
	public void run() {
		try (BufferedReader reader = Files.newBufferedReader(targetFilePath, charset)) {
			String line = null;
			while (samplingIsActive) {
				while ((line = reader.readLine()) != null) {
					sampleHolder.addSample(line);
					Thread.yield();
					TimeUnit.MILLISECONDS.sleep(samplingPeriod);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			errorRaised = true;
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}




	public boolean errorsDetected() {
		return errorRaised;
	}




	public String getSampledLogLine() {
		return sampleHolder.getSample();
	}
}
