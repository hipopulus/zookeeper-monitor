package me.hipoplar.zookeeper.monitor.response;

import org.apache.commons.lang3.StringUtils;

import me.hipoplar.zookeeper.monitor.host.ZookeeperCommand;

public abstract class ResponseParser {
	protected MonitorResponse result;
	
	protected ResponseParser() {
		
	}
	
	public static ResponseParser parser(ZookeeperCommand command) {
		ResponseParser parser = null;
		switch (command) {
		case conf:
			parser = new ConfResponseParser();
			parser.result = new ConfResponse();
			return parser;
		case cons:
			parser = new ConsResponseParser();
			parser.result = new ConsResponse();
			return parser;
		case dump:
			parser = new DumpResponseParser();
			parser.result = new DumpResponse();
			return parser;
		case stat:
			parser = new StatResponseParser();
			parser.result = new StatResponse();
			return parser;
		default:
			parser = new CommonResponseParser();
			parser.result = new CommonResponse();
			return parser;
		}
	}
	
	protected abstract void parseLine(String line);
	
	public final MonitorResponse parse(String response) {
		String[] lines = lines(response);
		if (lines != null) {
			for (String line : lines) {
				if (!StringUtils.isBlank(line)) {
					parseLine(line);
				}
			}
			return result;
		}
		return null;
	}
	
	private static String[] lines(String response) {
		if (response == null) {
			return null;
		}
		return response.split("\n");
	}
}
