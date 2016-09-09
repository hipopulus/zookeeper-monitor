package me.hipoplar.zookeeper.monitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ZkMonitorProperties.PREFIX)
public class ZkMonitorProperties {
	public static final String PREFIX = "zookeeper.monitor";
	private String connectString = "127.0.0.1:2181";
	public String getConnectString() {
		return connectString;
	}
	public void setConnectString(String connectString) {
		this.connectString = connectString;
	}
}
