package com.liyuan3210.web.comm.service;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener  {
	/**
	 * 当前在线人数
	 */
	public static long count=0;
	/**
	 * session Created
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		count++;
	}
	/**
	 * session Destroyed
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		count--;
	}

}
