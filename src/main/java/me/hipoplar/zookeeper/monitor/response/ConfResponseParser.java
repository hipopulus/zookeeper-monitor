package me.hipoplar.zookeeper.monitor.response;

public class ConfResponseParser extends ResponseParser {

	@Override
	protected void parseLine(String line) {
		String[] item = line.split("=");
		if (item.length == 2) {
			((ConfResponse) result).put(item[0], item[1]);
		}
	}

}
