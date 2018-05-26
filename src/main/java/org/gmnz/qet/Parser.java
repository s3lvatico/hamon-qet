package org.gmnz.qet;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.gmnz.qet.util.Formatting;

class Parser {

	private static final String PARSE_REGEXP = "(?<ipAddress>.*?) (?<rfc1413id>.*?) (?<userId>.*?) \\[(?<timestamp>.*?)\\] "
			+ "\"(?<httpMethod>.*?) (?<requestUrl>.*?) HTTP/.*?\" (?<httpStatusCode>[0-9]*?) (?<bytesTransferred>.*?)$";

	private static final String SECTION_REGEXP = "/([^/]*)";

	private final Pattern ptnLogLine;
	private final Pattern ptnSection;

	private static final Logger log = Logger.getLogger(Parser.class);

	public Parser() {
		ptnLogLine = Pattern.compile(PARSE_REGEXP);
		ptnSection = Pattern.compile(SECTION_REGEXP);
	}

	LogLineDto parseLogLine(String logLine) {
		Matcher mLogLineTokens = ptnLogLine.matcher(logLine);
		boolean found = mLogLineTokens.find();
		Matcher mSection;
		if (found) {
			LogLineDto dto = new LogLineDto();
			dto.ipAddress = mLogLineTokens.group("ipAddress");
			dto.rfc1413id = mLogLineTokens.group("rfc1413id");
			dto.userId = mLogLineTokens.group("userId");

			dto.timestamp = parseTimestamp(mLogLineTokens.group("timestamp"));
			dto.httpMethod = mLogLineTokens.group("httpMethod");

			String requestUrl = mLogLineTokens.group("requestUrl");
			dto.requestUrl = requestUrl;
			mSection = ptnSection.matcher(requestUrl);
			mSection.find();
			dto.section = mSection.group(1).length() != 0 ? mSection.group(1) : "ROOT";
			dto.httpStatusCode = parseNumericValue(mLogLineTokens.group("httpStatusCode"));
			dto.bytesTransferred = parseNumericValue(mLogLineTokens.group("bytesTransferred"));
			return dto;
		}
		else {
			log.warn("unparseable log line: <" + logLine + ">");
			return null;
		}
	}

	private Date parseTimestamp(String timestamp) {
		try {
			return Formatting.CLF_DF.parse(timestamp);
		}
		catch (ParseException e) {
			// SMTODO setta flag di errore appropriata
			e.printStackTrace();
			return null;
		}
	}

	private Integer parseNumericValue(String s) {
		try {
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			// TODO setta flag di errore appropriata
			return null;
		}
	}

}
