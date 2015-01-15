/**
 * 
 */
package com.easyway.spring.mina.server;



import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.easyway.spring.mina.client.UserInfo;

/**
 * @author longgangbai
 * 2015-1-14  下午4:27:10
 */
public class ServerHandler extends IoFilterAdapter implements IoHandler {
	private static ServerHandler samplMinaServerHandler = null;

	public static ServerHandler getInstances() {
		if (null == samplMinaServerHandler) {
			samplMinaServerHandler = new ServerHandler();
		}
		return samplMinaServerHandler;
	}

	private ServerHandler() {

	}

	// 当连接后打开时触发此方法，一般此方法与 sessionCreated 会被同时触发
	public void sessionOpened(IoSession session) throws Exception {
	}
	public void sessionClosed(IoSession session) {
	}
	public void messageReceived(IoSession session, Object message)
			throws Exception {	
		if (message instanceof UserInfo) {
			UserInfo text = (UserInfo) message;
			System.out.println("服务器接收到从客户端的姓名："+text.getName());
			System.out.println("服务器接收到从客户端的QQ："+text.getQQNum());
		} 
	}

	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {

	}

	// 当消息传送到客户端后触发
	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		
	}

	// 当一个新客户端连接后触发此方法.
	public void sessionCreated(IoSession arg0) throws Exception {
		
	}

	// 当连接空闲时触发此方法.
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		
	}

}
