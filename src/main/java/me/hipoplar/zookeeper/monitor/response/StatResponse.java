package me.hipoplar.zookeeper.monitor.response;

import java.util.HashMap;

public class StatResponse extends HashMap<String, Object> implements MonitorResponse {

	private static final long serialVersionUID = 1L;

	public static class Client extends HashMap<String, String> implements Comparable<Client> {

		private static final long serialVersionUID = 1L;

		@Override
		public int compareTo(Client o) {
			if (o == null) {
				return 1;
			}
			if (!(o instanceof Client)) {
				return 1;
			}
			if (o.get("client") == null) {
				return 1;
			}
			if (this.get("client") == null) {
				return 0;
			}
			String c1 = this.get("client");
			String c2 = o.get("client");
			int r = c1.substring(c1.indexOf("/") + 1, c1.indexOf(":")).compareTo(c2.substring(c2.indexOf("/") + 1, c2.indexOf(":")));
			if (r == 0) {
				r = c1.substring(c1.indexOf("[") + 1, c1.indexOf("]")).compareTo(c2.substring(c2.indexOf("[") + 1, c2.indexOf("]")));
				if (r == 0) {
					r = c1.substring(c1.indexOf(":") + 1, c1.indexOf("[")).compareTo(c2.substring(c2.indexOf(":") + 1, c2.indexOf("[")));
				}
			}
			return r;
		}
	}
}
