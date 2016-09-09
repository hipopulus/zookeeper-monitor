package me.hipoplar.zookeeper.monitor.response;

import java.util.Map;
import java.util.TreeSet;

import me.hipoplar.zookeeper.monitor.response.StatResponse.Client;

public class StatResponseParser extends ResponseParser {

	@Override
	protected void parseLine(String line) {
		if (line.startsWith(" /")) {
			Object clients = ((StatResponse) result).get("Clients");
			if (clients == null) {
				clients = new TreeSet<Map<String, String>>();
				((StatResponse) result).put("Clients", clients);
			}
			Client items = new Client();
			String client = line.substring(0, line.lastIndexOf("]") + 1);
			items.put("client", client);
			String clientInfo = line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(")"));
			String[] infoItems = clientInfo.split(",");
			for (String infoItem : infoItems) {
				String[] pair = infoItem.split("=");
				if (pair.length == 2) {
					items.put(pair[0], pair[1]);
				}
			}
			((TreeSet<Map<String, String>>)clients).add(items);
		} else if (line.contains("Clients")) {
			Object clients = ((StatResponse) result).get("Clients");
			if (clients == null) {
				clients = new TreeSet<Map<String, String>>();
				((StatResponse) result).put("Clients", clients);
			}
		} else {
			String[] pair = line.split(": ");
			if (pair.length == 2) {
				if (pair[0].contains("Zookeeper version")) {
					pair[0] = "version";
				} else if (pair[0].contains("Node count")) {
					pair[0] = "nodeCount";
				} else if (pair[0].contains("Latency min/avg/max")) {
					pair[0] = "latency";
				}
				((StatResponse) result).put(pair[0], pair[1]);
			}
		}
	}

}
