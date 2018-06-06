package org.gmnz.qet.clog;


import java.util.Collection;


interface ClfLineCollector {

	void receiveLine(String clfLine);




	void receiveLines(Collection<String> lines);
	
}
