package com.imooc.listener;

import java.util.Enumeration;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
//@WebListener("This is My Servlet 3.0 Listener")
public class MyServletRequestListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent servletrequestevent) {
		System.out.println("requestDestroyed ");
	}

	public void requestInitialized(ServletRequestEvent servletrequestevent) {
		String name = servletrequestevent.getServletRequest().getParameter("name");
		/*
		 * 不知道request绑定名的情况下,依旧可以通过getParameterNames()的枚举对象获取参数值
		 * Enumeration name1 = servletrequestevent.getServletRequest().getParameterNames();
		 */
		
		System.out.println("requestInitialized name:"+name);
	}

}
