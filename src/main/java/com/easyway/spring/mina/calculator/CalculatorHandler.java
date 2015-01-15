/**
 * 
 */
package com.easyway.spring.mina.calculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Apache MINA 2.0 简单应用示例及与Spring的集成
 * 
 * 基于Apache MINA 的网络应用有三个层次，分别是 I/O 服务、I/O 过滤器和 I/O 处理器： 1. I/O 服务：I/O 服务用来执行实际的
 * I/O 操作。Apache MINA 2.0 已经提供了一系列支持不同协议的 I/O 服务，如
 * TCP/IP、UDP/IP、串口和虚拟机内部的管道等。我们也可以实现自己的 I/O 服务。 2. I/O 过滤器：I/O
 * 服务能够传输的是字节流，而上层应用需要的是特定的对象与数据结构。I/O 过滤器用来完成这两者之间的转换。I/O
 * 过滤器的另外一个重要作用是对输入输出的数据进行处理，满足横切的需求。多个 I/O 过滤器串联起来，形成 I/O 过滤器链。 3. I/O 处理器：I/O
 * 处理器用来执行具体的业务逻辑, 对接收到的消息执行特定的处理。
 * 
 * 应用示例中要实现I/O 处理器及I/O 服务。
 * 
 * 开发环境: Windows 7, Apache MINA 2.0.4, eclipse
 * 
 * A. 实现I/O 处理器 I/O 处理器需要实现
 * org.apache.mina.core.service.IoHandler接口或者继承自org.apache
 * .mina.core.service.IoHandlerAdapter.
 * 
 * @author longgangbai 2015-1-14 下午4:39:01
 */
public class CalculatorHandler extends IoHandlerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(CalculatorHandler.class);

    private ScriptEngine jsEngine = null;

    public CalculatorHandler() {
	ScriptEngineManager seManager = new ScriptEngineManager();
	jsEngine = seManager.getEngineByName("JavaScript");

	if (jsEngine == null) {
	    throw new RuntimeException("Can not find out the javascript engine.");
	}
    }

    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	LOG.warn(cause.getMessage(), cause);
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
	String expression = message.toString();
	if ("exit".equalsIgnoreCase(expression.trim()) || "quit".equalsIgnoreCase(expression.trim())) {
	    session.close(true);
	    return;
	}
	try {
	    Object result = jsEngine.eval(expression);
	    session.write(result.toString());
	} catch (ScriptException e) {
	    LOG.error(e.getMessage(), e);
	    session.write("Error script expression! Please check it again.");
	}
    }
}
