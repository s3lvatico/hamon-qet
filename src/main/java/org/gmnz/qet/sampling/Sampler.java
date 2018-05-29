package org.gmnz.qet.sampling;



public class Sampler {

	private SampleHolderImpl sampleHolder;

	private CommonLogFileSamplerTask samplerTask;



	public Sampler(String targetFileName) {
		sampleHolder = new SampleHolderImpl();
	}
}
