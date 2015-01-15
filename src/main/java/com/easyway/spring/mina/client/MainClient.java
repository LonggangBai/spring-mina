/**
 * 
 */
package com.easyway.spring.mina.client;



import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
/**
 * @author longgangbai
 * 2015-1-14  下午4:28:17
 */
public class MainClient {
	private static MainClient mainClient = null;
	NioSocketConnector connector = new NioSocketConnector();
	DefaultIoFilterChainBuilder chain = connector.getFilterChain();

	public static MainClient getInstances() {
		if (null == mainClient) {
			mainClient = new MainClient();
		}
		return mainClient;
	}

	private MainClient() {
		chain.addLast("myChin", new ProtocolCodecFilter(
				new ObjectSerializationCodecFactory()));
		connector.setHandler(new ClientHandler());
		connector.setConnectTimeout(30);
		ConnectFuture cf = connector.connect(new InetSocketAddress("localhost",
				8888));
	}

	public static void main(String args[]) {
		MainClient.getInstances();
	}
}
