package org.gmnz.qet.sampling;



import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



class SampleHolderImpl implements SampleHolder {

	BlockingQueue<String> q;



	SampleHolderImpl() {
		q = new LinkedBlockingQueue<>();
	}



	@Override
	public void holdSample(String sampledString) {
		try {
			q.put(sampledString);
		}
		catch (InterruptedException e) {
			// SMTODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@Override
	public synchronized String getSample() {
		String sample = null;
		try {
			sample = q.take();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sample;
	}

}
