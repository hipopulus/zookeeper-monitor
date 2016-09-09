package me.hipoplar.zookeeper.monitor.response;

public class DumpResponseParser extends ResponseParser{

	@Override
	protected void parseLine(String line) {
		if (line.contains("/")) {
			((DumpResponse) result).add(line);
		}
	}
}
