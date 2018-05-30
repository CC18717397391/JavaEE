package com.imooc.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
//@WebListener("This is My Servlet 3.0 Listener")
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

	public void attributeAdded(ServletRequestAttributeEvent servletrequestattributeevent) {
		System.out.println("ServletRequest_attributeAdded:"+servletrequestattributeevent.getName());
	}

	public void attributeRemoved(ServletRequestAttributeEvent servletrequestattributeevent) {
		System.out.println("ServletRequest_attributeRemoved:"+servletrequestattributeevent.getName());

	}

	public void attributeReplaced(ServletRequestAttributeEvent servletrequestattributeevent) {
		System.out.println("ServletRequest_attributeReplaced:"+servletrequestattributeevent.getName());
	}

}
