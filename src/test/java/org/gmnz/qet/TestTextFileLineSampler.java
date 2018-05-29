package org.gmnz.qet;



import java.util.concurrent.TimeUnit;

import org.gmnz.qet.TextFileLineSamplerTask;
import org.junit.Assert;
import org.junit.Test;



public class TestTextFileLineSampler {

	@Test
	public void someTest() throws InterruptedException {
		final TextFileLineSamplerTask sampler = new TextFileLineSamplerTask("dati.txt");
		final int TEST_DURATION_MS = 10000;
		Thread t = new Thread(sampler);
		t.setDaemon(true);
		t.start();

		Runnable poller = new Runnable() {

			@Override
			public void run() {
				long startTime = System.currentTimeMillis();

				while (System.currentTimeMillis() - startTime < TEST_DURATION_MS) {
					String sampledLine = sampler.getSampledLogLine();
					if (sampledLine == null) {
						sampledLine = "[NULL]";
					}
					System.out.printf("sampled line: <%s>%n", sampledLine);
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("-- tempo scaduto");
			}
		};

		Thread pollerThread = new Thread(poller);
		pollerThread.start();

		System.out.println("errors? " + sampler.errorsDetected());
		Assert.assertFalse(sampler.errorsDetected());
		TimeUnit.MILLISECONDS.sleep(TEST_DURATION_MS);
	}

}
