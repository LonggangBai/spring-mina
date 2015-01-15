/**
 * 
 */
package com.easyway.spring.mina.client;



import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * @author longgangbai
 * 2015-1-14  下午4:28:51
 */

public class ClientHandler extends IoHandlerAdapter {
	private static ClientHandler samplMinaClientHandler = null;
	public static ClientHandler getInstances() {
		if (null == samplMinaClientHandler) {
			samplMinaClientHandler = new ClientHandler();
		}
		return samplMinaClientHandler;
	}

	public  ClientHandler() {

	}

	public void sessionOpened(IoSession session) throws Exception {
		session.write("客户端与服务器的会话打开了……");
		UserInfo text=new UserInfo();
		text.setName("梅竹寒香");
		text.setQQNum("972341215");
		session.write(text);
	}

	public void sessionClosed(IoSession session) {
	}

	public void messageReceived(IoSession session, Object message)
			throws Exception {
	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		System.out.println("客户端已经向服务器发送了："+arg1);
	}
}

