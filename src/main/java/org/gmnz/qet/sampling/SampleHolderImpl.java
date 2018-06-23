package org.gmnz.qet.sampling;



import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



class SampleHolderImpl implements SampleHolder {

	private BlockingQueue<String> q;



	SampleHolderImpl() {
		q = new LinkedBlockingQueue<>();
	}



	@Override
	public void holdSample(String sampledString) {
		try {
			q.put(sampledString);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

	}



	@Override
	public synchronized String getSample() {
		try {
			return q.take();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
