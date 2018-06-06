package org.gmnz.qet.clog;


import java.util.Collection;


public class ConsoleClfLineCollector implements ClfLineCollector {

	long t0 = System.currentTimeMillis();
	long elapsed;




	@Override
	public void receiveLine(String clfLine) {
		elapsed = System.currentTimeMillis() - t0;
		System.out.printf("@%7d received: <%s>%n", elapsed, clfLine);

	}




	@Override
	public void receiveLines(Collection<String> lines) {
		elapsed = System.currentTimeMillis() - t0;
		for (String clfLine : lines) {
			System.out.printf("@%7d received> %s%n", elapsed, clfLine);
		}
	}

}
