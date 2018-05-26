package org.gmnz.qet;

public interface StatsReceiver {

	void raiseRank(String section, int httpStatusCode);

	void raiseRank(String section);

	void logSiteHit();

}
