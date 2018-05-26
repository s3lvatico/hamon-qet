package org.gmnz.qet;

public class CommonLogFormatFileSampler implements Sampler {

	TextFileLineSamplerTask task;
	SampleHolder<String> sampleHolder;

	public CommonLogFormatFileSampler(String targetFileName) {
		sampleHolder = new SampleHolder<>();
		task = new TextFileLineSamplerTask(targetFileName, sampleHolder);
	}

	@Override
	public void start() {
		// SMTODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// SMTODO Auto-generated method stub
	}

	@Override
	public String getSample() {
		return sampleHolder.getSample();
	}

}
