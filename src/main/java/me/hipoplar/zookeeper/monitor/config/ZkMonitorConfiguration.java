package me.hipoplar.zookeeper.monitor.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.hipoplar.zookeeper.monitor.host.ZookeeperHost;

@Configuration
@EnableConfigurationProperties(ZkMonitorProperties.class)
public class ZkMonitorConfiguration {
	
	@Autowired
	private ZkMonitorProperties zkMonitorProperties;

	@Bean
	public ZookeeperHost zookeeperHost() {
		String connectString = zkMonitorProperties.getConnectString();
		return new ZookeeperHost(connectString.split(":")[0], Integer.valueOf(connectString.split(":")[1]));
	}
	
	@Bean
	public CuratorFramework zkClient() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework curator = CuratorFrameworkFactory.newClient(zkMonitorProperties.getConnectString(), retryPolicy);
		curator.start();
		return curator;
	}
}
