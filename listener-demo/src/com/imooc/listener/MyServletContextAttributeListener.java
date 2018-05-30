package com.imooc.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
//@WebListener("This is My Servlet 3.0 Listener")
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	public void attributeAdded(ServletContextAttributeEvent servletcontextattributeevent) {
		System.out.println("ServletContext_attributeAdded:"+servletcontextattributeevent.getName());
	}

	public void attributeRemoved(ServletContextAttributeEvent servletcontextattributeevent) {
		System.out.println("ServletContext_attributeRemoved:"+servletcontextattributeevent.getName());

	}

	public void attributeReplaced(ServletContextAttributeEvent servletcontextattributeevent) {
		System.out.println("ServletContext_attributeReplaced:"+servletcontextattributeevent.getName());

	}

}
