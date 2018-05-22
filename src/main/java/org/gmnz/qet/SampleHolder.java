package org.gmnz.qet;


import java.util.LinkedList;
import java.util.Queue;


class SampleHolder<ST> {

	private Queue<ST> _q;




	SampleHolder() {
		_q = new LinkedList<>();
	}




	int getNumSamples() {
		return _q.size();
	}




	int addSample(ST sample) {
		_q.add(sample);
		return getNumSamples();
	}




	ST getSample() {
		return _q.poll();
	}
}
