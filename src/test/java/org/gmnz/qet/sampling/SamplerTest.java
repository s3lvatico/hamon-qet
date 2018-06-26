package org.gmnz.qet.sampling;


public class SamplerTest {

	private Sampler sampler;
	private SampleHolder sampleHolder;




	public SamplerTest() throws FileSamplerException {
		sampleHolder = new SampleHolderImpl();
		sampler = new Sampler("example.log", sampleHolder);
	}




	public static void main(String[] args) throws FileSamplerException {
		SamplerTest test = new SamplerTest();

	}
}
