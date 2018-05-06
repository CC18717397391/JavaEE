package com.imooc.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent httpsessionevent) {
		System.out.println("sessionCreated");
	}

	public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
		System.out.println("sessionDestroyed");
	}

}
