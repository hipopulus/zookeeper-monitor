package me.hipoplar.zookeeper.monitor.host;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

import me.hipoplar.zookeeper.monitor.response.MonitorResponse;
import me.hipoplar.zookeeper.monitor.response.ResponseParser;

public class ZookeeperHost {
	
	private String host;
	private int port;

	public ZookeeperHost(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public MonitorResponse command(ZookeeperCommand command) {
		ResponseParser parser = ResponseParser.parser(command);
		return parser.parse(command(host, port, command));
	}
	
	private static String command(String host, int port, ZookeeperCommand command) {
		Socket socket = null;
		try {
			socket = new Socket();
			socket.setSoLinger(false, 10);
			socket.setSoTimeout(20000);
			socket.connect(new InetSocketAddress(host, port));
			IOUtils.write(command.name(), socket.getOutputStream());
			return IOUtils.toString(socket.getInputStream());
		} catch (Exception e) {
			return "Zookeeper instance not found.";
		} finally {
			IOUtils.closeQuietly(socket);
		}
	}
}
