/**
 * 
 */
package com.easyway.spring.mina.calculator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现I/O 服务：在示例中使用 TCP/IP 协议，需要在指定端口（端口号9876）监听，接受客户端的连接。
 * 
 * 
 * 说明: 过滤器只有在添加到过滤器链中的时候才起作用。过滤器链是过滤器的容器。过滤器链与 I/O 会话是一一对应的关系。
 * org.apache.mina.core.filterchain.IoFilterChain是 Apache MINA 中过滤器链的接口，其中
 * 提供了一系列方法对其中包含的过滤器进行操作，包括查询、添加、删除和替换等。 I/O 事件通过过滤器链之后会到达 I/O 处理器。I/O 处理器中与 I/O
 * 事件对应的方法会被调用。 Apache MINA 中 org.apache.mina.core.service.IoHandler是 I/O
 * 处理器要实现的接口， 一般情况下，只需要继承自
 * org.apache.mina.core.service.IoHandlerAdapter并覆写所需方法即可。
 * 
 * @author longgangbai 2015-1-14 下午4:39:43
 */
public class CalculatorServer {
    private static final int PORT = 9876;

    private static Logger log = LoggerFactory.getLogger(CalculatorServer.class);

    public static void main(String args[]) throws IOException {
	IoAcceptor acceptor = new NioSocketAcceptor();
	acceptor.getFilterChain().addLast("logger", new LoggingFilter());
	acceptor.getFilterChain().addLast("codec2012", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

	acceptor.setHandler(new CalculatorHandler());
	acceptor.bind(new InetSocketAddress(PORT));

	log.info("Calculator server has started up.... port: " + PORT);
    }
}
