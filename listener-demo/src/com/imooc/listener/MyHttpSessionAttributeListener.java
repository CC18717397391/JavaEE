package com.imooc.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent httpsessionbindingevent) {
		System.out.println("HttpSession_attributeAdded:"+httpsessionbindingevent.getName());
	}

	public void attributeRemoved(HttpSessionBindingEvent httpsessionbindingevent) {
		System.out.println("HttpSession_attributeRemoved:"+httpsessionbindingevent.getName());
	}

	public void attributeReplaced(HttpSessionBindingEvent httpsessionbindingevent) {
		System.out.println("HttpSession_attributeReplaced:"+httpsessionbindingevent.getName());
	}

}
