package me.hipoplar.zookeeper.monitor.response;

import java.util.HashMap;
import java.util.Map;

public class ConsResponseParser extends ResponseParser {

	@Override
	protected void parseLine(String line) {
		Map<String, String> items = new HashMap<String, String>();
		String client = line.substring(0, line.lastIndexOf("]") + 1);
		if (client == null || client.isEmpty()) {
			return;
		}
		items.put("client", client);
		String clientInfo = line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(")"));
		String[] infoItems = clientInfo.split(",");
		for (String infoItem : infoItems) {
			String[] pair = infoItem.split("=");
			if (pair.length == 2) {
				items.put(pair[0], pair[1]);
			}
		}
		((ConsResponse) result).add(items);
	}

}
