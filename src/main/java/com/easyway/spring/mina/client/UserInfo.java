/**
 * 
 */
package com.easyway.spring.mina.client;

/**
 * @author longgangbai 2015-1-14 下午4:29:19
 */
public class UserInfo implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String QQNum;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getQQNum() {
	return QQNum;
    }

    public void setQQNum(String qQNum) {
	QQNum = qQNum;
    }

}
