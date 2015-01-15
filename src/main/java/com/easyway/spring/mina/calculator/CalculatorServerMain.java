/**
 * 
 */
package com.easyway.spring.mina.calculator;

import org.apache.mina.core.service.IoAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalculatorServerMain {
    private static Logger log = LoggerFactory.getLogger(CalculatorServerMain.class);

    public static void main(String[] args) {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-calculator.xml");
	IoAcceptor acceptor = (IoAcceptor) ctx.getBean("ioAcceptor");
	log.info("Calculator server has started up.... Local Address: " + acceptor.getLocalAddress().toString() + " Default Local Address: "
		+ acceptor.getDefaultLocalAddress().toString());
    }

}
